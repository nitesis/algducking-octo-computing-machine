/*
 * Created on 02.12.2013
 */
package ch.fhnw.algd1.sudoku;

/**
 * @author Wolfgang Weck
 */
interface SudokuChecker {
	/**
	 * checks that there are no duplicate values within a single row
	 * 
	 * @param model
	 *          the SudokuModel to check
	 * @param i
	 *          the index of the row to check [0...number of rows[
	 * @return "no conflicting values found"
	 */
	boolean rowOK(SudokuModel model, int i);

	/**
	 * checks that there are no duplicate values within a single column
	 * 
	 * @param model
	 *          the SudokuModel to check
	 * @param i
	 *          the index of the column to check [0...number of column[
	 * @return "no conflicting values found"
	 */
	boolean colOK(SudokuModel model, int j);

	/**
	 * checks that there are no duplicate values within a single sub square
	 * 
	 * @param model
	 *          the SudokuModel to check
	 * @param i
	 *          the index of the row of any field within the sub square to be
	 *          checked
	 * @param j
	 *          the index of the column of any field within the sub square to be
	 *          checked
	 * @return "no conflicting values found"
	 */
	boolean squareOK(SudokuModel model, int i, int j);

	/**
	 * checks that the value of a specific square is unique within its row, its
	 * column and its sub square
	 * 
	 * @param model
	 *          the SudokuModel to be checked
	 * @param i
	 *          the index of the row the field to be checked
	 * @param j
	 *          the index of the column the field to be checked
	 * @return "no conflicting values found"
	 */
	boolean oneOK(SudokuModel model, int i, int j);

	/**
	 * checks for all fields if there is a conflict with any other field in the
	 * same row, column or sub square
	 * 
	 * @param model
	 *          the SudokuModel to be checked
	 * @return "no conflicts"
	 */
	boolean allOK(SudokuModel model);
}
