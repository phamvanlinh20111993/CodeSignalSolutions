package LeetCode;

/**
 * url: https://leetcode.com/problems/count-submatrices-with-equal-frequency-of-x-and-y/?envType=daily-question&envId=2026-03-19
 * Given a 2D character matrix grid, where grid[i][j] is either 'X', 'Y', or '.', return the number of submatrices that contain:

grid[0][0]
an equal frequency of 'X' and 'Y'.
at least one 'X'.
 

Example 1:

Input: grid = [["X","Y","."],["Y",".","."]]

Output: 3

Explanation:



Example 2:

Input: grid = [["X","X"],["X","Y"]]

Output: 0

Explanation:

No submatrix has an equal frequency of 'X' and 'Y'.

Example 3:

Input: grid = [[".","."],[".","."]]

Output: 0

Explanation:

No submatrix has at least one 'X'.

 

Constraints:

1 <= grid.length, grid[i].length <= 1000
grid[i][j] is either 'X', 'Y', or '.'.
 */
public class Count_Submatrices_With_Equal_Frequency_of_X_and_Y_3212 {

	  // A submatrix (x1, y1, x2, y2) is a matrix that forms by choosing all cells matrix[x][y]   where x1 <= x <= x2 and y1 <= y <= y2.
    //grid[0][0]: => (0,0,x2​,y2​)

    public int numberOfSubmatrices(char[][] grid) {

        int[][][] map = new int[grid.length][grid[0].length][2];

        int res = 0;
        for(int y = 0; y < grid.length; y++){
            for(int x = 0; x < grid[0].length; x++){
                int tempX = 0;
                int tempY = 0;
                if(x-1 >= 0 && y-1 >= 0){
                    tempX = -map[y-1][x-1][0];
                    tempY = -map[y-1][x-1][1];
                }

                if(x-1 >= 0){
                    tempX += map[y][x-1][0];
                    tempY += map[y][x-1][1];
                }

                if(y-1 >= 0){
                    tempX += map[y-1][x][0];
                    tempY += map[y-1][x][1];
                }

                map[y][x][0] = tempX + (grid[y][x] == 'X' ? 1 : 0);
                map[y][x][1] = tempY + (grid[y][x] == 'Y' ? 1 : 0);

                if( map[y][x][0] > 0 && map[y][x][0] == map[y][x][1]){
                    res++;
                }
            }
        }

        return res;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
