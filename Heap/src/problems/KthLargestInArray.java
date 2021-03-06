package problems;

import java.util.PriorityQueue;

// https://leetcode.com/problems/kth-largest-element-in-an-array/

public class KthLargestInArray {

	// TC - nlogk
	// kth largest- maintain a mean heap of size k
	public static int findKthLargest(int[] nums, int k) {
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		for (int n : nums) {
			minHeap.add(n);
			if (!minHeap.isEmpty() && minHeap.size() > k) {
				minHeap.poll();
			}
		}
		return minHeap.poll();
	}

	// TC o(nlogn) - doubt
	public static int findKthLargest1(int[] nums, int k) {
		int n = nums.length;
		int ans = 0;
		int j = 0;

		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
		for (int i = 0; i < n; i++) {
			pq.add(nums[i]);
		}
		while (j < k) {
			if (!pq.isEmpty()) {
				ans = pq.poll();
				j++;
			}
		}
		return ans;
	}

	// Quick select - Avg- o(n) discarding one half so can neglect the log n factor
	// Worst case- o(n^2)
	private static int findPivot(int[] nums, int low, int high) {
		int i = low - 1;
		int pivot = nums[high];
		for (int j = low; j < high; j++) {
			if (nums[j] <= pivot) {
				i++;
				int temp = nums[i];
				nums[i] = nums[j];
				nums[j] = temp;
			}
		}

		int temp = nums[high];
		nums[high] = nums[i + 1];
		nums[i + 1] = temp;
		return i + 1;
	}

	private static int quickSelect(int[] nums, int low, int high, int k) {
		int pivot = findPivot(nums, low, high);
		if (pivot == k) {
			return nums[pivot];
		} else if (pivot < k) {
			return quickSelect(nums, pivot + 1, high, k);
		} else {
			return quickSelect(nums, low, pivot - 1, k);
		}

	}

	// quick select
	public static int findKthLargest2(int[] nums, int k) {
		int n = nums.length;
		return quickSelect(nums, 0, n - 1, n - k);
	}

	public static void main(String[] args) {
		int[] arr = { 3, 2, 1, 5, 6, 4 };
		System.out.println(findKthLargest2(arr, 3));

	}

}
