package dp.lcs;
// https://leetcode.com/problems/longest-common-subsequence/
// https://www.geeksforgeeks.org/longest-common-subsequence-dp-4/

public class LCSTabPrint {
	private static int findLcs(String x, String y, int n1, int n2, int[][] dp) {
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
		return dp[n1][n2];
	}

	public static void main(String[] args) {
		String s1 = "AGGTAB";
		String s2 = "GXTXAYB";
		int x = s1.length();
		int y = s2.length();
		int[][] dp = new int[x + 1][y + 1];
		System.out.println(findLcs(s1, s2, x, y, dp)); // Output-4

	}

}
