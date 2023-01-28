/**
 * Link: 
 * https://app.codesignal.com/arcade/code-arcade/cliffs-of-pain/iGBDQE3KjqbYyF8DH
 * 
 *
 * Title: 
 * John has always liked analog clocks more than digital ones. He's been
 * dreaming about turning his digital clock into an analog one for as long as he
 * can remember, and now he met you, a great programmer, so his dream is about
 * to come true.
 * 
 * The screen of his digital clock is a simple 17 × 17 rectangle of pixels. It
 * always shows four digits: the first two stand for hours and second two for
 * minutes (John's clock uses the 24-hour format). Each digit is located in a 17 ×
 * 4 rectangle, with the leftmost column always empty, and the other three
 * columns filled according to this pattern with the proper scaling:
 * 
 * 
 * 
 * After the first two digits there is a separating column containing one symbol
 * ':' at its center.
 * 
 * Now John asks you to make his clock show time in the format similar to analog
 * clocks. Here's how: an analog clock can be represented as a circle (the
 * clock's borders) and two segments (the clock's hands). To show it on the 17 ×
 * 17 screen, you should light the pixels on it so that the pixel with
 * coordinates (x, y) is enabled if and only if the minimal distance to the
 * circle or one of the segments is less than sqrt(0.5).
 * 
 * John wants you to implement the function that changes the digital
 * representation to the analog one as described above. Given a 17 × 17
 * rectangle dtime representing digital time representation, return the analog
 * representation of the same time.
 * 
 * Please note that for the early prototype you have to develop, both of the
 * clock's hands should have the same length.
 * 
 * Example
 * 
 * For
 * 
 * 
 * dtime = [ ['.', '*', '*', '*', '.', '.', '*', '.', '.', '.', '*', '*', '*',
 * '.', '*', '*', '*'], ['.', '*', '.', '*', '.', '.', '*', '.', '.', '.', '.',
 * '.', '*', '.', '*', '.', '*'], ['.', '*', '.', '*', '.', '.', '*', '.', '.',
 * '.', '.', '.', '*', '.', '*', '.', '*'], ['.', '*', '.', '*', '.', '.', '*',
 * '.', '.', '.', '.', '.', '*', '.', '*', '.', '*'], ['.', '*', '.', '*', '.',
 * '.', '*', '.', '.', '.', '.', '.', '*', '.', '*', '.', '*'], ['.', '*', '.',
 * '*', '.', '.', '*', '.', '.', '.', '.', '.', '*', '.', '*', '.', '*'], ['.',
 * '*', '.', '*', '.', '.', '*', '.', '.', '.', '.', '.', '*', '.', '*', '.',
 * '*'], ['.', '*', '.', '*', '.', '.', '*', '.', '.', '.', '.', '.', '*', '.',
 * '*', '.', '*'], ['.', '*', '.', '*', '.', '.', '*', '.', ':', '.', '*', '*',
 * '*', '.', '*', '.', '*'], ['.', '*', '.', '*', '.', '.', '*', '.', '.', '.',
 * '.', '.', '*', '.', '*', '.', '*'], ['.', '*', '.', '*', '.', '.', '*', '.',
 * '.', '.', '.', '.', '*', '.', '*', '.', '*'], ['.', '*', '.', '*', '.', '.',
 * '*', '.', '.', '.', '.', '.', '*', '.', '*', '.', '*'], ['.', '*', '.', '*',
 * '.', '.', '*', '.', '.', '.', '.', '.', '*', '.', '*', '.', '*'], ['.', '*',
 * '.', '*', '.', '.', '*', '.', '.', '.', '.', '.', '*', '.', '*', '.', '*'],
 * ['.', '*', '.', '*', '.', '.', '*', '.', '.', '.', '.', '.', '*', '.', '*',
 * '.', '*'], ['.', '*', '.', '*', '.', '.', '*', '.', '.', '.', '.', '.', '*',
 * '.', '*', '.', '*'], ['.', '*', '*', '*', '.', '.', '*', '.', '.', '.', '*',
 * '*', '*', '.', '*', '*', '*'] ] the output should be
 * 
 * 
 * timeASCIIRepresentation(dtime) = [ ['.', '.', '.', '.', '*', '*', '*', '*',
 * '*', '*', '*', '*', '*', '.', '.', '.', '.'], ['.', '.', '.', '*', '*', '.',
 * '.', '.', '.', '.', '.', '.', '*', '*', '.', '.', '.'], ['.', '.', '*', '*',
 * '.', '.', '.', '.', '.', '.', '.', '.', '.', '*', '*', '.', '.'], ['.', '*',
 * '*', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '*', '*', '*', '.'],
 * ['*', '*', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '*', '.', '.',
 * '*', '*'], ['*', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '*', '.',
 * '.', '.', '.', '*'], ['*', '.', '.', '.', '.', '.', '.', '.', '.', '.', '*',
 * '.', '.', '.', '.', '.', '*'], ['*', '.', '.', '.', '.', '.', '.', '.', '.',
 * '*', '.', '.', '.', '.', '.', '.', '*'], ['*', '.', '.', '.', '.', '.', '.',
 * '.', '*', '.', '.', '.', '.', '.', '.', '.', '*'], ['*', '.', '.', '.', '.',
 * '.', '.', '.', '*', '.', '.', '.', '.', '.', '.', '.', '*'], ['*', '.', '.',
 * '.', '.', '.', '.', '.', '*', '.', '.', '.', '.', '.', '.', '.', '*'], ['*',
 * '.', '.', '.', '.', '.', '.', '.', '*', '.', '.', '.', '.', '.', '.', '.',
 * '*'], ['*', '*', '.', '.', '.', '.', '.', '.', '*', '.', '.', '.', '.', '.',
 * '.', '*', '*'], ['.', '*', '*', '.', '.', '.', '.', '.', '*', '.', '.', '.',
 * '.', '.', '*', '*', '.'], ['.', '.', '*', '*', '.', '.', '.', '.', '*', '.',
 * '.', '.', '.', '*', '*', '.', '.'], ['.', '.', '.', '*', '*', '.', '.', '.',
 * '*', '.', '.', '.', '*', '*', '.', '.', '.'], ['.', '.', '.', '.', '*', '*',
 * '*', '*', '*', '*', '*', '*', '*', '.', '.', '.', '.'] ] (Enabled pixels are
 * painted red to make them more visible).
 * 
 * Here is the geometrical representation of an analog clock showing time 01:30.
 * Enabled pixel are painted red.
 * 
 * 
 * 
 * Input/Output
 * 
 * [execution time limit] 4 seconds (js)
 * 
 * [input] array.array.char dtime
 * 
 * Digital time representation, where dtime[x][y] is equal to '*' if the pixel
 * with coordinates (x, y) is enabled and '.' otherwise. It is guaranteed that
 * the given time is correct, and that dtime[8][8] = ':'.
 * 
 * Guaranteed constraints: dtime.length = 17, dtime[i].length = 17.
 * 
 * [output] array.array.char
 * 
 * Representation of the same time on the same rectangle, but in an analog clock
 * format.
 */

