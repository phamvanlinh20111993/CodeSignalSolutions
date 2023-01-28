package Codesignal.Arcade;

public class ContoursShifting {

	static int[][] contoursShifting(int[][] matrix) {
		int left = 0, right = matrix[0].length, up = 0, down = matrix.length, i, j;
		int[][] res = new int[matrix.length][matrix[0].length];

		if (right - left == 1 && down - up == 1) {
			return matrix;
		}

		while (left < right && up < down) {
			if (left % 2 == 0) {

				if (right - left == 1 && down - up > 1) {
					for (i = up; i < down - 1; i++) {
						res[i + 1][left] = matrix[i][left];
					}
					res[up][left] = matrix[down - 1][left];
				} else if (down - up == 1 && right - left > 1) {
					for (i = left; i < right - 1; i++) {
						res[up][i + 1] = matrix[up][i];
					}
					res[up][left] = matrix[up][right - 1];
				} else {
					for (i = left; i < right - 1; i++) {
						res[up][i + 1] = matrix[up][i];
						res[down - 1][i] = matrix[down - 1][i + 1];
					}
					for (i = up; i < down - 1; i++) {
						res[i + 1][right - 1] = matrix[i][right - 1];
						res[i][left] = matrix[i + 1][left];
					}
				}
			} else {
				if (right - left == 1 && down - up > 1) {
					for (i = up; i < down - 1; i++) {
						res[i][left] = matrix[i + 1][left];
					}
					res[down - 1][right - 1] = matrix[up][left];
				} else if (down - up == 1 && right - left > 1) {
					for (i = left; i < right - 1; i++) {
						res[up][i] = matrix[up][i + 1];
					}
					res[up][right - 1] = matrix[up][left];
				} else {
					for (i = left; i < right - 1; i++) {
						res[up][i] = matrix[up][i + 1];
						res[down - 1][i + 1] = matrix[down - 1][i];
					}
					for (i = up; i < down - 1; i++) {
						res[i][right - 1] = matrix[i + 1][right - 1];
						res[i + 1][left] = matrix[i][left];
					}
				}
			}

			left++;
			right--;
			up++;
			down--;
		}

		for (i = 0; i < res.length; i++) {
			for (j = 0; j < res[0].length; j++) {
				System.out.print(res[i][j] + " ");
			}
			System.out.println();
		}

		return res;
	}

	public static void main(String[] args) {
		System.out.println("######### test 1 ###########");
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 }, { 17, 18, 19, 20 } };
		contoursShifting(matrix);

		System.out.println("######### test 2 ###########");
		int[][] matrix1 = { { 238, 239, 240, 241, 242, 243, 244, 245 } };
		contoursShifting(matrix1);

		System.out.println("######### test 3 ###########");
		int[][] matrix2 = { { 238 }, { 239 }, { 240 }, { 241 }, { 242 }, { 243 }, { 244 }, { 245 } };
		contoursShifting(matrix2);

		System.out.println("######### test 4 ###########");
		int[][] matrix3 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
		contoursShifting(matrix3);
	}

}
