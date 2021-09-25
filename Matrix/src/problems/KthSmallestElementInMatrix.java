package problems;

import java.util.PriorityQueue;

// https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/submissions/
// https://www.geeksforgeeks.org/kth-smallest-element-in-a-row-wise-and-column-wise-sorted-2d-array-set-1/

public class KthSmallestElementInMatrix {

	public static int kthSmallest(int[][] matrix, int k) {
		int m = matrix.length, n = matrix[0].length; // For general, the matrix need not be a square
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
		for (int r = 0; r < m; ++r) {
			for (int c = 0; c < n; ++c) {
				maxHeap.offer(matrix[r][c]);
				if (maxHeap.size() > k)
					maxHeap.poll();
			}
		}
		return maxHeap.poll();
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 5, 9 }, { 10, 11, 13 }, { 12, 13, 15 } };
		int k = 8;
		int ans = kthSmallest(matrix, k);
		System.out.println(ans);

	}

}
