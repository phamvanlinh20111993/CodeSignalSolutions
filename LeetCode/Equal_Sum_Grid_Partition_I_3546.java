package LeetCode;

/**
 * url: https://leetcode.com/problems/equal-sum-grid-partition-i/description/?envType=daily-question&envId=2026-03-25
 * 
 * You are given an m x n matrix grid of positive integers. Your task is to determine if it is possible to make either one horizontal or one vertical cut on the grid such that:

Each of the two resulting sections formed by the cut is non-empty.
The sum of the elements in both sections is equal.
Return true if such a partition exists; otherwise return false.

 

Example 1:

Input: grid = [[1,4],[2,3]]

Output: true

Explanation:



A horizontal cut between row 0 and row 1 results in two non-empty sections, each with a sum of 5. Thus, the answer is true.

Example 2:

Input: grid = [[1,3],[2,4]]

Output: false

Explanation:

No horizontal or vertical cut results in two non-empty sections with equal sums. Thus, the answer is false.

 

Constraints:

1 <= m == grid.length <= 105
1 <= n == grid[i].length <= 105
2 <= m * n <= 105
1 <= grid[i][j] <= 105
 * 
 */

public class Equal_Sum_Grid_Partition_I_3546 {

	public boolean canPartitionGrid(int[][] grid) {
		int rL = grid.length;
		int cL = grid[0].length;

		long[] prefixSumRow = new long[rL];
		for (int r = 0; r < rL; r++) {
			long s = 0;
			for (int c = 0; c < cL; c++) {
				s += grid[r][c];
			}
			if (r == 0) {
				prefixSumRow[0] = s;
			} else {
				prefixSumRow[r] = s + prefixSumRow[r - 1];
			}
		}

		for (int i = 0; i < rL; i++) {
			if (prefixSumRow[rL - 1] - prefixSumRow[i] == prefixSumRow[i]) {
				return true;
			}
		}

		long[] prefixSumCol = new long[cL];
		for (int c = 0; c < cL; c++) {
			long s = 0;
			for (int r = 0; r < rL; r++) {
				s += grid[r][c];
			}
			if (c == 0) {
				prefixSumCol[0] = s;
			} else {
				prefixSumCol[c] = s + prefixSumCol[c - 1];
			}
		}

		for (int i = 0; i < cL; i++) {
			if (prefixSumCol[cL - 1] - prefixSumCol[i] == prefixSumCol[i]) {
				return true;
			}
		}

		return false;
	}

	public static void main(String[] args) {
		
	}

}
