package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Url: https://leetcode.com/problems/surrounded-regions/description/?envType=problem-list-v2&envId=union-find
 * 
 * You are given an m x n matrix board containing letters 'X' and 'O', capture regions that are surrounded:

Connect: A cell is connected to adjacent cells horizontally or vertically.
Region: To form a region connect every 'O' cell.
Surround: The region is surrounded with 'X' cells if you can connect the region with 'X' cells and none of the region cells are on the edge of the board.
To capture a surrounded region, replace all 'O's with 'X's in-place within the original board. You do not need to return anything.

 

Example 1:

Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]

Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]

Explanation:


In the above diagram, the bottom region is not captured because it is on the edge of the board and cannot be surrounded.

Example 2:

Input: board = [["X"]]

Output: [["X"]]

 

Constraints:

m == board.length
n == board[i].length
1 <= m, n <= 200
board[i][j] is 'X' or 'O'.
 */
public class SurroundedRegions {
	
	record Coord(int x, int y) {
	}

	public boolean isInBoard(int x, int y, int bX, int bY) {
		return x >= 0 && y >= 0 && x < bX && y < bY;
	}

	public boolean isOnTheEdge(List<Coord> region, int bX, int bY) {
		for (Coord c : region) {
			if (c.x == 0 || c.y == 0 || c.x == bX - 1 || c.y == bY - 1) {
				return true;
			}
		}
		return false;
	}

	List<Coord> regionTmp;

	public void dfs(int x, int y, char[][] board, boolean[][] isVisited) {
		final int[][] D = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		isVisited[y][x] = true;
		regionTmp.add(new Coord(x, y));

		for (int[] c : D) {
			int nX = c[0] + x;
			int nY = c[1] + y;
			if (isInBoard(nX, nY, board[0].length, board.length) && board[nY][nX] == 'O' && !isVisited[nY][nX]) {
				dfs(nX, nY, board, isVisited);
			}
		}

	}

	public void solve(char[][] board) {
		int bYL = board.length, bXL = board[0].length;
		boolean[][] isVisited = new boolean[bYL][bXL];

		// List<List<Coord>> regions = new ArrayList<>();
		for (int y = 0; y < bYL; y++) {
			for (int x = 0; x < bXL; x++) {
				if (!isVisited[y][x] && board[y][x] == 'O') {
					regionTmp = new ArrayList<>();
					dfs(x, y, board, isVisited);
					if (isOnTheEdge(regionTmp, bXL, bYL))
						continue;
					// regions.add(regionTmp);
					for (Coord c : regionTmp) {
						board[c.y][c.x] = 'X';
					}
				}
			}
		}

		// for(List<Coord> region : regions){
		// for(Coord c : region){
		// board[c.y][c.x] = 'X';
		// }
		// }

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
