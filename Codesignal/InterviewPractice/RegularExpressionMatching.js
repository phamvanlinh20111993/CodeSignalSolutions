/**
 * Note: Avoid using regular expressions and implement regex matching yourself
 * in your solution, since this is what you would be asked to do during a real
 * interview.
 * 
 * Implement regular expression matching with support for '.' and '*', given the
 * following guidelines: '.' Matches any single character. '*' Matches zero or
 * more of the element that comes before it.
 * 
 * The matching should cover the entire input string s. If the pattern p matches
 * the input string s, return true, otherwise return false.
 * 
 * Example
 * 
 * For s = "bb" and p = "b", the output should be regularExpressionMatching(s,
 * p) = false; For s = "zab" and p = "z.*", the output should be
 * regularExpressionMatching(s, p) = true; For s = "caab" and p = "d*c*x*a*b",
 * the output should be regularExpressionMatching(s, p) = true. Input/Output
 * 
 * [execution time limit] 4 seconds (js)
 * 
 * [input] string s
 * 
 * A string consisting of only lowercase English letters.
 * 
 * Guaranteed constraints: 0 ≤ s.length ≤ 20.
 * 
 * [input] string p
 * 
 * A string consisting of only lowercase English letters and the characters .
 * and *.
 * 
 * Guaranteed constraints: 0 ≤ p.length ≤ 30.
 * 
 * [output] boolean
 * 
 * Return true if the pattern p matches the string s given the guidelines above,
 * otherwise return false.
 */
regularExpressionMatching = (s, p) => {
    let flag = false
    
    let  recursive = (pInd, sInd) => { 
        
        if(flag){
            return
        }
        
        if(pInd == p.length && sInd == s.length){
            flag = true
            return
        }else if(pInd == p.length && sInd < s.length){
            return
        }else if((sInd == s.length && pInd < p.length)){
            let ind = pInd
            while(ind < p.length && ind + 1 < p.length && p[ind+1] == '*') ind += 2
            if(ind == p.length){
                flag = true
            }
            return
        }
        
        if(p[pInd] == "."){
            if(pInd + 1 < p.length && p[pInd+1] == '*'){
                let indTmp = pInd + 2
                if(indTmp == p.length){
                    flag = true;
                    return    
                }  
                for(let ind = sInd; ind < s.length; ind++){
                    recursive(indTmp, ind);
                }

            }else{
                recursive(pInd+1, sInd+1);
            }
        }else{      
            if(pInd + 1 < p.length && p[pInd+1] == '*'){
                let ind = sInd
               
                while(p[pInd] == s[ind] && ind < s.length){
                    recursive(pInd + 2, ind);
                    ind++
                } 
                recursive(pInd + 2, ind);
            }else{
                if(p[pInd] == s[sInd]){
                    recursive(pInd + 1, sInd + 1);
                }else{
                    return
                }
            }
        }
        
    }
    recursive(0, 0)
    return flag
}
