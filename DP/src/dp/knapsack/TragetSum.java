package dp.knapsack;
// https://leetcode.com/problems/target-sum/

// exactly same as count number of subsets with given diff
public class TragetSum {

	public static int findTargetSumWays(int[] nums, int target) {
		int n = nums.length;
		int arrSum = 0;

		for (int i = 0; i < n; i++) {
			arrSum += nums[i];
		}

		// for test case
		// [100] -200
		if (Math.abs(target) > arrSum)
			return 0;

		if ((target + arrSum) % 2 != 0)
			return 0;

		int sum = (arrSum + target) / 2;
		int[][] dp = new int[n + 1][sum + 1];

		// init
		for (int i = 0; i <= n; i++) {
			dp[i][0] = 1;
		}
		// loop
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= sum; j++) {
				if (nums[i - 1] <= j) {
					dp[i][j] = dp[i - 1][j - nums[i - 1]] + dp[i - 1][j];
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}

		return (dp[n][sum]);

	}

	public static void main(String[] args) {
		int[] nums = {1,1,1,1,1};
		int target = 3;
		System.out.println(findTargetSumWays(nums, target)); // Output: 5

	}

}
