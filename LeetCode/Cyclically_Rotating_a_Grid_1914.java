package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * url: https://leetcode.com/problems/cyclically-rotating-a-grid/description/?envType=daily-question&envId=2026-05-09
 * 
 * You are given an m x n integer matrix grid​​​, where m and n are both even integers, and an integer k.

The matrix is composed of several layers, which is shown in the below image, where each color is its own layer:



A cyclic rotation of the matrix is done by cyclically rotating each layer in the matrix. To cyclically rotate a layer once, each element in the layer will take the place of the adjacent element in the counter-clockwise direction. An example rotation is shown below:


Return the matrix after applying k cyclic rotations to it.

 

Example 1:


Input: grid = [[40,10],[30,20]], k = 1
Output: [[10,20],[40,30]]
Explanation: The figures above represent the grid at every state.
Example 2:


Input: grid = [[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]], k = 2
Output: [[3,4,8,12],[2,11,10,16],[1,7,6,15],[5,9,13,14]]
Explanation: The figures above represent the grid at every state.
 

Constraints:

m == grid.length
n == grid[i].length
2 <= m, n <= 50
Both m and n are even integers.
1 <= grid[i][j] <= 5000
1 <= k <= 109
 */
public class Cyclically_Rotating_a_Grid_1914 {

	public void buildEachRateGrid(int[][] grid, int tLR, int tLC, int bRR, int bRC) {
		// left: up to down
		int tmp0 = grid[bRR][tLC];
		for (int r = bRR; r > tLR; r--) {
			grid[r][tLC] = grid[r - 1][tLC];
		}
		grid[tLR][tLC] = grid[tLR][tLC + 1];

		// bottom: left to right
		int tmp = grid[bRR][bRC];
		for (int c = bRC; c > tLC; c--) {
			grid[bRR][c] = grid[bRR][c - 1];
		}
		grid[bRR][tLC + 1] = tmp0;

		// right: down to up
		int tmp1 = grid[tLR][bRC];
		for (int r = tLR; r < bRR; r++) {
			grid[r][bRC] = grid[r + 1][bRC];
		}
		grid[bRR - 1][bRC] = tmp;

		// top: right to left
		for (int c = tLC; c < bRC; c++) {
			grid[tLR][c] = grid[tLR][c + 1];
		}
		grid[tLR][bRC - 1] = tmp1;
	}

	public int[][] rotateGrid(int[][] grid, int k) {
		int tLR = 0;
		int bRR = grid.length - 1;
		int tLC = 0;
		int bRC = grid[0].length - 1;

		List<Integer[]> childGridL = new ArrayList<>();
		while (tLC < bRC && tLR < bRR) {
			// y,x and y1, x1
			childGridL.add(new Integer[] { tLR, tLC, bRR, bRC });
			bRR--;
			tLC++;
			bRC--;
			tLR++;
		}

		for (int ind = 0; ind < childGridL.size(); ind++) {
			int h = 1 + childGridL.get(ind)[2] - childGridL.get(ind)[0];
			int w = 1 + childGridL.get(ind)[3] - childGridL.get(ind)[1];
			int tmp = 2 * (h + w) - 4;

			int lk = k % tmp;
			while (lk > 0) {
				buildEachRateGrid(grid, childGridL.get(ind)[0], childGridL.get(ind)[1], childGridL.get(ind)[2],
						childGridL.get(ind)[3]);
				lk--;
			}
		}

		return grid;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
