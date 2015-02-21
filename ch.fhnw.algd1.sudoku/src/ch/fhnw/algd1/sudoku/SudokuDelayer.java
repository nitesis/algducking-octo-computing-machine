/*
 * Created on 11.12.2013
 */
package ch.fhnw.algd1.sudoku;

/**
 * @author Wolfgang Weck
 */
public class SudokuDelayer implements SudokuListener {
	private static final int N0 = 1024;
	private int delay = 0, increment = 1, sleepTime = 0, n = 0;
	private boolean active = false;

	synchronized public void setDelay(int delay) {
		this.delay = delay;
		if (delay > 0) {
			int d = 1;
			for (int i = 1; i < delay; i++) {
				d = d * 2;
			}
			if (d < N0) {
				increment = d;
				sleepTime = 1;
			} else {
				increment = N0;
				sleepTime = d / N0;
			}
			n = 0;
		} else {
			sleepTime = 0;
		}
		notify();
	}

	synchronized public int getDelay() {
		return delay;
	}

	synchronized public void setActive(boolean active) {
		this.active = active;
		notify();
	}

	synchronized public void updated(int i, int j, int to) {
		n = (n + increment) % N0;
		if (active && n == 0 && sleepTime > 0) {
			try {
				wait(sleepTime);
			}
			catch (InterruptedException e) {}
		}
	}
}
