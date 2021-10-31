package dp.lcs;
// https://www.geeksforgeeks.org/longest-repeated-subsequence/

public class LongestRepeatingSubsequence {
	private static int longestRepeatedSubSeq(String str) {
		int n = str.length();

		// Create and initialize DP table
		int dp[][] = new int[n + 1][n + 1];
		for (int i = 0; i <= n; i++)
			for (int j = 0; j <= n; j++)
				dp[i][j] = 0;

		// Fill dp table (similar to LCS loops)
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				// If characters match and indexes are
				// not same
				if (str.charAt(i - 1) == str.charAt(j - 1) && i != j)
					dp[i][j] = 1 + dp[i - 1][j - 1];

				// If characters do not match
				else
					dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
			}
		}
		return dp[n][n];
	}

	public static void main(String[] args) {
		String str = "AABEBCDD";
		System.out.println(longestRepeatedSubSeq(str));
	}

}
