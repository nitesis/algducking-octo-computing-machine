package ch.fhnw.algd2.graphedit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;

import ch.fhnw.algd2.graphedit.GraphPanel.Node;

public class GraphEditorFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 422353942744213381L;

	static class GraphFileFilter extends javax.swing.filechooser.FileFilter {
		static final String EXTENSION = ".graph";
		static final String DESCRIPTION = "Graph Files (*.graph)";

		@Override
		public boolean accept(File f) {
			return (f.isDirectory()) ? true : f.getName().endsWith(EXTENSION);
		}

		@Override
		public String getDescription() {
			return DESCRIPTION;
		}
	}

	public interface GraphFactory {
		Graph<Node> newGraph(boolean directed);
	}
	GraphFactory graphFactory = null;
	Set<Node> marked = null;
	JLabel statusBar = new JLabel();
	BorderLayout borderLayout1 = new BorderLayout();
	JPanel jPanel1 = new JPanel();
	LayoutManager verticalFlowLayout1 = new VerticalFlowLayout();
	GraphPanel gPanel;
	ButtonGroup bg = new ButtonGroup();
	JPanel jPanel2 = new JPanel();
	LayoutManager verticalFlowLayout2 = new VerticalFlowLayout();
	JRadioButton DeleteEdge = new JRadioButton();
	JRadioButton EdgeInsert = new JRadioButton();
	JRadioButton NodeInsert = new JRadioButton();
	TitledBorder titledBorder1;
	JCheckBox autoNameCBox = new JCheckBox();
	JLabel weightLabel = new JLabel();
	JLabel directLabel = new JLabel();
	JMenuBar menuBar1 = new JMenuBar();
	JFileChooser fileChooser = new JFileChooser("./data");
	JRadioButton selectButton = new JRadioButton();
	TitledBorder titledBorder2;
	JLabel jLabel1 = new JLabel();
	JLabel jLabel2 = new JLabel();
	JLabel jLabel3 = new JLabel();
	JPanel jPanel3 = new JPanel();
	BorderLayout borderLayout2 = new BorderLayout();
	JPanel jPanel4 = new JPanel();
	JLabel jLabel4 = new JLabel();
	JLabel jLabel5 = new JLabel();
	GraphFileFilter gff = new GraphFileFilter();
	JButton DFSButton = new JButton("DFS");
