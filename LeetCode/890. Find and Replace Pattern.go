/**
 url: https://leetcode.com/problems/find-and-replace-pattern/description/?envType=problem-list-v2&envId=string
 Given a list of strings words and a string pattern, return a list of words[i] that match pattern. You may return the answer in any order.

A word matches the pattern if there exists a permutation of letters p so that after replacing every letter x in the pattern with p(x), we get the desired word.

Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another letter, and no two letters map to the same letter.

 

Example 1:

Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
Output: ["mee","aqq"]
Explanation: "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}. 
"ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation, since a and b map to the same letter.
Example 2:

Input: words = ["a","b","c"], pattern = "a"
Output: ["a","b","c"]
 

Constraints:

1 <= pattern.length <= 20
1 <= words.length <= 50
words[i].length == pattern.length
pattern and words[i] are lowercase English letters.
**/

func findAndReplacePattern(words []string, pattern string) []string {
    var l int = len(pattern)

    var res []string = []string{}
    for _, str := range words {
        patternM := make(map[rune]rune)
        mapTake := make(map[rune]bool)
        var isCorrect bool = true
        for i:= 0; i < l; i++ {
            patternR := rune(pattern[i])
            isTake := rune(str[i])
            _, ok := patternM[patternR] 

            if mapTake[isTake] && !ok {
                isCorrect = false
                break  
            }

            if ok {
                if patternM[patternR] != isTake {
                    isCorrect = false
                    break
                }
            }else{
                patternM[patternR] = isTake
                mapTake[isTake]= true
            }
        }

        if isCorrect {
            res = append(res, str)
        }
    }

    return res
}