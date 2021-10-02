package problems;

// https://leetcode.com/problems/n-queens-ii/

public class NQueens2 {

	int count = 0;

	private void solveNQueens(char[][] board, int col, int[] leftRow, int[] lowerDiagonal, int[] upperDiagonal) {
		if (col == board.length) {
			count++;
			return;
		}
		for (int row = 0; row < board.length; row++) {
			if (leftRow[row] == 0 && lowerDiagonal[row + col] == 0
					&& upperDiagonal[board.length - 1 + col - row] == 0) {

				leftRow[row] = 1;
				lowerDiagonal[row + col] = 1;
				upperDiagonal[board.length - 1 + col - row] = 1;

				board[row][col] = 'Q';

				solveNQueens(board, col + 1, leftRow, lowerDiagonal, upperDiagonal);

				board[row][col] = '.';

				leftRow[row] = 0;
				lowerDiagonal[row + col] = 0;
				upperDiagonal[board.length - 1 + col - row] = 0;
			}
		}
	}

	public int totalNQueens(int n) {

		char[][] board = new char[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				board[i][j] = '.';
			}
		}
		int[] leftRow = new int[n];
		int[] lowerDiagonal = new int[2 * n - 1];
		int[] upperDiagonal = new int[2 * n - 1];
		solveNQueens(board, 0, leftRow, lowerDiagonal, upperDiagonal);
		return count;
	}

	public static void main(String[] args) {
		NQueens2 nq = new NQueens2();
		int n = 4;
		System.out.println(nq.totalNQueens(n));

	}

}
