package problems;

// 516. Longest Palindromic Subsequence
/*Parent question LCS

Aditya verma DP
https://www.youtube.com/watch?v=wuOOOATz_IA&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=26

// blunder use i+1/++i
```
private static void test(int i) {
		System.out.println(i);
	}

	public static void main(String[] args) {
		int i=7;
		test(i++);
		test(i);
		}
```*/

public class LongestPalindromicSubsequence {
	Integer[][] dp;

	private int findLPS(String s, int i, int j) {
		if (i > j) {
			return 0;
		}
		if (i == j) {
			return 1;
		}
		if (dp[i][j] != null) {
			return dp[i][j];
		}
		if (s.charAt(i) == s.charAt(j)) {
			return dp[i][j] = findLPS(s, i + 1, j - 1) + 2;
		}
		return dp[i][j] = Math.max(findLPS(s, i + 1, j), findLPS(s, i, j - 1));
	}

	public int longestPalindromeSubseq(String s) {
		int n = s.length();
		dp = new Integer[n][n];
		return findLPS(s, 0, n - 1);
	}

	// -----------------------------------------------------------------------------------------//
	private int findLcs(String s1, String s2, int m, int n) {
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

	public int longestPalindromeSubseqTab(String s) {
		StringBuilder sb = new StringBuilder(s);
		String s2 = sb.reverse().toString();
		return findLcs(s, s2, s.length(), s2.length());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
