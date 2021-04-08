
/**
 * To celebrate Cyber Monday, Instacart has decided to give its shoppers
 * (employees that shop at grocery stores and deliver orders to customers) a
 * break. For a 24h period, every shopper only has to make 1 delivery, after
 * which his/her workday is over. However, since providing customers with a
 * reliable shopping experience is Instacart's #1 priority, it is important to
 * ensure that each order is fulfilled and delivered as promised.
 *
 * You are given a list of orders with the time periods when they should be
 * completed, and the time leadTime it takes to fulfill each order. Knowing the
 * time period each shopper is available (shoppers), find out whether the
 * current number of shoppers is enough to fulfill all orders.
 *
 * A shopper can fulfill an order if he/she can both start and finish it in the
 * time period specified for this order.
 *
 * Example
 *
 * For
 *
 * shoppers = [["15:10", "16:00"], ["17:40", "22:30"]] orders = [["17:30",
 * "18:00"], ["15:00", "15:45"]] and leadTime = [15, 30], the output should be
 * busyHolidays(shoppers, orders, leadTime) = true.
 *
 * The first shopper can take the second order, and the second shopper can take
 * the first one.
 *
 * For
 *
 * shoppers = [["15:10", "16:00"], ["17:50", "22:30"], ["13:00", "14:40"]]
 * orders = [["14:30", "15:00"]] and leadTime = [15], the output should be
 * busyHolidays(shoppers, orders, leadTime) = false.
 *
 * None of the shoppers can fulfill the given order. The first two will be
 * unavailable at the time of the order and the last one won't be able to finish
 * it in time, since the earliest time the order can be completed is 14:30 + 15
 * minutes = 14:45.
 *
 * Input/Output
 *
 * [execution time limit] 4 seconds (js)
 *
 * [input] array.array.string shoppers
 *
 * Available time for each shopper is given as an array of two strings [from,
 * to], where each string represents time in "hh:mm" format. The shopper is
 * available in the interval from from to to inclusive. It is guaranteed that
 * both from and to refer to the same day, and thus from < to in terms of time.
 *
 * Guaranteed constraints: 2 ≤ shoppers.length ≤ 40.
 *
 * [input] array.array.string orders
 *
 * For each order the period in which it should be fulfilled is given in the
 * same format as the availability of each shopper.
 *
 * Guaranteed constraints: 1 ≤ orders.length ≤ 40.
 *
 * [input] array.integer leadTime
 *
 * Array of positive integers of the same length as orders. leadTime[i] is the
 * number of minutes required to fulfill the ith order.
 *
 * Guaranteed constraints: leadTime.length = orders.length, 1 ≤ leadTime[i] ≤
 * 1000.
 *
 * [output] boolean
 *
 * true if the shoppers can fulfill each order, false otherwise.
 */


