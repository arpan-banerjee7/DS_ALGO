package slidingwindow.fixed;

import java.util.LinkedList;
import java.util.Queue;

// https://www.geeksforgeeks.org/first-negative-integer-every-window-size-k/

public class FirstNegativeNumber {

	private static int[] findFirstNegativeNumberInSubarrayOfSizeK(int[] a, int k) {
		int n = a.length;
		int[] ans = new int[n - k + 1];
		int idx = 0;

		Queue<Integer> q = new LinkedList<>();

		int start = 0;
		for (int end = 0; end < n; end++) {
			if (a[end] < 0) {
				q.add(a[end]);
			}

			if (end - start + 1 == k) { // Calculate result and Slide the window
				if (q.isEmpty()) {
					ans[idx++] = 0;
				} else {
					int num = q.peek();
					ans[idx++] = num;

					// Remove a[windowStart] from the queue since we need to slide the window now.
					// But only if it was added to the queue previously
					if (num == a[start]) {
						q.remove();
					}
				}
				start++; // Slide the window ahead
			}
		}

		return ans;
	}

	public static void main(String[] args) {
		int arr[] = { 12, -1, -7, 8, -15, 30, 16, 28 };
		int k = 3;
		int[] res = findFirstNegativeNumberInSubarrayOfSizeK(arr, k);
		for (int i : res) {
			System.out.print(i + " "); // Output- -1 -1 -7 -15 -15 0
		}

	}

}
