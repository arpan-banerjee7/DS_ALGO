package stackqueue.problems;
import java.util.Stack;

// https://leetcode.com/problems/largest-rectangle-in-histogram/
// https://youtu.be/J2X70jj_I1o
// https://www.geeksforgeeks.org/largest-rectangle-under-histogram/

public class MaximumAreaHistogram {

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

	public static void main(String[] args) {
		int[] arr = { 6, 2, 5, 4, 5, 1, 6 };
		// int[] arr = { 2, 3 };
		int max = largestRectangleArea(arr);
		System.out.println("The Maximum area is " + max);
	}

}
