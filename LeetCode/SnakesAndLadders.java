package LeetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Url: https://leetcode.com/problems/snakes-and-ladders/description/
 * You are given an n x n integer matrix board where the cells are labeled from 1 to n2 in a Boustrophedon style starting from the bottom left of the board (i.e. board[n - 1][0]) and alternating direction each row.

You start on square 1 of the board. In each move, starting from square curr, do the following:

Choose a destination square next with a label in the range [curr + 1, min(curr + 6, n2)].
This choice simulates the result of a standard 6-sided die roll: i.e., there are always at most 6 destinations, regardless of the size of the board.
If next has a snake or ladder, you must move to the destination of that snake or ladder. Otherwise, you move to next.
The game ends when you reach the square n2.
A board square on row r and column c has a snake or ladder if board[r][c] != -1. The destination of that snake or ladder is board[r][c]. Squares 1 and n2 are not the starting points of any snake or ladder.

Note that you only take a snake or ladder at most once per dice roll. If the destination to a snake or ladder is the start of another snake or ladder, you do not follow the subsequent snake or ladder.

For example, suppose the board is [[-1,4],[-1,3]], and on the first move, your destination square is 2. You follow the ladder to square 3, but do not follow the subsequent ladder to 4.
Return the least number of dice rolls required to reach the square n2. If it is not possible to reach the square, return -1.

 

Example 1:


Input: board = [[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,35,-1,-1,13,-1],[-1,-1,-1,-1,-1,-1],[-1,15,-1,-1,-1,-1]]
Output: 4
Explanation: 
In the beginning, you start at square 1 (at row 5, column 0).
You decide to move to square 2 and must take the ladder to square 15.
You then decide to move to square 17 and must take the snake to square 13.
You then decide to move to square 14 and must take the ladder to square 35.
You then decide to move to square 36, ending the game.
This is the lowest possible number of moves to reach the last square, so return 4.
Example 2:

Input: board = [[-1,-1],[-1,3]]
Output: 1
 

Constraints:

n == board.length == board[i].length
2 <= n <= 20
board[i][j] is either -1 or in the range [1, n2].
The squares labeled 1 and n2 are not the starting points of any snake or ladder.
 */
record KV (Integer n, Integer l) {}

public class SnakesAndLadders {
	
	public static int snakesAndLadders(int[][] board) {
        Queue<KV> nextQ = new LinkedList<>();
        nextQ.add(new KV(1, 0));

        Map<Integer, Integer> mappingPos = new HashMap<>();
        boolean dr = true;
        Integer maxN = 1;
        for(int r = board.length - 1; r >= 0; r--){
            if(dr){
                dr = false;
                for(int c = 0; c < board[r].length; c++){
                    if(board[r][c] > -1){
                        mappingPos.put(maxN, board[r][c]);
                    }
                    maxN++;
                }
            }else{
                dr = true;
                for(int c = board[r].length - 1; c > -1; c--){
                    if(board[r][c] > -1){
                        mappingPos.put(maxN, board[r][c]);
                    }
                    maxN++;
                }
            }
        }

        Set<Integer> isVisited = new HashSet<>();
        isVisited.add(1);
      
        System.out.println(mappingPos);
        while(nextQ.size() != 0){
            System.out.println(nextQ);
            System.out.println("set " + isVisited);
            KV nx = nextQ.poll();
            if(nx.n().equals(maxN)) return nx.l();
            for(int nextP = nx.n() + 1; nextP <= Math.min(nx.n() + 6, maxN); nextP++){
                if(mappingPos.containsKey(nextP)){
                    if(!isVisited.contains(nextP)){
                        nextQ.add(new KV(mappingPos.get(nextP), nx.l() + 1));
                        isVisited.add(nextP);
                    }
                }else{
                    if(!isVisited.contains(nextP)){
                        nextQ.add(new KV(nextP, nx.l() + 1));
                        isVisited.add(nextP);
                    }
                }
            }
        }

        return -1;
    }
	
	public static void main(String[] args) {

		int[][] board = new int[][] { 
			    { -1, -1, 128, -1, -1, -1, 136, -1, -1, -1, 109, -1 },
				{ -1, -1, -1, -1, -1, 103, -1, -1, 56, 10, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, 116, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, 50, -1, 67, 107 }, 
				{ -1, 40, -1, -1, -1, 20, -1, 59, -1, 67, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, 112, 133, 111, -1, -1, -1 },
				{ -1, -1, 112, -1, 74, -1, -1, -1, -1, -1, -1, -1 },
				{ 23, -1, 115, -1, 129, 126, -1, -1, -1, -1, -1, -1 },
				{ 106, 143, 81, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, 26, 102, 1, 29 }, 
				{ 26, -1, -1, -1, -1, -1, -1, -1, 27, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 } };
				
		System.out.println(snakesAndLadders(board));

	}

}
