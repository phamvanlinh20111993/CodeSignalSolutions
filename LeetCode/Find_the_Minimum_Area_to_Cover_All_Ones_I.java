package LeetCode;

/**
 * url: https://leetcode.com/problems/find-the-minimum-area-to-cover-all-ones-i/?envType=daily-question&envId=2025-08-22
 * 
 * You are given a 2D binary array grid. Find a rectangle with horizontal and vertical sides with the smallest area, such that all the 1's in grid lie inside this rectangle.

Return the minimum possible area of the rectangle.

 

Example 1:

Input: grid = [[0,1,0],[1,0,1]]

Output: 6

Explanation:



The smallest rectangle has a height of 2 and a width of 3, so it has an area of 2 * 3 = 6.

Example 2:

Input: grid = [[1,0],[0,0]]

Output: 1

Explanation:



The smallest rectangle has both height and width 1, so its area is 1 * 1 = 1.

 

Constraints:

1 <= grid.length, grid[i].length <= 1000
grid[i][j] is either 0 or 1.
The input is generated such that there is at least one 1 in grid.
 * 
 */
public class Find_the_Minimum_Area_to_Cover_All_Ones_I {
	
	public int minimumArea(int[][] grid) {
        int t = grid.length, d = 0, l = grid[0].length, r = 0;

        for(int ind = 0; ind < grid.length; ind++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[ind][j] == 1){
                    if(t > ind) t = ind;
                    if(d < ind) d = ind;
                    if(l > j) l = j;
                    if(r < j) r = j;
                }
            }
        }

    //    System.out.println(t + " " + d + " " + l + " " + r);

        return (d - t + 1) * (r - l + 1);
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
