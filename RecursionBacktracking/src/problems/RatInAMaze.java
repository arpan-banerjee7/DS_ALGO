package problems;

import java.util.ArrayList;
import java.util.List;

// https://www.geeksforgeeks.org/rat-in-a-maze-backtracking-2/https://www.geeksforgeeks.org/rat-in-a-maze-backtracking-2/
// https://leetcode.com/problems/word-search-ii/discuss/1485776/java-backtracking-derived-from-word-search-i-and-rate-in-a-maze-commented

public class RatInAMaze {

	private static void solve(int i, int j, StringBuffer ans, List<String> res, int[][] maze, boolean[][] travelled) {
		// base condition
		if (i == maze.length - 1 && j == maze.length - 1) {
			res.add(ans.toString());
			return;
		}

		if (maze[i][j] == 0) {
			return;
		}

		// D
		if ((i + 1 <= maze.length - 1) && maze[i + 1][j] != 0 && (!travelled[i + 1][j])) {
			ans.append("D");
			travelled[i + 1][j] = true;
			solve(i + 1, j, ans, res, maze, travelled);
			ans.setLength(ans.length() - 1);
			travelled[i + 1][j] = false;
		}
		// L
		if ((j - 1 >= 0) && maze[i][j - 1] != 0 && (!travelled[i][j - 1])) {
			ans.append("L");
			travelled[i][j - 1] = true;
			solve(i, j - 1, ans, res, maze, travelled);
			ans.setLength(ans.length() - 1);
			travelled[i][j - 1] = false;
		}
		// R
		if ((j + 1 <= maze.length - 1) && maze[i][j + 1] != 0 && (!travelled[i][j + 1])) {
			ans.append("R");
			travelled[i][j + 1] = true;
			solve(i, j + 1, ans, res, maze, travelled);
			ans.setLength(ans.length() - 1);
			travelled[i][j + 1] = false;
		}
		// U
		if ((i - 1 >= 0) && maze[i - 1][j] != 0 && (!travelled[i - 1][j])) {
			ans.append("U");
			travelled[i - 1][j] = true;
			solve(i - 1, j, ans, res, maze, travelled);
			ans.setLength(ans.length() - 1);
			travelled[i - 1][j] = false;
		}

	}

	public static void main(String[] args) {
		int[][] maze = { { 1, 1 }, { 1, 1 } };
		int N = maze.length;
		boolean[][] travelled = new boolean[N][N];
		List<String> res = new ArrayList<>();

		travelled[0][0] = true;
		solve(0, 0, new StringBuffer(), res, maze, travelled);

		res.forEach(e -> System.out.print(e + " "));
	}

}
