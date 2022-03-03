
/**
 * **using dp
 * call maxCommon(i, k) is the largest common substring of
 * 2 string s and c (with s, 0 <i <s.length, with c 0 <k <c.length)
 *  maxCommon(i, k) = [if(c[i] == s[k] k ~[0, l]) maxCommon(i, k) + 1,
 *                     else maxCommon(i-1, k), maxCommon(i, k-1)),
 *                     maxCommon(0, k) = 1 if(s[0] == c[k]) else 0,
 *                     maxCommon(i, 0) = 1 if(s[i] == c[0]) else 0]
 *
 *
 */
let arr;
 maxCommon = (o, n, i, k) => {
  //   console.log(i, k, arr[i][k])
    if(i >= 0 && k >= 0 && arr[i][k] > 0)
        return arr[i][k];

   //  console.log(i, k)
     if(i < 0 || k < 0) return 0;
     else if(o[i] == n[k]){
         arr[i][k] = 1 + maxCommon(o, n, i-1, k-1);
        return arr[i][k];
     }else{
         let mO = maxCommon(o, n, i-1, k),
             mK = maxCommon(o, n, i, k-1);
         arr[i][k] = mO > mK ? mO : mK;
        return  arr[i][k] ;
     }
 }

// displayDiff = (o, n) => {
//     arr = new Array(o.length)
//     for(let i = 0; i < o.length; i++){
//         arr[i] = new Array(n.length).fill(0)
//     }
//     let r = maxCommon(o, n, o.length - 1, n.length - 1)
//     console.log(r)
//     console.log(arr)
// }



