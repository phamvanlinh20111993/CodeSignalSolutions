package LeetCode;

import java.util.HashSet;
import java.util.Set;

/**
 * Url: https://leetcode.com/problems/valid-sudoku/description/
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
Note:

A Sudoku board (partially filled) could be valid but is not necessarily solvable.
Only the filled cells need to be validated according to the mentioned rules.
 

Example 1:


Input: board = 
[["5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: true
Example 2:

Input: board = 
[["8","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: false
Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 

Constraints:

board.length == 9
board[i].length == 9
board[i][j] is a digit 1-9 or '.'.
 */
public class ValidSudoku {
	
	public boolean isValidSudoku(char[][] board) {
		// check row
		for (int ind = 0; ind < 9; ind++) {
			Set<Character> dup = new HashSet<>();
			for (int pos = 0; pos < 9; pos++) {
				if (board[ind][pos] == '.')
					continue;
				if (dup.contains(board[ind][pos]))
					return false;
				dup.add(board[ind][pos]);
			}
		}
		// check column
		for (int c = 0; c < 9; c++) {
			Set<Character> dup = new HashSet<>();
			for (int r = 0; r < 9; r++) {
				if (board[r][c] == '.')
					continue;
				if (dup.contains(board[r][c]))
					return false;
				dup.add(board[r][c]);
			}
		}
		// Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9
		// without repetition.
		for (int ind = 0; ind < 9; ind++) {
			int r = 3 * (ind / 3);
			int c = 3 * (ind % 3);
			Set<Character> dup = new HashSet<>();
			for (int mr = r; mr < r + 3; mr++) {
				for (int mc = c; mc < c + 3; mc++) {
					if (board[mr][mc] == '.')
						continue;
					if (dup.contains(board[mr][mc]))
						return false;
					dup.add(board[mr][mc]);
				}
			}
		}

		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
