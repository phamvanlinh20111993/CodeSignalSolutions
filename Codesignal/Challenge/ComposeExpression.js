/**
 * 
 */

createAllPossibleNumber = (digits, target) => {   
    const SIGN = ["-", ""]
    let out = []
    backtrack = (digits, pos, strPresent) => {
      if(pos === digits.length && isValidStrNumber(strPresent, target)) {
        out.push(strPresent)
      }
      for(let ind = 0; ind < SIGN.length; ind++){
        if(pos < digits.length){
            backtrack(digits, pos+1, strPresent + SIGN[ind] + digits[pos])
        }
      }
    }

    backtrack(digits, 1, digits[0]);

    return out;
}

isValidStrNumber = (strNumber, target) => {
    let listNumberInStr = strNumber.split("-")
    //the new expressions should not contain leading zeros.
    for(let ind = 0; ind < listNumberInStr.length; ind++){
        if(listNumberInStr[ind].length > 1 && listNumberInStr[ind][0] == "0")
            return false
    }
    
    return true
}

composeExpression = (digits, target) => {
    let validNumbers = createAllPossibleNumber(digits, target) 
    let output = []
    
    calculateTargetValue = (digitList, target) => {
	    const BINARY_OPERATORS = ["+", "*", "-"];
	    
	    backtrack = (digitList, pos, strPresent) =>{
	      if(pos === digitList.length && eval(strPresent) == target) {
	          output.push(strPresent)
	      }
	      for(let ind = 0; ind < BINARY_OPERATORS.length; ind++){
	        if(pos < digitList.length){
	          backtrack(digitList, pos+1, strPresent + BINARY_OPERATORS[ind] + digitList[pos])
	        }
	      }
	    }
	    backtrack(digitList, 1, digitList[0]);
    }
    
    validNumbers.map(validNu => calculateTargetValue(validNu.split("-"), target))
    
    return output.sort();
}
