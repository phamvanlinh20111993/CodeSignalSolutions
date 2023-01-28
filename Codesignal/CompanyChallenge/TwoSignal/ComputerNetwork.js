/**
 * https://app.codesignal.com/company-challenges/two-sigma/56dCgYQwe8p7vBFiT
 * 
 * You know what they say: "time is money." In today's markets, the price of a stock that you see on your computer might not be the price that you end-up trading at, since by the time your request reaches the exchange the price might have changed. Therefore, the quicker you can get your order to the exchange, the better the chances that you will trade at your expected price.

Picture a peer-to-peer computer network of n nodes that's supposed to route your request from your computer to a computer where the trade is actually registered. Let's assume that the network is not optimized yet, so it's your task to implement an algorithm that computes the shortest path from the source at index 1 (your computer) to the destination at index n.

Example

For n = 4 and

network = [[1, 4, 200], 
           [1, 2, 5], 
           [1, 3, 10], 
           [2, 3, 4], 
           [2, 4, 150], 
           [3, 4, 100]]
the output should be
solution(n, network) = 109.

The shortest path is 1 -> 2 -> 3 -> 4.



Input/Output

[execution time limit] 4 seconds (js)

[input] integer n

A positive integer equal to the number of nodes in the network.

Guaranteed constraints:
1 ≤ n ≤ 10.

[input] array.array.integer network

For each valid i, network[i] consists of three positive integers and corresponds to the two-way connection between the nodes network[i][0] and network[i][1]. Routing the stock order through that connection takes network[i][2] milliseconds.

It is guaranteed that there is a route between any pair of nodes. It is also guaranteed that there is no more than one direct connection between any pair of nodes.

Guaranteed constraints:
0 ≤ network.length ≤ 20,
1 ≤ network[i][0], network[i][1] ≤ n,
1 ≤ network[i][2] ≤ 104.

[output] integer

The minimum time needed to route the stock from node 1 to node n in milliseconds.

 */


var min;
dfs = (n, startPoint, isVisited, network, total) =>{
    let arr = []
    isVisited[startPoint] = 1;
    for(let ind = 0; ind < network.length; ind++){
        if(network[ind][0] == startPoint){
            arr.push({n: network[ind][1], p: network[ind][2]})
        }
        if(network[ind][1] == startPoint){
            arr.push({n: network[ind][0], p: network[ind][2]});
        }
    }
    
    for(let ind = 0; ind < arr.length; ind++){
        if(isVisited[arr[ind].n] == 0){
            dfs(n, arr[ind].n, isVisited, network, total + arr[ind].p);
        }
    }
    isVisited[startPoint] = 0;
    if(startPoint == n && min > total){
        min = total
    }
}

solution = (n, network) => {
    min = 10000000;
    dfs(n, 1, new Array(n+1).fill(false), network, 0)
    return min;
}
