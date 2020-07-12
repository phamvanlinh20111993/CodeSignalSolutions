/**
 * 
 */

getSummaryNumber = (a, b) => a == b ? `${a}` : `${a}->${b}`

getSequenceNumber = (start, nums) => {
    let ind = start, end, l = nums.length;
    while(ind < l && ind+1 < l && nums[ind+1] - nums[ind] == 1)  ind++;
    return [nums[start], nums[ind], ind+1]
}

getSummaryNumbers = nums => {
    let init = 0, re = []
    while(init < nums.length){
        let r = getSequenceNumber(init, nums)
        re.push([r[0], r[1]])
        init = r[2]
    }

    return re;
}

composeRanges = nums => {
    let res = getSummaryNumbers(nums)
    let r = []
    res.map( v => {
        m = getSummaryNumber(v[0], v[1])
        r.push(m)
    })
    return r
}
