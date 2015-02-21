/*
 * Created on Dec 5, 2013
 */
package ch.fhnw.algd1.sudoku;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Wolfgang Weck
 */
@SuppressWarnings("serial")
public final class SudokuPanel extends Panel implements SudokuListener {
	private final Font fnt = new Font("", Font.BOLD, 32);
	private static final int FIELD_SIZE = 50, X0 = 5, Y0 = 5;
	private final SudokuObservableModel s;
	private final int LEN, SLEN;
	private int focusI = -1, focusJ = -1;
	private boolean showFocus = false, ignoreUpdates = false;

	public SudokuPanel(SudokuObservableModel s) {
		this.s = s;
		LEN = s.size();
		SLEN = s.subSquareSize();
		s.addListener(this);
		setPreferredSize(new Dimension(left(LEN) + X0, top(LEN) + Y0));
		setFocusable(true);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					final int x = e.getX(), y = e.getY();
					int i = 0;
					while (i <= LEN && top(i) < y)
						i++;
					int j = 0;
					while (j <= LEN && left(j) < x)
						j++;
					if (i > 0 && i <= LEN && j > 0 && j <= LEN) {
						setFocus(i - 1, j - 1);
					} else {
						removeFocus();
					}
				}
			}
		});
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				final char c = e.getKeyChar();
				if (c >= '0' && c <= '9') changeFocusField(c - '0');
				else if (c == ' ') changeFocusField(0);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (showFocus && focusI >= 0) {
					final int c = e.getKeyCode();
					if (c == KeyEvent.VK_KP_LEFT || c == KeyEvent.VK_LEFT) {
						setFocus(focusI, (focusJ + LEN - 1) % LEN);
					} else if (c == KeyEvent.VK_KP_RIGHT || c == KeyEvent.VK_RIGHT) {
						setFocus(focusI, (focusJ + 1) % LEN);
					} else if (c == KeyEvent.VK_KP_UP || c == KeyEvent.VK_UP) {
						setFocus((focusI + LEN - 1) % LEN, focusJ);
					} else if (c == KeyEvent.VK_KP_DOWN || c == KeyEvent.VK_DOWN) {
						setFocus((focusI + 1) % LEN, focusJ);
					}
				}
			}
		});
		addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				setFocusVisible(true);
			}

			public void focusLost(FocusEvent e) {
				setFocusVisible(false);
			}
		});
	}

	private int left(int j) {
		return X0 + 3 + j * (FIELD_SIZE + 1) + (j / SLEN) * 2;
	}

	private int top(int i) {
		return Y0 + 3 + i * (FIELD_SIZE + 1) + (i / SLEN) * 2;
	}

	@Override
	public void paint(Graphics g) {
		drawLines(g);
		g.setFont(fnt);
		drawFields(g);
	}

	private void updateField(int i, int j) {
		repaint(left(j), top(i), FIELD_SIZE, FIELD_SIZE);
	}

	private void drawLines(Graphics g) {
		final int w = left(LEN) - left(0) + 2, h = top(LEN) - top(0) + 2;
		int x = X0, j = 0;
		while (j < LEN) {
			if (j % SLEN == 0) {
				g.drawLine(x, Y0, x, Y0 + h);
				x++;
				g.drawLine(x, Y0, x, Y0 + h);
				x++;
			}
			g.drawLine(x, Y0, x, Y0 + h);
			x = x + 1 + FIELD_SIZE;
			j++;
		}
		g.drawLine(x, Y0, x, Y0 + h);
		x++;
		g.drawLine(x, Y0, x, Y0 + h);
		x++;
		g.drawLine(x, Y0, x, Y0 + h);
		int y = Y0, i = 0;
		while (i < LEN) {
			if (i % SLEN == 0) {
				g.drawLine(X0, y, X0 + w, y);
				y++;
				g.drawLine(X0, y, X0 + w, y);
				y++;
			}
			g.drawLine(X0, y, X0 + w, y);
			y = y + 1 + FIELD_SIZE;
			i++;
		}
		g.drawLine(X0, y, X0 + w, y);
		y++;
		g.drawLine(X0, y, X0 + w, y);
		y++;
		g.drawLine(X0, y, X0 + w, y);
	}

	private void drawFields(Graphics g) {
		for (int i = 0; i < LEN; i++)
			for (int j = 0; j < LEN; j++)
				drawField(g, i, j);
	}

	private void drawField(Graphics g, int i, int j) {
		final int val = s.get(i, j);
		if (val > 0) {
			if (showFocus && i == focusI && j == focusJ) {
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(left(j) + 1, top(i) + 1, FIELD_SIZE - 2, FIELD_SIZE - 2);
				g.setColor(Color.BLACK);
			}
			FontMetrics m = g.getFontMetrics();
			final int l = left(j) + (FIELD_SIZE - m.charWidth('0' + val)) / 2;
			final int h = top(i) + m.getHeight();
			g.drawString("" + val, l, h);
		} else {
			g.clearRect(left(j), top(i), FIELD_SIZE, FIELD_SIZE);
			if (showFocus && i == focusI && j == focusJ) {
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(left(j) + 1, top(i) + 1, FIELD_SIZE - 2, FIELD_SIZE - 2);
				g.setColor(Color.BLACK);
			}
		}
	}

	private void setFocus(int i, int j) {
		if (showFocus && (i != focusI || j != focusJ)) {
			final int oldI = focusI, oldJ = focusJ;
			focusI = i;
			focusJ = j;
			if (oldI >= 0) updateField(oldI, oldJ);
			updateField(focusI, focusJ);
		}
	}

	private void removeFocus() {
		final int oldI = focusI, oldJ = focusJ;
		focusI = -1;
		focusJ = -1;
		if (oldI >= 0) updateField(oldI, oldJ);
	}

	private void setFocusVisible(boolean showFocus) {
		if (this.showFocus != showFocus) {
			this.showFocus = showFocus;
			if (focusI >= 0) updateField(focusI, focusJ);
		}
	}

	private void changeFocusField(int to) {
		if (showFocus && focusI >= 0) {
			if (to > 0) s.set(focusI, focusJ, to);
			else s.clear(focusI, focusJ);
		}
		ignoreUpdates(false);
	}

	public void updateAllFields() {
		repaint();
	}

	public void updated(int i, int j, int to) {
		if (!ignoreUpdates) {
			updateField(i, j);
		}
	}

	public void ignoreUpdates(boolean ignore) {
		if (ignoreUpdates && !ignore) updateAllFields();
		ignoreUpdates = ignore;
	}
}