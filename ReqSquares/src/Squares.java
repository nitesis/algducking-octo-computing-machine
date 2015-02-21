import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 
 * @author nitesis
 *
 */
public class Squares extends Panel {
	

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
		Panel p = new Squares();
		f.add(p);
		f.setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		int w = getWidth(), h = getHeight(), size = ((w < h ? w : h) - 4) / 2;
		drawSquares(g, 2 + size / 2, 2 + size / 2, size);
		
	}
	private static final int margin = 2;

	private void drawSquares(Graphics g, int left, int top, int size) {
		left = left - margin;
		top = top + margin;
		size = size - 2 * margin;
		if (size >= 4) {
			g.drawRect(left, top, size/2, size/2);
			g.setColor(Color.GRAY);
//			g.fillRect(left, top, size, size);
			drawSquares(g, left/2, top/2, size/2);
			drawSquares(g, 4*left+size, top/2, size/2);
//			drawSquares(g, size/2 + left, size/2 + top, size/2);
		}
	}
}
