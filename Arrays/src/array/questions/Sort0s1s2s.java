package array.questions;

// Dutch National Flag Problem
// https://www.geeksforgeeks.org/sort-an-array-of-0s-1s-and-2s/
// https://leetcode.com/problems/sort-colors/

// TC-- o(n)
// Space-- o(1)
public class Sort0s1s2s {
	// 201, 102, 120
	// take two pointers start and end start for 0 and end for 2, iterate and swap
	// accordingly
	private static void sortColors(int[] nums) {
		int start = 0;
		int end = nums.length - 1;
		for (int i = 0; i <= end;) {
			if (nums[i] == 0) {
				int temp = nums[i];
				nums[i] = nums[start];
				nums[start] = temp;
				i++;
				start++;
			} else if (nums[i] == 2) {
				int temp = nums[i];
				nums[i] = nums[end];
				nums[end] = temp;
				end--;
			} else {
				i++;
			}
		}

	}

	public static void main(String[] args) {
		int[] nums = { 2, 0, 2, 1, 1, 0 };
		sortColors(nums);
		for (int i : nums) {
			System.out.print(i + " ");
		}

	}

}
