/**
 * 
 * 	https://app.codesignal.com/challenge/odtseCvtZ2rJgPt8r
 * 
 * Một kỹ sư nhận bản design về bể chứa nước từ một kiến trúc sư.
Bản design thể hiện dưới dạng một array of intergers.
Mỗi khối có unitsize như sau: (height = width = 1)

Tính diện tích mặt cắt của nước trong bể.

Example:
For water_area = [3,0,0,2,0,4] the output should be 3 + 3 + 1 + 3 = 10 (see picture below for illustration).
 */

/*solution = a =>{
    let b = a.map((v, i) => [i, v])
             .filter(v => v[1] > 0)
    b = b.sort((a, b) => a[1] - b[1])
    
    let m = new Map()
    b.map((v, i) => m.set(v[1], [v[0], i]))
    
    console.log(b, m)
    
    let seg = []
    
    const resursion = ([i, v]) => {
        
        if(!m.get(v)) return
        
        let [oldInd, ind] = m.get(v)// ind in sorted position
        let indTmp = ind
        console.log('vl', ind, seg)
        if(ind + 1 < b.length){
            while(indTmp < b.length && b[indTmp][0] <= i) indTmp ++ 
            if(indTmp < b.length){
                seg.push([i, b[indTmp][0]])
                console.log('data', [b[indTmp][0], a[b[indTmp][0]]])
                resursion([b[indTmp][0], a[b[indTmp][0]]])
            }
        }else{
              console.log('aki', indTmp)
            while(indTmp > 0 && b[indTmp][0] <= i) indTmp --
            if(indTmp >= 0){
                seg.push([i, b[indTmp][0]])
                console.log('data1', [b[indTmp][0], a[b[indTmp][0]]])
                resursion([b[indTmp][0], a[b[indTmp][0]]])
            }
            
        }
    }
    
    resursion([0, a[0]])
    console.log(seg)
     
    let r = 0
    seg.map(v => {
        let min = a[v[0]] > a[v[1]] ? a[v[1]] : a[v[0]]
        for(let ind = v[0]; ind <= v[1]; ind++){
            r += min - a[ind] > 0 ?  min - a[ind] : 0
        }
    })
    
    return r
}
*/

solution = a => {
    let first = [0, a[0]], 
        nearFirstMax = [-1, 1000000]
        seg = []
    
    for(let ind = 1; ind < a.length; ind++){
        if (a[ind] >= first[1]) {
            seg.push([first[0], ind])
            first = [ind, a[ind]]
            nearFirstMax = [-1, 1000000]
        } else {
            if(nearFirstMax[1] > Math.abs(a[ind] - first[1])){
                nearFirstMax = [ind, Math.abs(a[ind] - first[1])]
            }
        }
    }
   
    if(nearFirstMax[0] !== a.length-1){
        seg.push([first[0], nearFirstMax[0]])
        seg.push([nearFirstMax[0], a.length - 1])
    }else{
        seg.push([first[0], a.length - 1])
    }

    let r = 0
    seg.map(v => {
        let min = a[v[0]] > a[v[1]] ? a[v[1]] : a[v[0]]
        for(let ind = v[0]; ind <= v[1]; ind++){
            r += min - a[ind] > 0 ?  min - a[ind] : 0
        }
    })
    
    return r
}