package slidingwindow.fixed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/find-all-anagrams-in-a-string/
// https://www.geeksforgeeks.org/count-occurrences-of-anagrams/

public class FindOccurancesOfAnagrams {

	static boolean araAnagram(String s1, String s2) {
		// converting strings to char arrays
		char[] ch1 = s1.toCharArray();
		char[] ch2 = s2.toCharArray();

		// sorting both char arrays
		Arrays.sort(ch1);
		Arrays.sort(ch2);

		// Check for equality of strings
		if (Arrays.equals(ch1, ch2))
			return true;
		else
			return false;
	}

	static int countAnagrams(String text, String word) {
		int N = text.length();
		int n = word.length();

		// Initialize result
		int res = 0;

		for (int i = 0; i <= N - n; i++) {

			String s = text.substring(i, i + n);

			// Check if the word and substring are
			// anagram of each other.
			if (araAnagram(word, s))
				res++;
		}

		return res;
	}
	
	// sliding window with counter using 1 hashmap
	public static List<Integer> findAnagrams(String s, String p) {

		int n = s.length();
		int k = p.length();

		// final ans- keeps track of the count of anagrams
		List<Integer> res = new ArrayList<>();

		// has unique chars and their count in the pattern
		Map<Character, Integer> charCount = new HashMap<>();

		// to store the count of unique characters in the pattern=map.size()
		int count = 0;

		// prepare the window
		for (char c : p.toCharArray()) {
			charCount.put(c, charCount.getOrDefault(c, 0) + 1);
		}

		count = charCount.size();

		// start traversing the actual string in windows of length p and check
		// if the count of characters in the window is same as in the map

		int start = 0;
		for (int end = 0; end < n; end++) {

			// traverse and do calculations
			char e = s.charAt(end);
			if (charCount.containsKey(e)) {
				charCount.put(e, charCount.get(e) - 1);
				if (charCount.get(e) == 0) {
					count--;
				}
			}

			// we have hit the window size
			if (end - start + 1 == k) {
				// find an ans from the calculations done above
				if (count == 0) {
					res.add(start);
				}
				if (charCount.containsKey(s.charAt(start))) {
					charCount.put(s.charAt(start), charCount.get(s.charAt(start)) + 1);
					if (charCount.get(s.charAt(start)) == 1) {
						count++;
					}
				}
				// slide the window forward
				start++;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		String s = "abab";
		String p = "ab";
		List<Integer> res = findAnagrams(s, p);
		res.forEach(e -> System.out.print(e + " ")); // Output: [0,1,2]

	}

}
