package problems;

// 70. Climbing Stairs
// https://leetcode.com/problems/climbing-stairs/

public class ClimbStairs {

	// if we can figure out the number of ways to reach til (n-1) stairs and (n-2)
	// staires, then
	// total number of ways would be toal ways (n-1)+ total ways(n-2), since to
	// reach to n from (n-1)
	// there is only one way that is 1 step, and to reach n from (n-2) there are two
	// ways 1 using a
	// single step of 2, or two 1 steps

	// similar to fibonacci series
	class Solution {
		private int totalWays(int n, Integer[] dp) {
			if (n <= 2) {
				return n;
			}
			if (dp[n] != null) {
				return dp[n];
			}
			return dp[n] = totalWays(n - 1, dp) + totalWays(n - 2, dp);
		}

		public int climbStairs(int n) {
			Integer[] dp = new Integer[n + 1];
			return totalWays(n, dp);
		}
	}

	class Solution1 {
		Integer[] dp;

		private int getWays(int n, int m) {

			if (n < 0)
				return 0;
			if (n == 1 || n == 0)
				return 1;

			if (dp[n] != null) {
				return dp[n];
			}

			int totalWays = 0;
			for (int i = 1; i <= m; i++) {
				totalWays += getWays(n - i, m);
			}

			return dp[n] = totalWays;
		}

		public int climbStairs(int n) {
			int m = 2;
			dp = new Integer[n + 1];
			return getWays(n, m);
		}
	}
}
