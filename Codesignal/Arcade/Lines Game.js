/**
 * 
 */

const baseDirection = [
    [-1, 0],
    [0, 1],
    [1, 0],
    [0, -1]
]

isInsideField = ([r, c], [x, y]) => x > -1 && x < r && y > -1 && y < c

isEmptyField = v => /[^RBOVGYC]/i.test(v)

deleteBall = (start, end, type, field) => {
    switch(type){
        case "horizontal":
            for(let ind = start[0]; ind <= end[0]; ind++)
                field[start[1]][ind] = '.';
            break;

        case "vertical":
            for(let ind = start[1]; ind <= end[1]; ind++)
                field[ind][start[0]] = '.';
            break;

        case "main-diagonal":
            let y = start[1]
            for(let ind = start[0]; ind <= end[0]; ind++)
                field[y++][ind] = '.';
            break;

        case "filler-diagonal":
            let x = start[0]
            for(let ind = start[1]; ind <= end[1]; ind++)
                field[ind][x--] = '.';
            break;

        default:
            break;
    }

    return field;
}

showField = field => {
    let fieldArchive = []
    for(const f of field){
        let archiveF = f.join('')
        fieldArchive.push(archiveF)
        console.log(archiveF)
    }

    return fieldArchive;
}

getScoreOnHorizontal = (curClick, field) => {
    let score = 0, x = curClick[0], y = curClick[1],
        curBall = field[y][x], start, end;
    // check horizontal
    let totalBallHorizontal = 0, nX = x;
    while (nX > -1 && field[y][nX] == curBall){
        totalBallHorizontal++;
        nX--;
    }
    start = [nX+1, y];
    nX = x+1;
    while (nX < field.length && field[y][nX] == curBall){
        totalBallHorizontal++;
        nX++;
    }
    end = [nX-1, y]

    return {start, end, score: totalBallHorizontal};
}

getScoreOnVertical = (curClick, field) => {
    let score = 0, x = curClick[0], y = curClick[1],
        curBall = field[y][x], start, end;
    // check vertical 
    let totalBallVertical = 0, nY = y;

    while (nY > -1 && field[nY][x] == curBall){
        totalBallVertical++;
        nY--;
    }
    start = [x, nY+1]
    nY = y+1;
    while (nY < field.length && field[nY][x] == curBall){
        totalBallVertical++;
        nY++;
    }
    end = [x, nY-1];

    return {start, end, score: totalBallVertical};
}

getScoreOnMainDiagonal = (curClick, field) => {
    let score = 0, x = curClick[0], y = curClick[1],
        curBall = field[y][x], start, end, nX, nY;
    // main diagonal
    let totalDiagonalRight = 0;
    nX = x;
    nY = y;
    while (nX > -1 && nY > -1 && field[nY][nX] == curBall){
        nX--;
        nY--;
        totalDiagonalRight++;
    }
    start = [nX+1, nY+1]
    nX = x + 1;
    nY = y + 1;
    while (nX < field.length && nY < field.length && field[nY][nX] == curBall){
        totalDiagonalRight++;
        nX++;
        nY++;
    }
    end = [nX-1, nY-1];
    return {start, end, score: totalDiagonalRight};
}   

getScoreOnFillerDiagonal = (curClick, field) => {
    let score = 0, x = curClick[0], y = curClick[1], 
        curBall = field[y][x],
        start, end, nX, nY;
    // left diagonal(right corner to bottom left corner)
    let totalDiagonalLeft = 0;
    nX = x;
    nY = y;
    while (nX > -1 && nY < field.length && field[nY][nX] == curBall){
        totalDiagonalLeft++;
        nY++;
        nX--;
    }
    end = [nX+1, nY-1];
    nX = x + 1;
    nY = y - 1;
    while (nX < field.length && nY > -1 && field[nY][nX] == curBall){
        totalDiagonalLeft++;
        nY--;
        nX++;
    }
    start = [nX-1, nY+1];

    return {start, end, score: totalDiagonalLeft};
}

disappearBallsAfterSuccess = (curClick, field) => {
    let score = 0, x = curClick[0], y = curClick[1],
        curBall = field[y][x];

    let res = getScoreOnHorizontal(curClick, field);
    if(res.score > 4){
        score += res.score + 1
        field = deleteBall(res.start, res.end, "horizontal", field)
        field[y][x] = curBall
    }

    res = getScoreOnVertical(curClick, field);
    if(res.score > 4){
        score += res.score + 1
        field = deleteBall(res.start, res.end, "vertical", field)
        field[y][x] = curBall
    }

    res = getScoreOnMainDiagonal(curClick, field);
    if(res.score > 4){
        score += res.score + 1
        field = deleteBall(res.start, res.end, "main-diagonal", field)
        field[y][x] = curBall
    }

    res = getScoreOnFillerDiagonal(curClick, field);
    if(res.score > 4){
        score += res.score + 1
        field = deleteBall(res.start, res.end, "filler-diagonal", field)
    }
    if(score > 0)
        field[y][x] = "."
    score = score > 0 ? score - 1 : score;
    return {score, field};
}


