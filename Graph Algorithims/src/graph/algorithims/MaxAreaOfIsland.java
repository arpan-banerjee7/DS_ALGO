package graph.algorithims;

// 695. Max Area of Island
// https://leetcode.com/problems/max-area-of-island/

public class MaxAreaOfIsland {

	private int dfs(int[][] grid, int i, int j, int m, int n) {
		if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == 0)
			return 0;

		// D L R U
		int[][] directionsArr = { { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, 0 } };

		int size = 0;
		grid[i][j] = 0;
		for (int[] direction : directionsArr) {
			int row = i + direction[0];
			int col = j + direction[1];
			size += dfs(grid, row, col, m, n);
		}
		return size += 1;
	}

	public int maxAreaOfIsland(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		int maxSize = 0;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					maxSize = Math.max(maxSize, dfs(grid, i, j, m, n));

				}
			}
		}
		return maxSize;
	}

	public static void main(String[] args) {

	}

}
