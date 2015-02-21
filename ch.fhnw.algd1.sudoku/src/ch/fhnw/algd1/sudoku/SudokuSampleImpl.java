/*
 * Created on 03.12.2013
 */
package ch.fhnw.algd1.sudoku;

/**
 * @author Wolfgang Weck
 */
public final class SudokuSampleImpl implements SudokuSample {
	private static final int[][] data = { { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 5, 0, 2, 0, 8, 0, 4, 0 }, { 6, 0, 8, 0, 0, 0, 0, 1, 5 },
			{ 0, 0, 5, 0, 0, 2, 7, 6, 0 }, { 4, 6, 0, 0, 5, 3, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 3, 0, 1, 6, 4, 0, 0 },
			{ 0, 1, 6, 8, 9, 0, 2, 0, 3 }, { 0, 4, 0, 3, 0, 0, 0, 8, 1 } };

	/**
	 * @param args
	 */
	public void set(SudokuModel s) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (data[i][j] > 0) s.set(i, j, data[i][j]);
				else s.clear(i, j);
			}
		}
	}
}
