package hashmap;

import java.util.HashMap;
import java.util.Map;

public class FindPairGivenDifference {
	public boolean findPair(int arr[], int size, int n) {
		// code here

		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			if ((map.containsKey(arr[i] - n)) || (map.containsKey(n + arr[i]))) {
				return true;
			}
			map.put(arr[i], 1);
		}
		return false;
	}

	public static void main(String[] args) {
		/*
		 * Input: L = 6, N = 78 arr[] = {5, 20, 3, 2, 5, 80} Output: 1 Explanation: (2,
		 * 80) have difference of 78.
		 */
	}

}
