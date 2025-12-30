/**

url: https://leetcode.com/problems/magic-squares-in-grid/description/?envType=daily-question&envId=2025-12-30

A 3 x 3 magic square is a 3 x 3 grid filled with distinct numbers from 1 to 9 such that each row, column, and both diagonals all have the same sum.

Given a row x col grid of integers, how many 3 x 3 magic square subgrids are there?

Note: while a magic square can only contain numbers from 1 to 9, grid may contain numbers up to 15.

 

Example 1:


Input: grid = [[4,3,8,4],[9,5,1,9],[2,7,6,2]]
Output: 1
Explanation: 
The following subgrid is a 3 x 3 magic square:

while this one is not:

In total, there is only one magic square inside the given grid.
Example 2:

Input: grid = [[8]]
Output: 0
 

Constraints:

row == grid.length
col == grid[i].length
1 <= row, col <= 10
0 <= grid[i][j] <= 15

**/

func isMagicSquare (grid [][]int, r, c int) bool{
    var m []int = make([]int, 9)
    for rQ := r; rQ < r+3; rQ++ {
        for cQ := c; cQ < c+3; cQ++ {
            if grid[rQ][cQ] > 9 || grid[rQ][cQ] == 0 {
                return false
            }
            m[grid[rQ][cQ]-1]++
        }
    }
    // check  distinct numbers from 1 to 9 
    for i := 0; i < 9; i++ {
        if m[i] != 1 {
            return false
        }
    }

    //each row, column, and both diagonals all have the same sum.
    sumFirstDiagonal := grid[r][c] + grid[r+1][c+1] + grid[r+2][c+2]
    sumSecondDiagonal := grid[r+2][c] + grid[r+1][c+1] + grid[r][c+2]
    if sumFirstDiagonal != sumSecondDiagonal {
        return false
    }

    for rQ := r; rQ < r+3; rQ++ {
        sumC := 0
        for cQ := c; cQ < c+3; cQ++ {
            sumC += grid[rQ][cQ]
        }
        if sumC != sumFirstDiagonal {
            return false
        }
    }

    for cQ := c; cQ < c+3; cQ++ {
        sumR := 0
        for rQ := r; rQ < r+3; rQ++ {
            sumR += grid[rQ][cQ]
        }
        if sumR != sumFirstDiagonal {
            return false
        }
    }

    return true
}

func numMagicSquaresInside(grid [][]int) int {
    grR := len(grid)
    grC := len(grid[0])
    count := 0
    for r := 0; r < grR-2; r ++ {
        for c := 0; c < grC-2; c ++ {
         //   fmt.Println("r=",r, ",c=", c)
            if isMagicSquare(grid, r, c) {
                count++
            }
        } 
    }

    return count
}