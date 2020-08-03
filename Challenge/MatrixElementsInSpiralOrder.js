/**
 * 
 */
matrixElementsInSpiralOrder = m => {
    
    if(m.length == 0)
        return []
    
    const DIRECTION = [[0, 1], [1, 0], [0, -1], [-1, 0]]
    const RANGE_LIMIT = [m[0].length-1, m.length-1, 0, 0]
    const SQUARE_CYCLE = 4
    
    isInRangeLimit = (x, y) => {
        return x <= RANGE_LIMIT[1] && x >= RANGE_LIMIT[3] 
            && y <= RANGE_LIMIT[0] && y >= RANGE_LIMIT[2] 
    }
    
    let x = y = 0, r = [], cycle = 0
    
    while(isInRangeLimit(x, y)){
        r.push(m[x][y])
        if( ( cycle == 0 && y >= RANGE_LIMIT[0] )
            || (cycle == 1 && x >= RANGE_LIMIT[1] ) 
            || (cycle == 2 && y <= RANGE_LIMIT[2] ) 
            || (cycle == 3 && x <= RANGE_LIMIT[3] ) ){
            
            if(cycle == 2 && y <= RANGE_LIMIT[2]){
                RANGE_LIMIT[3]++
            }
            
            if(cycle == 3 && x <= RANGE_LIMIT[3]){
                RANGE_LIMIT[2]++
                RANGE_LIMIT[0]--
                RANGE_LIMIT[1]--
            }
            
            cycle++
            cycle %= SQUARE_CYCLE
        }
        
        x += DIRECTION[cycle][0]
        y += DIRECTION[cycle][1]
    }

    return r
}
