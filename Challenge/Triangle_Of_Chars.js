/**
 * url: https://app.codesignal.com/challenge/KsJvDBz24HK5qQrdv 
 */

function solution(n) {
    const ALPHABET_CHARS = "abcdefghijklmnopqrstuvwxyz{|}~"
    const STAR = "*"
    const BRACKETS = str => `{${str}}`
    const f = n
    
    const getRightSide = p => {
        let str = ""
        while(p >= 0){
            str += ALPHABET_CHARS[p-=2] + STAR
            p -= 2
        }
        
        return str
    }

    const amountSpace = n =>' '.repeat(n)
    
    const getLeftSide = n => getRightSide(n).split(STAR).reverse().join(STAR)
    
    
    let res = new Array(n).fill(STAR)
    n > 0 && (res[0] = (m = amountSpace(n-1)) + STAR + m)
    
    let aSpace = 0
    n = n-2
    while(n >= 0){
        const sp = amountSpace(aSpace)
        const l = getLeftSide(n-2)
        const r = getRightSide(n-2)
        let str = ""
        if(n%2 == 0){
            const ls = l ? l.substring(1) + STAR : ''
            const rs = r ? STAR + r.substring(0, r.length-1) : ''
            str = sp + BRACKETS(`${ls}${ALPHABET_CHARS[n]}${rs}`) + sp
        }else{
            str = sp + BRACKETS(`${l}${STAR}${ALPHABET_CHARS[n]}${STAR}${r}`) + sp
        }
       
        aSpace++
        res[n+1] = str
        n --
    }
    
    return res
}
