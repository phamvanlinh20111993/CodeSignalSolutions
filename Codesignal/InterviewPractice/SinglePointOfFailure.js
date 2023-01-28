/**
 * Note: Try to solve this task in O(n2) time, where n is a number of vertices,
 * since this is what you'll be asked to do during an interview.
 * 
 * Sue is a network administrator who consults for companies that have massive
 * Local Area Networks (LANs). The computers are connected together with network
 * cables, and Sue has been brought in to evaluate the company’s network. The
 * networks are huge, and she wants to ensure that no single network cable
 * failure can disconnect the network. Any cable that fails that leaves the
 * network in two or more disconnected pieces is called a single point of
 * failure.
 * 
 * She labels the different network devices from 0 to n - 1. She keeps an n × n
 * matrix connections, where connections[i][j] = 1 if there is a network cable
 * directly connecting devices i and j, and 0 otherwise. Write a function that
 * takes the matrix of connections, and returns the number of cables that are a
 * single point of failure.
 * 
 * Example
 * 
 * For connections = [[0, 1], [1, 0]], the output should be
 * singlePointOfFailure(connections) = 1. A failure of the cable that connects
 * devices 0 and 1 would leave the network disconnected.
 * 
 * 
 * 
 * For connections = [[0, 1, 1], [1, 0, 1], [1, 1, 0]], the output should be
 * singlePointOfFailure(connections) = 0. No failure of a single network cable
 * would result in the network being disconnected.
 * 
 * 
 * 
 * For connections = [[0, 1, 1, 1, 0, 0], [1, 0, 1, 0, 0, 0], [1, 1, 0, 0, 0,
 * 0], [1, 0, 0, 0, 1, 1], [0, 0, 0, 1, 0, 0], [0, 0, 0, 1, 0, 0]], the output
 * should be singlePointOfFailure(connections) = 3. The three cables that are
 * single points of failure are connected with device 3:
 * 
 * 
 * 
 * For connections = [[0, 1, 1, 1, 1], [1, 0, 0, 0, 0], [1, 0, 0, 0, 0], [1, 0,
 * 0, 0, 0], [1, 0, 0, 0, 0]], the output should be
 * singlePointOfFailure(connections) = 4. In this topology, every cable is a
 * single point of failure:
 * 
 * 
 * 
 * Input/Output
 * 
 * [execution time limit] 4 seconds (js)
 * 
 * [input] array.array.integer connections
 * 
 * Representations of connections between computers. connections[i][j] = 1 if
 * there is a network cable directly connecting devices i and j, and 0
 * otherwise. It is guaranteed that the original network is connected.
 * 
 * Guaranteed constraints: 1 ≤ connections.length ≤ 300, connections[i].length =
 * connections.length, connections[i][i] = 0, 0 ≤ connections[i][j] ≤ 1,
 * connections[i][j] = connections[j][i].
 * 
 * [output] integer
 * 
 * The number of cables in the network that are a single point of failure.
 */

/***
	Solution:
	1, If node just has 1 connection => single point of failure.
	2, If node has more than 1 connection.
	   + With each connection, Using dfs from that connection to the root (node 0)
	      . If existence at least 2 paths go to the root. Do nothing
          . Else this node is single point of failure.
		  
    Note: To help improve performance, we can create a global array track from any node path to the root 
	just avoid to do the same thing (Do dfs again.)
    I do not implement here.
	    
**/
singlePointOfFailure = (connections) => {
    let result = 0
    let isVisited = new Array(connections[0].length).fill(0)
    
    for (let ind = 1; ind < connections[0].length; ind++) {
        let countConnects = connections[ind].filter(connect => connect > 0)
        if (countConnects.length === 1) {
            result++
            isVisited[ind] = 1
        }
    }
    
    const checkPathToRoot = (point) => {
        const isVisited = new Array(connections[0].length).fill(0)
        let totalPath = 0
        let count = 0
        
        const dfs = (node) => {
            if (count > 0) return
            
            if (node === 0) {
                count++
                return
            } else {
                isVisited[node] = 1
            }
            
            for (let ind = 0; ind < connections[0].length; ind++) {
                if (isVisited[ind] === 0 && connections[node][ind] === 1) {
                    dfs(ind)
                }
            }
            isVisited[node] = 0
        }
        
        isVisited[point] = 1
        for (let ind = 0; ind < connections[0].length; ind++) {
            count = 0
            if (connections[point][ind] === 1) {
                dfs(ind)
            }
            totalPath += count
        }
        return totalPath
    }

    for (let ind = 1; ind < connections[0].length; ind++) {
        if (isVisited[ind] === 0 && checkPathToRoot(ind) <= 1) {
            result++
        }
    }
    
    return result
}
