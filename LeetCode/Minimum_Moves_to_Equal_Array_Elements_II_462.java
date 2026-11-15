package LeetCode;

import java.util.Arrays;

/**
 * url: https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/description/?envType=daily-question&envId=2026-11-02
 * 
 * Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.

In one move, you can increment or decrement an element of the array by 1.

Test cases are designed so that the answer will fit in a 32-bit integer.

 

Example 1:

Input: nums = [1,2,3]
Output: 2
Explanation:
Only two moves are needed (remember each move increments or decrements one element):
[1,2,3]  =>  [2,2,3]  =>  [2,2,2]
Example 2:

Input: nums = [1,10,2,9]
Output: 16
 

Constraints:

n == nums.length
1 <= nums.length <= 105
-109 <= nums[i] <= 109
 */
public class Minimum_Moves_to_Equal_Array_Elements_II_462 {

	public int minMoves2(int[] nums) {
		int sum = 0;
		int l = nums.length;
		for (int ind = 0; ind < l; ind++) {
			sum += nums[ind];
		}

		int med = sum / l;
		int res = 0;
		Arrays.sort(nums);
		int[] lack = new int[l];

		int pos = 0;
		int neg = 0;
		for (int ind = 0; ind < l; ind++) {
			lack[ind] = med - nums[ind];
			pos = lack[ind] > 0 ? pos + 1 : pos;
			neg = lack[ind] < 0 ? neg + 1 : neg;
			res += Math.abs(med - nums[ind]);
		}

		if (Math.abs(pos - neg) == 0) {
			return res;
		}

		if (pos > neg) {
			int start = pos - 1;
			int take = 0;
			int pre = 0;
			while (start >= l / 2) {
				lack[start] -= pre;
				take += lack[start] * (start - (l - start) + 2);
				pre += lack[start];
				start--;
			}
			return res - take;
		} else {
			int start = l - neg;
			int take = 0;
			int pre = 0;
			while (start <= (l - 1) / 2) {
				lack[start] += pre;
				take += -lack[start] * (l - 2 * start);
				pre += -lack[start];
				start++;
			}
			return res - take;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
