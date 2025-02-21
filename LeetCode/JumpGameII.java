package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Url: https://leetcode.com/problems/jump-game-ii/description/
 * 
 * You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].

Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at nums[i], you can jump to any nums[i + j] where:

0 <= j <= nums[i] and
i + j < n
Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].

 

Example 1:

Input: nums = [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [2,3,0,1,4]
Output: 2
 

Constraints:

1 <= nums.length <= 104
0 <= nums[i] <= 1000
It's guaranteed that you can reach nums[n - 1].
 * 
 */
public class JumpGameII {
	
	public int jump(int[] nums) {

		if (nums.length == 1)
			return 0;

		Queue<Integer[]> qIndex = new LinkedList<>();
		qIndex.add(new Integer[] { 1, nums[0], 1 });
		byte[] isVisited = new byte[nums.length];
		isVisited[0] = 1;
		while (qIndex.size() > 0) {
			Integer[] maxPos = qIndex.poll();

			if (maxPos[0] + maxPos[1] >= nums.length) {
				return maxPos[2];
			}
			for (int nextJump = 1; nextJump <= maxPos[1]; nextJump++) {
				if (isVisited[maxPos[0] + nextJump] == 0) {
					qIndex.add(new Integer[] { maxPos[0] + nextJump, nums[maxPos[0] + nextJump - 1], maxPos[2] + 1 });
					isVisited[maxPos[0] + nextJump - 1] = 1;
				}
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
