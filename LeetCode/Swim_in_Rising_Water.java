package LeetCode;

import java.util.PriorityQueue;

/**
 * Url: https://leetcode.com/problems/swim-in-rising-water/?envType=daily-question&envId=2025-10-06
 * 
 * You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).

It starts raining, and water gradually rises over time. At time t, the water level is t, meaning any cell with elevation less than equal to t is submerged or reachable.

You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distances in zero time. Of course, you must stay within the boundaries of the grid during your swim.

Return the minimum time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0).

 

Example 1:


Input: grid = [[0,2],[1,3]]
Output: 3
Explanation:
At time 0, you are in grid location (0, 0).
You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
You cannot reach point (1, 1) until time 3.
When the depth of water is 3, we can swim anywhere inside the grid.
Example 2:


Input: grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
Output: 16
Explanation: The final route is shown.
We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
 

Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 50
0 <= grid[i][j] < n2
Each value grid[i][j] is unique.

 */
public class Swim_in_Rising_Water {
	
	record Point(int x, int y, int height){}

    public boolean isInBoard(int x, int y, int H){
        return x > -1 && x < H && y > -1 && y < H;
    }

    public int swimInWater(int[][] grid) {
        int H = grid[0].length;
        Point startPoint = new Point(0, 0, grid[0][0]);
        PriorityQueue<Point> qQueue = new PriorityQueue<>( (a, b) -> Integer.compare(a.height, b.height));
        boolean [][] isVisited = new boolean[H][H];
        qQueue.offer(startPoint);

        final int [][] D = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

        int currentTimeInPath = 0;

        while(!qQueue.isEmpty()){
            Point p = qQueue.poll();

            if(isVisited[p.y][p.x]) continue;

            isVisited[p.y][p.x] = true;

            if(currentTimeInPath < p.height){
                currentTimeInPath = p.height;
            }

            if(isVisited[H-1][H-1]){
                   System.out.println(qQueue);
                return currentTimeInPath;
            }

            for(int [] d : D){
                int xT = d[0] + p.x;
                int yT = d[1] + p.y;
               
                if(isInBoard(xT, yT, H) && !isVisited[yT][xT]){
                     Point pT = new Point(xT, yT, grid[yT][xT]);
                    qQueue.offer(pT);
                }
            }
        }

        return currentTimeInPath;

    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
