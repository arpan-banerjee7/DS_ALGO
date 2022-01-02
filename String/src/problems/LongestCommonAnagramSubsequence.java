package problems;

import java.util.HashMap;
import java.util.Map;

// Longest Common Anagram Subsequence
// https://www.geeksforgeeks.org/longest-common-anagram-subsequence/

public class LongestCommonAnagramSubsequence {
	private static int findLCAS(String s1, String s2) {
		int count = 0;
		Map<Character, Integer> charMap = new HashMap<>();
		for (char c : s1.toCharArray()) {
			charMap.put(c, charMap.getOrDefault(c, 0) + 1);
		}

		for (char ch : s2.toCharArray()) {
			if (charMap.containsKey(ch)) {
				count++;
				charMap.put(ch, charMap.get(ch) - 1);
				if (charMap.get(ch) == 0) {
					charMap.remove(ch);
				}
			}
		}
		return count;
	}

	private static int findLCAS1(String s1, String s2) {
		int count = 0;
		int[] freq = new int[26];
		for (char c : s1.toCharArray()) {
			freq[c - 'a']++;
		}

		for (char ch : s2.toCharArray()) {
			if (freq[ch - 'a'] > 0) {
				count++;
				freq[ch - 'a']--;
			}
		}
		return count;
	}

//------------------------------ GFG approach----------------------------//
	static int SIZE = 26;

	// function to find the
	// length() of the longest
	// common anagram subsequence
	static int longCommomAnagramSubseq(String str1, String str2, int n1, int n2) {
		// hash tables for
		// storing frequencies
		// of each character
		int[] freq1 = new int[SIZE];
		int[] freq2 = new int[SIZE];

		for (int i = 0; i < SIZE; i++) {
			freq1[i] = 0;
			freq2[i] = 0;
		}

		int len = 0;

		// calculate frequency
		// of each character of
		// 'str1[]'
		for (int i = 0; i < n1; i++)
			freq1[(int) str1.charAt(i) - (int) 'a']++;

		// calculate frequency
		// of each character
		// of 'str2[]'
		for (int i = 0; i < n2; i++)
			freq2[(int) str2.charAt(i) - (int) 'a']++;

		// for each character add
		// its minimum frequency
		// out of the two Strings
		// in 'len'
		for (int i = 0; i < SIZE; i++)
			len += Math.min(freq1[i], freq2[i]);

		// required length()
		return len;
	}

	// Driver Code
	public static void main(String args[]) {
		String str1 = "abdacp";
		String str2 = "ckamb";
		int n1 = str1.length();
		int n2 = str2.length();
		System.out.print("Length = " + longCommomAnagramSubseq(str1, str2, n1, n2));
	}
}
