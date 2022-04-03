package dp.problems;

// 1155. Number of Dice Rolls With Target Sum
// https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/\

//TC- o(n*target*f)  here n is number of dices,f=6;
//space- o(n*target)

//recursive TC- o(f^n)
public class NumberOfDiceRollsWithTargetSum {
	private int totalWays(int dices, int target, int faces, Integer[][] dp) {
		if (dices == 0 && target == 0)
			return 1;

		if (dices == 0 || target < 0)
			return 0;

		if (dp[dices][target] != null)
			return dp[dices][target];

		// (a+b)%mod=(a%mod+b%mod)%mod;
		int ways = 0;
		int mod = 1000000007;
		for (int i = 1; i <= faces; i++) {
			int tempAns = totalWays(dices - 1, target - i, faces, dp) % mod;
			ways = ways % mod;
			ways = (ways + tempAns) % mod;
		}
		return dp[dices][target] = ways;
	}

	public int numRollsToTarget(int n, int k, int target) {
		Integer[][] dp = new Integer[n + 1][target + 1];
		return totalWays(n, target, k, dp);
	}

	public static void main(String[] args) {
		/*
		 * Input: n = 2, k = 6, target = 7 Output: 6 Explanation: You throw two dice,
		 * each with 6 faces. There are 6 ways to get a sum of 7: 1+6, 2+5, 3+4, 4+3,
		 * 5+2, 6+1.
		 */
	}

}
