/**
 * Each cell in a 2D grid contains either a wall ('W') or an enemy ('E'), or is
 * empty ('0'). Bombs can destroy enemies, but walls are too strong to be
 * destroyed. A bomb placed in an empty cell destroys all enemies in the same
 * row and column, but the destruction stops once it hits a wall.
 * 
 * Return the maximum number of enemies you can destroy using one bomb.
 * 
 * Note that your solution should have O(field.length · field[0].length)
 * complexity because this is what you will be asked during an interview.
 * 
 * Example
 * 
 * For
 * 
 * field = [["0", "0", "E", "0"], ["W", "0", "W", "E"], ["0", "E", "0", "W"],
 * ["0", "W", "0", "E"]] the output should be bomber(field) = 2.
 * 
 * Placing a bomb at (0, 1) or at (0, 3) destroys 2 enemies.
 * 
 * Input/Output
 * 
 * [execution time limit] 4 seconds (js)
 * 
 * [input] array.array.char field
 * 
 * A rectangular matrix containing characters '0', 'W' and 'E'.
 * 
 * Guaranteed constraints: 0 ≤ field.length ≤ 100, 0 ≤ field[i].length ≤ 100.
 * 
 * [output] integer
 */


/**
 * Using DP
 */
bomber = field => {
    if(field.length == 0){
        return 0
    }
    let storageData = new Array(field.length).fill([])
    storageData = storageData.map(v => 
        new Array(field[0].length).fill([])) 
    for(let ind = 0; ind < storageData.length; ind++){
        for(let pos = 0; pos < storageData[0].length; pos++){
            storageData[ind][pos] = [-1, -1, -1, -1]
        }
    }
    emptyField = []
    field.map((r, i) => r.map((v, p) => v == '0' ? emptyField.push([i, p]) : 1))
    
    if(emptyField.length == 0){
        return 0
    }
    const DIRECTION = [[-1, 0], [0, -1], [0, 1], [1, 0]]
    
    checkInField = ([x, y]) => x >= 0 && x < field.length 
                              && y >= 0 && y < field[0].length
    
    dp = ([i, p], direct) =>{
        if(direct > -1 && storageData[i][p][direct] > -1){
            return storageData[i][p][direct]
        }
        
        if(field[i][p] == '0' || field[i][p] == 'E'){
            let addition = field[i][p] == 'E' ? 1 : 0
            if(direct > -1){
                let v = DIRECTION[direct]
                let [x, y] = [v[0] + i, p + v[1]]
                if(checkInField([x, y])){
                    let result = dp([x, y], direct)
                    storageData[i][p][direct] = addition + result
                }else{
                    storageData[i][p][direct] = addition
                }
                
                return storageData[i][p][direct]
            }else{
                DIRECTION.map((v, ind) => {
                    let [x, y] = [v[0] + i, p + v[1]]
                    if(checkInField([x, y])){
                        let result = dp([x, y], ind)
                        storageData[i][p][ind] = addition + result
                    }else{
                        storageData[i][p][ind] = addition
                    }
                })
            }
        }else{
            storageData[i][p] = [0, 0, 0, 0]
        }
    
        return 0
    }
    
    let max = 0
    emptyField.map(v => {
        if(storageData[v[0]][v[1]][0] < 0){
            dp(v, -1) 
        }
        let tmp = storageData[v[0]][v[1]].reduce((v, t) => t += v, 0)
        max = max < tmp ? tmp : max
    })
    return max
}

/**
 * Using normal way
 */
let bomber_1 = field => {
    
    if(field.length == 0){
        return 0
    }
    
    checkEnemies = ([x, y]) => {
        let count = 0
        let temp = x + 1
        while(temp <  field.length){
            if(field[temp][y] == 'W') break;
            else if(field[temp][y] == 'E')  count++;
            temp++
        }
        temp = x - 1
         while(temp >= 0){
            if(field[temp][y] == 'W') break;
            else if(field[temp][y] == 'E') count++;
            temp--
        }
        
        temp = y - 1
        while(temp >= 0){
            if(field[x][temp] == 'W') break;
            else if(field[x][temp] == 'E')  count++;
            temp--
        }
        
        temp = y + 1
        while(temp < field[0].length){
            if(field[x][temp] == 'W') break;
            else if(field[x][temp] == 'E')  count++;
            temp++
        }
        
        return count
    }
    
    let maxCount = 0
    for(let ind = 0; ind < field.length; ind++){
        for(let pos = 0; pos < field[0].length; pos++){
            if(field[ind][pos] == '0'){
                let count = checkEnemies([ind, pos])
                maxCount = maxCount > count ? maxCount : count
            }
        }
    }
    
    return maxCount
}
