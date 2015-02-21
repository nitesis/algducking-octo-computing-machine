/*
 * Created on 02.12.2013
 */
package ch.fhnw.algd1.sudoku;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Wolfgang Weck
 */
public final class SudokuModelImpl implements SudokuObservableModel {
	private final static int LEN = 9, SLEN = 3;
	private final int[][] s = new int[LEN][LEN];

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.fhnw.algd1.sudoku.SudokuData#len()
	 */
	public int size() {
		return LEN;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.fhnw.algd1.sudoku.SudokuData#len()
	 */
	public int subSquareSize() {
		return SLEN;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.fhnw.algd1.sudoku.SudokuData#get(int, int)
	 */
	public int get(int i, int j) {
		return s[i][j];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.fhnw.algd1.sudoku.SudokuData#set(int, int, int)
	 */
	public void set(int i, int j, int to) {
		if (to < 1 || to > LEN) throw new IllegalArgumentException(to
				+ " not acceptable as value. Use [1..." + LEN + "]");
		s[i][j] = to;
		notifyViews(i, j, to);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.fhnw.algd1.sudoku.SudokuData#clear(int, int)
	 */
	public void clear(int i, int j) {
		s[i][j] = 0;
		notifyViews(i, j, 0);
	}
	private List<SudokuListener> listeners = new LinkedList<SudokuListener>();

	public void addListener(SudokuListener l) {
		if (!listeners.contains(l)) listeners.add(l);
	}

	public void removeListener(SudokuListener l) {
		if (listeners.contains(l)) listeners.remove(l);
	}

	private void notifyViews(int i, int j, int to) {
		for (SudokuListener l : new ArrayList<SudokuListener>(listeners)) {
			try {
				l.updated(i, j, to);
			}
			catch (Throwable t) {}
		}
	}
}
