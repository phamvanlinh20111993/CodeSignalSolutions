/**
 * Given an integer length, count the number of different good strings that have a length of exactly length. A good string is a string for which the following conditions are true:

A good string contains only lowercase English letters.
Each character in a good string is unique.
Exactly one character in a good string is lexicographically greater than the character that precedes it.
Example

For length = 2, the output should be
goodStringsCount(length) = 325.

If the first symbol is 'a', there are 25 possible good strings: "ab", "ac", ...
If the first symbol is 'b', there are 24 possible good strings: "bc", "bd", ...
...
If the first symbol is 'z', there are 0 possible good strings because there is no character that is lexicographically greater.
There are 25 + 24 + 23 + ... + 0 = 325 good strings that have a length of 2.

For length = 1, the output should be
goodStringsCount(length) = 0.

The 3rd rule for good strings can't be true if there is only one character in the string.

Input/Output

[execution time limit] 4 seconds (js)

[input] integer length

The length of good strings that you're looking for.

Guaranteed constraints:
1 ≤ length ≤ 9.

[output] integer

The number of different good strings with a length of exactly length.
 */

/**
 * GoodStringCount 1, length = 3, answer = 10400 26C3 = 2600,
 * 3(a,b,c),...(a,c,d) = 2600 case
 * 
 * check condition to match with third condition from title : (Exactly one
 * character in a good string is lexicographically greater than the character
 * that precedes it.) abc => cab (ab) bac (ac) bca (bc) acb (ac) => 4 case:
 * 2600*4 = 10400, i guess: 4 = 3C0 + 3C1
 * 
 * 2, length = 4, answer = 164450 26C4 = 14950, 4(a,b,c,d),...(a,c,d,e) = 14950
 * case
 * 
 * abcd => 11 case (let 164450/14950 = 11), i guess: 11 = 4C0 + 4C1 + 4C2
 * 
 * 
 * 2, length = 5, answer = 1710280 26C5 = 65780, 4(a,b,c,d,e),...(a,c,d,e, f) =
 * 65780 case
 * 
 * abcde => 26 case (let 1710280/65780 = 26 ), i guess: 26 = 5C0 + 5C1 + 5C2 +
 * 5C3 ......... => formula: 26Clength * (SUM(lengthC0 + .... +
 * lengthC(length-2))) hmmmmmmmmm.... really difficult to understand
 */

const fact = n => {
    let factN = 1
    for (let index = 2; index <= n; index++) {  
        factN *= index
    }
    return factN
}

const goodStringsCount = l => {
    if(l <= 1) return 0
   let combinationLength = fact(26)/(fact(26-l) * fact(l))
   let thirdCondition = 0;
   for(let ind = 0; ind <= l - 2; ind++){
       thirdCondition += fact(l)/(fact(l-ind) * fact(ind))
   } 
   return combinationLength * thirdCondition
}
