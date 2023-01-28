/**
 * 
 */


// call L[i] is the max of array in position i.
// We have the formula:
// L[i] = max(L[j] + 1 If(s[i] > s[j])) with j ~[0, i-1)
let arr, res
getLongest = (s, i) => {
   if(i <= 0) return 1;

   if(arr[i]) return arr[i]
   let max = 1;
   for(let ind = i-1; ind >= 0; ind--){
       let v = getLongest(s, ind);
       if(s[i] > s[ind]){
           v += 1
           if(max < v) max = v
       }
   }
   arr[i] = max;
   res = res < max ? max: res;
   return max;
}

longestIncreasingSubsequence = s => {

    arr = new Array(s.length).fill(0);
    res = 0;
    let r = getLongest(s, s.length - 1)
   
    return res
}
