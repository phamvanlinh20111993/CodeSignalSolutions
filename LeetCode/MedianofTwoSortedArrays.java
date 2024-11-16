package LeetCode;

/**
 * Url: https://leetcode.com/problems/median-of-two-sorted-arrays/
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

The overall run time complexity should be O(log (m+n)).

 

Example 1:

Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.
Example 2:

Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 

Constraints:

nums1.length == m
nums2.length == n
0 <= m <= 1000
0 <= n <= 1000
1 <= m + n <= 2000
-106 <= nums1[i], nums2[i] <= 106
 */
public class MedianofTwoSortedArrays {
	
	 public double findMedianSortedArrays(int[] nums1, int[] nums2) {
	        int i = 0, j = 0, ind = 0,
	            mid = (nums1.length + nums2.length)/2;
	        int[] mergedSortArr = new int[mid+2];

	        while(i < nums1.length && j < nums2.length && ind < mergedSortArr.length){
	            if(nums1[i] <= nums2[j]){
	                mergedSortArr[ind++] = nums1[i++];
	            }else{
	                mergedSortArr[ind++] = nums2[j++];
	            }
	        }

	        while(i < nums1.length && ind < mergedSortArr.length){
	            mergedSortArr[ind++] = nums1[i++];
	        }

	        while(j < nums2.length && ind < mergedSortArr.length){
	            mergedSortArr[ind++] = nums2[j++];
	        }

	        if(mid == 0) return mergedSortArr[mid];
	        
	      //  for(ind = 0; ind < mergedSortArr.length; ind++){
	      //      System.out.print(mergedSortArr[ind] + ", ");
	      //  }

	        if((nums1.length + nums2.length) %2 != 0)
	            return mergedSortArr[mid];
	        else 
	            return (mergedSortArr[mid-1]+mergedSortArr[mid])/2d;
	    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
