package LeetCode;

import java.util.List;

/**
 * Url: https://leetcode.com/problems/adjacent-increasing-subarrays-detection-ii/description/?envType=daily-question&envId=2025-10-15
 * 
 * Given an array nums of n integers, your task is to find the maximum value of k for which there exist two adjacent subarrays of length k each, such that both subarrays are strictly increasing. Specifically, check if there are two subarrays of length k starting at indices a and b (a < b), where:

Both subarrays nums[a..a + k - 1] and nums[b..b + k - 1] are strictly increasing.
The subarrays must be adjacent, meaning b = a + k.
Return the maximum possible value of k.

A subarray is a contiguous non-empty sequence of elements within an array.

 

Example 1:

Input: nums = [2,5,7,8,9,2,3,4,3,1]

Output: 3

Explanation:

The subarray starting at index 2 is [7, 8, 9], which is strictly increasing.
The subarray starting at index 5 is [2, 3, 4], which is also strictly increasing.
These two subarrays are adjacent, and 3 is the maximum possible value of k for which two such adjacent strictly increasing subarrays exist.
Example 2:

Input: nums = [1,2,3,4,4,4,4,5,6,7]

Output: 2

Explanation:

The subarray starting at index 0 is [1, 2], which is strictly increasing.
The subarray starting at index 2 is [3, 4], which is also strictly increasing.
These two subarrays are adjacent, and 2 is the maximum possible value of k for which two such adjacent strictly increasing subarrays exist.
 

Constraints:

2 <= nums.length <= 2 * 105
-109 <= nums[i] <= 109
 */
public class Adjacent_Increasing_Subarrays_Detection_II {

	public int maxIncreasingSubarrays(List<Integer> nums) {
		int l = nums.size();

		int left = -1, max = 1;
		for (int ind = 0; ind < l;) {
			int p = ind + 1;
			while (p < l && nums.get(p - 1) < nums.get(p))
				p++;

			if (p - ind == 1) {
				ind++;
				max = Math.max(left / 2, max);
				left = -1;
				continue;
			}

			if (left == -1) {
				left = p - ind;
				max = Math.max(left / 2, max);
			} else {
				if (p - ind > 0) {
					max = Math.max(max, Math.min(left, p - ind));
					max = Math.max(max, left / 2);
					left = p - ind;
				} else {
					left = -1;
				}
			}
			ind = p;
		}

		max = Math.max(left / 2, max);

		return max;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
