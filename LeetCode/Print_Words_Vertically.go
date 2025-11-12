/**
  url: https://leetcode.com/problems/print-words-vertically/description/?envType=problem-list-v2&envId=simulation
  Given a string s. Return all the words vertically in the same order in which they appear in s.
Words are returned as a list of strings, complete with spaces when is necessary. (Trailing spaces are not allowed).
Each word would be put on only one column and that in one column there will be only one word.

 

Example 1:

Input: s = "HOW ARE YOU"
Output: ["HAY","ORO","WEU"]
Explanation: Each word is printed vertically. 
 "HAY"
 "ORO"
 "WEU"
Example 2:

Input: s = "TO BE OR NOT TO BE"
Output: ["TBONTB","OEROOE","   T"]
Explanation: Trailing spaces is not allowed. 
"TBONTB"
"OEROOE"
"   T"
Example 3:

Input: s = "CONTEST IS COMING"
Output: ["CIC","OSO","N M","T I","E N","S G","T"]
 

Constraints:

1 <= s.length <= 200
s contains only upper case English letters.
It's guaranteed that there is only one space between 2 words.
 

**/

func printVertically(s string) []string {
    splStr := strings.Split(s, " ")

    var maxLength int = 0
    for i:= 0; i < len(splStr); i++ {
        if maxLength <  len(splStr[i]) {
            maxLength = len(splStr[i])
        }
    }

    res := []string{}
    for start := 0; start < maxLength; start++ {
        var strTmp string = ""
        for i:= 0; i < len(splStr); i++ {
            if start < len(splStr[i]) {
                strTmp += string(splStr[i][start])
            }else {
                strTmp += " "
            }
        }

        var startInd int = len(strTmp) -1
        for ; strTmp[startInd] == ' ';  startInd-- {}
        res = append(res, strTmp[0:startInd+1])
    }

    return res
}