/**
 * refer: https://app.codesignal.com/challenge/armA74XaJWbEg9aY7
 * 
 * Given a string s, checking that can be obtained a list at least two elements of strictly decreasing integer array by split s or not.
Examples:

**for: **
s = "0000000000000908076", the result is true. We can split s as ["00000000000009", "08", "07", "6"] equivalent [9, 8, 7, 6] as list of sequence stricky decreasing number.

**for: **
s = "1019", the result is false. we can't obtain any list of strictly sequence decreasing number.

**for: **
s = "100807", the result is false. we can obtain list [10, 8, 7] but required are list strictly decreasing integer.

[execution time limit] 4 seconds (js)

[input] string s

A string only contain a list integer number (from 1 to 2^31 if have valid answer).
Guaranteed constraints: 2 <= s.length <= 105000

[output] boolean

return true if can be obtained a list decreasing sequence numbers. In otherwise, return false.
 */

solution = s => {
    const greaterOneNumberStr = (str, strN) => {
        const l = parseInt(str)
        const r = parseInt(strN) 
        return l > r && l - r === 1
    }
    
    const isObtainListStrNumStr = (start, numStr) => {
        // end point, end recursive function
        if(start === s.length) return true
        
        for(let ind = start; ind <= s.length - 1; ind++){
            let pos = ind
            while(s[pos] === '0' && pos++ < s.length);
            
            const nextStr = s.substr(pos, numStr.length)
            const nextSStr = s.substr(pos, numStr.length - 1)

            return greaterOneNumberStr(numStr, nextStr) 
                    ? isObtainListStrNumStr(pos + nextStr.length, nextStr) 
                    : greaterOneNumberStr(numStr, nextSStr)  ? 
                      isObtainListStrNumStr(pos + nextSStr.length, nextSStr): false
        }         
    }
    
    const result = start => {

        while(s[start] === '0' && start++ < s.length);
        
        let chooseStr = ''
        for(let ind = start; ind < s.length-1; ind++){
            if(isObtainListStrNumStr(ind + 1,  chooseStr += s[ind])) return true
        }
        
        return false
    }
    
    return result(0)   
}