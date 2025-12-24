/**
	url: https://leetcode.com/problems/letter-case-permutation/?envType=problem-list-v2&envId=backtracking
	
	Given a string s, you can transform every letter individually to be lowercase or uppercase to create another string.

Return a list of all possible strings we could create. Return the output in any order.

 

Example 1:

Input: s = "a1b2"
Output: ["a1b2","a1B2","A1b2","A1B2"]
Example 2:

Input: s = "3z4"
Output: ["3z4","3Z4"]
 

Constraints:

1 <= s.length <= 12
s consists of lowercase English letters, uppercase English letters, and digits.

**/
var response []string

func recursion (s string, runes [] rune, ind int) {
    if ind >= len(runes) {
        var pos int = 0
        res := []rune{} 
        for _, v := range s {
            if !unicode.IsDigit(v) {
                res = append(res, runes[pos])
                pos++
                continue
            }
            res = append(res, v)
        }
        response = append(response, string(res))
        return
    }
    
    recursion(s, runes, ind+1)
    // lowercase
    if runes[ind] >= 97 {
        runes[ind] = unicode.ToUpper(runes[ind])
    }else {
        runes[ind] = unicode.ToLower(runes[ind])
    }
    recursion(s, runes, ind+1)
    if runes[ind] < 97 {
        runes[ind] = unicode.ToUpper(runes[ind])
    }else {
        runes[ind] = unicode.ToLower(runes[ind])
    }
}

func letterCasePermutation(s string) []string {
    runes := []rune{}
    for _, v := range s {
        if !unicode.IsDigit(v) {
            runes = append(runes, v)
        }
    }
    response = []string{}
    recursion(s, runes, 0)

    return response
}