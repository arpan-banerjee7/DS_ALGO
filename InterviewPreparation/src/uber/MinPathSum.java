package uber;

public class MinPathSum {
    private int findMinSum(int i, int j, int[][] grid) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return Integer.MAX_VALUE;
        }
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            return grid[i][j];
        }

        int minSum = Integer.MAX_VALUE;
        int[][] directions = {{0, 1}, {1, 0}};
        for (int[] dir : directions) {
            int row = i + dir[0];
            int col = j + dir[1];
            int childSum = findMinSum(row, col, grid);
            minSum = Math.min(minSum, childSum);
        }
        return grid[i][j] + minSum;

    }

    public int minPathSum(int[][] grid) {
        return findMinSum(0, 0, grid);
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 2, 3}, {4, 5, 6}};
        MinPathSum ms = new MinPathSum();
        System.out.println(ms.minPathSum(grid));
    }
}
