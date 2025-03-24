package LeetCode;

/**
 * Url: https://leetcode.com/problems/unique-paths/description/
 * 
 * There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The test cases are generated so that the answer will be less than or equal to 2 * 109.

 

Example 1:


Input: m = 3, n = 7
Output: 28
Example 2:

Input: m = 3, n = 2
Output: 3
Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down
 

Constraints:

1 <= m, n <= 100
 * 
 */
public class UniquePaths {
	
	// f[0][0] = 0
	// f[x][y] = f[x-1][y] + f[x][y-1] (x, y >= 0)

	int[][] memo;

	public int dp(int x, int y) {
		if (x <= 0 || y <= 0)
			return 0;
		if (memo[x][y] > 0) {
			return memo[x][y];
		}

		int total = 0;
		if (x > 0) {
			total += dp(x - 1, y) + 1;
		}
		if (y > 0) {
			total += dp(x, y - 1) + 1;
		}

		if (y > 0 && x > 0) {
			total -= 1;
		}

		if (memo[x][y] == 0) {
			memo[x][y] = total;
		}

		return total;
	}

	public int uniquePaths(int m, int n) {
		memo = new int[m][n];
		int total = dp(m - 1, n - 1);
		/*
		 * for(int x = 0; x < m; x++){ for(int y = 0; y < n; y++){
		 * System.out.println("(x, y)("+x+", "+y+")=" + memo[x][y]); } }
		 */

		return total + 1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
