package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*49. Group Anagrams
// https://leetcode.com/problems/group-anagrams/
key point here is to form the unique key
1. sorting
2. char[]
3. int[]
https://leetcode.com/problems/group-anagrams/discuss/1264167/Come-and-see!-Build-the-key-with-int26-more-code-but-easy-to-think-and-write
*/

// https://practice.geeksforgeeks.org/problems/print-anagrams-together/1/
// https://leetcode.com/problems/group-anagrams/
// https://www.geeksforgeeks.org/given-a-sequence-of-words-print-all-anagrams-together/
// https://www.geeksforgeeks.org/count-occurrences-of-anagrams/ --> brute force

public class GroupAnagrams {
	public List<List<String>> groupAnagrams(String[] strs) {
		Map<String, List<String>> seen = new HashMap<>();
		List<List<String>> res = new ArrayList<>();
		for (String s : strs) {
			char[] ch = s.toCharArray();
			Arrays.sort(ch);
			String key = new String(ch);
			if (!seen.containsKey(key)) {
				seen.put(key, new ArrayList<String>());
			}
			seen.get(key).add(s);
		}
		return new ArrayList<>(seen.values());
	}

	public List<List<String>> groupAnagrams1(String[] strs) {
		if (strs == null || strs.length == 0)
			return new ArrayList<>();
		Map<String, List<String>> map = new HashMap<>();
		for (String s : strs) {
			char[] ca = new char[26];
			for (char c : s.toCharArray())
				ca[c - 'a']++;
			String keyStr = String.valueOf(ca);
			if (!map.containsKey(keyStr))
				map.put(keyStr, new ArrayList<>());
			map.get(keyStr).add(s);
		}
		return new ArrayList<>(map.values());
	}

	public List<List<String>> groupAnagrams2(String[] strs) {
		List<List<String>> result = new ArrayList<>();
		if (strs == null || strs.length == 0)
			return result;

		Map<String, List<String>> map = new HashMap<>();
		for (String str : strs) {
			int[] chars = new int[26];
			for (char c : str.toCharArray())
				chars[c - 'a']++;

			StringBuilder sb = new StringBuilder();
			// build key in form of "char+freq", e.g. [2, 1, 3] => 'a2b1c3'
			// BTW, remember countAndSay problem?
			for (int i = 0; i < 26; i++) {
				char c = (char) (i + 'a');
				int freq = chars[i];
				// ignore if the freq is 0 to save some time, but it is minimal
				if (freq != 0)
					sb.append(c).append(freq);
			}

			String key = sb.toString();
			if (!map.containsKey(key))
				map.put(key, new ArrayList<String>());
			map.get(key).add(str);
		}

		result.addAll(map.values());
		return result;
	}

	public static void main(String[] args) {
		/*
		 * Input: strs = ["eat","tea","tan","ate","nat","bat"] Output:
		 * [["bat"],["nat","tan"],["ate","eat","tea"]]
		 */

	}

}
