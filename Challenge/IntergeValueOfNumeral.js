/**
 * 
 */


const ROMAN_TO_NUMBER = {'I':1, 'V':5, 'X':10, 'L':50, 'C':100, 'D':500, 'M': 1000};
const GROUP = {'IV': 1, 'IX': 1, 'XL': 1, 'XC': 1, 'CD': 1, 'CM': 1};
const GROUPN = {'XIX': 1, 'CXC': 1, 'MCM': 1 };

checkValidRomanNumeral = s => {
    // I, X, C, M, must not be repeated more than three consecutive times; 
    // digits V, L, D may not be repeated consecutively
    if(/[I]{4,}|[X]{4,}|[C]{4,}/.test(s) 
      || /[V]{2,}|[L]{2,}|[D]{2,}/.test(s)){
        return false
    }
    // Only valid: IV, IX, XL, XC, CD, CM 
    for(let i = 1; i < s.length; i++){
        if(ROMAN_TO_NUMBER[s[i-1]] < ROMAN_TO_NUMBER[s[i]] 
            && !GROUP[s[i-1]+s[i]]){
            return false;
        }
    }
    
    // Only valid for XIX, MCM, CXC
     for(let i = 1; i < s.length-1; i++){
        if(ROMAN_TO_NUMBER[s[i-1]] == ROMAN_TO_NUMBER[s[i+1]]){
            if(ROMAN_TO_NUMBER[s[i]] > ROMAN_TO_NUMBER[s[i-1]])
                return false;
            if(ROMAN_TO_NUMBER[s[i]] < ROMAN_TO_NUMBER[s[i-1]] && !GROUPN[s[i-1]+s[i]+s[i+1]])
                return false;
        }
    }
    
    return true
}

getValueOfRomanNumeral = s => {
    let num = ROMAN_TO_NUMBER[s[0]]
    for(let i = 1; i < s.length; i++){
        num += ROMAN_TO_NUMBER[s[i]]
        if(ROMAN_TO_NUMBER[s[i-1]] < ROMAN_TO_NUMBER[s[i]]){
            num -= 2 * ROMAN_TO_NUMBER[s[i-1]]
        }
    }  
    return num
}

integerValueOfRomanNumeral = s => checkValidRomanNumeral(s) ? getValueOfRomanNumeral(s) : -1
