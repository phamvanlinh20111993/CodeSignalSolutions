/**
 * 
 * Url: https://app.codesignal.com/challenge/a7bhLLazhtzcRxCAQ
 * 
 * LRC is a game that stands for Left, Right, Center
 * 
 * Play LRC with 3 or more players. On each player's turn, 3 dies are rolled to
 * determine if the player keeps their chips, passes them to other players,
 * and/or places chips in the center. Pass a chip to the left for each “L,” to
 * the right for each “R,” and to the center for every “C.” The player keeps a
 * chip for every dot rolled.
 * 
 * LRC
 * 
 * One player ​wins the game if all other players have no chips remaining. The
 * player who wins the game is rewarded all of the chips in the center in
 * addition to the chips they are holding.
 * 
 * In this version, player 0 always starts.
 * 
 * If the player has 3 or more chips, that player must roll all 3 die. If the
 * player only has 2 chips, that player must roll 2 die. (only consider first
 * two dies that were rolled of the 3. e.g. ".RC" means the player only rolled
 * "." and "R"; "C" is ignored) If the player only has 1 chip, that player must
 * roll 1 dice. (only consider first dice that was rolled of the 3. e.g. "C.L"
 * means the player only rolled "C"; "." and "L" are ignored) If the player has
 * zero chips, ignore the roll entirely! the player is still in the game but is
 * sitting idle until they have at least one chip.
 * 
 * Each dice has 6 faces. L, R, C, and 3 dots.
 * 
 * Determine which player wins and how many chips that player has.
 * 
 * Note: if there are fewer chips than players, fewer players than 3, or not
 * enough rolls to deem a winner, return [-1, -1]
 * 
 * See this site for more information
 * 
 * Examples diceRolls: ["LCC", "RRR"] chipCount: 9 players: 3 expected output:
 * [2, 9]
 * 
 * 9 chips are divided evenly across 3 players. player 0 rolls "LCC" and gives 1
 * chip to his neighbor (player 2) and places two chips in the center player 1
 * rolls "RRR" and gives 3 chips to his neighbor (player 2)
 * 
 * since player 0 and player 1 have 0 chips, player 2 wins with 9 total chips
 * (the 2 chips from the center, one chip from player 0, and 3 chips from player
 * 1, plus the 3 they started with)
 * 
 * diceRolls: ["CCL", "CCL", "CCR", "...", "CCR"] chipCount: 9 players: 4
 * expected output: [3, 8]
 * 
 * 9 chips cannot evenly divide across 4 players. each player receives 2 chips
 * and 1 chip is left out of the game. player 0 rolls "CCL" and puts 2 chips
 * into the center ("L" is ignored because player 1 only has 2 chips thus only
 * first 2 dies are considered) player 1 rolls "CCL" (same scenario) player 2
 * rolls "CCR" (same scenario) at this point player 4 wins and got 6 chips from
 * the center plus the 2 they started with totaling 8 (players 0-2 have no more
 * chips.)
 * 
 * [execution time limit] 4 seconds (js)
 * 
 * [input] array.string diceRolls
 * 
 * 0 <= i < 10000 diceRolls[i] represents the die roll from player # (i mod
 * playersCount) length(diceRolls[i]) == 3
 * 
 * [input] integer chipCount
 * 
 * The total number of chips to divide across all players (there could be some
 * chips left over. chips must be divided evenly)
 * 
 * [input] integer players
 * 
 * total number of players 0 <= players < 1000
 * 
 * [output] array.integer
 * 
 * [player number who won, how many chips the winning player has in the end
 * including what was in the center] e.g. [1, 9]
 */

solution = (diceRolls, chipCount, players) => {
    
    if(players < 3 || chipCount < players)  return [-1, -1] 
    
    let storePlayerChips = new Array()
    const setCheckCount = new Set()
    const chips =  Math.floor(chipCount/players);
    for(let ind = 0; ind < players; ind++){
        storePlayerChips.push({
            chips: chips,
            R: (ind + 1) % players,
            L: (players - 1 + ind) % players
        })
        setCheckCount.add(ind)
    }

    const TOTAL_DICE = 3;
    let centerChips = 0;
    
    for(let turn = 0; turn < diceRolls.length; turn++){
        const diceRoll = diceRolls[turn]
        const diceRArr = diceRoll.split('')
        const nextTurn = turn % players;
        const player = storePlayerChips[nextTurn]
        const amountAllRoll = player.chips >= TOTAL_DICE ? TOTAL_DICE : player.chips
        
        for(let ind = 0; ind < amountAllRoll; ind++){
            switch(diceRArr[ind]) {
                case 'L':
                    storePlayerChips[nextTurn].chips --
                    storePlayerChips[player.L].chips++
                    break;
                case 'R':
                    storePlayerChips[nextTurn].chips --
                    storePlayerChips[player.R].chips++
                    break;
                case 'C':
                    storePlayerChips[nextTurn].chips --
                    centerChips ++
                    break; 
                default:
                   break;
            }
        }
        if(storePlayerChips[nextTurn].chips <= 0){
            setCheckCount.delete(nextTurn)
        }else{
            setCheckCount.add(nextTurn)
        }
        
        if(setCheckCount.size == 1){
            break
        }
        
    }
     
    const winner = storePlayerChips.filter(player => player.chips > 0)
    if(winner.length > 1){
        return [-1, -1]
    }
    
    return [storePlayerChips.findIndex(player => player.chips > 0), 
            centerChips + winner[0].chips]
   
}
