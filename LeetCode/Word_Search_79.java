package LeetCode;

/**
 * url: https://leetcode.com/problems/word-search/?envType=study-plan-v2&envId=top-interview-150
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.

 

Example 1:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true
Example 2:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
Output: true
Example 3:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
Output: false
 

Constraints:

m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board and word consists of only lowercase and uppercase English letters.
 

Follow up: Could you use search pruning to make your solution faster with a larger board?

 */
public class Word_Search_79 {
	
	  boolean[][] isVisited;
	     boolean isExist;

	    public boolean isInBoard(int x, int y, int lX, int lY){
	        return x < lX && y < lY && x > -1 && y > -1;
	    }

	    public void recursive(char[][] board, String word, int p, int x, int y){
	        if(p >= word.length() || isExist){
	            isExist = true;
	            return;
	        }

	        int lX = board[0].length;
	        int lY = board.length;

	        if(isInBoard(x, y-1, lX, lY) && !isVisited[y-1][x] && board[y-1][x] == word.charAt(p)){
	            isVisited[y-1][x] = true;
	            recursive(board, word, p+1, x, y-1);
	            isVisited[y-1][x] = false;
	        }

	         if(isInBoard(x, y+1, lX, lY) && !isVisited[y+1][x] && board[y+1][x] == word.charAt(p)){
	            isVisited[y+1][x] = true;
	            recursive(board, word, p+1, x, y+1);
	            isVisited[y+1][x] = false;
	        }

	        if(isInBoard(x-1, y, lX, lY) && !isVisited[y][x-1] && board[y][x-1] == word.charAt(p)){
	            isVisited[y][x-1] = true;
	            recursive(board, word, p+1, x-1, y);
	            isVisited[y][x-1] = false;
	        }

	         if(isInBoard(x+1, y, lX, lY) && !isVisited[y][x+1] && board[y][x+1] == word.charAt(p)){
	            isVisited[y][x+1] = true;
	            recursive(board, word, p+1, x+1, y);
	            isVisited[y][x+1] = false;
	        }

	    }

	    public boolean exist(char[][] board, String word) {
	        isVisited = new boolean[board.length][board[0].length];
	        isExist = false;
	        for(int y = 0; y < board.length; y++){
	            if(isExist) break;
	            for(int x = 0; x < board[0].length; x++){
	                if(isExist) break;
	                if(word.charAt(0) == board[y][x]){
	                    isVisited[y][x] = true;
	                    recursive(board, word, 1, x, y);
	                     isVisited[y][x] = false;
	                }
	            }
	        }
	        return isExist;
	    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
