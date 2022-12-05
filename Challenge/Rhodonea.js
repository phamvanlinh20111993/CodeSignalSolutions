/**
 * Yesterday Ben hacked the school's database and fired his CS teacher; he
 * couldn't solve a coding challenge that Ben sent him via email. The teacher
 * didn't appreciate it and locked Ben's access to any advanced software. 🤬
 * 
 * Your task is to help Ben with today's practice and print rhodonea curve using
 * terminal.
 * 
 * Use 100 by 100 grid. Magnitude is 45. Use ' ' as an empty cell, '@' as your
 * 'pen'. Change your θ with 0.01° (degrees; you can assume that by default it's
 * already in degrees) step, but don't forget to use radians with your
 * trigonometric functions.
 * 
 * Since most of the values are a float value, round it to the nearest integer.
 * Place it at the center of the grid.
 * 
 * [execution time limit] 16 seconds (js)
 * 
 * [input] integer n
 * 
 * [input] integer d
 * 
 * [output] array.string
 * 
 * 
 * 
 */



/**
 * Ref: https://en.wikipedia.org/wiki/Rose_(mathematics)
 * k = n/d
 * r = a*cos(k*I) <=>
 * 
 * x = r*cos(I) = a*cos(k*I)*cos(I)
 * y = r*sin(I) = a*cos(k*I)*sin(I)
 * 
 * 
 * I think we has a mistake here: 
 * with n = 7, and d = 1 the rose i checked in wiki is 7 pentals but the author's solution is 8 pentals
 */
solution = (n, d) => {
    const k = n/d
    const a = 45
    const toRadian = degree => degree*(Math.PI/180)
    
    const cx = radian => Math.round(a*Math.cos(k*radian)*Math.cos(radian))
    const cy = radian => Math.round(a*Math.cos(k*radian)*Math.sin(radian))
 
    const res = new Array(100).fill([]).map(arr => new Array(100).fill(' '))
    
    for(let de = -d*180; de <= d*180; de += 0.01){
        const x = cx(toRadian(de)) + 50
        const y = -cy(toRadian(de)) + 50
        res[y][x] = '@'
    }
    
    return res.map(r => r.join(''))
    
}

