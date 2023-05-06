package codility.TwentySecondofAprilTwoThousandTwentyThree;

public class BattleShipGame {

    public static int[][] DIRECTIONS = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    public static boolean[][] isVisit;

    public static boolean isInMatrix(int x, int y, int mX, int mY) {
	return x > -1 && x < mX && y > -1 && y < mY;
    }

    // point(x, y)
    public static int dfs(int x, int y, String[] inputMatrix) {
	isVisit[y][x] = true;

	int count = 1;
	for (int n = 0; n < DIRECTIONS.length; n++) {
	    int nX = x + DIRECTIONS[n][0];
	    int nY = y + DIRECTIONS[n][1];

	    if (isInMatrix(nX, nY, inputMatrix[0].length(), inputMatrix.length) 
		    && inputMatrix[nY].charAt(nX) == '#'
		    && isVisit[nY][nX] == false) {
		count += dfs(nX, nY, inputMatrix);
	    }
	}

	return count;
    }

    public static int[] battleShipCount(String[] inputMatrix) {
	int[] res = { 0, 0, 0 };

	isVisit = new boolean[inputMatrix.length][inputMatrix[0].length()];
	for (int i = 0; i < inputMatrix.length; i++) {
	    for (int j = 0; j < inputMatrix[0].length(); j++) {
		isVisit[i][j] = false;
	    }
	}

	for (int i = 0; i < inputMatrix.length; i++) {
	    for (int j = 0; j < inputMatrix[0].length(); j++) {
		if (inputMatrix[i].charAt(j) == '#' && isVisit[i][j] == false) {
		    int c = dfs(j, i, inputMatrix);
		    res[c-1]++;
		}
	    }
	}

	return res;
    }

    public static void main(String[] args) {
	System.out.println("################ test 1 ##############");

	String[] input = { ".##.#", "#.#..", "#...#", "#.##." };
	int[] res = battleShipCount(input);
	System.out.println(res[0] + " " + res[1] + " " + res[2]);
    }
}
