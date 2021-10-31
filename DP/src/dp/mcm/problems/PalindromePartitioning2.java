package dp.mcm.problems;

import java.util.Arrays;

public class PalindromePartitioning2 {
	private static boolean isPalindrome(String s, int i, int j) {
		while (i < j) {
			if (s.charAt(i) != s.charAt(j)) {
				return false;
			}
			i++;
			j--;
		}
		return true;
	}

	private static int mcm(String s, int i, int j, int[][] dp) {
		if (i >= j) {
			return 0;
		}
		if (isPalindrome(s, i, j)) {
			return 0;
		}
		if (dp[i][j] != -1) {
			return dp[i][j];
		}
		int min = Integer.MAX_VALUE;
		for (int k = i; k < j; k++) {
			int temp = 1 + mcm(s, i, k, dp) + mcm(s, k + 1, j, dp);
			min = Math.min(min, temp);
		}
		dp[i][j] = min;
		return min;
	}

	public static void main(String[] args) {
		String s = "aab";
		int n = s.length();
		int[][] dp = new int[n + 1][n + 1];
		for (int i = 0; i < n; i++) {
			Arrays.fill(dp[i], -1);
		}
		System.out.println(mcm(s, 0, n - 1, dp)); // Output: 1
	}

}
