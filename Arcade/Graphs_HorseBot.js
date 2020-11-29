/**
 *
 */

function horsebot(n, m) {
    let isVisited = new Array(m).fill([])
    isVisited = isVisited.map(v => new Array(n).fill(0))
    let count = 0

    createDirection = (a, b) => {
        return {
            dx: [-a, a, b, b, a, -a, -b, -b],
            dy: [b, b, a, -a, -b, -b, -a, a]
        }
    }

    isInRange = (a, b) => a >= 0 && b >= 0 && a < m && b < n

    bfs = ([x, y], [dx, dy])=>{
        let queue = [[x, y]]
        isVisited[x][y] = 1

        while(queue.length > 0){
            let [qx, qy] = queue.shift()

            if(qx == m-1 && qy == n-1){
                return 1
            }

            for(let i = 0; i < dx.length; i++){
                let tx = dx[i] + qx
                let ty = dy[i] + qy

                if(isInRange(tx, ty) && isVisited[tx][ty] == 0){
                    isVisited[tx][ty] = 1
                    queue.push([tx, ty])
                }
            }
        }

        return 0
    }

    for(let i = 1; i < Math.min(m, n); i++){
        for(let j = i; j < Math.max(m, n); j++){
            let {dx, dy} = createDirection(i, j)
            count += bfs([0, 0], [dx, dy])
            isVisited = isVisited.map(v => v.map(d => 0))
        }
    }

    return count
}
