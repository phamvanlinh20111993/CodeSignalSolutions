package LeetCode;

/*
 * url: https://leetcode.com/problems/minimum-xor-path-in-a-grid/?envType=daily-question&envId=2026-10-28
 * 
 * You are given a 2D integer array grid of size m * n.

You start at the top-left cell (0, 0) and want to reach the bottom-right cell (m - 1, n - 1).

At each step, you may move either right or down.

The cost of a path is defined as the bitwise XOR of all the values in the cells along that path, including the start and end cells.

Return the minimum possible XOR value among all valid paths from (0, 0) to (m - 1, n - 1).

 

Example 1:

Input: grid = [[1,2],[3,4]]

Output: 6

Explanation:

There are two valid paths:

(0, 0) → (0, 1) → (1, 1) with XOR: 1 XOR 2 XOR 4 = 7
(0, 0) → (1, 0) → (1, 1) with XOR: 1 XOR 3 XOR 4 = 6
The minimum XOR value among all valid paths is 6.

Example 2:

Input: grid = [[6,7],[5,8]]

Output: 9

Explanation:

There are two valid paths:

(0, 0) → (0, 1) → (1, 1) with XOR: 6 XOR 7 XOR 8 = 9
(0, 0) → (1, 0) → (1, 1) with XOR: 6 XOR 5 XOR 8 = 11
The minimum XOR value among all valid paths is 9.

Example 3:

Input: grid = [[2,7,5]]

Output: 0

Explanation:

There is only one valid path:

(0, 0) → (0, 1) → (0, 2) with XOR: 2 XOR 7 XOR 5 = 0
The XOR value of this path is 0, which is the minimum possible.

 

Constraints:

1 <= m == grid.length <= 1000
1 <= n == grid[i].length <= 1000
m * n <= 1000
0 <= grid[i][j] <= 1023​
 * 
 */
public class Minimum_XOR_Path_in_a_Grid_3882 {
	
	 /*
    int minV = 1023;
    public void recursion(int x, int y, int[][] grid, int xorAcc){
        int xL = grid[0].length;
        int yL = grid.length;
        if(x >= xL || y >= yL){
            return;
        }
        int dt = xorAcc ^ grid[y][x];
        if(x == xL - 1 && y == yL - 1){
            minV = Math.min(minV, dt);
            return;
        }
        recursion(x+1, y, grid, dt);
        recursion(x, y+1, grid, dt);
    }

    public int minCost(int[][] grid) {
        int xL = grid[0].length;
        int yL = grid.length;
        recursion(0, 0, grid, 0);
        return minV;
    } */

    /*
    boolean [][][] memory;
    public boolean[] dp(int x, int y, int[][] grid){
        if(x < 0 || y < 0){
            boolean [] res = new boolean[1024];
            res[grid[y < 0 ? 0 : y][x < 0 ? 0 : 0]] = true;
            return res;
        }
       
        boolean[] d = dp(x - 1, y, grid);
        boolean[] d1 = dp(x, y-1, grid);

        boolean[] cur = new boolean[1024];
        for (int v = 0; v < 1024; v++) {
            if(d[v]){
                cur[grid[y][x] ^ v] = true;
            }
            if(d1[v]){
                cur[grid[y][x] ^ v] = true;
            }
        }

        memory[y][x] = cur;

        return cur;
    }

    public int minCost(int[][] grid) {
        int xL = grid[0].length;
        int yL = grid.length;
        memory = new boolean[yL][xL][1024];
        boolean [] res = dp(xL - 1, yL - 1, grid);
        for(int i = 0; i < 1024; i++){
            if(res[i]){
                return i;
            }
        }

        return 1023;
    } */
	
	// refer to chatgpt solution.: https://leetcode.com/problems/minimum-xor-path-in-a-grid/?envType=daily-question&envId=2026-10-28
	// fix 2 major problem above, 
	// 1, not use memory => we change to use visited to mark is visited or not then return memory
	// 2, the base case, ....
	boolean[][][] memory;
	boolean[][] visited;

	public boolean[] dp(int x, int y, int[][] grid) {
		if (visited[y][x]) {
			return memory[y][x];
		}

		boolean[] cur = new boolean[1024];
		int val = grid[y][x];

		if (x == 0 && y == 0) {
			cur[val] = true;
		} else {
			if (x > 0) {
				boolean[] d = dp(x - 1, y, grid);
				for (int v = 0; v < 1024; v++) {
					if (d[v]) {
						cur[v ^ val] = true;
					}
				}
			}

			if (y > 0) {
				boolean[] d1 = dp(x, y - 1, grid);
				for (int v = 0; v < 1024; v++) {
					if (d1[v]) {
						cur[v ^ val] = true;
					}
				}
			}
		}

		visited[y][x] = true;
		memory[y][x] = cur;
		return cur;
	}

	public int minCost(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;

		memory = new boolean[m][n][1024];
		visited = new boolean[m][n];
		boolean[] res = dp(n - 1, m - 1, grid);
		for (int i = 0; i < 1024; i++) {
			if (res[i]) {
				return i;
			}
		}

		return 1023;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
