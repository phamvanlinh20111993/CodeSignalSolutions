/**
 * 
 */
// reference https://www.geeksforgeeks.org/cutting-a-rod-dp-13/
// get all possible using recursive
cutRod = (p, v) => {
    let maxValue = v[p];
    for(let i = 1; i <= p; i++){
        let m = v[i] + cutRod(p - i, v);
        maxValue = maxValue > m ? maxValue : m
    }
    return maxValue
}

//using array
let arr;
cutRobOptimize = (p, v) => {
    let maxValue = v[p];
    for(let i = 1; i <= p; i++){
        let m = v[i];
        if(arr[p-i] == -1){
            m += cutRobOptimize(p - i, v);
            arr[p-i] = m - v[i];
        }else{
            m += arr[p-i];
        }
        
        maxValue = maxValue > m ? maxValue : m
    }
    return maxValue
}

rodCutting = (n, v) => {
    arr = new Array(n).fill(-1)
    return cutRobOptimize(n, v)
}
