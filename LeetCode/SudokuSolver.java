package LeetCode;

/**
 * Url: https://leetcode.com/problems/sudoku-solver/description/
 * 
 * Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
The '.' character indicates empty cells.

 

Example 1:


Input: board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
Output: [["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
Explanation: The input board is shown above and the only valid solution is shown below:


 

Constraints:

board.length == 9
board[i].length == 9
board[i][j] is a digit or '.'.
It is guaranteed that the input board has only one solution.
 */
public class SudokuSolver {
	char[] getFilledNum(int r, int c, char[][] board) {
		char[] checks = new char[10];

		for (int i = 0; i < 10; i++) {
			checks[i] = (char) (i + '0');
		}

		for (int i = 0; i < 9; i++) {

			if (board[i][c] != '.') {
				checks[board[i][c] - '0'] = '.';
			}
			if (board[r][i] != '.') {
				checks[board[r][i] - '0'] = '.';
			}
		}

		int rt = (r / 3) * 3, ct = (c / 3) * 3;
		for (int i = rt; i < rt + 3; i++) {
			for (int j = ct; j < ct + 3; j++) {
				if (board[i][j] != '.') {
					checks[board[i][j] - '0'] = '.';
				}
			}
		}

		return checks;
	}

	static char[][] boardT;

	public void recursion(int r, int c, char[][] board) {

		if (board[r][c] == '.') {
			char[] fills = getFilledNum(r, c, board);
			for (int ind = 1; ind < 10; ind++) {
				if (fills[ind] != '.') {
					board[r][c] = fills[ind];
					recursion(r, c, board);
					board[r][c] = '.';
				}
			}
		} else {
			if (r < 8 && c == 8)
				recursion(r + 1, 0, board);
			else if (r < 9 && c < 8)
				recursion(r, c + 1, board);
			else {
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						boardT[i][j] = board[i][j];
					}
				}

			}
		}
	}

	public void solveSudoku(char[][] board) {
		boardT = new char[9][9];
		recursion(0, 0, board);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				board[i][j] = boardT[i][j];
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
