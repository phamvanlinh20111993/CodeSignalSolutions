/**
 * Just past 11/22. hmm, I knew where I was wrong
 */
/*calculateBasins = grid => {
    const STR_EMPTY = '',
          STR_UNDERCORE = '-',
          neighborCoordX = [-1, 0, 1, 0],
          neighborCoordY = [0, -1, 0, 1];
    
    let isVisited = new Array(grid.length).fill([])
        isVisited = isVisited.map(v => new Array(grid[0].length).fill(-1))
    
    let basinMatrix = new Array(grid.length).fill([])
        basinMatrix = basinMatrix.map(v => new Array(grid[0].length).fill(STR_EMPTY))
          
    isInGrid = ([x, y]) => x < grid.length && y < grid[0].length && x >= 0 && y >= 0
    
    isEqualCoords = ([x, y], [x1, y1]) => x == x1 && y == y1
    
    
    getFourNeighbor = (ind, pos) => {
        let fourNeighbor = [[ind, pos, grid[ind][pos]]]
        for(let nextNeighbor = 0; nextNeighbor < neighborCoordX.length; nextNeighbor++){
            let nextX = ind + neighborCoordX[nextNeighbor], 
                nextY = pos + neighborCoordY[nextNeighbor];
                
            if(isInGrid([nextX, nextY]))
                fourNeighbor.push([nextX, nextY, grid[nextX][nextY]])
        }
        
        return fourNeighbor
    }
    
    getMinNeighbor = fourNeighbor => {
        let minNeighbor = [-1, -1, 101000]
        for(let neighbor of fourNeighbor){
            if(minNeighbor[2] > neighbor[2])
                minNeighbor = neighbor
        }
        return minNeighbor
    }
    
    let saveOriginData = new Map();
    
    dfsCheck = ([x, y]) =>{
        isVisited[x][y] = 1;
        let tempNeighbor = []
            
        for(let nextNeighbor = 0; nextNeighbor < neighborCoordX.length; nextNeighbor++){
            let nextX = x + neighborCoordX[nextNeighbor], 
                nextY = y + neighborCoordY[nextNeighbor];
                
            if(isInGrid([nextX, nextY]) && isVisited[nextX][nextY] < 0)
                tempNeighbor.push([nextX, nextY, grid[nextX][nextY]])
        }
        
        let fourNeighbor = getFourNeighbor(x, y)
        let minNeighbor = getMinNeighbor(fourNeighbor) 
            
        if(isEqualCoords(minNeighbor, [x, y])){
            for(let ind = 1; ind < fourNeighbor.length; ind++){
                let neighbor = fourNeighbor[ind];
                if(neighbor[2] > minNeighbor[2] && basinMatrix[neighbor[0]][neighbor[1]] == STR_EMPTY)
                    basinMatrix[neighbor[0]][neighbor[1]] = basinMatrix[x][y]
            }
        }else{
            saveOriginData.set(x + STR_UNDERCORE + y, minNeighbor[0] + STR_UNDERCORE + minNeighbor[1])
        }
        
        for(let neighbor of tempNeighbor)
            dfsCheck([neighbor[0], neighbor[1]])
    }
    
    let t = 0, v = 0, min = 1000
    for(let ind = 0; ind < grid.length; ind++){
        for(let pos = 0; pos < grid[0].length; pos++){
            let minNeighbor = getMinNeighbor(getFourNeighbor(ind, pos))
            if(isEqualCoords(minNeighbor, [ind, pos])){
                basinMatrix[ind][pos] = ind + STR_UNDERCORE + pos;
                if(min > minNeighbor[2]){
                    t = ind
                    v = pos
                    min = minNeighbor[2]
                }
                    
            }
        }
    }
    
 //   console.log(basinMatrix)
    dfsCheck([t, v]);
//    console.log(basinMatrix)

    let flag = saveOriginData.size > 0
    while(flag){
        flag = false;
        for (let key of saveOriginData.keys()) {
            let k = key.split(STR_UNDERCORE)
            let v = saveOriginData.get(key).split(STR_UNDERCORE)
            if(basinMatrix[v[0]][v[1]] != STR_EMPTY){
            //    console.log(basinMatrix[k[0]][k[1]], basinMatrix[v[0]][v[1]])
                basinMatrix[k[0]][k[1]] = basinMatrix[v[0]][v[1]]
            }else flag = true
        }
    }
    
    let countBasin = new Map();
    basinMatrix.map(r => {
        r.map(v => {
            if(!countBasin.has(v)) countBasin.set(v, 1)
            else countBasin.set(v, countBasin.get(v) + 1)
        })
    })
    
    return Array.from(countBasin.values()).sort((a, b) => b - a);
} */
/**
 * With above solution I have encountered a problem: 
 * If the point being visited is a sink, then the points around it do not necessarily 
 * belong to that pool.
 * Example: when visit at grid[0][0] value is 1, it is a sink then in my solution, 2 and 5 is belong 
 * 1. But if i visit at grid[0][1] with value is 2 first ??? then 5 and 7 belong 2. it not true.
 * So, im implement another solution.
 */
