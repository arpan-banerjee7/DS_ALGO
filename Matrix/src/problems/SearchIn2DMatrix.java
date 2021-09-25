package problems;

// https://leetcode.com/problems/search-a-2d-matrix/

public class SearchIn2DMatrix {

	private static boolean binarySearch(int[] arr, int low, int high, int target) {
		if (low > high) {
			return false;
		}
		int mid = low + (high - low) / 2;
		if (arr[mid] == target) {
			return true;
		}
		if (target > arr[mid]) {
			return binarySearch(arr, mid + 1, high, target);
		} else {
			return binarySearch(arr, low, mid - 1, target);
		}
	}

	public static boolean searchMatrix(int[][] matrix, int target) {
		int m = matrix.length;
		int n = matrix[0].length;
		for (int i = 0; i < m; i++) {
			if (target <= matrix[i][n - 1]) {
				return binarySearch(matrix[i], 0, n - 1, target);
			}
		}
		return false;
	}

	public static boolean searchMatrix2(int[][] matrix, int target) {
		int m = matrix.length;
		int n = matrix[0].length;
		int i = 0;
		int j = n - 1;
		while (i < m && j >= 0) {
			if (target == matrix[i][j]) {
				return true;
			} else if (target > matrix[i][j]) {
				i++;
			} else {
				j--;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 3, 5, 7 }, { 10, 11, 16, 20 }, { 23, 30, 34, 60 } };
		int target = 13;
		System.out.println(searchMatrix2(matrix, target));

	}

}
