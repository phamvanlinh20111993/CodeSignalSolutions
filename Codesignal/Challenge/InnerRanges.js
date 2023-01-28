/**
 * 
 */

isInRange = (a, b) => a + 1  <= b - 1

getRange = (a, b) => b - a == 2 ? `${a+1}` : `${a+1}->${b-1}`

reCreateArrayWithRange  = (nums, l, r) => {
    let newArr = [l-1],
        start = 0, 
        end = nums.length-1

    while(nums[start] < l - 1) start++

    while(nums[end] > r + 1) end--

    for(let i = start; i <= end; i++)
        newArr.push(nums[i])

    newArr.push(r + 1)

    return newArr
}

innerRanges = (nums, l, r) => {
    nums = reCreateArrayWithRange(nums, l, r)
    let res = [], start = 0
    for(let i = 1; i < nums.length; i++){
        if(isInRange(nums[start], nums[i]))
            res.push(getRange(nums[start], nums[i]))
        start++
    }

    return res
}
