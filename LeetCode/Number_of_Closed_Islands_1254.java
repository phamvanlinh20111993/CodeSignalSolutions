package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * url: https://leetcode.com/problems/number-of-closed-islands/description/?envType=problem-list-v2&envId=union-find
 * 
 * Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.

Return the number of closed islands.

 

Example 1:



Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
Output: 2
Explanation: 
Islands in gray are closed because they are completely surrounded by water (group of 1s).
Example 2:



Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
Output: 1
Example 3:

Input: grid = [[1,1,1,1,1,1,1],
               [1,0,0,0,0,0,1],
               [1,0,1,1,1,0,1],
               [1,0,1,0,1,0,1],
               [1,0,1,1,1,0,1],
               [1,0,0,0,0,0,1],
               [1,1,1,1,1,1,1]]
Output: 2
 

Constraints:

1 <= grid.length, grid[0].length <= 100
0 <= grid[i][j] <=1

 */
public class Number_of_Closed_Islands_1254 {

	record C(int r, int c) {
	}

	int[][] D = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public boolean isInGrid(int r, int c, int rLG, int cLG) {
		return r > -1 && r < rLG && c > -1 && c < cLG;
	}

	public boolean bfsValidCloseIsland(int r, int c, int[][] grid, boolean[][] isVisited) {
		Queue<C> s = new LinkedList<>();
		s.add(new C(r, c));
		// System.out.println("data " + new C(r, c));
		int rLG = grid.length;
		int cLG = grid[0].length;
		isVisited[r][c] = true;

		boolean isInClosedIslands = true;
		while (!s.isEmpty()) {
			C co = s.poll();
			for (int[] t : D) {
				int rN = co.r + t[0];
				int cN = co.c + t[1];
				if (!isInGrid(rN, cN, rLG, cLG)) {
					isInClosedIslands = false;
					continue;
				}

				if (!isVisited[rN][cN] && grid[rN][cN] == 0) {
					isVisited[rN][cN] = true;
					s.add(new C(rN, cN));
				}
			}
		}

		return isInClosedIslands;
	}

	public int closedIsland(int[][] grid) {
		boolean[][] isVisited = new boolean[grid.length][grid[0].length];
		int res = 0;

		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[0].length; c++) {
				if (!isVisited[r][c] && grid[r][c] == 0 && bfsValidCloseIsland(r, c, grid, isVisited)) {
					res++;
				}
			}
		}

		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
