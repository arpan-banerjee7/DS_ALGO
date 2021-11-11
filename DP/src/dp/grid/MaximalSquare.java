package dp.grid;

import java.util.Stack;

/*
1.  // Derived from Maximal rectangle
https://leetcode.com/problems/maximal-square/discuss/1094140/SIMPLEST-JAVA-SOLUTION-USING-MAXIMAL-RECTANGLE-SOLUTION

2.  DFS + Memo // down, right,diagonal length same for a square matrix- using this property run dfs for all 1s, find for each 1 what is the max length we can go where down, right and left all are equal, calculate the area, take the max of all areas calculated from all 1s
https://leetcode.com/problems/maximal-square/discuss/996864/Java-Recurision-(DFS-)-%2B-DP
 https://leetcode.com/problems/maximal-square/discuss/551246/DFS-%2B-Memoization

3. Top Down
https://www.youtube.com/watch?v=RElcqtFYTm0

**All solutions-**
https://leetcode.com/problems/maximal-square/discuss/1569523/java-aditya-verma-approach-similar-to-maximal-rectangle-minor-modification
 */

//Aditya Verma Approach, similar to Maximal Rectangle Problem with minor modification

// For all solutions-
// TC  O(m*n)
// Space -O(m*n)**
public class MaximalSquare {

	public int largestRectangleArea(int[] heights) {
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

		// modification here, we need a square, so taking the min of length and height
		// of
		// each block and calculating area, take max of all, that will be our ans
		for (int i = 0; i < n; i++) {
			int length = r[i] - l[i] - 1;
			int height = heights[i];
			int side = Math.min(length, height);
			max = Math.max(max, (side * side));
		}
		return max;
	}

	public int maximalSquare(char[][] matrix) {
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
		char[][] matrix = { { '0', '1' }, { '1', '0' } };
		MaximalSquare ms = new MaximalSquare();
		System.out.println(ms.maximalSquare(matrix));// Output: 1
	}

}
