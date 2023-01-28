/**
 * 
 */
closestLocation = (address, objects, names) => {
    
    distanceTwoPoint = (pointA, pointB) => Math.sqrt((pointA.x - pointB.x)**2 + (pointA.y - pointB.y)**2)
    
    // coordinate origin is (0, 0)
    distanceToPoint = point => distanceTwoPoint({x: 0, y : 0}, point)
    
    /**
	 * To find projection: - call projection of M is M', we have direction
	 * vector is MM' - MM' perpendicular with n of AB => AB*MM' vector = 0 (1) -
	 * but M' belong AB => AxM + ByM + c = 0 (2) => from (1) and (2) are
	 * inferred M'
	 * 
	 */
    findProjectionOfPointInStraightLine = (pointA, pointB) => {
        let directionsVector =  {x: pointB.x - pointA.x, y: pointB.y - pointA.y}
        let normalVector = {x: -directionsVector.y, y: directionsVector.x}
        
        let projection = {x: 0, y: 0}
        // liner: a(x-x0) + b(y - y0) = 0 <=> c = -a*x0 - b*y0
        let c = -normalVector.x * pointA.x - normalVector.y * pointA.y;
        projection.y = (-directionsVector.x * c) / (directionsVector.y * normalVector.x + directionsVector.x * normalVector.y);
        if( directionsVector.x != 0){
            projection.x = -(projection.y * directionsVector.y) / directionsVector.x;
        }
        
        return projection;
    }
    
    // AB lineSegment
    distanceToLineSegment = (pointA, pointB) => {
        let normalVector = {x: pointA.y - pointB.y, y: pointB.x - pointA.x}
        // In the case of a projection of a point on either end of a line
		// segment
        // distance between M(x0, y0) to line segment ax+by+c = 0 is: |ax0 + by0
		// + c|/sqrt(a^2 + b^2)
        let numerator = Math.abs(- normalVector.x * pointA.x - normalVector.y * pointA.y)
        let minDistance = numerator / Math.sqrt(normalVector.x**2 + normalVector.y**2)
        // Check that the projection of the point is at either end of the line
        let projection =  findProjectionOfPointInStraightLine(pointA, pointB)
        let distanceAMtoMB = distanceTwoPoint(projection, pointA) + distanceTwoPoint(projection, pointB);
        let distanceAB = distanceTwoPoint(pointA, pointB);
        
        if( distanceAMtoMB > distanceAB){
            minDistance = Math.min(distanceToPoint(pointA), distanceToPoint(pointB))
        }
        
        return minDistance;
    }
    
    isPlaceMatchDestination = (address, place) =>{
        address = address.toLowerCase()
        place = place.toLowerCase()
        
        isIdenticalPrefix = () => address == place
        
        isDifferOnlyOne = () => {
            if(place.length < address.length) return false
            // they differ only by one symbol not count the possition
            let c = 0;
            for(let ind = 0; ind < address.length; ind++){
                if(address[ind] !== place[ind])
                    c++
            }          
            return !(c > 1)
        }
        
        isDifferOneExtra = () => {
             if(place.length <= address.length) return false
             let pos = 0, c = 0
             for(ind = 0; ind < address.length; ind++){
                if(pos < place.length && address[ind] != place[pos]){
                    c++
                    continue;
                }
                pos++
             }
             
             return !(c > 1)
        }
        
        isMatchWithMissOne = () => {
            if(place.length <= address.length) return false
            let pos = 0, c = 0
            for(ind = 0; ind < address.length+1; ind++){
                if(pos < address.length && place[ind] != address[pos]){
                    c++
                    continue;
                }
                pos++
            }
             
            return !(c > 1)     
        }
        
        return isIdenticalPrefix() || isDifferOnlyOne() 
                || isDifferOneExtra() || isMatchWithMissOne()
    }
    
    let minPlace = {distance: 1000, name:""}
    for(let ind = 0; ind < names.length; ind++){
        if(isPlaceMatchDestination(address, names[ind])){
            let currentDistance = 0;
            
            if(objects[ind].length == 4){
                let pointA = {x: objects[ind][0], y: objects[ind][1]}
                let pointB = {x: objects[ind][2], y: objects[ind][3]}
                currentDistance = distanceToLineSegment(pointA, pointB)
            }else{
                let point = {x: objects[ind][0], y: objects[ind][1]}
                currentDistance = distanceToPoint(point)
            }
            if(minPlace.distance > currentDistance){
                minPlace.distance = currentDistance
                minPlace.name = names[ind]
            }
        }
    }
    
    return minPlace.name
}
