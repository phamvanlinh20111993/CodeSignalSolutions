package LeetCode;

import java.util.Arrays;

/**
 * url: https://leetcode.com/problems/check-if-there-is-a-valid-path-in-a-grid/?envType=daily-question&envId=2026-04-27
 * 
 * You are given an m x n grid. Each cell of grid represents a street. The street of grid[i][j] can be:

1 which means a street connecting the left cell and the right cell.
2 which means a street connecting the upper cell and the lower cell.
3 which means a street connecting the left cell and the lower cell.
4 which means a street connecting the right cell and the lower cell.
5 which means a street connecting the left cell and the upper cell.
6 which means a street connecting the right cell and the upper cell.

You will initially start at the street of the upper-left cell (0, 0). A valid path in the grid is a path that starts from the upper left cell (0, 0) and ends at the bottom-right cell (m - 1, n - 1). The path should only follow the streets.

Notice that you are not allowed to change any street.

Return true if there is a valid path in the grid or false otherwise.

 

Example 1:


Input: grid = [[2,4,3],[6,5,2]]
Output: true
Explanation: As shown you can start at cell (0, 0) and visit all the cells of the grid to reach (m - 1, n - 1).
Example 2:


Input: grid = [[1,2,1],[1,2,1]]
Output: false
Explanation: As shown you the street at cell (0, 0) is not connected with any street of any other cell and you will get stuck at cell (0, 0)
Example 3:

Input: grid = [[1,1,2]]
Output: false
Explanation: You will get stuck at cell (0, 1) and you cannot reach cell (0, 2).
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 300
1 <= grid[i][j] <= 6
 * 
 */
public class Check_if_There_is_a_Valid_Path_in_a_Grid_1391 {
	
	class Direct{
        int[] coords = new int[2];
        boolean[] validStreet = new boolean[]{false,false,false,false,false,false,false};
        @Override
        public String toString() {
            return "Direct{" +
                    "coords=" + Arrays.toString(coords) +
                    ", validStreet=" + Arrays.toString(validStreet) +
                    '}';
        }
    }
    class Street {
        Direct [] d = new Direct[]{new Direct(), new Direct()};
        @Override
        public String toString() {
            return "Street{" +
                    "d=" + Arrays.toString(d) +
                    '}';
        }
    }

    Street[] STREETS;

    // l: [-1, 0]
    // r: [1, 0]
    // t: [0, -1]
    // b: [0, 1]
    // street 1: [l, r]
    // street 2: [t, b]
    // street 3: [l, b]
    // street 4: [r, b]
    // street 5: [t, l]
    // street 6: [t, r]
    public void initialStreet(){
        STREETS = new Street[7];
        // street 1: [l, r]
        STREETS[1] = new Street();
        STREETS[1].d[0].coords = new int []{-1, 0};
        STREETS[1].d[0].validStreet[1] = true;
        STREETS[1].d[0].validStreet[4] = true;
        STREETS[1].d[0].validStreet[6] = true;

        STREETS[1].d[1].coords = new int []{1, 0};
        STREETS[1].d[1].validStreet[1] = true;
        STREETS[1].d[1].validStreet[3] = true;
        STREETS[1].d[1].validStreet[5] = true;

         // street 2: [t, b]
        STREETS[2] = new Street();
        STREETS[2].d[0].coords = new int []{0, -1};
        STREETS[2].d[0].validStreet[2] = true;
        STREETS[2].d[0].validStreet[3] = true;
        STREETS[2].d[0].validStreet[4] = true;

        STREETS[2].d[1].coords = new int []{0, 1};
        STREETS[2].d[1].validStreet[2] = true;
        STREETS[2].d[1].validStreet[5] = true;
        STREETS[2].d[1].validStreet[6] = true;

         // street 3: [l, b]
        STREETS[3] = new Street();
        STREETS[3].d[0].coords = new int []{-1, 0};
        STREETS[3].d[0].validStreet[1] = true;
        STREETS[3].d[0].validStreet[4] = true;
        STREETS[3].d[0].validStreet[6] = true;

        STREETS[3].d[1].coords = new int []{0, 1};
        STREETS[3].d[1].validStreet[2] = true;
        STREETS[3].d[1].validStreet[5] = true;
        STREETS[3].d[1].validStreet[6] = true;

        // street 4: [r, b]
        STREETS[4] = new Street();
        STREETS[4].d[0].coords = new int []{1, 0};
        STREETS[4].d[0].validStreet[1] = true;
        STREETS[4].d[0].validStreet[3] = true;
        STREETS[4].d[0].validStreet[5] = true;

        STREETS[4].d[1].coords = new int []{0, 1};
        STREETS[4].d[1].validStreet[2] = true;
        STREETS[4].d[1].validStreet[5] = true;
        STREETS[4].d[1].validStreet[6] = true;

        // street 5: [t, l]
        STREETS[5] = new Street();
        STREETS[5].d[0].coords = new int []{0, -1};
        STREETS[5].d[0].validStreet[2] = true;
        STREETS[5].d[0].validStreet[3] = true;
        STREETS[5].d[0].validStreet[4] = true;

        STREETS[5].d[1].coords = new int []{-1, 0};
        STREETS[5].d[1].validStreet[1] = true;
        STREETS[5].d[1].validStreet[4] = true;
        STREETS[5].d[1].validStreet[6] = true;

        // street 6: [t, r]
        STREETS[6] = new Street();
        STREETS[6].d[0].coords = new int []{0, -1};
        STREETS[6].d[0].validStreet[2] = true;
        STREETS[6].d[0].validStreet[3] = true;
        STREETS[6].d[0].validStreet[4] = true;

        STREETS[6].d[1].coords = new int []{1, 0};
        STREETS[6].d[1].validStreet[1] = true;
        STREETS[6].d[1].validStreet[3] = true;
        STREETS[6].d[1].validStreet[5] = true;
    }

    public boolean isInGrid(int r, int c, int rG, int cG){
        return r > -1 && r < rG && c > -1 && c < cG;
    }

    boolean isValidPath = false;
    public void dfs(int r, int c, int[][] grid, boolean[][] isVisited){
    //    System.out.println("r=" +r+",c=" + c);
        if(isValidPath || (r == grid.length - 1 && c == grid[0].length - 1)){
            isValidPath = true;
            return;
        }
        isVisited[r][c] = true;

        for (Direct dir : STREETS[grid[r][c]].d) {
              
            int dc = c + dir.coords[0];
            int dr = r + dir.coords[1];

            // System.out.println("dir="+ dir + " " + ",dr="+dr+",dc=" + dc);
            // if(isInGrid(dr, dc, grid.length, grid[0].length)){
            //     System.out.println("isInGrid="+ isInGrid(dr, dc, grid.length, grid[0].length));
            //     System.out.println("isVisited="+ isVisited[dr][dc]);
            //     System.out.println("dir.validStreet="+ dir.validStreet[grid[dr][dc]] + ",val="+grid[dr][dc]);
            // }
            if(isInGrid(dr, dc, grid.length, grid[0].length) && !isVisited[dr][dc]
                && dir.validStreet[grid[dr][dc]]){
                dfs(dr, dc, grid, isVisited);
            }
        }
    }

    public boolean hasValidPath(int[][] grid) {
        boolean[][] isVisited = new boolean[grid.length][grid[0].length];
        initialStreet();
        isValidPath = false;
        dfs(0, 0, grid, isVisited);
        return isValidPath;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
