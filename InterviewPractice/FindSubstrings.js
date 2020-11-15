/**
 * solution with O(10*5)*O(5)*O(30)
 */


findSubstrings = (words, parts) => {
    let mapParts = new Map()
    let minLength = 1000
    let maxLength = 0

    for (const part of parts){
        mapParts.set(part, part.length)
        minLength = part.length < minLength ? part.length : minLength
        maxLength = part.length > maxLength ? part.length : maxLength
    }

    buildingRes = (word, obj) => word.substring(0, obj.s) + "[" + obj.val + "]" + word.substring(obj.e)

    extractWord = word =>{
        for(let i = 0; i < word.length; i++){
            for(let p = minLength; p <= maxLength; p++){
                if(i + p <= word.length){
                   let val = word.substring(i, i + p)
                   if(mapParts.has(val)){
                       let maxLengthPart = resMap.get(word) ? resMap.get(word).val.length  : 0;
                       maxLengthPart < val.length && resMap.set(word, {s: i, e: i + p, val})
                   }
                }
            }
        }
    }

    let resMap = new Map()
    for(const word of words)
        extractWord(word)

    let res = []
    for(const word of words){
        if(resMap.has(word)){
            let obj = resMap.get(word)
            res.push(buildingRes(word, obj))
        }else    res.push(word)
    }

    return res
}
