/*
 * Created on 03.12.2013
 */
package ch.fhnw.algd1.sudoku;

/**
 * @author
 */
public final class SudokuSolverImpl implements SudokuSolver {
	private final SudokuChecker checker;

	/**
	 * Create a new solver based on a checker
	 * 
	 * @param checker
	 *            the SudokuChecker this solver will be using to determine the
	 *            validity of partial solutions
	 */
	public SudokuSolverImpl(SudokuChecker checker) {
		this.checker = checker;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.fhnw.algd1.sudoku.SudokuSolver#solved(ch.fhnw.algd1.sudoku.SudokuModel
	 * )
	 */
	public boolean solved(SudokuModel model, int i, int j) {
//		boolean found = false;
//		int val = 1;
		// TODO start recursive search at square (0,0)
		if (i == model.size()) {
			return checker.allOK(model);
		} else {
			final int nextI = i + (j + 1) / model.size(); 
			final int nextJ = (j + 1) % model.size();

			if (model.get(i, j) != 0) {
				return solved(model, nextI, nextJ);
			} else { // Probiere Werte 1-9
				boolean found = false;
				int val = 1;
				while (val <= model.size() && found != true) {
					model.set(i, j, val);

					if (checker.oneOK(model, i, j)) {
						found = solved(model, nextI, nextJ);
						val++;
					}
				}if (!found) {
					model.clear(i, j);
				}
				return found;
			}
		}
//		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.fhnw.algd1.sudoku.SudokuSolver#nofSolutions(ch.fhnw.algd1.sudoku.
	 * SudokuModel )
	 */
	public int nofSolutions(SudokuModel model) {
		// TODO start recursive enumeration of solutions, beginning at (0,0)
		return 0;
	}

	public boolean solved(SudokuModel model) {
		// TODO Auto-generated method stub
		return false;
	}

}
