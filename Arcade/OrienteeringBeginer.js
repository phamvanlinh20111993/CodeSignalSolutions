/**
 *
 */
/*orienteeringBeginner = (n, roads) => {
    let min = 10000000000;
    dp = node => {
        if(node[0] == n-1){
            console.log(node)
            if(min > node[1]){
                min = node[1]
            }
        }
        for(const next of roads[node[0]]){
            dp([next[0], next[1] + node[1]])
        }
    }

    dp([0, 0])

    return min
}
*/

orienteeringBeginner = (n, roads) => {

    let storeMinValue = new Array(n).fill(-1)

    dp = node => {
        if(node[0] == n-1) return 0

        if(storeMinValue[node[0]] > -1){
            return storeMinValue[node[0]]
        }

        let minCost = 500000000;
        for(const next of roads[node[0]]){
            let cost = next[1] + dp(next)
            if(minCost > cost)  minCost = cost
        }
        storeMinValue[node[0]] = minCost
        return minCost;
    }

    dp([0, 0])
    return storeMinValue[0]
}
