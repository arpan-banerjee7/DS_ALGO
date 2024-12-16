package uber;

import java.util.*;


public class FindMinPathWithNegativeWeights {
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        // Priority Queue to store (currentSum, row, col)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        // Track visited cells
        boolean[][] visited = new boolean[m][n];

        // Start from the top-left cell
        pq.offer(new int[]{grid[0][0], 0, 0});

        // Directions for moving: up, down, left, right
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentSum = current[0];
            int row = current[1];
            int col = current[2];

            // If reached bottom-right, return the result
            if (row == m - 1 && col == n - 1) {
                return currentSum;
            }

            // Mark as visited
            if (visited[row][col]) continue;
            visited[row][col] = true;

            // Explore all neighbors
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // Check bounds
                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && !visited[newRow][newCol]) {
                    pq.offer(new int[]{currentSum + grid[newRow][newCol], newRow, newCol});
                }
            }
        }

        // If no path found (unlikely with a valid grid), return a default value
        return -1;
    }

    public static void main(String[] args) {
        FindMinPathWithNegativeWeights solution = new FindMinPathWithNegativeWeights();
        int[][] grid1 = {
                {1, 2, 3},
                {4, 5, 6}
        };
        System.out.println("Test Case 1 Output: " + solution.minPathSum(grid1)); // Expected: 12

        // Test Case 2: Negative integers and all directions
        int[][] grid2 = {
                {1, 2, 3},
                {-4, 5, -6},
                {7, 8, 9}
        };
        System.out.println("Test Case 2 Output: " + solution.minPathSum(grid2)); // Expected: 5

        // Test Case 3: Single cell grid
        int[][] grid3 = {
                {0}
        };
        System.out.println("Test Case 3 Output: " + solution.minPathSum(grid3)); // Expected: 0

        // Test Case 4: Large grid with mixed values
        int[][] grid4 = {
                {1, -2, 3},
                {4, -5, 6},
                {-7, 8, 9}
        };
        System.out.println("Test Case 4 Output: " + solution.minPathSum(grid4)); // Expected: 8
    }
}
