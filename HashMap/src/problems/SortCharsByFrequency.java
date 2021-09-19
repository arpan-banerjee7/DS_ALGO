package problems;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// https://leetcode.com/problems/sort-characters-by-frequency/
// Iterating a priority queue using foreach is not the same as using queue.poll()
// https://leetcode.com/problems/sort-characters-by-frequency/discuss/93420/Java-O(n)-Bucket-Sort-Solution-O(nlogm)-PriorityQueue-Solution-easy-to-understand

public class SortCharsByFrequency {
	// TC(o(n)+o(mlogm)+o(mlogm))~o(mlogm)--??
	public static String frequencySort(String s) {
		int n = s.length();
		StringBuilder sb = new StringBuilder(n);

		// create a frquency hashmap
		// o(n)
		Map<Character, Integer> seen = new HashMap<>();
		for (int i = 0; i < n; i++) {
			seen.put(s.charAt(i), seen.getOrDefault(s.charAt(i), 0) + 1);
		}

		// sort based on frequency
		// o(mlogm)
		// m<<n
		PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
		for (Map.Entry<Character, Integer> entry : seen.entrySet()) {
			pq.add(entry);
		}

		// o(mlogm)
		while (!pq.isEmpty()) {
			Map.Entry<Character, Integer> entry = pq.poll();
			int value = entry.getValue();
			char key = entry.getKey();
			for (int i = 0; i < value; i++) {
				sb.append(key);
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String s = "tree";
		System.out.println(frequencySort(s)); // output - eert

	}

}
