package problems;

import java.util.HashMap;
import java.util.Map;

// https://www.geeksforgeeks.org/length-of-the-longest-substring-without-repeating-characters/
// https://leetcode.com/problems/longest-substring-without-repeating-characters/
// https://www.youtube.com/watch?v=L6cffskouPQ&list=PL_z_8CaSLPWeM8BDJmIYDaoQ5zuwyxnfj&index=11

// check the brute force solutions in gfg
public class LongestSubstringWithoutRepeatingChars {

	// variable window / variation of longest subarray with k unique chars
	// here all characters should be uiniqe so k=length of string
	int longestUniqueSubsttr(String S) {
		int start = 0;
		int max = Integer.MIN_VALUE;
		Map<Character, Integer> seen = new HashMap<>();
		for (int end = 0; end < S.length(); end++) {
			// do calculations
			char c = S.charAt(end);
			seen.put(c, seen.getOrDefault(c, 0) + 1);

			// condition has been hit
			while (seen.size() < end - start + 1) {
				// keep removing elements from map until size of map
				// is same as the window size, hence guaranteeing the presence of
				// unique chars in the window

				seen.put(S.charAt(start), seen.get(S.charAt(start)) - 1);
				if (seen.get(S.charAt(start)) == 0) {
					seen.remove(S.charAt(start));
				}

				// slide the window
				start++;
			}

			// find ans from calculations
			if (seen.size() == end - start + 1) {
				max = Math.max(max, end - start + 1);
			}
		}
		return max;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
