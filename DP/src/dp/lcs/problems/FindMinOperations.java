package dp.lcs.problems;
// https://www.geeksforgeeks.org/minimum-number-deletions-insertions-transform-one-string-another/

public class FindMinOperations {
	private static int findLcs(String s1, String s2, int m, int n) {
		int[][] dp = new int[m + 1][n + 1];
		for (int i = 0; i < m + 1; i++) {
			for (int j = 0; j < n + 1; j++) {
				if (i == 0 || j == 0) {
					dp[i][j] = 0;
				} else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = 1 + dp[i - 1][j - 1];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[m][n];
	}

	public static void main(String[] args) {
		String str1 = "geeksforgeeks";
		String str2 = "geeks";

		int m = str1.length();
		int n = str2.length();
		int ans = 0;
		int lcs = findLcs(str1, str2, m, n);
		ans = (m - lcs) + (n - lcs);// deletion and insertion
		System.out.println(ans);
		/*
		 * Output : Minimum Deletion = 8 Minimum Insertion = 0
		 */

	}

}
