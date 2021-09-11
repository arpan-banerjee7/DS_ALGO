package slidingwindow.hashmap;

import java.util.HashMap;
import java.util.Map;

// https://www.geeksforgeeks.org/find-the-largest-subarray-with-0-sum/
// TC-- o(n)
// Space -o(n)
public class LargestSubarraySum0 {

	private static int findLongestSubarray(int[] arr) {
		int count = 0;
		int max_count = 0;
		int sum = 0;
		Map<Integer, Integer> seen = new HashMap<Integer, Integer>();
		seen.put(0, -1);
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			if (seen.containsKey(sum)) {
				count = (i - seen.get(sum));
				max_count = Math.max(count, max_count);

			} else {
				seen.put(sum, i);
			}
		}
		return max_count;
	}

	public static void main(String[] args) {
		int arr[] = { 15, -2, 2, -8, 1, 7, 10, 23 };
		System.out.println(findLongestSubarray(arr));

	}

}
