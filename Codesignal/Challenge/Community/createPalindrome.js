
/**
 * url: https://app.codesignal.com/challenge/uy26cdtmst3M8pyzb
 * 
 * Using the string provided, return the longest lexicographical ordered palindrome string.
 * 
 * @param str
 * @returns
 */

function solution(str) {
    let m = new Map()
   
    for(let c of str){
        m.set(c, (m.get(c) || 0) + 1 )
    }
    
    let orderMoreOne = [],
        orderOne = ['|']
    for (const k of m.keys()) {
       if(m.get(k) > 1) orderMoreOne.push(k)
       else {
           orderOne = orderOne[0].charCodeAt(0) - k.charCodeAt(0) > 0 ? [k] : orderOne
       }
    }
    
    orderOne = orderOne[0] == '|' ? [''] : orderOne
    
    let res = ''
    orderMoreOne
    .sort((a, b) => a.charCodeAt(0) - b.charCodeAt(0))
    .forEach(v => {
        let d = Math.floor(m.get(v)/2)
        if(m.get(v) % 2 != 0) orderOne.push(v)
        while(d > 0){
            res += v
            d--
        }
    })
    
    return res + orderOne.sort((a, b) => a.charCodeAt(0) - b.charCodeAt(0))[0] + res.split("").reverse().join("");
}
