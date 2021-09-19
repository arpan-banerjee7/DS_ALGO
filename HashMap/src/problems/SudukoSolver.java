package problems;

public class SudukoSolver {

	// try recursively filling for each row, if it fails to fill 0-9
	// at some point backtrack and start over again

	// logic for can fill is simply a game of modulo
	private static boolean solve(char[][] board, int row) {
		for (int i = row; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == '.') { // place is empty
					for (int c = 1; c <= 9; c++) {
						char x = (char) (c + '0');
						if (canFill(board, i, j, x)) {

							board[i][j] = x;
							if (solve(board, i)) {
								return true;
							} else {
								board[i][j] = '.';
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}

	private static boolean canFill(char[][] board, int row, int col, char c) {
		for (int i = 0; i < 9; i++) {
			if (board[row][i] == c) {
				return false;
			} else if (board[i][col] == c) {
				return false;
			} else if (board[(3 * (row / 3) + i / 3)][(3 * (col / 3) + i % 3)] == c) {
				return false;
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
		System.out.println(solve(board, 0));

	}

}
