package dp.lcs.problems;
// https://leetcode.com/problems/shortest-common-supersequence/

// https://www.youtube.com/watch?v=823Grn4_dCQ&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=24
// https://www.youtube.com/watch?v=pHXntFeu6f8

public class PrintShortestCommonSupersequence2 {
	private static String findLcs(String s1, String s2, int m, int n) {
		String[][] dp = new String[m + 1][n + 1];
		for (int i = 0; i < m + 1; i++) {
			for (int j = 0; j < n + 1; j++) {
				if (i == 0 || j == 0) {
					dp[i][j] = "";
				} else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + s1.charAt(i - 1);
				} else {
					dp[i][j] = dp[i - 1][j].length() > dp[i][j - 1].length() ? dp[i - 1][j] : dp[i][j - 1];
				}
			}
		}
		return dp[m][n];
	}

	public static String shortestCommonSupersequence(String str1, String str2) {
		int m = str1.length();
		int n = str2.length();
		String lcs = findLcs(str1, str2, m, n);
		int p1 = 0;
		int p2 = 0;
		StringBuilder ans = new StringBuilder();
		for (char c : lcs.toCharArray()) {
			while (str1.charAt(p1) != c) {
				ans.append(str1.charAt(p1++));
			}
			while (str2.charAt(p2) != c) {
				ans.append(str2.charAt(p2++));
			}
			ans.append(c);
			p1++;
			p2++;
		}
		ans.append(str1.substring(p1));
		ans.append(str2.substring(p2));
		return ans.toString();
	}

	public static void main(String[] args) {
		String str1 = "abac";
		String str2 = "cab";
		System.out.println(shortestCommonSupersequence(str1, str2));// Output: "cabac"
	}

}
