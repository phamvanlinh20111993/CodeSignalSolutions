package LeetCode;

/**
 * url: https://leetcode.com/problems/battleships-in-a-board/description/
 * 
 * Given an m x n matrix board where each cell is a battleship 'X' or empty '.', return the number of the battleships on board.

Battleships can only be placed horizontally or vertically on board. In other words, they can only be made of the shape 1 x k (1 row, k columns) or k x 1 (k rows, 1 column), where k can be of any size. At least one horizontal or vertical cell separates between two battleships (i.e., there are no adjacent battleships).

 

Example 1:


Input: board = [["X",".",".","X"],[".",".",".","X"],[".",".",".","X"]]
Output: 2
Example 2:

Input: board = [["."]]
Output: 0
 

Constraints:

m == board.length
n == board[i].length
1 <= m, n <= 200
board[i][j] is either '.' or 'X'.
 

Follow up: Could you do it in one-pass, using only O(1) extra memory and without modifying the values board?
 */
public class Battleships_in_a_Board {
	
	public int countBattleships(char[][] board) {
        int lY = board.length;
        int lX = board[0].length;
        int c = 0;
        for(int y = 0; y < lY; y++){
            for(int x = 0; x < lX; x++){
                if(board[y][x] == 'X'){
                    c++;
                    int tX = x;
                    while(tX < lX && board[y][tX] == 'X'){
                        board[y][tX++] = '.';
                    }

                    int tY = y+1;
                    while(tY < lY && board[tY][x] == 'X'){
                        board[tY++][x] = '.';
                    }
                }
            }
        }

        return c;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
