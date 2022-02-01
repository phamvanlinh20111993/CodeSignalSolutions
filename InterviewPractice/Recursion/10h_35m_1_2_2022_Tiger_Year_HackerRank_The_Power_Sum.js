/**
 * **************************************************************
 * *          Begin writing code in New Tiger year              *
 * **************************************************************
 * 
 * Find the number of ways that a given integer, , can be expressed as the sum
 * of the powers of unique, natural numbers.
 * 
 * For example, if and , we have to find all combinations of unique squares
 * adding up to . The only solution is .
 * 
 * Function Description
 * 
 * Complete the powerSum function in the editor below. It should return an
 * integer that represents the number of possible combinations.
 * 
 * powerSum has the following parameter(s):
 * 
 * X: the integer to sum to N: the integer power to raise numbers to Input
 * Format
 * 
 * The first line contains an integer . The second line contains an integer .
 * 
 * Constraints
 * 
 * Output Format
 * 
 * Output a single integer, the number of possible combinations caclulated.
 * 
 * Sample Input 0
 * 
 * 10 2 
 * Sample Output 0
 * 
 * 1 
 * Explanation 0
 * 10 = 1^2 + 3^2
 * 
 * If and , we need to find the number of ways that can be represented as the
 * sum of squares of unique numbers.
 * 
 * 
 * This is the only way in which can be expressed as the sum of unique squares.
 * 
 * Sample Input 1
 * 
 * 100 2 
 * Sample Output 1
 * 
 * 3 
 * Explanation 1
 * 100 = 10^2 = 6^2 + 8^2 = 1^2 + 3^2 + 4^2 + 5^2 + 7^2
 * 
 * Sample Input 2
 * 
 * 100 3 
 * Sample Output 2
 * 
 * 1 
 * Explanation 2
 * 
 * can be expressed as the sum of the cubes of . . There is no other way to
 * express as the sum of cubes.
 */

function powerSum(X, N) {
	// Write your code here
	let count = 0
    let boundNumXByN = (X, N) => Math.ceil(Math.pow(X, N))
	let numX = boundNumXByN(X, 1/N)
	
	let recursion = (numX, sum, str) => {
		for(let ind = numX; ind > 0; ind --){
			let remainSum = sum - ind**N
			if(remainSum > 0){
				recursion(ind-1, remainSum)
			}else if(remainSum === 0){
				count++
			}
		}
	}
	
	recursion(numX, X)
	
	return count
}

// Test 1
console.log(powerSum(100, 2))

// Test 2
console.log(powerSum(1000, 3))