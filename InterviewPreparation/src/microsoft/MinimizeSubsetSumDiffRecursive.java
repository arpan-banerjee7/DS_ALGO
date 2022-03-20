package microsoft;

// 1049. Last Stone Weight II
// https://leetcode.com/problems/last-stone-weight-ii/

public class MinimizeSubsetSumDiffRecursive {
	class Solution {
		Integer[][] dp;

		private int findLowerBoundTargetSum(int[] stones, int target, int start, int currSum) {
			if (start == stones.length) {
				return currSum;
			}
			if (dp[start][currSum] != null) {
				return dp[start][currSum];
			}

			if (stones[start] + currSum > target) {
				return dp[start][currSum] = findLowerBoundTargetSum(stones, target, start + 1, currSum);
			} else {
				return dp[start][currSum] = Math.max(
						findLowerBoundTargetSum(stones, target, start + 1, currSum + stones[start]),
						findLowerBoundTargetSum(stones, target, start + 1, currSum));
			}

		}

		public int lastStoneWeightII(int[] stones) {
			int n = stones.length;
			int sum = 0;
			for (int i : stones) {
				sum += i;
			}
			// since we have to minimize the diff bw two subset sums
			// ideal subset sums=sum/2, we wll try to find a sum from the subsets which is
			// as close to sum/2 as possible=lower bound of sum/2

			// then S2-S1--> we need to minimize, S1+S2=sum, S2=sum-S1 -> minimize Sum-2S1
			int target = (sum >> 1);
			dp = new Integer[n][(sum >> 1) + 1];
			int maxSum = findLowerBoundTargetSum(stones, target, 0, 0);
			return sum - 2 * maxSum;
		}
	}

	public static void main(String[] args) {
//		Input: stones = [2,7,4,1,8,1]
//				Output: 1

	}

}
