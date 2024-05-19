/**

***) url: https://app.codesignal.com/interview-practice/question/8tJAmQkq5pTCewQSj/description

The inversion count for an array indicates how far the array is from being sorted. If the array is already sorted, then the inversion count is 0. If the array is sorted in reverse order, then the inversion count is the maximum possible value.

Given an array a, find its inversion count. Since this number could be quite large, take it modulo 109 + 7.

Example

For a = [3, 1, 5, 6, 4], the output should be solution(a) = 3.

The three inversions in this case are: (3, 1), (5, 4), (6, 4).

Input/Output

    [execution time limit] 4 seconds (js)

    [memory limit] 1 GB

    [input] array.integer a

    Guaranteed constraints:
    1 ≤ a.length ≤ 105,
    -1000 ≤ a[i] ≤ 1000.

    [output] integer

    The inversion count of a.


**/

/*solution = a => {
    let c = 0
    for(let ind = 0; ind < a.length; ind++){
        for(let j = ind+1; j < a.length; j++){
           if(a[ind] > a[j]) c++   
        }
    }
    
    return c
}
*/

solution = a => {
    
    actionSortTwoSortedArr = (l, r) => {
        let re = [], c = 0, i = 0, j = 0, te = 0
        while(i < l.length && j < r.length){
            if(l[i] > r[j]){
                te++
                j++
            }else{
                i++
                c += te
            }
        }
        while(i < l.length){
            c += te
            i++
        }
        
        i = 0, j = 0
        while(i < l.length || j < r.length){
            if(i < l.length && j < r.length){
                re.push(l[i] <= r[j] ? l[i++] : r[j++])
                continue
            }
            if(i < l.length){
                re.push(l[i++])
            } 
            if(j < r.length){
                re.push(r[j++])
            }
        }
        
        return {c, re}
    }
    
    mergeSort = (a, l, r) => {
        if(l >= r){
            return {c: 0, re: [a.re[l]]}
        }
        const m = parseInt((l + r) / 2)
        const le = mergeSort(a, l, m)
        const ri = mergeSort(a, m + 1, r)
        
        const re = actionSortTwoSortedArr(le.re, ri.re)
        
        return {c: le.c + ri.c + re.c, re: re.re}
    }
    
    const r = mergeSort({c: 0, re: a}, 0, a.length -1)
    return r.c % (10**9 + 7)
}