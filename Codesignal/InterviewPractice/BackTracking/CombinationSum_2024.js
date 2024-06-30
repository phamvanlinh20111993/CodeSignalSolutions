/**
	@url https://app.codesignal.com/interview-practice/question/JAhrxhDQDLDDA3NXe/description
	Given an array of integers a and an integer sum, find all of the unique combinations in a that add up to sum.
The same number from a can be used an unlimited number of times in a combination.
Elements in a combination (a1 a2 … ak) must be sorted in non-descending order, while the combinations themselves must be sorted in ascending order.
If there are no possible combinations that add up to sum, the output should be the string "Empty".

Example

For a = [2, 3, 5, 9] and sum = 9, the output should be
solution(a, sum) = "(2 2 2 3)(2 2 5)(3 3 3)(9)".

Input/Output

    [execution time limit] 4 seconds (js)

    [memory limit] 1 GB

    [input] array.integer a

    An array of positive integers.

    Guaranteed constraints:
    2 ≤ a.length ≤ 11,
    1 ≤ a[i] ≤ 9.

    [input] integer sum

    Guaranteed constraints:
    1 ≤ sum ≤ 25.

    [output] string

    All possible combinations that add up to a given sum, or "Empty" if there are no possible combinations.
	
**/

function solution(a, sum) {
    a = Array.from(new Set([...a])).sort((a,b) => a-b)
    
    let res = []
    
    backtracking = (s, pos, acc) => {
        for(let ind = pos; ind < a.length; ind++){
            if(s - a[ind] > 0){
                backtracking(s - a[ind], ind,
                     acc+ (acc == "(" ? "" : " ") + a[ind])
            }else if(s - a[ind] == 0){
                res.push(acc+ (acc == "(" ? "" : " ") + a[ind]+")")
            }
        }
    }
    
    backtracking(sum, 0, "(")
    
   // console.log(res)
  /*  res = res.sort((a, b) => {
        if(a.length == b.length){
            return a.localeCompare(b)
        }
        return b.length - a.length
    }) */
    
    return res.length == 0 ? "Empty" : res.join("")
}

