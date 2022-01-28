package stackqueue.problems;

import java.util.Arrays;
import java.util.Stack;

// https://www.youtube.com/watch?v=T5s96ynzArg&list=PL_z_8CaSLPWdeOezg68SKkeLN4-T_jNHd&index=3 Aditya Verma
// https://www.techiedelight.com/next-greater-element/ 3rd approach

// Monotonous Decreasing stack
public class PreviousGreaterElement {

	private static int[] findNextGreaterElements(int[] arr) {
		int[] res = new int[arr.length];
		// Arrays.fill(res, -1);

		Stack<Integer> st = new Stack<Integer>();
		int n = arr.length;

		for (int i = 0; i < n; i++) {
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

//		for (int i = 0; i <= n - 1; i++) {
//			while (!stack.isEmpty()) {
//				if (stack.peek() <= arr[i]) {
//					stack.pop();
//				} else {
//					res[i] = stack.peek();
//					break;
//				}
//			}
//			stack.push(arr[i]);
//		}

		return res;
	}

	public static void main(String[] args) {
		int[] arr = { 2, 7, 3, 5, 4, 6, 8 };

		int[] result = findNextGreaterElements(arr);
		System.out.println(Arrays.toString(result)); // Output- [-1, -1, 7, 7, 5, 7, -1]
	}

}
