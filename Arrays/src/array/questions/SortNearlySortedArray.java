package array.questions;

import java.util.PriorityQueue;

/*Sort a nearly sorted (or K sorted) array
https://www.geeksforgeeks.org/nearly-sorted-algorithm/
https://www.codingninjas.com/codestudio/problems/nearly-sorted_982937?leftPanelTab=0
	*/
public class SortNearlySortedArray {
	// elements lie either k positions ahead of it or k positions behind it
	// so to identify the correct element at a place we need to look
	// for the next k elments, just sort that chunk of k elements and find the
	// smallest of
	// them, this hints us to use a minHeap of size k and pop the smallest everytime
	public static int[] nearlySorted(int[] nums, int n, int k) {
		// Write your code here.
		int[] res = new int[n];
		int resIndex = 0;
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			minHeap.add(nums[i]);
			if (minHeap.size() > k) {
				res[resIndex++] = minHeap.poll();
			}
		}
		while (!minHeap.isEmpty()) {
			res[resIndex++] = minHeap.poll();
		}
		return res;
	}

	public static void main(String[] args) {

	}
}
