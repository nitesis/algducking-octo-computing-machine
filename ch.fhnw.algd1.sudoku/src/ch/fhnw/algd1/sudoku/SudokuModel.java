/*
 * Created on 02.12.2013
 */
package ch.fhnw.algd1.sudoku;

/**
 * @author Wolfgang Weck
 */
public interface SudokuModel {
	/**
	 * Get the size of the entire Sudoku square.
	 * 
	 * @return the number of rows and columns of the Sudoku represented by this
	 *         model.
	 */
	int size();

	/**
	 * Get the size of the sub squares of the Sudoku
	 * 
	 * @return the number of rows and columns of each sub square of the Sudoku
	 *         represented by this model.
	 */
	int subSquareSize();

	/**
	 * Get the value of a field
	 * 
	 * @param i
	 *          the row index of the field ([0...size()[).
	 * @param j
	 *          the column index of the field ([0...size()[).
	 * @return the value stored in the field (i, j). The value is in the interval
	 *         [1...size()].<br>
	 *         If the field is empty, 0 is returned.
	 */
	int get(int i, int j);

	/**
	 * Set a specific field to a specific value of the interval [1...size()].<br>
	 * 0 is not allowed. To set a field to empty state, use clear().
	 * 
	 * @param i
	 *          the row index of the field ([0...size()[).
	 * @param j
	 *          the column index of the field ([0...size()[).
	 * @param to
	 *          the value to write to the field ([1...size()]).
	 */
	void set(int i, int j, int to);

	/**
	 * Clear a specific field, i.e. set it to empty.
	 * 
	 * @param i
	 *          the row index of the field ([0...size()[).
	 * @param j
	 *          the column index of the field ([0...size()[).
	 */
	void clear(int i, int j);
}
