package dp.grid;

import java.util.Arrays;
import java.util.Stack;
// https://leetcode.com/problems/largest-rectangle-in-histogram/#:~:text=Largest%20Rectangle%20in%20Histogram%20%2D%20LeetCode&text=Given%20an%20array%20of%20integers,largest%20rectangle%20in%20the%20histogram.
// Aditya Verma Stack

public class LargestRectangleinHistogram {

	private static int[] findNextSmallerElements(int[] arr) {
		int[] res = new int[arr.length];
		int pseudo_index = arr.length;
		Arrays.fill(res, pseudo_index);

		Stack<Integer> stack = new Stack<Integer>();
		Stack<Integer> indexStack = new Stack<Integer>();
		int n = arr.length;

		for (int i = n - 1; i >= 0; i--) {
			while (!stack.isEmpty()) {
				if (stack.peek() >= arr[i]) {
					stack.pop();
					indexStack.pop();
				} else {
					res[i] = indexStack.peek();
					break;
				}
			}
			stack.push(arr[i]);
			indexStack.push(i);
		}

		return res;
	}

	private static int[] findPrevSmallerElements(int[] arr) {
		int[] res = new int[arr.length];
		int pseudo_index = -1;
		Arrays.fill(res, pseudo_index);

		Stack<Integer> stack = new Stack<Integer>();
		Stack<Integer> indexStack = new Stack<Integer>();
		int n = arr.length;

		for (int i = 0; i <= n - 1; i++) {
			while (!stack.isEmpty()) {
				if (stack.peek() >= arr[i]) {
					stack.pop();
					indexStack.pop();
				} else {
					res[i] = indexStack.peek();
					break;
				}
			}
			stack.push(arr[i]);
			indexStack.push(i);
		}

		return res;
	}

	public int largestRectangleArea(int[] arr) {

		int n = arr.length;
		int max = 0;

		int[] width = new int[n];
		int[] area = new int[n];

		int[] right = findNextSmallerElements(arr);
		int[] left = findPrevSmallerElements(arr);
		for (int i = 0; i <= n - 1; i++) {
			width[i] = right[i] - left[i] - 1;
			area[i] = width[i] * arr[i];
			if (area[i] > max)
				max = area[i];
		}
		return max;
	}

	public static void main(String[] args) {
		int[] heights = { 2, 1, 5, 6, 2, 3 };
		LargestRectangleinHistogram l = new LargestRectangleinHistogram();
		System.out.println(l.largestRectangleArea(heights));// Output: 10
	}

}
