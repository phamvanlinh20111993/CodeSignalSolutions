/**
 *
 */

/**
 * Format: {dollar: 1200, date: [day, month, year]}
 */
analysisHistory = historyArr => {
    const PATTERN = /^\$(\d+)[a-z\s]+([0-9\/]+)$/
    let res = []
    for(const history of historyArr){
        let matcher = history.match(PATTERN)
        let dateSplit = matcher[2].split("/")
        res.push({dollar: parseInt(matcher[1]),
                 date: [parseInt(dateSplit[1]), parseInt(dateSplit[0]), parseInt(dateSplit[2])]})
    }

    return res;
}

/**
 * Format, {date:[month, year], stringForm: ''}
 */
createCreditPayment = (startDate, endDate) => {
    let [monthStartDate, yearStartDate] = startDate.split("/")
    let [monthEndDate, yearEndDate] = endDate.split("/")
    let res = []

    let startMonth = parseInt(monthStartDate, 10)
    let endMonth = 12

    for(let startY = parseInt(yearStartDate); startY <= parseInt(yearEndDate, 10); startY++){
        if(startY == parseInt(yearEndDate, 10))
            endMonth = parseInt(monthEndDate, 10)
        for(let startM = startMonth; startM <= endMonth; startM++){
            res.push({date: [startM, startY], stringForm: ''})
        }
        startMonth = 1;
    }

    return res;
}

getPreviousDate = ([currentMonth, currentYear]) => {
    currentMonth = parseInt(currentMonth) - 1
    if(currentMonth < 1){
        currentMonth = 12;
        currentYear = parseInt(currentYear) - 1;
    }
    return [currentMonth, currentYear]
}

compareDate = ([m, y], [m1, y1]) => parseInt(m) ==  parseInt(m1)
                                  && parseInt(y) == parseInt(y1)

isBeforeDate = ([m, y], [m1, y1]) => {
    if(parseInt(y) > parseInt(y1)
        || (parseInt(y) == parseInt(y1) && parseInt(m) >= parseInt(m1)))
        return false
    return true
}

/**
 * format {isAddOneYear: boolean, currentB: b, str:''}
 */
getAddOneYear = (a, currentB, scheduleDate, history, startDate) => {
    // user has paid less than 3*b dollars during the last three months
    isPaidLess3b = () => {
        let currentMoney = 0
        let currentDate = scheduleDate
        for(let pos = 0; pos < 3; pos++){
            if(isBeforeDate(currentDate, startDate.split("/"))){
               return false
            }
            if(history[`${currentDate[0]}-${currentDate[1]}`]){
                currentMoney += history[`${currentDate[0]}-${currentDate[1]}`].dollar
            }
            currentDate = getPreviousDate(currentDate)
        }
        if(currentMoney < 3*currentB){
          return true
        }
        return false
    }

    //user late to pay for each of the last three months
    isPaidLate = () => {
        let countLatePaid = 3
        let currentDate = scheduleDate
        for(let pos = 0; pos < 3; pos++){
            if(isBeforeDate(currentDate, startDate.split("/"))){
                return false
            }
            if(history[`${currentDate[0]}-${currentDate[1]}`]){
                let historyDate = history[`${currentDate[0]}-${currentDate[1]}`].date
                countLatePaid -= parseInt(historyDate[0]) <= 15 ? 1 : 0
            }
            currentDate = getPreviousDate(currentDate)
        }

        if(countLatePaid == 3){
           return true
        }
        return false
    }

    //user didn't pay at all for the last two months
    isNotPay = () => {
        let countNotPay = 0
        let currentDate = scheduleDate
        for(let pos = 0; pos < 2; pos++){
            if(isBeforeDate(currentDate, startDate.split("/"))){
                return false;
            }
            if(!history[`${currentDate[0]}-${currentDate[1]}`]){
                countNotPay++;
            }
            currentDate = getPreviousDate(currentDate)
        }
        if(countNotPay > 1){
           return true
        }
        return false
    }

    let res = {isAddOneYear: false, currentB, str: ''}
    if(isNotPay() || isPaidLate() || isPaidLess3b()){
        let more = currentB + Math.round(a/100);
        res = {isAddOneYear: true, currentB: more, str: "Add one year, " + more+"." }
    }

    return res
}

/**
 * format {isRemoveOneYear: boolean, currentB: b, str: ''}
 */
getRemoveOneYear = (a, currentB, scheduleDate, history ,startDate) => {
    // During each of the last three months the user paid at least two times more than needed
    let countPayMoreNeeded = 0
    let currentDate = scheduleDate;
    let isPaidEnoughThree = 0;

    for(let pos = 0; pos < 3; pos++){
        if(history[currentDate[0]+'-'+currentDate[1]]){
            let currentMoney = history[currentDate[0]+'-'+currentDate[1]].dollar
            countPayMoreNeeded += currentMoney >= 2*currentB ? 1 : 0
            isPaidEnoughThree++;
        }
        currentDate = getPreviousDate(currentDate)
        if(isBeforeDate(currentDate, startDate.split("/")))
            break;
    }

    let res = {isRemoveOneYear: false, currentB, str: '' }
    if(isPaidEnoughThree == 3 && countPayMoreNeeded > 2){
        let more = currentB - Math.round(a/100);
        res = {isRemoveOneYear: true, currentB : more, str: "Remove one year, " + more + "."}
    }

    return res
}

creditPayments = (a, b, startDate, endDate, history) => {
    history = analysisHistory(history)

    let historyM = {}
    for(const h of history)
        historyM[h.date[1]+'-'+h.date[2]] = h

    let creditPayments = createCreditPayment(startDate, endDate)
    let res = []
    for(const creditPayment of creditPayments){
        let cPDate = creditPayment.date
        let addOneYear = getAddOneYear(a, b, cPDate, historyM, startDate)
        let removeOneYear = getRemoveOneYear(a, b, cPDate, historyM, startDate)

        if(addOneYear.isAddOneYear){
            res.push(addOneYear.str)
            b = addOneYear.currentB
        }else if(removeOneYear.isRemoveOneYear){
            b = removeOneYear.currentB
            res.push(removeOneYear.str)
        }else{
            res.push("Leave as it is, " + b + ".")
        }
    }

    return res
}
