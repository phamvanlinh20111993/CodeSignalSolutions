package LeetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Url: https://leetcode.com/problems/cinema-seat-allocation/description/?envType=daily-question&envId=2026-08-19
 * 
 * A cinema has n rows of seats, numbered from 1 to n. Each row has 10 seats, numbered from 1 to 10.

You are given a 2D integer array reservedSeats, where reservedSeats[i] = [rowi, seati] means that seat seati in row rowi is already reserved.

A four-person group must be assigned to four seats in the same row. The group can be seated in one of the following seat blocks:

seats 2, 3, 4, 5
seats 4, 5, 6, 7
seats 6, 7, 8, 9
A block can be used only if none of its seats are reserved. Each seat can be assigned to at most one group.

Return an integer denoting the maximum number of four-person groups that can be assigned.

 

Example 1:



Input: n = 3, reservedSeats = [[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]]
Output: 4
Explanation: The figure above shows an optimal allocation of four groups. Seats marked in blue are already reserved, and each set of four contiguous seats marked in orange is assigned to one group.
Example 2:

Input: n = 2, reservedSeats = [[2,1],[1,8],[2,6]]
Output: 2
Example 3:

Input: n = 4, reservedSeats = [[4,3],[1,4],[4,6],[1,7]]
Output: 4
 

Constraints:

1 <= n <= 109
1 <= reservedSeats.length <= min(10 * n, 104)
reservedSeats[i] == [rowi, seati]
1 <= rowi <= n
1 <= seati <= 10
All reservedSeats[i] are distinct.
 */
public class Cinema_Seat_Allocation_1386 {
	
	public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
		Map<Integer, Set<Integer>> mapSeatByRow = new HashMap<>();
		for (int[] row : reservedSeats) {
			Set<Integer> seatIds = mapSeatByRow.getOrDefault(row[0], new HashSet<>());
			seatIds.add(row[1]);
			mapSeatByRow.put(row[0], seatIds);
		}

		Integer currentAddedGroups = 2 * (n - mapSeatByRow.size());
		for (Integer rowNum : mapSeatByRow.keySet()) {
			Set<Integer> takenSeats = mapSeatByRow.get(rowNum);
			if (takenSeats == null) {
				currentAddedGroups += 2;
				continue;
			}
			boolean isTaken = false;
			if (!takenSeats.contains(2) && !takenSeats.contains(3) && !takenSeats.contains(4)
					&& !takenSeats.contains(5)) {
				currentAddedGroups++;
				isTaken = true;
			}
			if (!takenSeats.contains(6) && !takenSeats.contains(7) && !takenSeats.contains(8)
					&& !takenSeats.contains(9)) {
				currentAddedGroups++;
				isTaken = true;
			}
			if (!isTaken && !takenSeats.contains(4) && !takenSeats.contains(5) && !takenSeats.contains(6)
					&& !takenSeats.contains(7)) {
				currentAddedGroups++;
			}
		}

		return currentAddedGroups;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
