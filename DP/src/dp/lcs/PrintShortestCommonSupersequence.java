package dp.lcs;
// https://leetcode.com/problems/shortest-common-supersequence/
// https://www.youtube.com/watch?v=823Grn4_dCQ&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=24
// https://www.youtube.com/watch?v=pHXntFeu6f8

public class PrintShortestCommonSupersequence {
	public static String shortestCommonSupersequence(String str1, String str2) {
		int m = str1.length();
		int n = str2.length();
		StringBuilder ans = new StringBuilder();
		int[][] dp = new int[m + 1][n + 1];
		for (int i = 0; i < m + 1; i++) {
			for (int j = 0; j < n + 1; j++) {
				if (i == 0 || j == 0) {
					dp[i][j] = 0;
				} else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
					dp[i][j] = 1 + dp[i - 1][j - 1];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		int i = m;
		int j = n;
		while (i > 0 && j > 0) {
			if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
				ans.append(str1.charAt(i - 1));
				i--;
				j--;
			} else if (dp[i - 1][j] > dp[i][j - 1]) {
				ans.append(str1.charAt(i - 1));
				i--;
			} else {
				ans.append(str2.charAt(j - 1));
				j--;
			}
		}
		while (i > 0) {
			ans.append(str1.charAt(i - 1));
			i--;
		}
		while (j > 0) {
			ans.append(str2.charAt(j - 1));
			j--;
		}
		ans.reverse();
		return ans.toString();
	}

	public static void main(String[] args) {
		String str1 = "abac";
		String str2 = "cab";
		System.out.println(shortestCommonSupersequence(str1, str2));// Output: "cabac"
	}

}
