/**
 *The FDA recommends that for a healthy, balanced diet, a person on average needs around 2,000 Kcal a day to maintain their weight. As a result, Instacart is set to release a new feature that will help customers control their daily intake of calories. Given a list of items in a customer's cart, it will show the items that can be consumed in one day such that their total caloric value is as close to 2000 as possible.

Knowing the caloricValue of each bought item, return the 0-based indices of the items to be consumed in one day. If there is more than one option, return the lexicographically smallest one.

Example

For caloricValue = [400, 800, 400, 500, 350, 350], the output should be
dailyIntake(caloricValue) = [0, 2, 3, 4, 5].

Caloric value of items [1, 3, 4, 5] and [0, 2, 3, 4, 5] both sum up to 2000 but since [0, 2, 3, 4, 5] is lexicographically smaller than [1, 3, 4, 5], the answer is [0, 2, 3, 4, 5].

For caloricValue = [150, 900, 1000], the output should be
dailyIntake(caloricValue) = [0, 1, 2].

The total sum of all items (i.e. 2050) is 50 Kcal larger than 2000, so the answer is [0, 1, 2].

Input/Output

[execution time limit] 4 seconds (js)

[input] array.integer caloricValue

Caloric value of each item in the cart. The total sum of all items is not greater than 104.

Guaranteed constraints:
1 ≤ caloricValue.length ≤ 30,
2 ≤ caloricValue[i] ≤ 104.

[output] array.integer

The items to consume in a day.
 */


/**
 * return 1 if str > str1 0 if str = str1 -1 if str < str1
 */
let compareTwoData = (str, str1) => {
  // console.log('valye', str, str1)
    let arr = str.split("-").map(v => parseInt(v))
    let arr1 = str1.split("-").map(v => parseInt(v))

    let pos = 0
    for(let ind = 0; ind < arr.length; ind++){
        if(pos < arr1.length){
            if(arr[ind] > arr1[pos]){
                return 1;
            }
            if(arr[ind] < arr1[pos]){
                return -1;
            }
        }
        pos++
    }

    return arr.length > arr1.length ? 1 : arr.length == arr1.length ? 0 : -1
}


let getSmallestLexicographically = (resMap, maxInMinValue) => {
    let mapAsc = new Map([...resMap.entries()].sort((a, b) => parseInt(a)-parseInt(b)));
    let keyList = Array.from(mapAsc.keys())
    let keyMaxData = keyList[keyList.length-1]
    let response = []
    if(Math.abs(keyMaxData - 2000) == Math.abs(maxInMinValue[0] - 2000)){
        response = (compareTwoData(maxInMinValue[1], resMap.get(keyMaxData)[0]) > 0
                       ? resMap.get(keyMaxData): maxInMinValue[1]).split("-").map(v => parseInt(v))

    }else if(Math.abs(keyMaxData - 2000) < Math.abs(maxInMinValue[0] - 2000)){
        response = resMap.get(keyMaxData)[0].split("-").map(v => parseInt(v))
    }else{
        response = maxInMinValue[1].split("-").map(v => parseInt(v))
    }

    return response
}

dailyIntake = c => {

    if(c.length == 1){
        if(c[0] > 2000)
            return []

        return [0]
    }

    let maxInMinValue = [11000, "10000-10000"]
    c.map((v, index) => {
        if(maxInMinValue[0] > v && v > 2000){
            maxInMinValue = [v, index+""]
        }else if(maxInMinValue[0] == v && compareTwoData(maxInMinValue[1], index+"") > 0){
           maxInMinValue[1] = index+""
        }
    })

    let checkValidNumInArr = (str, num) => {
		let dataMap = new Set()
        str.split("-").map(v => dataMap.add(v))
		if(dataMap.has(num + "") || dataMap.has(num)){
		    return false
		}
		return true
	}

    let answerTotal = new Set()
	checkSumExistOnArray = (arr, sum) =>{

	  let dp = index => {
		if(index <= 0){
		  let map = new Map();
		  let setIgnoreDup = [index+""]
		  map.set(arr[index], setIgnoreDup);
		  return map
		}

		let storageData = dp(index-1);
		let storageDataKey = Array.from(storageData.keys());

		for(ind = 0; ind < storageDataKey.length; ind++){
			if(sum == storageDataKey[ind] + arr[index]){
				storageData.get(storageDataKey[ind]).map(v => {
					if(checkValidNumInArr(v + "", index)){
						let strArr = (v + '-' + index).split("-")
						answerTotal.add(strArr.join("-"))
					}
				})
			} else if(storageDataKey[ind] + arr[index] < sum){
			  let setOldLd = new Set(),
			      currentStorage = storageData.get(storageDataKey[ind]);

			  let minValue = "1000-1000";
			  currentStorage.map(v => {
			    if(checkValidNumInArr(v + "", index)){
				    let strArr = (v + '-' + index).split("-")
			        setOldLd.add(strArr.join('-'))
				}
			  })
			  Array.from(setOldLd).map(v => compareTwoData(minValue, v) > 0 ? (minValue = v) : 1)

			  let arrNew = storageData.get(storageDataKey[ind] + arr[index]) || [];
			  arrNew.map(v => compareTwoData(minValue, v) > 0 ? (minValue = v) : 1);

			  storageData.set(storageDataKey[ind] + arr[index], [minValue])
			}else{
                if(maxInMinValue[0] >= storageDataKey[ind] + arr[index]){
                    let min = storageDataKey[ind] + arr[index] == maxInMinValue[0] ? maxInMinValue[1] : "10000-1000"
                    maxInMinValue[0] = storageDataKey[ind] + arr[index]

                    storageData.get(storageDataKey[ind]).map(v => {
                        if(checkValidNumInArr(v + "", index) && compareTwoData(min, v + "-" + index) > 0)
                           min = v + "-" + index
				    })
                    maxInMinValue[1] = min
                }
            }
		}

		if(arr[index] < sum){
			let v = storageData.get(arr[index]) || []
			v.push(index+"")
			storageData.set(arr[index], v)
		}

		return storageData;
	  }
	  // run dp
	  return dp(arr.length-1);
	}

    let resMap = checkSumExistOnArray(c, 2000)

    let arrayTotal = Array.from(answerTotal)
    let minVal = arrayTotal[0]
    arrayTotal.map(v => compareTwoData(minVal, v) > 0 ? (minVal = v): 1)

    let response = (minVal || "").split("-").map(v => parseInt(v))

    // console.log(response.length, maxInMinValue, resMap)
    if(response.length == 1 && isNaN(response[0])){
        response = getSmallestLexicographically(resMap, maxInMinValue)
    }

    return response

}
