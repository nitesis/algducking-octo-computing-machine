/*
 * Created on 02.12.2013
 */
package ch.fhnw.algd1.sudoku;

/**
 * @author Wolfgang Weck
 */
public interface SudokuObservableModel extends SudokuModel {
	/**
	 * Register a listener to be called upon changes in the model If the listener
	 * is already registered, there is no effect. (No registrations of duplicates)
	 * 
	 * @param l
	 *          the listener to be called upon each change
	 */
	void addListener(SudokuListener l);

	/**
	 * Remove a listener from the model's registry. If the listener is not found
	 * to be registered, there is no effect.
	 * 
	 * @param l
	 */
	void removeListener(SudokuListener l);
}
