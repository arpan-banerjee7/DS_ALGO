package problems;

// 44. Wildcard Matching
/*
test cases try outs-
"aa"
"a*"
​
""
"**********"
​
s = "acdcb", p = "a*c?b"
 */

// https://leetcode.com/problems/wildcard-matching/discuss/1599421/Java-or-Top-Down-DP-or-Similar-to-Regular-Expression-Matching-or-Neetcode
// same as  10. Regular Expression Matching

public class WildcardMatching {
	private boolean solve(int i, int j, String s, String p, Boolean dp[][]) {

		if (i >= s.length() && j >= p.length()) {
			return true;
		}
		// System.out.println(i);
		// main string has chars left while pattern is finished
		if (j >= p.length()) {
			return false;
		}

		if (i >= s.length() && j != p.length()) {
			for (int idx = j; idx < p.length(); idx++) {
				if (p.charAt(idx) != '*')
					return false;
			}
			return true;
		}

		if (dp[i][j] != null) {
			return dp[i][j];
		}

		// either both might finish/ main char is finished but pattern is not
		// in this case we might get a macth if * are present

		if ((i < s.length()) && ((s.charAt(i) == p.charAt(j)) || (p.charAt(j) == '?'))) {
			return dp[i][j] = solve(i + 1, j + 1, s, p, dp);
		}
		// two cases- take that char or do not take that chartake that char
		// case 2 we can only take that char, if it has matched with the i th char of
		// stirng s

		if (j < p.length() && p.charAt(j) == '*') {
			dp[i][j] = (solve(i, j + 1, s, p, dp) || (solve(i + 1, j, s, p, dp)));
			return dp[i][j];
		}

		// if j+1 is not '*' and if ith and jth char of s and p has matched
		return dp[i][j] = false;
	}

	public boolean isMatch(String s, String p) {
		Boolean dp[][] = new Boolean[s.length() + 1][p.length() + 1];
		return solve(0, 0, s, p, dp);
	}
}
