package hashmap;

import java.util.HashMap;
import java.util.Map;

// Longest Subarray With Equal Number Of 0s 1s And 2s
// https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/hashmap-and-heaps/longest-subarray-with-equal-number-of-0s-1s-and-2s-official/ojquestion
public class LongestSubArraysWithEqual012 {
	public static int solution(int[] arr) {
		// code here
		int ans = 0;
		int z0 = 0;
		int z1 = 0;
		int z2 = 0;
		Map<String, Integer> map = new HashMap<>();
		String key = (z1 - z0) + "#" + (z2 - z1);
		map.put(key, -1);

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 0) {
				z0++;
			} else if (arr[i] == 1) {
				z1++;
			} else {
				z2++;
			}

			key = (z1 - z0) + "#" + (z2 - z1);
			if (map.containsKey(key)) {
				ans = Math.max(ans, i - map.get(key));
			} else {
				map.put(key, i);
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		// 0 1 0 2 0 1 0
		// 3

	}

}
