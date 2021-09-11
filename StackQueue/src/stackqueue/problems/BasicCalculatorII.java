package stackqueue.problems;

import java.util.Stack;
// Leetcode 227 - https://leetcode.com/submissions/detail/551952589/
// Ref: https://www.youtube.com/watch?v=wirVxwyhsLU Coding Interview Preparation

public class BasicCalculatorII {

	public static int calculate(String s) {
		int result = 0;
		// proceed only if the input is valid
		if (s != null && s.length() > 0) {
			Stack<Integer> nums = new Stack<>();
			char lastOperator = '+'; // start with +
			int num = 0; // start with 0
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				// find the number , if its a double digit
				if (Character.isDigit(c)) {
					num = num * 10 + c - '0';
				}
				// if the character is operator OR last character
				if (isOperator(c) || i == s.length() - 1) {
					// if the last operation was addition, add the number to stack
					if (lastOperator == '+') {
						nums.push(num);
					}
					// if the last operation was deletion, add the negative of the number to stack
					else if (lastOperator == '-') {
						nums.push(-1 * num);
					}
					/*
					 * if the last operation was multiplication or division, pop the last number
					 * from stack and perform the operation on current number and the number from
					 * stack and add the result back to the stack.
					 */
					else if (lastOperator == '*') {
						nums.push(nums.pop() * num);
					} else if (lastOperator == '/') {
						nums.push(nums.pop() / num);
					}
					// set the number back to zero
					num = 0;
					// set the current operation as last operation
					lastOperator = c;
				}
			}
			/*
			 * at this point , the stack will have only the numbers that need to be added.
			 * get those from stack and add to the result.
			 */
			while (!nums.isEmpty()) {
				result = result + nums.pop();
			}
		}
		return result;
	}

	private static boolean isOperator(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/';
	}
	
	public static void main(String[] args) {
		String s = "32 - 1 + 20*2 + 20/2";
		int res = calculate(s);
		System.out.println("The result is " + res);
	}
}
