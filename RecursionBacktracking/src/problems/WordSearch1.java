package problems;

// https://leetcode.com/problems/word-search/
// https://leetcode.com/problems/word-search-ii/discuss/1485776/java-backtracking-derived-from-word-search-i-and-rate-in-a-maze-commented

public class WordSearch1 {

	private static boolean solve(int i, int j, char[][] board, boolean[][] travelled, String word, int charIndex) {
		// base condition
		if (charIndex == word.length()) {
			return true;
		}

		// all invalid cases returns false
		// D L R U
		if (i >= board.length || j < 0 || j >= board[0].length || i < 0 || travelled[i][j]) {
			return false;
		}

		// char at board[i][j] doesn t match with the char at that index of the word
		if (board[i][j] != word.charAt(charIndex)) {
			return false;
		}

		// we will mark the curr i j as travelled
		travelled[i][j] = true;
		// check for all directions now, if any of them returns true we got our ans else
		// false
		// D L R U
		boolean isFound = (solve(i + 1, j, board, travelled, word, charIndex + 1) || // D
				solve(i, j - 1, board, travelled, word, charIndex + 1) || // L
				solve(i, j + 1, board, travelled, word, charIndex + 1) || // R
				solve(i - 1, j, board, travelled, word, charIndex + 1)); // U
		travelled[i][j] = false;
		return isFound;
	}

	public static boolean exist(char[][] board, String word) {
		int m = board.length;
		int n = board[0].length;
		boolean[][] travelled = new boolean[m][n];

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == word.charAt(0) && solve(i, j, board, travelled, word, 0)) {
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		char[][] board= {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
		String word = "ABCB";
		System.out.println(exist(board, word));
		// Output: false
	}

}
