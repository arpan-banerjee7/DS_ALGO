package problems;

// https://www.youtube.com/watch?v=Y72QeX0Efxw Take u forward
// https://www.techiedelight.com/place-rotate-matrix-90-degrees-clock-wise-direction/
// https://leetcode.com/problems/rotate-image/

public class RotateMatrixBy90Degree {

	// swap layer by layer
	public static void rotate(int[][] matrix) {
		int n = matrix.length;
		for (int i = 0; i < n / 2; i++) {
			for (int j = i; j < n - i - 1; j++) {
				int temp = matrix[j][n - i - 1];
				matrix[j][n - i - 1] = matrix[i][j];
				matrix[i][j] = matrix[n - j - 1][i];
				matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
				matrix[n - i - 1][n - j - 1] = temp;
			}
		}
	}

	// swap layer by layer
	public static void rotate2(int[][] mat) {
		// base case
		if (mat == null || mat.length == 0) {
			return;
		}

		// `N × N` matrix
		int N = mat.length;

		// Transpose the matrix
		for (int i = 0; i < N; i++) {
			for (int j = i; j < N; j++) {
				int temp = mat[i][j];
				mat[i][j] = mat[j][i];
				mat[j][i] = temp;
			}
		}

		// swap columns
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N / 2; j++) {
				int temp = mat[i][j];
				mat[i][j] = mat[i][N - j - 1];
				mat[i][N - j - 1] = temp;
			}
		}
	}

	public static void main(String[] args) {
		int matrix[][]= { {1,2,3}, {4,5,6}, {7,8,9} };
		rotate(matrix);
	}


}
