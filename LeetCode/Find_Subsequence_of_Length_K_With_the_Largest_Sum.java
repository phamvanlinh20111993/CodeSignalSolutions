package LeetCode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * url: https://leetcode.com/problems/find-subsequence-of-length-k-with-the-largest-sum/?envType=daily-question&envId=2025-06-28
 * You are given an integer array nums and an integer k. You want to find a subsequence of nums of length k that has the largest sum.

Return any such subsequence as an integer array of length k.

A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.

 

Example 1:

Input: nums = [2,1,3,3], k = 2
Output: [3,3]
Explanation:
The subsequence has the largest sum of 3 + 3 = 6.
Example 2:

Input: nums = [-1,-2,3,4], k = 3
Output: [-1,3,4]
Explanation: 
The subsequence has the largest sum of -1 + 3 + 4 = 6.
Example 3:

Input: nums = [3,4,3,3], k = 2
Output: [3,4]
Explanation:
The subsequence has the largest sum of 3 + 4 = 7. 
Another possible subsequence is [4, 3].
 

Constraints:

1 <= nums.length <= 1000
-105 <= nums[i] <= 105
1 <= k <= nums.length
 */
public class Find_Subsequence_of_Length_K_With_the_Largest_Sum {
	
	record Data(int value, int index) {
	}

	public int[] maxSubsequence(int[] nums, int k) {

		int[] res = new int[k];
		Data[] ex = new Data[nums.length];
		for (int ind = 0; ind < nums.length; ind++) {
			ex[ind] = new Data(nums[ind], ind);
		}
		Arrays.sort(ex, Comparator.comparing(item -> item.value));
		int c = 0;
		for (int ind = ex.length - 1; ind >= ex.length - k; ind--) {
			res[c++] = ex[ind].index;
		}

		Arrays.sort(res);
		for (int ind = 0; ind < k; ind++) {
			res[ind] = nums[res[ind]];
		}

		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
