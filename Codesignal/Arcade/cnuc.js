/**
 * https://app.codesignal.com/challenge/5LGDu5q5A3sbEpiNj
 * 
 * It's well known fact that Chuck Norris counted to Infinity. Twice. However,
 * not many know that he also can write binary using only zeros.
 * 
 * In this challenge we will unleash this mastery.
 * 
 * Here are the steps:
 * 
 * convert a character to a binary number of length 7, don't forget leading
 * zeros group 1's and 0's if it's 1, then write 0, and the number of ones in
 * this group using 0 n-times if it's 0, then write 00, and the number of zeros
 * in this group using 0 n-times join all values with a space It's called Chuck
 * Norris Unary Code ps. If you google it just to check if it's actually exist,
 * you probably will find the algorithm that encode the whole message rather
 * than doing it char by char.
 * 
 * Example "abc" -> "0 00 00 0000 0 0 0 00 00 000 0 0 00 0 0 00 00 000 0 00"
 * Here a is 0 00 00 0000 0 0. Ascii for a is 97, which in binary is 1100001 We
 * have 2 ones, 4 zeros and 1 one. Hence 0 00 for 2 ones, 00 0000 for 4 zeroes,
 * 0 0 for 1 one. b is 0 00 00 000 0 0 00 0. Ascii for b is 98, which in binary
 * is 1100010 We have 2 ones, 3 zeros, 1 one and 1 zero, Hence 0 00 for 2 ones,
 * 00 000 for 3 zeroes, 0 0 for 1 one and 00 0 for 1 zero. c is 0 00 00 000 0
 * 00. Ascii for c is 99, which in binary is 1100011 We have 2 ones, 3 zeros and
 * 2 ones, Hence 0 00 for 2 ones, 00 000 for 3 zeroes and 0 00 for 2 ones. Put
 * it together 0 00 00 0000 0 0 0 00 00 000 0 0 00 0 0 00 00 000 0 00
 * 
 * [execution time limit] 4 seconds (js)
 * 
 * [input] string s
 * 
 * Ascii chars from 32 to 126.
 * 
 * [output] string
 * 
 * Zeros separated with spaces.
 */

solution = s => {
 const toNorrisUnaryCode = (c, n) => `${+c==0?'00':'0'} ${'0'.repeat(n)}`  
 const binaries = s.split('').map(v =>{
  const b = v.toString(2)
  return '0'.repeat(7 - b.length) + b
})

 let r = ''
 binaries.map(v => v.match(/1+|0+/g)
         .map(g => r += toNorrisUnaryCode(g[0], g.length) + ' '))
 
 return r.trim()
}
