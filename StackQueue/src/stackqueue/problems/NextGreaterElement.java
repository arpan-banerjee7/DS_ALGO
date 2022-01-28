package stackqueue.problems;

import java.util.Stack;

// https://www.geeksforgeeks.org/next-greater-element/
// https://leetcode.com/problems/next-greater-element-i/ (TODO)
// https://www.techiedelight.com/next-greater-element/ 

// https://youtu.be/NXOOYYwpbg4 Aditya Verma

// Time Complexity: O(N) 
// Auxiliary Space: O(N)
// Monotonous Decreasing stack
public class NextGreaterElement {

	// Function to find the next greater element for each element of the array.
	public static long[] nextGreaterElement(long[] arr, int n) {

		Stack<Long> st = new Stack<>();
		long[] res = new long[n];
		for (int i = n - 1; i >= 0; i--) {
			while (!st.isEmpty() && st.peek() <= arr[i]) {
				st.pop();
			}
			if (st.isEmpty()) {
				res[i] = -1;
			} else {
				res[i] = st.peek();
			}
			st.push(arr[i]);

		}
		return res;
	}

	public static void main(String[] args) {
		long arr[] = { 11, 13, 13, 21, 3 }; 
		int n = arr.length;
		long[] res = nextGreaterElement(arr, n);
		for (long l : res) {
			System.out.print(l + " ");// Output- 13 21 21 -1 -1 
		}

	}

}
