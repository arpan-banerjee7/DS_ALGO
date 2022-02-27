package graph.algorithims;

// 200. Number of Islands
// https://leetcode.com/problems/number-of-islands/
//similar to rat in a maze and other prob of DLRU

public class NumberOfIslands {

	private void dfs(char[][] grid, int i, int j, int m, int n) {
		if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == '0')
			return;

		// D L R U
		int[][] directionsArr = { { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, 0 } };

		grid[i][j] = '0';
		for (int[] direction : directionsArr) {
			int row = i + direction[0];
			int col = j + direction[1];
			dfs(grid, row, col, m, n);
		}
	}

	public int numIslands(char[][] grid) {
		int islands = 0;
		int m = grid.length;
		int n = grid[0].length;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == '1') {
					dfs(grid, i, j, m, n);
					islands++;
				}
			}
		}
		return islands;
	}

	public static void main(String[] args) {
		/*Input: grid = [
		               ["1","1","1","1","0"],
		               ["1","1","0","1","0"],
		               ["1","1","0","0","0"],
		               ["0","0","0","0","0"]
		             ]
		             Output: 1*/

	}

}