calculateBasins = grid => {
    const STR_EMPTY = '',
          STR_UNDERCORE = '-',
          neighborCoordX = [-1, 0, 1, 0],
          neighborCoordY = [0, -1, 0, 1];

    let basinMatrix = new Array(grid.length).fill([])
        basinMatrix = basinMatrix.map(v => new Array(grid[0].length).fill(STR_EMPTY))
          
    isInGrid = ([x, y]) => x < grid.length && y < grid[0].length && x >= 0 && y >= 0
    
    isEqualCoords = ([x, y], [x1, y1]) => x == x1 && y == y1
    
    getFourNeighbor = (ind, pos) => {
        let fourNeighbor = [[ind, pos, grid[ind][pos]]]
        for(let nextNeighbor = 0; nextNeighbor < neighborCoordX.length; nextNeighbor++){
            let nextX = ind + neighborCoordX[nextNeighbor], 
                nextY = pos + neighborCoordY[nextNeighbor];
                
            if(isInGrid([nextX, nextY]))
                fourNeighbor.push([nextX, nextY, grid[nextX][nextY]])
        }
        
        return fourNeighbor
    }
    
    getMinNeighbor = fourNeighbor => {
        let minNeighbor = [-1, -1, 101000]
        for(let neighbor of fourNeighbor){
            if(minNeighbor[2] > neighbor[2])
                minNeighbor = neighbor
        }
        return minNeighbor
    }
    
    // find all sink in grid and put mark it to 
    for(let ind = 0; ind < grid.length; ind++){
        for(let pos = 0; pos < grid[0].length; pos++){
            let minNeighbor = getMinNeighbor(getFourNeighbor(ind, pos))
            if(isEqualCoords(minNeighbor, [ind, pos])){
                basinMatrix[ind][pos] = ind + STR_UNDERCORE + pos; 
            }
        }
    }
    
    findSink = ([x, y], savePathToSink) =>{
      let minNeighbor = getMinNeighbor(getFourNeighbor(x, y));
      if(basinMatrix[minNeighbor[0]][minNeighbor[1]] !== STR_EMPTY){
        basinMatrix[x][y] = basinMatrix[minNeighbor[0]][minNeighbor[1]];
        return basinMatrix[x][y]
      }else if(!isEqualCoords(minNeighbor, [x, y])){
        savePathToSink.push(minNeighbor);
        return findSink([minNeighbor[0], minNeighbor[1]], savePathToSink)
      }else return basinMatrix[x][y]
    }
    
    for(let ind = 0; ind < grid.length; ind++){
        for(let pos = 0; pos < grid[0].length; pos++){
            if(basinMatrix[ind][pos] == STR_EMPTY){
                let savePathToSink = [[ind, pos]]
                let typeSink = findSink([ind, pos], savePathToSink)
                for(let v of savePathToSink){
                   basinMatrix[v[0]][v[1]] = typeSink
                }
            }
        }
    }
     
    let countBasin = new Map();
    basinMatrix.map(r => {
        r.map(v => {
            if(!countBasin.has(v)) countBasin.set(v, 1)
            else countBasin.set(v, countBasin.get(v) + 1)
        })
    })
    
    return Array.from(countBasin.values()).sort((a, b) => b - a);
}