package dp.med;

/*Edit distance- TC-o(mN) Space-o(mn) //to do- o(m+n) %2 maintaining
//only two rows
https://leetcode.com/problems/edit-distance/submissions/
https://www.geeksforgeeks.org/edit-distance-dp-5/
https://www.youtube.com/watch?v=AuYujVj646Q  Techdose
*/
public class EditDistance {

	// Top Down DP
	// TC- O(3^m)
	Integer[][] dp;

	private int minEdit(String s1, String s2, int m, int n) {
		if (m == 0 && n == 0) {
			return 0;
		}
		if (m == 0)
			return n;
		if (n == 0)
			return m;

		if (dp[m][n] != null) {
			return dp[m][n];
		}

		if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
			return dp[m][n] = minEdit(s1, s2, m - 1, n - 1);
		}

		return dp[m][n] = 1 + Math.min(minEdit(s1, s2, m, n - 1),
				Math.min(minEdit(s1, s2, m - 1, n), minEdit(s1, s2, m - 1, n - 1)));

	}

	public int minDistance(String word1, String word2) {
		int m = word1.length();
		int n = word2.length();
		dp = new Integer[m + 1][n + 1];
		return minEdit(word1, word2, m, n);
	}

	/*--------------------------------------------------------------------------------------------------------------------------*/
	// TC-o(m*N) Space-o(m*n)
	// to do- o(m+n) %2 maintaining only two rows
	public int minDistance1(String word1, String word2) {
		int m = word1.length();
		int n = word2.length();
		int[][] dp = new int[m + 1][n + 1];

		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {

				// If first string is empty, only option is to
				// insert all characters of second string
				if (i == 0) {
					dp[i][j] = j;
				}

				// If second string is empty, the only option is to
				// remove all characters of first string
				else if (j == 0) {
					dp[i][j] = i;
				}

				// If last characters of two strings are same,
				// nothing much to do. Ignore last characters and
				// get count for remaining strings.
				else if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1];

					// If last characters are not same, consider all
					// three operations on last character of first
					// string, recursively compute minimum cost for all
					// three operations and take minimum of three
					// values.
				} else {
					dp[i][j] = 1 + Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1]));
				}

			}
		}
		return dp[m][n];
	}

	public static void main(String[] args) {
		/*Input: word1 = "horse", word2 = "ros"
				Output: 3
				Explanation: 
				horse -> rorse (replace 'h' with 'r')
				rorse -> rose (remove 'r')
				rose -> ros (remove 'e')
		*/
	}

}
