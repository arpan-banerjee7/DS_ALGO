package assignments;
// Leetcode 122. Best Time to Buy and Sell Stock II

// https://www.youtube.com/watch?v=K8iHi8AW1ls
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/submissions/
//  https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/discuss/1569093/C%2B%2BPython-Clean-and-Simple-Solution-w-Detailed-Explanation-and-Images-or-Buy-Low-Sell-High

public class BestTimeBuySellStockII {
	// using valley-peak approach
	// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/discuss/1569093/C%2B%2BPython-Clean-and-Simple-Solution-w-Detailed-Explanation-and-Images-or-Buy-Low-Sell-High
	public static int maxProfit(int[] prices) {

		int n = prices.length;
		int valley = 0;
		int peak = 0;

		int profit = 0;

		for (int i = 0; i < n; i++) {
			// find valley(local minima)
			// check down -- down -- down once we go up,earlier point is the
			// first valley
			while ((i + 1 < n) && (prices[i + 1]) <= prices[i]) {
				i++; // keep going down
			}

			if (i + 1 == n)
				break;

			valley = prices[i];
			i++; // start checking from the next point for peak

			// find peak go up up up--first point when we go down
			// earlier point was peak
			while ((i < n) && (prices[i] >= prices[i - 1])) {
				i++;
			}
			// if we found a valley there is bound to be a peak
			// so no need to check for boundary of i
			peak = prices[--i];
			profit = profit + (peak - valley);

		}
		return profit;
	}

	public int maxProfit1(int[] prices) {
		int n = prices.length;

		int diff = 0;

		for (int i = 1; i < n; i++) {
			if (prices[i] > prices[i - 1]) {
				diff += prices[i] - prices[i - 1];
			}
		}
		return diff;
	}

	public static void main(String[] args) {
		int[] prices = { 7, 1, 5, 3, 6, 4 };
		System.out.println(maxProfit(prices));// Output: 7

	}

}