//	JButton MarkButton = new JButton("Mark");
//	JButton SweepButton = new JButton("Sweep");
	JButton SP = new JButton("Shortest Path");
	JButton copyButton = new JButton("Copy");
	JButton topSortButton = new JButton();

	public void updateButtons() {
		Graph<?> g = gPanel.getModel();
		copyButton.setEnabled(true);
		DFSButton.setEnabled(g instanceof GraphAlgorithms.DFS);
//		MarkButton.setEnabled(g instanceof GraphAlgorithms.GarbageCollector);
//		SweepButton.setEnabled(false);
		SP.setEnabled(g instanceof GraphAlgorithms.ShortestPath
				&& g instanceof WeightedGraph);
		topSortButton.setEnabled(g instanceof GraphAlgorithms.TopSort);
		if (g.isDirected()) directLabel.setText("directed");
		else directLabel.setText("undirected");
		if (g instanceof WeightedGraph) weightLabel.setText("weighted");
		else weightLabel.setText("unweighted");
	}

	// Construct the frame
	public GraphEditorFrame(GraphFactory factory) {
		graphFactory = factory;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		gPanel = new GraphPanel(graphFactory.newGraph(false));
		updateButtons();
		try {
			jbInit();
			getContentPane().add(gPanel, BorderLayout.CENTER);
			bg.add(DeleteEdge);
			bg.add(selectButton);
			bg.add(EdgeInsert);
			bg.add(NodeInsert);
			bg.setSelected(NodeInsert.getModel(), true);
			fileChooser.addChoosableFileFilter(gff);
			fileChooser.setFileFilter(gff);
			// fileChooser.setCurrentDirectory(new
			// File(getClass().getResource("").getFile()));
			File f = new File(getClass().getResource("").getFile());
			if (f.isDirectory()) fileChooser.setCurrentDirectory(f);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Component initialization
	private void jbInit() throws Exception {
		titledBorder1 = new TitledBorder(BorderFactory.createEtchedBorder(
				Color.white, new java.awt.Color(142, 142, 142)), "Mode");
		titledBorder2 = new TitledBorder(BorderFactory.createEtchedBorder(
				Color.white, new java.awt.Color(142, 142, 142)), "Uebung 11");
		this.getContentPane().setLayout(borderLayout1);
		this.setSize(new Dimension(679, 621));
		this.setTitle("Graph Editor");
		statusBar.setBorder(BorderFactory.createLoweredBevelBorder());
		statusBar.setToolTipText("Shows status messages");
		statusBar.setText(" ");
		JMenu menuFile = new JMenu("File");
		// New
		JMenu newMenu = new JMenu();
		JMenuItem uugraphItem = new JMenuItem();
		JMenuItem dugraphItem = new JMenuItem();
		JMenuItem uwgraphItem = new JMenuItem();
		JMenuItem dwgraphItem = new JMenuItem();
		// Open...
		JMenuItem openItem = new JMenuItem("Open...");
		openItem.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (JFileChooser.APPROVE_OPTION == fileChooser
						.showOpenDialog(GraphEditorFrame.this)) {
					try {
						File file = fileChooser.getSelectedFile();
						Graph<Node> graph = readGraph(file);
						gPanel.setModel(graph);
						updateButtons();
					}
					catch (Exception ex) {
						statusBar.setText("Failed: Graph could not be opened");
						ex.printStackTrace();
					}
				}
			}
		});
		// Save
		JMenuItem saveItem = new JMenuItem("Save...");
		saveItem.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (JFileChooser.APPROVE_OPTION == fileChooser
						.showSaveDialog(GraphEditorFrame.this)) {
					try {
						File file = fileChooser.getSelectedFile();
						writeGraph(gPanel.getModel(), file);
					}
					catch (Exception ex) {
						statusBar.setText("Failed: Graph could not be saved");
						ex.printStackTrace();
					}
				}
			}
		});
		// Exit
		JMenuItem menuFileExit = new JMenuItem("Exit");
		menuFileExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuFile.add(newMenu);
		menuFile.add(openItem);
		menuFile.add(saveItem);
		menuFile.addSeparator();
		menuFile.add(menuFileExit);
		JMenu menuHelp = new JMenu("Help");
		JMenuItem menuHelpAbout = new JMenuItem("About");
		menuHelpAbout.addActionListener(new ActionListener() {
			@Override
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				AboutBox dlg = new AboutBox(GraphEditorFrame.this);
				Dimension dlgSize = dlg.getPreferredSize();
				Dimension frmSize = getSize();
				Point loc = getLocation();
				dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x,
						(frmSize.height - dlgSize.height) / 2 + loc.y);
				dlg.setModal(true);
				dlg.show();
			}
		});
		JButton printButton = new JButton("Print");
		printButton.setToolTipText("Print graph to console");
		printButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(gPanel.getModel());
			}
		});
		jPanel1.setLayout(verticalFlowLayout1);
		jPanel1.setBorder(BorderFactory.createRaisedBevelBorder());
		jPanel1.setPreferredSize(new Dimension(130, 130));
		jPanel2.setLayout(verticalFlowLayout2);
		DeleteEdge.setToolTipText("Change to Edge deletion mode");
		DeleteEdge.setText("Delete Edge");
		DeleteEdge.setFocusPainted(false);
		DeleteEdge.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeleteEdge_actionPerformed(e);
			}
		});
		EdgeInsert.setToolTipText("Change to Edge insertion mode");
		EdgeInsert.setText("Insert Edge");
		EdgeInsert.setFocusPainted(false);
		EdgeInsert.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EdgeInsert_actionPerformed(e);
			}
		});
		NodeInsert.setToolTipText("Change to Node insertion mode");
		NodeInsert.setText("Insert Node");
		NodeInsert.setFocusPainted(false);
		NodeInsert.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NodeInsert_actionPerformed(e);
			}
		});
		jPanel2.setBorder(titledBorder1);
		jPanel2.setPreferredSize(new Dimension(120, 150));
		autoNameCBox.setToolTipText("Automatcally assign names to new nodes?");
		autoNameCBox.setSelected(true);
		autoNameCBox.setText("Auto Name");
		autoNameCBox.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				autoNameCBox_actionPerformed(e);
			}
		});
		weightLabel.setBorder(BorderFactory.createLoweredBevelBorder());
		weightLabel.setToolTipText("Indicates whether shown graph is weighted");
		weightLabel.setText("unweighted");
		directLabel.setBorder(BorderFactory.createLoweredBevelBorder());
		directLabel.setToolTipText("Indicates whether shown graph is directed");
		directLabel.setText("undirected");
		newMenu.setText("New Graph");
		uugraphItem.setText("undirected, unweighted");
		uugraphItem.setIcon(new ImageIcon(
				ch.fhnw.algd2.graphedit.GraphEditorFrame.class
						.getResource("UUGraph.gif")));
		uugraphItem.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gPanel.setModel(graphFactory.newGraph(false));
				updateButtons();
			}
		});
		dugraphItem.setText("directed, unweighted");
		dugraphItem.setIcon(new ImageIcon(
				ch.fhnw.algd2.graphedit.GraphEditorFrame.class
						.getResource("DUGraph.gif")));
		dugraphItem.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gPanel.setModel(graphFactory.newGraph(true));
				updateButtons();
			}
		});
		uwgraphItem.setText("undirected, weighted");
		uwgraphItem.setIcon(new ImageIcon(
				ch.fhnw.algd2.graphedit.GraphEditorFrame.class
						.getResource("UWGraph.gif")));
		uwgraphItem.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gPanel.setModel(new WeightedGraphImpl<Node>(graphFactory
						.newGraph(false)));
				updateButtons();
			}
		});
		dwgraphItem.setText("directed, weighted");
		dwgraphItem.setIcon(new ImageIcon(
				ch.fhnw.algd2.graphedit.GraphEditorFrame.class
						.getResource("DWGraph.gif")));
		dwgraphItem.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gPanel.setModel(new WeightedGraphImpl<Node>(graphFactory.newGraph(true)));
				updateButtons();
			}
		});
		selectButton.setToolTipText("Allows to select multple vertices");
		selectButton.setText("Selection");
		selectButton.setFocusPainted(false);
		selectButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectButton_actionPerformed(e);
			}
		});
		DFSButton.setPreferredSize(new Dimension(120, 27));
		DFSButton
				.setToolTipText("Perform depth first search and show spanning tree");
		DFSButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StartOperation(e);
			}
		});
