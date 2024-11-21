package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Url: https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/description/?envType=daily-question&envId=2024-11-19
 * 
 * You are given an integer array nums and an integer k. Find the maximum subarray sum of all the subarrays of nums that meet the following conditions:

The length of the subarray is k, and
All the elements of the subarray are distinct.
Return the maximum subarray sum of all the subarrays that meet the conditions. If no subarray meets the conditions, return 0.

A subarray is a contiguous non-empty sequence of elements within an array.

 

Example 1:

Input: nums = [1,5,4,2,9,9,9], k = 3
Output: 15
Explanation: The subarrays of nums with length 3 are:
- [1,5,4] which meets the requirements and has a sum of 10.
- [5,4,2] which meets the requirements and has a sum of 11.
- [4,2,9] which meets the requirements and has a sum of 15.
- [2,9,9] which does not meet the requirements because the element 9 is repeated.
- [9,9,9] which does not meet the requirements because the element 9 is repeated.
We return 15 because it is the maximum subarray sum of all the subarrays that meet the conditions
Example 2:

Input: nums = [4,4,4], k = 3
Output: 0
Explanation: The subarrays of nums with length 3 are:
- [4,4,4] which does not meet the requirements because the element 4 is repeated.
We return 0 because no subarrays meet the conditions.
 

Constraints:

1 <= k <= nums.length <= 105
1 <= nums[i] <= 105
 */
public class MaxiumSumOfDistincTSubArraysWithLengthK {

	public long maximumSubarraySum(int[] nums, int k) {
		long sum = 0L;
		long[] prefixS = new long[nums.length];
		prefixS[0] = nums[0];
		for (int ind = 1; ind < nums.length; ind++) {
			prefixS[ind] += prefixS[ind - 1] + nums[ind];
		}

		Map<Integer, Integer> dup = new HashMap<>();
		int left = 0, dupInd = -1;
		for (int ind = 0; ind < nums.length; ind++) {
			if (dup.containsKey(nums[ind])) {
				dupInd = dupInd < dup.get(nums[ind]) ? dup.get(nums[ind]) : dupInd;
			}
			dup.put(nums[ind], ind);
			if (ind - left + 1 == k) {
				if (left > dupInd) {
					long tmpS = prefixS[ind] - (left == 0 ? 0 : prefixS[left - 1]);
					sum = sum < tmpS ? tmpS : sum;
				}
				if (dup.get(nums[left]) == left) {
					dup.remove(nums[left]);
				}
				left++;
			}
		}

		return sum;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
