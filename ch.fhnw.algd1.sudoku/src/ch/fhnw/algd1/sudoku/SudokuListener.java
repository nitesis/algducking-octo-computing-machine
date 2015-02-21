/*
 * Created on Dec 5, 2013
 */
package ch.fhnw.algd1.sudoku;

/**
 * @author Wolfgang Weck
 */
public interface SudokuListener {
	void updated(int i, int j, int to);
}
