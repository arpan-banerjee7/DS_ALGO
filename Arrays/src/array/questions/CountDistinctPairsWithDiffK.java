package array.questions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//https://www.geeksforgeeks.org/count-pairs-difference-equal-k/
//https://www.hackerrank.com/challenges/pairs/problem
/*	sort, removie duplicates, binary search arr[i]+k
	set, again iterate find arr[i]+k
	*/

public class CountDistinctPairsWithDiffK {
	public static int pairs(int k, List<Integer> arr) {
		// Write your code here
		int count = 0;
		Set<Integer> seen = new HashSet<>(arr);
		for (int i : arr) {
			if (seen.contains(i + k)) {
				count++;
			}
		}
		return count;

	}

	static int binarySearch(int arr[], int low, int high, int x) {
		if (high >= low) {
			int mid = low + (high - low) / 2;
			if (x == arr[mid])
				return mid;
			if (x > arr[mid])
				return binarySearch(arr, (mid + 1), high, x);
			else
				return binarySearch(arr, low, (mid - 1), x);
		}
		return -1;
	}

	// Returns count of pairs with
	// difference k in arr[] of size n.
	static int countPairsWithDiffK(int arr[], int n, int k) {
		int count = 0, i;

		// Sort array elements
		Arrays.sort(arr);

		// code to remove duplicates from arr[]

		// Pick a first element point
		for (i = 0; i < n - 1; i++)
			if (binarySearch(arr, i + 1, n - 1, arr[i] + k) != -1)
				count++;

		return count;
	}

	public static void main(String[] args) {
		int arr[] = { 1, 5, 3, 4, 2 };
		int n = arr.length;
		int k = 3;
		System.out.println("Count of pairs with given diff is " + countPairsWithDiffK(arr, n, k));

	}

}
