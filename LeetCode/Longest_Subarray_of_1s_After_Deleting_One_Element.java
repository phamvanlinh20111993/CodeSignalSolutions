package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * url: https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/description/?envType=daily-question&envId=2025-08-24
 * 
 * Given a binary array nums, you should delete one element from it.

Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0 if there is no such subarray.

 

Example 1:

Input: nums = [1,1,0,1]
Output: 3
Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
Example 2:

Input: nums = [0,1,1,1,0,1,1,0,1]
Output: 5
Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].
Example 3:

Input: nums = [1,1,1]
Output: 2
Explanation: You must delete one element.
 

Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.
 * 
 */
public class Longest_Subarray_of_1s_After_Deleting_One_Element {
	
	public record Data(int st, int end) {
	}

	public int longestSubarray(int[] nums) {

		List<Data> dt = new ArrayList<>();
		int st = -1;
		for (int ind = 0; ind < nums.length; ind++) {
			if (nums[ind] == 1) {
				st = st == -1 ? ind : st;
			} else {
				if (st > -1) {
					Data d = new Data(st, ind);
					dt.add(d);
				}
				st = -1;
			}
		}

		if (st > -1) {
			Data d = new Data(st, nums.length);
			dt.add(d);
		}

		int max = dt.size() > 0 ? dt.get(0).end - dt.get(0).st : 0;
		for (int ind = 1; ind < dt.size(); ind++) {
			if (dt.get(ind - 1).end + 1 == dt.get(ind).st) {
				if (max < dt.get(ind).end - dt.get(ind - 1).st) {
					max = dt.get(ind).end - dt.get(ind - 1).st - 1;
				}
			}
			if (max < dt.get(ind).end - dt.get(ind).st) {
				max = dt.get(ind).end - dt.get(ind).st;
			}
		}

		return max == nums.length ? max - 1 : max;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
