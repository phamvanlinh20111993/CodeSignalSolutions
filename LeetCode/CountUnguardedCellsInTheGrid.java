package LeetCode;

import java.util.HashSet;
import java.util.Set;

/**
 * Url:
 * https://leetcode.com/problems/count-unguarded-cells-in-the-grid/description/?envType=daily-question&envId=2024-11-21
 * 
 * You are given two integers m and n representing a 0-indexed m x n grid. You
 * are also given two 2D integer arrays guards and walls where guards[i] =
 * [rowi, coli] and walls[j] = [rowj, colj] represent the positions of the ith
 * guard and jth wall respectively.
 * 
 * A guard can see every cell in the four cardinal directions (north, east,
 * south, or west) starting from their position unless obstructed by a wall or
 * another guard. A cell is guarded if there is at least one guard that can see
 * it.
 * 
 * Return the number of unoccupied cells that are not guarded.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: m = 4, n = 6, guards = [[0,0],[1,1],[2,3]], walls =
 * [[0,1],[2,2],[1,4]] Output: 7 Explanation: The guarded and unguarded cells
 * are shown in red and green respectively in the above diagram. There are a
 * total of 7 unguarded cells, so we return 7. Example 2:
 * 
 * 
 * Input: m = 3, n = 3, guards = [[1,1]], walls = [[0,1],[1,0],[2,1],[1,2]]
 * Output: 4 Explanation: The unguarded cells are shown in green in the above
 * diagram. There are a total of 4 unguarded cells, so we return 4.
 * 
 * 
 * Constraints:
 * 
 * 1 <= m, n <= 105 2 <= m * n <= 105 1 <= guards.length, walls.length <= 5 *
 * 104 2 <= guards.length + walls.length <= m * n guards[i].length ==
 * walls[j].length == 2 0 <= rowi, rowj < m 0 <= coli, colj < n All the
 * positions in guards and walls are unique.
 * 
 */
public class CountUnguardedCellsInTheGrid {

	public boolean canMoveNextCell(int x, int y, int m, int n, Set<String> walls) {
		return !walls.contains(x + "-" + y) && x > -1 && y > -1 && x < m && y < n;
	}

	public Set<String> getOccupationCell(int x, int y, int m, int n, Set<String> wallSets) {
		int[][] DIRECTIONS = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
		Set<String> occupationCell = new HashSet<>();

		for (int ind = 0; ind < DIRECTIONS.length; ind++) {
			int nX = x + DIRECTIONS[ind][0];
			int nY = y + DIRECTIONS[ind][1];
			while (canMoveNextCell(nX, nY, m, n, wallSets) && !occupationCell.contains(nX + "-" + nY)) {
				occupationCell.add(nX + "-" + nY);
				nX += DIRECTIONS[ind][0];
				nY += DIRECTIONS[ind][1];
			}
		}
		return occupationCell;
	}

	public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {

		Set<String> wallSets = new HashSet<>();

		for (int ind = 0; ind < walls.length; ind++) {
			wallSets.add(walls[ind][0] + "-" + walls[ind][1]);
		}

		for (int ind = 0; ind < guards.length; ind++) {
			wallSets.add(guards[ind][0] + "-" + guards[ind][1]);
		}

		Set<String> occupationCell = new HashSet<>();
		for (int ind = 0; ind < guards.length; ind++) {
			occupationCell.addAll(getOccupationCell(guards[ind][0], guards[ind][1], m, n, wallSets));
		}

		return m * n - wallSets.size() - occupationCell.size();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
