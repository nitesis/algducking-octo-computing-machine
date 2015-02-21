/*
 * Created on 02.12.2013
 */
package ch.fhnw.algd1.sudoku;

/**
 * @author Wolfgang Weck
 */
public final class SudokuCheckerImpl implements SudokuChecker {
	/**
	 * @param args
	 */
	public boolean rowOK(SudokuModel s, int i) {
		final int size = s.size();
		boolean[] used = new boolean[size + 1];
		int j = 0;
		while (j < size && !used[s.get(i, j)]) {
			int val = s.get(i, j);
			if (val > 0) used[val] = true;
			j++;
		}
		return j == size;
	}

	/**
	 * @param args
	 */
	public boolean colOK(SudokuModel s, int j) {
		final int size = s.size();
		boolean[] used = new boolean[size + 1];
		int i = 0;
		while (i < size && !used[s.get(i, j)]) {
			int val = s.get(i, j);
			if (val > 0) used[val] = true;
			i++;
		}
		return i == size;
	}

	/**
	 * @param args
	 */
	public boolean squareOK(SudokuModel s, int i, int j) {
		boolean[] used = new boolean[s.size() + 1];
		final int subS = s.subSquareSize();
		i = (i / subS) * subS;
		j = (j / subS) * subS;
		int i1 = 0, j1 = subS;
		while (i1 < subS && j1 == subS) {
			j1 = 0;
			while (j1 < subS && !used[s.get(i + i1, j + j1)]) {
				int val = s.get(i + i1, j + j1);
				if (val > 0) used[val] = true;
				j1++;
			}
			if (j1 == subS) i1++;
		}
		return i1 == subS;
	}

	public boolean oneOK(SudokuModel s, int i, int j) {
		return rowOK(s, i) && colOK(s, j) && squareOK(s, i, j);
	}

	public boolean allOK(SudokuModel s) {
		final int size = s.size(), subS = s.subSquareSize();
		int i = 0;
		while (i < size && rowOK(s, i))
			i++;
		if (i == size) {
			i = 0;
			while (i < size && colOK(s, i))
				i++;
		}
		if (i == size) {
			i = 0;
			int j = size;
			while (i < size && j == size) {
				j = 0;
				while (j < size && squareOK(s, i, j))
					j += subS;
				if (j == size) i += subS;
			}
		}
		return i == size;
	}
}
