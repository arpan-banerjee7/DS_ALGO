package stackqueue.problems;

import java.util.Stack;

//Leetcode 224 - https://leetcode.com/problems/basic-calculator/
//Ref: https://www.youtube.com/watch?v=081AqOuasw0

public class BasicCalculator {
	public static void main(String[] args) {
		String s = "(1+(4+5+2)-3)+(6+8)";
		int res = calculate(s);
		System.out.println("The result is " + res);
	}

	public static int calculate(String s) {
		int sum = 0;
		int sign = 1;
		Stack<Integer> stack = new Stack<Integer>();

		for (int i = 0; i < s.length(); i++) {
			if (Character.isDigit(s.charAt(i))) {
				int num = 0;
				while (i < s.length() && Character.isDigit(s.charAt(i))) {
					num = num * 10 + s.charAt(i) - '0';
					i++;
				}
				sum += sign * num;
				i--;
			} else if (s.charAt(i) == '+') {
				sign = 1;
			} else if (s.charAt(i) == '-') {
				sign = -1;
			} else if (s.charAt(i) == '(') {
				stack.push(sum);
				stack.push(sign);
				sum = 0;
				sign = 1;
			} else if (s.charAt(i) == ')') {
				sum = stack.pop() * sum;
				sum = sum + stack.pop();
			}
		}
		return sum;
	}

}
