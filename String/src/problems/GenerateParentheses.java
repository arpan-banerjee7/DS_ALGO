package problems;

import java.util.ArrayList;
import java.util.List;

// 22. Generate Parentheses
// https://leetcode.com/problems/generate-parentheses/

public class GenerateParentheses {
	private void generate(int n, List<String> res, StringBuilder temp, int open, int close) {
		if (open == n && close == n) {
			res.add(temp.toString());
			return;
		}

		if (open <= n) {
			temp.append("(");
			generate(n, res, temp, open + 1, close);
			temp.setLength(temp.length() - 1);
		}

		if (close < open) {
			temp.append(")");
			generate(n, res, temp, open, close + 1);
			temp.setLength(temp.length() - 1);
		}
	}

	public List<String> generateParenthesis(int n) {
		List<String> res = new ArrayList<>();
		generate(n, res, new StringBuilder(), 0, 0);
		return res;
	}

	public static void main(String[] args) {
		/*
		 * Input: n = 3 Output: ["((()))","(()())","(())()","()(())","()()()"]
		 */
	}

}
