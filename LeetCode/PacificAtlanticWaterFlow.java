package LeetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;


/**
 * url: https://leetcode.com/problems/pacific-atlantic-water-flow/description/?envType=daily-question&envId=2025-10-05
 * 
 * There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.

The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).

The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.

Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.

 

Example 1:


Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
Explanation: The following cells can flow to the Pacific and Atlantic oceans, as shown below:
[0,4]: [0,4] -> Pacific Ocean 
       [0,4] -> Atlantic Ocean
[1,3]: [1,3] -> [0,3] -> Pacific Ocean 
       [1,3] -> [1,4] -> Atlantic Ocean
[1,4]: [1,4] -> [1,3] -> [0,3] -> Pacific Ocean 
       [1,4] -> Atlantic Ocean
[2,2]: [2,2] -> [1,2] -> [0,2] -> Pacific Ocean 
       [2,2] -> [2,3] -> [2,4] -> Atlantic Ocean
[3,0]: [3,0] -> Pacific Ocean 
       [3,0] -> [4,0] -> Atlantic Ocean
[3,1]: [3,1] -> [3,0] -> Pacific Ocean 
       [3,1] -> [4,1] -> Atlantic Ocean
[4,0]: [4,0] -> Pacific Ocean 
       [4,0] -> Atlantic Ocean
Note that there are other possible paths for these cells to flow to the Pacific and Atlantic oceans.
Example 2:

Input: heights = [[1]]
Output: [[0,0]]
Explanation: The water can flow from the only cell to the Pacific and Atlantic oceans.
 

Constraints:

m == heights.length
n == heights[r].length
1 <= m, n <= 200
0 <= heights[r][c] <= 105
 * 
 */
public class PacificAtlanticWaterFlow {

	public record P(int x, int y) {
		public String key() {
			return this.x + " " + y;
		}
	}

	public boolean isInBoard(int x, int y, int hX, int hY) {
		return x > -1 && x < hX && y > -1 && y < hY;
	}

	public boolean canFlowBothOcean(int x, int y, int[][] heights, Set<String> pacificOcean,
			Set<String> atlanticOcean) {
		final int[][] D = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
		int hX = heights[0].length, hY = heights.length;
		boolean[][] isVisited = new boolean[hY][hX];
		Stack<P> stack = new Stack<>();

		P point = new P(x, y);
		stack.push(point);

		boolean isInAtlanticOcean = false;
		boolean isInPacificOcean = false;

		while (!stack.isEmpty()) {
			P p = stack.pop();
			if (!isInAtlanticOcean && atlanticOcean.contains(p.key())) {
				isInAtlanticOcean = true;
			}
			if (!isInPacificOcean && pacificOcean.contains(p.key())) {
				isInPacificOcean = true;
			}
			if (isInAtlanticOcean && isInPacificOcean) {
				return true;
			}

			isVisited[p.y][p.x] = true;

			for (int[] c : D) {
				int xT = c[0] + p.x;
				int yT = c[1] + p.y;
				if (isInBoard(xT, yT, hX, hY) && !isVisited[yT][xT] && heights[p.y][p.x] >= heights[yT][xT]) {
					stack.push(new P(xT, yT));
				}
			}
		}

		return false;
	}

	public List<List<Integer>> pacificAtlantic(int[][] heights) {
		List<List<Integer>> res = new ArrayList<>();
		int hX = heights[0].length, hY = heights.length;
		Set<String> pacificOcean = new HashSet<>();
		Set<String> atlanticOcean = new HashSet<>();

		for (int y = 0; y < hY; y++) {
			pacificOcean.add(0 + " " + y);
			atlanticOcean.add((hX - 1) + " " + y);
		}
		for (int x = 0; x < hX; x++) {
			pacificOcean.add(x + " " + 0);
			atlanticOcean.add(x + " " + (hY - 1));
		}

		for (int y = 0; y < hY; y++) {
			for (int x = 0; x < hX; x++) {
				if (canFlowBothOcean(x, y, heights, pacificOcean, atlanticOcean)) {
					res.add(List.of(y, x));
				}
			}
		}

		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
