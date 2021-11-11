package dp.grid;

import java.util.Arrays;
//https://leetcode.com/problems/minimum-path-sum/

// Bottom Up approach
// Ref: https://www.youtube.com/watch?v=pGMsrvt0fpk

public class MinimumPathSumTab {
	public int minPathSum(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;

		int[][] dp = new int[m + 1][n + 1];
		for (int i = 0; i <= m; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}

		// to fill the last row, to begin there
		dp[m][n - 1] = 0;

		// value of a cell= min(down cell,right cell)+own cell value
		for (int i = m - 1; i >= 0; i--) {
			for (int j = n - 1; j >= 0; j--) {
				dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
			}
		}

		return dp[0][0];
	}

	public static void main(String[] args) {
		int[][] grid = { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };
		MinimumPathSum ms = new MinimumPathSum();
		System.out.println(ms.minPathSum(grid));// Output: 7
	}

}
