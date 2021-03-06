package problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// https://leetcode.com/problems/word-break/
// https://www.techiedelight.com/word-break-problem/
// https://leetcode.com/problems/word-break/submissions/

/*https://www.youtube.com/watch?v=LmHWIsBQBU4
https://www.geeksforgeeks.org/word-break-problem-dp-32/

overlapping subsproblems-
"abcde"
["a","b","c","d","ab","abc","abcd"]

String s = "myapplemyapplemy";
		String[] wordDict = {"m", "my", "apple" };*/
		
public class WordBreak1 {

	private static boolean isPossible(String s, Set<String> seen, Map<String, Boolean> dp) {
		if (s.length() == 0) {
			return true;
		}

		if (dp.containsKey(s)) {
			return dp.get(s);
		}

		for (int i = 1; i <= s.length(); i++) {
			if (seen.contains(s.substring(0, i))) {
				if (isPossible(s.substring(i), seen, dp)) {
					dp.put(s.substring(i), true);
					return true;
				}
			}

		}
		dp.put(s, false);

		return false;
	}

	public static boolean wordBreak(String s, List<String> wordDict) {
		Set<String> seen = new HashSet<>();
		Map<String, Boolean> dp = new HashMap<>();

		for (String word : wordDict) {
			seen.add(word);
		}
		return isPossible(s, seen, dp);
	}

	public static void main(String[] args) {
		String s = "leetcode";
		List<String> wordDict = Arrays.asList("leet", "code");
		System.out.println(wordBreak(s, wordDict));
		// Output: true
	}

}
