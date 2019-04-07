package Challenge;

public class smallestComponent {

	static int[] coordX = { -1, 0, 1, 0 }, coordY = { 0, -1, 0, 1 };
	static int count = 1;
	public static int[][] map = new int[200][200], mapTmp = new int[200][200];

	public static void backTracking(String[] matrix, char str, int i, int j, int row, int col) {

		for (int k = 0; k < 4; k++) {
			int x = coordX[k] + j, y = coordY[k] + i;
			if (x >= 0 && y >= 0 && x < col && y < row) {
				if (map[y][x] == 0 && matrix[y].charAt(x) == str) {
					map[y][x] = 1;
					mapTmp[y][x] = 1;
					count++;
					backTracking(matrix, str, y, x, row, col);
					// map[y][x] = 0;
				}
			}
		}
	}

	public static void main(String[] args) {
		int i, j, minLength = Integer.MAX_VALUE;

		for (i = 0; i < 200; i++) {
			for (j = 0; j < 200; j++) {
				map[i][j] = 0;
				mapTmp[i][j] = 0;
			}
		}
		// String[] matrix = { "KJJLL", "KKJJL", "JKKJL", "JJJJL" };
		// String[] matrix = { "XXXXXXX", "XXXXXXX", "XXXXXXX", "XXXXXXX", "XXXXXXX" };
		// String[] matrix = { "XYYYYYX", "XXYYYXX", "XXXYYYX", "XXYYYXX", "XXXYYXX",
		// "XXXYYYX", "XXYYXXX", "XXXYYXX",
		// "XXXYXXX", "XXXXXXX" };
		// String[] matrix = { "NNO", "ONO", "OON", "ONN" };
		String[] matrix = { "1111111111111111", "1000111111100011", "0001111111101111", "1101111111001111",
				"1100011110000001", "1111000100100111", "1110000001100111", "1100110111110011", "1001100011111001",
				"1011001000111111", "1011111110001111", "1111111111111111" };
		// i = 0;
		// j = 1;
		for (i = 0; i < matrix.length; i++) {
			for (j = 0; j < matrix[i].length(); j++) {
				if (mapTmp[i][j] == 0) {
					map[i][j] = 1;
					mapTmp[i][j] = 1;
					count = 1;
					backTracking(matrix, matrix[i].charAt(j), i, j, matrix.length, matrix[0].length());
					System.out.println(matrix[i].charAt(j) + "  " + count);
					if (minLength > count)
						minLength = count;
				}
			}
		}

		System.out.println("min length" + minLength);
	}

}
