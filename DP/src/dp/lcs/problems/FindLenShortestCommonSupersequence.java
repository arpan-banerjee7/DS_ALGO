package dp.lcs.problems;

import java.util.Arrays;
// https://leetcode.com/problems/shortest-common-supersequence/

public class FindLenShortestCommonSupersequence {
	private static int findLcs(String s1, String s2, int m, int n, int[][] dp) {
		if (m == 0 || n == 0) {
			return 0;
		}
		if (dp[m][n] != -1) {
			return dp[m][n];
		}
		if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
			return dp[m][n] = 1 + findLcs(s1, s2, m - 1, n - 1, dp);
		} else {
			return dp[m][n] = Math.max(findLcs(s1, s2, m - 1, n, dp), findLcs(s1, s2, m, n - 1, dp));
		}
	}

	public static void main(String[] args) {
		String str1 = "abac";
		String str2 = "cab";
		int m = str1.length();
		int n = str2.length();
		int[][] dp = new int[m + 1][n + 1];
		for (int i = 0; i < m + 1; i++) {
			Arrays.fill(dp[i], -1);
		}
		int lcs = findLcs(str1, str2, m, n, dp);
		System.out.println(m + n - lcs); // Output-5

	}

}
