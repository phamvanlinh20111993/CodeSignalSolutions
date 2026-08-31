package LeetCode;

/**
 * url: https://leetcode.com/problems/removing-minimum-and-maximum-from-array/?envType=daily-question&envId=2026-08-30
 * 
 * You are given a 0-indexed array of distinct integers nums.

There is an element in nums that has the lowest value and an element that has the highest value. We call them the minimum and maximum respectively. Your goal is to remove both these elements from the array.

A deletion is defined as either removing an element from the front of the array or removing an element from the back of the array.

Return the minimum number of deletions it would take to remove both the minimum and maximum element from the array.

 

Example 1:

Input: nums = [2,10,7,5,4,1,8,6]
Output: 5
Explanation: 
The minimum element in the array is nums[5], which is 1.
The maximum element in the array is nums[1], which is 10.
We can remove both the minimum and maximum by removing 2 elements from the front and 3 elements from the back.
This results in 2 + 3 = 5 deletions, which is the minimum number possible.
Example 2:

Input: nums = [0,-4,19,1,8,-2,-3,5]
Output: 3
Explanation: 
The minimum element in the array is nums[1], which is -4.
The maximum element in the array is nums[2], which is 19.
We can remove both the minimum and maximum by removing 3 elements from the front.
This results in only 3 deletions, which is the minimum number possible.
Example 3:

Input: nums = [101]
Output: 1
Explanation:  
There is only one element in the array, which makes it both the minimum and maximum element.
We can remove it with 1 deletion.
 

Constraints:

1 <= nums.length <= 105
-105 <= nums[i] <= 105
The integers in nums are distinct.

 */
public class Removing_Minimum_and_Maximum_From_Array_2091 {

	record DT(int value, int index) {
	}

	public int minimumDeletions(int[] nums) {
		DT max = new DT(-1000000, -1);
		DT min = new DT(1000000, -1);
		int l = nums.length;

		for (int ind = 0; ind < l; ind++) {
			if (nums[ind] > max.value) {
				max = new DT(nums[ind], ind);
			}
			if (nums[ind] < min.value) {
				min = new DT(nums[ind], ind);
			}
		}

		// System.out.println("length: " + l);
		// System.out.println("max: " + max);
		// System.out.println("min: " + min);

		int amountP1 = 1 + Math.max(max.index, min.index);
		int amountP2 = 2 + Math.min(max.index, min.index) + (l - amountP1);
		int amountP3 = l - Math.min(max.index, min.index);

		return Math.min(amountP1, Math.min(amountP2, amountP3));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
