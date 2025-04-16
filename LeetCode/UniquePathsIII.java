package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * url: https://leetcode.com/problems/unique-paths-iii/description/?envType=problem-list-v2&envId=backtracking
 * ou are given an m x n integer array grid where grid[i][j] could be:

1 representing the starting square. There is exactly one starting square.
2 representing the ending square. There is exactly one ending square.
0 representing empty squares we can walk over.
-1 representing obstacles that we cannot walk over.
Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.

 

Example 1:


Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
Output: 2
Explanation: We have the following two paths: 
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
Example 2:


Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
Output: 4
Explanation: We have the following four paths: 
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
Example 3:


Input: grid = [[0,1],[2,0]]
Output: 0
Explanation: There is no path that walks over every empty square exactly once.
Note that the starting and ending square can be anywhere in the grid.
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 20
1 <= m * n <= 20
-1 <= grid[i][j] <= 2
There is exactly one starting cell and one ending cell.
 */
public class UniquePathsIII {
	int count;

	public boolean isInGrid(int x, int y, int gridX, int gridY) {
		return x > -1 && y > -1 && x < gridX && y < gridY;
	}

	public void backtracking(int[][] grid, int[][] isVisit, int x, int y, final int max, int amount) {
		if (amount == max && grid[y][x] == 2) {
			count++;
			return;
		}
		int[][] d = { { 0, -1 }, { 0, 1 }, { 1, 0 }, { -1, 0 } };
		for (int k = 0; k < 4; k++) {
			int nX = d[k][0] + x;
			int nY = d[k][1] + y;
			if (isInGrid(nX, nY, grid[0].length, grid.length) && isVisit[nY][nX] == 0 && grid[nY][nX] != -1) {
				isVisit[nY][nX] = 1;
				backtracking(grid, isVisit, nX, nY, max, amount + 1);
				isVisit[nY][nX] = 0;
			}
		}

	}

	public int uniquePathsIII(int[][] grid) {
		count = 0;
		int maxAmountWalk = 0;

		int sX = 0, sY = 0;
		for (int y = 0; y < grid.length; y++) {
			for (int x = 0; x < grid[0].length; x++) {
				if (grid[y][x] != -1) {
					maxAmountWalk++;
				}
				if (grid[y][x] == 1) {
					sX = x;
					sY = y;
				}
			}
		}

		int[][] isVisit = new int[grid.length][grid[0].length];

		isVisit[sY][sX] = 1;
		backtracking(grid, isVisit, sX, sY, maxAmountWalk, 1);

		return count;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
