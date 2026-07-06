package LeetCode;

import java.util.Arrays;

/**
 * url: https://leetcode.com/problems/remove-covered-intervals/description/?envType=daily-question&envId=2026-07-06
 * 
 * Given an array intervals where intervals[i] = [li, ri] represent the interval [li, ri), remove all intervals that are covered by another interval in the list.

The interval [a, b) is covered by the interval [c, d) if and only if c <= a and b <= d.

Return the number of remaining intervals.

 

Example 1:

Input: intervals = [[1,4],[3,6],[2,8]]
Output: 2
Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.
Example 2:

Input: intervals = [[1,4],[2,3]]
Output: 1
 

Constraints:

1 <= intervals.length <= 1000
intervals[i].length == 2
0 <= li < ri <= 105
All the given intervals are unique.
 * 
 */
public class Remove_Covered_Intervals_1288 {

	public int removeCoveredIntervals(int[][] intervals) {
		Arrays.sort(intervals, (a, b) -> {
			if (a[0] != b[0]) {
				return Integer.compare(a[0], b[0]);
			}
			return Integer.compare(b[1], a[1]);
		});

		int res = intervals.length;

		for (int ind = intervals.length - 1; ind >= 0; ind--) {
			for (int j = 0; j < ind; j++) {
				if (intervals[ind][0] >= intervals[j][0] && intervals[ind][1] <= intervals[j][1]) {
					res--;
					break;
				}
			}
		}

		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
