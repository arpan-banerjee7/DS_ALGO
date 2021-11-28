package problems;

// 10. Regular Expression Matching
// https://leetcode.com/problems/regular-expression-matching/
// similar question- wildcard matching
// https://leetcode.com/problems/wildcard-matching/discuss/1599421/java-top-down-dp-similar-to-regular-expression-matching-neetcode\

// Try- "ab" ".*c"

public class RegularExpressionMatching {

	private boolean solve(int i, int j, String s, String p, Boolean dp[][]) {

		if (i >= s.length() && j >= p.length()) {
			return true;
		}

		// main string has chars left while pattern is finished
		if (j >= p.length()) {
			return false;
		}
		if (dp[i][j] != null) {
			return dp[i][j];
		}

		// either both might finish/ main char is finished but pattern is not
		// in this case we might get a macth if * are present

		boolean match = ((i < s.length()) && ((s.charAt(i) == p.charAt(j)) || (p.charAt(j) == '.')));
		// two cases- take that char or do not take that chartake that char
		// case 2 we can only take that char, if it has matched with the i th char of
		// stirng s

		if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
			dp[i][j] = (solve(i, j + 2, s, p, dp) || (match && solve(i + 1, j, s, p, dp)));
			return dp[i][j];
		}

		// if j+1 is not '*' and if ith and jth char of s and p has matched
		return dp[i][j] = match && solve(i + 1, j + 1, s, p, dp);
	}

	public boolean isMatch(String s, String p) {
		Boolean dp[][] = new Boolean[s.length() + 1][p.length() + 1];
		return solve(0, 0, s, p, dp);
	}
}
