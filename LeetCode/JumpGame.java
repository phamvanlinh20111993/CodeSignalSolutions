package LeetCode;

import java.util.Stack;

/**
 * Url: https://leetcode.com/problems/jump-game/description/
 * 
 * You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.

Return true if you can reach the last index, or false otherwise.

 

Example 1:

Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 

Constraints:

1 <= nums.length <= 104
0 <= nums[i] <= 105
 * 
 */
public class JumpGame {

	public boolean canJump(int[] nums) {
		Stack<Integer[]> stIndex = new Stack<>();
		stIndex.push(new Integer[] { 1, nums[0] });
		byte[] isVisited = new byte[nums.length];
		while (stIndex.size() > 0) {
			Integer[] maxPos = stIndex.pop();
			isVisited[maxPos[0] - 1] = 1;
			if (maxPos[0] + maxPos[1] >= nums.length) {
				return true;
			}
			for (int nextJump = 1; nextJump <= maxPos[1]; nextJump++) {
				if (isVisited[maxPos[0] + nextJump] == 0) {
					stIndex.push(new Integer[] { maxPos[0] + nextJump, nums[maxPos[0] + nextJump - 1] });
				}
			}
		}

		return false;
	}

}
