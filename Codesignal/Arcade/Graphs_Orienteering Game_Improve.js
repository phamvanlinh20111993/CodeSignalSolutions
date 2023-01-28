/**
 * 
 */
solution = board => {
    
    if (board[0].length == 1) return 0
    
    let storageMinPath = new Array(board.length).fill(new Array())
    storageMinPath = storageMinPath.map(v => new Array(board[0].length).fill(1000000))
    //initial
    storageMinPath[0][0] = board[0][0]
    storageMinPath[0][1] = board[0][0] + board[0][1]
    storageMinPath[1][0] = board[0][0] + board[1][0]
    storageMinPath[1][1] = board[1][1] + Math.min(storageMinPath[0][1], storageMinPath[1][0])
    
    let directs = [
        [0, -1],
        [1, 0],
        [-1, 0],
        [0, 1]
    ]

    const isInMatrix = ([x, y], range = 100000) => x >= 0 && y >= 0 
                    && x <= Math.min(range, board.length - 1)
                    && y <= Math.min(range, board[0].length - 1)
                    
    const checkFlawsPath = ([x, y], storage = storageMinPath) => {
        let minVal = storage[x][y] - board[x][y]
        directs.map(direct => {
            const xC = x + direct[0]
            const yC = y + direct[1]
            if (isInMatrix([xC, yC])) {
                minVal = minVal > storage[xC][yC] ? storage[xC][yC] : minVal
            }
        })
        
        if (minVal != storage[x][y] - board[x][y]) {
            storage[x][y] = board[x][y] + minVal
            directs.map(direct => {
                const xC = x + direct[0]
                const yC = y + direct[1]
                if (isInMatrix([xC, yC])) {
                    checkFlawsPath([xC, yC], storageMinPath)
                }
            })
        } else {
            return
        }
    }
    
    for (let i = 0; i < storageMinPath.length; i++) {
        for (j = 0; j < storageMinPath[0].length; j++) {
            checkFlawsPath([i, j], storageMinPath)
        }
    }
    
    return storageMinPath[board.length - 1][board[0].length - 1] - board[board.length - 1][board[0].length - 1]
}