package assignments;
// Leetcode 901 https://leetcode.com/problems/online-stock-span/

import java.util.Stack;

// https://www.youtube.com/watch?v=lGWLBgwd-cw
// Monotonous decreasing stack to find previous greater element

public class OnlineStockSpan {
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

	/**
	 * Your StockSpanner object will be instantiated and called as such:
	 * StockSpanner obj = new StockSpanner(); int param_1 = obj.next(price);
	 */
}
