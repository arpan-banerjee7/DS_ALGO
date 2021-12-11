package array.questions;

import java.util.HashMap;
import java.util.Map;

/*454. 4Sum II  
TC- o(n^2)
https://leetcode.com/problems/4sum-ii/
https://www.youtube.com/watch?v=XHlA0KdvQBc
*/	

public class FourSum2 {

	// Optimization using Hashmap
	public int fourSumCount1(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
		int n = nums1.length;
		int count = 0;
		// taking sum from first two arrays in hashmap
		Map<Integer, Integer> seen = new HashMap<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				seen.put(nums1[i] + nums2[j], seen.getOrDefault(nums1[i] + nums2[j], 0) + 1);
			}
		}

		// calculating the sum from other halfs
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				count += seen.getOrDefault(-(nums3[i] + nums4[j]), 0);
			}
		}
		return count;
	}

	// TLE
	public int fourSumCount2(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
		int count = 0;
		int n = nums1.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					for (int l = 0; l < n; l++) {
						if (nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0) {
							count++;
						}
					}
				}
			}
		}
		return count;
	}

	public static void main(String[] args) {
		/*
		 * Input: nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2] Output:
		 * 2 Explanation: The two tuples are: 1. (0, 0, 0, 1) -> nums1[0] + nums2[0] +
		 * nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0 2. (1, 1, 0, 0) -> nums1[1] +
		 * nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
		 */
	}

}
