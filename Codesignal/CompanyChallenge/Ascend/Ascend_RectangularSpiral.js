/**
 * You're given two opposite vertices of an axis-aligned rectangle, and your
 * task is to write a function rectangularSpiral, which takes two arguments:
 * 
 * firstPoint - the coordinates of one corner of the rectangle, and the starting
 * point of the spiral path to be drawn secondPoint - the coordinates of the
 * corner of the rectangle opposite to the starting point. The function should
 * build a clockwise rectangular spiral that covers the rectangle enclosed
 * within firstPoint and secondPoint (see the example for a better
 * understanding). Note: firstPoint may not be the upper left corner of the
 * rectangle, but in all cases firstPoint and secondPoint will be opposite
 * corners of the rectangle, i.e. all points in the rectangle (x, y) may be
 * written using the following inequalities:
 * 
 * min(firstPoint[0], secondPoint[0]) <= x <= max(firstPoint[0], secondPoint[0])
 * min(firstPoint[1], secondPoint[1]) <= y <= max(firstPoint[1], secondPoint[1])
 * Also, note that the side lengths of the rectangle may be equal to zero.
 * 
 * The coordinate system of the rectangle is similar to the SVG coordinate
 * system: [0, 0] is the upper left corner, the positive x axis is directed to
 * the right and the positive y axis is directed down.
 * 
 * Your task is to return a list of coordinates [x, y] representing all the
 * corners of the spiral, in the order in which you'd encounter them, starting
 * from firstPoint. Resulting list should contain at least 2 elements, where the
 * first element equals firstPoint and the last element equals secondPoint.
 * 
 * Example
 * 
 * For firstPoint = [0, 0] and secondPoint = [4, 3], the output should be
 * rectangularSpiral(firstPoint, secondPoint) = [[0, 0], [4, 0], [4, 3], [0, 3],
 * [0, 1], [3, 1], [3, 2], [1, 2]]
 * 
 * 
 * For firstPoint = [1, 3] and secondPoint = [3, 1], the output should be
 * rectangularSpiral(firstPoint, secondPoint) = [[1, 3], [1, 1], [3, 1], [3, 3],
 * [2, 3], [2, 2]]
 * 
 * 
 * For firstPoint = [5, 4] and secondPoint = [5, 4], the output should be
 * rectangularSpiral(firstPoint, secondPoint) = [[5, 4], [5, 4]]. Input/Output
 * 
 * [execution time limit] 4 seconds (js)
 * 
 * [input] array.integer firstPoint
 * 
 * [x, y] coordinates of the starting point.
 * 
 * Guaranteed constraints: firstPoint.length = 2, 0 ≤ firstPoint[0] ≤ 100, 0 ≤
 * firstPoint[1] ≤ 100.
 * 
 * [input] array.integer secondPoint
 * 
 * [x, y] coordinates of the finishing point, opposite corner of the rectangle
 * relative to firstPoint.
 * 
 * Guaranteed constraints: secondPoint.length = 2, 0 ≤ secondPoint[0] ≤ 100, 0 ≤
 * secondPoint[1] ≤ 100.
 * 
 * [output] array.array.integer
 * 
 * A list of [x, y] coordinates which define the path from the starting point up
 * to and including the finishing point. You do not need to list every point
 * that the path will go through, just the corners of the spiral (i.e.: the
 * endpoints of each line segment).
 */


/**
 * This is not good solution because it use a map to save then check any point
 * visited, waste memory. Im use another solution use in java but still two
 * complicate, because use many if else commands hm, anyway, i passed =)), i
 * will improve performance when have time
 */
