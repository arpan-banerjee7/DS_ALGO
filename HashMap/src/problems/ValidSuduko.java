package problems;

import java.util.HashSet;

// https://leetcode.com/problems/valid-sudoku/
// https://www.youtube.com/watch?v=Pl7mMcBm2b8
// https://www.geeksforgeeks.org/hashset-add-method-in-java/

public class ValidSuduko {

	public static boolean isValidSudoku(char[][] board) {
		HashSet<String> seen = new HashSet<>();

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				char curr = board[i][j];

				if (curr != '.') {
					/*
					 * Hash_Set.add(Object element) Return Value: The function returns True if the
					 * element is not present in the HashSet otherwise False if the element is
					 * already present in the HashSet.
					 */
					if (!seen.add(curr + " found in row " + i) || !seen.add(curr + " found in column " + j)
							|| !seen.add(curr + " found in subbox " + i / 3 + "-" + j / 3))
						return false;
				}

			}
		}
		return true;
	}

	public static void main(String[] args) {
		char[][] board = { { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
				{ '6', '.', '.', '1', '9', '5', '.', '.', '.' }, { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
				{ '8', '.', '.', '.', '6', '.', '.', '.', '3' }, { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
				{ '7', '.', '.', '.', '2', '.', '.', '.', '6' }, { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
				{ '.', '.', '.', '4', '1', '9', '.', '.', '5' }, { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
		System.out.println(isValidSudoku(board));

	}

}
