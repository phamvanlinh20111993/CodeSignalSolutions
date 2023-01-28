/**
 * https://app.codesignal.com/company-challenges/two-sigma/EYt3tqQCBgnc6Lsk5
 * 
 * When visualizing market data over a long period of time, it is often helpful to build an Open-high-low-close (OHLC) chart. However, to build an OHLC chart you first need to prepare some data. For each financial instrument consider each day when it was traded, and find the following prices the instrument had that day:

open price: the price of the first trade of the day;
high price: the highest trade of the day;
low price: the lowest trade of the day;
close price: the price of the last trade of the day.
Given a stream of trade data ordered by time, write a program to compute the OHLC by day and instrument (see output section for the format details).
If two trades happen to have equal timestamps, then their order is determined by the order of their timestamps in the timestamp array.

Example

For

timestamp = [1450625399, 1450625400, 1450625500, 
             1450625550, 1451644200, 1451690100, 1451691000]
instrument = ["HPQ", "HPQ", "HPQ", "HPQ", "AAPL", "HPQ", "GOOG"],
side = ["sell", "buy", "buy", "sell", "buy", "buy", "buy"],
price = [10, 20.3, 35.5, 8.65, 20, 10, 100.35], and
size = [10, 1, 2, 3, 5, 1, 10], the output should be

solution(timestamp, instrument, side, price, size) = 
[["2015-12-20", "HPQ", "10.00", "35.50", "8.65", "8.65"],
 ["2016-01-01", "AAPL", "20.00", "20.00", "20.00", "20.00"],
 ["2016-01-01", "GOOG", "100.35", "100.35", "100.35", "100.35"],
 ["2016-01-01", "HPQ", "10.00", "10.00", "10.00", "10.00"]]
Input/Output

[execution time limit] 4 seconds (js)

[input] array.integer timestamp

A nondecreasing sequence of positive integers. timestamp[i] stands for the Unix time when the ith trade was made.

Guaranteed constraints:
1 ≤ timestamp.length ≤ 50,
666 ≤ timestamp[i] < 2 · 109.

[input] array.string instrument

Array of the same length as timestamp. instrument[i] is the ticker symbol (identifier) for the financial instrument taking part in the ith trade.

Guaranteed constraints:
instrument.length = timestamp.length,
1 ≤ instrument[i].length ≤ 4.

[input] array.string side

Array of the same length as timestamp. side[i] equals either "buy" or "sell" depending on whether instrument[i] was bought or sold during the ith trade.

Guaranteed constraints:
side.length = timestamp.length.

[input] array.float price

Array of the same length as timestamp. price[i] is the price of the instrument[i] during the ith trade. It is guaranteed that price[i] has no more than two digits after the decimal point.

Guaranteed constraints:
price.length = timestamp.length,
0.5 ≤ price[i] ≤ 100.5.

[input] array.integer size

Array of the same length as timestamp. size[i] equals the number of shares of the instrument[i] traded during the ith trade.

Guaranteed constraints:
size.length = timestamp.length,
1 ≤ size[i] ≤ 5 · 105.

[output] array.array.string

The ith row of the output should contain the following elements:

output[i][0] - local server date in the YYYY-MM-DD format;
output[i][1] - a ticker symbol for some instrument;
output[i][2] - a string corresponding to the open price;
output[i][3] - a string corresponding to the high price;
output[i][4] - a string corresponding to the low price;
output[i][5] - a string corresponding to the close price.
Each string corresponding to the price should contain exactly two digits after the decimal point and at least one digit before.

For each unique pair of a date and an instrument present in the inputs, such that there was a trade of that instrument on that day, there should be exactly one row in the output.

Output rows should be ordered by dates. Rows corresponding to the same date should be ordered in lexicographical order for ticker symbols.
 */



formatDateByTimeStamp = t => {
    let d = new Date(t*1000);
    let year = d.getFullYear();
    let month = d.getMonth() + 1;
    let day = d.getDate();
    return  year + '-' + (month < 10 ? '0' + month : month) + '-' + (day < 10 ? '0' + day : day);
}

statisTicOHLC = arr => {
 
    let openPrice = arr[0].price, 
        highPrice = arr[0].price, 
        lowPrice = arr[0].price, 
        closePrice = arr[0].price,
        earlyTimeStamp = arr[0].timestamp,
        lateTimeStamp = arr[0].timestamp;
    
    for(let ind = 1; ind < arr.length; ind++){
        // get open price
        if(arr[ind].timestamp < earlyTimeStamp){
            earlyTimeStamp = arr[ind].timestamp;
            openPrice = arr[ind].price;
        }
        // get close price 
        if(arr[ind].timestamp >= earlyTimeStamp){
            lateTimeStamp = arr[ind].timestamp;
            closePrice = arr[ind].price;
        }
        // get high price
        if(highPrice < arr[ind].price)
            highPrice = arr[ind].price;
         // get low price
        if(lowPrice > arr[ind].price)
            lowPrice = arr[ind].price;
    }
    
    return [openPrice.toFixed(2), highPrice.toFixed(2), lowPrice.toFixed(2), closePrice.toFixed(2)]
}

solution = (timestamp, instrument, side, price, size) => {
    let dOHLC = [],
        mapStatistic  = new Map();
    for(let ind = 0; ind < timestamp.length; ind++){
        let key = instrument[ind] +"**"+ formatDateByTimeStamp(timestamp[ind]), value;
        if(mapStatistic.has(key)){
            value = mapStatistic.get(key);
            value.push({ price: price[ind], timestamp: timestamp[ind]})
        }else{
            value = new Array({price: price[ind], timestamp: timestamp[ind]})
        }
        mapStatistic.set(key, value);
    }
    
    let index = 0;
    mapStatistic.forEach((value, key)=> {
        let splitKey = key.split("**");
        dOHLC[index++] = [splitKey[1], splitKey[0]].concat(statisTicOHLC(value));
    });   
    
    
    dOHLC.sort((v1, v2) => v1[0] > v2[0] ? 1 :  v1[0] == v2[0] ? v1[1].localeCompare(v2[1]): -1)
    
    return dOHLC;
}
