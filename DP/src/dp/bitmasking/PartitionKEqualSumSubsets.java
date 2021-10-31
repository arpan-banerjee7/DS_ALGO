package dp.bitmasking;
// https://leetcode.com/problems/partition-to-k-equal-sum-subsets/

// TC- o(n*2^n)
public class PartitionKEqualSumSubsets {
	public static boolean canPartitionKSubsets(int[] nums, int k) {
		int n = nums.length;
		int max = Integer.MIN_VALUE;

		// find sum of the array, to find the target sum(i.e what will be the sum of
		// each partition)
		// and also check for invalid conditions
		int sum = 0;
		for (int i : nums) {
			sum += i;
			max = Math.max(max, i);
		}
		// invalid cases
		if (sum % k != 0 || max > sum / k) {
			return false;
		}

		int target = sum / k;

		int[] dp = new int[1 << n];
		for (int i = 0; i < (1 << n); i++) {
			dp[i] = -1;
		}
		dp[0] = 0;
		// Try all combinations
		for (int mask = 0; mask < (1 << n); mask++) {
			if (dp[mask] == -1) {
				continue;
			}
			for (int j = 0; j < n; j++) {
				if (((mask) & (1 << j)) == 0 && dp[mask] + nums[j] <= target) {
					dp[mask | (1 << j)] = (dp[mask] + nums[j]) % target;
				}
			}
		}
		return dp[(1 << n) - 1] == 0 ? true : false;
	}

	public static void main(String[] args) {
		int[] nums = { 4, 3, 2, 3, 5, 2, 1 };
		int k = 4;
		System.out.println(canPartitionKSubsets(nums, k));

		/*
		 * Output: true Explanation: It's possible to divide it into 4 subsets (5), (1,
		 * 4), (2,3), (2,3) with equal sums.
		 */
	}

}
