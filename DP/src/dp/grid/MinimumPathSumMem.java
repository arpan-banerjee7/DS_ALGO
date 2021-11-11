package dp.grid;

import java.util.Arrays;
// https://leetcode.com/problems/minimum-path-sum/

public class MinimumPathSumMem {
	int dp[][];

	private int solve(int[][] grid, int i, int j) {

		if (i >= grid.length || j >= grid[0].length) {
			return Integer.MAX_VALUE; // tricky part
		}

		if (i == grid.length - 1 && j == grid[0].length - 1) {
			return grid[i][j];
		}
		if (dp[i][j] != -1) {
			return dp[i][j];
		}
		return dp[i][j] = grid[i][j] + Math.min(solve(grid, i + 1, j), // D
				solve(grid, i, j + 1));// R
	}

	public int minPathSum(int[][] grid) {
		if (grid.length == 0) {
			return 0;
		}
		int m = grid.length;
		int n = grid[0].length;
		dp = new int[m][n];
		for (int i = 0; i < m; i++) {
			Arrays.fill(dp[i], -1);
		}
		return solve(grid, 0, 0);
	}

	public static void main(String[] args) {
		int[][] grid = { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };
		MinimumPathSum ms = new MinimumPathSum();
		System.out.println(ms.minPathSum(grid));// Output: 7
	}

}
