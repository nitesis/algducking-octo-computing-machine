package ch.fhnw.algd1.backtracking.queens;

public final class BoardChecker {
	public static boolean isValid(boolean[][] board) {
		if (board.length < 1 || board.length != board[0].length) {
			throw new IllegalArgumentException("Invalid board");
		}
		for (int row = 0; row < board.length; row++) {
			int column = 0;
			while (column < board.length && !board[row][column]) {
				++column;
			}
			if (column == board.length) {
				throw new IllegalStateException("No queen placed at row: " + row);
			}
			if (!canPlace(board, row, column)) {
				return false;
			}
		}
		return true;
	}

	public static boolean canPlace(boolean[][] board, int row, int col) {
		return validRow(board, row, col) && validColumn(board, row, col)
				&& validDiagonal(board, row, col);
	}

	private static boolean validDiagonal(boolean[][] board, int row, int col) {
		int i = board.length - 1, j1 = col - row + i, j2 = col + row - i;
		while (i >= 0
				&& (((j1 < 0 || j1 >= board.length || !board[i][j1]) && (j2 < 0
						|| j2 >= board.length || !board[i][j2])) || i == row)) {
			i--;
			j1--;
			j2++;
		}
		return i < 0;
	}

	private static boolean validColumn(boolean[][] board, int row, int col) {
		int i = board.length - 1;
		while (i >= 0 && (!board[i][col] || i == row))
			i--;
		return i < 0;
	}

	private static boolean validRow(boolean[][] board, int row, int col) {
		int i = board[row].length - 1;
		while (i >= 0 && (!board[row][i] || i == col))
			i--;
		return i < 0;
	}
}
