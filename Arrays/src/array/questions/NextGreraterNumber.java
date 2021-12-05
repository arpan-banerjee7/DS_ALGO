package array.questions;

import java.util.Arrays;

// 31. Next Permutation

// https://www.geeksforgeeks.org/find-next-greater-number-set-digits/
// https://leetcode.com/problems/next-permutation/

public class NextGreraterNumber {
	// 218765
	public void nextPermutation(int[] nums) {
		int n = nums.length;

		int minIndex = -1;
		int min = 0;

		// traverse from left and find the first small number
		for (int i = n - 1; i > 0; i--) {
			if (nums[i - 1] < nums[i]) {
				minIndex = i - 1;// 1
				min = nums[i - 1];
				break;
			}

		}

		if (minIndex == -1) {
			Arrays.sort(nums);
			return;
		}

		// find an element which is next greater to min, and which lies after min
		int nextMaxIndex = -1;
		for (int i = minIndex + 1; i < n; i++) {
			if (nums[i] > min) {
				nextMaxIndex = i;// 5
			}
		}
		// swap // 258761
		int temp = nums[minIndex];
		nums[minIndex] = nums[nextMaxIndex];
		nums[nextMaxIndex] = temp;

		// sort from minIndex+1 to nextMaxIndex // 251678
		Arrays.sort(nums, minIndex + 1, n);

	}

	public static void main(String[] args) {
		/*
		 * Input: n = "218765" Output: "251678"
		 * 
		 * Input: n = "1234" Output: "1243"
		 * 
		 * Input: n = "4321" Output: "Not Possible"
		 * 
		 * Input: n = "534976" Output: "536479"
		 */

	}

}
