package problems;

import java.util.ArrayList;
import java.util.List;

// https://www.youtube.com/watch?v=i05Ju7AftcM
// https://leetcode.com/problems/n-queens/

public class NQueens1 {

	private static List<String> convert(char[][] board) {
		List<String> ans = new ArrayList<>();
		for (char[] b : board) {
			String s = new String(b);
			ans.add(s);
		}
		return ans;
	}

	private static void solve(List<List<String>> res, char[][] board, int col, int[] leftRow, int[] upperDiagonal,
			int[] lowerDiagonal) {
		if (col == board.length) {
			res.add(convert(board));
			return;
		}
		for (int row = 0; row < board.length; row++) {
			if (leftRow[row] == 0 && upperDiagonal[row + col] == 0
					&& lowerDiagonal[board.length - 1 + col - row] == 0) {
				leftRow[row] = 1;
				upperDiagonal[row + col] = 1;
				lowerDiagonal[board.length - 1 + col - row] = 1;
				board[row][col] = 'Q';
				solve(res, board, col + 1, leftRow, upperDiagonal, lowerDiagonal);
				board[row][col] = '.';
				leftRow[row] = 0;
				upperDiagonal[row + col] = 0;
				lowerDiagonal[board.length - 1 + col - row] = 0;
			}
		}

	}

	public static List<List<String>> solveNQueens(int n) {
		char[][] board = new char[n][n];
		List<List<String>> res = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				board[i][j] = '.';
			}
		}
		int[] leftRow = new int[n];
		int[] upperDiagonal = new int[2 * n - 1];
		int[] lowerDiagonal = new int[2 * n - 1];
		solve(res, board, 0, leftRow, upperDiagonal, lowerDiagonal);
		return res;
	}

	public static void main(String[] args) {
		int n = 4;
		System.out.println(solveNQueens(n));
		// output- [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
	}

}
