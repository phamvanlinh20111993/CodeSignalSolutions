/**
 * 
 */

const kingDirections = [
        [1, 0],
        [1, -1],
        [1, 1],
        [-1, 1],
        [-1, 0],
        [-1, -1],
        [0, 1],
        [0, -1]
    ],
    knightDirections = [
        [-1, 2],
        [-2, 1],
        [-2, -1],
        [-1, -2],
        [1, -2],
        [2, -1],
        [2, 1],
        [1, 2]
    ],
    convertCharToNum = {
        'a': 1,
        'b': 2,
        'c': 3,
        'd': 4,
        'e': 5,
        'f': 6,
        'g': 7,
        'h': 8
    },
    kingValOnChess = 'k',
    amazonValOnChess = 'a',
    kingValOcupiedOnChess = 't',
    amazonValOcupiedOnChess = 'o',
    freeVal = '.';

checkValidCoord = ([x, y]) => x > -1 && y > -1 && x < 8 && y < 8;

checkValidCurrentCoord = (chessBoard, [x, y]) => checkValidCoord([x, y]) &&
    (chessBoard[x][y] == freeVal ||
        chessBoard[x][y] == amazonValOnChess)

// black's king is under the amazon's attack and it cannot make a valid move
getCheckmate = chessBoard => {
    let count = 0
    chessBoard.map((row, x) => {
        row.map((value, y) => {
            if (value == amazonValOcupiedOnChess) {
                let flag = true
                for (const [cX, cY] of kingDirections) {
                    if (checkValidCurrentCoord(chessBoard, [x + cX, y + cY])) {
                        flag = false
                        break;
                    }
                }
                count += flag ? 1 : 0
            }
        })
    })

    return count;
}

//black's king is under the amazon's attack but it can reach a safe square in one move
getCheck = chessBoard => {
    let count = 0
    chessBoard.map((row, x) => {
        row.map((value, y) => {
            if (value == amazonValOcupiedOnChess) {
                let f = 0
                for (const [cX, cY] of kingDirections)
                    f += checkValidCurrentCoord(chessBoard, [x + cX, y + cY])
                count += f > 0 ? 1 : 0
            }
        })
    })
    return count
}

// black's king is on a safe square but it cannot make a valid move
getStalemate = chessBoard => {
    let count = 0
    chessBoard.map((row, x) => {
        row.map((value, y) => {
            //suppose this is coordinates of black king
            if (value == freeVal) {
                let flag = true
                for (const [cX, cY] of kingDirections) {
                    if (checkValidCurrentCoord(chessBoard, [x + cX, y + cY])) {
                        flag = false
                        break
                    }
                }
                count += flag ? 1 : 0
            }
        })
    })
    return count
}

//black's king is on a safe square and it can make a valid move.
getSafePosition = chessBoard => {
    let count = 0
    chessBoard.map((row, x) => {
        row.map((value, y) => {
            //suppose this is coordinates of black king
            if (value == freeVal) {
                let f = 0
                for (const [cX, cY] of kingDirections) {
                    if (checkValidCurrentCoord(chessBoard, [x + cX, y + cY])) {
                        f++
                        break
                    }
                }
                count += f > 0 ? 1 : 0
            }
        })
    })
    return count
}

