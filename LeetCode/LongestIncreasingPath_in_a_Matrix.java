package LeetCode;

import java.util.PriorityQueue;
import java.util.Set;

/**
 * 
 * Url: https://leetcode.com/problems/longest-increasing-path-in-a-matrix/description/?envType=problem-list-v2&envId=depth-first-search
 * 
 * Given an m x n integers matrix, return the length of the longest increasing path in matrix.

From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).

 

Example 1:


Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
Output: 4
Explanation: The longest increasing path is [1, 2, 6, 9].
Example 2:


Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
Output: 4
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
Example 3:

Input: matrix = [[1]]
Output: 1
 

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 200
0 <= matrix[i][j] <= 231 - 1

 */
public class LongestIncreasingPath_in_a_Matrix {
	
	public boolean isInMatrix(int x, int y, int r, int c){
        return y < r && x < c && x > -1 && y > -1;
    }

    public record Coord(int x, int y, int val){}
    // Not pass, fail 5/139 test case, pass 135/139
    public int dfs(int x, int y, int[][] matrix, Set<String> isVisit){
        int c = 1;
        int [][] d = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        isVisit.add(x + "-" + y);

        PriorityQueue<Coord> prio = new PriorityQueue<Coord>((a, b) -> a.val - b.val);
        for(int ind = 0; ind < d.length; ind++){
            int dx = d[ind][0] + x;
            int dy = d[ind][1] + y;
            if(isInMatrix(dx, dy, matrix.length, matrix[0].length) && !isVisit.contains(dx + "-" + dy) &&
                matrix[y][x] < matrix[dy][dx]){
                prio.add(new Coord(dx, dy, matrix[dy][dx]));
            }
        }

        while (!prio.isEmpty()) {
            Coord dt = prio.poll();
            int t = dfs(dt.x, dt.y, matrix, isVisit) + 1;
            if(c < t) c = t;
        }
    
        return c;
    }

    public int dp(int x, int y, int[][] matrix, int[][] maxValues){
        if(maxValues[y][x] > 0) return maxValues[y][x];
        int max = 0;
        int [][] d = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

        for(int ind = 0; ind < d.length; ind++){
            int dx = d[ind][0] + x;
            int dy = d[ind][1] + y;
            if( isInMatrix(dx, dy, matrix.length, matrix[0].length) && matrix[y][x] < matrix[dy][dx]){
                max = Math.max(max, dp(dx, dy, matrix, maxValues));
            }
        }

        maxValues[y][x] = max + 1;

        return max+1;
    }


    public int longestIncreasingPath(int[][] matrix) {
        int m = 0;
        int [][] maxValues = new int[matrix.length][matrix[0].length];
        for(int y = 0; y < matrix.length; y++){
            for(int x = 0; x < matrix[0].length; x++){
                int mT = dp(x, y, matrix, maxValues);
                if(m < mT) m = mT;
            }
        }

        return m;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
