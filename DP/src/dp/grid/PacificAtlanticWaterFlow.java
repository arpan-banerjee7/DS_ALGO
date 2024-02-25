package dp.grid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Boundary DFS
// 417. Pacific Atlantic Water Flow
// https://leetcode.com/problems/pacific-atlantic-water-flow/description/
/*
    Boundary DFS, Run DFS from all the boundaries(first row,last row|| first col, last col) and mark the cells
    Logic is ultimately water will fall into pacific ocean from first row and first col, and similary for atlantic
    last row and last column, so we wll start from these edges and try to mark the cells till there is a valid path.
    valid path is current cell should be greater than or equal to previous cell height.
    as Pacific vis or atlantic visited depending on for which we are running DFS for

 */
public class PacificAtlanticWaterFlow {
    public void dfs(int i, int j, int heights[][], int preHeight, boolean vis[][]) {
        if (i < 0 || j < 0 || i == heights.length || j == heights[0].length || vis[i][j] || preHeight > heights[i][j])
            return;
        vis[i][j] = true;
        int[][] directions = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
        for (int[] dir : directions) {
            int row = i + dir[0];
            int col = j + dir[1];
            dfs(row, col, heights, heights[i][j], vis);
        }
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int n = heights.length, m = heights[0].length;
        boolean[][] pacific = new boolean[n][m], atlantic = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            dfs(i, 0, heights, heights[i][0], pacific); //1st col
            dfs(i, m - 1, heights, heights[i][m - 1], atlantic); //last col
        }
        for (int j = 0; j < m; j++) {
            dfs(0, j, heights, heights[0][j], pacific); //1st row
            dfs(n - 1, j, heights, heights[n - 1][j], atlantic); //last row
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (pacific[i][j] && atlantic[i][j]) ans.add(Arrays.asList(i, j));
            }
        }
        return ans;
    }
}
