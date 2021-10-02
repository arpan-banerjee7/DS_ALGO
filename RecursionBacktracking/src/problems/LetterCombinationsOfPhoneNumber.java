package problems;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/letter-combinations-of-a-phone-number/
// https://www.youtube.com/watch?v=0snEunUacZY
// https://doubleroot.in/lessons/permutations-combinations/division-into-groups-1/

public class LetterCombinationsOfPhoneNumber {

	// TC -n*n^4 -?
	private static void solve(List<String> res, String[] charMap, String digits, String op, int i) {
		if (op.length() == digits.length()) {
			res.add(op);
			return;
		}

		String letters = charMap[digits.charAt(i) - '0'];// imp
		for (int j = 0; j < letters.length(); j++) {
			solve(res, charMap, digits, op + letters.charAt(j), i + 1);
		}
	}

	// less intuition
	public static List<String> letterCombinations(String digits) {
		String[] charMap = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
		String op = "";
		List<String> res = new ArrayList<>();
		if (digits.length() != 0) {
			solve(res, charMap, digits, op, 0);
		}
		return res;
	}

	public static void main(String[] args) {
		String digits = "23";
		System.out.println(letterCombinations(digits));

		// Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
	}

}
