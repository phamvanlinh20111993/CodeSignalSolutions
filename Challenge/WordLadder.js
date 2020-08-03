/**
 * 
 */

/**
 * 
 * We can use BFS to solve this problem as follows:
 * - Note: im using convet hit -> cog
 * 1, From beginWord, found all possible change one word then check it if exist in wordList, take all of that text. Example:
 * hit => [a-z]it, h[a-z]t, hi[a-z] => check in wordList we only word "hot"
 *  2, go to step 1 with list found [hot, ...] => Do the same until you find the result.
 * With bfs, the first resunt found is the shortest transformation sequence. 
 * 
 */

wordLadder = (beginWord, endWord, wordList) => {
   
    let wordListMap = new Map()
    wordList.map(v => wordListMap.set(v, 1))
    
    if(!wordListMap.has(endWord) || beginWord.length != endWord.length)
        return 0
        
    getTextsInWordList = (word, wordListMap, level) => {
        const ALPHABET = 'abcdefghijklmnopqrstuvwxyz';

        let existInWordList = []
        for(let ind = 0; ind < word.length; ind++){
             let newWord = ""
            for(let pos = 0; pos < ALPHABET.length; pos++){
                if(ALPHABET[pos] != word[ind]){
                    newWord = word.substring(0, ind) + ALPHABET[pos] + word.substring(ind+1, word.length)
                    if(wordListMap.has(newWord) && wordListMap.get(newWord) > 0 ){
                        existInWordList.push({word: newWord, level})
                        wordListMap.set(newWord, 0)
                    }
                }
            }
                
        }
        
        return existInWordList
        
    }
    
    //start using bfs    
    let queue = [{ word: beginWord, level: 0}];
    while(queue.length > 0){
        let data = queue.shift();
        if(data.word == endWord){
            return data.level+1
        }
        //get allposible 
        queue = queue.concat(getTextsInWordList(data.word, wordListMap, data.level+1))
    }
    
    return 0;
}
