package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// https://www.youtube.com/watch?v=LmHWIsBQBU4
// memo ex 1 - not clear -https://leetcode.com/problems/word-break-ii/discuss/904761/Java-or-Memoization
// finally got it - https://www.youtube.com/watch?v=jQJyWcRPEpE
// https://leetcode.com/problems/word-break-ii/discuss/232645/Java-Accepted-Memoization-%2B-DFS

/*Print word break
https://www.youtube.com/watch?v=LmHWIsBQBU4
*/
public class WordBreak2 {

	private static List<String> solve(String s, Set<String> seen, String op, List<String> res) {

		if (s.length() == 0) {
			res.add(op.trim());
		}
		for (int i = 1; i <= s.length(); i++) {
			String prefix = s.substring(0, i);
			if (seen.contains(prefix)) {
				solve(s.substring(i), seen, op + prefix + " ", res);
			}

		}
		return res;

	}

	public static List<String> wordBreak(String s, List<String> wordDict) {
		Set<String> seen = new HashSet<>(wordDict);
		List<String> res = new ArrayList<>();
		return solve(s, seen, new String(), res);
	}

	public static void main(String[] args) {
		String s = "catsanddog";
		List<String> wordDict = Arrays.asList("cat", "cats", "and", "sand", "dog");
		System.out.println(wordBreak(s, wordDict));

	}

	
	
	//-------------------- Print word Break------------------------------//
	class Solution {

		private void solve(String s, Set<String> seen, String op, List<String> res) {
			if (s.length() == 0) {
				res.add(op.trim());
				return;
			}
			for (int i = 1; i <= s.length(); i++) {
				if (seen.contains(s.substring(0, i))) {
					solve(s.substring(i), seen, op + s.substring(0, i) + " ", res);
				}

			}

		}

		public List<String> wordBreak(String s, List<String> wordDict) {
			List<String> res = new ArrayList<>();
			Set<String> seen = new HashSet<>();
			Map<String, Boolean> dp = new HashMap<>();

			for (String word : wordDict) {
				seen.add(word);
			}
			solve(s, seen, new String(), res);
			return res;
		}
	}

}