busyHolidays = (shoppers, orders, leadTime) => {

    let getTime = timeStr => {
        let timeArr = timeStr.split(":")
        return [parseInt(timeArr[0]), parseInt(timeArr[1])]
    }

    // 1 if timeStr > timeStr1
    // 0 if timeStr == timeStr1
    // -1 if timeStr < timeStr1
    let compareTime = (timeStr, timeStr1) => {
        let [h, m] = getTime(timeStr)
        let [h1, m1] = getTime(timeStr1)
        if(h === h1){
            return m > m1 ? 1 : m === m1 ? 0 : -1
        }
        return h > h1 ? 1 : -1
    }

    // return minutes timeStr - timeStr1
    let substractTime = (timeStr, timeStr1) => {
        let [h, m] = getTime(timeStr)
        let [h1, m1] = getTime(timeStr1)
        return (h - h1) * 60 + (m - m1)
    }

    let canShoppersTakeOrder = (order, leadTimeOrder) => {

    	 /*let cantakeOrder = (shopper, order, leadTimeOrder) => {
	        // shopper before order
	        if(compareTime(shopper[1], order[0]) >= 0 && compareTime(order[0], shopper[0]) >= 0
	           && substractTime(shopper[1], order[0]) >= leadTimeOrder){
	            return true
	        }
	        // order before shopper
	        if(compareTime(order[1], shopper[0]) >= 0 && compareTime(shopper[0], order[0]) >= 0
	            && substractTime(order[1], shopper[0]) >= leadTimeOrder){
	            return true
	        }
	        // shopper is in order time
	        if(compareTime(order[0], shopper[0]) <= 0 && compareTime(order[1], shopper[1]) >= 0
	           && substractTime(shopper[1], shopper[0]) >= leadTimeOrder){
	            return true
	        }
	        // order is in shopper time
	        if(compareTime(shopper[0], order[0]) <= 0 && compareTime(shopper[1], order[1]) >= 0
	            && substractTime(order[1], order[0]) >= leadTimeOrder){
	            return true
	        }

	        return false
	    }  */

          let cantakeOrder = (shopper, order, leadTimeOrder) => {
            let minTime = compareTime(shopper[0], order[0]) > 0 ? shopper[0] : order[0];
            let rangeTime = substractTime(order[1], minTime)
            let rangeTime1 = substractTime(shopper[1], minTime)

            if(rangeTime >= leadTimeOrder && rangeTime1 >= leadTimeOrder){
                return true
            }

            return false
        }

        let shoppersTakeOrder = new Array()
        let ind = 0
        for(const shopper of shoppers){
            if(cantakeOrder(shopper, order, leadTimeOrder)){
                shoppersTakeOrder.push(String.fromCharCode(ind + 65))
            }
            ind++
        }

        return shoppersTakeOrder
    }

    let orderGroup = new Array()
    let ind = 0
    for(const order of orders){
        orderGroup.push(canShoppersTakeOrder(order, leadTime[ind++]))
    }

    let isVisited = new Map()
    // Only filter each order has one shipper can be take
    let filterData = orderGroup => {
        let orderGroupClone = [...orderGroup]
        // Delete duplicate value with single
        for(let ind = 0, indTmp = 0; ind < orderGroupClone.length; ind++, indTmp++){
            const order = orderGroupClone[ind]
            if(order.length === 1){
                !isVisited.has(order[0]) ? (isVisited.set(order[0], 2) && orderGroup.splice(indTmp, 1) && indTmp--)
                                         : (orderGroup[indTmp] = [])
            }
        }

        for(let ind = 0; ind < orderGroup.length; ind++){
            orderGroup[ind] = orderGroup[ind].filter(v => !isVisited.has(v))
        }

        return orderGroup
    }

    orderGroup = filterData(orderGroup)

    let isAnyEmpty = orderGroup =>{
        let emptyData = orderGroup.filter(v => v.length === 0)
        return emptyData && emptyData.length > 0
    }

    if(isAnyEmpty(orderGroup)){
        return false
    }

    let flag = false
    let backTrackCheckAllOrdersCanDelivery = (current, orderGroup, isVisited) => {

        let isNotVisit = shipper => !isVisited.has(shipper) || (isVisited.has(shipper)
                                    && isVisited.get(shipper) === 0)

        if(flag) return true

        if(current >= orderGroup.length){
            flag = true
            return flag;
        }

        for(const shipper of orderGroup[current]){
            if(isNotVisit(shipper)){
                isVisited.set(shipper, 1)
                backTrackCheckAllOrdersCanDelivery(current + 1, orderGroup, isVisited)
                isVisited.set(shipper, 0)
            }
        }

        return false;
    }

    backTrackCheckAllOrdersCanDelivery(0, orderGroup, isVisited)

    return flag

    /*  let dpIsFullOrdersCanDelivery = (current, orderGroup) => {
		    if(current <= 0){
		        if(orderGroup.length == 0){
		            return ['']
		        }
		        return [...orderGroup[0]]
		    }
		    let initList = dpIsFullOrdersCanDelivery(current-1, orderGroup)
		    let newList = new Array()
		    orderGroup[current].map(shopper => {
		        initList.map(data => {
		            if(!data.includes(shopper)){
		                newList.push(data + shopper);
		            }
		        })
		    })
		    return newList;
		}
		let res = dpIsFullOrdersCanDelivery(orderGroup.length-1, orderGroup)

		return res.length > 0 */

}


