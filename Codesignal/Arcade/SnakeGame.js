/**
 * 
 */



outOfGameBoard = ([gameBoardX, gameBoardY], [x, y]) => !(x > -1 && x < gameBoardX && y > -1 && y < gameBoardY)

isSnakeEatItsTail = snakesC => snakesC.findIndex((e, i) => i > 0 && snakesC[0].x == e.x 
                                                      && snakesC[0].y == e.y) > 0

nextDirection = (snakesC, command) => {
    let mapHeadDirectLeft = {">": "^", "<": "v", "^" : "<", "v" : ">"},
        mapHeadDirectRight = {">": "v", "<" : "^", "^" : ">", "v": "<"};

    moveSnake = snakesC => {
        let xN = snakesC[0].x,
            yN = snakesC[0].y
        for(let index = 1; index < snakesC.length; index++){
            let x = snakesC[index].x, 
                y = snakesC[index].y;
            snakesC[index] = {x: xN, y: yN}
            xN = x
            yN = y
        }
            
        return snakesC
    }

    if(command == "L"){
        snakesC[0].direct = mapHeadDirectLeft[snakesC[0].direct]
    }else if(command == "R"){
        snakesC[0].direct = mapHeadDirectRight[snakesC[0].direct]
    }else {
        snakesC = moveSnake(snakesC)
        if(snakesC[0].direct == ">")
            snakesC[0].x = snakesC[0].x + 1;
        if(snakesC[0].direct == "<")
            snakesC[0].x = snakesC[0].x - 1;
        if(snakesC[0].direct == "v")
            snakesC[0].y = snakesC[0].y + 1;
        if(snakesC[0].direct == "^")
            snakesC[0].y = snakesC[0].y - 1;
    }
    return snakesC;
}

mapStateSnakeToGameBoard = (snakesC, gameBoard) => {
    snakesC.map(e => gameBoard[e.y][e.x] = (snakesC[0].isEnd ? "X" : e.direct || "*"))
    return gameBoard;
}

extractSnakeFromGameBoard = (head, gameBoard) => {
    let snakesC = [{...head}],
        x = head.x,
        y = head.y,
        key = `${x}${y}`,
        markSnakesC = {};
    markSnakesC[key] = 1
    gameBoard[y][x] = "."
    //get first tail
    if(head.direct == ">"){
        x -= 1
        while(x > -1 && gameBoard[y][x] == '*'){
            gameBoard[y][x] = "."
            snakesC.push({x, y})
            markSnakesC[`${x}${y}`] = 1
            x--
        }
    }else if(head.direct == "v"){
         y -= 1
        while(y > -1 && gameBoard[y][x] == '*') {
            gameBoard[y][x] = "."
            snakesC.push({x, y})
            markSnakesC[`${x}${y}`] = 1
            y--
        }
    }else if(head.direct == "<"){
        x += 1
        while(x < gameBoard[0].length && gameBoard[y][x] == '*'){
            gameBoard[y][x] = "."
            snakesC.push({x,y})
            markSnakesC[`${x}${y}`] = 1
            x++
        }
    }else{
        y += 1
        while(y < gameBoard.length && gameBoard[y][x] == '*') {
            gameBoard[y][x] = "."
            snakesC.push({x, y})
            markSnakesC[`${x}${y}`] = 1
            y++
        }
    }

    let instruction = [[0, -1], [0, 1], [1, 0], [-1, 0]], isExitTail = true

    while(isExitTail){
        let x = snakesC[snakesC.length-1].x,
            y = snakesC[snakesC.length-1].y;
        isExitTail = false;
        instruction.map(e => {
            let nX = e[0] + x, 
                nY = e[1] + y;
            if(!markSnakesC[`${nX}${nY}`] 
               && !outOfGameBoard([gameBoard[0].length, gameBoard.length], [nX, nY])
               && gameBoard[nY][nX] == "*"){
                gameBoard[nY][nX] = "."
                snakesC.push({x: nX, y: nY});
                markSnakesC[`${nX}${nY}`] = 1;
                isExitTail = true;
            }
        })
    }

    return snakesC;
}

function snakeGame(gameBoard, commands) {
    let head = {}
    gameBoard.map((row, y) => row.map((val, x) => !'.*'.includes(val) 
                                                 && (head = {x, y, direct: val})));
    let snakesC = extractSnakeFromGameBoard(head, gameBoard);     
    let ind = 0

    for(const c of commands){
        let sBefore = new Array(snakesC.length);
        sBefore = snakesC.map(v => {return {...v}});

        snakesC = nextDirection(snakesC, c); 
        let isEnd = outOfGameBoard([gameBoard[0].length, gameBoard.length], [snakesC[0].x, snakesC[0].y]) 
                    || isSnakeEatItsTail(snakesC);
        if(!isEnd){
            // clear last position
            gameBoard[sBefore[sBefore.length-1].y][sBefore[sBefore.length-1].x] = ".";
            snakesC[0] = {...snakesC[0], isEnd};
        }else{
            snakesC = sBefore
            snakesC[0] = {...sBefore[0], isEnd};
            break;
        }
    }

    return  mapStateSnakeToGameBoard(snakesC, gameBoard);          
}
