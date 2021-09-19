package problems;
// remember to see the burte force solution

import java.util.HashMap;
import java.util.Map;

// problem reduces to find longest subarray with sum 0 after replacing 0 s with -1

//https://leetcode.com/problems/contiguous-array/
public class ContiguousArray {

	public static int findMaxLength(int[] nums) {
		int n = nums.length;
		// replace 0s with -1s
		for (int i = 0; i < n; i++) {
			if (nums[i] == 0) {
				nums[i] = -1;
			}
		}

		Map<Integer, Integer> seen = new HashMap<>();
		int sum = 0;
		int maxLen = 0;
		seen.put(0, -1);
		for (int i = 0; i < n; i++) {
			sum += nums[i];
			if (!seen.containsKey(sum)) {
				seen.put(sum, i);
			} else {
				maxLen = Math.max(maxLen, i - seen.get(sum));
			}
		}
		return maxLen;
	}

	public static void main(String[] args) {
		int[] nums = { 0, 1 };
		System.out.println(findMaxLength(nums)); // ouput - 2

	}

}
