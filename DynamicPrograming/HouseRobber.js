/**
 * 
 */

/*let maxVal
robHouse = (nums, houseNumber, value) =>{
    for(let i = houseNumber + 2; i < nums.length; i++)
        robHouse(nums, i, value + nums[i]);

    if(maxVal < value){
        maxVal = value;
    }
}

function houseRobber(nums) {
    let maxValS = 0;
    maxVal = 0;
    robHouse(nums, 0, nums[0])
    maxValS = maxVal;
    maxVal = 0;
    robHouse(nums, 1, nums[1])
    return maxValS > maxVal ? maxValS : maxVal;
} */


// reference fomula from  https://www.geeksforgeeks.org/find-maximum-possible-stolen-value-houses/

// robHouse(i) = max(val[i] +robHouse(i-2, j), robHouse(i-1)))

let storeMaxRob;
robHouse  = (nums, houseNumber) => {
 //    console.log('val1', houseNumber)

  console.log('fic', houseNumber)
    if(houseNumber > -1){
        let storeV = 0, chooseRobNext = 0;
        if(houseNumber-2 > - 1){
            if(storeMaxRob[houseNumber-2] != -1){
                storeV = storeMaxRob[houseNumber-2];
            }else{
                storeMaxRob[houseNumber-2] = storeV = robHouse(nums, houseNumber - 2);
            }
        }

        if(houseNumber-1 > - 1){
            if(storeMaxRob[houseNumber-1] != -1){
                chooseRobNext = storeMaxRob[houseNumber - 1];
            }else{
                storeMaxRob[houseNumber-1] = chooseRobNext = robHouse(nums, houseNumber - 1);
            }
        }

        let chooseRob = nums[houseNumber] + storeV;
        return chooseRob > chooseRobNext ? chooseRob: chooseRobNext;
    }else{
        return 0
    }
}

function houseRobber(nums) {
    storeMaxRob = new Array(nums.length).fill(-1);
    return robHouse(nums, nums.length-1);
}
