package problems;

// 5. Longest Palindromic Substring
// https://leetcode.com/problems/longest-palindromic-substring/

// https://www.youtube.com/watch?v=UflHuQj6MVA   Techdose
// https://www.youtube.com/watch?v=XYQecbcd6_c    Neetcode

public class LongestPalindromicSubstring {

	class Solution1 {
		public String longestPalindrome(String s) {
			int n = s.length();
			int maxLength = 0;
			int start = 0;

			// odd length
			for (int i = 0; i < n; i++) {
				int low = i, high = i;
				while (low >= 0 && high < n && s.charAt(low) == s.charAt(high)) {
					if (high - low + 1 > maxLength) {
						start = low;
						maxLength = high - low + 1;
					}
					low--;
					high++;
				}
			}

			// even length
			for (int i = 0; i < n - 1; i++) {

				int low = i, high = i + 1;
				while (low >= 0 && high < n && s.charAt(low) == s.charAt(high)) {
					if (high - low + 1 > maxLength) {
						start = low;
						maxLength = high - low + 1;
					}
					low--;
					high++;
				}

			}

			return s.substring(start, start + maxLength);
		}
	}

	class Solution {
		int maxlen = 0;
		int start = 0;

		public String longestPalindrome(String s) {

			if (s.length() < 2) {
				return s;
			}

			for (int i = 0; i < s.length(); i++) {
				expand(s, i, i);
				expand(s, i, i + 1);
			}

			return s.substring(start, start + maxlen);

		}

		public void expand(String str, int begin, int end) {
			while (begin >= 0 && end < str.length() && str.charAt(begin) == str.charAt(end)) {
				begin--;
				end++;
			}

			if (maxlen < (end - begin - 1)) {
				start = begin + 1;
				maxlen = end - begin - 1;
			}
		}
	}

	public static void main(String[] args) {
		/*
		 * Input: s = "babad" Output: "bab" Explanation: "aba" is also a valid answer.
		 */

	}

}
