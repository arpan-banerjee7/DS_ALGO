package slidingwindow.fixed;

// https://youtu.be/KtpqeN0Goro
// https://www.geeksforgeeks.org/find-maximum-minimum-sum-subarray-size-k/
// Min Sum- https://www.techiedelight.com/find-minimum-sum-subarray-given-size-k/

// TC-o(n)
// space-o(1)
public class MaxSumSubarrayOfSizeK {

	private static int findMaxSumSubarrayOfSizeK(int[] a, int k) {

		if (k == 0 || a.length == 0)
			return 0;

		int max_sum = Integer.MIN_VALUE;

		int start = 0;
		int window_sum = 0;

		for (int end = 0; end < a.length; end++) {
			window_sum += a[end]; // Add the next element in the window

			if (end - start + 1 == k) {
				max_sum = Math.max(max_sum, window_sum); // Upadate the maximum sum
				window_sum -= a[start]; // substract the element which is moving out of the window
				start++; // slide the start point of the window
			}
		}

		return max_sum;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 4, 2, 10, 23, 3, 1, 0, 20 };
		System.out.println(findMaxSumSubarrayOfSizeK(arr, 4)); // ans=39

	}

}
