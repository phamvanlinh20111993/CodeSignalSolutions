package LeetCode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * This solution is base on my FE developer colleague Thomas Nguyen in the same project (10/03/2025)
 * 
 * Idea:
 * 1. Initial block real border to priority Queue p (sort by ascending order)
 * 2. for loop while p is not null, get the lowest border and check the surrounding point
 *    2.0. Initial a variable minHeightBorder to mark it as lowest border, this variable only update
 *    2.1. if it is less than current border we can add it to result then add to the queue p
 *         else we can mark it as a new border then add to the queue p
 *    2.2. Update the minHeightBorder if data in queue is actually a border
 * 
 * 
 * 
 * 
 * Url: https://leetcode.com/problems/trapping-rain-water-ii/?envType=daily-question&envId=2025-10-03
 * 
 * Given an m x n integer matrix heightMap representing the height of each unit cell in a 2D elevation map, return the volume of water it can trap after raining.

 

Example 1:


Input: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
Output: 4
Explanation: After the rain, water is trapped between the blocks.
We have two small ponds 1 and 3 units trapped.
The total volume of water trapped is 4.
Example 2:


Input: heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
Output: 10
 

Constraints:

m == heightMap.length
n == heightMap[i].length
1 <= m, n <= 200
0 <= heightMap[i][j] <= 2 * 104
 

 */
public class TrappingRainWaterII {

	record Point(int x, int y, boolean isBorder, int heightVal) {
	}

	boolean isInBlock(int x, int y, int xL, int yL) {
		return x > -1 && x < xL && y > -1 && y < yL;
	}

	boolean isBorder(int x, int y, int xL, int yL) {
		return y == 0 || y == yL - 1 || x == 0 || x == xL - 1;
	}

	public int trapRainWater(int[][] heightMap) {
		int yL = heightMap.length;
		int xL = heightMap[0].length;

		PriorityQueue<Point> priorityQueue = new PriorityQueue<Point>(new Comparator<Point>() {
			@Override
			public int compare(Point a, Point b) {
				return a.heightVal - b.heightVal;
			}
		});
		boolean[][] isVisited = new boolean[yL][xL];
		final int[][] D = new int[][] { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

		for (int y = 0; y < yL; y++) {
			for (int x = 0; x < xL; x++) {
				if (isBorder(x, y, xL, yL)) {
					priorityQueue.offer(new Point(x, y, true, heightMap[y][x]));
				}
			}
		}

//		System.out.println(priorityQueue);

		int minHeight = -1;
		int res = 0;
		while (priorityQueue.size() > 0) {
			Point point = priorityQueue.poll();
			if (isVisited[point.y][point.x])
				continue;

			// System.out.println("point=" + point);

			isVisited[point.y][point.x] = true;

			if (point.isBorder) {
				minHeight = point.heightVal;
			} else {
				res += minHeight - heightMap[point.y][point.x];
				// System.out.println("Add " + res + ", minHeight= " + minHeight + ", point=" +
				// point);
			}

			for (int[] d : D) {
				int xT = d[0] + point.x;
				int yT = d[1] + point.y;

				if (isInBlock(xT, yT, xL, yL) && !isVisited[yT][xT]) {
					if (minHeight > heightMap[yT][xT]) {
						if (!isBorder(xT, yT, xL, yL)) {
							priorityQueue.offer(new Point(xT, yT, false, heightMap[yT][xT]));
						}
					} else {
						priorityQueue.offer(new Point(xT, yT, true, heightMap[yT][xT]));
					}
				}
			}
		}

		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
