/**
 * 
 */


/*
const baseDirection = [[1, 0], [1, 1], [0, 1]];
checkPointIsInSquare = ([br, bc], [x, y]) => x > -1 && y > -1 && x < br && y < bc;

getMaxSquare = ([x, y], matrix) => {
    let maxSquare = 1,
        checkIsNewPoint = new Map(),
        storeDirectPoint = [[x, y]],
        storeDirectPointTmp;
    
    while(true){
        storeDirectPointTmp = [];
        
        for (const [x, y] of storeDirectPoint){

            for(baseD of baseDirection){
                let pX = x + baseD[0],
                    pY = y + baseD[1],
                    key = `${pX}-${pY}`;
                if(checkPointIsInSquare([matrix.length, matrix[0].length], [pX, pY]) && matrix[pX][pY] === "1"){
                    if(!checkIsNewPoint.has(key)){
                        checkIsNewPoint.set(key, 1);
                        storeDirectPointTmp.push([pX, pY])
                    }
                }else
                    return maxSquare;
            }
        }

        storeDirectPoint = storeDirectPointTmp
        maxSquare += storeDirectPoint.length
    }
}

// using brute force
maximalSquare = m => {
    let maxSquare = 0;
    for(let x = 0; x < m.length; x++){
        for (let y = 0; y < m[0].length; y++){
            if(m[x][y] === "1"){
                let t = getMaxSquare([x, y], m)
                maxSquare = t > maxSquare ? t : maxSquare
            }
        }
    }

    return maxSquare;
} */




/*Using dp:
   Call f(x, y) is maximum square at each position (x, y) in matrix with matrix[x][y] = '1'. 
   We have:
   let preDiagonal = f(x-1, y) if x-1 >= 0 && y >= 0
                   = 0         otherwise
   let preTop = f(x, y-1) if x >= 0 && y-1 >= 0
              = 0         otherwise      
   let preHorizontal = f(x-1, y-1) if x-1 >= 0 && y-1 >= 0 
                     = 0           otherwise 
   => f(x, y) = 1 + min(preDiagonal, preTop, preHorizontal) if maxtrix[x][y] == '1'
              = 0                                           if maxtrix[x][y] == '0'
*/
let arrDp;
getMaximalSquare = ([x, y], matrix) =>{
    if(arrDp[x][y] > -1)
        return arrDp[x][y]

    let preTop = preDiagonal = preHorizontal = 0;
    if(x > 0 && y > -1)
        preTop = getMaximalSquare([x - 1, y], matrix);
    if(x > -1 && y > 0)
        preHorizontal = getMaximalSquare([x, y - 1], matrix);
    if(x > 0 && y > 0)
        preDiagonal = getMaximalSquare([x - 1, y - 1], matrix);

    if( matrix[x][y] === "0")
        arrDp[x][y] = 0
    else
        arrDp[x][y] = 1 + Math.min(preTop, preHorizontal, preDiagonal);

    return arrDp[x][y];
}


maximalSquare = m => {
    if(m.length == 0) return 0
    let maxSquare = 0;

    arrDp = new Array(m.length).fill([])
    arrDp = arrDp.map(v => new Array(m[0].length).fill(-1))
    
    for(let x = m.length - 1; x >= 0; x--){
        for (let y = m[0].length - 1; y >= 0; y--){
            if(m[x][y] === "1"){
               let square = getMaximalSquare([x, y], m)
               maxSquare = Math.max(square, maxSquare)
            }
        }
    }
 
    return maxSquare**2;
}
