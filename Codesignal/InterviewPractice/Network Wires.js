/**
 * 
 * Link: https://app.codesignal.com/interview-practice/task/kfus4H4CSZBjCukwo/description
 * 
 * As a director of a large company, you try to do your best to make the working
 * environment as efficient as possible. However, it's difficult to do so when
 * even the hardware used in the office is not efficient: there are too many
 * wires connecting your employees' computers.
 * 
 * Naturally, you decided to minimize their number by getting rid of some wires.
 * There's only one constraint: if it is possible to deliver information from
 * one computer to another one using the wires, it should still be possible to
 * do so after the renovation. You would also like to minimize the total wires
 * length, because the longer the wires, the more it possible for you to trip
 * over them at some point.
 * 
 * Given the plan of your n office computers and the wires connecting them, find
 * the minimum resulting length of the wires after removing some of them
 * according to the constraints above.
 * 
 * Example
 * 
 * For n = 7 and
 * 
 * wires = [[0, 1, 1], [2, 0, 2], [1, 2, 3], [3, 4, 1], [4, 5, 2], [5, 6, 3],
 * [6, 3, 2]] the output should be solution(n, wires) = 8.
 * 
 * 
 * 
 * The best way is to remove wires 3 and 6 (1-based).
 * 
 * Input/Output
 * 
 * [execution time limit] 4 seconds (js)
 * 
 * [input] integer n
 * 
 * The number of computers in your office.
 * 
 * Guaranteed constraints: 1 ≤ n ≤ 100.
 * 
 * [input] array.array.integer wires
 * 
 * Information about the wires connecting computers. For each valid i, wires[i]
 * contains three numbers: wires[i][0] and wires[i][1] stand for the ids of
 * computers ith wire connects, and wires[i][2] stands for the length of this
 * wire. It is guaranteed that there is no more than one wire between each pair
 * of computers.
 * 
 * Guaranteed constraints: 0 ≤ wires.length ≤ n · (n - 1) / 2, wires[i].length =
 * 3, 0 ≤ wires[i][0], wires[i][1] < n, 0 ≤ wires[i][2] ≤ 100, wires[i][0] ≠
 * wires[i][1].
 * 
 * [output] integer
 * 
 * The minimum resulting length of the wires after removing some of them.
 * 
 */

solution  = (n, wires) => {
    // return solution_Prime(n, wires)
    return solution_Krustal(n, wires)
}

// ref: https://vi.wikipedia.org/wiki/Thu%E1%BA%ADt_to%C3%A1n_Prim
solution_Prime = (n, wires) => {

    // build list adjacency
    let listAdj = new Array(n).fill([])
    listAdj = listAdj.map(v => new Array());

    wires.map(v => listAdj[v[0]].push([v[1], v[2]]) && listAdj[v[1]].push([v[0], v[2]]))

    console.log('listAdj', listAdj)

    // using prim algo
    let selectPeak = new Set(),
        selectPeakStack = new Array(),
        selectEdge = new Set(),
        countMin = 0,
        selectList = new Array();

    for (let ind = 0; ind < n; ind++) {

        if (!selectPeak.has(ind)) {
            selectPeak.add(ind)
            selectPeakStack.push(ind)
            selectList = new Array()
            selectEdge = new Set()
        }

        let minPeakDis = [100000, 0];
        while (minPeakDis[1] != -1) {
            // initiation
            minPeakDis = [100000, -1]
            // to reduce n*(n+1)/2 by using binary heap
            //... not implement here
            selectList = [...selectList, ...listAdj[selectPeakStack[selectPeakStack.length - 1]]]

            selectList.map((v, i) => {
                if (minPeakDis[0] > v[1] && !selectPeak.has(v[0]) && !selectEdge.has(`${v[0]}-${i}`)) {
                    minPeakDis = [v[1], v[0]]
                }
            })

            if (minPeakDis[1] == -1) {
                break;
            }

            selectEdge.add(`${selectPeakStack[selectPeakStack.length -1]}-${minPeakDis[1]}`)
            selectEdge.add(`${minPeakDis[1]}-${selectPeakStack[selectPeakStack.length -1]}`)

            selectPeak.add(minPeakDis[1])
            selectPeakStack.push(minPeakDis[1])

            countMin += minPeakDis[0]
        }
    }

    return countMin;
}

// ref: https://vi.wikipedia.org/wiki/Thu%E1%BA%ADt_to%C3%A1n_Kruskal
solution_Krustal = (n, wires) => {

    wires = wires.sort((a, b) => a[2] - b[2])

    console.log(' sorting wires', wires)

    // ref: https://www.geeksforgeeks.org/union-find/
    const parent = new Array(n).fill(-1)
    let detectCycle = (edge) => {
        // find
        let findParent = (peak) => {
            if (parent[peak] == -1) return peak
            return findParent(parent[peak])
        }

        let left = findParent(edge[0]),
            right = findParent(edge[1]);

        if (left != right) {
            // union
            parent[left] = right;
            return false;
        }

        return true
    }

    // using krustal algo
    let countMin = 0,
        selectPeak = new Set(),
        selectEdge = new Set();

    wires.map(edge => {
        if (!detectCycle(edge)) {
            selectPeak.add(edge[0])
            selectPeak.add(edge[1])

            countMin += edge[2]
        }
    })

    return countMin;
}

