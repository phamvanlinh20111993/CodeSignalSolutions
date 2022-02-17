/**
 * Calculate the difference between two given dates in the following format: YMD (
 * Years , Months , Days )
 * 
 * Example: dateFrom : "01-01-2016" dateTo: "28-02-2017"
 * 
 * Result: 1Y1M27D
 * 
 * [execution time limit] 4 seconds (js)
 * 
 * [input] string dateFrom
 * 
 * Date from "dd-mm-yyyy"
 * 
 * [input] string dateTo
 * 
 * Date to "dd-mm-yyyy"
 * 
 * [output] string
 * 
 * Result in Format: {0}Y{1}M{2}D
 * 
 * [execution time limit] 3 seconds (java)
 * 
 * [input] string dateFrom
 * 
 * [input] string dateTo
 * 
 * [output] string
 * 
 * [Java] Syntax Tips // Prints help message to the console // Returns a string // //
 * Globals declared here will cause a compilation error, // declare variables
 * inside the function instead! String helloWorld(String name) {
 * System.out.println("This prints to the console when you Run Tests"); return
 * "Hello, " + name; }
 */

//
//dateFrom: "15-02-2000"
// dateTo: "14-03-2000"
// expected : 28D


//
//dateFrom: "11-02-1999"
//dateTo: "20-04-2000"
//expected: "1Y2M9D"

// https://stackoverflow.com/questions/8922145/overriding-the-javascript-date-constructor/19070660
Date = class extends Date {
    constructor(dateStr) {
        if(/^\d{2}-\d{2}-\d{4}$/.test((dateStr+''))){
            const params = dateStr.split('-')
            super(`${params[2]}-${params[1]}-${params[0]}`)
        }else {
          super(dateStr)
        }
    }
}

// https://stackoverflow.com/questions/16353211/check-if-year-is-leap-year-in-javascript
const isleapYear = y => ((y % 4 === 0) && (y % 100 != 0)) || (y % 400 === 0)

const dayInMonth = (m, y) => {
    if(isNaN(y) || isNaN(m)){
        throw Error('paramters must be (should integer) number.')
        return -1
    }
    
    m = parseInt(m)
    y = parseInt(y)
    
    const DAY_IN_MONTH = {'1': 31, 
                          '2' : isleapYear(y) ? 29 : 28, 
                          '3': 31, 
                          '4': 30, 
                          '5': 31, 
                          '6': 30, 
                          '7': 31, 
                          '8': 31, 
                          '9': 30 , 
                          '10': 31, 
                          '11': 30, 
                          '12': 31}
    
    return DAY_IN_MONTH[m+'']
}

const extraDayInLeapYear = (yearFrom, yearTo) => {
	if(isNaN(yearFrom) || isNaN(yearTo)){
        throw Error('paramters must be (should integer) number.')
        return 0
    }
	
	const tmp = yearFrom
	yearFrom = yearFrom > yearTo ?  yearTo : yearFrom
    yearTo = yearFrom === yearTo ? tmp : yearTo
    
    let count = 0
    for(let year = yearFrom + 1; year < yearTo; year++){
    	count += isleapYear(year) ? 1 : 0
    }
	
	return count
}

Date.prototype.diff || (Date.prototype.diff = function (d){
    
    if(!d instanceof Date){
        throw Error('parameter is not a date type.')
        return '' 
    }
    
    let dBefore = d
    let dAfter = this
    let sign = ''
    if( dAfter.getTime() - dBefore.getTime() < 0 ){
        dBefore = this
        dAfter = d
        sign = '-'
    }
    
    console.log(dAfter, dBefore, dBefore.getMonth())
    let Y = dAfter.getFullYear() - dBefore.getFullYear()
    let M = dAfter.getMonth() - dBefore.getMonth()
    let D = dAfter.getDate() - dBefore.getDate() 
             + extraDayInLeapYear(dBefore.getFullYear(), 
                        dAfter.getMonth() > 2 ? dAfter.getFullYear() + 1 :  dAfter.getFullYear())

    // console.log(extraDayInLeapYear(dBefore.getFullYear(), 
    //                     dAfter.getMonth() > 2 ? dAfter.getFullYear() + 1 :  dAfter.getFullYear()))
  
    if( D < 0 ){
        D += dayInMonth(dBefore.getMonth() + 1, dBefore.getFullYear()) 
        M --
    }                  
    
    if( M < 0 ){
        M += 12
        Y --
    }
   
    return `${sign}${Y}Y${M}M${D}D`
})

solution = (dateFrom, dateTo) => new Date(dateTo).diff(new Date(dateFrom))