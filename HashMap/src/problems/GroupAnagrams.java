package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/group-anagrams/
// https://www.geeksforgeeks.org/given-a-sequence-of-words-print-all-anagrams-together/
// https://www.geeksforgeeks.org/count-occurrences-of-anagrams/ --> brute force


public class GroupAnagrams {

	public static List<List<String>> groupAnagrams(String[] strs) {
		Map<String, ArrayList<String>> seen = new HashMap<>();
		for (String s : strs) {
			char[] arr = s.toCharArray();
			Arrays.sort(arr);
			String key = String.valueOf(arr);
			if (!seen.containsKey(key)) {
				seen.put(key, new ArrayList<String>());
			}
			seen.get(key).add(s);
		}
		return new ArrayList<>(seen.values());
	}

	public static void main(String[] args) {
		String[] arr = { "eat", "tea", "tan", "ate", "nat", "bat" };
		List<List<String>> res = groupAnagrams(arr);
		res.forEach(e -> System.out.println(e.toString()));
	}

}
