/**
 * 
 * Ref:
 * https://app.codesignal.com/interview-practice/task/tNajEfDckCgSvrjQp/description
 * 
 * 
 * 
 * We will define the multiplicative inverse n modulo m as an integer ninv such
 * that (n · ninv) % m = 1. We will restrict our attention to the inverses ninv
 * in the interval [o, m-1].
 * 
 * Note: For a % b, the % sign indicates the modulo operation (i.e., the
 * remainder of dividing a by b).
 * 
 * Given the numbers n and m, find the multiplicative inverse n modulo m. If no
 * such multiplicative inverse exists, return -1.
 * 
 * Example
 * 
 * For n = 4 and m = 15, the output should be solution(n, m) = 4.
 * 
 * 4 · 4 = 16 = 15 · 1 + 1, i.e. (4 · 4) % 15 = 1, so ninv = 4 is correct
 * answer.
 * 
 * For n = 7 and m = 15, the output should be solution(n, m) = 13.
 * 
 * 7 · 13 = 91 = 15 · 6 + 1, i.e. (7 · 13) % 15 = 1, so ninv = 13 is correct
 * answer.
 * 
 * For n = 5 and m = 15, the output should be solution(n, m) = -1.
 * 
 * None of numbers 0, 1, ..., 14 are correct.
 * 
 * Input/Output
 * 
 * [execution time limit] 4 seconds (js)
 * 
 * [input] integer64 n
 * 
 * Guaranteed constraints: 0 ≤ n ≤ m - 1.
 * 
 * [input] integer64 m
 * 
 * Guaranteed constraints: 2 ≤ m ≤ 1015.
 * 
 * [output] integer64
 * 
 * Return the multiplicative inverse n modulo m, or -1 if it doesn't exist.
 * 
 */

// modulo recipe: (n · ninv) % m = 1 
// <=> n · ninv = km + 1 (k is positive number)
// <=> ax + by = 1 (a = n, b = -m, x = ninv, y = k)
//
// => https://vi.wikipedia.org/wiki/Gi%E1%BA%A3i_thu%E1%BA%ADt_Euclid_m%E1%BB%9F_r%E1%BB%99ng

// => https://en.wikipedia.org/wiki/Euclidean_algorithm

// => https://vi.wikipedia.org/wiki/Gi%E1%BA%A3i_thu%E1%BA%ADt_Euclid#:~:text=Gi%E1%BA%A3i%20thu%E1%BA%ADt%20Euclid%20d%E1%BB%B1a%20tr%C3%AAn,v%C3%A0%20252%20%E2%88%92%20105%20%3D%20147.
const gcd = (a, b) => b == 0 ? a : gcd(b, a % b)

solution = (n, m) => {
    // console.log(gcd(n, m))
    // gcd(m, n) = 1 then exist x, y integer
    if(gcd(n, m) !== 1) return -1
    
    /**
     * Ref: https://vi.wikipedia.org/wiki/Gi%E1%BA%A3i_thu%E1%BA%ADt_Euclid_m%E1%BB%9F_r%E1%BB%99ng
     * Formula base on : gcd(a, b) = d
     * 
     *  r0 = a, r1 = b
     *  Call r2 is module's result of r0/r1 (mod(r0, r1)), and q1 is Math.floor(r0/r1)
     *  => r0 = q1*r1 + r2 (r0 = Math.floor(r0/r1) * r1 + mod(r0/r1) = r0)
     *     r1 = q2*r2 + r3 (...)
     *     .....
     *     rm = qm+1 + rm+1
     *  After m step then rm+2 = 0    
     * 
     *  Equation: ax + by = d
     *     a*1 + b*0 = a = r0 with x0 = 1, y0 = 0
     *     a*0 + b*1 = b = r1 with x1 = 0, y1 = 1
     * 
     *     In general, suppose there is:
     *     a*xi + b*yi = ri (1)
     *     a*x(i+1) + b*y(i+1) = r(i+1) (2)
     *     ri = q(i+1)*r(i+1) + r(i+2) (3)
     * 
     *  (1),(2),(3) => x(i+2) = xi - q(i+1)*x(i+1)
     *                 y(i+2) = yi - q(i+1)*y(i+1)
     *  Then i = m - 1, found result x(m+1), y(m+1)
     */
    const modInverseEuclid = (a, b, [x1, y1, x0, y0]) => {
        
        if(b == 0) return x0
         
        const q1 = Math.floor(a/b),
              x2 = x0 - q1*x1, 
              y2 = y0 - q1*y1;
              
        console.log(a, b, x2, y2, [x1, y1, x0, y0])
       
        return modInverseEuclid(b, a % b, [x2, y2, x1, y1])
    }
    
    // x0 = 1, y0 = 0, x1 = 0, y1 = 1, step i = 0
    return modInverseEuclid(n, m, [0, 1, 1, 0])
}


