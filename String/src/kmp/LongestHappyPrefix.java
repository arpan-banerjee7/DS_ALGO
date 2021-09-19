package kmp;

// https://leetcode.com/problems/longest-happy-prefix/
public class LongestHappyPrefix {

	 public static String longestPrefix(String s) {

			int n = s.length();
			int[] lps = new int[n];
			int x = 0;

			// KMP for finding LPS array
			for (int i = 1; i < n; i++) {
				x = lps[i - 1];
				while (s.charAt(i) != s.charAt(x)) {
					if (x == 0) {
						x = -1;
						break;
					} else {
						x = lps[x - 1];
					}
				}
				lps[i] = x + 1;
			}

			/*
			 * Each element of the lps array holds the length of longest prefix which is
			 * also a suffix upto that index in the main string, so the last value in * the
			 * lps array gives us the length of longest prefix-suffix of the entire string
			 */
			
			/*index= [ 0,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12]
			 *s=     ["l","e","e","t","c","o","d","e","l","e","e","t"]
			 *lps=   [ 0,  0,  0,  0,  0,  0,  0,  0,  1,  2,  3,  4]
			 *
			 * Here, if you see index 3, lps is 0, that means for string "lee"
			 * the length of longest prefix which is suffix is 0.
			 * 
			 * if you see index 10, lps is 2, so that means for string
			 * "leetcodele" the length of longest prefix which is suffix is 2
			 */
			return s.substring(0, lps[lps.length - 1]);
		}
	 
	public static void main(String[] args) {
		String s = "level";
		System.out.println(longestPrefix(s)); // Output: "l"

	}

}
