/**
 * refer: https://app.codesignal.com/interview-practice/task/bdatSZvuKFyjJ8eYw/description
 * 
 * You are the head zookeeper at a large zoo. You've been contacted by schools in the area that want to bring in classes so that students can feed the animals. The animals can only be fed once a day, so no two classes can come on the same day if they want to feed the same animals.

You have a list classes, such that classes[i] is a list of animals that the ith class wants to feed. Classes i and j cannot come on the same day if an animal in classes[i] also appears in classes[j] (for i ≠ j). Your job is to determine the minimum number of days you would need to have all the classes come to feed the animals if they can all come within 5 days. If it isn't possible for all the classes to come within 5 days, return -1 instead.

Example

For classes = [["Tiger", "Lima", "Frog"], ["Tiger", "Zebra","Lion"], ["Tiger", "Rabbit"], ["Emu", "Zebra", "Rabbit"]], the output should be
solution(classes) = 3.
Classes 0, 1, and 2 all want to feed the Tiger, so they have to come on different days. Class 3 cannot come on the same day as class 1 (because of the Zebra) or class 2 (because of the Rabbit), but they can come on the same day as class 0. Therefore it only takes 3 days for all the classes to visit the zoo.

For classes = [["Tiger", "Lima", "Frog"], ["Tiger", "Zebra", "Lion"], ["Tiger", "Rabbit"], ["Lima", "Zebra", "Rabbit"]], the output should be
solution(classes) = 4.
Each class has to come on a separate day, because every pair of classes has at least one animal in common.

For classes = [["Lion", "Emu"], ["Giraffe", "Peacock"], ["Lima"], ["Tiger", "Cheetah", "Slugs"], ["Snakes", "Sealion"]], the output should be
solution(classes) = 1.
No classes have animals in common, so they can all come on the same day.

Input/Output

[execution time limit] 4 seconds (js)

[input] array.array.string classes

classes[i] is a list of animals that the ith class wants to feed.

Guaranteed constraints:
1 ≤ classes.length ≤ 15,
1 ≤ classes[i].length ≤ 10,
1 ≤ classes[i][j].length ≤ 14.

[output] integer

The minimum number of days you would need to have all the classes come to feed the animals. If it isn't possible for all the classes to come within 5 days, return -1 instead.
 */


/*solution = classes => {
    let groups = new Array()
    classes.map(v => {
        let isAdd = false
        groups = groups.map(dt => {
            const newSet = new Set([...dt, ...v])
            if(!isAdd && dt.size + v.length === newSet.size){
                isAdd = true
                return newSet
            }else{
                return dt
            }
        })
        
        if(!isAdd) {
            groups.push(new Set([...v]))
        }
    })
    
    return groups.length > 5 ? -1 : groups.length
}
*/

/*solution = classes => {

    let isInClassB = (classA, classB) => {
        let classAMap = new Map()
        classA.map(v => classAMap.set(v, 1))
        let isInClass = false
        classB.map(v => classAMap.has(v) ? (isInClass = true) : 1)
        return isInClass
    }
    
    let graph = new Array(classes.length).fill([])
    graph = graph.map(v => [])
    classes.map((cl, ind) => {
        classes.filter((clas, pos) => {
            if(pos !== ind && isInClassB(cl, clas)){
                graph[ind].push(pos)
            }
        })
    })
    graph = graph.map(g => g.sort((a, b) => a - b))
    console.log(graph)
    const colorMarks = new Array(classes.length).fill(0)
    
    const findMinColorCode = colorMs => {
        const maxColorCode = colorMs.reduce((a, b) => Math.max(a, b), -Infinity);
        let minColorCode = 1
        
        for(let ind = 1; ind <= maxColorCode + 1; ind++){
            if(!colorMs.includes(ind)){
                minColorCode = ind
                break
            }
        }
        
        return minColorCode
    }
    
    // graph color algo
    //....
    
     dfsGraphColor = node => {
        let arr = []
        graph[node].forEach(adjNode => colorMarks[adjNode] > 0 && arr.push(colorMarks[adjNode]))
        colorMarks[node] = findMinColorCode(arr)

        graph[node].forEach(adjNode => colorMarks[adjNode] === 0 && dfsGraphColor(adjNode))
    }
    colorMarks.map((v, ind) =>{
        v === 0 && dfsGraphColor(ind)
    })
    const res = new Set([...colorMarks]).size
    return res > 5 ? -1 : res 
} */


/**
 * I read some solutions after i finished my solution
 * best solution for this is Backtracking K-coloring problem
 * refer: https://www.geeksforgeeks.org/m-coloring-problem-backtracking-5/
 * I did not follow that but it's very good for worth learning
 */

solution = classes => {

    const isInClassB = (classA, classB) => {
        const classAMap = new Map()
        classA.map(v => classAMap.set(v, 1))
        let isInClass = false
        classB.map(v => classAMap.has(v) && (isInClass = true))
        return isInClass
    }
    
    let graph = new Array(classes.length).fill([]).map(v => [])
    classes.map((cl, ind) => 
        classes.filter((clas, pos) => pos !== ind && isInClassB(cl, clas) && graph[ind].push(pos))
    )
    
    const findMinColorCode = colorMs => {
        const maxColorCode = colorMs.reduce((a, b) => Math.max(a, b), 1)
        let minColorCode = 1   
        for(let ind = 1; ind <= maxColorCode + 1; ind++){
            if(!colorMs.includes(ind)){
                minColorCode = ind
                break
            }
        }
        return minColorCode
    }
    
    /**
	 * start with each vertex then we can find the miminum color
	 */
    const allPosibleColoring = start => {
        const colorMarks = new Array(graph.length).fill(0)  
        // graph coloring algo
        // ....
        const graphColoring = node => {
            let arr = []
          
            graph[node].forEach(adjNode => 
                colorMarks[adjNode] > 0 && arr.push(colorMarks[adjNode]))
                
            colorMarks[node] = findMinColorCode(arr)
            
            graph[node].forEach(adjNode => colorMarks[adjNode] === 0 && graphColoring(adjNode))
        }
        // choose point to start coloring
        graphColoring(start)
        
        graph.map((v, ind) => {
            if(colorMarks[ind] === 0){
                graphColoring(ind)
            }
        })
        
        return new Set([...colorMarks]).size
    }
    
    let minColor = 10000
    graph.map((v, ind) => {
        const currColor = allPosibleColoring(ind)
        minColor = currColor > minColor ? minColor : currColor
    })
    
    return minColor > 5 ? -1 : minColor
}