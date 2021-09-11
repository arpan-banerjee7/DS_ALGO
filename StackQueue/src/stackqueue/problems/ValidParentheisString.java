package stackqueue.problems;

import java.util.Stack;

// https://leetcode.com/problems/valid-parenthesis-string/
// https://youtu.be/KuE_Cn3xhxI Techdose
public class ValidParentheisString {

	private static boolean checkValidString(String s) {

		Stack<Integer> stack = new Stack<Integer>();
		Stack<Integer> star = new Stack<Integer>();

		for (int i = 0; i < s.length(); i++) {
			char x = s.charAt(i);
			if (x == '(') {
				stack.push(i);
				continue;
			} else if (x == '*') {
				star.push(i);
			} else {
				if (!stack.isEmpty()) {
					stack.pop();
				} else if (!star.empty()) {
					// Require for this case
					// Input: s = "(*))"
					// Output: true
					star.pop();
				} else {
					return false;
				}
			}
		}

		/*
		 * Now process leftover opening brackets balance other opening brackets -- ((*)
		 * position of * and ( is very imp, in order to balance ( must occur berfore *
		 * and ) must occur after * check that by comparing the indices here we are
		 * tryig to balance the remaining (
		 * 
		 */
		while (!stack.isEmpty()) {
			if (star.empty())
				return false;
			else if (stack.peek() < star.peek()) {
				stack.pop();
				star.pop();
			} else // CASE: stack.top() > star.top()
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		String s = "(*))";

		// Function call
		if (checkValidString(s))
			System.out.println("Balanced ");
		else
			System.out.println("Not Balanced ");
	}

}
