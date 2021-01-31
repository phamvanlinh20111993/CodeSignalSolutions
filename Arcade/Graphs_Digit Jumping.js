/**
 *
 */
digitJumping = (grid, start, finish) => {

    if(start[0] == finish[0] && start[1] == finish[1])  return 0

    let isVisited = new Array(grid.length).fill([])
    isVisited = isVisited.map(v => new Array(grid[0].length).fill(-1))
    let direction = [[1, 0], [-1, 0], [0, 1], [0, -1]]
    let storeMinStep = new Array(10).fill(-1)

    isInGridRange = ([a, b]) => a >= 0 && a < grid.length
                                && b >= 0 && b < grid[0].length

    let findMinStep = ([a, b]) => {
        let queue = [{pos: [a, b], step: 0}]
        isVisited[a][b] = 1

        let min = 10000000;

        while(queue.length > 0){
            let currentPos = queue.shift()
            let [x, y] = currentPos.pos
            let step = currentPos.step

            if(x == finish[0] && y == finish[1]){
            	let calStep = step
            	if(storeMinStep[grid[x][y]] > -1){
            		calStep = Math.min(step, storeMinStep[grid[x][y]]) +1
            	}
                min = min > calStep ? calStep : min
            }

            storeMinStep[grid[x][y]] = storeMinStep[grid[x][y]] == -1 ? step
                                       : storeMinStep[grid[x][y]]

            for(const d of direction){
                const dX = d[0] + x
                const dY = d[1] + y

                if(dX == finish[0] && dY == finish[1] && storeMinStep[grid[dX][dY]] > -1){
                    min = min > storeMinStep[grid[x][y]] + 2
                                 ? storeMinStep[grid[x][y]] + 2: min
                }

                if(isInGridRange([dX, dY]) && isVisited[dX][dY] == -1){
                    isVisited[dX][dY] = 1
                    queue.push({pos: [dX, dY], step: step+1})
                }

            }
        }

        return min
    }

    return findMinStep(start, 0)

}