package dp.grid;

// Boundary DFS
// 130. Surrounded Regions
// https://leetcode.com/problems/surrounded-regions/description/
public class SurroundedRegions {
    private void dfs(char[][] grid, int i, int j, boolean[][] vis) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 'X' || vis[i][j])
            return;

        vis[i][j] = true;

        int[][] directions = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
        for (int[] dir : directions) {
            int row = i + dir[0];
            int col = j + dir[1];
            dfs(grid, row, col, vis);
        }
    }

    private void modDfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 'X')
            return;

        grid[i][j] = 'X';

        int[][] directions = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
        for (int[] dir : directions) {
            int row = i + dir[0];
            int col = j + dir[1];
            modDfs(grid, row, col);
        }
    }

    public void solve(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] vis = new boolean[n][m];
        // boundary dfs, converting all boundary elements to 0
        // set 1st and last column
        for (int j = 0; j < m; j++) {
            if (grid[0][j] == 'O') {
                dfs(grid, 0, j, vis);
            }
            if (grid[n - 1][j] == 'O') {
                dfs(grid, n - 1, j, vis);
            }
        }

        // set first and last row
        for (int i = 0; i < n; i++) {
            if (grid[i][0] == 'O') {
                dfs(grid, i, 0, vis);
            }
            if (grid[i][m - 1] == 'O') {
                dfs(grid, i, m - 1, vis);
            }
        }

        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (grid[i][j] == 'O' && !vis[i][j]) {
                    modDfs(grid, i, j);
                }
            }
        }

    }
}
