package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * url: https://leetcode.com/problems/find-the-minimum-and-maximum-number-of-nodes-between-critical-points/description/?envType=daily-question&envId=2026-08-31
 * 
 * A critical point in a linked list is defined as either a local maxima or a local minima.

A node is a local maxima if the current node has a value strictly greater than the previous node and the next node.

A node is a local minima if the current node has a value strictly smaller than the previous node and the next node.

Note that a node can only be a local maxima/minima if there exists both a previous node and a next node.

Given a linked list head, return an array of length 2 containing [minDistance, maxDistance] where minDistance is the minimum distance between any two distinct critical points and maxDistance is the maximum distance between any two distinct critical points. If there are fewer than two critical points, return [-1, -1].

 

Example 1:


Input: head = [3,1]
Output: [-1,-1]
Explanation: There are no critical points in [3,1].
Example 2:


Input: head = [5,3,1,2,5,1,2]
Output: [1,3]
Explanation: There are three critical points:
- [5,3,1,2,5,1,2]: The third node is a local minima because 1 is less than 3 and 2.
- [5,3,1,2,5,1,2]: The fifth node is a local maxima because 5 is greater than 2 and 1.
- [5,3,1,2,5,1,2]: The sixth node is a local minima because 1 is less than 5 and 2.
The minimum distance is between the fifth and the sixth node. minDistance = 6 - 5 = 1.
The maximum distance is between the third and the sixth node. maxDistance = 6 - 3 = 3.
Example 3:


Input: head = [1,3,2,2,3,2,2,2,7]
Output: [3,3]
Explanation: There are two critical points:
- [1,3,2,2,3,2,2,2,7]: The second node is a local maxima because 3 is greater than 1 and 2.
- [1,3,2,2,3,2,2,2,7]: The fifth node is a local maxima because 3 is greater than 2 and 2.
Both the minimum and maximum distances are between the second and the fifth node.
Thus, minDistance and maxDistance is 5 - 2 = 3.
Note that the last node is not considered a local maxima because it does not have a next node.
 

Constraints:

The number of nodes in the list is in the range [2, 105].
1 <= Node.val <= 105
 */
public class Find_the_Minimum_and_Maximum_Number_of_Nodes_Between_Critical_Points_2058 {
	
	public int[] nodesBetweenCriticalPoints(ListNode head) {
		int size = 0;
		ListNode ite = head;
		int pre = -1;
		int curr = -1;
		int next = -1;
		List<Integer> inds = new ArrayList<>();
		while (ite != null) {
			if (size == 0) {
				pre = ite.val;
			} else if (size == 1) {
				curr = ite.val;
			} else if (size == 2) {
				next = ite.val;
			} else {
				// System.out.println("pre: " + pre + ", curr: " + curr + ", next: " + next);
				if (curr > pre && curr > next) {
					inds.add(size);
				}
				if (curr < pre && curr < next) {
					inds.add(size);
				}
				pre = curr;
				curr = next;
				next = ite.val;
			}

			size++;
			ite = ite.next;
		}

		if (curr > pre && curr > next) {
			inds.add(size);
		}
		if (curr < pre && curr < next) {
			inds.add(size);
		}

		int s = inds.size();
		if (s < 2) {
			return new int[] { -1, -1 };
		}
		int m = 1000000;
		for (int ind = 1; ind < s; ind++) {
			m = m > inds.get(ind) - inds.get(ind - 1) ? inds.get(ind) - inds.get(ind - 1) : m;
		}

		return new int[] { m, inds.get(s - 1) - inds.get(0) };
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
