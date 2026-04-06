package LeetCode;

/**
 * url: https://leetcode.com/problems/determine-whether-matrix-can-be-obtained-by-rotation/description/?envType=daily-question&envId=2026-03-22
 * 
 * Given two n x n binary matrices mat and target, return true if it is possible to make mat equal to target by rotating mat in 90-degree increments, or false otherwise.

 

Example 1:


Input: mat = [[0,1],[1,0]], target = [[1,0],[0,1]]
Output: true
Explanation: We can rotate mat 90 degrees clockwise to make mat equal target.
Example 2:


Input: mat = [[0,1],[1,1]], target = [[1,0],[0,1]]
Output: false
Explanation: It is impossible to make mat equal to target by rotating mat.
Example 3:


Input: mat = [[0,0,0],[0,1,0],[1,1,1]], target = [[1,1,1],[0,1,0],[0,0,0]]
Output: true
Explanation: We can rotate mat 90 degrees clockwise two times to make mat equal target.
 

Constraints:

n == mat.length == target.length
n == mat[i].length == target[i].length
1 <= n <= 10
mat[i][j] and target[i][j] are either 0 or 1.
 */
public class Determine_Whether_Matrix_Can_Be_Obtained_By_Rotation_1886 {
	
	  // => formula for mat[1][1] rl=2, cl=2
    // mat[0][0] = mat[1][0]
    // mat[0][1] = mat[0][0]
    // mat[1][0] = mat[1][1]
    // mat[1][1] = mat[0][1]
    // => matC[r][c] = mat[cL-c-1][r]

    public int[][] rotation90(int[][] mat){
        int l = mat.length;
        int [][] res = new int[l][l];

        for(int r = 0; r < l; r++){
            for(int c = 0; c < l; c++){
                res[r][c] = mat[l-c-1][r];
            } 
        }

        return res;
    }

    public boolean isMatch(int[][] mat, int[][] target){
        for(int r = 0; r < mat.length; r++){
            for(int c = 0; c < target[0].length; c++){
                if(mat[r][c] != target[r][c]){
                    return false;
                }
            }
        }

        return true;
    }

    public boolean findRotation(int[][] mat, int[][] target) {
        int [][] matR90 = mat;
        if(isMatch(matR90, target)){
            return true;
        }
        for(int a = 0; a < 3; a++){
            matR90 = rotation90(matR90);
            if(isMatch(matR90, target)){
                return true;
            }
        }

        return false;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
