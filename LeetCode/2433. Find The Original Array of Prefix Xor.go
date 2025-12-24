/**
	url: https://leetcode.com/problems/find-the-original-array-of-prefix-xor/?envType=problem-list-v2&envId=array
	You are given an integer array pref of size n. Find and return the array arr of size n that satisfies:

pref[i] = arr[0] ^ arr[1] ^ ... ^ arr[i].
Note that ^ denotes the bitwise-xor operation.

It can be proven that the answer is unique.

 

Example 1:

Input: pref = [5,2,0,3,1]
Output: [5,7,2,3,2]
Explanation: From the array [5,7,2,3,2] we have the following:
- pref[0] = 5.
- pref[1] = 5 ^ 7 = 2.
- pref[2] = 5 ^ 7 ^ 2 = 0.
- pref[3] = 5 ^ 7 ^ 2 ^ 3 = 3.
- pref[4] = 5 ^ 7 ^ 2 ^ 3 ^ 2 = 1.
Example 2:

Input: pref = [13]
Output: [13]
Explanation: We have pref[0] = arr[0] = 13.
 

Constraints:

1 <= pref.length <= 105
0 <= pref[i] <= 106
**/


// to make binary as the same length
func toBinary(num int) []int {
    res := []int{}
    for num > 0 {
        res = append(res, num%2)
        num /= 2
    }

    var l int = len(res)
    for ;l < 24; l++ {
        res = append(res, 0)
    }

    slices.Reverse(res)
    return res
}

func pow (base, exponent int ) int {
    var res int = 1
    for ind := 0; ind < exponent; ind++ {
        res *= base
    }
    return res
}

func max(a, b int) int {
    if a < b {
        return b
    }
    return a
}

func findXOR (a, b []int) int {
    var la int = len(a) - 1
    var t int = 0
    var num int = 0

    for i := la; i > -1; i-- {
        if a[i] != b[i] {
            num += pow(2, t)
        }
        t++
    }
    
    return num
}

func findArray(pref []int) []int {
    res := []int{}

    var temp int = pref[0]
    res = append(res, temp)
    for i := 1; i < len(pref); i++ {
        t := findXOR(toBinary(temp), toBinary(pref[i]))
        res = append(res, t)
        temp ^= t
    }

    return res
}