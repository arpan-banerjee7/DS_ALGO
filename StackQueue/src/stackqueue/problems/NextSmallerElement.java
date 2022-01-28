package stackqueue.problems;

import java.util.Arrays;
import java.util.Stack;

// https://www.geeksforgeeks.org/next-smaller-element/
// https://youtu.be/T5s96ynzArg

// Time Complexity: O(N) 
// Auxiliary Space: O(N)

// Monotonous Increasing stack
public class NextSmallerElement {

	private static int[] findNextSmallerElements(int[] arr) {
		int[] res = new int[arr.length];
		//Arrays.fill(res, -1);

		Stack<Integer> st = new Stack<Integer>();
		int n = arr.length;

		for (int i = n - 1; i >= 0; i--) {
			while (!st.isEmpty() && st.peek() >= arr[i]) {
				st.pop();
			}
			if (st.isEmpty()) {
				res[i] = -1;
			} else {
				res[i] = st.peek();
			}
			st.push(arr[i]);
		}

//		
//		for (int i = n - 1; i >= 0; i--) {
//			while (!st.isEmpty()) {
//				if (st.peek() >= arr[i]) {
//					st.pop();
//				} else {
//					res[i] = st.peek();
//					break;
//				}
//			}
//			st.push(arr[i]);
//		}
//       
		return res;
	}

	public static void main(String[] args) {
		int[] arr = { 4, 5, 2, 10, 8 };

		int[] result = findNextSmallerElements(arr);
		System.out.println(Arrays.toString(result)); // Output - [2, 2, -1, 8, -1]

	}

}
