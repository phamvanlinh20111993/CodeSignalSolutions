package LeetCode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * url: https://leetcode.com/problems/find-a-safe-walk-through-a-grid/?envType=daily-question&envId=2026-07-02
 * 
 * You are given an m x n binary matrix grid and an integer health.

You start on the upper-left corner (0, 0) and would like to get to the lower-right corner (m - 1, n - 1).

You can move up, down, left, or right from one cell to another adjacent cell as long as your health remains positive.

Cells (i, j) with grid[i][j] = 1 are considered unsafe and reduce your health by 1.

Return true if you can reach the final cell with a health value of 1 or more, and false otherwise.

 

Example 1:

Input: grid = [[0,1,0,0,0],[0,1,0,1,0],[0,0,0,1,0]], health = 1

Output: true

Explanation:

The final cell can be reached safely by walking along the gray cells below.


Example 2:

Input: grid = [[0,1,1,0,0,0],[1,0,1,0,0,0],[0,1,1,1,0,1],[0,0,1,0,1,0]], health = 3

Output: false

Explanation:

A minimum of 4 health points is needed to reach the final cell safely.


Example 3:

Input: grid = [[1,1,1],[1,0,1],[1,1,1]], health = 5

Output: true

Explanation:

The final cell can be reached safely by walking along the gray cells below.



Any path that does not go through the cell (1, 1) is unsafe since your health will drop to 0 when reaching the final cell.

 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 50
2 <= m * n
1 <= health <= m + n
grid[i][j] is either 0 or 1.
 */
public class Find_a_Safe_Walk_Through_a_Grid_3286 {
	
	record C(int x, int y, int h){}

    boolean isInGrid(int x, int y, int gX, int gY){
        return x > -1 && y > -1 && y < gY && x < gX;
    }

    /*public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        Queue<C> queue = new LinkedList<>();
        int gY = grid.size();
        int gX = grid.get(0).size();
        boolean[][] isVisited = new boolean[gY][gX];
        int[][] minValueAtGrid = new int[gY][gX];
        int [][] D = new int[][]{{-1, 0},{1, 0},{0, -1},{0, 1}};
        queue.add(new C(0, 0, health - grid.get(0).get(0)));
        isVisited[0][0] = true;
       
        while(!queue.isEmpty()){
            C node = queue.poll();
            if(node.h > 0 && node.y == gY-1 && node.x == gX-1){
                return true;
            }

            List<C> sortL = new ArrayList<>();
            for(int[] d : D){
                int xd = d[0] + node.x;
                int yd = d[1] + node.y;
                if(isInGrid(xd, yd, gX, gY)){  
                    if(node.h - grid.get(yd).get(xd) < 1){
                        continue;
                    }
                    if(!isVisited[yd][xd]){
                        isVisited[yd][xd] = true;
                        sortL.add(new C(xd, yd, node.h - grid.get(yd).get(xd)));
                    }
                    if(node.h - grid.get(yd).get(xd) > minValueAtGrid[yd][xd]){
                        minValueAtGrid[yd][xd] = node.h - grid.get(yd).get(xd);
                    }
                }
            }
            sortL.sort(Comparator.comparingInt((C c) -> c.h).reversed());
            queue.addAll(sortL);
        }

        for (int y = 0; y < minValueAtGrid.length; y++) {
            for (int x = 0; x < minValueAtGrid[y].length; x++) {
                System.out.print(minValueAtGrid[y][x] + " ");
            }
            System.out.println(); 
        }

        return false;
    } */

    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        Queue<C> queue = new PriorityQueue<>(Comparator.comparingInt((C c) -> c.h).reversed());
        int gY = grid.size();
        int gX = grid.get(0).size();
        boolean[][] isVisited = new boolean[gY][gX];
        int [][] D = new int[][]{{-1, 0},{1, 0},{0, -1},{0, 1}};
        queue.add(new C(0, 0, health - grid.get(0).get(0)));
        isVisited[0][0] = true;
       
        while(!queue.isEmpty()){
            C node = queue.poll();
            if(node.h > 0 && node.y == gY-1 && node.x == gX-1){
                return true;
            }

            List<C> sortL = new ArrayList<>();
            for(int[] d : D){
                int xd = d[0] + node.x;
                int yd = d[1] + node.y;
                if(isInGrid(xd, yd, gX, gY)){  
                    if(node.h - grid.get(yd).get(xd) < 1){
                        continue;
                    }
                    if(!isVisited[yd][xd]){
                        isVisited[yd][xd] = true;
                        queue.add(new C(xd, yd, node.h - grid.get(yd).get(xd)));
                    }
                }
            }
        }

        return false;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
