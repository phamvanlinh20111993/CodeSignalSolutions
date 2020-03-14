/**
 * 
 */

// find max palindrome in this string.formula  
// totalSub(s, i, j) = if(s[i] == s[j]) totalSub(s, i+1, j-1)+2 else max(totalSub(s, k, i+1, j), totalSub(s, k, i, j-1))
// have some reference formula : https://www.geeksforgeeks.org/longest-palindromic-subsequence-dp-12/
let arr 
totalSub = (s, k, i, j) => {
    if(arr[`${i}--${j}`]){
        return arr[`${i}--${j}`];
    }
    
    if(i > j) return k
    if(i == j) return k + 1

    if(s[i] == s[j]){
        let v = totalSub(s, k + 2, i + 1, j-1);
        arr[`${i}--${j}`] = v;
        return v;
    }else{
        let p = totalSub(s, k, i+1, j),
            m = totalSub(s, k, i, j-1);
            tmp = p > m ? p : m;
        arr[`${i}--${j}`] = tmp
        return tmp
    }
}

kpalindrome = (s, k) => {
   arr = {}
   let t = totalSub(s, 0, 0, s.length-1)
   return k >= s.length - t  ? true: false
}
