package dp.knapsack.problems.unbounded;
// Max number of ways

import java.util.Arrays;

// https://leetcode.com/problems/coin-change-2/
public class CoinChangeProb2 {

	private static int coinChange(int n, int sum, int[] coins, int[][] dp, int count) {
		if (sum == 0) {
			count++;
			return count;
		}
		if (n == 0) {
			return 0;
		}

		if (dp[n][sum] != -1) {
			return dp[n][sum];
		}

		if (coins[n - 1] > sum) {
			return dp[n][sum] = coinChange(n - 1, sum, coins, dp, count);
		} else {
			return dp[n][sum] = coinChange(n - 1, sum, coins, dp, count)
					+ coinChange(n, sum - coins[n - 1], coins, dp, count);
		}
	}
	
	 public int changeTab(int amount, int[] coins) {
	        int n=coins.length;
	        int[][] dp=new int[n+1][amount+1];
	        // when sum is 0, there is always one way
	        for(int i=0;i<=n;i++){
	            dp[i][0]=1;
	        }
	        
	        for(int i=1;i<n+1;i++){
	            for(int j=1;j<amount+1;j++){
	                if(coins[i-1]>j){
	                    dp[i][j]=dp[i-1][j];
	                }else{
	                    dp[i][j]=dp[i-1][j]+dp[i][j-coins[i-1]];
	                }
	            }
	        }
	        return dp[n][amount];
	    }

	public static int change(int amount, int[] coins) {
		int n = coins.length;
		int[][] dp = new int[n + 1][amount + 1];
		for (int i = 0; i <= n; i++) {
			Arrays.fill(dp[i], -1);
		}
		return coinChange(n, amount, coins, dp, 0);
	}

	public static void main(String[] args) {
		int amount = 5;
		int[] coins = { 1, 2, 5 };
		System.out.println(change(amount, coins));
		/*
		 * Output: 4 Explanation: there are four ways to make up the amount: 5=5 5=2+2+1
		 * 5=2+1+1+1 5=1+1+1+1+1
		 */
	}

}
