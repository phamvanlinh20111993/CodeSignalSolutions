/**
 * 
 */

/**
 * find longest increasing substring value from s and s1 string
 * 
 * Call f(i, j) is max value increase substring from index i and index j of s
 * and s1 We have: f(i, j) = 1 + f(i-1, j-1) if s[i] === s1[j] max(f(i-1, j),
 * f(i, j-1)) if s[i] !== s1[j] 0 if i or j < 0
 */
let s = 'dsadwqerwfegryryhghjhsdfwewrwetwetwetwegsggfdggvcbvcbvcbcvegertertqtretregfgfbgfhdhfgdkliup896755451ewsdvvcbnmjhkueyertsfdsfhgjh'
let s1 = '243refdbnytjrtyttagfbvcbxvbvxcbvcbxvcbvcbvcbvcbvbgfdhgfhfgdhgfdhgfdhfgdhfgdhfgdhfgdhgfdhfghfggalghreaifgndmgangoajagkmfdgnahlnarpetoregjoigsiodgjf'



// let s = 'abcadvk'
// let s1 = 'ack'



// let s = 'abcadvk1234'
// let s1 = 'ackaaaaaaaaa1'



// let s = 'abcadvk11111111111'
// let s1 = 'ackbadvkbbbbbbbbbb1'
const mem = {}
let dp = (i, j) => {
    if (i < 0 || j < 0) return [0, []]

    if (mem[`${i}-${j}`]) {
        return mem[`${i}-${j}`]
    }

    if (s[i] === s1[j]) {
        const m = dp(i - 1, j - 1)
        m[1].push([i, j])
        mem[`${i}-${j}`] = [1 + m[0], m[1]]
    } else {
        const l = dp(i, j - 1),
            r = dp(i - 1, j)
        mem[`${i}-${j}`] = l[0] > r[0] ? l : r
    }



    return mem[`${i}-${j}`]
}



dp(s.length - 1, s1.length - 1)
const res = mem[`${s.length -1}-${s1.length-1}`]
console.log(res[0], res[1].length, res[1].map(v => `${v[0]}-${v[1]}`).join(";"))