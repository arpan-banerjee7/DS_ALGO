package dp.grid;
// https://leetcode.com/problems/dungeon-game/discuss/745340/post-Dedicated-to-beginners-of-DP-or-have-no-clue-how-to-start
// To Do- https://www.youtube.com/watch?v=4uUGxZXoR5o Techdose Approach
// Similar to MinimumPathSum

public class DungeonGame {
	private int solve(int[][] grid, int i, int j) {

		if (i >= grid.length || j >= grid[0].length) {
			return Integer.MAX_VALUE; // tricky part
		}

		if (i == grid.length - 1 && j == grid[0].length - 1) {
			// when we reach the last cell, lets say with 5 health,
			// and the last cell contains positive health, so
			// we ask the last cell wht is the min health u need it will be 1(as knight
			// gains health here)
			// but if it has -ve health then health needed to survive at that cell would be
			// the health I am carrying so far minus the health deducted at that cell+1
			return (grid[i][j] <= 0) ? -grid[i][j] + 1 : 1;
		}
		int minHealthRequired = Math.min(solve(grid, i + 1, j), solve(grid, i, j + 1)) - grid[i][j];
		return minHealthRequired <= 0 ? 1 : minHealthRequired;

	}

	public int calculateMinimumHP(int[][] dungeon) {
		if (dungeon.length == 0) {
			return 0;
		}

		return solve(dungeon, 0, 0);
	}

	public static void main(String[] args) {
		int[][] dungeon = { { -2, -3, 3 }, { -5, -10, 1 }, { 10, 30, -5 } };
		DungeonGame dg = new DungeonGame();
		System.out.println(dg.calculateMinimumHP(dungeon));// Output: 7
	}

}