// global variable
const STAR = "*";

// is array [17][3] base on title
const getASCIINumber = (arrayNum = [[]]) => {
    
    let leftFirstStar = 0
    let leftSecondStar = 0
    let rightFirstStar = 0
    let rightSecondStar = 0
    let middleColumnStar = 0
    
    const arrL = arrayNum.length
    const haftFirstArrL = Math.ceil(arrL/2)
    const haftSecondArrL = haftFirstArrL - 1
    const middleColL = arrayNum[0].length

    arrayNum.map((c , index) => {
        leftFirstStar += index < haftFirstArrL && c[0] === STAR
        rightFirstStar += index < haftFirstArrL && c[arrayNum[0].length - 1] === STAR
        leftSecondStar += index >= haftFirstArrL && c[0] === STAR
        rightSecondStar += index >= haftFirstArrL && c[arrayNum[0].length - 1] === STAR
        middleColumnStar += c[Math.floor(middleColL/2)] === STAR
    })
    
    const topStar = arrayNum[0].filter(v => v === STAR).length
    const bottomStar = arrayNum[arrayNum.length - 1].filter(v => v === STAR).length
    const middleRowStar = arrayNum[haftFirstArrL-1].filter(v => v === STAR).length
        
    const ruleNumDefinitions = [leftFirstStar, leftSecondStar, rightFirstStar, rightSecondStar, middleColumnStar, topStar, bottomStar, middleRowStar]
    
   // console.log(ruleNumDefinitions.join("-"))
    
    const compareTwoArray = (arr1, arr2) => arr1.join(';') === arr2.join(';')
    
    const checkNumberZero = () => compareTwoArray(ruleNumDefinitions, [haftFirstArrL, haftSecondArrL, haftFirstArrL, haftSecondArrL, 2, middleColL, middleColL, 2])
    
    const checkNumberOne = () => compareTwoArray(ruleNumDefinitions, [0, 0, 0, 0, arrayNum.length, 1, 1, 1])

    const checkNumberTwo = () => compareTwoArray(ruleNumDefinitions, [2, haftSecondArrL, haftFirstArrL, 1, 3, middleColL, middleColL, middleColL])
    
    const checkNumberThree = () => compareTwoArray(ruleNumDefinitions, [2, 1, haftFirstArrL, haftSecondArrL, 3, middleColL, middleColL, middleColL])
    
    const checkNumberFour = () => compareTwoArray(ruleNumDefinitions, [haftFirstArrL, 0, haftFirstArrL, haftSecondArrL, 1, 2, 1, middleColL])
    
    const checkNumberFive = () => compareTwoArray(ruleNumDefinitions, [haftFirstArrL, 1, 2, haftSecondArrL, 3, middleColL, middleColL, middleColL])
    
    const checkNumberSix = () => compareTwoArray(ruleNumDefinitions, [haftFirstArrL, haftSecondArrL, 2, haftSecondArrL, 3, middleColL, middleColL, middleColL])
    
    const checkNumberSeven = () => compareTwoArray(ruleNumDefinitions, [1, 0, haftFirstArrL, haftSecondArrL, 1, middleColL, 1, 1])
    
    const checkNumberEight = () => compareTwoArray(ruleNumDefinitions, [haftFirstArrL, haftSecondArrL, haftFirstArrL, haftSecondArrL, 3, middleColL, middleColL, middleColL])
    
    const checkNumberNine = () => compareTwoArray(ruleNumDefinitions, [haftFirstArrL, 1, haftFirstArrL, haftSecondArrL, 3, middleColL, middleColL, middleColL])
    
    const arrayCheck = [checkNumberZero(), checkNumberOne(), checkNumberTwo(), checkNumberThree(), checkNumberFour(), checkNumberFive(), checkNumberSix(), checkNumberSeven(), checkNumberEight(), checkNumberNine()]
    
 //   console.log(arrayCheck.join(";"))
    return arrayCheck.findIndex(v => v === true)
}

