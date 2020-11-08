/**
 *
 */
/* rule :

- For a 1-byte character, the first bit is a 0, followed by its unicode code.
- For an n-byte character, the first n bits are all 1s and the n + 1 bit is 0. This is followed by n - 1 bytes in which the 2 most significant bits (that is, the leftmost 2) are 10.

*/
checkFirstBit = s => {
    let ind = 0
    while(ind < s.length && s[ind++] == '1');
    return ind-1
}

checkBit10 = s => {
    return s[0] == '1' && s[1] == '0'
}

streamValidation = s=> {

    for(let i = 0; i < s.length; i++){
        s[i] = s[i].toString(2)

        if(s[i].length < 8)
            s[i] = "0".repeat(8-s[i].length) + s[i]
    }

    let ind = 0
    while(ind < s.length){
        let count = checkFirstBit(s[ind])
        if(count == 1 || count > s.length)
            return false
        let pos = ind + 1

        while(pos < ind + count){
            if(!checkBit10(s[pos++]))
                return false
        }

        ind += count == 0 ? 1 : count
    }


    return true
}
