package LeetCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * url: https://leetcode.com/problems/sort-the-matrix-diagonally/description/?envType=problem-list-v2&envId=sorting
 * 
 * A matrix diagonal is a diagonal line of cells starting from some cell in either the topmost row or leftmost column and going in the bottom-right direction until reaching the matrix's end. For example, the matrix diagonal starting from mat[2][0], where mat is a 6 x 3 matrix, includes cells mat[2][0], mat[3][1], and mat[4][2].

Given an m x n matrix mat of integers, sort each matrix diagonal in ascending order and return the resulting matrix.

 

Example 1:


Input: mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
Output: [[1,1,1,1],[1,2,2,2],[1,2,3,3]]
Example 2:

Input: mat = [[11,25,66,1,69,7],[23,55,17,45,15,52],[75,31,36,44,58,8],[22,27,33,25,68,4],[84,28,14,11,5,50]]
Output: [[5,17,4,1,52,7],[11,11,25,45,8,69],[14,23,25,44,58,15],[22,27,31,36,50,66],[84,28,75,33,55,68]]
 

Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 100
1 <= mat[i][j] <= 100

 */
public class Sort_the_Matrix_Diagonally_1329 {
	
	public int[] nextDiagonal(int x, int y, int maX, int maY) {
		int[] nextDi = { -1, -1 };
		if (x + 1 < maX && y + 1 < maY) {
			return new int[] { x + 1, y + 1 };
		}
		return nextDi;
	}

	public int[][] diagonalSort(int[][] mat) {
		int maX = mat[0].length;
		int maY = mat.length;
		for (int x = 0; x < maX - 1; x++) {
			List<Integer> diagonal = new ArrayList<>();
			int[] d = nextDiagonal(x, 0, maX, maY);
			diagonal.add(mat[0][x]);
			while (d[0] > -1) {
				diagonal.add(mat[d[1]][d[0]]);
				d = nextDiagonal(d[0], d[1], maX, maY);
			}

			Collections.sort(diagonal);
			d = nextDiagonal(x, 0, maX, maY);
			mat[0][x] = diagonal.get(0);
			int ind = 0;
			while (d[0] > -1) {
				mat[d[1]][d[0]] = diagonal.get(++ind);
				d = nextDiagonal(d[0], d[1], maX, maY);
			}
		}

		for (int y = 1; y < maY - 1; y++) {
			List<Integer> diagonal = new ArrayList<>();
			int[] d = nextDiagonal(0, y, maX, maY);
			diagonal.add(mat[y][0]);
			while (d[0] > -1) {
				diagonal.add(mat[d[1]][d[0]]);
				d = nextDiagonal(d[0], d[1], maX, maY);
			}
			Collections.sort(diagonal);

			d = nextDiagonal(0, y, maX, maY);
			mat[y][0] = diagonal.get(0);
			int ind = 0;
			while (d[0] > -1) {
				mat[d[1]][d[0]] = diagonal.get(++ind);
				d = nextDiagonal(d[0], d[1], maX, maY);
			}
		}

		return mat;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
