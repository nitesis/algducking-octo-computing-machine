package ch.fhnw.algd2.heaptest;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HeapTest<K> extends JFrame {
	private final static int MAX_HEAP_SIZE = 31;
	private PriorityQueue<K> queueModel;
	private TreeComponent<K> heapView;
	private JTextField text;

	HeapTest(PriorityQueue<K> q) {
		setTitle("Heap Test (" + q.getClass().getName() + ")");
		queueModel = q;
		heapView = new TreeComponent<K>(queueModel);
		getContentPane().setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(10, 1));
		text = new JTextField(3);
		panel.add(text);
		text.setMaximumSize(text.getPreferredSize());
		text.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					queueModel.add(null, new Integer(text.getText()));
					heapView.repaint();
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
				text.setText("");
			}
		});
		JButton b;
		panel.add(b = new JButton("Add"));
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					queueModel.add(null, new Integer(text.getText()));
					heapView.repaint();
				}
				catch (QueueFullException ex) {
					ex.printStackTrace();
				}
				text.setText("");
			}
		});
		panel.add(b = new JButton("Remove Min"));
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					queueModel.removeMin();
					heapView.repaint();
				}
				catch (QueueEmptyException ex) {
					ex.printStackTrace();
				}
			}
		});
		getContentPane().add("Center", heapView);
		getContentPane().add("East", panel);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		text.setFont(heapView.getFont());
	}

	public static void main(String[] args) throws Exception {
		automaticTests();
		// consoleTest();
		Frame f = new HeapTest<String>(new Heap<String>(MAX_HEAP_SIZE));
		f.setSize(400, 300);
		f.setVisible(true);
	}

	@SuppressWarnings("unused")
	private static void consoleTest() {
		// liest Prioritäten von der Standardeingabe und fügt diese in einen Heap
		// ein. Sobald eine Leerzeile gelesen wird, ist die Eingabe beendet
		long prioIn = 0;
		Heap<Long> heap = new Heap<Long>(MAX_HEAP_SIZE);
		int cnt = 0;
		// Eingabestream bereitstellen
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		// Prioritaeten einlesen
		System.out.println("Willkommen zum HeapTest!");
		System.out.println("Prioritaeten durch ENTER getrennt eingeben und");
		System.out.println("mit Leerzeile die Eingabe beenden.");
		String str = "";
		do {
			try {
				str = stdin.readLine();
				if (!str.equals("")) {
					prioIn = Long.parseLong(str);
					System.out.println("insert(" + prioIn + ")");
					heap.add(new Long(prioIn), prioIn);
					cnt++;
				}
			}
			catch (IOException e) {
				System.err.println("Fehler: Kann nicht von stdin lesen! (" + e + ")");
			}
			catch (NumberFormatException e) {
				System.err.println("Warnung: Eingabe ist nicht vom Typ 'long'"
						+ "und wird deshalb ignoriert (" + e + ").");
			}
			catch (QueueFullException e) {
				System.err
						.println("Warnung: Die Queue ist bereits voll. Die letzte Eingabe wurde ignoriert.");
			}
		}
		while (!str.equals("") && cnt < MAX_HEAP_SIZE);
		// Heap als Array mit Prioritaeten ausgeben zur Kontrolle auf stdout
		long[] longHeap = heap.toLongArray();
		System.out.println("Heap als Array dargestellt:");
		cnt = 0;
		while (cnt < longHeap.length) {
			System.out.print(longHeap[cnt] + " ");
			cnt++;
		}
		// Elemente aufsteigend sortiert ausgeben
		System.out.println("");
		System.out.println("Nach Prioritaeten sortiert:");
		long old = Long.MIN_VALUE;
		boolean ok = true;
		while (!heap.isEmpty()) {
			Long l;
			try {
				l = heap.removeMin();
				System.out.print(l + " ");
				ok = ok && (old <= (old = l.longValue()));
			}
			catch (QueueEmptyException e) {
				System.out.println("Es wurde removeMin aufgerufen nachdem)");
				System.out.println("isEmpty false ergeben hatte.)");
				System.out.println("Trotzdem wurde eine QueueEmptyException geworfen.");
			}
		}
		System.out.println();
		if (!ok) System.out
				.println("Die Ordnungseigenschaft ist nicht erfuellt!!!");
	}

	private static void automaticTests() throws Exception {
		try {
			Heap<String> h = new Heap<String>(2);
			h.add("1", 1);
			h.add("0", 0);
			if (2 != h.size()) {
				System.out
						.println("Die Methode size() ist nicht richtig implementiert.");
				System.out
						.println("=> In einen Heap sind zwei Elemente eingefuegt worden,");
				System.out.println("=> size() gibt als Resultat jedoch " + h.size()
						+ " zurueck!");
				System.exit(0);
			}
			if (!h.isFull()) {
				System.out
						.println("Die Methode isFull() ist nicht richtig implementiert.");
				System.out
						.println("=> In einen Heap(2) sind zwei Elemente eingefuegt worden,");
				System.out
						.println("=> isFull() gibt als Resultat jedoch false zurueck!");
				System.exit(0);
			}
		}
		catch (QueueFullException e) {
			System.out.println("Der Konstruktor ist nicht richtig definiert, denn");
			System.out.println("wenn in ein Objekt Heap(2) zwei Elemente eingefuegt");
			System.out.println("werden so wird eine QueueFullException geworfen.");
			throw e;
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("Die Methode insert(...) enthaelt einen Fehler; es");
			System.out.println("ist eine IndexOutOfBoundsException geworfen worden;");
			System.out
					.println("offensichtlich wird ausserhalb des Feldes zugegriffen.");
			throw e;
		}
	}

	private static class TreeComponent<K> extends JComponent {
		private static final int MIN_HEIGHT = 3;
		private final PriorityQueue<K> queueModel;
		private Font font;

		TreeComponent(PriorityQueue<K> q) {
			queueModel = q;
		}

		@Override
		public Font getFont() {
			return font;
		}

		private void drawTree(Graphics g, long[] q, int index, int x, int y,
				int dx, int dy, int c) {
			if (2 * index + 1 < q.length) {
				g.drawLine(x, y, x - dx, y + dy);
				drawTree(g, q, 2 * index + 1, x - dx, y + dy, dx / 2, dy, c);
			}
			if (2 * (index + 1) < q.length) {
				g.drawLine(x, y, x + dx, y + dy);
				drawTree(g, q, 2 * (index + 1), x + dx, y + dy, dx / 2, dy, c);
			}
			if (index == 0 || q[(index - 1) / 2] <= q[index]) g.setColor(Color.green);
			else g.setColor(Color.red);
			g.fillOval(x - c, y - c, 2 * c + 1, 2 * c + 1);
			g.setColor(Color.black);
			g.drawOval(x - c, y - c, 2 * c, 2 * c);
			String str = "" + q[index];
			FontMetrics m = g.getFontMetrics(font);
			g.drawString(str, x - m.stringWidth(str) / 2, y + m.getAscent() / 2 - 3);
		}

		private int height(PriorityQueue<?> q) {
			int s = q.size(), h = 0;
			while (s > 0) {
				s >>= 1;
				h++;
			}
			return h;
		}

		@Override
		public void paint(Graphics g) {
			int w = getWidth();
			int h = getHeight();
			int dy = h / (1 + Math.max(MIN_HEIGHT, height(queueModel)));
			int y = dy / 2;
			int c = y / 2;
			int x = w / 2;
			int dx = (x - c) / 2;
			g.setFont(font = new Font(null, Font.BOLD, c * 3 / 2));
			long[] q = queueModel.toLongArray();
			if (q.length > 0) drawTree(g, q, 0, x, y, dx, dy, c);
		}
	}
}
