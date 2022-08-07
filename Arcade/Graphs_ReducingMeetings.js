/**
 * 
 * You've just become a director of a big company. As it turned out, the company
 * favors one-on-one meetings, which is why there are so many of them each week.
 * Each meeting has an integer efficiency value that shows how much profit it
 * gives the company. In order to make your employees chat less and work more,
 * you'd like remove some one-on-one's from the weekly schedule and leave as few
 * meetings as possible. Of course, there are some restrictions:
 * 
 * it should still be possible to deliver information from any employee to any
 * other one (probably, it'll take a bunch of meetings to deliver a piece of
 * information from one worker to the other one); there should be as few
 * meetings left as possible; the total efficiency should be the maximum
 * possible. The total efficiency equals the sum of efficiencies of all
 * one-on-one's left. Given the number of people in the company n and the list
 * of current one-on-one meetings with their efficiency values, find the best
 * total efficiency of the reduced scheme.
 * 
 * Example
 * 
 * For n = 4 and meetings = [[0, 1, 3], [1, 2, 2], [3, 0, 1], [3, 2, 5]], the
 * output should be solution(n, meetings) = 10.
 * 
 * The best way to reduce meetings is to remove the 3rd (1-based) meeting from
 * the list.
 * 
 * 
 * 
 * Input/Output
 * 
 * [execution time limit] 4 seconds (js)
 * 
 * [input] integer n
 * 
 * The number of people working in your company.
 * 
 * Guaranteed constraints: 1 ≤ n ≤ 100.
 * 
 * [input] array.array.integer meetings
 * 
 * Information about the meetings. For each valid i, meetings[i] contains three
 * numbers: meetings[i][0] and meetings[i][1] stand for ids of the employees
 * between which the meeting is hold and meetings[i][2] stands for the
 * efficiency of this meeting. It is guaranteed that there is no more than one
 * meeting between each pair of workers, and that initially it is possible to
 * deliver information from any colleague to any other one.
 * 
 * Guaranteed constraints: n - 1 ≤ meetings.length ≤ n · (n - 1) / 2,
 * meetings[i].length = 3, 0 ≤ meetings[i][0], meetings[i][1] < n, 0 ≤
 * meetings[i][2] ≤ 100, meetings[i][0] ≠ meetings[i][1].
 * 
 * [output] integer
 * 
 * The best total efficiency of the reduced meetings scheme.
 * 
 * Codesignal Url: https://app.codesignal.com/arcade/graphs-arcade/office-structures/hX3PNx29qLjEomMmN
 * 
 */

// Using prim solution
// https://vi.wikipedia.org/wiki/Thu%E1%BA%ADt_to%C3%A1n_Prim
solution = (n, meetings) => {
    
    let adjMatrix = new Array(n).fill([])
    adjMatrix = adjMatrix.map(v => new Array())
    meetings.map(v => adjMatrix[v[0]].push([v[1], v[2]]) 
        && adjMatrix[v[1]].push([v[0], v[2]]) )
    
    let visitNodes = new Set(), total = 0, edges = []
    const prim = start => {
       
       if(visitNodes.size === n){
           return
       }
        
       visitNodes.add(start)
       edges = [...edges, ...adjMatrix[start]]
      
       let minEdge = [0, -1]
       for(edge of edges){
           if(!visitNodes.has(edge[0]) && minEdge[1] < edge[1]){
               minEdge = edge
           }
       }
       
       if(minEdge[0] >  0){
            total += minEdge[1]
            prim(minEdge[0])
       }
       
    }
    
    prim(0)
    
    return total
}

