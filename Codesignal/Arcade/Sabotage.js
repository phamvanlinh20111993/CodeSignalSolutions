/**
 *
 */

  /*sabotageCheck = curC => {
        isVisited[`${curC[0]}-${curC[1]}`] = true
        let nextDirectCoords = directionInstruct[hangar[curC[1]][curC[0]]]
        let nextX = curC[0] + nextDirectCoords[0]
        let nextY = curC[1] + nextDirectCoords[1]

        if(isVisited[`${nextX}-${nextY}`]){
            return true
        }
        if(!isInRange([nextX, nextY])){
           return false
        }

        return sabotageCheck([nextX, nextY])
    } */


sabotage = hangar => {
    const directionInstruct = {'U': [0, -1],
                               'D': [0, 1],
                               'R': [1, 0],
                               'L': [-1, 0]
                              }
    let isVisited = {}

    let isInRange = ([x, y]) => x >= 0 && y >= 0
                                && x < hangar[0].length
                                && y < hangar.length


    let out = {}
    let canNotOut = {}
    sabotageCheck = cur => {
        let stack = [cur]

        while(stack.length > 0){
            let curC = stack.pop()
            isVisited[`${curC[0]}-${curC[1]}`] = true
            let nextDirectCoords = directionInstruct[hangar[curC[1]][curC[0]]]
            let nextX = curC[0] + nextDirectCoords[0]
            let nextY = curC[1] + nextDirectCoords[1]

            if(isVisited[`${nextX}-${nextY}`]){
                return true
            }
            if(!isInRange([nextX, nextY])){
                return false
            }
            stack.push([nextX, nextY])
        }

        return true
    }

    let totalWay = 0
    hangar.map((r, i) => {
        r.map((v, p) => {
            let key = `${p}-${i}`
            if(!out[key] && !canNotOut[key]){
                let flag = sabotageCheck([p, i])
                totalWay += flag ? 1 : 0
                if(flag){
                    Object.keys(isVisited).map(function(key, index) {
                        canNotOut[key] = true;
                    });
                }else{
                    Object.keys(isVisited).map(function(key, index) {
                        out[key] = true;
                    });
                }

                isVisited = {}
            }else if(canNotOut[key]){
                totalWay += 1
            }
        })
    })

    return totalWay;
}

