package array.questions;

import java.util.PriorityQueue;

// Quick Select
// https://leetcode.com/problems/kth-largest-element-in-an-array/
// https://www.geeksforgeeks.org/kth-smallestlargest-element-unsorted-array/

//TC--- Avg- o(n^2)
//      BC-  o(n)

/*
3 ways-

Normal sort and n-k th element nlogn
quick select Tc avg o(n) worst case o(n^2)
// TC - nlogk
// kth largest- maintain a mean heap of size k
Max heap- put all the elements in heap and start popping till we find the kth largets element
// TC O(n + klogn) time as O(n) to build the heap and O(klogn) time to heapify the heap again.
Sort Colors tc- O(N) SPACE O(1)
https://leetcode.com/problems/sort-colors/
*/

public class FindKthLargestElement {
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

	public static void main(String[] args) {
		int[] nums = { 3, 2, 1, 5, 6, 4 };
		int k = 2;
		;
		int n = nums.length;
		System.out.println(quickSelect(nums, 0, n - 1, n - k));

	}

}
