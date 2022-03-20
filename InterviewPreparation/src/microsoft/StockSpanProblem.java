package microsoft;

import java.util.Arrays;
import java.util.Stack;

// https://leetcode.com/problems/online-stock-span/
// https://www.geeksforgeeks.org/the-stock-span-problem/
// https://youtu.be/p9T-fE1g1pU Aditya Verma

public class StockSpanProblem {

	// Function to calculate the span of stock price for all n days.
	public static int[] calculateSpan(int price[], int n) {
		// Your code here
		Stack<Integer> st = new Stack<>();
		int[] res = new int[n];

		for (int i = 0; i < n; i++) {

			while (!st.isEmpty() && price[st.peek()] <= price[i]) {
				st.pop();
			}
			if (st.isEmpty()) {
				st.push(i);
				res[i] = i + 1;
			} else if (price[st.peek()] > price[i]) {

				res[i] = i - st.peek();
				st.push(i);
			}
		}
		return res;
	}

	//------------------------------------easier version-----------------------------------------------//
	// same concept as previous greater element
	// monotonous decreasing stack
	class StockSpanner {
		Stack<int[]> st;

		public StockSpanner() {
			this.st = new Stack<>();
		}

		public int next(int price) {
			int span = 1;
			while (!st.isEmpty() && st.peek()[0] <= price) {
				span += st.peek()[1];
				st.pop();
			}
			st.push(new int[] { price, span });
			return span;
		}
	}

	public static void main(String[] args) {
		int[] arr = { 100, 80, 60, 70, 60, 75, 85 };

		int[] result = calculateSpan(arr, arr.length);
		System.out.println(Arrays.toString(result));
	}

}
