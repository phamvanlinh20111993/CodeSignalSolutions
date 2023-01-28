/**
 * 
 */

knightOnBoardProbability = (n, m, steps, x, y) => {
    if(steps == 0) return 1;
    
    const PX = [-2, -1, 1, 2, 2, 1,-1, -2];
    const PY = [1, 2, 2, 1, -1, -2, -2, -1];
    let expectedResult = 8**steps;
    let actualResult = 0;
    
    isInBoard = (a, b) => a >= 0 && b >= 0 && a < n && b < m
    
    checkValidNextKnight = (x, y) => {
        let stack = []
        for(let i = 0; i < 8; i++){
            if(isInBoard(x + PX[i], y + PY[i]))
                stack.push([x + PX[i],  y + PY[i]])
        }
        return stack;
    }
    
    initValidKnightPositionInBoard = () => {
        let map = {}
        for(let i = 0; i < n; i++){
            for(let j = 0; j < m; j++)
                map[`${i}-${j}`] = checkValidNextKnight(i, j);
        }
        return map
    }
    
    let validPosEachStep = {};
    let validPosInBoard = initValidKnightPositionInBoard();
    let dispValidPosEachStep = {};
    dispValidPosEachStep[`${x}-${y}`] = 1;
    while(steps > 0){
        validPosEachStep = {...dispValidPosEachStep}
        dispValidPosEachStep = {}
        let keyInValidPos = Object.keys(validPosEachStep)
        // max sapce m*n
        for(let i = 0; i < keyInValidPos.length; i++){
            let listValidPos = validPosInBoard[keyInValidPos[i]]
            for(let pos = 0; pos < listValidPos.length; pos++){
                let coordKeyMap = `${listValidPos[pos][0]}-${listValidPos[pos][1]}`
                if(!dispValidPosEachStep[coordKeyMap])
                   dispValidPosEachStep[coordKeyMap] = validPosEachStep[keyInValidPos[i]]
                else
                   dispValidPosEachStep[coordKeyMap] += validPosEachStep[keyInValidPos[i]]
            }
        }
        
        steps --;
    }
    
    let keysInFinalStep = Object.keys(dispValidPosEachStep)
    for(let ind = 0; ind < keysInFinalStep.length; ind++)
        actualResult += dispValidPosEachStep[keysInFinalStep[ind]]
    
    return actualResult/expectedResult;
}
