/**
 * 
 */
let storeVal;

getChemistryFormular = (formula, index) => formula[index] + formula[index+1].toLowerCase()

recursive = (formulaMap, formula, index, str, totalElement) => {
    if(index < formula.length){
        if(formulaMap.has(formula[index])){
            recursive(formulaMap, formula, index + 1, 
                      str + formula[index], totalElement+1)
        } 
        if(index + 1 < formula.length 
            && formulaMap.has(getChemistryFormular(formula, index)) ){
            recursive(formulaMap, formula, index + 2, 
                      str + getChemistryFormular(formula, index), 
                      totalElement+1)
        }
    }else{
        storeVal.push([str, totalElement])
    }

}

restoreChemicalFormula = (elements, formula) => {
    let formulaMap = new Map()

    elements.map(v => formulaMap.set(v, 1));

    storeVal = []
    recursive(formulaMap, formula, 0, "", 0)
    storeVal.sort((a, b) => a[1] - b[1])

    let sameSmallestNumberElementsList = []
    let val = storeVal[0][1], index = 0

    while(index < storeVal.length && storeVal[index][1] == val){
        sameSmallestNumberElementsList.push(storeVal[index++][0])
    }
    sameSmallestNumberElementsList.sort((a, b) => b.localeCompare(a))

    return sameSmallestNumberElementsList[0]
}
