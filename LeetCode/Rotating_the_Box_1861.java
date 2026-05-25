package LeetCode;

/**
 * url: https://leetcode.com/problems/rotating-the-box/description/?envType=daily-question&envId=2026-05-06
 * 
 * You are given an m x n matrix of characters boxGrid representing a side-view of a box. Each cell of the box is one of the following:

A stone '#'
A stationary obstacle '*'
Empty '.'
The box is rotated 90 degrees clockwise, causing some of the stones to fall due to gravity. Each stone falls down until it lands on an obstacle, another stone, or the bottom of the box. Gravity does not affect the obstacles' positions, and the inertia from the box's rotation does not affect the stones' horizontal positions.

It is guaranteed that each stone in boxGrid rests on an obstacle, another stone, or the bottom of the box.

Return an n x m matrix representing the box after the rotation described above.

 

Example 1:



Input: boxGrid = [["#",".","#"]]
Output: [["."],
         ["#"],
         ["#"]]
Example 2:



Input: boxGrid = [["#",".","*","."],
              ["#","#","*","."]]
Output: [["#","."],
         ["#","#"],
         ["*","*"],
         [".","."]]
Example 3:



Input: boxGrid = [["#","#","*",".","*","."],
              ["#","#","#","*",".","."],
              ["#","#","#",".","#","."]]
Output: [[".","#","#"],
         [".","#","#"],
         ["#","#","*"],
         ["#","*","."],
         ["#",".","*"],
         ["#",".","."]]
 

Constraints:

m == boxGrid.length
n == boxGrid[i].length
1 <= m, n <= 500
boxGrid[i][j] is either '#', '*', or '.'.
 */
public class Rotating_the_Box_1861 {
	
    public char[][] rotateTheBox(char[][] boxGrid) {
        int rL = boxGrid.length;
        int cL = boxGrid[0].length;
       
        for(int r = 0; r < rL; r++){
            int emptyInd = cL-1;
            int stoneInd = cL-1;

            while(stoneInd > -1 && emptyInd > -1){
                if(boxGrid[r][emptyInd] == '#' || boxGrid[r][emptyInd] == '*'){ 
                    if( boxGrid[r][emptyInd] == '*'){
                        stoneInd = emptyInd - 1;
                    }
                    emptyInd--;
                    continue;
                }
                if(boxGrid[r][stoneInd] == '.' || boxGrid[r][stoneInd] == '*'){ 
                    if( boxGrid[r][stoneInd] == '*'){
                        emptyInd = stoneInd - 1;
                    }
                    stoneInd--;
                    continue;
                }
                if(stoneInd >= emptyInd){
                    stoneInd--;
                    continue;
                }

                char tmp = boxGrid[r][stoneInd];
                boxGrid[r][stoneInd] = boxGrid[r][emptyInd];
                boxGrid[r][emptyInd] = tmp;
            }
            // for(int c = 0; c < cL; c++){
            //     System.out.print(boxGrid[r][c] + " ");
            // }
            // System.out.println();
        }

        char[][] box90Grid = new char[cL][rL];
        for(int c = 0; c < cL; c++){
            for(int r = 0; r < rL; r++){
                box90Grid[c][rL - r - 1] = boxGrid[r][c];
            }
        }

        return box90Grid;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
