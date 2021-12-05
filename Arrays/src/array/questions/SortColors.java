package array.questions;

// 75. Sort Colors
// https://www.youtube.com/watch?v=sEQk8xgjx64

public class SortColors {
	public void sortColors(int[] nums) {
		int n = nums.length;

		int start = 0;
		int end = n - 1;
		int i = 0;
		while (i <= end) {
			// swap i and end
			if (nums[i] == 2) {
				int temp = nums[end];
				nums[end] = nums[i];
				nums[i] = temp;
				end--;
				// swap i and start
			} else if (nums[i] == 0) {
				int temp = nums[start];
				nums[start] = nums[i];
				nums[i] = temp;
				start++;
				i++;
			} else {
				i++;
			}
		}
	}

	public static void main(String[] args) {
		/*
		 * Input: nums = [2,0,2,1,1,0] Output: [0,0,1,1,2,2]
		 */
	}

}
