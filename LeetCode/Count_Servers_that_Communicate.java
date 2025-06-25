package LeetCode;

import java.util.HashSet;
import java.util.Set;

/**
 * Url: https://leetcode.com/problems/count-servers-that-communicate/description/?envType=problem-list-v2&envId=union-find
 * 
 * You are given a map of a server center, represented as a m * n integer matrix grid, where 1 means that on that cell there is a server and 0 means that it is no server. Two servers are said to communicate if they are on the same row or on the same column.

Return the number of servers that communicate with any other server.

 

Example 1:



Input: grid = [[1,0],[0,1]]
Output: 0
Explanation: No servers can communicate with others.
Example 2:



Input: grid = [[1,0],[1,1]]
Output: 3
Explanation: All three servers can communicate with at least one other server.
Example 3:



Input: grid = [[1,1,0,0],[0,0,1,0],[0,0,1,0],[0,0,0,1]]
Output: 4
Explanation: The two servers in the first row can communicate with each other. The two servers in the third column can communicate with each other. The server at right bottom corner can't communicate with any other server.
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m <= 250
1 <= n <= 250
grid[i][j] == 0 or 1
 */
public class Count_Servers_that_Communicate {
	
	public int countServers(int[][] grid) {
        Set<String> countSame = new HashSet<>();

        int rL = grid.length, cL = grid[0].length;

        for(int r = 0; r < rL; r++){
            int amountComputer = 0;
            for(int c = 0; c < cL; c++){
                if(grid[r][c] ==1){
                    amountComputer++;
                }
            }
            if(amountComputer > 1){
                for(int c = 0; c < cL; c++){
                    if(grid[r][c] ==1){
                        countSame.add(r+"-"+c);
                    }
                }
            }
        }

        for(int c = 0; c < cL; c++){
            int amountComputer = 0;
            for(int r = 0; r <  rL; r++){
                if(grid[r][c] ==1){
                    amountComputer++;
                }
            }
            if(amountComputer > 1){
                for(int r = 0; r < rL; r++){
                    if(grid[r][c] ==1){
                        countSame.add(r+"-"+c);
                    }
                }
            }
        }

        return countSame.size();
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