//		MarkButton.setPreferredSize(new Dimension(80, 27));
//		MarkButton.setToolTipText("Perform mark phase of garbage collection");
//		MarkButton.addActionListener(new java.awt.event.ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				StartOperation(e);
//			}
//		});
//		SweepButton.setPreferredSize(new Dimension(80, 27));
//		SweepButton.setToolTipText("Perform sweep phase of garbage collection");
//		SweepButton.addActionListener(new java.awt.event.ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				StartOperation(e);
//			}
//		});
		SP.setPreferredSize(new Dimension(120, 27));
		SP.setToolTipText("Find shortest path between two selected vertices");
		SP.setText("Shortest Path");
		SP.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StartOperation(e);
			}
		});
		jLabel1.setText(" ");
		jLabel2.setText("Operations:");
		jLabel3.setText(" ");
		jPanel3.setLayout(borderLayout2);
		jPanel3.setBorder(BorderFactory.createRaisedBevelBorder());
		jLabel4.setText(" ");
		jLabel5.setPreferredSize(new Dimension(116, 15));
		jLabel5.setText(" ");
		topSortButton.setToolTipText("Topologically sort vertices");
		topSortButton.setText("TopSort");
		topSortButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StartOperation(e);
			}
		});
		copyButton.setToolTipText("Make a copy and diplay it");
		copyButton.setText("Copy");
		copyButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StartOperation(e);
			}
		});
		menuHelp.add(menuHelpAbout);
		menuBar1.add(menuFile);
		menuBar1.add(menuHelp);
		this.setJMenuBar(menuBar1);
		this.getContentPane().add(jPanel1, BorderLayout.EAST);
		jPanel1.add(jPanel2, null);
		jPanel2.add(selectButton, null);
		jPanel2.add(NodeInsert, null);
		jPanel2.add(EdgeInsert, null);
		jPanel2.add(DeleteEdge, null);
		jPanel1.add(jLabel4, null);
		jPanel1.add(jLabel2, null);
		jPanel1.add(copyButton, null);
		jPanel1.add(jLabel1, null);
		jPanel1.add(DFSButton, null);
