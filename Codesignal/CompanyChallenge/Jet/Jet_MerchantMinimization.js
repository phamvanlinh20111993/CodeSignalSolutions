/**

At Jet.com, each customer is guaranteed quick service since there are always enough merchants available to fulfill a given order. To keep the number of available merchants high, Jet tries to minimize the number of merchants required to fulfill a given order.

Consider a list of items the customer has ordered, and a list of available merchants. For each merchant it is known which items they have in their inventory. Calculate which merchants should fulfill the order so that all items are accounted for and the number of merchants is kept as small as possible. Return the answer as a 0-based array of merchant indices.

If there are several possible answers, return the lexicographically smallest one. If there is no answer, return [-1] instead.

Example

For items = ["apples", "bananas", "kiwis"] and

merchants = [["apples", "pineapples"],
             ["apples", "kiwis"],
             ["kiwis", "papayas", "bananas"],
             ["bananas", "apples"]]
the output should be
merchantMinimization(items, merchants) = [0, 2].

In this case it's impossible to use a single available merchant to fulfill the entire order. However, there are several sets of 2 merchants that could work:

merchants 1 and 2;
merchants 1 and 3;
merchants 2 and 3;
merchants 0 and 2.
The last option forms the lexicographically smallest array, thus it is the correct answer.

Input/Output

[execution time limit] 4 seconds (js)

[input] array.string items

Array of items in the order. It is guaranteed that each item name is unique.

Guaranteed constraints:
1 ≤ items.length ≤ 12,
1 ≤ items[i].length ≤ 15.

[input] array.array.string merchants

The list of available merchants. For each valid i, merchants[i] is an array of different strings representing items which the ith merchant can fulfill.

Guaranteed constraints:
3 ≤ merchants.length ≤ 10,
1 ≤ merchants[i].length ≤ 7,
1 ≤ merchants[i][j].length ≤ 15.

[output] array.integer

The indices of the group of merchants that should fulfill the order or [-1] if there is no answer.

[JavaScript] Syntax Tips

**/
// true if a < b
// false in otherwise
let compareTwoArr = (a, b) => {
    
    if(a.length < b.length){
        return true;
    }
    
    let ind = 0, pos = 0
    while(ind < a.length && pos < b.length){
        
        if(a[ind] < b[pos])
            return true
            
        if(a[ind] > b[pos])
            return false
        
        ind++
        pos++
    }
}

merchantMinimization = (items, merchants) => {
   let mechantItemSelection = new Array(items.length).fill([]);
   mechantItemSelection = mechantItemSelection.map(v => [])
   
   items.map((v, index) => {
       merchants.map((row, pos) => {
            if(row.includes(v)){
                mechantItemSelection[index].push(pos) 
            }
       })
   })
   
   const isItemNotFoundMechant = () => {
       let empty = mechantItemSelection.filter(v => v.length == 0)
       return empty && empty.length > 0 
   }
   
   if(isItemNotFoundMechant()){
       return [-1]
   }
   
   let currentMin = ""
   let backTrackFindMin = (start, distinctVal) => {
       if(start >= mechantItemSelection.length){
           if(currentMin == ""){
               currentMin = distinctVal
           }else if(compareTwoArr(Array.from(distinctVal), Array.from(currentMin))){
                 currentMin = distinctVal
           }
           return
       }
       
       for(let ind = 0; ind < mechantItemSelection[start].length; ind++){
          let newSet = new Set(distinctVal)
          newSet.add(mechantItemSelection[start][ind])
          backTrackFindMin(start + 1, newSet)
       }
       
   }
   
   backTrackFindMin(0, new Set())
   
   return Array.from(currentMin).sort((a,b) => a - b)
}
