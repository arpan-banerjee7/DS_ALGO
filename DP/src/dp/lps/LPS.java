package dp.lps;
// https://www.geeksforgeeks.org/longest-palindromic-subsequence-dp-12/
// https://leetcode.com/problems/longest-palindromic-subsequence/

// Take reverse of given string and find LCS bw them
public class LPS {

	Integer[][] dp;

	private int findLCS(String text1, String text2, int i, int j) {
		if (i < 0 || j < 0)
			return 0;
		if (dp[i][j] != null)
			return dp[i][j];

		if (text1.charAt(i) == text2.charAt(j)) {
			return 1 + findLCS(text1, text2, i - 1, j - 1);
		}
		return dp[i][j] = Math.max(findLCS(text1, text2, i - 1, j), findLCS(text1, text2, i, j - 1));
	}

	public int longestPalindromeSubseq(String s) {
		int n = s.length();
		dp = new Integer[n][n];
		StringBuilder sb = new StringBuilder(s);
		String s2 = sb.reverse().toString();
		return findLCS(s, s2, n - 1, n - 1);
	}
	/*
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
		String s = "cbbd";
		StringBuilder sb = new StringBuilder(s);
		String s2 = sb.reverse().toString();
		System.out.println(findLcs(s, s2, s.length(), s2.length())); // Output: 2

	}
*/
}
