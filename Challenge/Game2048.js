/**
 * 
 */


shiftLToR = a => {
    let l = a.length - 1, f = false, tmp = [];
    while(l >= 0){
        if(a[l] > 0){
            if(tmp.length == 0){
                tmp.unshift(a[l])
            }else{
                if(!f && tmp[0] == a[l]){
                    tmp[0] += a[l]
                    f = true
                }else{
                    tmp.unshift(a[l])
                    f = false
                }   
            }
        }
        l--
    }

    l = a.length - tmp.length, v = 0
    while(v++ < l) tmp.unshift(0)
    return tmp
}

shiftRToL = a => {
    let l = 0, i = 0, f = false, tmp, p = a.length
    while(l < p){
        if(a[l] > 0){
            if(f){
               if(a[i] == a[l]){
                   a[i] += a[l]
                   a[l] = 0
                   f = false
               }else i = l
            }else{
                i = l
                f = true
            }
        }
        l++
    }
    let m = a.filter(v => v > 0), v = 0
    l = a.length - m.length
    while(v++ < l) m.push(0)

    return m
}

goLeft = grid => {
    for(let i = 0; i < grid.length; i++)
        grid[i] = shiftRToL(grid[i])
    return grid
}

goRight = grid => {
    for(let i = 0; i < grid.length; i++)
        grid[i] = shiftLToR(grid[i]);
    return grid
}

goUp = grid => {
    let tmp = new Array(grid[0].length);
    for(let i = 0; i < grid.length; i++){
        for(j = 0; j < grid[0].length; j ++){
            tmp[j] = grid[j][i]
        }
        tmp = shiftRToL(tmp)
        for(j = 0; j < grid[0].length; j ++){
            grid[j][i] = tmp[j]
        }
    }

    return grid
}

goDown = grid => {

    let tmp = new Array(grid[0].length);
    for(let i = 0; i < grid.length; i++){
        for(j = 0; j < grid[0].length; j ++){
            tmp[j] = grid[j][i]
        }
        tmp = shiftLToR(tmp)
        for(j = 0; j < grid[0].length; j ++){
            grid[j][i] = tmp[j]
        }
    }

    return grid
}

game2048 = (grid, path) => {
     console.log(shiftLToR([2, 0, 2 , 4]))
    // console.log(shiftRToL([4, 0, 2 , 2]))

    for(e of path){
        if(e == "L")
            goLeft(grid)
        if(e == "R")
            goRight(grid)
        if(e == "U")
            goUp(grid)
        if(e == "D")
            goDown(grid)
    }

    return grid
}
