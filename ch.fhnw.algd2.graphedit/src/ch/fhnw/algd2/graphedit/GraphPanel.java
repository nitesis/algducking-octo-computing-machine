package ch.fhnw.algd2.graphedit;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class GraphPanel extends JPanel implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = -8419706701369525541L;

	static class Node implements Comparable<Node> {
		double x, y;
		String lbl;
		boolean marked = false;

		public Node() {
			lbl = null;
			x = y = 0.0;
		}

		Node(String label, double x, double y) {
			lbl = label;
			this.x = x;
			this.y = y;
		}

		void paint(Graphics g, FontMetrics fm) {
			int x = (int)this.x;
			int y = (int)this.y;
			int w = fm.stringWidth(lbl) + 10;
			int h = fm.getHeight() + 4;
			g.setColor((marked) ? GraphPanel.selectColor : GraphPanel.nodeColor);
			g.fillRect(x - w / 2, y - h / 2, w, h);
			g.setColor(Color.black);
			g.drawRect(x - w / 2, y - h / 2, w - 1, h - 1);
			g.drawString(lbl, x - (w - 10) / 2, (y - (h - 4) / 2) + fm.getAscent());
		}

		boolean contains(int mx, int my, FontMetrics fm) {
			int nw = fm.stringWidth(lbl) + 10;
			int nh = fm.getHeight() + 4;
			int nx = (int)x - nw / 2;
			int ny = (int)y - nh / 2;

			nw += nx;
			nh += ny;
			return mx >= nx && mx <= nw && my >= ny && my <= nh;
		}

		@Override
		public boolean equals(Object o) {
			return (o instanceof Node) ? lbl.equals(((Node)o).lbl) : false;
		}

		@Override
		public int hashCode() {
			return lbl.hashCode();
		}

		@Override
		public int compareTo(Node n) {
				return lbl.compareTo(n.lbl);
		}

		@Override
		public String toString() {
			return lbl;
		}

	}

	static final Color fixedColor = Color.red;
	static final Color selectColor = Color.pink;
	static final Color edgeColor = Color.black;
	static final Color nodeColor = new Color(250, 220, 100);
	static final Color stressColor = Color.gray;
	static final Color arcColor = Color.black;

	static final int SELECTION_MODE = 0;
	static final int NODE_INSERT_MODE = 1;
	static final int EDGE_INSERT_MODE = 2;
	static final int EDGE_DELETE_MODE = 3;

	Graph<Node> graph;

	int name = 1;
	Node picked = null;
	Node from = null;

	int mode = NODE_INSERT_MODE;
	boolean autoName = true;

	Image offscreen;
	Dimension offscreensize;
	Graphics offgraphics;

	public GraphPanel(Graph<Node> model) {
		graph = model;
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	void addNode(String label, double x, double y) {
		Node n = new Node(label, x, y);
		graph.addVertex(n);
	}

	void addEdge(Node from, Node to, double weight) {
		if (graph instanceof WeightedGraph)
			((WeightedGraph<Node>)graph).addEdge(from, to, weight);
		else
			graph.addEdge(from, to);
	}

	void removeEdge(Node from, Node to) {
		graph.removeEdge(from, to);
	}

	String nextName() {
		return String.valueOf(name++);
	}

	@Override
	public synchronized void paint(Graphics g) {
		Dimension d = getSize();
		if ((offscreen == null) || (d.width != offscreensize.width)
				|| (d.height != offscreensize.height)) {
			offscreen = createImage(d.width, d.height);
			offscreensize = d;
			offgraphics = offscreen.getGraphics();
			offgraphics.setFont(getFont());
		}

		offgraphics.setColor(getBackground());
		offgraphics.fillRect(0, 0, d.width, d.height);

		Set<Node> nodes = graph.getVertices();
		Iterator<Node> vit = nodes.iterator();

		while (vit.hasNext()) {
			Node from = vit.next();
			Iterator<Node> eit = graph.getAdjacentVertices(from).iterator();

			while (eit.hasNext()) {
				Node to = eit.next();
				int x1 = (int)from.x;
				int y1 = (int)from.y;
				int x2 = (int)to.x;
				int y2 = (int)to.y;
				offgraphics.setColor(arcColor);
				offgraphics.drawLine(x1, y1, x2, y2);
				if (graph.isDirected()) {
					offgraphics.fillOval((x1 + x2 + 2 * x2) / 4 - 3,
							(y1 + y2 + 2 * y2) / 4 - 3, 7, 7);
				}
				if (graph instanceof WeightedGraph) {
					WeightedGraph<Node> wg = (WeightedGraph<Node>)graph;
					double w = wg.getEdgeWeight(from, to);
					if (wg.isDirected())
						offgraphics.drawString(String.valueOf(w), (x1 + x2 + 2 * x2) / 4 + 8,
								(y1 + y2 + 2 * y2) / 4 + 8);
					else
						offgraphics.drawString(String.valueOf(w), (x1 + x2) / 2 + 8,
								(y1 + y2) / 2 + 8);
				}

			}
		}
		FontMetrics fm = offgraphics.getFontMetrics();
		vit = nodes.iterator();
		while (vit.hasNext()) {
			Node n = vit.next();
			n.paint(offgraphics, fm);
		}
		g.drawImage(offscreen, 0, 0, null);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Node clicked = null;
		FontMetrics fm = offgraphics.getFontMetrics();
		Iterator<Node> it = graph.getVertices().iterator();
		while (it.hasNext() && clicked == null) {
			Node n = it.next();
			if (n.contains(e.getX(), e.getY(), fm))
				clicked = n;
		}
		switch (mode) {
		case SELECTION_MODE:
			if (clicked != null) {
				clicked.marked = !clicked.marked;
			}
			break;

		case EDGE_INSERT_MODE:
			if (clicked != null) {
				if (from == null) {
					from = clicked;
					from.marked = true;
				} else {
					if (from != clicked) {
						if (graph instanceof WeightedGraph) {
							String s = JOptionPane.showInputDialog("Enter weight:");
							double w = Double.parseDouble(s);
							addEdge(from, clicked, w);
						} else {
							addEdge(from, clicked, 0);
						}
					}
					from.marked = false;
					from = null;
				}
			}
			break;

		case EDGE_DELETE_MODE:
			if (clicked != null) {
				if (from == null) {
					from = clicked;
					from.marked = true;
				} else {
					if (from != clicked) {
						removeEdge(from, clicked);
					}
					from.marked = false;
					from = null;
				}
			}
			break;

		default:
		}
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {

		Node clicked = null;
		FontMetrics fm = offgraphics.getFontMetrics();
		Iterator<Node> it = graph.getVertices().iterator();
		while (it.hasNext() && clicked == null) {
			Node n = it.next();
			if (n.contains(e.getX(), e.getY(), fm))
				clicked = n;
		}

		switch (mode) {
		case SELECTION_MODE:
			picked = clicked;
			if (picked != null) {
				picked.x = e.getX();
				picked.y = e.getY();
			}
			repaint();
			break;

		case NODE_INSERT_MODE:
			if (clicked == null) {
				if (autoName)
					addNode(nextName(), e.getX(), e.getY());
				else {
					String s = JOptionPane.showInputDialog("Enter name for node:");
					addNode(s, e.getX(), e.getY());
				}
			}
			repaint();
			break;
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (mode == SELECTION_MODE) {
			if (picked != null) {
				picked = null;
			}
			repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// do nothing
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// do nothing
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (picked != null) {
			picked.x = e.getX();
			picked.y = e.getY();
		}
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// do nothing
	}

	public void setMode(int newMode) {
		if (newMode >= SELECTION_MODE && newMode <= EDGE_DELETE_MODE)
			mode = newMode;
	}

	public int getMode() {
		return mode;
	}

	public void setAutoName(boolean auto) {
		autoName = auto;
	}

	public void setModel(Graph<Node> newGraph) {
		graph = newGraph;
		from = null;
		picked = null;
		name = 1;
		repaint();
	}

	public Graph<Node> getModel() {
		return graph;
	}

	public Set<Node> markedNodes() {
		Set<Node> markedNodes = new TreeSet<Node>();
		for (Node n : graph.getVertices()) {
			if (n.marked)
				markedNodes.add(n);
		}
		return markedNodes;
	}

	public void unmarkAll() {
		for (Node n : graph.getVertices()) {
			n.marked = false;
		}
		repaint();
	}

	public void markAll(Set<Node> s) {
		for (Node n : s) {
			n.marked = true;
		}
		repaint();
	}
}
