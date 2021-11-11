package dp.grid;
// https://leetcode.com/problems/unique-paths/
// https://www.youtube.com/watch?v=IlEsdxuD4lY

// TLE

public class UniquePaths {
	int count = 0;
	private void solve(int i, int j, int[][] maze) {

		// base condition
		if (i == maze.length - 1 && j == maze[0].length - 1) {
			count++;
			return;
		}

		// D
		if (i + 1 <= maze.length - 1) {
			solve(i + 1, j, maze);
		}

		// R
		if (j + 1 <= maze[0].length - 1) {
			solve(i, j + 1, maze);
		}
	}

	public static void main(String[] args) {
		int m = 3, n = 7;
		int[][] maze = new int[m][n];
		UniquePaths up = new UniquePaths();
		up.solve(0, 0, maze);
		System.out.println(up.count);// Output: 28
	}

}
