package goldmansachs;

import java.util.Stack;

// 1047. Remove All Adjacent Duplicates In String
// https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
// https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/discuss/294893/JavaC++Python-Two-Pointers-and-Stack

public class RemoveAllAdjacentDuplicatesInString {

	// with extra space
	public String removeDuplicates(String S) {
		Stack<Character> stack = new Stack<>();
		for (char s : S.toCharArray()) {
			if (!stack.isEmpty() && stack.peek() == s)
				stack.pop();
			else
				stack.push(s);
		}
		StringBuilder sb = new StringBuilder();
		for (char s : stack)
			sb.append(s);
		return sb.toString();
	}

	// // without extra space
	public String removeDuplicates1(String s) {
		int n = s.length();
		int i = 0; // modifies the char array and will be used to print the resultant string
		int j = 0; // current iteration
		char res[] = s.toCharArray();
		while (i < n && j < n) {
			res[i] = res[j];
			if (i > 0 && res[i] == res[i - 1]) {// same adjacent chars
				i -= 2;
				// go back in res array to two places, in next iteration we res[i]=res[j]
				// will modify the array
			}
			i++;
			j++;
		}
		return new String(res, 0, i);
	}

	public static void main(String[] args) {
		/*
		 * Input: s = "abbaca" Output: "ca" Explanation: For example, in "abbaca" we
		 * could remove "bb" since the letters are adjacent and equal, and this is the
		 * only possible move. The result of this move is that the string is "aaca", of
		 * which only "aa" is possible, so the final string is "ca".
		 */
	}

}
