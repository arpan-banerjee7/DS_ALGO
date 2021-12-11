package array.questions;

// 121. Best Time to Buy and Sell Stock
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/

public class BestTimeToBuySellStock {
	public int maxProfit(int[] prices) {
		int min = prices[0];
		int maxProfit = Integer.MIN_VALUE;

		for (int i = 0; i < prices.length; i++) {
			if (prices[i] < min) {
				min = prices[i];
			} else {
				maxProfit = Math.max(maxProfit, prices[i] - min);
			}

		}
		return maxProfit;
	}

	public static void main(String[] args) {
		/*
		 * Input: prices = [7,1,5,3,6,4] Output: 5 Explanation: Buy on day 2 (price = 1)
		 * and sell on day 5 (price = 6), profit = 6-1 = 5. Note that buying on day 2
		 * and selling on day 1 is not allowed because you must buy before you sell.
		 */

	}

}