disappearBallsAfterSuccessList = (curListAddBall, field) => {
   let listDeleteBall = [], score = 0;
   let preventAddScoreDuplicate = {};
   for(c of curListAddBall){
        let x = c[0], y = c[1], curBall = field[y][x];

        if(isEmptyField(curBall))
            continue; 
        let res = getScoreOnHorizontal([x, y], field);
        let key = `${res.start[0]}${res.start[1]}${res.end[0]}${res.end[1]}`;
        if(res.score > 4 && !preventAddScoreDuplicate[key]){
            score += res.score + 1;
            listDeleteBall.push({start: res.start, end: res.end, type: "horizontal"})
        }
        preventAddScoreDuplicate[key] = 1
       
        res = getScoreOnVertical([x, y], field);
        key = `${res.start[0]}${res.start[1]}${res.end[0]}${res.end[1]}`;
        if(res.score > 4 && !preventAddScoreDuplicate[key]){
            score += res.score + 1;
            listDeleteBall.push({start: res.start, end: res.end, type: "vertical"})
        }
        preventAddScoreDuplicate[key] = 1;

        res = getScoreOnMainDiagonal([x, y], field);
        key = `${res.start[0]}${res.start[1]}${res.end[0]}${res.end[1]}`;
        if(res.score > 4 && !preventAddScoreDuplicate[key]){
            score += res.score + 1;
            listDeleteBall.push({start: res.start, end: res.end, type: "main-diagonal"})
        }
        preventAddScoreDuplicate[key] = 1;

        res = getScoreOnFillerDiagonal([x, y], field);
        key = `${res.start[0]}${res.start[1]}${res.end[0]}${res.end[1]}`;
        if(res.score > 4 && !preventAddScoreDuplicate[key]){
            score += res.score + 1;
            listDeleteBall.push({start: res.start, end: res.end, type: "filler-diagonal"})
        }
        preventAddScoreDuplicate[key] = 1
   }
   for(const list of listDeleteBall){
        let key = `${list.start[0]}${list.start[1]}${list.end[0]}${list.end[1]}`;
        if(!preventAddScoreDuplicate[key]){
            field = deleteBall(list.start, list.end, list.type, field)
        }
   }
   score = score > 0 ? score - 1 : score;
   return {score, field};
}


//using BFS to solve this check
checkHasClearPath = (currClick, desClick, field) => {
    let isVisit = {},
        queue = [currClick]
    isVisit[`${currClick[0]}${currClick[1]}`] = 1

    while (queue.length) {
        let clickC = queue.shift()
        for (const coord of baseDirection) {
            let nX = clickC[0] + coord[0],
                nY = clickC[1] + coord[1];
            if (desClick[0] == nX && desClick[1] == nY)
                return true;
            if (isInsideField([field[0].length, field.length], [nX, nY]) &&
                isEmptyField(field[nY][nX]) &&
                !isVisit[`${nX}${nY}`]) {
                queue.push([nX, nY])
                isVisit[`${nX}${nY}`] = 1
            }
        }
    }

    return false;
}


appearBallAfterUnsuccessMove = (field, newB, newBallsC, currPos) => {
    let score = 0, listCoordBallsAdd = [];

    for (let index = currPos; index < currPos + 3 && index < newBallsC.length; index++){
        field[newBallsC[index][0]][newBallsC[index][1]] = newB[index]
    }
    let fieldTmp = field.map(function(arr) {
        return arr.slice();
    });
    // the ball fill to matrix at the same time
    for (let index = currPos; index < currPos + 3 && index < newBallsC.length; index++){
        let x = newBallsC[index][0],
            y = newBallsC[index][1];
        if(!isEmptyField(field[x][y])){
            listCoordBallsAdd.push([y, x]);
        }
    }

    let res = disappearBallsAfterSuccessList(listCoordBallsAdd, fieldTmp);

    return {score: res.score, field: res.field};
}

moveBall = (field, currClick, desClick) => {
    field[desClick[1]][desClick[0]] = field[currClick[1]][currClick[0]];
    field[currClick[1]][currClick[0]] = '.';
    return field;
}

// game url: https://icolorlines.com/play-icolorlines/
// example video: https://www.youtube.com/watch?v=8GI4A-FGZKE
linesGame = (field, clicks, newBalls, newBallsCoordinates) => {
    let curClick = null, score = 0, currPos = 0;
    for(let pos = 0; pos < clicks.length; pos++){
        let x = clicks[pos][0],
            y = clicks[pos][1]
        //do somthing here
        if(curClick && isEmptyField(field[x][y])){
            let cX = curClick[0],
                cY = curClick[1];
            //check valid clear path
            if(checkHasClearPath([cY, cX], [y, x], field)){
                field = moveBall(field, [cY, cX], [y, x]);
                let changeState = disappearBallsAfterSuccess([y, x], field);
                score += changeState.score;
                field = changeState.field;
                if(changeState.score == 0){
                    let afterChange = appearBallAfterUnsuccessMove(field, newBalls, newBallsCoordinates, currPos);
                    score += afterChange.score;
                    field = afterChange.field;
                    currPos += 3;
                }
                curClick = null;
                continue;
            }
        }
        if(!isEmptyField(field[x][y])){
            curClick = clicks[pos]
        }
    }

    return score;
}