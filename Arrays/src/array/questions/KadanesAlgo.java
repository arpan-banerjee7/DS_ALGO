package array.questions;

// 53. Maximum Subarray
// https://leetcode.com/problems/maximum-subarray/

//kadane's algo TC -o(n)
public class KadanesAlgo {
	public int maxSubArray(int[] nums) {
		int currSum = 0;
		int maxSum = nums[0];

		for (int i = 0; i < nums.length; i++) {
			currSum += nums[i];
			maxSum = Math.max(maxSum, currSum);
			if (currSum < 0) {
				currSum = 0;
			}

		}
		return maxSum;
	}

	public static void main(String[] args) {
		/*
		 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4] Output: 6 Explanation: [4,-1,2,1] has
		 * the largest sum = 6.
		 */
	}

}
