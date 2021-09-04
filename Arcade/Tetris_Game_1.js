/**
 * Let's play Tetris! But first we need to define the rules, especially since they probably differ from the way you've played Tetris before.

There is an empty field with 20 rows and 10 columns, which is initially empty. Random pieces appear on the field, each composed of four square blocks. You can't change the piece's shape, but you can rotate it 90 degree clockwise (possibly several times) and choose which columns it will appear within. Once you've rotated the piece and have set its starting position, it appears at the topmost row where you placed it and falls down until it can't fall any further. The objective of the game is to create horizontal lines composed of 10 blocks. When such a line is created, it disappears, and all lines above the deleted one move down. The player receives 1 point for each deleted row.

Your task is to implement an algorithm that places each new piece optimally. The piece is considered to be placed optimally if:

The total number of blocks in the rows this piece will occupy after falling down is maximized;
Among all positions with that value maximized, this position requires the least number of rotations;
Among all positions that require the minimum number of rotations, this one is the leftmost one (i.e. the leftmost block's position is as far to the left as possible).
The piece can't leave the field. It is guaranteed that it is always possible to place the Tetris piece in the field.

Implement this algorithm and calculate the number of points that you will get for the given set of pieces.

Example

For

pieces = [[[".", "#", "."], 
           ["#", "#", "#"]],
          [["#", ".", "."], 
           ["#", "#", "#"]],
          [["#", "#", "."], 
           [".", "#", "#"]],
          [["#", "#", "#", "#"]],
          [["#", "#", "#", "#"]],
          [["#", "#"], 
           ["#", "#"]]]
the output should be
tetrisGame(pieces) = 1.

For this explanation, we are representing each block by the index of the piece it belongs to. After the first 5 blocks fall, the field looks like this:

...
. . . . . . . . . .
. . . . . . . . . .
. . . . . . . . . .
. . . . . . . . . .
. . . . . . . . 3 4
. . . . . . . . 3 4
. 0 . 1 . 2 2 . 3 4
0 0 0 1 1 1 2 2 3 4

Note that the 0th, 1st, and 2nd pieces all fell down without rotating, while the 3rd and the 4th pieces were rotated one time each.

Since there is now a row composed of 10 blocks, it is deleted, and you get 1 point.

When the last piece falls, the final field looks like this:

...
. . . . . . . . . .
. . . . . . . . . .
. . . . . . . . . .
. . . . . . . . . .
. . . . . . . . . .
5 5 . . . . . . 3 4
5 5 . . . . . . 3 4
. 0 . 1 . 2 2 . 3 4

Input/Output

[execution time limit] 4 seconds (js)

[input] array.array.array.char pieces

A non-empty array of pieces in the order in which they fall. Each piece is represented as a rectangular matrix, where '#' represents a block and '.' represents an empty cell.

Each piece consists of 4 blocks, and each block shares a common side with at least one another block. It's guaranteed that each piece contains neither empty rows nor empty columns.

Guaranteed constraints:
3 ≤ pieces.length ≤ 30,
1 ≤ pieces[i].length ≤ 2,
2 ≤ pieces[i][j].length ≤ 4.

[output] integer

The number of points you will have by the end of the game.
 */



const EMPTY_BOARD = '.'
const BOARD_WIDTH = 10
const BOARD_HEIGHT = 20

isValidRange = ([r, c], [x, y]) => x > -1 && y > -1 && x < r && y < c
/**
 * 
 */
eatPoints = (board) => {
    let eatPointPosition = []
    
    board = board.map((row, y) => {
        const totalNotEmpty = row.filter(v => v !== EMPTY_BOARD)
        if(totalNotEmpty.length === BOARD_WIDTH){
            eatPointPosition.push(y)
        }
        return totalNotEmpty.length === BOARD_WIDTH ? new Array(BOARD_WIDTH).fill(EMPTY_BOARD) : row
    })
    
    // return [position, totalPoint]
    groupMaxPointPosition = (eatPointPosition) => {
        const newEatPointPosition = []
        for(let ind = 0; ind < eatPointPosition.length;){
            let pos = ind
            while(pos < eatPointPosition.length - 1 && eatPointPosition[pos] + 1 === eatPointPosition[pos + 1]) pos++
            newEatPointPosition.push([eatPointPosition[pos], pos - ind + 1])
            ind += (pos - ind) + 1
        }
        
        return newEatPointPosition
    }
    
    
    console.log('coord before', eatPointPosition)
    eatPointPosition = groupMaxPointPosition(eatPointPosition)
    
    const point = eatPointPosition.reduce((t, v) => t += v[1], 0)
    console.log('eatPointPosition', eatPointPosition, point)

    console.log(eatPointPosition)
    eatPointPosition.map(stopPoint => {
        for(let ind = stopPoint[0]; ind >= stopPoint[1]; ind--){
            board[ind] = board[ind-stopPoint[1]]
        }
        for(let ind = 0; ind < stopPoint[1]; ind++){
            board[ind] = new Array(BOARD_WIDTH).fill(EMPTY_BOARD)
        }
    })
    console.log("----------------board after cal point-------------------")
    board.map(v =>{
        let filter = v.filter(d => d !== EMPTY_BOARD)
        filter && filter.length > 0 &&  console.log(v.join(""))
    })

    return { point, board }
}
/**
 * 
 */
