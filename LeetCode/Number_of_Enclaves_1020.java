package LeetCode;

import java.util.Stack;

/**
 * url: https://leetcode.com/problems/number-of-enclaves/description/?envType=problem-list-v2&envId=union-find
 * 
 * You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.

A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.

Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.

 

Example 1:


Input: grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
Output: 3
Explanation: There are three 1s that are enclosed by 0s, and one 1 that is not enclosed because its on the boundary.
Example 2:


Input: grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
Output: 0
Explanation: All 1s are either on the boundary or can reach the boundary.
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 500
grid[i][j] is either 0 or 1.
 */
public class Number_of_Enclaves_1020 {

	record C(int r, int c) {
	}

	int[][] D = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public boolean isInGrid(int r, int c, int rLG, int cLG) {
		return r > -1 && r < rLG && c > -1 && c < cLG;
	}

	public boolean isNotReachBoundaryGrid(int r, int c, int rLG, int cLG) {
		return r > 0 && r < rLG - 1 && c > 0 && c < cLG - 1;
	}

	public int canWalkOffBoundaryGrid(int r, int c, int[][] grid, boolean[][] isVisited) {
		Stack<C> s = new Stack<>();
		s.push(new C(r, c));
		int rLG = grid.length;
		int cLG = grid[0].length;
		int amount = 0;
		boolean isInBoundary = false;
		isVisited[r][c] = true;

		while (!s.isEmpty()) {
			C co = s.pop();
			if (isNotReachBoundaryGrid(co.r, co.c, rLG, cLG)) {
				amount++;
			} else {
				isInBoundary = true;
			}

			for (int[] t : D) {
				int rN = co.r + t[0];
				int cN = co.c + t[1];

				if (!isInGrid(rN, cN, rLG, cLG))
					continue;

				if (!isVisited[rN][cN] && grid[rN][cN] == 1) {
					isVisited[rN][cN] = true;
					s.push(new C(rN, cN));
				}
			}
		}

		return isInBoundary ? 0 : amount;
	}

	public int numEnclaves(int[][] grid) {
		boolean[][] isVisited = new boolean[grid.length][grid[0].length];
		int res = 0;
		for (int r = 1; r < grid.length - 1; r++) {
			for (int c = 1; c < grid[0].length - 1; c++) {
				if (!isVisited[r][c] && grid[r][c] == 1) {
					int amount = canWalkOffBoundaryGrid(r, c, grid, isVisited);
					// System.out.println("amount = " + amount);
					res += amount;
				}
			}
		}

		return res;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
