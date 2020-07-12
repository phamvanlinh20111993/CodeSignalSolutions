/**
 * 
 */
nightRoute = city => {
    let traceMinPath = new Array(city.length).fill(-1)  
    
    let isVisit = new Array(city.length).fill([])
    isVisit = isVisit.map(v => new Array(city.length).fill(0))
    
    /* Using dynamic programming:
	     Call f[x] is mint cost from any path connected with x
	     => - f(n) = 0
	        - f[x] = Min(cost(x, yt) + f[yt]) with yt is a list path(vertex) connected with x
	        - Min cost of the trip from 0 to n is f(0)
	*/
    getMinPath = curr => {
        if(curr == city.length-1)
            return 0
        if(traceMinPath[curr] > -1)
            return traceMinPath[curr]
            
        let minCost = 1000;
        for(let ind = 0; ind < city.length; ind++){
            if(isVisit[curr][ind] == 0 && city[curr][ind] > -1){
                isVisit[curr][ind] = 1 
                minCost = Math.min(city[curr][ind] + getMinPath(ind), minCost)
            }
        }
        if( traceMinPath[curr] > - 1){
            if( traceMinPath[curr] > minCost)
                traceMinPath[curr] = minCost
        }else
            traceMinPath[curr] = minCost
            
        return traceMinPath[curr]
    }
    
    getMinPath(0)   
    
    return traceMinPath[0]
}
