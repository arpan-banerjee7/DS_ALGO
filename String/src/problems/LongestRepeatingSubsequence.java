package problems;

// Longest Repeating Subsequence
// https://www.geeksforgeeks.org/longest-repeating-subsequence/

/*This problem is just the modification of Longest Common Subsequence problem. 
 * The idea is to find the LCS(str, str)where str is the input string with the restriction 
 * that when both the characters are same, they shouldn’t be on the same index in the two strings. */

public class LongestRepeatingSubsequence {
	static int findLongestRepeatingSubSeq(String str) {
		int n = str.length();

		// Create and initialize DP table
		int[][] dp = new int[n + 1][n + 1];

		// Fill dp table (similar to LCS loops)
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				// If characters match and indexes are not same
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

		String str = "aabb";
		System.out.println("The length of the largest subsequence that" + " repeats itself is : "
				+ findLongestRepeatingSubSeq(str));
	}

}
