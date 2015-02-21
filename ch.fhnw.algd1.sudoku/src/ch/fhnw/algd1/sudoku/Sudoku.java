/*
 * Created on 08.12.2013
 */
package ch.fhnw.algd1.sudoku;

/**
 * @author Wolfgang Weck
 */
public final class Sudoku {
	public static void main(String[] args) {
		SudokuObservableModel model = new SudokuModelImpl();
		SudokuChecker checker = new SudokuCheckerImpl();
		SudokuSolver solver = new SudokuSolverImpl(checker);
		SudokuSample sample = new SudokuSampleImpl();
		new SudokuGUI(model, checker, solver, sample);
	}
}
