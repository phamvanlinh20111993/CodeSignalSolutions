/**
url: https://leetcode.com/problems/count-number-of-nice-subarrays/?envType=problem-list-v2&envId=hash-table

Given an array of integers nums and an integer k. A continuous subarray is called nice if there are k odd numbers on it.

Return the number of nice sub-arrays.

 

Example 1:

Input: nums = [1,1,2,1,1], k = 3
Output: 2
Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
Example 2:

Input: nums = [2,4,6], k = 1
Output: 0
Explanation: There are no odd numbers in the array.
Example 3:

Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
Output: 16
 

Constraints:

1 <= nums.length <= 50000
1 <= nums[i] <= 10^5
1 <= k <= nums.length

*/


func numberOfSubarrays(nums []int, k int) int {
    var l int = len(nums)
    oldNumInds := []int{}

    for i:= 0; i < l; i++ {
        if nums[i] % 2 != 0 {
            oldNumInds = append(oldNumInds, i)
        }
    }
    
    l1 := len(oldNumInds)
    var res int = 0
    for i := k-1; i < l1; i++ {
        var pr int = oldNumInds[1+i-k]+1
        if i - k >= 0 {
            pr -= oldNumInds[i-k] + 1
        }
        
        var nt int = 0
        if i + 1 < l1 {
            nt = oldNumInds[i+1] - oldNumInds[i]
        }else {
            nt = l - oldNumInds[i]
        }
        res += pr*nt
    }


    return res
}