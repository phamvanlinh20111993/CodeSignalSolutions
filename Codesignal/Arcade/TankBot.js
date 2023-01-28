/**
 *
 */
tankbot = forest => {
	const direction = [[0, 1], [1, 0], [-1, 0], [0, -1]]

    // let mapX = new Map()
	// let mapY = new Map()

	// forest.map((r, i) => {
	//     r.map((v, j) => {
	//         if(!v){
	//             mapX.set(j, i)
	//             mapY.set(i, j)
	//         }
	//     })
	// })

    // this check may be less cost complexity but got error
    // isCollisionWithTree = (x, y, l) => {
    //     if(mapX.has(x) && (mapX.get(x) >= y && mapX.get(x) <= y + l))
    //         return true

    //     if(mapY.has(y) && (mapY.get(y) >= x && mapY.get(y) <= x + l))
    //         return true

    //     if(mapY.has(x+l) && (mapX.get(x+l) >= y && mapX.get(x+l) <= y + l))
    //         return true

    //     if(mapY.has(y+l) && (mapY.get(y+l) >= x && mapY.get(y+l) <= x + l))
    //         return true

    //     return false
    // }

	isCollisionWithTree = (x, y, l) => {
        for(let i = x; i <= x + l ; i++){
            for(let j = y; j <= y +l; j++)
                if(!forest[j][i])
                    return true
        }

        return false
    }

    let isVisited = new Array(forest[0].length).fill([])
    isVisited = isVisited.map(v => new Array(forest.length).fill(0))

    isIntheForest = (x, y, length) => x >= 0 && y >= 0
    && x + length < forest[0].length && y + length < forest.length

    // Using bfs
    let maximum = 0
    findTheWay = (x, y, l) => {

        let queue = [[x, y]]
        isVisited[x][y] = 1

        while(queue.length > 0){
            let [xA, yA] = queue.shift()
            if(xA + l == forest[0].length-1 && yA + l == forest.length-1){
                maximum = maximum < l ? l + 1 : maximum + 1
                return true
            }

            for(let i = 0; i < direction.length; i++){
                let xT = direction[i][0] + xA
                let yT = direction[i][1] + yA

                if(isIntheForest(xT, yT, l) && !isVisited[xT][yT] &&
                !isCollisionWithTree(xT, yT, l)){
                    queue.push([xT, yT])
                    isVisited[xT][yT] = 1
                }
            }
        }

        return false
    }

    if(forest[0][0] == false) return 0

    for(let i = 0; i < forest.length; i++){
        if(!findTheWay(0, 0, i)){
            break
        }
        isVisited = isVisited.map(r => r.map(v => 0))
    }

    return maximum
}
