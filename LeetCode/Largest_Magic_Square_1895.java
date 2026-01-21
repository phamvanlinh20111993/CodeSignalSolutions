package LeetCode;

/**
 * url: https://leetcode.com/problems/largest-magic-square/description/?envType=daily-question&envId=2026-01-18
 * 
 * A k x k magic square is a k x k grid filled with integers such that every row sum, every column sum, and both diagonal sums are all equal. The integers in the magic square do not have to be distinct. Every 1 x 1 grid is trivially a magic square.

Given an m x n integer grid, return the size (i.e., the side length k) of the largest magic square that can be found within this grid.

 

Example 1:


Input: grid = [[7,1,4,5,6],[2,5,1,6,4],[1,5,4,3,2],[1,2,7,3,4]]
Output: 3
Explanation: The largest magic square has a size of 3.
Every row sum, column sum, and diagonal sum of this magic square is equal to 12.
- Row sums: 5+1+6 = 5+4+3 = 2+7+3 = 12
- Column sums: 5+5+2 = 1+4+7 = 6+3+3 = 12
- Diagonal sums: 5+4+3 = 6+4+2 = 12
Example 2:


Input: grid = [[5,1,3,1],[9,3,3,1],[1,3,3,8]]
Output: 2
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 50
1 <= grid[i][j] <= 106
 */
public class Largest_Magic_Square_1895 {

	public boolean isMagicSquare(int xP, int yP, int l, int[][] grid) {
		// sum column
		int sum = -1;
		for (int x = xP; x < xP + l; x++) {
			int s = 0;
			for (int y = yP; y < yP + l; y++) {
				s += grid[y][x];
			}
			if (sum == -1) {
				sum = s;
			} else {
				if (sum != s)
					return false;
			}
		}
		// sum row
		for (int y = yP; y < yP + l; y++) {
			int s = 0;
			for (int x = xP; x < xP + l; x++) {
				s += grid[y][x];
			}
			if (sum != s)
				return false;
		}

		// main diagonal
		int x = xP;
		int y = yP;
		int s = 0;
		while (x < xP + l) {
			s += grid[y][x];
			x++;
			y++;
		}

		if (sum != s)
			return false;
		x = xP + l - 1;
		y = yP;
		s = 0;
		while (x >= xP) {
			s += grid[y][x];
			x--;
			y++;
		}
		if (sum != s)
			return false;

		return true;
	}

	public int largestMagicSquare(int[][] grid) {
		int largest = 1;
		int xL = grid[0].length;
		int yL = grid.length;

		for (int y = 0; y < yL; y++) {
			for (int x = 0; x < xL; x++) {
				int l = Math.min(yL - y, xL - x);
				if (largest > l)
					continue;
				while (l > 1) {

					if (isMagicSquare(x, y, l, grid)) {
						largest = Math.max(l, largest);
					}
					l--;
				}
			}
		}

		return largest;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
