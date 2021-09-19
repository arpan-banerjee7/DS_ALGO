package problems;

import java.util.Stack;

// https://leetcode.com/problems/maximal-rectangle/description/
// https://youtu.be/J2X70jj_I1o

public class MaximalRectangle {
	public static int largestRectangleArea(int[] heights) {
		// your code here
		// nearest smaller to left
		int n = heights.length;
		int max = Integer.MIN_VALUE;
		Stack<Integer> left = new Stack<>();
		int[] l = new int[n];

		Stack<Integer> right = new Stack<>();
		int[] r = new int[n];
		// NSL
		// we need to find the index of the next smaller element on the left side
		for (int i = 0; i < n; i++) {
			while (!left.isEmpty() && heights[left.peek()] >= heights[i]) {
				left.pop();
			}
			if (left.isEmpty()) {
				l[i] = -1;
			} else {
				l[i] = left.peek();
			}
			left.push(i);
		}

		// NSR
		// we need to find the index of the next smaller element to the right side
		// handle edge case , when there are no smaller elements to the right
		// assume the next smaller elements index to be lenght of the inout array in
		// that case
		// we do this to facilitate the max calculation that has been done in the end
		for (int i = n - 1; i >= 0; i--) {
			while (!right.isEmpty() && heights[right.peek()] >= heights[i]) {
				right.pop();
			}
			if (right.isEmpty()) {
				r[i] = n;
			} else {
				r[i] = right.peek();
			}
			right.push(i);
		}

		for (int i = 0; i < n; i++) {
			max = Math.max(max, ((r[i] - l[i] - 1) * heights[i]));
		}
		return max;
	}

	// parent method
	public static int maximalRectangle(char[][] matrix) {

		int m = matrix.length;
		if (m == 0) {
			return 0;
		}
		int n = matrix[0].length;
		int[] heights = new int[n];
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0) {
					heights[j] = matrix[i][j] - '0';
				} else if (matrix[i][j] - '0' == 0) {
					heights[j] = 0;
				} else {
					heights[j] = heights[j] + (matrix[i][j] - '0');
				}
			}
			max = Math.max(max, largestRectangleArea(heights));
		}
		return max;

	}

	public static void main(String[] args) {
		char[][] arr = { { '1', '0', '1', '0', '0' }, { '1', '0', '1', '1', '1' }, { '1', '1', '1', '1', '1' },
				{ '1', '0', '0', '1', '0' } };
		System.out.println(maximalRectangle(arr));
	}

}
