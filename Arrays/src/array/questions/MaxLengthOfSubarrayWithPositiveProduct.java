package array.questions;

// 1567. Maximum Length of Subarray With Positive Product
// https://leetcode.com/problems/maximum-length-of-subarray-with-positive-product/
// https://www.youtube.com/watch?v=bFer5PdsgpY brute force
// o(n) https://www.youtube.com/watch?v=vmY9ctncXQI md fraz 

public class MaxLengthOfSubarrayWithPositiveProduct {

	// brute force with pruning
	public int getMaxLen(int[] nums) {
		int n = nums.length;
		int max = 0;
		int negatives = 0;
		for (int i = 0; i < n; i++) {
			if (n - i <= max) {
				return max;
			}
			if (nums[i] != 0) {
				negatives = nums[i] < 0 ? 1 : 0;
				if (max == 0) {
					max = nums[i] > 0 ? 1 : 0;// [1,-7] or [-1,5] or [0,8,0,-3,2]
				}

				for (int j = i + 1; j < n; j++) {
					if (nums[j] < 0) {
						negatives++;
					} else if (nums[j] == 0) {
						break;
					}
					if (negatives % 2 == 0) {
						max = Math.max(max, j - i + 1);
					}
				}
			}
		}
		return max;
	}

	// o(n) solution
	// when there are even number of negative elements then the ans is the lenght of
	// the array
	// odd number of negative- 2, -2, 3, 6, 4, -7, -9, 2, 9, 9 we can do two things
	// ignore till -2, and consider the rest, or ignore from -9. that is take from n
	// to first negative, or from start negtaive till the first starting point

	// when there are 0, we will follow step 2, but for each intervals
	// (-2, 2,) 0, (3, -3, -6,) 0, (9, 8, -16)

	public int getMaxLen1(int[] nums) {
		int n = nums.length;
		int res = 0;
		int start = 0;
		int end = 0;
		int max = 0;
		int countNeg = 0;
		int startNeg = -1;
		int endNeg = -1;

		for (int i = 0; i < n;) {
			start = i;

			// if there are 0's in the begining
			while (start < n && nums[start] == 0) {
				start++;
			}
			end = start;

			// increment end pointer till we encounter 0, take the first n=interval and pply
			// logic
			startNeg = -1;
			endNeg = -1;
			countNeg = 0;
			while (end < n && nums[end] != 0) {
				if (nums[end] < 0) {
					countNeg++;
					if (startNeg == -1) {
						startNeg = end;
					}
					endNeg = end;
				}
				end++;
			}
			if (countNeg % 2 == 0) {
				max = Math.max(max, end - start);
			} else {
				max = Math.max(max, Math.max(end - startNeg - 1, endNeg - start));
			}
			i = end + 1;
		}
		return max;
	}

	public static void main(String[] args) {
		/*
		 * Input: nums = [1,-2,-3,4] Output: 4 Explanation: The array nums already has a
		 * positive product of 24.
		 */
	}

}
