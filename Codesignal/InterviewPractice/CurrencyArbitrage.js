/**
 * Note: Try to solve this task in O(n3) time, where n is a number of currencies, since this is what you'll be asked to do during an interview.

A currency converter has the exchange rates exchange, such that exchange[i][j] represents the amount of money you would get for exchanging 1 unit of the ith currency for 1 unit of the jth currency. A "non-exchange" (that is, exchanging a currency with itself) is represented by exchange[i][i] = 1.

Write a function that returns True if it's possible to make money by doing a series of exchanges (i.e. to start with some currency C and to end with a greater amount of currency C after a series of exchanges), and False otherwise.

Example

For exchange = [[1, 0.5, 0.2], [1.8, 1, 0.5], [3.95, 1.2, 1]], the output should be
solution(exchange) = false.
There is no way of transferring money through a series of exchanges to make more money. For example, taking $1 from currency 0, we can get $0.50 (currency 1), then get $0.25 (currency 2) and finally $0.9875 (currency 0). This is an example of one series of exchanges; there is no possible series of exchanges that makes money.

For exchange = [[1, 0.5, 0.2], [1.8, 1, 0.5], [4.05, 1.2, 1]], the output should be
solution(exchange) = true.
Taking $1 from currency 0, we can get $0.50 (currency 1), then get $0.25 (currency 2), and finally get $1.0125 (currency 0).

For exchange = [[1, 0.5, 0.2], [2.05, 1, 0.5], [3.3, 1.2, 1]], the output should be
solution(exchange) = true.
Taking $1 from currency 0, we can get $0.50 (currency 1), and then get $1.025 (currency 0).

Input/Output

[execution time limit] 4 seconds (js)

[memory limit] 1 GB

[input] array.array.float exchange

exchange[i][j] represents the amount of money you would get for exchanging 1 unit of the ith currency for 1 unit of the jth currency.

Guaranteed constraints:
 1 ≤ exchange.length ≤ 14,
exchange[i].length = exchange.length,
0.001 ≤ exchange[i][j] ≤ 1000,
exchange[i][i] = 1.

[output] boolean

Return True if it's possible to make money by doing a series of currency exchanges, and False otherwise.
 */

 /**
  * We should use Floyd–Warshall algorithm for this problem. 
  * refer: https://en.wikipedia.org/wiki/Floyd%E2%80%93Warshall_algorithm 
  * for now, i use my custom algorithm, it pass :)))
  */
 
// O(n^2)
const bfsFindValidExchange = (node, exchange) => {
    let queue = [{n: node, f: node, t: node, v: 1}]
    let isVisit = new Set()
    while(queue.length > 0){
        let n = queue.shift()
        
   //     if(n.t == node && n.v > 1){
   //         return true
   //     }
        
        for(let ind = 0; ind < exchange.length; ind++){
            if(n.n != ind && !isVisit.has(`${n.n}-${ind}`)){
                queue.push({n: ind, f: n.n, t: ind, v: n.v * exchange[n.n][ind]})
                isVisit.add(`${n.n}-${ind}`)
            }
            if(ind == node && n.v * exchange[n.n][ind] > 1){
                return true
            }
        }
    }
    
    return false
}

// O(n^2)
const dfsFindValidExchange = (node, exchange) => {
    let stack = [{n: node, f: node, t: node, v: 1}]
    let isVisit = new Set()
    while(stack.length > 0){
        let n = stack.pop()
         isVisit.add(`${n.f}-${n.t}`)
        for(let ind = 0; ind < exchange.length; ind++){
            if(n.n != ind && !isVisit.has(`${n.n}-${ind}`)){
                stack.push({n: ind, f: n.n, t: ind, v: n.v * exchange[n.n][ind]})
            }
            if(ind == node && n.v * exchange[n.n][ind] > 1){
                return true
            }
        }
    }
    
    return false
}

function solution(exchange) {
	// total O(n^3)
    for(let ind = 0; ind < exchange.length; ind++){
		//  if(dfsFindValidExchange(ind, exchange)){
        if(bfsFindValidExchange(ind, exchange)){
            return true
        }
    }
    
    return false
}
