package slidingwindow.variable;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/minimum-window-substring/

public class MaximumWindowSubstring {

	// variable slidig window prob
	public static String minWindow(String s, String t) {
		int start = 0;
		int min = Integer.MAX_VALUE;
		String res = "";
		int count = 0;

		Map<Character, Integer> seen = new HashMap<Character, Integer>();

		// prepare the window
		for (int i = 0; i < t.length(); i++) {
			seen.put(t.charAt(i), seen.getOrDefault(t.charAt(i), 0) + 1);
		}
		count = seen.size();

		for (int end = 0; end < s.length(); end++) {
			// keep track of how many more unique chars are needed
			char q = s.charAt(end);
			if (seen.containsKey(q)) {
				seen.put(q, seen.get(q) - 1);

				// no longer need character q, as we have that character
				// (required number of times) in our substr till now
				if (seen.get(q) == 0) {
					count--;
				}
			}

			while (count == 0) {
				// got a possible ans
				if (end - start + 1 < min) {

					min = (end - start) + 1;
					res = s.substring(start, end + 1);
				}

				// look for smaller lengths till count=0(means
				// till there are required number of chars present
				// in the substr so far)

				// do the aboveby reducing the subtsr from left side
				// using start pointer, simultaneously decreasing its count
				// in the map

				char w = s.charAt(start);

				// removing it from map means we would need that char in our
				// substr again
				if (seen.containsKey(w)) {
					seen.put(w, seen.get(w) + 1);
					if (seen.get(w) == 1) {
						count = 1;
					}
				}

				start++;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		String s = "ADOBECODEBANC";
		String t = "ABC";
		System.out.print(minWindow(s, t)); // Output- "BANC"
	}

}
