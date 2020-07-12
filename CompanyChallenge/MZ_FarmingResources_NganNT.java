package CompanyChallenge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class MZ_FarmingResources_NganNT {

	static int[] neighBorHoodCoordX = { -1, 1, 0, 1, 0, -1 };
	static int[] neighBorHoodCoordY = { 0, 0, -1, -1, 1, 1 };
	static Set<String> impassableCoords;

	static boolean farmingResources(int[] friendlyTroops, int[] enemyTroops, int[] loggingCamp,
			int[][] impassableCells) {
		impassableCoords = new HashSet<>();
		for (int i = 0; i < impassableCells.length; i++) {
			impassableCoords.add(impassableCells[i][0] + "-" + impassableCells[i][1]);
		}

		int minPathFrientlyToLoggingCamp = bfs(friendlyTroops, loggingCamp);
		int minPathEnemyToLoggingCamp = bfs(enemyTroops, loggingCamp);
		return minPathFrientlyToLoggingCamp * friendlyTroops[2] < minPathEnemyToLoggingCamp * enemyTroops[2];
	}

	static int bfs(int[] startCoord, int[] endCoord) {
		Map<String, Integer> isVisited = new HashMap<String, Integer>();
		isVisited.put(startCoord[0] + "-" + startCoord[1], 0);
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] { startCoord[0], startCoord[1] });

		while (queue.size() > 0) {
			int[] coordChecked = queue.poll();
			String currentCoordStr = coordChecked[0] + "-" + coordChecked[1];
			if (coordChecked[0] == endCoord[0] && coordChecked[1] == endCoord[1]) {
				return isVisited.get(currentCoordStr);
			}
			for (int i = 0; i < 6; i++) {
				int x = coordChecked[0] + neighBorHoodCoordX[i];
				int y = coordChecked[1] + neighBorHoodCoordY[i];
				currentCoordStr = coordChecked[0] + "-" + coordChecked[1];
				String nextCoordStr = x + "-" + y;
				if (!impassableCoords.contains(nextCoordStr) && !isVisited.containsKey(nextCoordStr)) {
					queue.add(new int[] { x, y });
					isVisited.put(nextCoordStr, isVisited.get(currentCoordStr) + 1);
				}
			}
		}
		return 0;
	}

	public static void main(String[] args) {

		System.out.println("############# Test 1 ######################");
		int[] friendlyTroops = new int[] { -2, 2, 3 }, enemyTroops = new int[] { 1, 0, 9 },
				loggingCamp = new int[] { 0, 0 };

		int[][] impassableCells = new int[][] { { -1, 1 } };
		System.out.println(farmingResources(friendlyTroops, enemyTroops, loggingCamp, impassableCells));

		System.out.println("############# Test 2 ######################");
		int[] friendlyTroops1 = new int[] { -2, 2, 3 }, enemyTroops1 = new int[] { 1, 0, 9 },
				loggingCamp1 = new int[] { 0, 0 };

		int[][] impassableCells1 = new int[][] {};
		System.out.println(farmingResources(friendlyTroops1, enemyTroops1, loggingCamp1, impassableCells1));
	}

}
