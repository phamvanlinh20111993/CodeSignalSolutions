/**
 * https://app.codesignal.com/company-challenges/jet/gJ7iZxY7DSGPC4dew
 * 
 * Jet is building a new feature designed to significantly reduce order delivery
 * times. However, faster delivery sometimes means higher shipping fees, and for
 * cost-conscious customers this might be a problem. Your task is to implement a
 * function that finds the fastest delivery time for a given order, taking into
 * account that the customer doesn't want to pay more than maxPrice.
 * 
 * Consider a customer's basket of items. You are given a list of available
 * vendors from which you can order these items. For each vendor you know the
 * products they provide with their price (vendorProducts) and the time it will
 * take to deliver them (vendorsDelivery). Find the vendors that should be
 * chosen so that the total price of items in the basket is not greater than the
 * maxPrice, while keeping delivery time to a minimum. The delivery time is the
 * maximum delivery time of all chosen vendors.
 * 
 * You should only choose vendors you're going to buy something from. It is
 * guaranteed that there is always exactly one solution.
 * 
 * Example
 * 
 * For maxPrice = 7, vendorsDelivery = [5, 4, 2, 3], and
 * 
 * vendorsProducts = [[1, 1, 1], [3, -1, 3], [-1, 2, 2], [5, -1, -1]] the output
 * should be solution(maxPrice, vendorsDelivery, vendorsProducts) = [1, 2].
 * 
 * There are three items in the basket, so here is the list of options:
 * 
 * although vendor 0 can provide all items for the lowest price, it will take 5
 * days to deliver them; vendors 1 and 2 can deliver all items in 4 and 2 days
 * respectively, so in 4 days all of the items will have been delivered. It
 * would cost 3 + 2 + 2 = 7 (note that the 2nd 0-based item is provided by both
 * vendors, but since the price at vendor number 2 is lower, that's where we
 * would purchase it); vendors 2 and 3 will deliver everything in just 3 days,
 * but it would cost 2 + 2 + 5 = 9, which is too much. Thus, vendors 1 and 2
 * should be chosen to fulfill the order.
 * 
 * Input/Output
 * 
 * [execution time limit] 4 seconds (js)
 * 
 * [input] integer maxPrice
 * 
 * The maximum amount of money the customer is willing to pay.
 * 
 * Guaranteed constraints: 1 ≤ maxPrice ≤ 106.
 * 
 * [input] array.integer vendorsDelivery
 * 
 * For each valid i, vendorsDelivery[i] is the number of days it will take the
 * ith vendor to deliver the goods.
 * 
 * Guaranteed constraints: 1 ≤ vendorsDelivery.length ≤ 100, 1 ≤
 * vendorsDelivery[i] ≤ 106 + 1.
 * 
 * [input] array.array.integer vendorsProducts
 * 
 * Rectangular matrix with vendorsDelivery.length rows. The number of its
 * columns equals the number of items in the basket. If vendors[i][j] > 0, then
 * the ith vendor provides the jth item, and it costs vendors[i][j].
 * vendors[i][j] = -1 otherwise, which means that the ith vendor doesn't provide
 * the jth item.
 * 
 * Guaranteed constraints: vendorsProducts.length = vendorsDelivery.length, 1 ≤
 * vendorsProducts[0].length ≤ 100, -1 ≤ vendorsProducts[i][j] ≤ 106.
 * 
 * [output] array.integer
 * 
 * A sorted array of 0-based indices of the vendors that should be chosen to
 * fulfill the order.
 * 
 * The order is fulfilled if for each item j (0 ≤ j < vendors[0].length) there
 * is a vendor which provides it.
 */


// Array.prototype.pushNumOrder || (Array.prototype.pushNumOrder =
// function(element){
   
// })


// Array.prototype.pushNumOrder || (Array.prototype.pushNumOrder = function(element){
   
// })


// Array.prototype.pushNumOrder || (Array.prototype.pushNumOrder = function(element){
   
// })

function solution(maxPrice, vendorsDelivery, vendorsProducts) {
    
    const addOrderElement = (arr, e) => {
  
        if(arr.length === 0){
        	arr.push(e)
            return arr
        }else if(arr.length === 1){
        	arr[0].day > e.day ? arr.unshift (e) : arr.push(e)
            return arr
        }
        
        const binarySearch = (i, j) =>{
            if(i === j){
                return e.day > arr[i].day ? i + 1 : i - 1
            }
            const mid = parseInt((i + j) / 2)
            if(arr[mid].day > e.day){
                return binarySearch(0, mid)
            }else{
                return binarySearch(mid+1, j)
            }
        }
        
        const pos = binarySearch(0, arr.length - 1)
        
    	if(pos < 0 || pos > arr.length - 1){
           pos < 0 ? arr.unshift(e) : arr.push(e)
           return arr
        }
        
        return [...arr.slice(0, pos + 1), e].concat(arr.slice(pos + 1))
    }
    
    
    const isFullProduct = arr => {
        let count = 0
        for(let ind = 1; ind < arr[0].length; ind++){
            for(let pos = 0; pos < arr.length; pos++) {
                if(arr[pos][ind] > 0){
                    count++
                    break
                }
            }
        }
        
        return count === vendorsProducts[0].length
    }
    
    const isInPriceRange = arr => {
        let minRange = 0
        for(let ind = 1; ind < arr[0].length; ind++){
            let min = 10000000
            for(let pos = 0; pos < arr.length; pos++) {
                if(min > arr[pos][ind] && arr[pos][ind] > 0){
                    min = arr[pos][ind]
                }
            }
            
            minRange += min
        }
        
        return minRange <= maxPrice 
    }
    
    const setectMin = arr => {
        let ranges = []
        for(let ind = 1; ind < arr[0].length; ind++){
            let min = [10000000, -1]
            for(let pos = 0; pos < arr.length; pos++) {
                if(min[0] > arr[pos][ind] && arr[pos][ind] > 0){
                    min = [arr[pos][ind], arr[pos][0]]
                }
            }
            ranges.push(min)
        }
        
        return Array.from(new Set([...ranges.map(v => v[1])]))
    }
    
    const vendorsDeliveryInds = vendorsDelivery.map((v, id) => [v, id])
    .sort((a, b) => a[0] - b[0])
    
    const copProducts = []
    for(let ind = 0; ind < vendorsDeliveryInds.length; ind++){
        copProducts.push([vendorsDeliveryInds[ind][1],...vendorsProducts[vendorsDeliveryInds[ind][1]]])
            
        if(isFullProduct(copProducts) 
            && isInPriceRange(copProducts)){
            break
        }
    }
    
    return setectMin(copProducts)
}