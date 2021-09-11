package slidingwindow.variable;

// https://www.geeksforgeeks.org/longest-subarray-sum-elements-atmost-k/

//TC-- o(n)
//Space -o(1)
public class LargestSubarraySumAtmostK {

	// function to find the length of largest
	// subarray having sum atmost k.
	public static int atMostSum(int arr[], int n, int k) {
		int sum = 0;
		int cnt = 0, maxcnt = 0;

		for (int i = 0; i < n; i++) {

			// If adding current element doesn't
			// cross limit add it to current window
			if ((sum + arr[i]) <= k) {
				sum += arr[i];
				cnt++;
			}

			// Else, remove first element of current
			// window.
			else if (sum != 0) {
				sum = sum - arr[i - cnt] + arr[i];
			}

			// keep track of max length.
			maxcnt = Math.max(cnt, maxcnt);
		}
		return maxcnt;
	}

	/* Driver program to test above function */
	public static void main(String[] args) {
		int arr[] = { 1, 2, 1, 0, 1, 1, 0 };
		int n = arr.length;
		int k = 4;

		System.out.print(atMostSum(arr, n, k));

	}

}
