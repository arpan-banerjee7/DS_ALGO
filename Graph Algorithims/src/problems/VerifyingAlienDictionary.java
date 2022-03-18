package problems;

// 953. Verifying an Alien Dictionary
// https://leetcode.com/problems/verifying-an-alien-dictionary/
// https://leetcode.com/problems/verifying-an-alien-dictionary/discuss/1776305/Java-beat-100

public class VerifyingAlienDictionary {
	private boolean isSmaller(String word1, String word2, int[] dict) {
		int len1 = word1.length();
		int len2 = word2.length();
		for (int i = 0; i < Math.min(len1, len2); i++) {
			if (dict[word1.charAt(i) - 'a'] < dict[word2.charAt(i) - 'a']) {
				return true;
			} else if (dict[word1.charAt(i) - 'a'] > dict[word2.charAt(i) - 'a']) {
				return false;
			}
		}
		if (len1 <= len2) {
			return true;
		}
		return false;

	}

	public boolean isAlienSorted(String[] words, String order) {
		int[] dict = new int[26];
		for (int i = 0; i < 26; i++) {
			dict[order.charAt(i) - 'a'] = i;
		}

		for (int i = 0; i < words.length - 1; i++) {
			if (!isSmaller(words[i], words[i + 1], dict)) {
				return false;
			}
		}
		return true;
	}
	public static void main(String[] args) {
		/*
		 * Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
		 * Output: true Explanation: As 'h' comes before 'l' in this language, then the
		 * sequence is sorted.
		 */
	}
}
