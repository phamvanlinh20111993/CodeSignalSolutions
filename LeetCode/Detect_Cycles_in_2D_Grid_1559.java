package LeetCode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * url:
 * 
 * Given a 2D array of characters grid of size m x n, you need to find if there exists any cycle consisting of the same value in grid.

A cycle is a path of length 4 or more in the grid that starts and ends at the same cell. From a given cell, you can move to one of the cells adjacent to it - in one of the four directions (up, down, left, or right), if it has the same value of the current cell.

Also, you cannot move to the cell that you visited in your last move. For example, the cycle (1, 1) -> (1, 2) -> (1, 1) is invalid because from (1, 2) we visited (1, 1) which was the last visited cell.

Return true if any cycle of the same value exists in grid, otherwise, return false.

 

Example 1:



Input: grid = [["a","a","a","a"],["a","b","b","a"],["a","b","b","a"],["a","a","a","a"]]
Output: true
Explanation: There are two valid cycles shown in different colors in the image below:

Example 2: https://leetcode.com/problems/detect-cycles-in-2d-grid/description/?envType=daily-question&envId=2026-04-26



Input: grid = [["c","c","c","a"],["c","d","c","c"],["c","c","e","c"],["f","c","c","c"]]
Output: true
Explanation: There is only one valid cycle highlighted in the image below:

Example 3:



Input: grid = [["a","b","b"],["b","z","b"],["b","b","a"]]
Output: false
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 500
grid consists only of lowercase English letters.
 * 
 */
public class Detect_Cycles_in_2D_Grid_1559 {

	public boolean isInGrid(int x, int y, int xL, int yL) {
		return x > -1 && y > -1 && x < xL && y < yL;
	}

	class Coord {
		int r;
		int c;
		int pr;
		int pc;

		public Coord(int r, int c, int pr, int pc) {
			this.r = r;
			this.c = c;
			this.pr = pr;
			this.pc = pc;
		}

		@Override
		public String toString() {
			return "Coord{" + "r=" + r + ", c=" + c + ", pr=" + pr + ", pc=" + pc + '}';
		}
	}

	public boolean isContainsCycleAt(int r, int c, char[][] grid, boolean[][] isVisited) {
		int[][] D = new int[][] { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };
		int gR = grid.length;
		int gC = grid[0].length;
		Stack<Coord> s = new Stack<>();
		s.push(new Coord(r, c, -1, -1));

		Set<String> vs = new HashSet<>();

		while (!s.isEmpty()) {
			Coord co = s.pop();
			isVisited[co.r][co.c] = true;
			vs.add(co.r + "-" + co.c);
			for (int d = 0; d < 4; d++) {
				int rD = co.r + D[d][0];
				int cD = co.c + D[d][1];

				if (isInGrid(rD, cD, gR, gC) && grid[rD][cD] == grid[r][c]) {
					if (!isVisited[rD][cD]) {
						s.push(new Coord(rD, cD, co.r, co.c));
					} else {
						if ((rD != co.pr || cD != co.pc) && vs.contains(rD + "-" + cD)) {
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	public boolean containsCycle(char[][] grid) {
		int gR = grid.length;
		int gC = grid[0].length;
		boolean[][] isVisited = new boolean[gR][gC];
		for (int r = 0; r < gR; r++) {
			for (int c = 0; c < gC; c++) {
				if (!isVisited[r][c] && isContainsCycleAt(r, c, grid, isVisited)) {
					return true;
				}
			}
		}

		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
