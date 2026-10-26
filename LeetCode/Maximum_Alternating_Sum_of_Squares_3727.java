package LeetCode;

import java.util.Arrays;

/**
 * url: https://leetcode.com/problems/maximum-alternating-sum-of-squares/description/?envType=daily-question&envId=2026-10-13
 * 
 * You are given an integer array nums. You may rearrange the elements in any order.

The alternating score of an array arr is defined as:

score = arr[0]2 - arr[1]2 + arr[2]2 - arr[3]2 + ...
Return an integer denoting the maximum possible alternating score of nums after rearranging its elements.

 

Example 1:

Input: nums = [1,2,3]

Output: 12

Explanation:

A possible rearrangement for nums is [2,1,3], which gives the maximum alternating score among all possible rearrangements.

The alternating score is calculated as:

score = 22 - 12 + 32 = 4 - 1 + 9 = 12

Example 2:

Input: nums = [1,-1,2,-2,3,-3]

Output: 16

Explanation:

A possible rearrangement for nums is [-3,-1,-2,1,3,2], which gives the maximum alternating score among all possible rearrangements.

The alternating score is calculated as:

score = (-3)2 - (-1)2 + (-2)2 - (1)2 + (3)2 - (2)2 = 9 - 1 + 4 - 1 + 9 - 4 = 16

 

Constraints:

1 <= nums.length <= 105
-4 * 104 <= nums[i] <= 4 * 104
 */
public class Maximum_Alternating_Sum_of_Squares_3727 {

	public long maxAlternatingSum(int[] nums) {
		long res = 0L;
		int l = nums.length;
		for (int ind = 0; ind < l; ind++) {
			nums[ind] = nums[ind] < 0 ? 0 - nums[ind] : nums[ind];
		}

		Arrays.sort(nums);

		int left = 0;
		int right = l - 1;

		while (left < right) {
			res += nums[right] * nums[right] - nums[left] * nums[left];
			left++;
			right--;
		}

		if (l % 2 != 0) {
			res += nums[right] * nums[right];
		}

		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
