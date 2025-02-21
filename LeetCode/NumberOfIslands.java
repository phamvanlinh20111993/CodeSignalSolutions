package LeetCode;

import java.util.Stack;

/**
 * Url: https://leetcode.com/problems/number-of-islands/description/
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

 

Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
Example 2:

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'.
 */
public class NumberOfIslands {
	
	public boolean isInGrid(int x, int y, int gX, int gY) {
		return x > -1 && y > -1 && x < gX && y < gY;
	}

	public char[][] dfs(char[][] grid, int x, int y) {
		Stack<Integer[]> stack = new Stack<>();
		stack.push(new Integer[] { x, y });
		int gX = grid.length, gY = grid[0].length;

		while (!stack.empty()) {
			Integer[] d = stack.pop();
			grid[d[0]][d[1]] = '-';

			if (isInGrid(d[0] + 1, d[1], gX, gY) && grid[d[0] + 1][d[1]] == '1') {
				stack.push(new Integer[] { d[0] + 1, d[1] });
			}
			if (isInGrid(d[0] - 1, d[1], gX, gY) && grid[d[0] - 1][d[1]] == '1') {
				stack.push(new Integer[] { d[0] - 1, d[1] });
			}
			if (isInGrid(d[0], d[1] + 1, gX, gY) && grid[d[0]][d[1] + 1] == '1') {
				stack.push(new Integer[] { d[0], d[1] + 1 });
			}
			if (isInGrid(d[0], d[1] - 1, gX, gY) && grid[d[0]][d[1] - 1] == '1') {
				stack.push(new Integer[] { d[0], d[1] - 1 });
			}
		}

		// for(int i = 0; i < grid.length; i++){
		// for(int j = 0; j < grid[0].length; j++){
		// System.out.print(grid[i][j] + " ");
		// }
		// System.out.println();
		// }

		return grid;
	}

	public int numIslands(char[][] grid) {
		int amount = 0;
		for (int y = 0; y < grid.length; y++) {
			for (int x = 0; x < grid[0].length; x++) {
				if (grid[y][x] == '1') {
					amount++;
					grid = dfs(grid, y, x);
				}
			}
		}

		return amount;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
