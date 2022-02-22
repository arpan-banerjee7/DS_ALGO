package array.questions;

// 152. Maximum Product Subarray
// https://leetcode.com/problems/maximum-product-subarray/
// https://www.youtube.com/watch?v=lXVy6YWFcRM Neetcode
// test case dry run- [-2,0,-1,5,6]

public class MaximumProductSubarray {

	// brute force TLE
	public int maxProduct(int[] nums) {
		int n = nums.length;
		int maxProduct = Integer.MIN_VALUE;
		int currProduct = 1;

		for (int i = 0; i < n; i++) {
			currProduct = nums[i];
			maxProduct = Math.max(maxProduct, currProduct);
			for (int j = i + 1; j < n; j++) {
				currProduct *= nums[j];
				maxProduct = Math.max(maxProduct, currProduct);
				if (currProduct == 0)
					break;
			}
		}
		return maxProduct;
	}

	// o(n) solution, keep track of max and min at each step- trick to remember
	// return the max
	// test case dry run- [-2,0,-1,5,6]
	public int maxProduct1(int[] nums) {
		int res = Integer.MIN_VALUE;
		int currMax = 1;
		int currMin = 1;

		for (int num : nums) {
			int temp = currMax;
			currMax = Math.max(Math.max(num * currMax, num * currMin), num);
			currMin = Math.min(Math.min(num * temp, num * currMin), num);
			res = Math.max(res, currMax);
		}
		return res;
	}

	public static void main(String[] args) {
		/*
		 * Input: nums = [-2,0,-1] Output: 0 Explanation: The result cannot be 2,
		 * because [-2,-1] is not a subarray.
		 */
	}

}
