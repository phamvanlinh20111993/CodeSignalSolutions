package LeetCode;

/**
 * url: https://leetcode.com/problems/minimum-size-subarray-sum/?envType=study-plan-v2&envId=top-interview-150
 * Given an array of positive integers nums and a positive integer target, return the minimal length of a 
subarray
 whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.

 

Example 1:

Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: The subarray [4,3] has the minimal length under the problem constraint.
Example 2:

Input: target = 4, nums = [1,4,4]
Output: 1
Example 3:

Input: target = 11, nums = [1,1,1,1,1,1,1,1]
Output: 0
 

Constraints:

1 <= target <= 109
1 <= nums.length <= 105
1 <= nums[i] <= 104
 

Follow up: If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log(n)).
 */
public class MinimumSizeSubArraySum {

	public int minSubArrayLen(int k, int[] nums) {
		int sumSlideW = 0;
		int startInd = 0;
		int size = nums.length + 10;
		for (int ind = 0; ind < nums.length; ind++) {
			sumSlideW += nums[ind];
			if (sumSlideW >= k) {
				while (startInd < ind && sumSlideW - nums[startInd] >= k) {
					sumSlideW -= nums[startInd];
					startInd++;
				}
				size = size > ind - startInd + 1 ? ind - startInd + 1 : size;
			}
		}

		return size == 10 + nums.length ? 0 : size;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
