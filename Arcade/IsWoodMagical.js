/**
 *
 */

// reference: https://www.geeksforgeeks.org/detect-cycle-undirected-graph/
isWoodMagical = (n, wmap) => {
    let isVisited = new Array(n).fill(0)
    let parrentCycle = new Array(n).fill(-1)

    let adjMatrix = new Array(n).fill([])
    adjMatrix = adjMatrix.map(v => new Array())

    for(const list of wmap){
        adjMatrix[list[0]].push(list[1])
        adjMatrix[list[1]].push(list[0])
    }

 //   console.log(adjMatrix)
    checkCycleLength = (current, parent, cyclePoint) =>{
        let len = 0
        while(cyclePoint[current] != parent && cyclePoint[current] > -1){
            current = cyclePoint[current]
            len++
        }
        return len > 0 ? len : 1;
    }

    let count = 0
    let dfs = (start, parent) => {
        isVisited[start] = 1

        if(count % 2 != 0){
            return
        }

        for(let next of adjMatrix[start]){
            if(!isVisited[next]){
                parrentCycle[next] = start
                dfs(next, start)
            // undirect graph must be check next != parent
            }else if(isVisited[next] == 1 && next != parent){
                let check = checkCycleLength(start, next, parrentCycle)
                if(check % 2 != 0){
                    count = check
                }
            }
        }

        if(count % 2 != 0){
            return
        }

        isVisited[start] = 2
    }

    for(let ind = 0; ind < adjMatrix.length; ind++){
        if(adjMatrix[ind].length > 0){
            dfs(ind, -1)
            if(count % 2 != 0){
                return false
            }
        }
    }

   return count % 2 == 0
}
