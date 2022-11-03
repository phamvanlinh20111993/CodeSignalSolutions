/**
 * Url: https://app.codesignal.com/challenge/nnEi7539DmJb3Kn7j
 * 
 * Given an array of Sets, return the strongly connected sets.
 * 
 * Edit:
 * 
 * Sort the output lexicographically (horizontally first then vertically).
 * [execution time limit] 4 seconds (js)
 * 
 * [input] array.array.char sets
 * 
 * [output] array.array.char
 */

solution = a => {
    let s = a.map(v => new Set([...v])),
        i = new Array(a.length).fill(0),
        j = 0,
        c = [...i]
        
    while(true){
        
        while(j < s.length){
            if(i[j]){ 
                j++
                continue
            }
            
            for(p = j + 1; p < s.length; p++){
                t = 0
                if(i[p]) continue
                s[p].forEach(v => t = s[j].has(v) ? 1 : t)
                if(t) {
                    s[j] = new Set([...s[j], ...s[p]])
                    i[p] = 1
                }
            }
            j++
        }
        
        if(c.join('') === i.join('')) break
        else{
             c = [...i]
             j = 0
        }
    }
      
    return s.filter((v, j) => !i[j])
            .map((v, j) => Array.from(v))
            .map(v => v.sort((a, b) => a.charCodeAt(0) - b.charCodeAt(0)))
            .sort((a, b) => a[0].charCodeAt(0) - b[0].charCodeAt(0)
         // {
                // let i = 0, j = 0
                // while(i < a.length && j < b.length
                // && a[i].localeCompare(b[j]) == 0){
                // i++
                // j++
                // }
                // TODO if a[a.length - 1] == b[b.length-1]
                // then compare a and b length
                // return a[0].charCodeAt(0) - b[0].charCodeAt(0)
         // }
            )
}
