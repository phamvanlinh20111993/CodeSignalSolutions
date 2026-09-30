package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * url: https://leetcode.com/problems/minimum-moves-to-clean-the-classroom/?envType=daily-question&envId=2026-09-01
 * 
 * You are given an m x n grid classroom where a student volunteer is tasked with cleaning up litter scattered around the room. Each cell in the grid is one of the following:

'S': Starting position of the student
'L': Litter that must be collected (once collected, the cell becomes empty)
'R': Reset area that restores the student's energy to full capacity, regardless of their current energy level (can be used multiple times)
'X': Obstacle the student cannot pass through
'.': Empty space
You are also given an integer energy, representing the student's maximum energy capacity. The student starts with this energy from the starting position 'S'.

Each move to an adjacent cell (up, down, left, or right) costs 1 unit of energy. If the energy reaches 0, the student can only continue if they are on a reset area 'R', which resets the energy to its maximum capacity energy.

Return the minimum number of moves required to collect all litter items, or -1 if it's impossible.

 

Example 1:

Input: classroom = ["S.", "XL"], energy = 2

Output: 2

Explanation:

The student starts at cell (0, 0) with 2 units of energy.
Since cell (1, 0) contains an obstacle 'X', the student cannot move directly downward.
A valid sequence of moves to collect all litter is as follows:
Move 1: From (0, 0) → (0, 1) with 1 unit of energy and 1 unit remaining.
Move 2: From (0, 1) → (1, 1) to collect the litter 'L'.
The student collects all the litter using 2 moves. Thus, the output is 2.
Example 2:

Input: classroom = ["LS", "RL"], energy = 4

Output: 3

Explanation:

The student starts at cell (0, 1) with 4 units of energy.
A valid sequence of moves to collect all litter is as follows:
Move 1: From (0, 1) → (0, 0) to collect the first litter 'L' with 1 unit of energy used and 3 units remaining.
Move 2: From (0, 0) → (1, 0) to 'R' to reset and restore energy back to 4.
Move 3: From (1, 0) → (1, 1) to collect the second litter 'L'.
The student collects all the litter using 3 moves. Thus, the output is 3.
Example 3:

Input: classroom = ["L.S", "RXL"], energy = 3

Output: -1

Explanation:

No valid path collects all 'L'.

 

Constraints:

1 <= m == classroom.length <= 20
1 <= n == classroom[i].length <= 20
classroom[i][j] is one of 'S', 'L', 'R', 'X', or '.'
1 <= energy <= 50
There is exactly one 'S' in the grid.
There are at most 10 'L' cells in the grid.
 */
public class Minimum_Moves_to_Clean_the_Classroom_3568 {

	record Q(int x, int y, int mask, int step, int energy) {
	}

	public boolean isInClassRoom(int x, int y, int xL, int yL) {
		return x > -1 && x < xL && y > -1 && y < yL;
	}

	public int minMoves(String[] classroom, int energy) {
		int xL = classroom[0].length();
		int yL = classroom.length;
		int xS = -1, yS = -1;

		int[][] litterIndex = new int[yL][xL];
		int amountLittlers = 0;
		for (int y = 0; y < yL; y++) {
			for (int x = 0; x < xL; x++) {
				if (classroom[y].charAt(x) == 'S') {
					xS = x;
					yS = y;
				}
				if (classroom[y].charAt(x) == 'L') {
					litterIndex[y][x] = amountLittlers;
					amountLittlers++;
				}
			}
		}

		int[][] D = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		Queue<Q> q = new LinkedList<>();
		q.add(new Q(xS, yS, 0, 0, energy));
		// There are at most 10 'L' cells in the grid.
		boolean[][][][] isVisited = new boolean[yL][xL][energy + 1][1 << amountLittlers];
		isVisited[yS][xS][energy][0] = true;
		int allMask = (1 << amountLittlers) - 1; // 11111111....

		while (!q.isEmpty()) {
			Q t = q.poll();

			if (allMask == t.mask) {
				return t.step;
			}

			if (t.energy <= 0)
				continue;

			for (int[] cT : D) {
				int xT = t.x + cT[0];
				int yT = t.y + cT[1];
				if (isInClassRoom(xT, yT, xL, yL) && classroom[yT].charAt(xT) != 'X') {
					int newEnergy = classroom[yT].charAt(xT) == 'R' ? energy : t.energy - 1;
					int newMask = t.mask;

					if (classroom[yT].charAt(xT) == 'L') {
						newMask |= (1 << litterIndex[yT][xT]);
					}

					if (isVisited[yT][xT][newEnergy][newMask]) {
						continue;
					}

					isVisited[yT][xT][newEnergy][newMask] = true;
					q.add(new Q(xT, yT, newMask, t.step + 1, newEnergy));
				}
			}

		}

		return -1;
	}

	    /*
	    int res = 100000;
	    Map<String, Integer> memo;
	    public void dfs(Q t, String[] classroom, int energy, int amountLittlers, boolean [][] isVisited){
	        if(t.litters == amountLittlers){
	   //         System.out.println(t.step);
	            res = Math.min(res, t.step);
	            return;
	        }

	        if(t.energy <= 0){
	            return;
	        }

	        String key = t.coord.x + "," +
	                 t.coord.y + "," +
	                 t.energy + "," +
	                 t.mask;

	        if (memo.containsKey(key) && memo.get(key) <= t.step) {
	            return;
	        }
	        memo.put(key, t.step);

	        int xL = classroom[0].length();
	        int yL = classroom.length;
	        int [][] D = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	        int cX = t.coord.x;
	        int cY = t.coord.y;
	        isVisited[cY][cX] = true;

	        for(int [] cT : D){
	            int xT = cX + cT[0];
	            int yT = cY + cT[1];
	            if(isInClassRoom(xT, yT, xL, yL) && !isVisited[yT][xT] && classroom[yT].charAt(xT) != 'X'){
	                int newEnergy = classroom[yT].charAt(xT) == 'R' ? energy : t.energy - 1;
	                int amountLitters = classroom[yT].charAt(xT) == 'L' ? t.litters + 1 : t.litters;
	                dfs(new Q(new C(xT, yT), amountLitters, t.step + 1, newEnergy), classroom, energy, amountLittlers, isVisited);
	                isVisited[cY][cX] = false;
	            }
	        }
	       
	    }

	     public int minMoves(String[] classroom, int energy) {
	        int xL = classroom[0].length();
	        int yL = classroom.length;
	        int xS = -1, yS = -1;
	        int amountLittlers = 0;
	        boolean [][] isVisited = new boolean[yL][xL];
	        for(int y = 0; y < yL; y++){
	            for(int x = 0; x < xL; x++){
	                isVisited[y][x] = false;
	                if(classroom[y].charAt(x) == 'S'){
	                    xS = x;
	                    yS = y;
	                }
	                if(classroom[y].charAt(x) == 'L'){
	                    amountLittlers++;
	                }
	            }
	        }

	        res = 100000;
	        memo = new HashMap<>();
	        dfs(new Q(new C(xS, yS), 0, 0, energy, ), classroom, energy, amountLittlers, isVisited);

	        return res == 100000 ? -1 : res;
	    }
	} */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
