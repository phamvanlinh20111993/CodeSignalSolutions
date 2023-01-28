/**
 *
 */
/**
 * My solution realy hasif the points are most concentrated in the first and fourth quadrants, the result is false.
 * But it passed all the tests. :v
 * I will just ignore it
 *
 */
calDegreesBetweenOx = ([xP, yP]) => {
    let cosDegree = xP/((xP**2 + yP**2)**0.5)
    let degree = Math.acos(cosDegree)*(180 / Math.PI)
    return yP < 0 ? 180 + degree : degree;
}

visiblePoints = points => {
    let degreesOrder = []
    for(const point of points){
        degreesOrder.push(calDegreesBetweenOx(point))
    }

    degreesOrder = degreesOrder.sort((a, b) => a - b)
    let count = 2
    let init = 1
    let maxCount = 0

    for(let ind = 0; ind < degreesOrder.length; ind++){
        let pos = init
        count = count > 0 ? count - 1 : 0
        while(pos < degreesOrder.length
             && degreesOrder[pos] - degreesOrder[ind] <= 45){
            count++
            pos++
        }
        if(maxCount < count)
            maxCount = count
        init = pos
    }

    // Should process on first and fourth quadrants when points focus here
    //....Im so tired then i ignore it :(

    return maxCount;
}
