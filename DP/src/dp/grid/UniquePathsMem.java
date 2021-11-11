package dp.grid;

import java.util.Arrays;
//https://leetcode.com/problems/unique-paths/
// https://www.youtube.com/watch?v=IlEsdxuD4lY

public class UniquePathsMem {

	int[][] dp;

	private int solve(int i, int j, int m, int n) {
		if ((i == m - 1) && (j == n - 1)) {
			return 1;
		}

		if ((i >= m) || (j >= n)) {
			return 0;
		}
		if (dp[i][j] != -1) {
			return dp[i][j];
		}

		return dp[i][j] = solve(i + 1, j, m, n) + solve(i, j + 1, m, n);
	}

	public int uniquePaths(int m, int n) {
		dp = new int[m][n];
		for (int i = 0; i < m; i++) {
			Arrays.fill(dp[i], -1);
		}
		return solve(0, 0, m, n);
	}

	public static void main(String[] args) {
		int m = 3, n = 7;

		UniquePathsMem up = new UniquePathsMem();
		System.out.println(up.uniquePaths(m, n));// Output: 28
	}

}
