package array.questions;

import java.util.HashMap;
import java.util.Map;


/*1. Two Sum
https://leetcode.com/problems/two-sum/
*/

public class TwoSum {
	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> seen = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			if (seen.containsKey(target - nums[i])) {
				return new int[] { seen.get(target - nums[i]), i };
			} else {
				seen.put(nums[i], i);
			}
		}
		return new int[0];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
