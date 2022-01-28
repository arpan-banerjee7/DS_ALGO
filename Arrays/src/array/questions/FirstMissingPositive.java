package array.questions;

// https://leetcode.com/problems/first-missing-positive/\

// TC O(3n)=o(n)
// space - o(1)
public class FirstMissingPositive {

	public int firstMissingPositive(int[] nums) {
		int n = nums.length;

		// mark all negative numbers as 0 since it is of no use
		for (int i = 0; i < n; i++) {
			if (nums[i] < 0) {
				nums[i] = 0;
			}
		}

		// now mark the positions of the positive elements in the array
		// 1 maps to 0, 2 maps to 1 etc
		// take abs() since we are modifying the array in the same loop,
		// so there are chances that elemnts will be negative
		for (int i = 0; i < n; i++) {
			int pos = Math.abs(nums[i]) - 1;
			if (pos >= 0 && pos <= n - 1) {
				if (nums[pos] > 0) {
					nums[pos] = -nums[pos];
				} else if (nums[pos] == 0) {
					nums[pos] = -(n + 1);
				} else {
					continue;
				}
			}
		}

		// a negative value in the pos means that is present
		// look for first positive value
		for (int i = 0; i < n; i++) {
			if (nums[i] >= 0) {
				return i + 1;
			}
		}
		return n + 1;
	}

	public static void main(String[] args) {
//		Input: nums = [1,2,0]
//				Output: 3

	}

}
