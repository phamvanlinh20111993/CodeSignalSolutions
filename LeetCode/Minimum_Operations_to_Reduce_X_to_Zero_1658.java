package LeetCode;

/**
 * url: https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/description/?envType=daily-question&envId=2026-09-23
 * 
 * You are given an integer array nums and an integer x. In one operation, you can either remove the leftmost or the rightmost element from the array nums and subtract its value from x. Note that this modifies the array for future operations.

Return the minimum number of operations to reduce x to exactly 0 if it is possible, otherwise, return -1.

 

Example 1:

Input: nums = [1,1,4,2,3], x = 5
Output: 2
Explanation: The optimal solution is to remove the last two elements to reduce x to zero.
Example 2:

Input: nums = [5,6,7,8,9], x = 4
Output: -1
Example 3:

Input: nums = [3,2,20,1,1,3], x = 10
Output: 5
Explanation: The optimal solution is to remove the last three elements and the first two elements (5 operations in total) to reduce x to zero.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 104
1 <= x <= 109
 * 
 */
public class Minimum_Operations_to_Reduce_X_to_Zero_1658 {
	
	public int minOperations(int[] nums, int x) {
		int minOp = 1000000;
		int left = -1;
		int l = nums.length - 1;
		int right = l;

		int currentSum = 0;
		for (int ind = 0; ind < l; ind++) {
			if (currentSum + nums[ind] > x) {
				break;
			}
			currentSum += nums[ind];
			left = ind;
		}

		while (right > -1 && currentSum + nums[right] <= x && right > left) {
			currentSum += nums[right];
			right--;
		}
		minOp = currentSum == x ? 1 + l - right + left : minOp;

		while (left > -1 && right > left) {
			// System.out.println("left="+left + ", right=" + right+", currentSum=" +
			// currentSum);
			if (left > -1) {
				currentSum -= nums[left--];
			}
			while (currentSum + nums[right] <= x && right > left) {
				currentSum += nums[right];
				right--;
			}
			if (currentSum == x) {
				minOp = Math.min(minOp, 1 + l - right + left);
			}
		}

		return minOp == 1000000 ? -1 : minOp;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
