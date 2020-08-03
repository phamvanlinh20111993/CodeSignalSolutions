/**
 * 
 */

/**
 * - Call f[x][y] is the longest subsequence palindrome from x to y in string a. 
 * So f[0][a.length-1] is the answer for this.
 * - f[0][0] = 1 // each string have mininum palindrome
 * - f[x][y] = max(f[x+1][y], f[x][y-1], f[x+1][y-1]) + 2  if(a[x] == a[y])
 *           = max(f[x+1][y], f[x][y-1], f[x+1][y-1]) 
 *   with every x < y
 * */ 
longestSubsequencePalindrome = a => {
    
    let tracePath = new Array(a.length).fill(new Array());
    tracePath = tracePath.map(v => new Array(a.length).fill(-1));
    
    dpGetLongestSubsequencePadlindrome = (start, end) =>{
        if(start > end){ 
            return 0
        }
        
        if(start == end){
            return 1
        }
    
        let childLeft, childRight, grandChildren;
        
        if(tracePath[start+1][end-1] >= 0){
            grandChildren = tracePath[start+1][end-1]
        } else {
            grandChildren = dpGetLongestSubsequencePadlindrome(start+1, end-1)
            tracePath[start+1][end-1] = grandChildren
        } 
        
        if(a[start] == a[end]){
            tracePath[start][end] = grandChildren + 2
            return grandChildren + 2;
        }else{
            
            if(tracePath[start+1][end] >= 0){
                childLeft = tracePath[start+1][end]
            }else {
                childLeft = dpGetLongestSubsequencePadlindrome(start+1, end)
                tracePath[start+1][end] = childLeft
            }
            
            if(tracePath[start][end-1] >= 0){
                childRight = tracePath[start][end-1]
            }else {
                childRight = dpGetLongestSubsequencePadlindrome(start, end-1)
                tracePath[start][end-1] = childRight
            }
            
            let maxValue = Math.max(...[childLeft, childRight, grandChildren]);
            tracePath[start][end] = maxValue
              
            return maxValue;
        }
    }
    
    let res = dpGetLongestSubsequencePadlindrome(0, a.length-1)

    return res
}
