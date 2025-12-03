/**
	url: https://leetcode.com/problems/generate-binary-strings-without-adjacent-zeros/description/?envType=problem-list-v2&envId=string
	
	
	You are given a positive integer n.

A binary string x is valid if all substrings of x of length 2 contain at least one "1".

Return all valid strings with length n, in any order.

 

Example 1:

Input: n = 3

Output: ["010","011","101","110","111"]

Explanation:

The valid strings of length 3 are: "010", "011", "101", "110", and "111".

Example 2:

Input: n = 1

Output: ["0","1"]

Explanation:

The valid strings of length 1 are: "0" and "1".

 

Constraints:

1 <= n <= 18
 

**/


var count []string = []string{}

func backtracking(s string, len int, n int) {
    if len >= n {
        count = append(count, s)
        return
    }
    if len==0 || s[len-1] == 49 {
        backtracking(s+"0", len+1, n)
        backtracking(s+"1", len+1, n)
    }else {
        backtracking(s+"1", len+1, n)
    }
}

func validStrings(n int) []string {
    count = []string{}
    backtracking("", 0, n)
    return count
}