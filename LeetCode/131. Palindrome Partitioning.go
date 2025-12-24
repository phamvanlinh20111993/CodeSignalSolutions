/**
	url: https://leetcode.com/problems/palindrome-partitioning/?envType=problem-list-v2&envId=backtracking
	Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.

 

Example 1:

Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]
Example 2:

Input: s = "a"
Output: [["a"]]
 

Constraints:

1 <= s.length <= 16
s contains only lowercase English letters.
 

**/

func isPalindrome(str []rune) bool {
    l := len(str)
    if l < 2 {
        return true
    }
    for i := 0; i < l/2; i++ {
        if str[i] != str[l-i-1] {
            return false
        }
    } 
    return true
}

func partition(s string) [][]string {
    allPossible := [][]string{}

    strChar := []string{}
    for _, v := range s {
        strChar = append(strChar, string(v))
    }
    var backtracking func(strChar []string, next []string, pos int)
    backtracking = func (strChar []string, next []string, pos int) {
            if pos >= len(strChar) {
                allPossible = append(allPossible, next)
                return
            }

            r := []rune{}
            for i:= pos; i < len(strChar); i++ {
                r = append(r, []rune(strChar[i])[0])
                if isPalindrome(r) {
                    cloned := make([]string, len(next))
                    copy(cloned, next)
                    backtracking(strChar, append(cloned, string(r)), i+1)
                }
            } 
        }

    backtracking (strChar , []string{}, 0)

    return allPossible
}