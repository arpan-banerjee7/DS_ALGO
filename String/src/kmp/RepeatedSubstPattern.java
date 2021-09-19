package kmp;

// https://leetcode.com/problems/repeated-substring-pattern/
// https://www.youtube.com/watch?v=p92_kEjyJAo

public class RepeatedSubstPattern {
	// KMP - find lps array if lps[n-1]>=1 & n%(n-lps(n-1))==0 true else false
	// second check determines if the lps of the string can divide it totally

	public static void computeLPS(String s, int[] lps) {
		int n = s.length();
		lps[0] = 0;
		for (int i = 1; i < n; i++) {
			int x = lps[i - 1];
			while (s.charAt(x) != s.charAt(i)) {
				if (x == 0) {
					x = -1;
					break;
				}
				x = lps[x - 1];
			}
			lps[i] = x + 1;
		}
	}

	public static boolean repeatedSubstringPattern(String s) {
		int n = s.length();
		int[] lps = new int[n];
		computeLPS(s, lps);
		return lps[n - 1] > 0 && ((n % (n - lps[n - 1])) == 0);
	}

	public static void main(String[] args) {
		String s="abab";
		System.out.println(repeatedSubstringPattern(s)); // output- true

	}

}
