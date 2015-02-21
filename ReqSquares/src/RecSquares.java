import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/*
 * Created on Nov 16, 2013
 */
/**
 * @author Wolfgang Weck
 */
public class RecSquares extends Panel {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Frame f = new Frame();
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		f.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				f.repaint();
			}
		});
		f.setSize(410, 450);
		Panel p = new RecSquares();
		f.add(p);
		f.setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		int w = getWidth(), h = getHeight();
		drawSquares(g, 2, 2, (w < h ? w : h) - 4 );
		
	}
	private static final int margin = 2;

	private void drawSquares(Graphics g, int left, int top, int size) {
		left = left + margin;
		top = top + margin;
		size = size - 2 * margin;
		if (size >= 30) {
			g.drawRect(left, top, size, size);
			drawSquares(g, size/2 + left, top, size/2);
			drawSquares(g, left, top, size/2);
			drawSquares(g, size/2 + left, size/2 + top, size/2);	
		}
	}
}
