package goldmansachs;

import java.util.Stack;
// 1209. Remove All Adjacent Duplicates in String II
// https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/
// https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/discuss/392933/JavaC%2B%2BPython-Two-Pointers-and-Stack-Solution

public class RemoveAllAdjacentDuplicatesInStringII {
	// extra space using stack
	class Pair {
		char c;
		int count;

		public Pair(char c, int count) {
			this.c = c;
			this.count = count;
		}
	}

	public String removeDuplicates(String s, int k) {
		Stack<Pair> stack = new Stack<>();
		for (char c : s.toCharArray()) {
			// compare prev with cur
			if (!stack.isEmpty() && stack.peek().c == c) {
				stack.peek().count++;
			} else {
				// not repeat
				stack.push(new Pair(c, 1));
			}
			// remove when num of dup == k
			if (stack.peek().count == k)
				stack.pop();
		}
		// build result
		StringBuilder sb = new StringBuilder();
		for (Pair pair : stack) {
			for (int i = 0; i < pair.count; i++) {
				sb.append(pair.c);
			}
		}
		return sb.toString();
	}

	// without space
	public String removeDuplicates1(String s, int k) {
		int n = s.length();
		// modifies the char array and will be used to print the resultant string
		int i = 0;
		int j = 0; // current iteration
		char res[] = s.toCharArray();

		// as i moves back we have chances of getting k elements together again, so we
		// need to keep track of previous counts, dry run once to understand this
		int[] count = new int[n];
		while (i < n && j < n) {
			res[i] = res[j];
			// same adjacent chars
			// update count array based on i and not j
			count[i] = (i > 0 && res[i] == res[i - 1]) ? count[i - 1] + 1 : 1;
			if (count[i] == k)
				i -= k;

			i++;
			j++;
		}
		return new String(res, 0, i);
	}

	public static void main(String[] args) {
		/*
		 * Input: s = "deeedbbcccbdaa", k = 3 Output: "aa" Explanation: First delete
		 * "eee" and "ccc", get "ddbbbdaa" Then delete "bbb", get "dddaa" Finally delete
		 * "ddd", get "aa"
		 */
	}
}
