/**
 * 
 */
 // coordinates get to next pipe when know type of current pipe
const nextPipeCoords = {
        '1': {'0,1': [0, -1], '0,-1': [0, 1]},
        '2': {'1,0': [-1, 0], '-1,0': [1, 0]},
        '3': {'1,0': [0, 1], '0,1': [1, 0]},
        '4': {'0,1': [-1, 0], '-1,0': [0, 1]},
        '5': {'0,-1': [-1, 0], '-1,0': [0, -1]},
        '6': {'1,0': [0, -1], '0,-1': [1, 0]},
        '7': {'1,0': [-1, 0], '-1,0': [1, 0], '0,1': [0, -1], '0,-1': [0, 1]}
};

//check right pipe coords correct when connect from pour source
const validPipeSource = {'-1,0': '2367', '1,0': '2457', '0,1': '1567', '0,-1': '1347'};

isInSideMaxtrix = ([r, c], [x, y]) => x > -1 && x < r && y > -1 && y < c

isPourSource = v => /[a-z]/.test(v)

isSink = v => /[A-Z]/.test(v)

isPipe = v => /[1-7]/.test(v)

countPipesDrown = (totalStep, nextPipeCoord, state) => {
   
    //find the sink
    let countPipe = new Set(), count = 0;
    countPipe.add(`${nextPipeCoord.x}${nextPipeCoord.y}`)
    while(count++ < totalStep){
        let nX = nextPipeCoord.x + nextPipeCoord.nextDirect[0],
            nY = nextPipeCoord.y + nextPipeCoord.nextDirect[1];
            
        if(isInSideMaxtrix([state[0].length, state.length], [nX, nY]) 
        && isPipe(state[nY][nX])){
            let key = `${-nextPipeCoord.nextDirect[0]},${-nextPipeCoord.nextDirect[1]}`;
            nextPipeCoord = {x: nX, y: nY, nextDirect: nextPipeCoords[state[nY][nX]][key]}
            countPipe.add(`${nX}${nY}`)
        }else{
            break;
        }
    }

    return countPipe;
}

getPipesCorrectFromPourSourceToSink = (coord, state) => {

    findSinkFromPipe = nextPipeCoord => {
        //find the sink
        let flag = true, countPipe = new Set();
        while(nextPipeCoord){
            let nX = nextPipeCoord.x + nextPipeCoord.nextDirect[0],
                nY = nextPipeCoord.y + nextPipeCoord.nextDirect[1];
            
            if(isInSideMaxtrix([state[0].length, state.length], [nX, nY])){
                if(isPipe(state[nY][nX])){
                    let key = `${-nextPipeCoord.nextDirect[0]},${-nextPipeCoord.nextDirect[1]}`;
                    nextPipeCoord = {x: nX, y: nY, nextDirect: nextPipeCoords[state[nY][nX]][key]}
                    countPipe.add(`${nX}${nY}`)
               }else{
                    if(!isSink(state[nY][nX])){
                        flag = false;
                        console.log('fuck', nY, nX)
                    }
                    break;
               }
            } else {
                flag = false;
                 console.log('fuck1', nY, nX)
                break;
            }
        }

        return {flag, countPipe};
    }

    let directionCoords = [[-1, 0], [0, 1], [1, 0], [0, -1]],
        x = coord.x, 
        y = coord.y,
        nextPipeCoord = null, start = 0,
        flag = true, 
        originPipeCoord = [],
        minStart = 101,
        countPipe = new Set();

    for(const direct of directionCoords){
        let nX = x + direct[0],
            nY = y + direct[1];
        if(isInSideMaxtrix([state[0].length, state.length], [nX, nY]) 
           && isPipe(state[nY][nX])  
           && validPipeSource[`${direct[0]},${direct[1]}`].includes(state[nY][nX])){
            countPipe.add(`${nX}${nY}`)
            //invert coord of start
            let key = `${-direct[0]},${-direct[1]}`;
            nextPipeCoord = {x: nX, y: nY, nextDirect: nextPipeCoords[state[nY][nX]][key]}
            //get first pipe connected with pour source
            originPipeCoord.push(nextPipeCoord)
            let result = findSinkFromPipe(nextPipeCoord);
            countPipe = new Set([...countPipe, ...result.countPipe])
            if(!result.flag){
                flag = false;
                minStart = minStart > result.countPipe.size ? result.countPipe.size: minStart
            }
        }
    }

    return  {flag, countPipe, originPipeCoord, minStart};
}

getTotalPipeCorrect = (listPourSource, state) => {
    let finalTotal = 0, countPipe = new Set(), 
        minPosPipeCrush = 101, originPipeCoord = [], flag = true;

    for(const coord of listPourSource){
        let res = getPipesCorrectFromPourSourceToSink(coord, state)
        countPipe = new Set([...countPipe, ...res.countPipe])
        if(!res.flag){
            flag = false;
            if(minPosPipeCrush > res.minStart)
                minPosPipeCrush = res.minStart
        }
        originPipeCoord = originPipeCoord.concat(res.originPipeCoord)
    }

    if(!flag){
        let total = 0
  //      console.log(minPosPipeCrush, originPipeCoord)
        let cTemp = new Set(), surplus = 0
        for(const coord of originPipeCoord){
            let c = countPipesDrown(minPosPipeCrush+1, coord, state)
    
            if(c.size > minPosPipeCrush+1)
                surplus += c.size - minPosPipeCrush - 1
            cTemp = new Set([...cTemp,...c])
        }
        return -cTemp.size + surplus
    }

 //   console.log(minPosPipeCrush, originPipeCoord)

    return countPipe.size;
}

pipesGame = state => {
    let listPourSource = [], coords;

    for(let y = 0; y < state.length; y++){
        coords = state[y] = state[y].split("");
        for(let x = 0; x < coords.length; x++)
            isPourSource(coords[x]) && listPourSource.push({x, y})
    }
  //  console.log(state)
    return getTotalPipeCorrect(listPourSource, state);
}
