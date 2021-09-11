package slidingwindow.fixed;

import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeMap;

// https://www.geeksforgeeks.org/sliding-window-maximum-maximum-of-all-subarrays-of-size-k/
// https://leetcode.com/problems/sliding-window-maximum/

public class MaximumOfAllSubarraysOfSizeK {

	// Preferred Solution
	// TC- O(n)
	// Space- O(k)
	public static int[] maxSlidingWindow(int[] nums, int k) {

		int[] res = new int[nums.length - k + 1];

		int start = 0;

		Deque<Integer> deque = new LinkedList<>();

		/*
		 * Always store window_end in deque but before doing so, remove all the smaller
		 * element/s than that element from the deque.
		 * 
		 * when we do compare for removal, do peekLast() as last element will be
		 * smallest in queue and then if after removing(pollLast()) it, if there are
		 * other smaller element/s remove them before adding an element in queue.
		 * 
		 * That will place an existing element right after bigger element and so we will
		 * have natural asc order in deque.
		 * 
		 */
		for (int end = 0; end < nums.length; end++) {

			while (!deque.isEmpty() && deque.peekLast() < nums[end]) {
				deque.pollLast();
			}

			deque.add(nums[end]);

			if (end - start + 1 == k) {
				res[start] = deque.peekFirst();

				// before sliding window

				// 1. if the element going out is at the peek of deque, remove it
				if (deque.peekFirst() == nums[start]) {
					deque.pollFirst();
				}
				start++;
			}
		}

		return res;
	}

	// TC- O(N * Log k).
	// Space- o(k)
	public static int[] maxSlidingWindow2(int[] nums, int k) {
		if (nums == null || nums.length == 0)
			return new int[] {};

		int idx = 0;
		int window_start = 0;
		TreeMap<Integer, Integer> tree = new TreeMap<Integer, Integer>();
		int n = nums.length;

		// the maxWindow to save the largest in sliding window with width k
		int[] maxWindow = new int[n - k + 1];

		for (int window_end = 0; window_end < nums.length; window_end++) {
			tree.put(nums[window_end], window_end);

			if (window_end - window_start + 1 == k) {

				// get the last (largest) key of the tree and put it into the max array
				maxWindow[idx++] = tree.lastKey();

				if (tree.get(nums[window_start]) == window_start) {
					tree.remove(nums[window_start]);
				}
				window_start++; // Slide the window ahead
			}
		}
		return maxWindow;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 3, -1, -3, 5, 3, 6, 7 };
		int k = 3;
		int[] res = maxSlidingWindow(nums, k); // Output: [3,3,5,5,6,7]
		for (int i : res) {
			System.out.print(i + " ");
		}
	}

}
