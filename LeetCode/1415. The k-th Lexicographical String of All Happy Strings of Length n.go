/**
url: https://leetcode.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/submissions/1843101041/?envType=problem-list-v2&envId=string

A happy string is a string that:

consists only of letters of the set ['a', 'b', 'c'].
s[i] != s[i + 1] for all values of i from 1 to s.length - 1 (string is 1-indexed).
For example, strings "abc", "ac", "b" and "abcbabcbcb" are all happy strings and strings "aa", "baa" and "ababbc" are not happy strings.

Given two integers n and k, consider a list of all happy strings of length n sorted in lexicographical order.

Return the kth string of this list or return an empty string if there are less than k happy strings of length n.

 

Example 1:

Input: n = 1, k = 3
Output: "c"
Explanation: The list ["a", "b", "c"] contains all happy strings of length 1. The third string is "c".
Example 2:

Input: n = 1, k = 4
Output: ""
Explanation: There are only 3 happy strings of length 1.
Example 3:

Input: n = 3, k = 9
Output: "cab"
Explanation: There are 12 different happy string of length 3 ["aba", "abc", "aca", "acb", "bab", "bac", "bca", "bcb", "cab", "cac", "cba", "cbc"]. You will find the 9th string = "cab"
 

Constraints:

1 <= n <= 10
1 <= k <= 100
 


**/
var result []string = []string{}

func recursive(s string, n int) {
    if n == 0 {
       result = append(result, s)
       return
    }

    if s == "" {
        recursive(s+"a", n-1)
        recursive(s+"b", n-1)
        recursive(s+"c", n-1)
    }else {
        if s[len(s)-1] == 'a' {
            recursive(s+"b", n-1)
            recursive(s+"c", n-1)
        }else 
        if s[len(s)-1] == 'b' {
            recursive(s+"a", n-1)
            recursive(s+"c", n-1)
        }else 
        if s[len(s)-1] == 'c' {
            recursive(s+"a", n-1)
            recursive(s+"b", n-1)
        }
    }
}

func getHappyString(n int, k int) string {
    result = []string{}

    if k > 3 * int(math.Pow(2, float64(n-1))){
        return ""
    }

    recursive("", n)

    return result[k-1]
}