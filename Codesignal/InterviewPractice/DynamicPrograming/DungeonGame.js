
/**
 * call [needBlood, currentBlood] is output for dp. We have:
 * f(x,y ) = min(f(x, y-1), f(x-1, y)) (min of needBlood)            
 * f(0, 0) = [dungeon[x][y] > 0 ? 0 : - dungeon[x][y], 
 *            dungeon[x][y] > 0 ? dungeon[x][y] : 0]
 */
/*let storeVal;
dpDungeonGame = (x, y, dungeon) => {
    if(x < 0)  return y - 1 < 0 ? storeVal[0][0] : dpDungeonGame(0, y-1, dungeon)
    if(y < 0)  return x - 1 < 0 ? storeVal[0][0] : dpDungeonGame(x-1, 0, dungeon)
    if(storeVal[x][y]) return storeVal[x][y]
  
    let [needBlood, currentBlood] = dpDungeonGame(x-1, y, dungeon)

    if(dungeon[x][y] > 0){
        currentBlood += dungeon[x][y]
    }else{
        let t = dungeon[x][y] + currentBlood
        if(t <= 0){
            needBlood += -1*t
            currentBlood = 0
        }else
            currentBlood = t
    }
    
    let [needBlood1, currentBlood1] = dpDungeonGame(x, y-1, dungeon)

    if(dungeon[x][y] > 0){
        currentBlood1 += dungeon[x][y]
    }else{
        let t = dungeon[x][y] + currentBlood1
        if(t <= 0){
            needBlood1 += -1*t
            currentBlood1 = 0
        }else
            currentBlood1 = t
    }

    if(needBlood > needBlood1){
        storeVal[x][y] = [needBlood1, currentBlood1]
    }else if(needBlood == needBlood1){
        storeVal[x][y] = currentBlood1 > currentBlood ? [needBlood1, currentBlood1]: [needBlood, currentBlood]
    }else{
        storeVal[x][y] = [needBlood, currentBlood]
    }

    console.log(x, y, '  ', [needBlood1, currentBlood1], ' ', [needBlood, currentBlood])
    return storeVal[x][y]
}

dungeonGame = dungeon => {
    storeVal = new Array(dungeon.length).fill(1)
    storeVal = storeVal.map(v => new Array(dungeon[0].length))
    storeVal[0][0] = [dungeon[0][0] > 0 ? 0 : - dungeon[0][0], 
                      dungeon[0][0] > 0 ? dungeon[0][0] : 0];

    dpDungeonGame(dungeon.length-1, dungeon[0].length-1, dungeon)

    return storeVal[dungeon.length-1][dungeon[0].length-1][0] + 1
} */

/* if i using bottom - up DP approach then can not resolve this test:
    1, in: [[0,2,2], [-1,-1,2], [9,-1,-100]] out: 94
    2, in: [[0,2,2], [-1,-1,-1], [9,-1,-100]] out: 94
    hm, i can't resolve this problems. :((((
    => im using another approach top-down DP with rules:
     + call: x = dungeon.length-1, 
             y = dungeon[0].length-1
            => init storeVal[x][y] = dungeon[x][y]
     + when back to [0, 0]:
         assign needBlood = f(i+1, j, dungeon) + dungeon[i][j]
         assign needBlood1 = f(i, j+1, dungeon) + dungeon[i][j]
         // note: If at any point knight health level drops to 0 or below, knight  dies immediately => show needBlood, needBlood1 always 1 (health )
         if(needBlood >= 0) needBlood = -1
         if(needBlood1 >= 0) needBlood1 = -1
         // using max because health is always < 0
         storeVal[i][j] =  Math.max(needBlood1, needBlood)
        
    => return storeVal[0][0] > 0 ? 1 : Math.abs(storeVal[0][0])
*/
dpDungeonGame = (x, y, dungeon) => {
    let a = dungeon.length-1,
        b = dungeon[0].length-1
    if(x > a)  return y + 1 > b ? storeVal[a][b] : dpDungeonGame(a, y+1, dungeon)
    if(y > b)  return x + 1 > a ? storeVal[a][b] : dpDungeonGame(x+1, b, dungeon)
    if(storeVal[x][y]) return storeVal[x][y]
  
    let needBlood = dpDungeonGame(x+1, y, dungeon) + dungeon[x][y]
    let needBlood1 = dpDungeonGame(x, y+1, dungeon) + dungeon[x][y]

    if(needBlood >= 0) needBlood = -1
    if(needBlood1 >= 0) needBlood1 = -1
    
    storeVal[x][y] = Math.max(needBlood1, needBlood)
    return storeVal[x][y]
}

var storeVal
dungeonGame = dungeon => {
    storeVal = new Array(dungeon.length).fill(1)
    storeVal = storeVal.map(v => new Array(dungeon[0].length))
    let x = dungeon.length - 1,
        y = dungeon[0].length - 1
    storeVal[x][y] = dungeon[x][y] - 1;

    dpDungeonGame(0, 0, dungeon)

    return storeVal[0][0] > 0 ? 1 : Math.abs(storeVal[0][0])
}