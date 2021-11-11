package dp.grid;
// https://leetcode.com/problems/dungeon-game/discuss/745340/post-Dedicated-to-beginners-of-DP-or-have-no-clue-how-to-start
// Similar to MinimumPathSum
// To Do- https://www.youtube.com/watch?v=4uUGxZXoR5o Techdose Approach

import java.util.Arrays;

public class DungeonGameTab {
	public int calculateMinimumHP(int[][] dungeon) {
		int m = dungeon.length;
		int n = dungeon[0].length;

		int[][] dp = new int[m + 1][n + 1];
		for (int i = 0; i <= m; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}

		// to fill the last row, to begin there
		dp[m][n - 1] = 1;

		// value of a cell= min(down cell,right cell)+own cell value
		for (int i = m - 1; i >= 0; i--) {
			for (int j = n - 1; j >= 0; j--) {
				int minHealthRequired = Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j];
				dp[i][j] = minHealthRequired <= 0 ? 1 : minHealthRequired;
			}
		}

		return dp[0][0];
	}

	public static void main(String[] args) {
		int[][] dungeon = { { -2, -3, 3 }, { -5, -10, 1 }, { 10, 30, -5 } };
		DungeonGame dg = new DungeonGame();
		System.out.println(dg.calculateMinimumHP(dungeon));// Output: 7
	}
}
