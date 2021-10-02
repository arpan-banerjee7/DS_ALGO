package problems;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/word-search-ii/
// https://leetcode.com/problems/word-search-ii/discuss/1485776/java-backtracking-derived-from-word-search-i-and-rate-in-a-maze-commented
public class WordSearch2 {

	private static boolean solve(int i, int j, char[][] board, String word, int charIndex) {
		// base condition
		if (charIndex == word.length()) {
			return true;
		}

		// all invalid cases returns false
		// D L R U
		if (i >= board.length || j < 0 || j >= board[0].length || i < 0) {
			return false;
		}

		// char at board[i][j] doesn t match with the char at that index of the word
		if (board[i][j] != word.charAt(charIndex)) {
			return false;
		}
		char temp = board[i][j];
		// we will mark the curr i j as travelled
		board[i][j] = '#';
		// check for all directions now, if any of them returns true we got our ans else
		// false
		// D L R U
		boolean isFound = (solve(i + 1, j, board, word, charIndex + 1) || // D
				solve(i, j - 1, board, word, charIndex + 1) || // L
				solve(i, j + 1, board, word, charIndex + 1) || // R
				solve(i - 1, j, board, word, charIndex + 1)); // U
		board[i][j] = temp;
		return isFound;
	}

	private static boolean isPresent(char[][] board, String word) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == word.charAt(0) && solve(i, j, board, word, 0)) {
					return true;
				}

			}
		}
		return false;
	}

	public static List<String> findWords(char[][] board, String[] words) {
		List<String> res = new ArrayList<>();
		for (String word : words) {
			if (isPresent(board, word)) {
				res.add(word);
			}
		}

		return res;
	}

	public static void main(String[] args) {
		char[][] board = { { 'o', 'a', 'a', 'n' }, { 'e', 't', 'a', 'e' }, { 'i', 'h', 'k', 'r' },
				{ 'i', 'f', 'l', 'v' } };
		String[] words = { "oath", "pea", "eat", "rain" };
		List<String> res = findWords(board, words);
		res.forEach(e -> System.out.print(e + " "));
		// Output: ["eat","oath"]
	}

}
