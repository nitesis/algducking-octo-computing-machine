import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Circles extends Panel{
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
		Panel p = new Circles();
		f.add(p);
		f.setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		int w = getWidth(), h = getHeight();
		drawCircles(g, 2, 2, (w < h ? w : h) - 4);
		
	}
	private static final int margin = 2;

	private void drawCircles(Graphics g, int x, int y, int size) {
		x = x + margin;
		y = y + margin;
		size = size - 2 * margin;
		if (size >= 4) {
			g.drawOval(x, y, size, size);
			drawCircles(g, x, size/4 + y, size/2);
			drawCircles(g, size/2 + x, size/4 + y, size/2);
		}
	}
}
