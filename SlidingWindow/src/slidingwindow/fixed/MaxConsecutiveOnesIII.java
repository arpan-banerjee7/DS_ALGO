package slidingwindow.fixed;

// https://leetcode.com/problems/max-consecutive-ones-iii/

public class MaxConsecutiveOnesIII {

	// fixed sliding window
	public static int longestOnes(int[] nums, int k) {
		int start = 0;
		int max = Integer.MIN_VALUE;
		for (int end = 0; end < nums.length; end++) {
			if (nums[end] == 0) {
				k--;
			}

			while (k < 0) {
				if (nums[start] == 0) {
					k++;
				}
				start++;
			}

			max = Math.max(max, end - start + 1);
		}

		return max;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 };
		int k = 2;
		System.out.println(longestOnes(nums, k));// Output: 6

	}

}