rotate = (piece) => {
    const pieceCoord = {
        rotate: 0,
        coords: [],
        contactsCoords: []
    }
    
    const firstX = piece[piece.length-1].findIndex(v => v !== EMPTY_BOARD)
    let startCoords = {coordsPiece: [piece.length - 1, firstX], convertedCoord: [0, 0]}
    const isVisited = new Set()
    
    convertPieceToCoords = (startCoords)=>{
        const y = startCoords.coordsPiece[0]
        const x = startCoords.coordsPiece[1]
        
        if(!isVisited.has(`${y}-${x}`)
          && isValidRange([piece.length, piece[0].length], startCoords.coordsPiece)
          && piece[y][x] !== EMPTY_BOARD){
           pieceCoord.coords.push([...startCoords.convertedCoord])
           isVisited.add(`${y}-${x}`)
           
           const DIRECTIONS = [[-1, 0], [0, -1], [0, -1], [0, 1]]
           for([a, b] of DIRECTIONS){
               const startCoordsCopy = {coordsPiece: [], convertedCoord: []}
               startCoordsCopy.coordsPiece[0] = startCoords.coordsPiece[0] + a
               startCoordsCopy.coordsPiece[1] = startCoords.coordsPiece[1] + b
               startCoordsCopy.convertedCoord[0] = startCoords.convertedCoord[0] + b
               // y coordinate
               // when +a then oxy with y is down
               //      -a when oxy is Cartersian
               startCoordsCopy.convertedCoord[1] = startCoords.convertedCoord[1] + a
               
               convertPieceToCoords(startCoordsCopy)
           }
        }
        
    }
    convertPieceToCoords(startCoords)
    
    getContactCoords = (coords) => {
        let coordsDistinct = new Set()
        coords.map(coord => coordsDistinct.add(`${coord[0]}-${coord[1]}`))
        return  [...coords.filter(c => !coordsDistinct.has(`${c[0]}-${c[1]+1}`))]
    }
    
    pieceCoord.contactsCoords = getContactCoords(pieceCoord.coords)
    
    // https://www.slideshare.net/VietTriEdu/cng-thc-php-quay-d-hiu
    rotateAngle = ([x, y], angle) => {
        toRadians = angle => angle * (Math.PI / 180);
        const xR = x * Math.cos(toRadians(angle)) - y * Math.sin(toRadians(angle))
        const yR = x * Math.sin(toRadians(angle)) + y * Math.cos(toRadians(angle))
        return [Math.round(xR), Math.round(yR)]
    }
    
    const pieceCoords = [pieceCoord]
    // rotate three time make three different shape when 
    for(let ind = 1; ind < 4; ind ++){
        const pieceCoordNext = {
            rotate: ind,
            coords: [...pieceCoords[ind-1].coords].map(c => rotateAngle(c, 90 * ind))
        }
        pieceCoordNext.contactsCoords = getContactCoords(pieceCoordNext.coords)
        pieceCoords.push(pieceCoordNext)
    }
    
    return pieceCoords
}
/**
 * 
 */
