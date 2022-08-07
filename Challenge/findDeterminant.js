/**
 *  https://app.codesignal.com/challenge/cr6YHsSLk5HCCPiK5
 */

// https://mathworld.wolfram.com/Determinant.html
// https://math.stackexchange.com/questions/319321/understanding-the-leibniz-formula-for-determinants#:~:text=The%20formula%20says%20that%20det,permutation%20get%20a%20minus%20sign.&text=where%20the%20minus%20signs%20correspond%20to%20the%20odd%20permutations%20from%20above.
// https://vi.wikipedia.org/wiki/Ph%C3%A9p_kh%E1%BB%AD_Gauss

// https://vi.wikipedia.org/wiki/%C4%90%E1%BB%8Bnh_th%E1%BB%A9c
/*solution = matrix => {
    
    const totalP = n => {
        let a = [], isVisited = new Array(n).fill(false)
        const tP = (i, n, s) => {
            
            if(s.length == n){
                a.push(s)
            }
            
            for(let ind = 0; ind < n; ind++){
                if(!isVisited[ind]){
                    isVisited[ind] = true
                    ind < n && tP(ind + 1, n, s + ind)
                    isVisited[ind] = false
                }
            }
        }
        tP(0, n, '')
        return a
    }
    
    let t = totalP(matrix.length)
    t = t.map(v => v.split('').map((d, i) => i+d))
    let re = 0
    const mapI = new Array(matrix.length).fill(0).map((v, i) => i)
    
    
    const calSign = arr => {
        let t = arr.map((v, i) => arr[i] === mapI[i])
        console.log('t', t)
        let co = t.filter(v => v === false).length
        return  co > 0 && co % 2 == 0 ? -1 : 1
    }
    
    console.log(t, mapI)
    
    t.map((r, i) => {
        let te = 1, co = []
        r.map((c, p) => {
            const s = c.split('')
            te *= matrix[+s[0]][+s[1]]
            co.push(+s[1])
        })
        
        
        console.log(te, calSign(co))
        
        re += te * calSign(co)
    })
    
    return re
} */


// http://tailieudientu.lrc.tnu.edu.vn/chi-tiet/phuong-phap-khu-gauss-giai-he-phuong-trinh-dai-so-tuyen-tinh-44432.html
/*solution = matrix => {
    
    const det = m => {
        const newArr = (r, c) =>  Array(r).fill([]).map(v => new Array(c).fill(0))
                  
        const detDefault = ma => {
            let re = 0
            
            for(let t = 0; t < ma.length; t++){
                let val = ma[0][t],
                    nArr = newArr(ma.length - 1, ma.length - 1)       
                if(val == 0) continue
                    
                for(let i = 1; i < ma.length; i++){
                    for(let j = 0, k = 0; j < ma[0].length; j++){
                        if(j == t) continue
                        nArr[i-1][k++] = ma[i][j]
                    }
                }    
                re += (-1)**t * val * det(nArr)
            }
            
            return re
        }
        
        if(m.length < 2 && m[0].length < 2){
            return m[0][0]
        }
        
        if(m.length == 2 && m[0].length == 2){
            return m[0][0] * m[1][1] - m[1][0] * m[0][1]
        }
        
        return detDefault(m)
    }
    
    const gaussianElimination = m => {
        
        if(m.length < 3) return m
    
        const findMaxAtRow = (c, r = [0, 1]) => {
            // find max row at column c
            let maxAtRow = -10000000, row = 0
            for(let i = r[0]; i <= r[1]; i++){
                if(maxAtRow < m[i][c]){
                    maxAtRow = m[i][c]
                    row = i
                }
            }
            
            return row
        }
        
        const swapMaxRow = (r, rsw) => {
            for(let ind = 0; ind < m.length; ind++){
                const t = m[r][ind]
                m[r][ind] = m[rsw][ind]
                m[rsw][ind] = t
            }
            
            return m
        }   
        // implement gauss logic
        // http://tailieudientu.lrc.tnu.edu.vn/chi-tiet/phuong-phap-khu-gauss-giai-he-phuong-trinh-dai-so-tuyen-tinh-44432.html
        let r = m.length - 1, c = m[0].length - 1
        while(r > 0 && c > 0){
         //   m = swapMaxRow(r, findMaxAtRow(c, [0, r])) 
        //    console.log('m', m)
            if(m[r][c] == 0){ 
                r -- 
                c --
                continue 
            }
            
            for(let ind = r - 1; ind >= 0; ind--){
                if(m[ind][c] == 0)  continue
                const val = -m[ind][c] / m[r][c]
                
                for(let p = c; p >= 0; p--){
                    m[ind][p] += val * m[r][p]
                }
            }
            r -- 
            c --
         //   console.log('m-after', m)
        }
        
        return m
    }
    
    const mGE = gaussianElimination(matrix)
    const realV = det(mGE) 
    console.log('realV', realV)
    return Math.round(realV)
} */



