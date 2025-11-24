/**
  Url: https://leetcode.com/problems/maximum-number-of-operations-to-move-ones-to-the-end/description/?envType=daily-question&envId=2025-11-13


	You are given a binary string s.

You can perform the following operation on the string any number of times:

Choose any index i from the string where i + 1 < s.length such that s[i] == '1' and s[i + 1] == '0'.
Move the character s[i] to the right until it reaches the end of the string or another '1'. For example, for s = "010010", if we choose i = 1, the resulting string will be s = "000110".
Return the maximum number of operations that you can perform.

 

Example 1:

Input: s = "1001101"

Output: 4

Explanation:

We can perform the following operations:

Choose index i = 0. The resulting string is s = "0011101".
Choose index i = 4. The resulting string is s = "0011011".
Choose index i = 3. The resulting string is s = "0010111".
Choose index i = 2. The resulting string is s = "0001111".
Example 2:

Input: s = "00111"

Output: 0

 

Constraints:

1 <= s.length <= 105
s[i] is either '0' or '1'.
**/

func maxOperations(s string) int {
    numOne := []int{}
    var amountNum1 = 0
    for _, v := range s {
        if v == 49 {
            amountNum1++
        }else{
            if amountNum1 > 0 {
                numOne = append(numOne, amountNum1)
            }
            amountNum1 = 0  
        }
    }

    var res int = 0
    if len(numOne) > 0 {
        res = numOne[0]
    }
    for i:= 1; i < len(numOne); i++ {
       numOne[i] += numOne[i-1]
       res += numOne[i]
    }

    return res 
}