package problems;

import java.util.Arrays;

/*1143. Longest Common Subsequence
https://leetcode.com/problems/longest-common-subsequence/
LCS & LPS -- similar classic variation
*/
// https://leetcode.com/problems/longest-common-subsequence/
// https://www.geeksforgeeks.org/longest-common-subsequence-dp-4/

// TC- 2^min(m,n))
// Space min(m,n)

public class LongestCommonSubsequence {
	private static int findLcs(String s1, String s2, int x, int y, int[][] dp) {
		if (x == 0 || y == 0) {
			return 0;
		}
		if (dp[x][y] != -1) {
			return dp[x][y];
		}
		if (s1.charAt(x - 1) == s2.charAt(y - 1)) {
			return dp[x][y] = 1 + findLcs(s1, s2, x - 1, y - 1, dp);
		} else {
			return dp[x][y] = Math.max(findLcs(s1, s2, x - 1, y, dp), findLcs(s1, s2, x, y - 1, dp));
		}
	}

	private static String findLcsTab(String x, String y, int n1, int n2, int[][] dp) {
		for (int i = 0; i < n1 + 1; i++) {
			for (int j = 0; j < n2 + 1; j++) {
				if (i == 0 || j == 0) {
					dp[i][j] = 0;
				} else if (x.charAt(i - 1) == y.charAt(j - 1)) {
					dp[i][j] = 1 + dp[i - 1][j - 1];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		StringBuilder ans = new StringBuilder();
		int i = n1;
		int j = n2;
		while (i > 0 && j > 0) {
			if (x.charAt(i - 1) == y.charAt(j - 1)) {
				ans.append(x.charAt(i - 1));
				i--;
				j--;
			} else if (dp[i - 1][j] > dp[i][j - 1]) {
				i--;
			} else {
				j--;
			}
		}
		return ans.reverse().toString();
	}

	public static void main(String[] args) {
		// your code here
		String s1 = "AGGTAB";
		String s2 = "GXTXAYB";
		int x = s1.length();
		int y = s2.length();
		int[][] dp = new int[x + 1][y + 1];
		for (int i = 0; i < x + 1; i++) {
			Arrays.fill(dp[i], -1);
		}
		System.out.println(findLcs(s1, s2, x, y, dp));// Output-4
	}

}
