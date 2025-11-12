/**
  url: https://leetcode.com/problems/partition-labels/description/?envType=problem-list-v2&envId=hash-table
  
  You are given a string s. We want to partition the string into as many parts as possible so that each letter appears in at most one part. For example, the string "ababcc" can be partitioned into ["abab", "cc"], but partitions such as ["aba", "bcc"] or ["ab", "ab", "cc"] are invalid.

Note that the partition is done so that after concatenating all the parts in order, the resultant string should be s.

Return a list of integers representing the size of these parts.

 

Example 1:

Input: s = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.
Example 2:

Input: s = "eccbbbbdec"
Output: [10]
 

Constraints:

1 <= s.length <= 500
s consists of lowercase English letters.
 
**/

func partitionLabels(s string) []int {
    m := make(map[rune]int)
    for p, v := range s {
      m[v] = p
    }

    res := []int{}
    var count int = 1
    var maxL = -1
    for p, v := range s {
        if maxL < m[v] {
            maxL = m[v]
        }

        if p < maxL {
            count++
        }else{
           maxL = -1 
           res = append(res, count)
           count = 1
        }
    }

    return res
}