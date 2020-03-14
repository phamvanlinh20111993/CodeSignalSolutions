package BackTracking;

public class MineDetection {

	static int[] pX = { -1, 0, 1, 1, 1, 0, -1, -1 }, 
			     pY = { 1, 1, 1, 0, -1, -1, -1, 0 };
	static int[][] mapMarkMine;

	static void mineDetection(int rootX, int rootY, int i, int j, int[][] mapMine, int [][] output) {

		for (int k = 1; k >= 0; k--) {
			System.out.println("(" + i + ", " + j + ") --- ("+rootX + ", " +rootY +")");
			if (mapMarkMine[i][j] == 0) {
				mapMarkMine[i][j] = 1;
				output[i][j] = k;

				int count = 0;
				
				System.out.println("count " + count);
				if (count != mapMine[rootX][rootY]) {
					for (int l = 0; l < 8; l++) {
						if (rootX + pX[l] >= 0 && rootY + pY[l] >= 0 && rootX + pX[l] < mapMine.length
								&& rootY + pY[l] < mapMine[0].length) {
							if(mapMarkMine[rootX + pX[l]][rootY + pY[l]] == 0) {
								mineDetection(rootX, rootY, rootX + pX[l], rootY + pY[l], mapMine, output);
								break;
							}
						}
					}
				} else {
					System.out.println("next ");
					if (rootY < mapMine[0].length) {
						mineDetection(rootX, rootY + 1, rootX-1 >= 0 ? rootX-1 : rootX, rootY+1, mapMine, output);
					}
					if (rootX < mapMine.length) {
						mineDetection(rootX + 1, rootY, rootX-1 >= 0 ? rootX-1 : rootX, rootY+1, mapMine, output);
					}
					if (rootY == mapMine[0].length && rootX == mapMine.length ) {
						for (int l = 0; l < output.length; l++) {
							for (int t = 0; t < output[l].length; l++) {
								System.out.print(output[l][t] + " ");
							}
							System.out.println();
						}
					}
				}
				mapMarkMine[i][j] = 0;
				output[i][j] = 0;
			}
		}

	}

	public static void main(String[] args) {
		int[][] mapMine = { { 1, 3, 3, 1 }, 
				            { 2, 3, 4, 4 }, 
				            { 3, 6, 5, 3 }, 
				            { 1, 3, 3, 3 } };
		mapMarkMine = new int[mapMine.length][mapMine[0].length];
		int[][] output = new int[mapMine.length][mapMine[0].length];
		int i, j;

		for (i = 0; i < mapMine.length; i++) {
			for (j = 0; j < mapMine.length; j++) {
				mapMarkMine[i][j] = 0;
				output[i][j] = 0;
			}
		}
		System.out.println("########################### test 1 ################################");
		mineDetection(0, 1, 0, 0, mapMine, output);

	}

}
