package LeetCode;

/**
 * url: https://leetcode.com/problems/max-area-of-island/?envType=problem-list-v2&envId=depth-first-search
 * 
 * You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

The area of an island is the number of cells with a value 1 in the island.

Return the maximum area of an island in grid. If there is no island, return 0.

 

Example 1:


Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
Output: 6
Explanation: The answer is not 11, because the island must be connected 4-directionally.
Example 2:

Input: grid = [[0,0,0,0,0,0,0,0]]
Output: 0
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 50
grid[i][j] is either 0 or 1.
 */
public class MaxAreaofIsland {

	public boolean isInMatrix(int x, int y, int r, int c) {
		return y < r && x < c && x > -1 && y > -1;
	}

	public int dfs(int x, int y, int[][] grid, boolean[][] isVisit) {
		int count = 1;
		int[][] d = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
		isVisit[y][x] = true;
		for (int ind = 0; ind < d.length; ind++) {
			int dx = d[ind][0] + x;
			int dy = d[ind][1] + y;
			if (isInMatrix(dx, dy, grid.length, grid[0].length) && isVisit[dy][dx] == false && grid[dy][dx] == 1) {
				count += dfs(dx, dy, grid, isVisit);
			}
		}

		return count;
	}

	public int maxAreaOfIsland(int[][] grid) {
		int cM = 0;
		boolean[][] isVisit = new boolean[grid.length][grid[0].length];
		for (int y = 0; y < grid.length; y++) {
			for (int x = 0; x < grid[0].length; x++) {
				if (grid[y][x] == 1 && isVisit[y][x] == false) {
					cM = Math.max(dfs(x, y, grid, isVisit), cM);
				}
			}
		}

		return cM;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
