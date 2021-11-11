package dp.grid;
// All solutions-
// https://leetcode.com/problems/maximal-square/discuss/1569523/java-aditya-verma-approach-similar-to-maximal-rectangle-minor-modification

public class MaximalSquareMemTab {
	public int maximalSquare(char[][] matrix) {
		int rows = matrix.length;
		if (rows == 0) {
			return 0;
		}

		int column = matrix[0].length;
		int[][] dp = new int[rows + 1][column + 1];

		int largest = 0;
		for (int i = 1; i <= rows; i++) {
			for (int j = 1; j <= column; j++) {

				if (matrix[i - 1][j - 1] == '1') {
					// value = current val + left diagonal + left row + upper col
					dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));

					if (largest < dp[i][j])
						largest = dp[i][j];
				}

			}
		}

		return largest * largest;
	}

	public static void main(String[] args) {
		char[][] matrix = { { '0', '1' }, { '1', '0' } };
		MaximalSquareMem ms = new MaximalSquareMem();
		System.out.println(ms.maximalSquare(matrix));// Output: 1
	}
}