let rectangularSpiral = (firstPoint, secondPoint) => {
		// (y, x) <=> (i, j), clockwise direction
		const DIRECTION = [[1, 0], [0, 1], [-1, 0], [0, -1]];
		const CYCLE = 4;
		
		let [x, y] = firstPoint
		let [x1, y1] = secondPoint
		let isVisited = new Map();
        
        if(x == x1 && y == y1) return [firstPoint, secondPoint]
        
		const getStartDirection = () =>{
			// firstPoint and secondPoint direction vector
			let nfs = [x1 - x, y1 - y]
			// x direction vector
			let nx = [1, 0]
			// y direction vector
			let ny = [0, -1]
			let distance = Math.sqrt(nfs[0] * nfs[0] + nfs[1] * nfs[1])
			let cosNfsNx = distance == 0 ? 0 : nfs[0] / distance
			let cosNfsNy = distance == 0 ? 0 : nfs[1] / distance
			let angleNfsNx = Math.acos(cosNfsNx) * (180 / Math.PI)
			let angleNfsNy = Math.acos(cosNfsNy) * (180 / Math.PI)
			let startDirect = 0;
            
			if(angleNfsNx >= 90 && angleNfsNy < 90) startDirect = 1;	
			if(angleNfsNx > 90 && angleNfsNy >= 90) startDirect = 2;			
			if(angleNfsNx <= 90 && angleNfsNy > 90) startDirect = 3;
			
			return startDirect == 0 ? 3 : startDirect - 1;
		}
		
		const startSpiralBound = () => {
            return [[firstPoint[0], firstPoint[1]], [secondPoint[0], secondPoint[1]], 
            [secondPoint[0], firstPoint[1]], [firstPoint[0], secondPoint[1]]]
		}
		
		let minX = 1000, minY = 1000, maxX = -1, maxY = -1
		startSpiralBound().map(v => {
			minX = minX > v[0] ? v[0] : minX
			maxX = maxX < v[0] ? v[0] : maxX
			minY = minY > v[1] ? v[1] : minY
			maxY = maxY < v[1] ? v[1] : maxY
		})
		const isInRange = (x, y) =>{
			return x >= minX && x <= maxX &&  y >= minY && y <= maxY
		}

		const getNextDirection = ([x, y], currentDirect) =>{
			let isChange = false
			startSpiralBound().map(v => isChange = (v[0] == x && v[1] == y) ? true : isChange)
			let store  = [x, y]
			
			let nextX = -1, nextY = -1;
			if(!isChange){
				nextX = x + DIRECTION[currentDirect][0]
				nextY = y + DIRECTION[currentDirect][1]
				// next cycle of spiral
				if(isVisited.has(`${nextX}-${nextY}`)){
					isChange = true
				}
			}
			
			return {isChange, 
				next: isChange ? (currentDirect + 1) % CYCLE : currentDirect,
				store
			}
		}
		
		const isEndSpiral = ([x, y], currentDirect)=> {
			let x1 = x + DIRECTION[currentDirect][0]
			let y1 = y + DIRECTION[currentDirect][1]

			if(isVisited.has(`${x1}-${y1}`) || !isInRange(x1, y1)){
				let nextDirect = (currentDirect + 1) % CYCLE
				let nextX = x + DIRECTION[nextDirect][0]
				let nextY = y + DIRECTION[nextDirect][1]
				if(isVisited.has(`${nextX}-${nextY}`)){
					return true
				}
			}
			// is out of start box
			return false
		}
		
		let isEnd = false;
		let res = []
        isVisited.set(`${x}-${y}`, 1)
		let currentDirect = getStartDirection(); 
		
		let count = 0;
		while(!isEnd){
			let currentD = getNextDirection([x, y], currentDirect)
			
			if(!isInRange(x, y)) break;
			
            isVisited.set(`${x}-${y}`, 1)	
			if(currentD.isChange){
				res.push(currentD.store)
				currentDirect = currentD.next
			}
			x += DIRECTION[currentDirect][0]
			y += DIRECTION[currentDirect][1]
			isEnd = isEndSpiral([x, y], currentDirect)
			if(isEnd){
				res.push([x, y])
			}
		}	
		return res;
	}