package assignments;

// https://www.youtube.com/watch?v=K8iHi8AW1ls
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/submissions/
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/discuss/1569093/C%2B%2BPython-Clean-and-Simple-Solution-w-Detailed-Explanation-and-Images-or-Buy-Low-Sell-High
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/   Leetcode 121
public class BestTimeBuySellStock {
	public static int maxProfit(int[] prices) {

		int min = prices[0];
		int maxProfit = 0;

		for (int i = 1; i < prices.length; i++) {
			if (prices[i] <= min) {
				min = prices[i];
				continue;
			}
			maxProfit = Math.max(maxProfit, (prices[i] - min));
		}
		return maxProfit;
	}

	public static void main(String[] args) {
		int[] prices = { 7, 1, 5, 3, 6, 4 };
		System.out.println(maxProfit(prices));// Output: 5
	}

}
