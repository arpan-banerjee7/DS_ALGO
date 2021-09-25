package problems;

import java.util.ArrayList;
import java.util.List;

// https://www.techiedelight.com/print-matrix-spiral-order/

public class SpiralTraversal {

	public static List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> list = new ArrayList<>();

		// base case
		if (matrix == null || matrix.length == 0) {
			return list;
		}

		int top = 0, bottom = matrix.length - 1;
		int left = 0, right = matrix[0].length - 1;

		while (true) {
			if (left > right)
				break;

			for (int i = left; i <= right; i++) {
				list.add(matrix[top][i]);
			}

			top++;

			if (top > bottom)
				break;

			for (int i = top; i <= bottom; i++) {
				list.add(matrix[i][right]);
			}

			right--;

			if (left > right)
				break;

			for (int i = right; i >= left; i--) {
				list.add(matrix[bottom][i]);
			}

			bottom--;

			if (top > bottom)
				break;

			for (int i = bottom; i >= top; i--) {
				list.add(matrix[i][left]);
			}

			left++;
		}
		return list;
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4, 5 }, { 16, 17, 18, 19, 6 }, { 15, 24, 25, 20, 7 }, { 14, 23, 22, 21, 8 },
				{ 13, 12, 11, 10, 9 } };
		List<Integer> list = spiralOrder(matrix);
		System.out.println(list);
	}

}
