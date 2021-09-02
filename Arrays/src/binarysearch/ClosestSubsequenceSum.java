package binarysearch;

import java.util.Arrays;

// https://leetcode.com/problems/closest-subsequence-sum/
// https://www.geeksforgeeks.org/meet-in-the-middle/

// TC-- (for actual search) O(N/2*2^N/2)+ (for sorting)O(N/2*2^N/2)+ (for generating subsets)O(N/2*2^N/2)~o(n*2^(n/2))
public class ClosestSubsequenceSum {

	private static void createSet(int[] set, int[] a, int n1) {
		int sum, ind = 0, n = a.length;
		for (int i = 0; i < n1; i++) {
			sum = 0;
			for (int j = 0; j < n; j++) {
				if ((i & (1 << j)) > 0)
					sum += a[j];
			}
			set[ind++] = sum;
		}
	}

	public static int minAbsDifference(int[] nums, int goal) {
		int n = nums.length;
		int[] set1 = new int[1 << n / 2];
		int[] set2 = new int[1 << (n - n / 2)];
		// O(N/2*2^N/2)
		createSet(set1, Arrays.copyOfRange(nums, 0, n / 2), 1 << n / 2);
		// O(N/2*2^N/2)
		createSet(set2, Arrays.copyOfRange(nums, n / 2, n), 1 << (n - n / 2));
		// O(2^N/2 * log2^N/2) => N/2*2^N/2
		Arrays.sort(set2);
		int min = Integer.MAX_VALUE;
		for (int i : set1) {
			// log2^N/2 => N/2
			int posi = Arrays.binarySearch(set2, goal - i);
			if (posi >= 0)
				return 0;
			int pos = -1 * (posi + 1);
			int low = pos - 1;
			if (low >= 0)
				min = Math.min(min, Math.abs(goal - (i + set2[low])));
			if (pos != set2.length)
				min = Math.min(min, Math.abs(goal - (i + set2[pos])));
		}
		return min;
	}

	public static void main(String[] args) {
		int[] arr = { 5, -7, 3, 5 };
		int[] arr1 = { 7, -9, 15, -2 };
		int goal1 = -5;
		int goal = 6;
		System.out.println(minAbsDifference(arr1, goal1));

	}

}
