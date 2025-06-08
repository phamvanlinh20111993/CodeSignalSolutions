package LeetCode;

/**
 * url: https://leetcode.com/problems/minimum-path-sum/description/ 	
 * 
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

 

Example 1:


Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
Output: 7
Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
Example 2:

Input: grid = [[1,2,3],[4,5,6]]
Output: 12
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 200
0 <= grid[i][j] <= 200

 */
public class MinimumPathSum {
	
	int[][] s;

	// s[i][j] = Math.min(s[i-1][j], s[i][j-1]) + grid[i][j]
	// s[0][0] = grid[0][0]
	public int memorize(int[][] grid, int i, int j) {
		if (i < 0 || j < 0) {
			return 1000000;
		}
		if (i == 0 && j == 0) {
			return grid[0][0];
		}
		if (s[i][j] != -1) {
			return s[i][j];
		}

		int l = memorize(grid, i - 1, j);
		int r = memorize(grid, i, j - 1);
		int m = Math.min(l, r);

		s[i][j] = m + grid[i][j];
		return s[i][j];
	}

	public int minPathSum(int[][] grid) {

		int r = grid.length, c = grid[0].length;

		s = new int[r][c];

		for (int ind = 0; ind < r; ind++) {
			for (int i = 0; i < c; i++) {
				s[ind][i] = -1;
			}
		}

		memorize(grid, r - 1, c - 1);

		return s[r - 1][c - 1] == -1 ? grid[0][0] : s[r - 1][c - 1];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
