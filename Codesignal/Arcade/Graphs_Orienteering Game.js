/**
 * Link:
 * https://app.codesignal.com/arcade/graphs-arcade/orienteering/gDRv9hSBGutrsawEt/solutions?solutionId=bikR9BCoepimnz6nr
 * 
 * 
 * Not long ago you saw an orienteering competition on TV and immediately wanted
 * to try it yourself. You've joined an orienteering club and started preparing
 * for the upcoming competitions.
 * 
 * You've just came home from one particularly exhausting competition and
 * decided to relax by playing a board game which all qualified participants
 * (including you, of course) got as a reward. In this game your objective is to
 * navigate your way on the board from the start located in the upper-left
 * corner to the finish located in the bottom-right corner in as little time as
 * possible.
 * 
 * The game board is a rectangle divided into equal cells. Each cell contains a
 * number denoting the time you should wait in this cell before advancing to the
 * next one. From each cell you can move only to the neighboring one, i.e. the
 * one directly below, above, to the left or to the right of your current
 * position.
 * 
 * Given the game board find the minimum time required to reach the finish from
 * the start.
 * 
 * Example
 * 
 * For
 * 
 * board = [[42, 51, 22, 10, 0 ], [2, 50, 7, 6, 15], [4, 36, 8, 30, 20], [0, 40,
 * 10, 100, 1 ]] the output should be solution(board) = 140.
 * 
 * Example
 * 
 * Input/Output
 * 
 * [execution time limit] 4 seconds (js)
 * 
 * [input] array.array.integer board
 * 
 * The game board of size n × m. The start is located at board[0][0] and the
 * finish is at board[n - 1][m - 1].
 * 
 * Guaranteed constraints: 1 ≤ board.length ≤ 250, 1 ≤ board[i].length ≤ 250, 0 ≤
 * board[i][j] ≤ 100.
 * 
 * [output] integer
 * 
 * The minimum time required to reach the finish from the start.
 */

solution = board => {
    
    if (board[0].length == 1) return 0
    // console.log('origin', board, board[0].length, board.length)
    
    let storageMinPath = new Array(board.length).fill(new Array())
    storageMinPath = storageMinPath.map(v => new Array(board[0].length).fill(-1))
    // initial
    storageMinPath[0][0] = board[0][0]
    storageMinPath[0][1] = board[0][0] + board[0][1]
    storageMinPath[1][0] = board[0][0] + board[1][0]
    storageMinPath[1][1] = board[1][1] + Math.min(storageMinPath[0][1], storageMinPath[1][0])
    
    let storageMinPath1 = JSON.parse(JSON.stringify(storageMinPath))
    
    let directions = [
        [0, -1],
        [1, 0],
        [-1, 0],
        [0, 1]
    ]

    const isInMatrix = ([x, y], range = 100000) => x >= 0 && y >= 0 
                    && x <= Math.min(range, board.length - 1)
                    && y <= Math.min(range, board[0].length - 1)
    let isVisited
    const fillMinPath = ([x, y], range, storage = storageMinPath, directs = directions) => {
        if (storage[x][y] > -1) {
            isVisited.delete(`${x}-${y}`)
            return storage[x][y]
        }
        let minVal = 10000000
        directs.map(direct => {
            const xC = x + direct[0]
            const yC = y + direct[1]
            if (isInMatrix([xC, yC], range) && !isVisited.has(`${xC}-${yC}`)) {
                isVisited.add(`${xC}-${yC}`)
                minVal = Math.min(fillMinPath([xC, yC], range, storage), minVal)
            }
        })
        storage[x][y] = board[x][y] + minVal
        return storage[x][y]
    }
    
    // console.log('directs', directs)
    let maxRange = Math.max(board[0].length, board.length)
    let minRange = Math.min(board[0].length - 1, board.length - 1)
    const findMaxUpperAndMinLower = (storage = storageMinPath) => {
        // a[x][y] = [x, y]
        let maxUpper = [],
            minLower = []
        // max upper
        maxUpper[1] = storage[0].length - 1
        while (maxUpper[1] - 1 > 0 && storage[0][maxUpper[1] - 1] < 0) maxUpper[1]--
        maxUpper[0] = -1
        while (maxUpper[0] + 1 < storage.length &&
            storage[maxUpper[0] + 1][maxUpper[1]] !== -1) maxUpper[0]++
        maxUpper[0] + 1 < storage.length && maxUpper[0]++
        
        // min lower
        minLower[0] = storage.length - 1
        while (minLower[0] - 1 >= 0 && storage[minLower[0] - 1][0] < 0) minLower[0]--
        minLower[1] = -1
        while (minLower[1] + 1 < storage[0].length - 1 &&
            storage[minLower[0]][minLower[1] + 1] > 0) minLower[1]++
        minLower[1] + 1 < storage[0].length && minLower[1]++
        
        return [maxUpper, minLower]
    }
    
    for (let range = 2; range < maxRange; range++) {
        const [maxUpper, minLower] = findMaxUpperAndMinLower(storageMinPath)
        console.log('maxUpper', maxUpper, 'minLower', minLower)
        
        isVisited = new Set()
        fillMinPath(maxUpper, range)
        isVisited = new Set()
        fillMinPath(minLower, range, storageMinPath1)
        // console.log('board')
        // storageMinPath.map(v => console.log(JSON.stringify(v)))
        // console.log('board1')
        // storageMinPath1.map(v => console.log(JSON.stringify(v)))
        storageMinPath = storageMinPath.map((r, i) => {
            return r.map((c, j) => {
                if (c > -1 && storageMinPath1[i][j] > -1) {
                    if (c > storageMinPath1[i][j]) {
                        return storageMinPath1[i][j]
                    } else {
                        storageMinPath1[i][j] = c
                        return c
                    }
                } else {
                     return c
                }
            })
        })
        // console.log('merge')
    // storageMinPath.map(v => console.log(JSON.stringify(v)))
    }
    const checkFlawsPath = ([x, y], storage = storageMinPath, directs = directions) => {
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
                    checkFlawsPath([xC, yC], storageMinPath1, directions)
                }
            })
        } else {
            return
        }
    }
    
    for (let i = 0; i < storageMinPath1.length; i++) {
        for (j = 0; j < storageMinPath1[0].length; j++) {
            checkFlawsPath([i, j], storageMinPath1, directions)
        }
    }
    
    return storageMinPath1[board.length - 1][board[0].length - 1] - board[board.length - 1][board[0].length - 1]
}