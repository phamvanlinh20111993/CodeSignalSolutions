/**
 *
 */
coverDebts = (s, debts, interests)=> {
    let sortedDebtAndInterests = []
    for(let ind = 0; ind < debts.length; ind++){
        sortedDebtAndInterests[ind] = { debt: debts[ind],
                                       interest: interests[ind] }
    }

    sortedDebtAndInterests = sortedDebtAndInterests.sort((a, b) => {
        return b.interest - a.interest
    })

    let ind = 0, total = 0
    while(ind < debts.length && sortedDebtAndInterests[ind].debt > 0){
        let go = ind
        let allowSpendMoney = s * 0.1

        while(go < debts.length && allowSpendMoney >= sortedDebtAndInterests[go].debt){
            total += sortedDebtAndInterests[go].debt
            allowSpendMoney -= sortedDebtAndInterests[go].debt
            sortedDebtAndInterests[go].debt = 0
            go++
        }

        if(go < debts.length){
            sortedDebtAndInterests[go].debt -= allowSpendMoney
            total += allowSpendMoney
        }

        for(let index = go; index < debts.length; index++){
            sortedDebtAndInterests[index].debt +=
            sortedDebtAndInterests[index].debt * (sortedDebtAndInterests[index].interest/100)
        }

        ind = go
    }

    return total
}
