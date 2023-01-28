/**
 * 
 */
electricField = (grid, wires) => {
    
    let directions = [[1, 0], [-1, 0], [0, 1], [0, -1]];
    
    let isInRange = ([x, y]) => x >= 0 && y >= 0 && x < grid[1] 
                               && y < grid[0]
    
    let isVisited = {}
    
    let mapWirePoint = new Map()
    wires.map(([x, y, x1, y1]) => {
        let minX = Math.min(x, x1)
        let minY = Math.min(y, y1)
        let maxX = Math.max(x, x1)
        let maxY = Math.max(y, y1)
        
        if(y == y1){
            let i = minX
            while(i <= maxX){
                mapWirePoint.set(`${i}-${minY}`, 1)
                i += 0.5
            }
        }
        
        if(x == x1){
            let i = minY
            while(i <= maxY){
                mapWirePoint.set(`${minX}-${i}`, 1)
                i += 0.5
            }
        }
    })
    
    const canPassWire = ([curX, curY], [nextX, nextY]) => {
        let [x, y] = [(curX + nextX) / 2, (curY + nextY) / 2]
        return !mapWirePoint.has(`${x}-${y}`)
    }
    
    // current = [x, y]
    const bfs = cur => {
        
        let queue = [{coord: cur, level: 0}]
        isVisited[`${cur[0]}-${cur[1]}`] =  1
        
        while(queue.length > 0){
            
            let s = queue.shift();
            
            let current = s.coord
            let level = s.level
            
            if(current[0] == grid[1]-0.5 && current[1] == grid[0]-0.5){
               return level
            }
        
            for(const direct of directions){
                const nextX = direct[0] + current[0]
                const nextY = direct[1] + current[1]
                
                if(!isVisited[`${nextX}-${nextY}`] && canPassWire(current, [nextX, nextY]) 
                    && isInRange([nextX, nextY])){
                     isVisited[`${nextX}-${nextY}`] =  1    
                    queue.push({coord:[nextX, nextY], level: level + 1})
                }
            }
        }
        
        return -1
    }
    
    return bfs([0.5, 0.5])
}
