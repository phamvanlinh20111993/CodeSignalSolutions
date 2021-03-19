
// is same ?? : https://vn.spoj.com/problems/LATGACH/
// I try to find formula for this but it seem very deficult, so i reference on internet:
// https://math.stackexchange.com/questions/664113/count-the-ways-to-fill-a-4-times-n-board-with-dominoes
// using dp top down to solve.
// dp(n - 1) + 5 * dp(n - 2) + dp(n - 3) - dp(n - 4).
// When the formula is available, the problem is considered solved. 

fillingBlocks = n => {
    
    let store = new Map()
    
    dp = n => {
        if(n <= 1) return 1
        else if(n == 2) return 5
        else if(n == 3) return 11
        else if(n == 4) return 36
        else {
          //  if(store.has(n))
          //      return store.get(n)
            
            let res = dp(n - 1) + 5 * dp(n - 2) + dp(n - 3) - dp(n - 4);
         //   store.set(n, res)
            
            return res;
        }
    }
    
    return dp(n)
}