/*let isLeftLess = (a, b) => {
    let SPECIAL = ["[", "]", ")", "("]
    let pos = 0;

    for (let ind = 0; ind < a.length; ind++) {
        if (pos < b.length) {
            if ((!SPECIAL.includes(a[ind]) && !SPECIAL.includes(b[pos])) ||
                (SPECIAL.includes(a[ind]) && SPECIAL.includes(b[pos]))) {
                if (a[ind].localeCompare(b[pos]) != 0) {
                    return a[ind].localeCompare(b[pos]) == -1 ? true : false
                }
            } else {
                return !SPECIAL.includes(a[ind])
            }
        }
        pos++
    }

    return a.length <= b.length
}

let getMatrixMaxSamePosition = (s, n) => {

    let containSameData = new Array(s.length + 1).fill([])
    containSameData = containSameData.map(v => new Array(n.length + 1).fill("0"))
    let dpStorageMaxSame = new Map();

    let dp = (start, startNew) => {
        let count1 = -1

        if (dpStorageMaxSame.has(`${start}-${startNew}`)) {
            return dpStorageMaxSame.get(`${start}-${startNew}`)
        }

        for (let ind = start; ind < s.length; ind++) {
            for (let pos = startNew; pos < n.length; pos++) {
                if (s[ind] == n[pos]) {
                    let count2 = dp(ind + 1, pos + 1) + 1;
                    // console.log("(start, startNew) = ", start, startNew, "(ind, pos) = ", ind,pos, count2, count1)
                    if (count2 >= count1) {
                        count1 = count2
                        containSameData[ind][pos] = count1
                    }
                }
            }
        }

        // alway save best counts
        if (!dpStorageMaxSame.has(`${start}-${startNew}`)) {
            dpStorageMaxSame.set(`${start}-${startNew}`, count1)
        }

        return count1;
    }

    let count = dp(0, 0);

    return {
        matrix: containSameData,
        count
    };
}

let selectSameChar = (s, n) => {
    let matrixSame = getMatrixMaxSamePosition(s, n),
        j = 0,
        matrix = matrixSame.matrix,
        count = matrixSame.count,
        snSame = new Array();

    let groupMapByCount = new Map()
    // put it to map
    matrix.map((row, ind) => {
        row.map((col, pos) => {
            if (col !== "0") {
                if (groupMapByCount.has(col)) {
                    let arr = groupMapByCount.get(col)
                    arr.push([ind, pos])
                    groupMapByCount.set(col, arr)
                } else {
                    groupMapByCount.set(col, new Array([ind, pos]))
                }
            }
        })
    })

    let countTmp = count - 1;
    let matchSame = new Array();
    //	if data more than 1 array, let create function to handle it
    //	arr save each element is array [count, index in String s, index in String n]

    if (groupMapByCount.size > 0) {
        let arr = groupMapByCount.get(count);
        let sMin = ""
        let getIndex = [];

        console.log(arr)

        arr.map(data => {
            let sLeft = s.substring(0, data[0]);
            let nRight = n.substring(0, data[1]);

            if (sLeft.length > 0) {
                sLeft = "(" + sLeft + ")"
            }

            if (nRight.length > 0) {
                nRight = "[" + nRight + "]"
            }

            if (sMin == "") {
                sMin = sLeft + nRight;
                getIndex = data;
            } else {
                if (isLeftLess(sLeft + nRight, sMin)) {
                    sMin = sLeft + nRight;
                    getIndex = data;
                }
            }
        })

        matchSame.push([count, ...getIndex])
    }

    while (countTmp >= 0) {
        let arr = groupMapByCount.get(countTmp);
        let arrPrevious = matchSame[matchSame.length - 1];
        let sMin = ""
        let getIndex = [];

        arr.map((data, pos) => {
            if (data[0] > arrPrevious[1] && data[1] > arrPrevious[2]) {
                let sLeft = s.substring(arrPrevious[1] + 1, data[0]);
                let nRight = n.substring(arrPrevious[2] + 1, data[1]);

                if (sLeft.length > 0) {
                    sLeft = "(" + sLeft + ")"
                }

                if (nRight.length > 0) {
                    nRight = "[" + nRight + "]"
                }

                //  let sLeft = "(" + s.substring(arrPrevious[1] + 1, data[0]) + ")";
                //  let nRight = "[" + n.substring(arrPrevious[2] + 1, data[1]) + "]";
             //   console.log(countTmp, "*" + sMin + "*", "*" + sLeft + "*", "*" + nRight + "*")
                if (sMin == "") {
                    sMin = sLeft + nRight;
                    getIndex = data;
                } else {
                    if (isLeftLess(sLeft + nRight, sMin)) {
                        sMin = sLeft + nRight;
                        getIndex = data;
                    }
                }
            }
        })

        matchSame.push([countTmp, ...getIndex])
        countTmp--;
    }

    return matchSame;



    let sS1Same = new Array();
    for (let ind = 0; ind < matrix.length; ind++) {
        // 0 is count value, 1 is index in s length, 2 is index in s1 length
        let store = [0, -1, -1],
            isCount = false;
        for (let p = j; p < matrix[ind].length; p++) {
            if (count === matrix[ind][p]) {
                //	console.log(count, matrix[ind][p])
                isCount = true
                //	console.log(count, p)
                if (store[1] == -1) {
                    store = [count, ind, p]
                } else if (s[store[1]] > s[ind]) {
                    store = [count, ind, p]
                }
            }
        }

        if (isCount) {
            j = store[2] + 1
            sS1Same.push(store);
            count--;
        }
    }

  //  return sS1Same;

}

let mergeOne = (s, n) => {
    let arr = selectSameChar(s, n);
    if (arr.length == 0) {
        return "(" + s + ")" + "[" + n + "]"
    }

    let str = ""
    if (arr[0][1] > 0) {
        str += "("
        for (let p = 0; p < arr[0][1]; p++)
            str += s[p]
        str += ")"
    }
    if (arr[0][2] > 0) {
        str += "["
        for (let p = 0; p < arr[0][2]; p++) {
            str += n[p]
        }
        str += "]"
    }

    str += s[arr[0][1]]

    for (let ind = 1; ind < arr.length; ind++) {
        if (arr[ind][1] - arr[ind - 1][1] > 1) {
            str += "("
            for (let p = arr[ind - 1][1] + 1; p < arr[ind][1]; p++)
                str += s[p]
            str += ")"
        }

        if (arr[ind][2] - arr[ind - 1][2] > 1) {
            str += "["
            for (let p = arr[ind - 1][2] + 1; p < arr[ind][2]; p++)
                str += n[p]
            str += "]"
        }

        str += s[arr[ind][1]]
    }

    let ind = arr.length - 1;
    if (ind < 0) {
        return str;
    }

    if (s.length - arr[ind][1] > 1) {
        str += "("
        for (let p = arr[ind][1] + 1; p < s.length; p++)
            str += s[p]
        str += ")"
    }

    if (n.length - arr[ind][2] > 1) {
        str += "["
        for (let p = arr[ind][2] + 1; p < n.length; p++)
            str += n[p]
        str += "]"
    }

    return str;
}

displayDiff = (o, n) => {
    return mergeOne(o, n)
}
*/

