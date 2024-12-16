package uber;

public class RobotBlockerDistance {
    // https://leetcode.com/discuss/interview-question/5815294/Uber-SDE2-Onsite
    public static void main(String[] args) {
        String[][] board = {
                {"O", "E", "E", "X"},
                {"X", "X", "O", "E"},
                {"E", "X", "O", "X"}
        };

        // Target distances: up=2, down=2, left=4, right=1
        int[] target = {2, 2, 4, 1};

        // Dimensions of the board
        int rows = board.length;
        int cols = board[0].length;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if ("O".equals(board[r][c])) {
                    // Compute distances
                    int upDist = distanceToX(board, r, c, -1, 0);    // up direction
                    int downDist = distanceToX(board, r, c, 1, 0);   // down direction
                    int leftDist = distanceToX(board, r, c, 0, -1);   // left direction
                    int rightDist = distanceToX(board, r, c, 0, 1);   // right direction

                    int[] distances = {upDist, downDist, leftDist, rightDist};

                    // Check if matches the target
                    if (matches(distances, target)) {
                        System.out.println("Robot at (" + r + "," + c + ") matches the target distances!");
                    }
                }
            }
        }
    }

    /**
     * Finds the distance from a starting position (r, c) to the nearest "X" in a given direction.
     * dr, dc represent the direction increments (for example, up: dr = -1, dc = 0).
     * Returns the number of steps until an "X" is found, or -1 if none found.
     */
    private static int distanceToX(String[][] board, int r, int c, int dr, int dc) {
        int rows = board.length;
        int cols = board[0].length;
        int distance = 0;
        int nr = r + dr;
        int nc = c + dc;

        while (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
            if ("X".equals(board[nr][nc])) {
                return distance + 1; // distance + 1 because we moved one step from robot
            }
            nr += dr;
            nc += dc;
            distance++;
        }

        // If no "X" found
        return -1;
    }

    /**
     * Checks if two integer arrays are equal.
     */
    private static boolean matches(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) return false;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) return false;
        }
        return true;
    }
}
