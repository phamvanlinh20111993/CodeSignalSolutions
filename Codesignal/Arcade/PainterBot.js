/**
 *
 */
painterBot = (canvas, operations, d) => {
    let isVisited = new Array(canvas.length).fill([])
    isVisited = isVisited.map(v => new Array(canvas[0].length).fill(false))

    let direction = [[-1, 0], [1, 0], [0, -1], [0, 1]]

    isInCanvasRange = ([x, y]) => x < canvas.length && x >= 0
                                && y < canvas[0].length && y >= 0

    fillPainCanvas = ([x, y, color], initOne) => {
        isVisited[x][y] = true
        if(Math.abs(canvas[x][y] - initOne) <= d){
            canvas[x][y] = color
        }

        for(const direct of direction){
            const nextX = direct[0] + x
            const nextY = direct[1] + y

            if(isInCanvasRange([nextX, nextY])
               && !isVisited[nextX][nextY]
               && Math.abs(canvas[nextX][nextY] - initOne) <= d){
                fillPainCanvas([nextX, nextY, color], initOne)
            }
        }
    }

    operations.map(floodFill => {
        isVisited = new Array(canvas.length).fill([])
        isVisited = isVisited.map(v => new Array(canvas[0].length).fill(false))
        fillPainCanvas(floodFill, canvas[floodFill[0]][floodFill[1]])
    })

    return canvas

}
