package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * url: https://leetcode.com/problems/diagonal-traverse/description/?envType=daily-question&envId=2025-08-25
 * 
 * Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.

 

Example 1:


Input: mat = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,4,7,5,3,6,8,9]
Example 2:

Input: mat = [[1,2],[3,4]]
Output: [1,2,3,4]
 

Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 104
1 <= m * n <= 104
-105 <= mat[i][j] <= 105
 */
public class DiagonalTraverse {
	public List<Integer> up(int x, int y, int[][] mat) {
		List<Integer> res = new ArrayList<>();
		int xL = mat[0].length;

		while (x < xL && y > -1) {
			res.add(mat[y][x]);
			x++;
			y--;
		}

		return res;
	}

	public List<Integer> down(int x, int y, int[][] mat) {
		List<Integer> res = new ArrayList<>();
		int yL = mat.length;

		while (x > -1 && y < yL) {
			res.add(mat[y][x]);
			x--;
			y++;
		}

		return res;
	}

	record DT(int x, int y) {
	}

	public int[] findDiagonalOrder(int[][] mat) {
		int x = 0, y = 0, xL = mat[0].length, yL = mat.length;
		List<Integer> re = new ArrayList<>();

		List<DT> downD = new ArrayList<>();
		while (true) {
			downD.add(new DT(x, y));
			if (x < xL - 1) {
				x++;
				continue;
			}
			if (x == xL - 1) {
				y++;
			}
			if (y == yL)
				break;
		}

		int x1 = 0, y1 = 0;
		List<DT> upD = new ArrayList<>();
		while (true) {
			upD.add(new DT(x1, y1));
			if (y1 < yL - 1) {
				y1++;
				continue;
			}
			if (y1 == yL - 1) {
				x1++;
			}
			if (x1 == xL)
				break;
		}

		for (int ind = 0; ind < upD.size(); ind++) {
			if (ind % 2 == 0) {
				re.addAll(up(upD.get(ind).x, upD.get(ind).y, mat));
			} else {
				re.addAll(down(downD.get(ind).x, downD.get(ind).y, mat));
			}
		}

		int[] reI = new int[re.size()];
		int ind = 0;
		for (Integer v : re) {
			reI[ind++] = v;
		}

		return reI;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
