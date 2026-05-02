package LeetCode;

import java.util.Arrays;

/**
 * url:
 * https://leetcode.com/problems/minimum-operations-to-make-a-uni-value-grid/description/?envType=daily-question&envId=2026-04-28
 * 
 * 
 * You are given a 2D integer grid of size m x n and an integer x. In one
 * operation, you can add x to or subtract x from any element in the grid.
 * 
 * A uni-value grid is a grid where all the elements of it are equal.
 * 
 * Return the minimum number of operations to make the grid uni-value. If it is
 * not possible, return -1.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[2,4],[6,8]], x = 2 Output: 4 Explanation: We can make every
 * element equal to 4 by doing the following: - Add x to 2 once. - Subtract x
 * from 6 once. - Subtract x from 8 twice. A total of 4 operations were used.
 * Example 2:
 * 
 * 
 * Input: grid = [[1,5],[2,3]], x = 1 Output: 5 Explanation: We can make every
 * element equal to 3. Example 3:
 * 
 * 
 * Input: grid = [[1,2],[3,4]], x = 2 Output: -1 Explanation: It is impossible
 * to make every element equal.
 * 
 * 
 * Constraints:
 * 
 * m == grid.length n == grid[i].length 1 <= m, n <= 105 1 <= m * n <= 105 1 <=
 * x, grid[i][j] <= 104
 */
public class Minimum_Operations_to_Make_a_Uni_Value_Grid_2033 {

	public int minOperations(int[][] grid, int x) {
		int[] gridN = new int[grid.length * grid[0].length];
		int ind = 0;
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[0].length; c++) {
				gridN[ind++] = grid[r][c];
			}
		}

		Arrays.sort(gridN);

		for (ind = 1; ind < gridN.length; ind++) {
			if ((gridN[ind] - gridN[ind - 1]) % x != 0) {
				return -1;
			}
		}

		int medianN = gridN[gridN.length / 2];
		int res = 0;
		for (ind = 0; ind < gridN.length; ind++) {
			res += (int) Math.abs(medianN - gridN[ind]) / x;
		}

		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
