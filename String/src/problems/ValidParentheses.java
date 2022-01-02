package problems;

import java.util.Stack;

// 20. Valid Parentheses
// https://leetcode.com/problems/valid-parentheses/

/*It's brilliant. The basic idea is to push the right parentheses ')', ']', or '}' into the stack 
each time when we encounter left ones. And if a right bracket appears in the string, we need check 
if the stack is empty and also whether the top element is the same with that right bracket. 
If not, the string is not a valid one. At last, we also need check if the stack is empty.*/

public class ValidParentheses {
	// push the corresponding closing braces on stack when you encounter an opening
	// bracket
	// when you encounter a closing bracket, check if the stack is empty or if the
	// top of the stack has the corresponding closing bracket which we pushed
	// earlier
	// if either of the above case is false its false
	// at last check if the stack is empty

	public boolean isValid(String s) {
		Stack<Character> st = new Stack<>();
		for (char c : s.toCharArray()) {
			if (c == '(') {
				st.push(')');
			} else if (c == '{') {
				st.push('}');
			} else if (c == '[') {
				st.push(']');
			} else if (st.isEmpty() || st.pop() != c) {
				return false;
			}
		}
		return st.isEmpty();
	}

	public boolean isValid1(String s) {
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
		/*
		 * Input: s = "()[]{}" Output: true
		 */
	}

}
