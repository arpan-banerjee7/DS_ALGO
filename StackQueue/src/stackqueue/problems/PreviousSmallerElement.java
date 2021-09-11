package stackqueue.problems;

import java.util.Arrays;
import java.util.Stack;
// https://www.youtube.com/watch?v=85LWui3FlVk&list=PL_z_8CaSLPWdeOezg68SKkeLN4-T_jNHd&index=4 Aditya Verma
// https://www.techiedelight.com/next-greater-element/ 3rd approach

public class PreviousSmallerElement {

	private static int[] findPrevSmallerElements(int[] arr) {
		int[] res = new int[arr.length];
		Arrays.fill(res, -1);

		Stack<Integer> st = new Stack<Integer>();
		int n = arr.length;

		for (int i = 0; i < n; i++) {
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
		
//		for (int i = 0; i <= n - 1; i++) {
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

		return res;
	}
	
	public static void main(String[] args) {
		int[] arr = { 2, 7, 3, 5, 4, 6, 8 };

		int[] result = findPrevSmallerElements(arr);
		System.out.println(Arrays.toString(result)); // Ouput- [-1, 2, 2, 3, 3, 4, 6]

	}

}
