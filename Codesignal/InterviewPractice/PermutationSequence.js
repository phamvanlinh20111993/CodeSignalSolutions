/**
 * The set [1, 2, 3, ... , n] contains a total of n! unique permutations. List all the permutations for an integer n in lexicographical order and return the kth permutation in the sequence as a string. To build this string, concatenate decimal representations of permutation elements from left to right without any delimiters.

Example

For n = 3 and k = 3, the output should be
solution(n, k) = "213".

The ordered list of possible permutations for 3! is:

  1) "123"
  2) "132"
  3) "213"
  4) "231"
  5) "312"
  6) "321"
The 3rd permutation in the lexicographically ordered sequence is "213".

Input/Output

[execution time limit] 4 seconds (js)

[input] integer n

Guaranteed constraints:
1 ≤ n ≤ 103.

[input] integer k

Guaranteed constraints:
1 ≤ k ≤ min(109, n!).

[output] string

A string representing the kth item in the lexicographically ordered permutation sequence for n!.
 */

/**
 * How to resolve this solution. Please seeing this example: Ex: n = 5, k = 33 1
 * 2 3 4 5 120 24 6 2 1
 * 
 * start 33, 33 > 24, 24 * 2 = 48 > 33 -> pos 2 is 2 (in list 12345) 37 - 24*1 =
 * 9, 9 > 6, 6 * 2 = 12 > 9 -> pos 2 is 3 (list 1345) 9 - 6 * 1 = 3, 2 * 2 = 4 >
 * 3 -> pos 2 is 4 (in list 145) 3 - 2*1 = 1, 1*1 >= 1 -> pos 1 is 1 (in list
 * 15) 1 - 1*0 = 1, 1* >= 1 -> pos 1 is 5 (in remain list 5) => Solution is
 * 23415 .............................................................
 */        
solution = (n, k) => {
    
    const MAX_K = 87178291200 // 14! max number over 10^9
    
    let nArrSelectNum = new Array(n).fill({}).map((v, i) => ({...v, isSelect : false, val : i + 1}))
  
    permutationCountArr = n => {
        let total = 1
        let arr = [1]
        for(let ind = 2; ind <= n; ind++){
            total *= ind
            total > MAX_K ?  arr.unshift(0) : arr.unshift(total)    
        }
        
        return arr
    }
    
    const totalArr = permutationCountArr(n)
    let res = ''
    let start = 0
    while ((totalArr[start] === 0 || totalArr[start] > k) && start < totalArr.length){
        if(totalArr[start] !== 0 && start + 1 < totalArr.length && totalArr[start+1] <= k) break 
        
        nArrSelectNum[start].isSelect = true
        res += (start++ + 1) + ''
    }
    
    // check k equal with any number in permutations list
    if(k === totalArr[start]){
        return res + nArrSelectNum.slice(start)
                .sort((a, b) => b.val - a.val)
                .map(v => v.val).join('')
    }
    
    if(start + 1 < totalArr.length && k === totalArr[start+1]){
        return res + nArrSelectNum[start].val + nArrSelectNum.slice(start+1)
                .sort((a, b) => b.val - a.val)
                .map(v => v.val).join('')
    }
    
    // not equal
	// ------------------------------------------------------------------------------
    /**
	 * a = 20, b = 5 => min number k with: b*k >= a is 6
	 */
    findMinNumGreaterThan = (a, b) =>  a % b === 0 ? a/b : Math.ceil(a/b)
    
    /**
	 * checking valid number with position in nArrSelectNum respectively. Then
	 * add to response
	 */
    getNumInListFrom = index => {
        let start = 0, ind = 0
        for(ind = 0; ind < nArrSelectNum.length; ind++){
            if(nArrSelectNum[ind].isSelect === false){
                start++
            }
            if(start === index) break
        }
        return {pos: ind, val: nArrSelectNum[ind].val}
    }
    
    // decreasing number from k to end with 1 that respect with totalArr.length
    let num = k
    while(start + 1 < totalArr.length){
        
        const index = findMinNumGreaterThan(num, totalArr[start + 1])
        const {pos, val} = getNumInListFrom(index)
        nArrSelectNum[pos].isSelect = true
        res += val
        num -= (index - 1) * totalArr[start + 1]
        start++
    }
    
    return res +  nArrSelectNum.filter(v => !v.isSelect).map(v => v.val).join('')
}
