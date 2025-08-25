package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * url: https://leetcode.com/problems/number-of-zero-filled-subarrays/description/?envType=daily-question&envId=2025-08-19
 * 
 * Given an integer array nums, return the number of subarrays filled with 0.

A subarray is a contiguous non-empty sequence of elements within an array.

 

Example 1:

Input: nums = [1,3,0,0,2,0,0,4]
Output: 6
Explanation: 
There are 4 occurrences of [0] as a subarray.
There are 2 occurrences of [0,0] as a subarray.
There is no occurrence of a subarray with a size more than 2 filled with 0. Therefore, we return 6.
Example 2:

Input: nums = [0,0,0,2,0,0]
Output: 9
Explanation:
There are 5 occurrences of [0] as a subarray.
There are 3 occurrences of [0,0] as a subarray.
There is 1 occurrence of [0,0,0] as a subarray.
There is no occurrence of a subarray with a size more than 3 filled with 0. Therefore, we return 9.
Example 3:

Input: nums = [2,10,2019]
Output: 0
Explanation: There is no subarray filled with 0. Therefore, we return 0.
 

Constraints:

1 <= nums.length <= 105
-109 <= nums[i] <= 109
 */
public class Number_of_Zero_Filled_Subarrays {
	
	 public long zeroFilledSubarray(int[] nums) {
	        List<Integer> conAmount = new ArrayList<>();
	        int c = 0;
	        for(int ind = 0; ind < nums.length; ind++){
	            if(nums[ind] == 0){
	                c++;
	            }else{
	                if(c > 0){
	                    conAmount.add(c);
	                }
	                c = 0;
	            }
	        }

	        if(c > 0) conAmount.add(c);

	        long res = 0L;
	        for(Integer a : conAmount){
	            res += ((long)a*a + a)/2;
	        }


	        return res;
	    }
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
