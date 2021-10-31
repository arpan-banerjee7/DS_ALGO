package dp.lcs;

import java.util.Arrays;
// https://leetcode.com/problems/longest-common-subsequence/
// https://www.geeksforgeeks.org/longest-common-subsequence-dp-4/

public class LCSMem {
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
