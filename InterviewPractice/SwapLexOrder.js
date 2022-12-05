/**
 * Ref:
 * https://app.codesignal.com/interview-practice/question/5vXzdE9yzjsoMZ9sk/description
 * Given a string str and array of pairs that indicates which indices in the
 * string can be swapped, return the lexicographically largest string that
 * results from doing the allowed swaps. You can swap indices any number of
 * times.
 * 
 * Example
 * 
 * For str = "abdc" and pairs = [[1, 4], [3, 4]], the output should be
 * solution(str, pairs) = "dbca".
 * 
 * By swapping the given indices, you get the strings: "cbda", "cbad", "dbac",
 * "dbca". The lexicographically largest string in this list is "dbca".
 */



	const groupingPairArr = arr => {
		let groupByMap = new Map() 
		arr.map(v => {
			groupByMap.has(v[0]) ? groupByMap.set(v[0], new Set([...groupByMap.get(v[0]), ...new Set([...v])])) : groupByMap.set(v[0], new Set([...v])) 
			groupByMap.has(v[1]) ? groupByMap.set(v[1], new Set([...groupByMap.get(v[1]), ...new Set([...v])])) : groupByMap.set(v[1], new Set([...v]))
		})
		
		let res = [],
			index = 0,
			isVisited = new Set();
			
		for (let [key, value] of groupByMap) {
			if (!isVisited.has(key)) {
				let initialSet = value,
					initial = Array.from(initialSet),
					ind = 0,
					isIncrease = false;
					
				index === res.length && (res[index] = new Set())
				// https://stackoverflow.com/questions/55460303/why-does-js-keep-insertion-order-in-set 
				//      
				while (ind < initial.length) {
					if (!isVisited.has(initial[ind])) {
						isVisited.add(initial[ind]) 
						res[index] = new Set([...res[index], ...groupByMap.get(initial[ind])]) 
						Array.from(groupByMap.get(initial[ind])).map(v => initialSet.add(v)) 
						initial = Array.from(initialSet) 
						isIncrease = true
					}
					ind++
				}
				
				isIncrease && index++;
			}
		}
		
		return res.filter(v => v.size !== 0)
	}
	
	solution = (str, pairs) => {
		// ignore same pairs. ex : [2, 3] <=> [3,2]   
		let checkSamePair = new Set()
		
		pairs.map(v => {
			const k = `${v[0]}-${v[1]}`
			const kReverse = `${v[1]}-${v[0]}`
			if (!checkSamePair.has(k) && !checkSamePair.has(kReverse)) {
				checkSamePair.add(k)
			}
		}) 
		
		pairs = Array.from(checkSamePair).map(v => {
			const s = v.split('-') 
			return [+s[0], +s[1]]
		})
		// split pair to group having the same in common. ex:   
		// [1, 3] have the same in common with [1, 5], [3,9], [7, 1],...   
		const groupPairs = groupingPairArr(pairs) 
	
		const strCopy = str.split('');
	    groupPairs.map((group, ind) => {
		    let strArr = [], groupArr = Array.from(group)
			groupArr.map(v => {
				strArr.push(str[v-1])
			})
			
			strArr = strArr.sort((a, b) => b.localeCompare(a))
			groupArr = groupArr.sort((a,b) => a - b)
			strArr.map((data, ind) =>{
				strCopy[groupArr[ind]-1] = data
			})
		})
		
		return strCopy.join('')
		
	}