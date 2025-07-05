package LeetCode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * url: https://leetcode.com/problems/sliding-puzzle/description/?envType=problem-list-v2&envId=backtracking
 * 
 * On an 2 x 3 board, there are five tiles labeled from 1 to 5, and an empty square represented by 0. A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

Given the puzzle board board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.

 

Example 1:


Input: board = [[1,2,3],[4,0,5]]
Output: 1
Explanation: Swap the 0 and the 5 in one move.
Example 2:


Input: board = [[1,2,3],[5,4,0]]
Output: -1
Explanation: No number of moves will make the board solved.
Example 3:


Input: board = [[4,1,2],[5,0,3]]
Output: 5
Explanation: 5 is the smallest number of moves that solves the board.
An example path:
After move 0: [[4,1,2],[5,0,3]]
After move 1: [[4,1,2],[0,5,3]]
After move 2: [[0,1,2],[4,5,3]]
After move 3: [[1,0,2],[4,5,3]]
After move 4: [[1,2,0],[4,5,3]]
After move 5: [[1,2,3],[4,5,0]]
 

Constraints:

board.length == 2
board[i].length == 3
0 <= board[i][j] <= 5
Each value board[i][j] is unique.
 */
public class SlidingPuzzle {
	
	public boolean isInBoard(int x, int y) {
		return x >= 0 && x < 2 && y >= 0 && y < 3;
	}

	public int[][] clone(int[][] board) {
		int[][] boardCLone = new int[3][3];
		boardCLone[0][0] = board[0][0];
		boardCLone[0][1] = board[0][1];
		boardCLone[0][2] = board[0][2];

		boardCLone[1][0] = board[1][0];
		boardCLone[1][1] = board[1][1];
		boardCLone[1][2] = board[1][2];

		return boardCLone;
	}

	public boolean isValid(int[][] b) {
		return b[0][0] == 1 && b[0][1] == 2 && b[0][2] == 3 && b[1][0] == 4 && b[1][1] == 5 && b[1][2] == 0;
	}

	record Data(int x, int y, int lv, int[][] board) {
	}

	public int bfs(int x, int y, int[][] board) {
		Queue<Data> queue = new LinkedList<>();
		queue.add(new Data(x, y, 0, board));

		Set<String> isVisit = new HashSet<>();

		int[][] D = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

		String k = board[0][0] + "" + board[0][1] + "" + board[0][2] + "" + board[1][0] + "" + board[1][1] + ""
				+ board[1][2];
		isVisit.add(k);

		while (!queue.isEmpty()) {
			Data data = queue.poll();
			int lv = data.lv;
			if (isValid(data.board)) {
				return lv;
			}

			for (int[] d : D) {
				int dX = d[0] + data.x;
				int dY = d[1] + data.y;
				if (isInBoard(dX, dY)) {
					int[][] boardClone = clone(data.board);
					boardClone[data.x][data.y] = boardClone[dX][dY];
					boardClone[dX][dY] = 0;

					String k1 = boardClone[0][0] + "" + boardClone[0][1] + "" + boardClone[0][2] + "" + boardClone[1][0]
							+ "" + boardClone[1][1] + "" + boardClone[1][2];
					if (!isVisit.contains(k1)) {
						isVisit.add(k1);
						queue.add(new Data(dX, dY, lv + 1, boardClone));
					}
				}
			}
		}

		return -1;
	}

	public int slidingPuzzle(int[][] board) {
		for (int y = 0; y < 2; y++) {
			for (int x = 0; x < 3; x++) {
				if (board[y][x] == 0) {
					return bfs(y, x, board);
				}
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
