package CodeJavaEx;

public class SamSung_SMVC_Shipper {
	/**
	 * 
	 * @param road
	 * @param points
	 * @return
	 */
	static int Shipper(int[] road, int points) {

		int[][] graph = new int[points][points];
		int i, j, k = 0, time = 0;
		int[] isVisited = new int[points];
		for (i = 0; i < points; i++) {
			isVisited[i] = 0;
			for (j = i + 1; j < points; j++) {
				graph[i][j] = graph[j][i] = road[k];
				k++;
			}
		}

		i = 0;
		for (k = 0; k < points; k++) {
			int min = Integer.MAX_VALUE, pos = 0;
			isVisited[i] = 1;
			for (j = 0; j < points; j++) {
				if (isVisited[j] == 0 && min > graph[i][j]) {
					min = graph[i][j];
					pos = j;
				}
			}
			time += min == Integer.MAX_VALUE ? graph[0][i] : min;
			i = pos;
		}

		System.out.println("Min is " + time);
		return time;
	}

	public static void main(String[] args) {
		System.out.println("################ test 1 ###############");
		int[] road = { 1, 16, 8, 12, 32, 24, 3, 57, 19, 34 };
		int points = 5;
		Shipper(road, points);
	}

}