//		jPanel1.add(MarkButton, null);
//		jPanel1.add(SweepButton, null);
		jPanel1.add(topSortButton, null);
		jPanel1.add(jLabel3, null);
		jPanel1.add(SP, null);
		jPanel1.add(jLabel5, null);
		jPanel1.add(autoNameCBox, null);
		jPanel1.add(printButton, null);
		this.getContentPane().add(jPanel3, BorderLayout.SOUTH);
		jPanel3.add(statusBar, BorderLayout.CENTER);
		jPanel3.add(jPanel4, BorderLayout.EAST);
		jPanel4.add(directLabel, null);
		jPanel4.add(weightLabel, null);
		newMenu.add(uugraphItem);
		newMenu.add(dugraphItem);
		newMenu.add(uwgraphItem);
		newMenu.add(dwgraphItem);
	}

	// Help | About action performed
	@SuppressWarnings("deprecation")
	public void helpAbout_actionPerformed(ActionEvent e) {
		AboutBox dlg = new AboutBox(this);
		Dimension dlgSize = dlg.getPreferredSize();
		Dimension frmSize = getSize();
		Point loc = getLocation();
		dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x,
				(frmSize.height - dlgSize.height) / 2 + loc.y);
		dlg.setModal(true);
		dlg.show();
	}

	void selectButton_actionPerformed(ActionEvent e) {
		gPanel.setMode(GraphPanel.SELECTION_MODE);
	}

	void NodeInsert_actionPerformed(ActionEvent e) {
		gPanel.setMode(GraphPanel.NODE_INSERT_MODE);
	}

	void EdgeInsert_actionPerformed(ActionEvent e) {
		gPanel.setMode(GraphPanel.EDGE_INSERT_MODE);
	}

	void DeleteEdge_actionPerformed(ActionEvent e) {
		gPanel.setMode(GraphPanel.EDGE_DELETE_MODE);
	}

	void autoNameCBox_actionPerformed(ActionEvent e) {
		gPanel.setAutoName(autoNameCBox.isSelected());
	}

	@SuppressWarnings("unchecked")
	void StartOperation(ActionEvent evt) {
		String command = evt.getActionCommand();
		String status = "done";
		Set<Node> set = gPanel.markedNodes();
		Graph<Node> g = gPanel.getModel();
		Object result = null;
		if (command.equals("Shortest Path")) {
			if (g instanceof WeightedGraph) {
				if (g instanceof GraphAlgorithms.ShortestPath) {
					if (set.size() == 2) {
						Iterator<Node> it = set.iterator();
						result = ((GraphAlgorithms.ShortestPath<GraphPanel.Node>)g)
								.getShortestPath(it.next(), it.next());
					} else {
						status = "select start and end vertex";
					}
				} else {
					status = "Graph does not implement "
							+ GraphAlgorithms.ShortestPath.class.getName();
				}
			} else {
				status = "Graph must be weighted";
			}
		} else if (command.equals("Copy")) {
			result = g.clone();
		} else if (command.equals("DFS")) {
			if (g instanceof GraphAlgorithms.DFS) {
				if (set.size() != 1) {
					status = "Select exactly one vertex to begin with DFS";
				} else {
					result = ((GraphAlgorithms.DFS<GraphPanel.Node>)g).traverse(set
							.iterator().next());
				}
			} else status = "Graph does not implement "
					+ GraphAlgorithms.DFS.class.getName();
		} else if (command.equals("Mark")) {
			if (g instanceof GraphAlgorithms.GarbageCollector) {
				if (set.size() < 1) {
					status = "Select some vertices as anchors for mark phase";
					marked = null;
//					SweepButton.setEnabled(false);
				} else {
					result = ((GraphAlgorithms.GarbageCollector<GraphPanel.Node>)g)
							.GCMark(set);
					marked = (Set<Node>)result;
//					SweepButton.setEnabled(true);
				}
			} else status = "Graph does not implement "
					+ GraphAlgorithms.GarbageCollector.class.getName();
		} else if (command.equals("Sweep")) {
			if (g instanceof GraphAlgorithms.GarbageCollector) {
				result = ((GraphAlgorithms.GarbageCollector<GraphPanel.Node>)g)
						.GCSweep(marked);
				marked = null;
//				SweepButton.setEnabled(false);
			}
		} else if (command.equals("TopSort")) {
			if (g.isDirected()) {
				if (g instanceof GraphAlgorithms.TopSort) {
					((GraphAlgorithms.TopSort)g).sort();
				} else status = "Graph does not implement "
						+ GraphAlgorithms.TopSort.class.getName();
			} else {
				status = "Graph must be directed";
			}
		} else {
			status = "Operation only available for class AdjListGraph";
		}
		statusBar.setText(status);
		if (result != null) {
			if (result instanceof Graph) {
				gPanel.setModel((Graph<Node>)result);
			} else if (result instanceof Set) {
				gPanel.unmarkAll();
				gPanel.markAll((Set<Node>)result);
			}
		}
	}

	public static void writeGraph(Graph<Node> g, File file) {
		try {
			java.util.List<Node> vertices = new ArrayList<Node>();
			FileOutputStream fos = new FileOutputStream(file);
			PrintWriter out = new PrintWriter(fos);
			out.print("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
			out.println();
			out.print("<graph\n");
			out.print("\ttype=\"");
			out.print(g.getClass().getName());
			out.print("\"");
			out.println();
			out.print("\tdirected=\"");
			out.print(g.isDirected());
			out.print("\"");
			out.println();
			out.print("\tweighted=\"");
			out.print(g instanceof WeightedGraph);
			out.print("\">");
			out.println();
			Set<Node> s = g.getVertices();
			Iterator<Node> it = s.iterator();
			int id = 0;
			while (it.hasNext()) {
				Node v = it.next();
				vertices.add(id, v);
				ch.fhnw.algd2.graphedit.GraphPanel.Node node = v;
				out.print("\t<vertex x=\"");
				out.print(node.x);
				out.print("\" y=\"");
				out.print(node.y);
				out.print("\" id=\"");
				out.print(id);
				out.print("\">\n");
				out.print("\t\t" + node.lbl);
				out.println();
				out.print("\t</vertex>\n");
				id++;
			}
			it = s.iterator();
			while (it.hasNext()) {
				Node x = it.next();
				int from = vertices.indexOf(x);
				Iterator<Node> e = g.getAdjacentVertices(x).iterator();
				while (e.hasNext()) {
					Node y = e.next();
					int to = vertices.indexOf(y);
					out.print("\t<edge ");
					out.print("from=\"");
					out.print(from);
					out.print("\" ");
					out.print("to=\"");
					out.print(to);
					out.print("\"");
					if (g instanceof WeightedGraph) {
						out.print(">");
						out.println();
						out.print("\t\t" + ((WeightedGraph<Node>)g).getEdgeWeight(x, y));
						out.print("\t</edge>\n");
					} else {
						out.print("/>\n");
					}
				}
			}
			out.print("</graph>\n");
			out.flush();
			out.close();
		}
		catch (Exception e) {}
	}

	public static Graph<Node> readGraph(File file) throws Exception {
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		Handler h = new Handler();
		parser.parse(file, h);
		return h.getGraph();
	}

	static class Handler extends org.xml.sax.helpers.DefaultHandler {
		private Graph<Node> g;
		private Exception exception = null;

		Graph<Node> getGraph() throws Exception {
			if (exception != null) throw exception;
			return g;
		}
		private int id;
		private double x;
		private double y;
		private StringBuffer sb;
		private boolean weighted;
		private Integer from;
		private Integer to;
		private Map<Integer, Node> vertices = new HashMap<Integer, Node>();

		@SuppressWarnings("unchecked")
		@Override
		public void startElement(String xmlns, String name, String qname,
				Attributes atts) {
			try {
				if (qname.equals("graph")) {
					String type = atts.getValue("type");
					boolean directed = atts.getValue("directed").equals("true");
					Class<?> c = Class.forName(type);
					g = (Graph<Node>)c.getConstructor(new Class[] { boolean.class })
							.newInstance(new Object[] { new Boolean(directed) });
					weighted = atts.getValue("weighted").equals("true");
				} else if (qname.equals("vertex")) {
					sb = new StringBuffer();
					id = Integer.parseInt(atts.getValue("id"));
					x = Double.parseDouble(atts.getValue("x"));
					y = Double.parseDouble(atts.getValue("y"));
				} else if (qname.equals("edge")) {
					from = new Integer(atts.getValue("from"));
					to = new Integer(atts.getValue("to"));
					if (weighted) sb = new StringBuffer();
				}
			}
			catch (Exception e) {
				if (exception == null) exception = e;
			}
		}

		@Override
		public void endElement(String xmlns, String name, String qname) {
			try {
				if (qname.equals("vertex")) {
					ch.fhnw.algd2.graphedit.GraphPanel.Node node = new ch.fhnw.algd2.graphedit.GraphPanel.Node();
					node.x = x;
					node.y = y;
					node.lbl = sb.toString().trim();
					g.addVertex(node);
					vertices.put(new Integer(id), node);
					sb = null;
				} else if (qname.equals("edge")) {
					Node x = vertices.get(from);
					Node y = vertices.get(to);
					if (weighted) {
						double w = Double.parseDouble(sb.toString().trim());
						sb = null;
						((WeightedGraph<Node>)g).addEdge(x, y, w);
					} else {
						g.addEdge(x, y);
					}
				}
			}
			catch (Exception e) {
				if (exception == null) exception = e;
			}
		}

		@Override
		public void characters(char[] ch, int start, int length) {
			if (sb != null) sb.append(ch, start, length);
		}
	}

	static class AboutBox extends JDialog {
		/**
		 * 
		 */
		private static final long serialVersionUID = -1216318294837408621L;

		public AboutBox(Frame parent) {
			super(parent);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			JPanel panel1 = new JPanel();
			JPanel panel2 = new JPanel();
			JPanel insetsPanel1 = new JPanel();
			JPanel insetsPanel3 = new JPanel();
			JButton button1 = new JButton("OK");
			JLabel label1 = new JLabel("Graph Editor", SwingConstants.CENTER);
			JLabel label2 = new JLabel("Informatik 3", SwingConstants.CENTER);
			JLabel label3 = new JLabel();
			JLabel label4 = new JLabel("Ch. Denzler, 2001-2004",
					SwingConstants.CENTER);
			Border border1;
			border1 = BorderFactory.createEmptyBorder(20, 50, 20, 50);
			this.setTitle("About Graph Editor");
			setResizable(false);
			panel1.setLayout(new BorderLayout());
			panel2.setLayout(new BorderLayout());
			insetsPanel3.setLayout(new GridLayout(4, 1));
			button1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			insetsPanel3.setBorder(border1);
			this.getContentPane().add(panel1, null);
			insetsPanel3.add(label1, null);
			insetsPanel3.add(label2, null);
			insetsPanel3.add(label3, null);
			insetsPanel3.add(label4, null);
			panel2.add(insetsPanel3, BorderLayout.CENTER);
			insetsPanel1.add(button1, null);
			panel1.add(insetsPanel1, BorderLayout.SOUTH);
			panel1.add(panel2, BorderLayout.NORTH);
			pack();
		}
	}

	static class VerticalFlowLayout implements LayoutManager {
		@Override
		public void addLayoutComponent(String name, Component comp) {}

		@Override
		public void removeLayoutComponent(Component comp) {}

		@Override
		public Dimension minimumLayoutSize(Container parent) {
			return parent.getSize();
		}

		@Override
		public Dimension preferredLayoutSize(Container parent) {
			return parent.getSize();
		}

		@Override
		public void layoutContainer(Container parent) {
			Insets insets = parent.getInsets();
			int x = insets.left;
			int y = insets.top;
			int w = parent.getSize().width - insets.left - insets.right;
			int numberOfComponents = parent.getComponentCount();
			for (int i = 0; i < numberOfComponents; i++) {
				Component c = parent.getComponent(i);
				if (c != null && c.isVisible()) c.setBounds(x, y, w,
						c.getPreferredSize().height);
				y += c.getPreferredSize().height;
			}
		}
	}
}
