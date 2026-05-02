package LeetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * url: https://leetcode.com/problems/count-sub-islands/description/?envType=problem-list-v2&envId=union-find
 * 
 * You are given two m x n binary matrices grid1 and grid2 containing only 0's (representing water) and 1's (representing land). An island is a group of 1's connected 4-directionally (horizontal or vertical). Any cells outside of the grid are considered water cells.

An island in grid2 is considered a sub-island if there is an island in grid1 that contains all the cells that make up this island in grid2.

Return the number of islands in grid2 that are considered sub-islands.

 

Example 1:


Input: grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
Output: 3
Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
The 1s colored red in grid2 are those considered to be part of a sub-island. There are three sub-islands.
Example 2:


Input: grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
Output: 2 
Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
The 1s colored red in grid2 are those considered to be part of a sub-island. There are two sub-islands.
 

Constraints:

m == grid1.length == grid2.length
n == grid1[i].length == grid2[i].length
1 <= m, n <= 500
grid1[i][j] and grid2[i][j] are either 0 or 1.
 */

public class Count_Sub_Islands_1905 {

	record C(int r, int c) {
	}

	int[][] D = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public boolean isInGrid(int r, int c, int rLG, int cLG) {
		return r > -1 && r < rLG && c > -1 && c < cLG;
	}

	public void dfsUnion(int r, int c, int[][] grid, boolean[] isVisited, int[] group) {
		int rLG = grid.length;
		int cLG = grid[0].length;
		isVisited[r * cLG + c] = true;

		for (int[] t : D) {
			int rN = r + t[0];
			int cN = c + t[1];

			if (!isInGrid(rN, cN, rLG, cLG))
				continue;
			// System.out.println("data " + rN + "," + cN);

			if (!isVisited[rN * cLG + cN] && grid[rN][cN] == 1) {
				group[rN * cLG + cN] = r * cLG + c;
				dfsUnion(rN, cN, grid, isVisited, group);
			}
		}
	}

	public int findRoot(int[] unionGroup, int element) {
		while (unionGroup[element] > -1) {
			element = unionGroup[element];
		}
		return element;
	}

	public boolean isBelong(int r, int c, int[][] grid, boolean[] isVisited, int[] unionGroup) {
		Queue<C> s = new LinkedList<>();
		s.add(new C(r, c));
		int rLG = grid.length;
		int cLG = grid[0].length;
		isVisited[r * cLG + c] = true;

		List<Integer> sameGroup = new ArrayList<>();
		while (!s.isEmpty()) {
			C co = s.poll();
			sameGroup.add(co.r * cLG + co.c);
			for (int[] t : D) {
				int rN = co.r + t[0];
				int cN = co.c + t[1];

				if (!isInGrid(rN, cN, rLG, cLG))
					continue;

				if (!isVisited[rN * cLG + cN] && grid[rN][cN] == 1) {
					isVisited[rN * cLG + cN] = true;
					s.add(new C(rN, cN));
				}
			}
		}

		System.out.println(sameGroup);
		for (int e : sameGroup) {
			// System.out.println("data " + findRoot(unionGroup, e));
			int parent = findRoot(unionGroup, e);
			if (parent == e && unionGroup[e] == -1)
				return false;
		}

		return true;
	}

	public int countSubIslands(int[][] grid1, int[][] grid2) {
		int gRL = grid1.length;
		int gCL = grid1[0].length;
		int[] unionGroup = new int[gRL * gCL];
		boolean[] isVisited1 = new boolean[gRL * gCL];

		for (int ind = 0; ind < unionGroup.length; ind++) {
			unionGroup[ind] = -1;
		}

		for (int r = 0; r < gRL; r++) {
			for (int c = 0; c < gCL; c++) {
				if (!isVisited1[r * gCL + c] && grid1[r][c] == 1) {
					unionGroup[r * gCL + c] = -10;
					dfsUnion(r, c, grid1, isVisited1, unionGroup);
				}
			}
		}

		// for(int ind = 0; ind < unionGroup.length; ind++){
		// System.out.print(ind+":"+unionGroup[ind]+", ");
		// }
		// System.out.println();

		int res = 0;
		boolean[] isVisited2 = new boolean[gRL * gCL];
		for (int r = 0; r < gRL; r++) {
			for (int c = 0; c < gCL; c++) {
				if (!isVisited2[r * gCL + c] && grid2[r][c] == 1) {
					// System.out.println("rc="+r+", " + c);
					if (isBelong(r, c, grid2, isVisited2, unionGroup)) {
						res++;
					}
				}
			}
		}

		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
