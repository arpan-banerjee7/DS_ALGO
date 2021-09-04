package slidingwindow.normal;

// https://www.geeksforgeeks.org/minimum-length-subarray-sum-greater-given-value/
// For negative numbers- https://www.geeksforgeeks.org/find-subarray-with-given-sum-with-negatives-allowed-in-constant-space/

// TC-- o(n)
// Space -o(1)
public class SmallestSubarrayWithSumGreaterK {
	// Returns length of smallest subarray with sum greater
	// than x. If there is no subarray with given sum, then
	// returns n+1
	static int smallestSubWithSum(int arr[], int n, int x) {
		// Initialize current sum and minimum length
		int curr_sum = 0, min_len = n + 1;

		// Initialize starting and ending indexes
		int start = 0, end = 0;
		while (end < n) {
			// Keep adding array elements while current sum
			// is smaller than or equal to x
			while (curr_sum <= x && end < n)
				curr_sum += arr[end++];

			// If current sum becomes greater than x.
			while (curr_sum > x && start < n) {
				// Update minimum length if needed
				if (end - start < min_len)
					min_len = end - start;

				// remove starting elements
				curr_sum -= arr[start++];
			}
		}
		return min_len;
	}

	// Driver program to test above functions
	public static void main(String[] args) {
		int arr1[] = { 1, 1, 47, 2, 3 };
		int x = 46;
		int n1 = arr1.length;
		System.out.println(smallestSubWithSum(arr1, n1, x));
	}
}