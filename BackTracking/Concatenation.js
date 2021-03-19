/**
 * Concatenation is an operation that joins strings. For example, the
 * concatenation of strings "smart" and "phone" is "smartphone". Concatenation
 * can be expanded to more than two strings; for example, concatenating "co",
 * "dil" and "ity" results in "codility"
 *
 *
 * Given an array A consisting of strings, your function should calculate the
 * length of the longest string S such that: S is a concatenation of some of the
 * strings from A; every letter in S is different.
 *
 * Write a function: class Solution { public int solution(String[] A); } that,
 * given an array A consisting of N strings, calculates the length of the
 * longest string S fulfilling the conditions above. If no such string exists,
 * your function should return 0. Examples: Given A = ["co", "dil", "ity"], your
 * function should return 5. The resulting string S could be "codil", "dilco",
 * "coity" or "ityco".
 *
 * Given A = ["abc", "yyy", "def", "csv"], your function should return 6. The
 * resulting string S could be "abcdef", "defabc", "defcsv" or "csvdef".
 *
 * Given A = ["potato", "kayak", "banana", "racecar"], your function should
 * return 0. It is impossible to choose any of these strings as each of them
 * contains repeating letters.
 *
 * Given A = ["eva", "jqw", "tyn", "jan"], your function should return 9. One of
 * the possible strings of this length is "evajqwtyn".
 *
 * Assume that: N is an integer within the range [1..8] each string in A
 * consists of lowercase English letters the sum of lengths of strings in A does
 * not exceed 100
 *
 * In your solution, focus on correctness. The performance of your solution will
 * not be the focus of the assessment.
 */

let isSelfNotDupChar = s => {
	let set = new Set()
  let isDup = s.split("").filter(v => set.has(v) ? true : (set.add(v) && false))
  return isDup.length === 0
}

let concatenation = arrCheck =>{
	let arr = arrCheck.filter(v => isSelfNotDupChar(v))
  let isNotInAnother = (me, another) => {
    let res = me.split("").filter(v => another.includes(v)) || []
    return res.length === 0
  }
  let countMax = 0;
  let recursiveCheckMax = (start, res) => {
    for(let ind = start + 1; ind < arr.length; ind++){
      if(isNotInAnother(arr[start], res))
        recursiveCheckMax(ind, res + arr[start])
    }
    countMax = countMax < res.length ?  res.length: countMax
    }
    recursiveCheckMax(0, "")
    return countMax;
}

console.log("test1 : ", concatenation(["co", "dil", "ity"]))
console.log("test2: ", concatenation(["abc", "yyy", "def", "csv"]))
console.log("test3: ", concatenation(["potato", "kayak", "banana", "racecar"]))
console.log("test4: ", concatenation(["eva", "jqw", "tyn", "jan"]))
