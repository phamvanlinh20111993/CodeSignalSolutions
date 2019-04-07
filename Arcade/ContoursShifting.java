package Arcade;

public class ContoursShifting {

	static int[][] contoursShifting(int[][] matrix) {
		int left = 0, right = matrix[0].length,
			up = 0, down = matrix.length, i, j;
		int [][] res = new int[matrix.length][matrix[0].length];
		
		while(left < right && up < down) {
			//clockwise direction
			if(left %2 ==0) {
				System.out.println(left + "  " + right);
				//
				for(i = left+1; i < right; i++) {
					res[up][i] = matrix[up][i-1];
				}
				if(up+1 < down) {
					res[up][left] = matrix[up+1][left];
				}else {
					res[up][left] = matrix[up][right-1];
				}
				//
				for(i = up+1; i < down; i++) {
					res[i][right-1] = matrix[i-1][right-1];
				}
				//
				if(down-up > 1) {
					for(i = left; i < right-1; i++) {
						res[down-1][i] = matrix[down-1][i+1];
					}
				}
				//
				if(right-left > 1) {
					for(i = up+1; i < down-1; i++) {
						res[i][left] = matrix[i+1][left];
					}
				}
			}else {//counterclockwise 
//				for(i = left+1; i < right; i++) {
//					res[up][i-1] = matrix[up][i];
//				}
//				if(up < down-1) {
//					res[up][right-1] = matrix[up+1][right-1];
//				}else {
//					res[up][right-1] = matrix[up][left];
//				}
//				//
//				for(i = up+1; i < down-1; i++) {
//					res[i][right-1] = matrix[i+1][right-1];
//				}
//				//
//				for(i = left; i < right-1; i++) {
//					res[down-1][i] = matrix[down-1][i+1];
//				}
//				//
//				for(i = up+1; i < down-1; i++) {
//					res[i][left] = matrix[i+1][left];
//				}
			}
			
			left++;
			right--;
			up++;
			down--;
		}
		
		for(i = 0; i < res.length; i++) {
			for(j = 0; j < res[0].length; j++) {
				System.out.print(res[i][j] + " ");
			}
			System.out.println();
		}
		
		return res;
	}

	public static void main(String[] args) {
		System.out.println("######### test 1 ###########");
		int[][] matrix = { { 1, 2, 3, 4 }, 
				           { 5, 6, 7, 8 }, 
				           { 9, 10, 11, 12 }, 
				           { 13, 14, 15, 16 }, 
				           { 17, 18, 19, 20 } };
		contoursShifting(matrix);
		
		System.out.println("######### test 2 ###########");
		int[][] matrix1 = { { 238, 239, 240, 241, 242, 243, 244, 245 } };
		contoursShifting(matrix1);

		System.out.println("######### test 3 ###########");
		int[][] matrix2 = { { 238 }, {239}, {240}, {241}, {242}, {243}, {244}, {245} };
		contoursShifting(matrix2);
	}

}
