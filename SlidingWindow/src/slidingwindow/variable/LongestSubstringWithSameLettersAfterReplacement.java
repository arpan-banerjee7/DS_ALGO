package slidingwindow.variable;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/longest-repeating-character-replacement/

public class LongestSubstringWithSameLettersAfterReplacement {
	private static int findLengthOfLongestSubstringWithSameLettersAfterReplacement(String s, int k) {
		int n = s.length();

		// length of the longest substring with same letters after replacement.
		int maxLen = -1;

		// character count for each character in the current window
		Map<Character, Integer> windowCharCount = new HashMap<>();

		int windowStart = 0;
		for (int windowEnd = 0; windowEnd < n; windowEnd++) {
			// Add the next character to the window
			char c = s.charAt(windowEnd);
			windowCharCount.put(c, windowCharCount.getOrDefault(c, 0) + 1);

			// Calculate max repeating character in the current window
			int maxRepeatLetterCount = getMaxRepeatLetterCount(windowCharCount);

			/*
			 * The current window has a letter that repeats 'maxRepeatLetterCount' times. If
			 * the remaining letters in the window are less than or equal to k then we can
			 * replace them all. Otherwise, we need to shrink the window since we are not
			 * allowed to replace more than 'k' letters.
			 */
			while (windowEnd - windowStart + 1 - maxRepeatLetterCount > k) {
				char leftChar = s.charAt(windowStart);
				windowCharCount.put(leftChar, windowCharCount.get(leftChar) - 1);
				windowStart++; // Shrink the window
			}

			// At this point, the number of remaining letters in the window are less than or
			// equal to k.
			// So we can replace them all to obtain a substring with same letters.
			// Update the max length if the current window size is longer.
			maxLen = Math.max(maxLen, windowEnd - windowStart + 1);
		}

		return maxLen;
	}

	private static int getMaxRepeatLetterCount(Map<Character, Integer> charCount) {
		int maxCount = 0;
		for (int count : charCount.values()) {
			if (maxCount < count) {
				maxCount = count;
			}
		}
		return maxCount;
	}

	public static void main(String[] args) {
		String s = "ABAB";
		int k=2;
		System.out.println(findLengthOfLongestSubstringWithSameLettersAfterReplacement(s, k)); // Output-4
	}

}
