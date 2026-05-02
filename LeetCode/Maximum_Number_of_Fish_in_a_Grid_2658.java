package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * url: https://leetcode.com/problems/maximum-number-of-fish-in-a-grid/description/?envType=problem-list-v2&envId=union-find
 * 
 * You are given a 0-indexed 2D matrix grid of size m x n, where (r, c) represents:

A land cell if grid[r][c] = 0, or
A water cell containing grid[r][c] fish, if grid[r][c] > 0.
A fisher can start at any water cell (r, c) and can do the following operations any number of times:

Catch all the fish at cell (r, c), or
Move to any adjacent water cell.
Return the maximum number of fish the fisher can catch if he chooses his starting cell optimally, or 0 if no water cell exists.

An adjacent cell of the cell (r, c), is one of the cells (r, c + 1), (r, c - 1), (r + 1, c) or (r - 1, c) if it exists.

 

Example 1:


Input: grid = [[0,2,1,0],[4,0,0,3],[1,0,0,4],[0,3,2,0]]
Output: 7
Explanation: The fisher can start at cell (1,3) and collect 3 fish, then move to cell (2,3) and collect 4 fish.
Example 2:


Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,1]]
Output: 1
Explanation: The fisher can start at cells (0,0) or (3,3) and collect a single fish. 
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 10
0 <= grid[i][j] <= 10
 */
public class Maximum_Number_of_Fish_in_a_Grid_2658 {

	record C(int r, int c) {
	}

	int[][] D = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public boolean isInGrid(int r, int c, int rLG, int cLG) {
		return r > -1 && r < rLG && c > -1 && c < cLG;
	}

	public int bfs(int r, int c, int[][] grid, boolean[][] isVisited) {
		Queue<C> s = new LinkedList<>();
		s.add(new C(r, c));
		int rLG = grid.length;
		int cLG = grid[0].length;
		isVisited[r][c] = true;

		int res = 0;
		while (!s.isEmpty()) {
			C co = s.poll();
			res += grid[co.r][co.c];

			for (int[] t : D) {
				int rN = co.r + t[0];
				int cN = co.c + t[1];
				if (!isInGrid(rN, cN, rLG, cLG)) {
					continue;
				}
				if (!isVisited[rN][cN] && grid[rN][cN] > 0) {
					isVisited[rN][cN] = true;
					s.add(new C(rN, cN));
				}
			}
		}

		return res;
	}

	public int findMaxFish(int[][] grid) {
		boolean[][] isVisited = new boolean[grid.length][grid[0].length];
		int res = 0;
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[0].length; c++) {
				if (!isVisited[r][c] && grid[r][c] > 0) {
					int amount = bfs(r, c, grid, isVisited);
					res = Math.max(amount, res);
				}
			}
		}

		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