let isLeftLess = (a, b) => {
    let SPECIAL = ["[", "]", ")", "("]
    let pos = 0;

    for (let ind = 0; ind < a.length; ind++) {
        if (pos < b.length) {
            if ((!SPECIAL.includes(a[ind]) && !SPECIAL.includes(b[pos])) ||
                (SPECIAL.includes(a[ind]) && SPECIAL.includes(b[pos]))) {
                if (a[ind].localeCompare(b[pos]) != 0) {
                    return a[ind].localeCompare(b[pos]) == -1 ? true : false
                }
            } else {
                return !SPECIAL.includes(a[ind])
            }
        }
        pos++
    }

    return a.length <= b.length
}

let getMatrixMaxSamePosition = (s, n) => {

    let containSameData = new Array(s.length).fill([])
    containSameData = containSameData.map(v => new Array(n.length).fill("0"))
    let dpStorageMaxSame = new Map();

    let dp = (start, startNew) => {
        let count1 = -1

        if (dpStorageMaxSame.has(`${start}-${startNew}`)) {
            return dpStorageMaxSame.get(`${start}-${startNew}`)
        }

        for (let ind = start; ind < s.length; ind++) {
            for (let pos = startNew; pos < n.length; pos++) {
                if (s[ind] == n[pos]) {
                    let count2 = dp(ind + 1, pos + 1) + 1;
                    if (count2 >= count1) {
                        count1 = count2
                        containSameData[ind][pos] = count1
                    }
                }
            }
        }

        // alway save best counts
        if (!dpStorageMaxSame.has(`${start}-${startNew}`)) {
            dpStorageMaxSame.set(`${start}-${startNew}`, count1)
        }

        return count1;
    }

    let count = dp(0, 0);

    return {
        matrix: containSameData,
        count
    };
}
/**
  This solution is not good when check all case of subsequence.
  but i try another solution but it not work: hazzz. 
  When i have time, i will review it later
 
 let selectSameChar = (s, n) => {
    let matrixSame = getMatrixMaxSamePosition(s, n),
        j = 0,
        matrix = matrixSame.matrix,
        count = matrixSame.count,
        snSame = new Array();

    let groupMapByCount = new Map()
    // put it to map
    matrix.map((row, ind) => {
        row.map((col, pos) => {
            if (col !== "0") {
                if (groupMapByCount.has(col)) {
                    let arr = groupMapByCount.get(col)
                    arr.push([ind, pos])
                    groupMapByCount.set(col, arr)
                } else {
                    groupMapByCount.set(col, new Array([ind, pos]))
                }
            }
        })
    })

    let countTmp = count - 1;
    let matchSame = new Array();
    //	if data more than 1 array, let create function to handle it
    //	arr save each element is array [count, index in String s, index in String n]
    
    if (groupMapByCount.size > 0) {
        let arr = groupMapByCount.get(count);
        let sMin = ""
        let getIndex = [];
        
        console.log(arr)
        
        arr.map(data => {
            let sLeft = s.substring(0, data[0]);
            let nRight = n.substring(0, data[1]);

            if (sLeft.length > 0) {
                sLeft = "(" + sLeft + ")"
            }

            if (nRight.length > 0) {
                nRight = "[" + nRight + "]"
            }

            if (sMin == "") {
                sMin = sLeft + nRight;
                getIndex = data;
            } else {
                if (isLeftLess(sLeft + nRight, sMin)) {
                    sMin = sLeft + nRight;
                    getIndex = data;
                }
            }
        })

        matchSame.push([count, ...getIndex])
    }

    while (countTmp >= 0) {
        let arr = groupMapByCount.get(countTmp);
        let arrPrevious = matchSame[matchSame.length - 1];
        let sMin = ""
        let getIndex = [];

        arr.map((data, pos) => {
            if (data[0] > arrPrevious[1] && data[1] > arrPrevious[2]) {
                let sLeft = s.substring(arrPrevious[1] + 1, data[0]);
                let nRight = n.substring(arrPrevious[2] + 1, data[1]);

                if (sLeft.length > 0) {
                    sLeft = "(" + sLeft + ")"
                }

                if (nRight.length > 0) {
                    nRight = "[" + nRight + "]"
                }

                //  let sLeft = "(" + s.substring(arrPrevious[1] + 1, data[0]) + ")";
                //  let nRight = "[" + n.substring(arrPrevious[2] + 1, data[1]) + "]";
             //   console.log(countTmp, "*" + sMin + "*", "*" + sLeft + "*", "*" + nRight + "*")
                if (sMin == "") {
                    sMin = sLeft + nRight;
                    getIndex = data;
                } else {
                    if (isLeftLess(sLeft + nRight, sMin)) {
                        sMin = sLeft + nRight;
                        getIndex = data;
                    }
                }
            }
        })

        matchSame.push([countTmp, ...getIndex])
        countTmp--;
    }
    
    return matchSame;
    
    let sS1Same = new Array();
    for (let ind = 0; ind < matrix.length; ind++) {
        // 0 is count value, 1 is index in s length, 2 is index in s1 length
        let store = [0, -1, -1],
            isCount = false;
        for (let p = j; p < matrix[ind].length; p++) {
            if (count === matrix[ind][p]) {
                //	console.log(count, matrix[ind][p])
                isCount = true
                //	console.log(count, p)
                if (store[1] == -1) {
                    store = [count, ind, p]
                } else if (s[store[1]] > s[ind]) {
                    store = [count, ind, p]
                }
            }
        }

        if (isCount) {
            j = store[2] + 1
            sS1Same.push(store);
            count--;
        }
    }

  //  return sS1Same;
  
}
 */
