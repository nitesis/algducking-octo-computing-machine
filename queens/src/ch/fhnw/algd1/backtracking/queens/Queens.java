package ch.fhnw.algd1.backtracking.queens;

/**
 * Queens Problem
 */
public final class Queens {

	private static boolean solved(boolean[][] board) {
		return false;
	}

	public static void main(String[] args) {
		final int N = 4;
		boolean[][] board = new boolean[N][N];
		
		if (solved(board)) {
			BoardPrinter.print(board);
		} else {
			System.err.println("No solution found for N = " + N);
		}

	}

}
