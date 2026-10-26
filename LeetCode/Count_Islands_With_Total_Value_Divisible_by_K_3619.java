package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * url: https://leetcode.com/problems/count-islands-with-total-value-divisible-by-k/description/?envType=daily-question&envId=2026-10-26
 * 
 * You are given an m x n matrix grid and a positive integer k. An island is a group of positive integers (representing land) that are 4-directionally connected (horizontally or vertically).

The total value of an island is the sum of the values of all cells in the island.

Return the number of islands with a total value divisible by k.

 

Example 1:


Input: grid = [[0,2,1,0,0],[0,5,0,0,5],[0,0,1,0,0],[0,1,4,7,0],[0,2,0,0,8]], k = 5

Output: 2

Explanation:

The grid contains four islands. The islands highlighted in blue have a total value that is divisible by 5, while the islands highlighted in red do not.

Example 2:


Input: grid = [[3,0,3,0], [0,3,0,3], [3,0,3,0]], k = 3

Output: 6

Explanation:

The grid contains six islands, each with a total value that is divisible by 3.

 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 1000
1 <= m * n <= 105
0 <= grid[i][j] <= 106
1 <= k <= 106
 */
public class Count_Islands_With_Total_Value_Divisible_by_K_3619 {

	static int[][] D = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	private boolean isInGrid(int x, int y, int gx, int gy) {
		return x > -1 && x < gx && y > -1 && y < gy;
	}

	public long bfs(int x, int y, int[][] grid, boolean[][] isVisited) {
		long sum = 0;
		Queue<Integer[]> q = new LinkedList<>();
		int gY = grid.length;
		int gX = grid[0].length;

		q.offer(new Integer[] { x, y });
		isVisited[y][x] = true;

		while (!q.isEmpty()) {
			Integer[] c = q.poll();
			sum += grid[c[1]][c[0]];
			for (int[] d : D) {
				int xT = d[0] + c[0];
				int yT = d[1] + c[1];
				// System.out.println("xT=" + xT+", yT=" + yT + ", inGrid=" + isInGrid(xT, yT,
				// gX, gY));
				if (isInGrid(xT, yT, gX, gY) && !isVisited[yT][xT] && grid[yT][xT] > 0) {
					isVisited[yT][xT] = true;
					q.offer(new Integer[] { xT, yT });
				}
			}
		}

		// System.out.println("sum=" + sum);

		return sum;
	}

	public int countIslands(int[][] grid, int k) {
		int gy = grid.length;
		int gx = grid[0].length;

		boolean[][] isVisited = new boolean[gy][gx];

		int res = 0;
		for (int y = 0; y < gy; y++) {
			for (int x = 0; x < gx; x++) {
				if (grid[y][x] > 0 && isVisited[y][x] == false) {
					long s = bfs(x, y, grid, isVisited);
					res += s % k == 0 ? 1 : 0;
				}
			}
		}

		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