/**
 * [[4,-2,-7,3,5], 
    [0,-4,2,0,0], 
    [7,-5,-6,4,-8], 
    [5,-3,5,2,-3], 
    [0,-1,9,-1,2]] = 622
 * 
 * [[0,-1,9,-1,2], 
    [0,-4,2,0,0], 
    [7,-5,-6,4,-8], 
    [5,-3,5,2,-3], 
    [4,-2,-7,3,5]] = -622
 * 
 * 
 */
solution = matrix => {
    
    const det = m => {
        const newArr = (r, c) =>  Array(r).fill([]).map(v => new Array(c).fill(0))
                  
        const detDefault = ma => {
            let re = 0
            
            for(let t = ma.length - 1; t >= 0; t--){
                let val = ma[ma.length-1][t],
                    nArr = newArr(ma.length - 1, ma.length - 1)       
                if(val == 0) continue
                    
                for(let i = 0; i < ma.length - 1; i++){
                    for(let j = 0, k = 0; j < ma[0].length; j++){
                        if(j == t) continue
                        nArr[i][k++] = ma[i][j]
                    }
                }                               // row i + col j, i,j start from index 1
                re +=  val * det(nArr) * (-1)**(t + ma.length + 1)
          //      re +=  val * det(nArr)
            }
            
            return re
        }
        
        if(m.length < 2 && m[0].length < 2){
            return m[0][0]
        }
        
        if(m.length == 2 && m[0].length == 2){
            return m[0][0] * m[1][1] - m[1][0] * m[0][1]
        }
        
        return detDefault(m)
    }
    
    const gaussianElimination = m => {
        
        if(m.length < 3) return m
    
        const findMaxAtRow = (c, r = [0, 1]) => {
            // find max row at column c
            let maxAtRow = -10000000, row = 0
            for(let i = r[0]; i <= r[1]; i++){
                if(maxAtRow < m[i][c]){
                    maxAtRow = m[i][c]
                    row = i
                }
            }
            
            return row
        }
        
        const swapMaxRow = (r, rsw) => {
            for(let ind = 0; ind < m.length; ind++){
                const t = m[r][ind]
                m[r][ind] = m[rsw][ind]
                m[rsw][ind] = t
            }
            
            return m
        }   
        // implement gauss logic
        // http://tailieudientu.lrc.tnu.edu.vn/chi-tiet/phuong-phap-khu-gauss-giai-he-phuong-trinh-dai-so-tuyen-tinh-44432.html
        let r = 0, c = 0
        while(r <  m.length - 1 && c < m[0].length - 1){
        //    m = swapMaxRow(r, findMaxAtRow(c, [r, m[0].length - 1])) 
        //   console.log('m', m)
            if(m[r][c] == 0){ 
                r ++ 
                c ++
                continue 
            }
            
            for(let ind = r + 1; ind <= m.length - 1; ind ++){
                if(m[ind][c] == 0)  continue
                const val = -m[ind][c] / m[r][c]
                
                for(let p = c; p <= m[0].length - 1; p++){
                    m[ind][p] += val * m[r][p]
                }
            }
            r ++ 
            c ++
        //    console.log('m-after', m)
        }
        
        return m
    }
    
    const mGE = gaussianElimination(matrix)
    const realV = det(mGE) 
    return Math.round(realV)
}