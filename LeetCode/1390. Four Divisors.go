
/**
 url: https://leetcode.com/problems/four-divisors/?envType=daily-question&envId=2026-01-04
 
 Given an integer array nums, return the sum of divisors of the integers in that array that have exactly four divisors. If there is no such integer in the array, return 0.

 

Example 1:

Input: nums = [21,4,7]
Output: 32
Explanation: 
21 has 4 divisors: 1, 3, 7, 21
4 has 3 divisors: 1, 2, 4
7 has 2 divisors: 1, 7
The answer is the sum of divisors of 21 only.
Example 2:

Input: nums = [21,21]
Output: 64
Example 3:

Input: nums = [1,2,3,4,5]
Output: 0
 

Constraints:

1 <= nums.length <= 104
1 <= nums[i] <= 105

**/


func sumFourDivisors(nums []int) int {
    var divisors [2]int
    count := 0
    res := 0
    for _, num := range nums {
        count = 2
     //   for i := 2; i <= int(math.Sqrt(float64(num))); i++ {
        for i := 2; i*i <= num; i++ {
            if num % i == 0 {
                if i == num/i {
                    count++
                }else {
                    count += 2
                    divisors[0] = i
                    divisors[1] = num/i
                }
            }
            if count > 4 {
                break
            }
        }

        if count == 4 {
            res = res + 1 + num + divisors[0] + divisors[1]
        }
    }

    return res
}