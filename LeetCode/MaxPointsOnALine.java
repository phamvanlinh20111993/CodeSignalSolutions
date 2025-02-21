package LeetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Url: https://leetcode.com/problems/max-points-on-a-line/description/
 * 
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane, return the maximum number of points that lie on the same straight line.

 

Example 1:


Input: points = [[1,1],[2,2],[3,3]]
Output: 3
Example 2:


Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4
 

Constraints:

1 <= points.length <= 300
points[i].length == 2
-104 <= xi, yi <= 104
All the points are unique.
 */
public class MaxPointsOnALine {
	
	public int findGCD(int n, int n1) {
		return n1 == 0 ? n : findGCD(n1, n % n1);
	}

	public int[] findStraightLineIndex(int x, int y, int x1, int y1) {
		int vX = -(y1 - y), vY = x1 - x;
		return new int[] { vX, vY, -vX * x - vY * y };
	}

	public int[] optimalIndex(int[] lineIndex) {
		int index = Math.abs(findGCD(lineIndex[0], findGCD(lineIndex[1], lineIndex[2])));
		return new int[] { lineIndex[0] / index, lineIndex[1] / index, lineIndex[2] / index };
	}

	public int maxPoints(int[][] points) {

		if (points.length < 3)
			return points.length;
		Map<String, Set<String>> countSame = new HashMap<>();

		int maxPoint = 1; // 1 <= points.length <= 300
		for (int ind = 0; ind < points.length - 1; ind++) {
			for (int j = ind + 1; j < points.length; j++) {
				int[] dt = optimalIndex(
						findStraightLineIndex(points[ind][0], points[ind][1], points[j][0], points[j][1]));
				String key = dt[0] + "|" + dt[1] + "|" + dt[2];

				String v1 = points[ind][0] + "|" + points[ind][1], v2 = points[j][0] + "|" + points[j][1];
				Set<String> val = countSame.getOrDefault(key, new HashSet<>());
				val.add(v1);
				val.add(v2);

				countSame.put(key, val);
			}
		}

		for (Map.Entry<String, Set<String>> entry : countSame.entrySet()) {
			int size = entry.getValue().size();
			maxPoint = maxPoint < size ? size : maxPoint;
		}

		return maxPoint;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
