package slidingwindow.variable;

import java.util.HashMap;
import java.util.Map;

// https://www.geeksforgeeks.org/longest-sub-array-sum-k/
// https://www.geeksforgeeks.org/smallest-subarray-with-sum-k-from-an-array/

public class LargestSubarrayWithSumK {

	// Works only for positive integers
	public static int lenOfLongSubarr(int A[], int N, int K) {
		// Complete the function
		int start = 0;
		int sum = 0;
		int max = Integer.MIN_VALUE;
		for (int end = 0; end < N; end++) {
			sum += A[end];

			while (sum > K) {
				sum = sum - A[start];
				start++;
			}
			if (sum == K) {
				max = Math.max(max, end - start + 1);
			}

		}
		return max;
	}

	// Works for both positive and -ve numbers
	// Time Complexity: O(n).
	// Auxiliary Space: O(n).
	public static int lenOfLongSubarr1(int A[], int N, int K) {

		// Complete the function
		int sum = 0;
		int max = 0;
		Map<Integer, Integer> seenSum = new HashMap<Integer, Integer>();
		seenSum.put(0, -1);
		for (int i = 0; i < N; i++) {
			sum += A[i];

			if (!seenSum.containsKey(sum)) {
				seenSum.put(sum, i);
			}
			if (seenSum.containsKey(sum - K)) {
				max = Math.max(max, i - seenSum.get(sum - K));
			}
		}
		return max;
	}

	public static void main(String[] args) {
		int[] arr = { 10, 5, 2, 7, 1, 9 };
		int[] arr1 = { -7, -11, -3, -7, 4, 15, -13, 18, -10, -10 };
		int n = arr.length;
		int k = 15;
		int n1 = arr1.length;
		int k1 = 23;
		System.out.println("Length = " + lenOfLongSubarr(arr, n, k)); // Output=4
		System.out.println();
		System.out.println("Length = " + lenOfLongSubarr1(arr1, n1, k1)); // Output=0

	}

}