const calClockHands = (h, m) => {
    const CENTER = [8, 8]
    const RADIUS = CENTER[0]
    const twelveAndZeroHourCoord = [CENTER[0], 0]
    let res = [[]]
    
    /**
     * calculate coord follow triangle when know 2 points and angle 
     */
    let calCoord = ([a, b], e, [c, d], f, isGreater180) =>{
        const k = a**2 - c**2 + b**2 - d**2 + f**2 - e**2
        const m = (a-c)/(d-b)
        const t = -k/(2*(d-b))
        // https://vndoc.com/cach-tinh-delta-va-delta-phay-phuong-trinh-bac-2-7317
        let deltaCollapse = (m*(t-d)-c)**2 - (1+m**2)*(c**2 + (t-d)**2 - f**2)
        console.log('deltaCollapse', deltaCollapse)
        deltaCollapse = Math.sqrt(deltaCollapse);
        const x1 = (c - m*(t-d) + deltaCollapse)/(1 + m**2)
        const x2 = (c - m*(t-d) - deltaCollapse)/(1 + m**2)
        
        console.log('x1, x2', x1, x2)
        const x = isGreater180 ? Math.min(x1, x2) : Math.max(x1, x2)
        const y = (2*(a - c)*x - k) / (2*(d - b)) 
        
        return [x, y]
    }
    
    const toRadians = angle => angle * (Math.PI / 180)
    // https://thapsang.vn/cach-van-dung-dinh-ly-cosin-trong-tam-giac
    const calSideByAngle = (angle, a, b) => Math.sqrt(a**2 + b**2 - 2*a*b*Math.cos(toRadians(angle)))
    
    const getMinuteHandApexCoord = () => {
        let minuteAngle = (360/60) * m
        const isMinuteAngleGreater180 = minuteAngle >= 180
        minuteAngle = minuteAngle > 180 ? 360 - minuteAngle : minuteAngle
        console.log('minuteAngle', minuteAngle)
        const distStartToMinuteCoord = calSideByAngle(minuteAngle, CENTER[0], CENTER[0])
        console.log(CENTER, RADIUS, twelveAndZeroHourCoord, distStartToMinuteCoord)
        const minuteHandCoord = calCoord(CENTER, RADIUS, twelveAndZeroHourCoord, distStartToMinuteCoord, isMinuteAngleGreater180)
        return minuteHandCoord
    }
   
    const getHourHandApexCoord = () => {
        let hourAngle = (360/12) * (h % 12) + m/2
        const isHourAngleGreater180 = hourAngle >= 180
        hourAngle = hourAngle > 180 ? 360 - hourAngle : hourAngle
        console.log('hourAngle', hourAngle)
        const distStartToHourCoord =  calSideByAngle(hourAngle, CENTER[0], CENTER[0])
        console.log(CENTER, RADIUS, twelveAndZeroHourCoord, distStartToHourCoord)
        let hourHandCoord = calCoord(CENTER, RADIUS, twelveAndZeroHourCoord, distStartToHourCoord, isHourAngleGreater180)
        return hourHandCoord
    }
    
    const getSetLineCoords = ([x, y]) => {
        // build a,b,c factors for line ax + by + c = 0
        // a, b is coords of n(a, b) normal vector
        const a = CENTER[1] - y
        const b = x - CENTER[0] 
        const c = -a * CENTER[0] - b * CENTER[1]
        const LINE_PIXCEL_MAX_DIST = +(Math.sqrt(2)/2).toFixed(5)
        /**
         * As title: distance from M(x, y) to minute hands less than l = sqrt(0.5)
         * with x belong [center[0], minuteHandApex[0]]
         * d = sqrt(a*a + b*b)
         * => Check:  0 <= |(ax + by + c) / d| < l
         * Or we can break math abs to check in equality. |a| < 1 <=> a < 1 || -a < 1.
         *  
         * y max belong from coord [0, 17]
         * Reference: https://vietjack.com/toan-lop-10/tinh-khoang-cach-tu-mot-diem-den-mot-duong-thang.jsp
         */
        
        const d = Math.sqrt(a**2 + b**2)
        console.log('a, b, c, d', a, b, c, d)
        
        const minX = Math.round(Math.min(CENTER[0], x))
        const maxX = Math.ceil(Math.max(CENTER[0], x))
        let setLineCoords = []
        for(let x = minX; x <= maxX; x++){
           /* let leftCond = +(((-a*x - c)/b).toFixed(5))
            leftCond = leftCond < 0 ? 0 : leftCond
            
            const rightCondPos = +(((LINE_PIXCEL_MAX_DIST*d - c - a*x)/b).toFixed(5))
            const rightCondNe = +((-(LINE_PIXCEL_MAX_DIST*d - c - a*x)/b).toFixed(5))
            
            let rightCond = Math.max(rightCondPos, rightCondNe)
            rightCond = rightCond > 17 ? 17: rightCond
            
            console.log('leftCond, rightCond = ', leftCond, rightCond)
            for(let y = Math.floor(leftCond); y < rightCond; y++){
                setLineCoords.push([x, y])
                console.log('x, y = ', x, y)
            } */
            
        	// CENTER[0] = 8
            const minY = y >= CENTER[0] ? CENTER[0] : 1
            const maxY = y >= CENTER[0] ? 16 : CENTER[0] + 1
            for(let y = minY; y < maxY; y++){
                const distance = +Math.abs((a*x + b*y + c) / d).toFixed(5)
                if(distance >= 0 && distance < LINE_PIXCEL_MAX_DIST){
                    setLineCoords.push([x, y])
                    console.log('x, y , d = ', x, y, distance, LINE_PIXCEL_MAX_DIST)
                }
            }
        }
        
        return setLineCoords
    }
   
    const minuteHandApexCoord = getMinuteHandApexCoord()
    console.log('minuteHandApexCoord', minuteHandApexCoord)
    const setLineMinuteHandCoords = getSetLineCoords(minuteHandApexCoord)
    console.log('setLineMinuteHandCoords', setLineMinuteHandCoords)
    
    const hourHandApexCoord = getHourHandApexCoord()
    console.log('hourHandApexCoord', hourHandApexCoord)
    const setLineHourHandCoords = getSetLineCoords(hourHandApexCoord)
    console.log('setLineHourHandCoords', setLineHourHandCoords)
   
    return [...setLineMinuteHandCoords, ...setLineHourHandCoords]
}

