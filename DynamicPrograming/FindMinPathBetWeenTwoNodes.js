
let arr
//Find shortest path between two node
//formula: d[i, j] = min(v[i-k] + min(k)) k is every nodes connected with node i
//                   if k == j return v[j]
getDistance = (node, finish, graph) => {
    if(arr[node-1] > -1){
        return arr[node-1]
    }else{
        isVisit[node-1] = 1
    }
    let min = 1000000;
    for(let i = 0; i < graph.length; i++){
        if(graph[node-1][i] > 0 && isVisit[i] == -1){
            if(i == finish-1){
                min = min > graph[node-1][i] ? graph[node-1][i] : min;
            }else{
                let m = graph[node-1][i] + getDistance(i+1, finish, weight, graph)
                min = min > m ? m : min;
            }
        }
    }
    //restore
    isVisit[node-1] = -1
    arr[node-1] = min;
    return min;
}


shortestPathWithEdge = (start, finish, graph) => {
    if(start >= graph.length) return 0
    isVisit = new Array(graph.length).fill(-1)
    arr = new Array(graph.length).fill(-1)
    let r = getDistance(start, finish, weight, graph);
    console.log('min', r, arr)

    return r
}


/**
 * 
 * https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/
 * [[ 0, 4, 0, 0, 0, 0, 0, 8, 0 ],[ 4, 0, 8, 0, 0, 0, 0, 11, 0 ],[ 0, 8, 0, 7, 0, 4, 0, 0, 2 ],[ 0, 0, 7, 0, 9, 14, 0, 0, 0 ],[ 0, 0, 0, 9, 0, 10, 0, 0, 0 ], [ 0, 0, 4, 14, 10, 0, 2, 0, 0 ], [ 0, 0, 0, 0, 0, 2, 0, 1, 6 ],[ 8, 11, 0, 0, 0, 0, 1, 0, 7 ],[ 0, 0, 2, 0, 0, 0, 6, 7, 0 ] ]
 */