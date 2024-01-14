// Not pass: 18/20 :((((, using Kruskal algorithm
/**
 * Url:
 * https://app.codesignal.com/arcade/graphs-arcade/office-structures/9RNbcwe9XWrNKK6qw
 * 
 * In order to protect your office from intruders, your boss decided to install
 * a high-tech lock on the door. The lock represents a large cube with some
 * points floating inside. When the correct pin is entered, the points start to
 * connect to each other by rays of light until they form a single connected
 * structure with rays of the minimum possible total length. When this happens,
 * the lock opens.
 * 
 * Your boss likes interesting challenges, but is not very fond of solving them
 * himself. This is why he asked you, his most (or least?) favorite employee, to
 * solve the challenge he came up with. Given the set of points, he wants you to
 * find the optimal structure that opens the lock. Since there can be several
 * optimal structures, your task is to return the minimum total length of all
 * the rays in one of such structures.
 * 
 * Example
 * 
 * For points = [[0, 0, 0], [1, 1, 1], [1, -1, 1], [-1, 1, 1], [-1, -1, -1]],
 * the output should be solution(points) = 6.9282032303.
 * 
 * The best way is to connect point [0, 0, 0] with all other points.
 * 
 * Input/Output
 * 
 * [execution time limit] 9.5 seconds (js)
 * 
 * [input] array.array.integer points
 * 
 * An array of points floating in the cube. The ith point is represented by its
 * coordinates in 3D space: points[i][0] for x, points[i][1] for y and
 * points[i][2] for z.
 * 
 * Guaranteed constraints: 1 ≤ points.length ≤ 2500, points[i].length = 3, -5000 ≤
 * points[i][j] ≤ 5000.
 * 
 * [output] float
 * 
 * The total length of the rays in an optimal structure. Your answer will be
 * considered correct if its absolute error doesn't exceed 10-5.
 * 
 * 
 */

solution = p =>  {
    const dist = (p1, p2) => ((p1[0] - p2[0])**2 + (p1[1] - p2[1])**2  + (p1[2] - p2[2])**2)**0.5
    
    let connections = new Array()
    for(let ind = 0; ind < p.length - 1; ind++){
        for(let i = ind + 1; i < p.length; i++){
            connections.push([ind, i, dist(p[ind], p[i])])
        }
    }
    connections = connections.sort((a, b) => a[2] - b[2])
    let total = 0, pointsMarking = new Set()
    
    // ref: https://www.geeksforgeeks.org/union-find/
    const parent = new Array(p.length).fill(-1)
    const isCycle = point => {
        // find
        let findParent = peak => {
            if (parent[peak] == -1) return peak
            return findParent(parent[peak])
        }

        let left = findParent(point[0]),
            right = findParent(point[1]);

        if (left != right) {
            // union
            parent[left] = right;
            return false;
        }

        return true
    }
    
    connections.map(conn => {
        if(!isCycle(conn)){
            total += conn[2]
        }
    })
   
    
    return total
}
