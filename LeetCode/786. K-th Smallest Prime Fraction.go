/**
 url: https://leetcode.com/problems/k-th-smallest-prime-fraction/description/?envType=problem-list-v2&envId=binary-search
 
 You are given a sorted integer array arr containing 1 and prime numbers, where all the integers of arr are unique. You are also given an integer k.

For every i and j where 0 <= i < j < arr.length, we consider the fraction arr[i] / arr[j].

Return the kth smallest fraction considered. Return your answer as an array of integers of size 2, where answer[0] == arr[i] and answer[1] == arr[j].

 

Example 1:

Input: arr = [1,2,3,5], k = 3
Output: [2,5]
Explanation: The fractions to be considered in sorted order are:
1/5, 1/3, 2/5, 1/2, 3/5, and 2/3.
The third fraction is 2/5.
Example 2:

Input: arr = [1,7], k = 1
Output: [1,7]
 

Constraints:

2 <= arr.length <= 1000
1 <= arr[i] <= 3 * 104
arr[0] == 1
arr[i] is a prime number for i > 0.
All the numbers of arr are unique and sorted in strictly increasing order.
1 <= k <= arr.length * (arr.length - 1) / 2
 
**/

func kthSmallestPrimeFraction(arr []int, k int) []int {
    type KV struct {
        k string
        v float64
    }    
    var kv []KV
    for i := 0; i < len(arr)-1; i++ {
        for j := i+1; j < len(arr); j++ {
            kv = append(kv, KV{strconv.Itoa(arr[i]) + "+" + strconv.Itoa(arr[j]), float64(arr[i])/float64(arr[j])})
        }
    }
    sort.Slice(kv, func(i, j int) bool {
        return kv[i].v < kv[j].v
    })
    substrings := strings.Split(kv[k-1].k, "+") 

    a, _ := strconv.Atoi(substrings[0])
    b, _ := strconv.Atoi(substrings[1])

   
    return []int {a, b}
}