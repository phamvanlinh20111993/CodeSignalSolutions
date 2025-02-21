package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Url: https://leetcode.com/problems/equal-row-and-column-pairs/description/
 * 
 * Given a 0-indexed n x n integer matrix grid, return the number of pairs (ri, cj) such that row ri and column cj are equal.

A row and column pair is considered equal if they contain the same elements in the same order (i.e., an equal array).

 

Example 1:


Input: grid = [[3,2,1],[1,7,6],[2,7,7]]
Output: 1
Explanation: There is 1 equal row and column pair:
- (Row 2, Column 1): [2,7,7]
Example 2:


Input: grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
Output: 3
Explanation: There are 3 equal row and column pairs:
- (Row 0, Column 0): [3,1,2,2]
- (Row 2, Column 2): [2,4,2,2]
- (Row 3, Column 2): [2,4,2,2]
 

Constraints:

n == grid.length == grid[i].length
1 <= n <= 200
1 <= grid[i][j] <= 105
 * 
 */
public class EqualRowAndColumnPairs {

	public int equalPairs(int[][] grid) {
		Map<String, Integer> s = new HashMap<>();
		int n = grid[0].length;
		for (int i = 0; i < n; i++) {
			StringBuilder d = new StringBuilder("");
			for (int j = 0; j < n; j++) {
				d.append(grid[j][i]).append("-");
			}
			s.put(d.toString(), (!s.containsKey(d.toString()) ? 0 : s.get(d.toString())) + 1);
		}

		int c = 0;
		for (int i = 0; i < n; i++) {
			StringBuilder d = new StringBuilder("");
			for (int j = 0; j < n; j++) {
				d.append(grid[i][j]).append("-");
			}
			if (s.containsKey(d.toString()))
				c += s.get(d.toString());
		}

		return c;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
