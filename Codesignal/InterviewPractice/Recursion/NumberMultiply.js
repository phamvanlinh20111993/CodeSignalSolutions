/**
 * Give number n, calculation: n = a*b*c*... with a,b,c is a prime.
 * Eg: 
 *  100 = 10^2
 *  101 = 101
 *  102 = 2*51
 *  4 = 2^2
 *  68 = 2^2 * 17
 */
numberMultiply = n => {

  let isPrime = n => {
      
      if(n < 4) return true
  
      let count = 0
      
      for(let ind = 2; ind <= Math.sqrt(n); ind++){
       if(n % ind === 0){
           count++
          }
      }
      
      return count == 0
  }
  
  let storage = []
  
  let consoleResult = (n, arr) => console.log(n + ' = ' + arr.join("*"))
  
  let consolePrettyResult =  (n, arr) => {
      let str = ''
      for(let ind = 0; ind < arr.length;){
       str += arr[ind]
        let pos = ind+1, power = 1
        while(arr[ind] == arr[pos]){
           pos++
           power++
        }
        ind + 1 < pos && (str += '^' + power)
        str += '*'
        ind = pos
      }
      
      console.log(n + ' = ' + str.substring(0, str.length-1))
  }
  
  let recursion = n => {
      let remain = n, ind = 2
   while(remain % ind !== 0 && ind < n/2) ind++
      
      while(remain % ind === 0){
          remain > 1 && storage.push(ind)
          remain /= ind
      }
      
      if(n !== remain){
       recursion(remain)
      }else{
         remain > 1 && storage.push(remain)
      }
      
  }
  
  n < 10000000 && isPrime(n) ? storage.push(n) : recursion(n)
  
  console.log("###################### with Test n = " +  n)
  consoleResult(n, storage)
  consolePrettyResult(n, storage)
} 


numberMultiply(120242342)
numberMultiply(120)
numberMultiply(2*2*3*3*7*11*17)
numberMultiply(4294967296)
numberMultiply(13500000)
numberMultiply(20000000004000)
numberMultiply(200000000040001)
numberMultiply(100000000040001)
numberMultiply(190111000040017)
numberMultiply(11777777)
numberMultiply(47)
numberMultiply(1314335464575352546)