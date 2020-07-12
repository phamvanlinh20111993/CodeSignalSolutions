/**
 * 
 */
parkingSpot = (carDimensions, parkingLot, luckySpot) => {
    
    checkValidLuckySpot = () => {
        for(let pos = luckySpot[0]; pos <= luckySpot[2]; pos++){
            for(let ind = luckySpot[1]; ind <= luckySpot[3]; ind++){
                if(parkingLot[pos][ind] > 0)
                    return false
            }
        }
                
        return true
    }
    
    checkLeftValidEntrance = () => {
        for(let ind = luckySpot[0]; ind <= luckySpot[2]; ind++){
            let flag = true;
            if(ind + carDimensions[1] - 1 <= luckySpot[2]){
                for(let pos = ind; pos < ind + carDimensions[1]; pos++){
                    if(parkingLot[pos][0] > 0){
                        flag = false;
                        break;
                    }
                }             
                if(flag && carDimensions[0] <= luckySpot[3] - luckySpot[1] + 1){
                    //check path in between entrance and luckyspot
                    for(let index = ind; index < ind + carDimensions[1]; index++){
                        for(let position = 0; position < luckySpot[1]; position++)
                            if(parkingLot[index][position] > 0)
                                return false;
                    }
                    return true;
                }
            }     
        }
        
        return false
    } 
    
    checkRightValidEntrance = () => {
         for(let ind = luckySpot[0]; ind <= luckySpot[2]; ind++){
            let flag = true;
            if(ind + carDimensions[1] - 1 <= luckySpot[2]){
                for(let pos = ind; pos < ind + carDimensions[1]; pos++){
                    if(parkingLot[pos][parkingLot[0].length-1] > 0){
                        flag = false;
                        break;
                    }
                }               
                if(flag && carDimensions[0] <= luckySpot[3] - luckySpot[1] + 1){
                    //check path in between entrance and luckyspot
                    for(let index = ind; index < ind + carDimensions[1]; index++){
                        for(let position = parkingLot[0].length - 1; position > luckySpot[3]; position--)
                            if(parkingLot[index][position] > 0)
                                return false;
                    }
                    return true;
                }
            }     
        }
        
        return false
    } 
    
    checkUpValidEntrance = () => {
         for(let ind = luckySpot[1]; ind <= luckySpot[3]; ind++){
            let flag = true;
            if(ind + carDimensions[1] - 1 <= luckySpot[3]){
                for(let pos = ind; pos < ind + carDimensions[1]; pos++){
                    if(parkingLot[0][pos] > 0){
                        flag = false;
                        break;
                    }
                }               
                if(flag && carDimensions[0] <= luckySpot[2] - luckySpot[0] + 1){
                     //check path in between entrance and luckyspot
                    for(let index = ind; index < ind + carDimensions[1]; index++){
                        for(let position = 0; position < luckySpot[0]; position++)
                            if(parkingLot[position][index] > 0)
                                return false;
                    }
                    return true;
                }
            }     
        }
        
        return false
    } 
    
     checkDownValidEntrance = () => {
         for(let ind = luckySpot[1]; ind <= luckySpot[3]; ind++){
            let flag = true;
            if(ind + carDimensions[1] - 1 <= luckySpot[3]){
                for(let pos = ind; pos < ind + carDimensions[1]; pos++){
                    if(parkingLot[parkingLot.length-1][pos] > 0){
                        flag = false;
                        break;
                    }
                }               
                if(flag && carDimensions[0] <= luckySpot[2] - luckySpot[0] + 1){
                     //check path in between entrance and luckyspot
                    for(let index = ind; index < ind + carDimensions[1]; index++){
                        for(let position = parkingLot.length - 1; position > luckySpot[2]; position--)
                            if(parkingLot[position][index] > 0)
                                return false;
                    }
                    return true;
                }
            }     
        }
        
        return false
    } 
    
    checkValidEntrance = () => checkLeftValidEntrance() || checkRightValidEntrance() 
                               || checkUpValidEntrance() || checkDownValidEntrance()
    
    return checkValidLuckySpot() && checkValidEntrance()
}
