package array.questions;

import java.util.HashMap;
import java.util.Map;

// Equal 0, 1 and 2
// https://practice.geeksforgeeks.org/problems/equal-0-1-and-23208/1#

public class CountSubArraysWithEqual012 {
	long getSubstringWithEqual012(String str) {
		// code here
		long count = 0;
		int z0 = 0;
		int z1 = 0;
		int z2 = 0;
		Map<String, Integer> map = new HashMap<>();
		String key = (z1 - z0) + "#" + (z2 - z1);
		map.put(key, 1);

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '0') {
				z0++;
			} else if (str.charAt(i) == '1') {
				z1++;
			} else {
				z2++;
			}

			key = (z1 - z0) + "#" + (z2 - z1);
			if (map.containsKey(key)) {
				count += map.get(key);
				map.put(key, map.get(key) + 1);
			} else {
				map.put(key, 1);
			}
		}
		return count;

	}

	public static void main(String[] args) {
		/*
		 * Input: str = “0102010” Output: 2 Explanation: Substring str[2, 4] = “102” and
		 * substring str[4, 6] = “201” has equal number of 0, 1 and 2
		 */
	}

}
