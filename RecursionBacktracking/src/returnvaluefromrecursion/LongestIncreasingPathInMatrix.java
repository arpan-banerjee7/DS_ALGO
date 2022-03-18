package returnvaluefromrecursion;

// 329. Longest Increasing Path in a Matrix
// https://leetcode.com/problems/longest-increasing-path-in-a-matrix/

// return value from recursion
public class LongestIncreasingPathInMatrix {
	private static int dfs(int[][] matrix, int i, int j, int m, int n, boolean[][] vis, Integer[][] dp) {

		if (dp[i][j] != null) {
			return dp[i][j];
		}

		// D L R U
		int maxSize = 0;
		vis[i][j] = true;
		int d = 0;
		int l = 0;
		int r = 0;
		int u = 0;

		// D
		if (i + 1 < m && matrix[i + 1][j] > matrix[i][j]) {
			d = dfs(matrix, i + 1, j, m, n, vis, dp);
		}
		// L
		if (j - 1 >= 0 && matrix[i][j - 1] > matrix[i][j]) {
			l = dfs(matrix, i, j - 1, m, n, vis, dp);
		}
		// R
		if (j + 1 < n && matrix[i][j + 1] > matrix[i][j]) {
			r = dfs(matrix, i, j + 1, m, n, vis, dp);
		}
		// U
		if (i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j]) {
			u = dfs(matrix, i - 1, j, m, n, vis, dp);
		}

		vis[i][j] = false;
		int currMax = Math.max(Math.max(d, l), Math.max(r, u));
		maxSize = Math.max(maxSize, currMax);
		return dp[i][j] = maxSize + 1;
	}

	public int longestIncreasingPath(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;

		int maxLen = Integer.MIN_VALUE;
		boolean[][] vis = new boolean[m][n];
		Integer[][] dp = new Integer[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (!vis[i][j]) {
					maxLen = Math.max(maxLen, dfs(matrix, i, j, m, n, vis, dp));
				}
			}
		}
		return maxLen;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
