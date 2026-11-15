package LeetCode;

import java.util.Arrays;

/*
 * url: https://leetcode.com/problems/minimum-moves-to-equal-array-elements/description/?envType=daily-question&envId=2026-11-01
 * Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.

In one move, you can increment n - 1 elements of the array by 1.

 

Example 1:

Input: nums = [1,2,3]
Output: 3
Explanation: Only three moves are needed (remember each move increments two elements):
[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
Example 2:

Input: nums = [1,1,1]
Output: 0
 

Constraints:

n == nums.length
1 <= nums.length <= 105
-109 <= nums[i] <= 109
The answer is guaranteed to fit in a 32-bit integer.
 */
public class Minimum_Moves_to_Equal_Array_Elements_453 {
	
	// input: [1, 3, 6, 8, 22]
    // [22, 24, 27, 29, 22]  22-1 = +21
    // [29, 31, 34, 29, 29]  29-22 = +7
    // [34, 36, 34, 34, 34]  34-29 = +5
    // [36, 36, 36, 36, 36]  36-34= +2
    // total : 35
    public int minMoves(int[] nums) {
        Arrays.sort(nums);
        int tmp = 0;
        int pre = 0;
        for(int ind = nums.length - 1; ind > -1; ind--){
            nums[ind] += tmp;
            pre = nums[ind] - nums[0];
            nums[0] += pre;
            tmp += pre;
        }

        return tmp;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
