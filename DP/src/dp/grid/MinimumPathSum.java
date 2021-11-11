package dp.grid;
// https://leetcode.com/problems/minimum-path-sum/
// To Do- Techdose Approach
public class MinimumPathSum {

	private static int solve(int[][] grid, int i, int j) {

		if (i == grid.length || j == grid[0].length) {
			return Integer.MAX_VALUE;
		}

		if (i == grid.length - 1 && j == grid[0].length - 1) {
			return grid[i][j];
		}

		return grid[i][j] + Math.min(solve(grid, i + 1, j), // D
				solve(grid, i, j + 1));// R
	}

	public int minPathSum(int[][] grid) {
		if (grid.length == 0) {
			return 0;
		}
		return solve(grid, 0, 0);
	}

	public static void main(String[] args) {
		int[][] grid = { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };
		MinimumPathSum ms = new MinimumPathSum();
		System.out.println(ms.minPathSum(grid));// Output: 7
	}

}
