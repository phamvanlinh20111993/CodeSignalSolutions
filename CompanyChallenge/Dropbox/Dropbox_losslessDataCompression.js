/**
 * 
 */
losslessDataCompression = (inputString, width) => {
    const getWindowData = ind => ind < width 
                     ? inputString.substring(0, ind) 
                     : inputString.substring(ind - width, ind);
                       
    // Find such 'startIndex' and 'length' that 
    // s[i, i + length - 1] = s[startIndex, startIndex + length - 1] 
    // and s[startIndex, startIndex + length - 1]
    const findMappingWindow = (windowData, ind) => {
        let length = inputString.length - ind
        length = length > windowData.length ? windowData.length : length
        while(length > 0){
           const matchVal = inputString.substring(ind, ind + length)
           const position = windowData.indexOf(matchVal)
           if(position > -1){
               return {startIndex: position, length}
           }
           length -- 
        } 
        
        return {startIndex: -1, length: - 1}
    }
    
    let res = ''
    for(let index = 0; index < inputString.length;){
       let windowSlice = getWindowData(index)
       const mappingWindow = findMappingWindow(windowSlice, index)
       if(mappingWindow.startIndex > -1) {
           res += `(${mappingWindow.startIndex + (index - width >= 0 ? index - width: 0)},${mappingWindow.length})`
           index += mappingWindow.length - 1  
       } else {
           res += inputString[index]
       }
       
       index++
    }
    
     
    
    return res
}
