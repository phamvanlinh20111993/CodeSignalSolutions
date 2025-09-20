package LeetCode;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Url: https://leetcode.com/problems/min-cost-to-connect-all-points/description/
 * 
 * You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].

The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.

Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.

 

Example 1:


Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
Output: 20
Explanation: 

We can connect the points as shown above to get the minimum cost of 20.
Notice that there is a unique path between every pair of points.
Example 2:

Input: points = [[3,12],[-2,5],[-4,1]]
Output: 18
 

Constraints:

1 <= points.length <= 1000
-106 <= xi, yi <= 106
All pairs (xi, yi) are distinct.
 */
public class Min_Cost_to_Connect_All_Points {

	public int distance(int x, int y, int x1, int y1) {
		return Math.abs(x - x1) + Math.abs(y1 - y);
	}

	record PointsDistance(int x, int y, int x1, int y1, int cost) {
	}

	// using Prim's algorithm
	public int minCostConnectPoints(int[][] points) {
		Set<String> isVisited = new HashSet<>();
		int x = points[0][0], y = points[0][1];

		PriorityQueue<PointsDistance> prioQueue = new PriorityQueue<>(new Comparator<PointsDistance>() {
			@Override
			public int compare(PointsDistance p1, PointsDistance p2) {
				return p1.cost - p2.cost;
			}
		});

		prioQueue.add(new PointsDistance(x, y, x, y, 0));
		int mCost = 0;
		while (isVisited.size() < points.length) {
			PointsDistance pointsDistance = prioQueue.poll();
			String key = pointsDistance.x + " " + pointsDistance.y;
			String key1 = pointsDistance.x1 + " " + pointsDistance.y1;

			if (isVisited.contains(key) && isVisited.contains(key1)) {
				continue;
			}

			mCost += pointsDistance.cost;
			// System.out.println(pointsDistance);

			if (!isVisited.contains(key)) {
				x = pointsDistance.x;
				y = pointsDistance.y;
			} else {
				x = pointsDistance.x1;
				y = pointsDistance.y1;
			}

			isVisited.add(x + " " + y);

			for (int ind = 0; ind < points.length; ind++) {
				if (!isVisited.contains(points[ind][0] + " " + points[ind][1])) {
					int cost = distance(x, y, points[ind][0], points[ind][1]);
					prioQueue.add(new PointsDistance(x, y, points[ind][0], points[ind][1], cost));
				}
			}
		}

		return mCost;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
