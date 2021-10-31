package dp.lps;
// https://www.geeksforgeeks.org/minimum-insertions-to-form-a-palindrome-dp-28/

public class MinInsertionToMakePalindrome {

	// Time Complexity: O(n2)
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
		String s = "geeks";
		StringBuilder sb = new StringBuilder(s);
		String s2 = sb.reverse().toString();
		System.out.println(s.length() - findLcs(s, s2, s.length(), s2.length()));
		// Output - Minimum number of deletions = 3
	}
}
