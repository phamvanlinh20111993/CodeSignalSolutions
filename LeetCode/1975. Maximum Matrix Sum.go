/**
	url: https://leetcode.com/problems/maximum-matrix-sum/submissions/1875433714/?envType=daily-question&envId=2026-01-05

	You are given an n x n integer matrix. You can do the following operation any number of times:

Choose any two adjacent elements of matrix and multiply each of them by -1.
Two elements are considered adjacent if and only if they share a border.

Your goal is to maximize the summation of the matrix's elements. Return the maximum sum of the matrix's elements using the operation mentioned above.

 

Example 1:


Input: matrix = [[1,-1],[-1,1]]
Output: 4
Explanation: We can follow the following steps to reach sum equals 4:
- Multiply the 2 elements in the first row by -1.
- Multiply the 2 elements in the first column by -1.
Example 2:


Input: matrix = [[1,2,3],[-1,-2,-3],[1,2,3]]
Output: 16
Explanation: We can follow the following step to reach sum equals 16:
- Multiply the 2 last elements in the second row by -1.
 

Constraints:

n == matrix.length == matrix[i].length
2 <= n <= 250
-105 <= matrix[i][j] <= 105

**/

// refer get idea not copy solution in leetcode Solutions tab, i recheck in note booked: 
// idea: We can alway turn any the even negative number to positive, if contain odd negative number, we get the Math.min(Math.abs(num)) then sum - 2*Math.abs(num )
func maxMatrixSum(matrix [][]int) int64 {
    var sum int64 = 0
    var negativeCount int = 0
    var minNeNumber int = 100000000
    for _, r := range matrix {
        for _, c := range r {
            if c <= 0 {
                negativeCount ++
                sum += int64(-c)
                minNeNumber = min(minNeNumber, -c)
            }else {
                sum += int64(c)
                minNeNumber = min(minNeNumber, c)
            }
        }
    }

    fmt.Println("sum", sum)
    fmt.Println("minNeNumber", minNeNumber)

    if negativeCount %2 == 0 {
        return sum
    }

    return sum - 2*int64(minNeNumber)
}
