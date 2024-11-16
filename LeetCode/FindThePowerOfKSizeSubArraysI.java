package LeetCode;
/**
 * 
 * Url: https://leetcode.com/problems/find-the-power-of-k-size-subarrays-i/description/
 * 
 * You are given an array of integers nums of length n and a positive integer k.

The power of an array is defined as:

Its maximum element if all of its elements are consecutive and sorted in ascending order.
-1 otherwise.
You need to find the power of all 
subarrays
 of nums of size k.

Return an integer array results of size n - k + 1, where results[i] is the power of nums[i..(i + k - 1)].

 

Example 1:

Input: nums = [1,2,3,4,3,2,5], k = 3

Output: [3,4,-1,-1,-1]

Explanation:

There are 5 subarrays of nums of size 3:

[1, 2, 3] with the maximum element 3.
[2, 3, 4] with the maximum element 4.
[3, 4, 3] whose elements are not consecutive.
[4, 3, 2] whose elements are not sorted.
[3, 2, 5] whose elements are not consecutive.
Example 2:

Input: nums = [2,2,2,2,2], k = 4

Output: [-1,-1]

Example 3:

Input: nums = [3,2,3,2,3,2], k = 2

Output: [-1,3,-1,3,-1]

 

Constraints:

1 <= n == nums.length <= 500
1 <= nums[i] <= 105
1 <= k <= n
 */
public class FindThePowerOfKSizeSubArraysI {

	public int[] resultsArray(int[] nums, int k) {
		int[] result = new int[nums.length - k + 1];
		int p = 0;
		for (int ind = 0; ind < nums.length - k + 1; ind++) {
			boolean isSorted = true;
			for (int i = ind; i < ind + k; i++) {
				if (i < ind + k - 1 && nums[i] + 1 != nums[i + 1]) {
					isSorted = false;
					break;
				}
			}
			result[p++] = isSorted ? nums[ind + k - 1] : -1;
		}

		return result;
	}
	
	public int[] resultsArray_Improve(int[] nums, int k) {

        int [] result = new int[nums.length - k + 1];
        int p = 0;

        boolean isSorted = false;
        int failSortedInd = -1;
        for(int ind = 0; ind < nums.length - k + 1; ind++){

            // still true
            if(isSorted == true && nums[ind+k-1] - 1 == nums[ind+k-2]){
                result[p++] = nums[ind+k-1];
                continue;
            }
            
            // still false
            if(isSorted == false && ind < failSortedInd){
                result[p++] = -1;
                continue;
            }

            isSorted = true;
            for(int i = ind; i < ind+k; i++){
                if(i < ind + k - 1 &&nums[i] + 1 != nums[i+1]){
                    failSortedInd = i+1;
                    isSorted = false;
                    break;
                }
            }

            result[p++] = isSorted ? nums[ind+k-1] : -1; 
        } 

       return result;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
