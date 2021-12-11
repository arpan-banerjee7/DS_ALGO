package array.questions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*229. Majority Element II  
https://leetcode.com/problems/majority-element-ii/discuss/1614484/Java-or-o(n2)-Brute-Force-or-o(n)-single-pass-HashMap-or-Boyer-Moore-Algo
test case 111
https://leetcode.com/problems/majority-element-ii/
Striver*/

public class MajorityElement2 {
	public List<Integer> majorityElement(int[] nums) {
		int n = nums.length;
		int count = 1;
		List<Integer> res = new ArrayList<>();
		Map<Integer, Integer> freq = new HashMap<>();
		for (int i = 0; i < n; i++) {
			freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
			if (freq.get(nums[i]) > n / 3) {
				res.add(nums[i]);
				freq.put(nums[i], Integer.MIN_VALUE);
			}
		}
		return res;
	}
	public static void main(String[] args) {
		/*
		 * Input: nums = [3,2,3] Output: [3]
		 */
	}
}
