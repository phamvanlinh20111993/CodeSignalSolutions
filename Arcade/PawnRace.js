/**
 * 
 */


checkIsWhitePawnCaptureBlack = (whiteCoord, blackCoord) => 
    (whiteCoord[0] + 1 == blackCoord[0] || whiteCoord[0] - 1 == blackCoord[0]) && whiteCoord[1] + 1 == blackCoord[1]

checkWhitePawnIsJumped2 = (whiteCoord, blackCoord) => whiteCoord[1] == 2 && 
    !((whiteCoord[0] + 1 == blackCoord[0] || whiteCoord[0] - 1 == blackCoord[0]) && whiteCoord[1] + 3 == blackCoord[1])

// black pawn can jump if coord is 7 and not in capture the opponent's pawn range
checkBlackPawnIsJumped2 = (whiteCoord, blackCoord) => blackCoord[1] == 7 && 
    !((blackCoord[0] + 1 == whiteCoord[0] || blackCoord[0] - 1 == whiteCoord[0]) && blackCoord[1] - 3 == whiteCoord[1])

checkIsBlackPawnCaptureWhite = (whiteCoord, blackCoord) => 
    (blackCoord[0] + 1 == whiteCoord[0] || blackCoord[0] - 1 == whiteCoord[0]) && blackCoord[1] - 1 == whiteCoord[1]

// distance on horizontal
winnerDistanceBetweenOne = (whiteCoord, blackCoord, toMove) => {
    if(whiteCoord[1] == 2 && blackCoord[1] == 7){
        return toMove == "w" ? "black" : "white"
    }else{
        while(whiteCoord[1] < 8 && blackCoord[1] > 1){
            if(toMove == "w"){
                if(checkIsWhitePawnCaptureBlack(whiteCoord, blackCoord))
                    return "white"

                if(checkWhitePawnIsJumped2(whiteCoord, blackCoord))
                    whiteCoord[1] += 2
                else
                    whiteCoord[1] += 1
                toMove = "b"
            }else{
                if(checkIsBlackPawnCaptureWhite(whiteCoord, blackCoord))
                    return "black"

                if(checkBlackPawnIsJumped2(whiteCoord, blackCoord))
                    blackCoord[1] -= 2
                else
                    blackCoord[1] -= 1
                toMove = "w"
            }
        }
    }

    return whiteCoord[1] >= 8 ? "white" : "black"
}

// distance on horizontal
winnerDistanceLargeThanOne = (whiteCoord, blackCoord, toMove) => {
    let stepW = whiteCoord[1] == 2 ? 7 - whiteCoord[1] : 8 - whiteCoord[1],
        stepB = blackCoord[1] == 7 ? blackCoord[1] - 2 : blackCoord[1] - 1
    if(stepW == stepB)
        return toMove == "w" ? "white": "black"
    else 
        return stepW < stepB ? "white" : "black"
}

// base on horizontal
winnerWhenWhiteAndBlackSamePath = (whiteCoord, blackCoord, toMove) => 
                              whiteCoord[1] > blackCoord[1] ? winnerDistanceLargeThanOne(whiteCoord, blackCoord, toMove) 
                                                            : "draw"

pawnRace = (white, black, toMove) => {
    let whiteCoord = white.split(""),
        blackCoord = black.split(""),
        m = {'a': 1, 'b': 2, 'c': 3, 'd': 4, 
             'e': 5, 'f': 6, 'g': 7, 'h': 8}

    whiteCoord[0] = m[whiteCoord[0]]
    whiteCoord[1] = parseInt(whiteCoord[1])

    blackCoord[0] = m[blackCoord[0]]
    blackCoord[1] = parseInt(blackCoord[1])

    if(Math.abs(whiteCoord[0] - blackCoord[0]) > 1)
        return winnerDistanceLargeThanOne(whiteCoord, blackCoord, toMove)
    else if(whiteCoord[0] == blackCoord[0]) // for case: e5,e4,b
        return winnerWhenWhiteAndBlackSamePath(whiteCoord, blackCoord, toMove)
    else
        return winnerDistanceBetweenOne(whiteCoord, blackCoord, toMove)
}
