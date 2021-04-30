/**
 * A top secret message containing uppercase letters from 'A' to 'Z' has been
 * encoded as numbers using the following mapping:
 * 
 * 'A' -> 1 'B' -> 2 ... 'Z' -> 26 You are an FBI agent and you need to
 * determine the total number of ways that the message can be decoded.
 * 
 * Since the answer could be very large, take it modulo 109 + 7.
 * 
 * Example
 * 
 * For message = "123", the output should be mapDecoding(message) = 3.
 * 
 * "123" can be decoded as "ABC" (1 2 3), "LC" (12 3) or "AW" (1 23), so the
 * total number of ways is 3.
 * 
 * Input/Output
 * 
 * [execution time limit] 4 seconds (js)
 * 
 * [input] string message
 * 
 * A string containing only digits.
 * 
 * Guaranteed constraints: 0 ≤ message.length ≤ 105.
 * 
 * [output] integer
 * 
 * The total number of ways to decode the given message.
 */



let countAllPosible
recursiveGetAllPosible = (message, index, str) => {
    if(index < message.length){
        if(message[index] != "0"){
            recursiveGetAllPosible(message, index + 1, str + message[index] + " ")
            if(index + 1 < message.length){
                let mergeMessageInd = message[index] + message[index+1]
                if(parseInt(mergeMessageInd) < 27)
                    recursiveGetAllPosible(message, index + 2, str +mergeMessageInd + " ")
            }
        }
    }else{
      // console.log(str)
        countAllPosible++
    }
}

let storeValue;
const MODULO = 1000000007;

dpCountAllPossible = (message, index) => {
    if(index <= 0) return storeValue[0]
    else {
    	// min value is 1
        let currentVal = 1, previousVal = 1;
        if(storeValue[index-1] >= 0){
            currentVal = storeValue[index-1]
        }else{
            currentVal = dpCountAllPossible(message, index-1) % MODULO
            storeValue[index-1] = currentVal;
        }

        if(index-2 >= 0){
            if(storeValue[index-2] >= 0){
                previousVal = storeValue[index-2]
            }else{
                previousVal = dpCountAllPossible(message, index-2) % MODULO
                storeValue[index-2] = previousVal
            }
        }
        
        if(parseInt(message[index-1]) >= 3 
          || parseInt(message[index-1] + message[index]) > 26 
          || parseInt(message[index-1]) == 0){
            return currentVal;
        }else if(parseInt(message[index]) == 0){
            return previousVal
        }else {
            return currentVal + previousVal;
        }
    }
}

mapDecoding = message => {

    if(message.includes("00") || /[3-9]0/.test(message) || message == "0")
        return 0

    if(message.length < 2)
        return 1;

    countAllPosible = 0
    storeValue = new Array(message.length).fill(-1)
    storeValue[0] = 1;
 
    let m = dpCountAllPossible(message, message.length-1)
    return m % MODULO
}

// test 1
let message = "1221112111122221211221221212212212111221222212122221222112122212121212221212122221211112212212211211";// 782204094
   // test 2
    message = "1221112111122221"; // 1597
 // test 3
    message = "2871221111122261"; // 233
 // test 4
    message = "11115112112";// 104
 // test 5
    message = "1001";//0

