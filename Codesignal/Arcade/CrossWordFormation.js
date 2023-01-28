/**
 *
 */

/*compareTwoArr = (a, a1) => {
    let ind = 0, ind1 = 0,
        res = []

   while(ind < a.length && ind1 < a1.length){
        if(a[ind][0] >= a1[ind1][0]){
           ind1++
           continue
        }

        for(let p = ind1; p < a1.length; p++){
            if(a1[p][0] - a[ind][0] > 1 ){
                res.push([a1[p][0] - a[ind][0], a[ind][1], a1[p][1]])
            }
        }

        ind++
        ind1++
    }
   return res
}

checkMatch = (w, w1, w2, w3) => {
    let dup = [],
    dup1 = []
    for(let i = 0; i < w.length; i++){
        for(let p = 0; p < w1.length; p++){
            if(w[i] == w1[p])
                dup.push([i, p])
        }
        for(let p = 0; p < w2.length; p++){
            if(w[i] == w2[p])
                dup1.push([i, p])
        }
    }
 //   console.log('v', dup,  'v1', dup1)
    let res = compareTwoArr(dup, dup1)
    return res
}   */


checkMatch = (w, w1, w2, w3) => {
    let res = []
    let dup = []
    for(let i = 0; i < w.length; i++){
        for(let p = 0; p < w1.length; p++){
            if(w[i] == w1[p]){
                dup.push([i, p])
            }
        }
    }

    for(let j = 0; j < dup.length; j++){
        for(let i = dup[j][0]+2; i < w.length; i++){
            for(let p = 0; p < w2.length; p++){
                if(w[i] == w2[p]){
                    res.push([i-dup[j][0], dup[j][1], p])
                }
            }
        }
    }

    return res
}

getAllPosible = (w, w1, w2, w3) => {
    let res = checkMatch(w, w1, w2, w3)
    if(res.length == 0){
        return 0
    }

    let count = 0
    //[length, start dup, start dup1]
    for(let i = 0; i < res.length; i++){
        let ind = res[i][1] + 2, ind1 = res[i][2] + 2;

        while(ind < w1.length && ind1 < w2.length){
            for(let p = 0; p < w3.length - res[i][0]; p++){
                if(w3[p] == w1[ind] && w3[p + res[i][0]] == w2[ind1]){
                    count++
                }
            }
            ind++
            ind1++
        }
    }

    return count
}

crosswordFormation = w => {
    let cases = [1234,2134,3124,1324,2314,3214,3241,2341,4321,3421,2431,4231,4132,1432,  3412, 4312,1342, 3142,2143,1243, 4213, 2413, 1423, 4123]
    let res = 0
    for(let c = 0; c < cases.length; c++){
        let o = (cases[c] + '').split('')
        let p = parseInt(o[0]) - 1
        let p1 = parseInt(o[1]) - 1
        let p2 = parseInt(o[2]) - 1
        let p3 = parseInt(o[3]) - 1

        res += getAllPosible(w[p], w[p1], w[p2], w[p3])
    }

    return res
}
