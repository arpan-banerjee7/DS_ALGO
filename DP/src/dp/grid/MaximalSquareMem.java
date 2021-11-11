package dp.grid;
// All solutions-
// https://leetcode.com/problems/maximal-square/discuss/1569523/java-aditya-verma-approach-similar-to-maximal-rectangle-minor-modification

public class MaximalSquareMem {
	public int maximalSquare(char[][] a) {
		if (a.length == 0)
			return 0;
		int m = a.length, n = a[0].length;
		int ans = Integer.MIN_VALUE;
		Integer[][] dp = new Integer[m + 1][n + 1];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (a[i][j] == '1') {
					ans = Math.max(ans, dfs(a, i, j, m, n, dp));
				}
			}
		}
		return ans * ans;
	}

	private int dfs(char[][] a, int i, int j, int m, int n, Integer[][] dp) {
		if (i < 0 || i >= m || j < 0 || j >= n || a[i][j] == '0')
			return 0;
		if (dp[i][j] != null)
			return dp[i][j];
		int down = dfs(a, i + 1, j, m, n, dp);
		int right = dfs(a, i, j + 1, m, n, dp);
		int diagonal = dfs(a, i + 1, j + 1, m, n, dp);
		return dp[i][j] = 1 + Math.min(Math.min(down, right), diagonal);

	}

	public static void main(String[] args) {
		char[][] matrix = { { '0', '1' }, { '1', '0' } };
		MaximalSquareMem ms = new MaximalSquareMem();
		System.out.println(ms.maximalSquare(matrix));// Output: 1
	}
}
