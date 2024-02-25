package dp.grid;

// Boundary DFS
// 1254. Number of Closed Islands
// https://leetcode.com/problems/number-of-closed-islands/description/?envType=list&envId=ewdybh21

public class NumberOfClosedIslands {
    private void dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 1)
            return;

        grid[i][j] = 1;

        int[][] directions = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
        for (int[] dir : directions) {
            int row = i + dir[0];
            int col = j + dir[1];
            dfs(grid, row, col);
        }
    }

    public int closedIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        // boundary dfs, converting all boundary elements to 0
        // set 1st and last row
        for (int j = 0; j < m; j++) {
            if (grid[0][j] == 0) {
                dfs(grid, 0, j);
            }
            if (grid[n - 1][j] == 0) {
                dfs(grid, n - 1, j);
            }
        }

        // set first and last column
        for (int i = 0; i < n; i++) {
            if (grid[i][0] == 0) {
                dfs(grid, i, 0);
            }
            if (grid[i][m - 1] == 0) {
                dfs(grid, i, m - 1);
            }
        }

        int ans = 0;
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (grid[i][j] == 0) {
                    dfs(grid, i, j);
                    ans++;
                }
            }
        }

        return ans;

    }
}
