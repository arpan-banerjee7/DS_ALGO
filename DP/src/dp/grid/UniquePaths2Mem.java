package dp.grid;

import java.util.Arrays;

public class UniquePaths2Mem {
	int[][] dp;

	private int solve(int i, int j, int[][] grid) {
		if ((i == grid.length - 1) && (j == grid[0].length - 1) && grid[i][j] != 1) {
			return 1;
		}

		if ((i >= grid.length) || (j >= grid[0].length)) {
			return 0;
		}

		if (grid[i][j] == 1) {
			return 0;
		}

		if (dp[i][j] != -1) {
			return dp[i][j];
		}

		return dp[i][j] = solve(i + 1, j, grid) + solve(i, j + 1, grid);
	}

	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		dp = new int[obstacleGrid.length][obstacleGrid[0].length];
		for (int i = 0; i < obstacleGrid.length; i++) {
			Arrays.fill(dp[i], -1);
		}
		return solve(0, 0, obstacleGrid);
	}

	public static void main(String[] args) {
		int[][] obstacleGrid = { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } };
		UniquePaths2Mem up = new UniquePaths2Mem();
		System.out.println(up.uniquePathsWithObstacles(obstacleGrid));
	}

}
