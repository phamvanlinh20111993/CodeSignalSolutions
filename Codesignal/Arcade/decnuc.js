/**
 * https://app.codesignal.com/challenge/gpi2kAFQ8FcRFdxqd
 * 
 * Given a string of zeros and spaces encoded in Chuck Norris Unary Code, decode
 * it. See the original challenge for more info.
 * 
 * Given sequences are valid.
 * 
 * [execution time limit] 4 seconds (js)
 * 
 * [input] string s
 * 
 * Blob of zeros and spaces.
 * 
 * [output] string
 * 
 * Decoded text.
 */



solution = s => s ? s.match(/00?\s0+/g)
.map(v => (p = v.split(' ')) && `${p[0] == '0' ? '1': '0'}`.repeat(p[1].length))
  .join('')
  .match(/.{7}/g)
  .map(v => String.fromCharCode(parseInt(v, 2)))
  .join('') : ''