let selectSameChar = (s, n) => {
    let matrixSame = getMatrixMaxSamePosition(s, n),
        j = 0,
        matrix = matrixSame.matrix,
        count = matrixSame.count,
        snSame = new Array();

    let groupMapByCount = new Map()
    // put it to map
    matrix.map((row, ind) => {
        row.map((col, pos) => {
            if (col !== "0") {
                if (groupMapByCount.has(col)) {
                    let arr = groupMapByCount.get(col)
                    arr.push([ind, pos])
                    groupMapByCount.set(col, arr)
                } else {
                    groupMapByCount.set(col, new Array([ind, pos]))
                }
            }
        })
    })

    let matchSame = []

    let recursiveGetALlPosible = (c, result) => {
        if (c < 0) {
            matchSame.push(result)
            return
        }
        let arr = groupMapByCount.get(c);
        let arrPrevious = c === count ? [-1, -1, -1] : result[count - c - 1]

        arr.map((data, pos) => {
            if (data[0] > arrPrevious[1] && data[1] > arrPrevious[2]) {
                result[count - c] = [c, ...data]
                recursiveGetALlPosible(c - 1, [...result])
            }
        })

    }

    let c = count;
    if (count > -1) {
        let result = new Array(count).fill([])
        recursiveGetALlPosible(c, result)
    }

    return matchSame;
}

let mergeOne = (arr, s, n) => {
    if (arr.length == 0) {
        return "(" + s + ")" + "[" + n + "]"
    }

    let str = ""
    if (arr[0][1] > 0) {
        str += "("
        for (let p = 0; p < arr[0][1]; p++)
            str += s[p]
        str += ")"
    }
    if (arr[0][2] > 0) {
        str += "["
        for (let p = 0; p < arr[0][2]; p++) {
            str += n[p]
        }
        str += "]"
    }

    str += s[arr[0][1]]

    for (let ind = 1; ind < arr.length; ind++) {
        if (arr[ind][1] - arr[ind - 1][1] > 1) {
            str += "("
            for (let p = arr[ind - 1][1] + 1; p < arr[ind][1]; p++)
                str += s[p]
            str += ")"
        }

        if (arr[ind][2] - arr[ind - 1][2] > 1) {
            str += "["
            for (let p = arr[ind - 1][2] + 1; p < arr[ind][2]; p++)
                str += n[p]
            str += "]"
        }

        str += s[arr[ind][1]]
    }

    let ind = arr.length - 1;
    if (ind < 0) {
        return str;
    }

    if (s.length - arr[ind][1] > 1) {
        str += "("
        for (let p = arr[ind][1] + 1; p < s.length; p++)
            str += s[p]
        str += ")"
    }

    if (n.length - arr[ind][2] > 1) {
        str += "["
        for (let p = arr[ind][2] + 1; p < n.length; p++)
            str += n[p]
        str += "]"
    }

    return str;
}

displayDiff = (oldVersion, newVersion) => {

    let res = selectSameChar(oldVersion, newVersion);
    let strMin = mergeOne(res[0] || new Array(), oldVersion, newVersion);

    for (let ind = 1; ind < res.length; ind++) {
        let str = mergeOne(res[ind], oldVersion, newVersion);
        if (isLeftLess(str, strMin)) {
            strMin = str
        }
    }

    return strMin
}