/**
 * 
 * URL: https://app.codesignal.com/interview-practice/task/QTirmApTj7sWaidLk/description
 * 
 * You have a strongly connected directed graph that has positive weights in the
 * adjacency matrix g. The graph is represented as a square matrix, where
 * g[i][j] is the weight of the edge (i, j), or -1 if there is no such edge.
 * 
 * Given g and the index of a start vertex s, find the minimal distances between
 * the start vertex s and each of the vertices of the graph.
 * 
 * Example
 * 
 * For
 * 
 * g = [[-1, 3, 2], [2, -1, 0], [-1, 0, -1]] and s = 0, the output should be
 * solution(g, s) = [0, 2, 2].
 * 
 * Example
 * 
 * The distance from the start vertex 0 to itself is 0. The distance from the
 * start vertex 0 to vertex 1 is 2 + 0 = 2. The distance from the start vertex 0
 * to vertex 2 is 2. Input/Output
 * 
 * [execution time limit] 4 seconds (js)
 * 
 * [input] array.array.integer g
 * 
 * The graph is represented as a square matrix, as described above.
 * 
 * Guaranteed constraints: 2 ≤ g.length ≤ 100, g.length = g[i].length, -1 ≤
 * g[i][j] ≤ 30.
 * 
 * [input] integer s
 * 
 * The start vertex (0-based).
 * 
 * Guaranteed constraints: 0 ≤ s < g.length.
 * 
 * [output] array.integer
 * 
 * An array, the ith element of which is equal to the distance between the start
 * vertex s and the ith vertex of the graph g.
 * 
 * [JavaScript] Syn
 */

// Using Backtracking - not pass
/*solution = (g, st) => {
    let isVisited, 
        res = new Array(g.length).fill(100000)
    
    const dpGetInstance = (s, d, total) => {
        console.log('s', s)
        if (s == d) {
            console.log(s, 'total', total)
            res[d] = res[d] > total ? total : res[d]
            return
        }

        for (let i = 0; i < g[0].length; i++) {
            if (g[s][i] != -1 && !isVisited.has(`${s}-${i}`)) { //`${s}-${i}`
                isVisited.add(`${s}-${i}`)
                dpGetInstance(i, d, total + g[s][i])
                isVisited.delete(`${s}-${i}`)
            }
        }
    }
    
    for(let ind = 0; ind < g.length; ind++){
        isVisited = new Set()
        console.log('next')
        dpGetInstance(st, ind, 0)
    }
    
    return res
} */



// Using DP: has some problem
solution = (g, st) => {
    const MAX = 100 * 100 * (30 + 1)
    let isVisited, 
        res = new Array(g.length).fill(MAX)
    res[st] = 0
    
    const dpGetInstance = d => {    
        if(res[d] < MAX) return res[d]
        
        let min = MAX
        for (let i = 0; i < g.length; i++) {
            const key = `${d}-${i}`
            if (g[i][d] != -1 && !isVisited.has(key)) {
                isVisited.add(key)
                const m = g[i][d] + dpGetInstance(i)
                min = min > m ? m : min
                isVisited.delete(key)
            }
        }
        
        res[d] = res[d] > min ? min : res[d] 
        return min
    }
    
 //   for(let ind = 0; ind < g.length; ind++){
    for(let ind = g.length - 1; ind >= 0; ind--){
        ind != st && (res[ind] = MAX)
        isVisited = new Set()
        dpGetInstance(ind)
    }
    
    // Fail 15/16, when loop again, passed -_-
//    for(let ind = 0; ind < g.length; ind++){
    for(let ind = g.length - 1; ind >= 0; ind--){
        ind != st && (res[ind] = MAX)
        isVisited = new Set()
        dpGetInstance(ind)
    }
    
    return res
}

//Using Dijkstra
//https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
//https://chidokun.github.io/2021/09/dijkstra-algorithm/
solution = (g, st) => {
	// TODO
}