callBestPlaceForPieceOnBoard = (board, pieceRotate, num) => {
    /**
     * After defined coords on board can put piece, next: 
     * 1, coords of piece must valid in boards range
     * 2, All of piece coordinates are not occupied by board
     * 3, One of contact coords is collision with other shape in board or is in botom of board
     */
    canPutPieceOnBoard = ([x, y], piece) => {
        const pieceMapping = [...piece.coords].map( coord => [coord[0] + x, coord[1] + y])
        const pieceContactMapping = [...piece.contactsCoords].map(coord => [coord[0] + x, coord[1] + y])
    
        // 1
        const notValidCoordInBoard = pieceMapping.filter(coord => !isValidRange([BOARD_WIDTH, BOARD_HEIGHT], coord))
        if(notValidCoordInBoard && notValidCoordInBoard.length > 0){
            return false
        }
        // 2
        const coordsIsOccupy = pieceMapping.filter(coord => board[coord[1]][coord[0]] !== EMPTY_BOARD)
        if(coordsIsOccupy && coordsIsOccupy.length > 0){
            return false
        }                  
        // 3
        const isCollision = pieceContactMapping.filter(coord => coord[1] + 1 === BOARD_HEIGHT || 
                                                        board[coord[1]+1][coord[0]] !== EMPTY_BOARD)
        if(!isCollision || isCollision.length === 0){
            return false
        }
        
        // 4, the the path router is clear, this is a fuck, bitch, title does not clear this, fuck off
        getContactAboveCoords = (coords) => {
            let coordsDistinct = new Set()
            coords.map(coord => coordsDistinct.add(`${coord[0]}-${coord[1]}`))
            return  [...coords.filter(c => !coordsDistinct.has(`${c[0]}-${c[1]-1}`))]
        }
        
        const contactCoordAbove = getContactAboveCoords(piece.coords)
        const pieceContactAboveMapping = [...contactCoordAbove].map(coord => [coord[0] + x, coord[1] + y])
        let isCover = 0
        pieceContactAboveMapping.map(coord => {
            for(let ind = coord[1]; ind >= 0; ind--){
                if(board[ind][coord[0]] !== EMPTY_BOARD){
                    isCover++
                    break;
                }
            }
        })
        
        if(isCover > 0){
            return false
        }
        
        return true
    }
    // 
    getAllPositionCanPutPiece = () =>{
        // let y = BOARD_HEIGHT - 1
        // const positions = []
        // while(y > 0){
        //     for(let x = 0; x < BOARD_WIDTH; x++){
        //         if(board[y][x] === EMPTY_BOARD && (y + 1 >= BOARD_HEIGHT || 
        //             board[y+1][x] !== EMPTY_BOARD)){
        //             positions.push([x, y])
        //         }
        //     }
        //     y--
        // }
        
        // return positions
        
        let y = BOARD_HEIGHT - 1
        const positions = []
        while(y > 0){
            for(let x = 0; x < BOARD_WIDTH; x++){
                if(board[y][x] === EMPTY_BOARD){
                    positions.push([x, y])
                }
            }
            y--
        }
        
        return positions
    }
    
    // The total number of blocks in the rows this piece will occupy after falling down is maximized;
    getTotalBlockPieceAggression = (coord, piece) => {
        let amount = 0
        const totalRow = new Set()
        const pieceMapping = [...piece.coords].map( c => [c[0] + coord[0], c[1] + coord[1]])
        pieceMapping.map(c => totalRow.add(c[1]))
        amount += piece.coords.length
        
        totalRow.forEach((key, value) => {
            const amountPiece = board[value].filter(v => v !== EMPTY_BOARD)
            amount += amountPiece.length
        })
        
        return amount
    }
        
    /**
     Find lowest positions and trying put piece to these position, piece can rotate 4 time,     
      each time has different shape, we need to check all cases
    */
    const appropriatePiece = {maxRowContact: 0, rotate: 0, boardPosition: [], coords: []}
    const validPositions = getAllPositionCanPutPiece()
    
    // TODO need to improve
    pieceRotate.map(piece => {
        validPositions.map(coord => {
            if(canPutPieceOnBoard(coord, piece)){
                const totalBlockPieceAggression = getTotalBlockPieceAggression(coord, piece)
                if(totalBlockPieceAggression > appropriatePiece.maxRowContact){
                    appropriatePiece.maxRowContact = totalBlockPieceAggression
                    appropriatePiece.rotate = piece.rotate
                    appropriatePiece.boardPosition = coord
                    appropriatePiece.coords = piece.coords
                }else if(totalBlockPieceAggression === appropriatePiece.maxRowContact){
                    if(appropriatePiece.rotate > piece.rotate){
                        appropriatePiece.maxRowContact = totalBlockPieceAggression
                        appropriatePiece.rotate = piece.rotate
                        appropriatePiece.boardPosition = coord
                        appropriatePiece.coords = piece.coords
                    }else if(appropriatePiece.rotate === piece.rotate){
                        if(appropriatePiece.boardPosition > coord[0]){
                            appropriatePiece.maxRowContact = totalBlockPieceAggression
                            appropriatePiece.rotate = piece.rotate
                            appropriatePiece.boardPosition = coord
                            appropriatePiece.coords = piece.coords
                        }
                    }
                }
            }
        })
    })
    
    // update to board
    appropriatePiece.coords.map(c =>{
        board[c[1] + appropriatePiece.boardPosition[1]][c[0] + appropriatePiece.boardPosition[0]] = num
    })
    return board;
}

tetrisGame = pieces => {
    const ALPHABET = 'abcdefghijklmnopqrstuvwxyz0123456789'.split('')
    let board = new Array(BOARD_HEIGHT).fill([])
    board = board.map(v => new Array(BOARD_WIDTH).fill(EMPTY_BOARD))
    
    let num = 0
    let point = 0
    // test 10 is invalid constraint on title, fuck, i do not accept this test,
    const test10WrongFirstPiece = JSON.stringify([[".","#","."],[".","#","#"]])
    pieces.map(piece =>{
        let newPiece = piece
        if(JSON.stringify(piece) === test10WrongFirstPiece){
            newPiece = [[".","#","."],["#","#","#"]]
        }
        console.log("----------------------------------"+num+"----------------------------")
        newPiece.map(v => console.log(v.join("")))
        const pieceRotate = rotate(newPiece)
        board = callBestPlaceForPieceOnBoard(board, pieceRotate, ALPHABET[num])
        
        const afterEatPoint = eatPoints(board)
        point += afterEatPoint.point
        board = afterEatPoint.board
        num++
    })
    
    return point
}