getChessBoardStatus = (chessBoard, [kX, kY], [aX, aY]) => {
    chessBoard[kX][kY] = kingValOnChess
    chessBoard[aX][aY] = amazonValOnChess

    let chessBoardSttWithKing = (chessBoard, [kX, kY]) => {
        kingDirections.map(([x, y]) => {
            if (checkValidCoord([kX + x, kY + y]))
                chessBoard[kX + x][kY + y] = kingValOcupiedOnChess
        })
        return chessBoard;
    }

    let chessBoardSttWithAmazon = (chessBoard, [aX, aY]) => {
        chessBoard.map((row, x) => {
            // on horizontal and vertical
            if (x != aX) {
                if (chessBoard[x][aY] == freeVal)
                    chessBoard[x][aY] = amazonValOcupiedOnChess
            } else {
                row.map((v, y) => {
                    if (y != aY && chessBoard[aX][y] == freeVal)
                        chessBoard[aX][y] = amazonValOcupiedOnChess
                })
            }
            // two diagonal
            row.map((v, y) => {
                if (Math.abs(x - aX) == Math.abs(y - aY) && x != aX && y != aY) {
                    if (chessBoard[x][y] == freeVal)
                        chessBoard[x][y] = amazonValOcupiedOnChess
                }
            })
        })

        knightDirections.map(([x, y]) => {
            if (checkValidCoord([aX + x, aY + y]) && chessBoard[aX + x][aY + y] == freeVal)
                chessBoard[aX + x][aY + y] = amazonValOcupiedOnChess
        })

        return chessBoard;
    }

    let chessBoardSttWithKingAndAmazon = (chessBoard, [kX, kY], [aX, aY])=>{
        if(kX == aX){
            if(kY > aY){
                let p = kY + 1
                while(p < 8){
                    if(chessBoard[aX][p] == amazonValOcupiedOnChess)
                        chessBoard[aX][p] = freeVal
                    p++
                }
            }else {
                let p = kY - 1
                while(p >= 0){
                    if(chessBoard[aX][p] == amazonValOcupiedOnChess)
                        chessBoard[aX][p] = freeVal
                    p--
                }
            }

        }else if(kY == aY){
            if(aX > kX){
                let p = kX - 1
                while(p >= 0){
                    if(chessBoard[p][aY] == amazonValOcupiedOnChess)
                        chessBoard[p][aY] = freeVal
                    p--
                }
            }else{
                let p = kX + 1
                while(p < 8){
                    if(chessBoard[p][aY] == amazonValOcupiedOnChess)
                        chessBoard[p][aY] = freeVal
                    p++
                }
            }
        }else if(Math.abs(kX - aX) == Math.abs(kY - aY)){
            if(kX - aX == kY - aY){
                if(kX - aX > 0){
                    let p = kX+1, pN = kY+1
                    while(p < 8 && pN < 8){
                        if(chessBoard[p][pN] == amazonValOcupiedOnChess)
                            chessBoard[p][pN] = freeVal
                        p++
                        pN++
                    }
                }else{
                    let p = kX-1, pN = kY-1
                    while(p >= 0 && pN >= 0){
                        if(chessBoard[p][pN] == amazonValOcupiedOnChess)
                            chessBoard[p][pN] = freeVal
                        p--
                        pN--
                    }
                }
            }else{
                if(kX < aX){
                    let p = kX - 1, pN = kY+1;
                    while(p >= 0 && pN < 8){
                        if(chessBoard[p][pN] == amazonValOcupiedOnChess)
                            chessBoard[p][pN] = freeVal
                        pN++
                        p--
                    }
                }else{
                    let p = kX + 1, pN = kY-1;
                    while(p < 8 && pN >= 0){
                        if(chessBoard[p][pN] == amazonValOcupiedOnChess)
                            chessBoard[p][pN] = freeVal
                        pN--
                        p++
                    }
                }
            }
        }

        return chessBoard
    }

    chessBoard = chessBoardSttWithKing(chessBoard, [kX, kY]);
    chessBoard = chessBoardSttWithAmazon(chessBoard, [aX, aY]);
    chessBoard = chessBoardSttWithKingAndAmazon(chessBoard, [kX, kY], [aX, aY]);
    return chessBoard;
}

show = chessBoard => {
    chessBoard = chessBoard.map(v => v.join(' '))
    console.log(chessBoard)
}

amazonCheckmate = (king, amazon) => {
    //init chess board
    let chessBoard = new Array(8).fill([])
    chessBoard = chessBoard.map(v => new Array(8).fill(freeVal))

    //convert to position in array
    king = [8 - parseInt(king.split('')[1]), convertCharToNum[king.split('')[0]] - 1]
    amazon = [8 - parseInt(amazon.split('')[1]), convertCharToNum[amazon.split('')[0]] - 1]

    chessBoard = getChessBoardStatus(chessBoard, [king[0], king[1]], [amazon[0], amazon[1]]);

    show(chessBoard)
    return [
        getCheckmate(chessBoard),
        getCheck(chessBoard),
        getStalemate(chessBoard),
        getSafePosition(chessBoard),
    ];
}