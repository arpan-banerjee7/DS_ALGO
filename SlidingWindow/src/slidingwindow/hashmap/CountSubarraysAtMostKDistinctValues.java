package slidingwindow.hashmap;

import java.util.HashMap;
import java.util.Map;

// https://www.codingninjas.com/codestudio/problems/subarrays-with-at-most-k-distinct-values_1473804?leftPanelTab=0
// https://www.youtube.com/watch?v=shsYUyF7pEs
// https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/hashmap-and-heaps/count-of-substrings-having-at-most-k-unique-characters-official/ojquestion

// TC-- o(n)
// Space -o(n)
public class CountSubarraysKDistinctValues {
	public static int kDistinctSubarrays(int arr[], int n, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		int ans = 0;
		for (int i = 0, j = 0; i < n; i++) {
			map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
			while (map.size() > k) {
				map.put(arr[j], map.get(arr[j]) - 1);
				if (map.get(arr[j]) == 0) {
					map.remove(arr[j]);
				}
				j++;
			}
			ans += (i - j + 1);
		}
		return ans;

	}

	public static void main(String[] args) {
		int[] arr = { 1, 1, 2, 3 };
		int n = arr.length;
		int k = 2;
		System.out.println(kDistinctSubarrays(arr, n, k));

	}

}
