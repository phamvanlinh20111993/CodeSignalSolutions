/**
 * 
 */
function farmingResources(friendlyTroops, enemyTroops, loggingCamp, impassableCells) {
    const NEIGHBORHOOD_COORDS = [[-1, 0], [1, 0], [0, -1], [1, -1], [0, 1], [-1, 1]];
    const impassableCellsMap = {}
    for(cell of impassableCells){
        impassableCellsMap[`${cell[0]}-${cell[1]}`] = 1
    }
    isGoToImpassableCell = point => impassableCellsMap[`${point[0]}-${point[1]}`] ? true : false;
    isEquals = (point1, point2) => point1[0] == point2[0] &&  point1[1] == point2[1]
    
    // type [a, b, c]: a, b are location of your troops; c size of troops
    getMinPathToLoggingCamp = troop =>{
        let isVisited = {};
        let queue = [[troop[0], troop[1], 0]];
        let path = 0;
        
        while(queue.length > 0){
            let currentCoordTroop = queue.shift();
            if(isEquals(loggingCamp, currentCoordTroop)){
                path = currentCoordTroop[2];
                break;
            }
            
            for(let ind = 0; ind < NEIGHBORHOOD_COORDS.length; ind++){
                let nextCoordX = currentCoordTroop[0] + NEIGHBORHOOD_COORDS[ind][0],
                    nextCoordY =  currentCoordTroop[1] + NEIGHBORHOOD_COORDS[ind][1];
                if(!isVisited[`${nextCoordX}-${nextCoordY}`] 
                    && !isGoToImpassableCell([nextCoordX, nextCoordY])){
                     isVisited[`${nextCoordX}-${nextCoordY}`] = 1;
                    queue.push([nextCoordX, nextCoordY, currentCoordTroop[2]+1])   
                }
            }
        }
        
        return path;
    }
    
    let friendlyTroopsFree = getMinPathToLoggingCamp(friendlyTroops) * friendlyTroops[2]
    let enemyTroopsFree = getMinPathToLoggingCamp(enemyTroops) * enemyTroops[2]
 
    return friendlyTroopsFree < enemyTroopsFree;
}
