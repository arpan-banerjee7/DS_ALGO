package stackqueue.problems;

import java.util.Stack;

// https://leetcode.com/problems/valid-parentheses/
// https://www.geeksforgeeks.org/check-for-balanced-parentheses-in-an-expression/
// https://www.youtube.com/watch?v=FPvAfpSXjRw&list=PLEJXowNB4kPzEvxN8ed6T13Meet7HP3h0&index=4 Techdose 
public class ValidParanthesis {

	public static boolean isValid(String s) {
		Stack<Character> stack = new Stack<Character>();
		// Iterate through string until empty
		for (int i = 0; i < s.length(); i++) {
			// Push any open parentheses onto stack
			if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{')
				stack.push(s.charAt(i));
			// Check stack for corresponding closing parentheses, false if not valid
			else if (s.charAt(i) == ')' && !stack.empty() && stack.peek() == '(')
				stack.pop();
			else if (s.charAt(i) == ']' && !stack.empty() && stack.peek() == '[')
				stack.pop();
			else if (s.charAt(i) == '}' && !stack.empty() && stack.peek() == '{')
				stack.pop();
			else
				return false;
		}
		// return true if no open parentheses left in stack
		return stack.empty();
	}

	public static void main(String[] args) {
		String expr = "([{}])";

		// Function call
		if (isValid(expr))
			System.out.println("Balanced ");
		else
			System.out.println("Not Balanced ");
	}

}