const fillToTimeASCII = coords => {
	// Using another way: points that are not outside the circle's radius 
    const coordsOutFrame1 = [[0, 0], [1, 0], [2, 0], [3, 0], [0, 1], [1, 1], [2, 1], [0, 2], [1, 2], [0, 2]]
    const coordsOutFrame2 = coordsOutFrame1.map(v => [16 - v[0], v[1]])
    const coordsOutFrame3 = coordsOutFrame1.map(v => [16 - v[0], 16 - v[1]])
    const coordsOutFrame4 = coordsOutFrame1.map(v => [v[0], 16 - v[1]])
    const coordsOutFrame = [...coordsOutFrame1, ...coordsOutFrame2, ...coordsOutFrame3, ...coordsOutFrame4]
    const coordsOutFrameSet = new Set([...coordsOutFrame.map(v =>`${v[0]}-${v[1]}`)])
    
    const coordsSet = new Set([...coords.map(v =>`${v[0]}-${v[1]}`)])
    
    const CLOCK_FRAME = 
       [['.', '.', '.', '.', '*', '*', '*', '*', '*', '*', '*', '*', '*', '.', '.', '.', '.'],
        ['.', '.', '.', '*', '*', '.', '.', '.', '.', '.', '.', '.', '*', '*', '.', '.', '.'],
        ['.', '.', '*', '*', '.', '.', '.', '.', '.', '.', '.', '.', '.', '*', '*', '.', '.'],
        ['.', '*', '*', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '*', '*', '.'],
        ['*', '*', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '*', '*'],
        ['*', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '*'],
        ['*', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '*'],
        ['*', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '*'],
        ['*', '.', '.', '.', '.', '.', '.', '.', '*', '.', '.', '.', '.', '.', '.', '.', '*'],
        ['*', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '*'],
        ['*', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '*'],
        ['*', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '*'],
        ['*', '*', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '*', '*'],
        ['.', '*', '*', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '*', '*', '.'],
        ['.', '.', '*', '*', '.', '.', '.', '.', '.', '.', '.', '.', '.', '*', '*', '.', '.'],
        ['.', '.', '.', '*', '*', '.', '.', '.', '.', '.', '.', '.', '*', '*', '.', '.', '.'],
        ['.', '.', '.', '.', '*', '*', '*', '*', '*', '*', '*', '*', '*', '.', '.', '.', '.']]
    
    console.log('coordsOutFrameSet', coordsOutFrameSet)
  return CLOCK_FRAME.map((r, y) => r.map((v, x) => coordsSet.has(`${x}-${y}`) && !coordsOutFrameSet.has(`${x}-${y}`) ? STAR : v))
}

//Maybe reference: https://voer.edu.vn/m/cac-thuat-toan-to-mau/3b801df6
let timeASCIIRepresentation = dtime => {
    const firstHourNum = getASCIINumber(dtime.map(v => [v[1], v[2], v[3]]))
    const secondHourNum = getASCIINumber(dtime.map(v => [v[5], v[6], v[7]]))
    const firstMinuteNum = getASCIINumber(dtime.map(v => [v[10], v[11], v[12]]))
    const secondMinuteNum = getASCIINumber(dtime.map(v => [v[14], v[15], v[16]]))
    
    console.log(firstHourNum * 10 + secondHourNum, firstMinuteNum * 10 + secondMinuteNum) 
    return fillToTimeASCII(calClockHands(firstHourNum*10 + secondHourNum, firstMinuteNum*10 + secondMinuteNum))
}
