package slidingwindow.variable;

import java.util.HashMap;
import java.util.Map;

// https://www.geeksforgeeks.org/find-the-longest-substring-with-k-unique-characters-in-a-given-string/

public class LongestSubstringWithKUniqueCharacters {

	public static int longestkSubstr(String s, int k) {

		int n = s.length();
		int maxLen = -1; // Stores the length of the longest substring with k unique characters found so
							// far.
		Map<Character, Integer> windowCharCount = new HashMap<>(); // Stores the character count for each character in
																	// the current window
		int start = 0;

		for (int end = 0; end < n; end++) {

			// Add the next character to the sliding window
			char c = s.charAt(end);
			windowCharCount.put(c, windowCharCount.getOrDefault(c, 0) + 1);

			// Shrink the sliding window, until we have exactly 'k' distinct characters in
			// the window
			while (windowCharCount.size() > k) {
				char leftChar = s.charAt(start);

				// Discard the character at windowStart since we're gonna move it out of the
				// window now.
				windowCharCount.put(leftChar, windowCharCount.get(leftChar) - 1);
				if (windowCharCount.get(leftChar) == 0) {
					windowCharCount.remove(leftChar);
				}

				start++; // Shrink the window
			}

			if (windowCharCount.size() == k) {
				// Update maximum length found so far
				maxLen = Math.max(maxLen, end - start + 1);
			}
		}

		return maxLen;
	}

	public static void main(String[] args) {
		String s = "aabacbebebe";
		int k = 3;
		System.out.println(longestkSubstr(s, k)); // Output-7

	}

}
