package array.questions;

// 167. Two Sum II - Input Array Is Sorted
// https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
	
public class TwoSum2 {
	public int[] twoSum(int[] numbers, int target) {
		int start = 0;
		int end = numbers.length - 1;

		while (start < end) {
			if (numbers[start] + numbers[end] == target) {
				return new int[] { start + 1, end + 1 };
			} else if (numbers[start] + numbers[end] < target) {
				start++;
			} else {
				end--;
			}
		}
		return new int[0];
	}

	public static void main(String[] args) {
		/*
		 * Input: numbers = [2,7,11,15], target = 9 Output: [1,2] Explanation: The sum
		 * of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].
		 */

	}

}
