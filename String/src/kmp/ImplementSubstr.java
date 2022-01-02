package kmp;
// 28. Implement strStr()
// https://leetcode.com/problems/implement-strstr/

public class ImplementSubstr {
	private int[] computeLPS(String s) {
		int n = s.length();
		int[] lps = new int[n];
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
		return lps;
	}

	public int strStr(String haystack, String needle) {

		int n = haystack.length();
		int m = needle.length();

		if (m == 0)
			return 0;
		int[] lps = computeLPS(needle);

		int i = 0, j = 0;
		while (i < n) {
			if (haystack.charAt(i) == needle.charAt(j)) {
				i++;
				j++;
				if (j == m)
					return i - m;
			} else {
				if (j != 0) {
					j = lps[j - 1];
				} else {
					i++;
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		/*
		 * Input: haystack = "hello", needle = "ll" Output: 2
		 */
	}

}
