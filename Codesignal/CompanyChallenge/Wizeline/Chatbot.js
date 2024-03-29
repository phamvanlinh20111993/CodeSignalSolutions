/**
 * https://app.codesignal.com/company-challenges/wizeline/Kv4xAQGkYNgmawGLR
 * 
 * You have a list conversations, in which each element is a conversation that is represented as an array of words. You need to create a chatbot that will complete a conversation that is currently in progress, currentConversation.

To do that, the chatbot must find the conversation from the given list that has the largest number of unique words that match with words from the currentConversation. If there are several conversations that match this condition, the chatbot should use the one that appears first in conversations. If no conversation from the list contains any matching words from currentCoversation, the chatbot should leave currentConversation as it is.

If there is a conversation that can complete currentConversation, the chatbot should find the first word in it that appears after all the matching words. The chatbot should then append this word, along with all the words that follow it in that conversation, to currentConversation.

Return the final state of currentConversation.

Example

For
conversations = [
    ["where", "are", "you", "live", "i", "live", "in", "new", "york"],
    ["are", "you", "going", "somewhere", "tonight", "no", "i", "am", "too", "tired", "today"],
    ["hello", "what", "is", "your", "name", "my", "name", "is", "john"]]
and currentConversation = ["hello", "john", "do", "you", "have", "a", "favorite", "city", "to", "live", "in", "yes", "it", "is"], the output should be
solution(conversations, currentConversation) = ["hello", "john", "do", "you", "have", "a", "favorite", "city", "to", "live", "in", "yes", "it", "is", "new", "york"].

The second conversation has only one matching word, "you". But the other two conversations both have three unique matching words. In the first conversation, the matches are "you", "live", and "in". In the third conversation, the matches are "hello", "john", and "is". Since we have two options that could complete our current conversation, we should choose the one that appears earlier in the list, so we use the first conversation. In that conversation, the last matching word is "in", so we add the last two words, "new" and "york", to currentConversation to complete it.

For
conversations = [
    ["lets", "have", "some", "fun"],
    ["i", "never", "get", "it"],
    ["be", "aware", "of", "this", "house"],
    ["he", "will", "call", "her"]]
and currentConversation = ["can", "you", "please"], the output should be
solution(conversations, currentConversation) = ["can", "you", "please"].

None of the conversations have any words that match words in currentConversation, so we add nothing to it.

Input/Output

[execution time limit] 4 seconds (js)

[input] array.array.string conversations

An array of conversations, where each conversation is represented as an array of strings. Each string contains only lowercase English letters.

Guaranteed constraints:
1 ≤ conversations.length ≤ 104,
1 ≤ conversations[i].length < 100,
1 ≤ conversations[i][j].length ≤ 15.

[input] array.string currentConversation

The conversation in progress, which needs to be completed by the chatbot. Each string contains only lowercase English letters.

Guaranteed constraints:
1 ≤ currentConversation.length ≤ 100,
1 ≤ currentConversation[i].length ≤ 15.

[output] array.string

The completed currentConversation.
 */

solution = (conversations, currentConversation) => {
    let m = new Map(), maxCount = 0, arrP = {posInd : -1, last: -1};
    
    currentConversation.map((v, i)=> m.set(v, 1));
                            
    for(let index = 0; index < conversations.length; index++){
        let lastIndexMatch, matchCount = 0, posInd, newMap = new Map(m);
        
        for(let ind = 0; ind < conversations[index].length; ind++){
            if(newMap.get(conversations[index][ind]) > 0){
                matchCount++;
                lastIndexMatch = ind;
                posInd = index;
                newMap.set(conversations[index][ind], 
                           newMap.get(conversations[index][ind]) - 1)
            }else if(newMap.get(conversations[index][ind]) == 0){
                // get last index match to add, passed last hidden test
                   lastIndexMatch = ind;   
            }
        }
        
        if(matchCount > maxCount){
            maxCount = matchCount;
            arrP = {posInd : index, last: lastIndexMatch};
        }
    }
    

    if(arrP.posInd > -1){
        for(let i = arrP.last + 1; i < conversations[arrP.posInd].length; i++)
            currentConversation.push(conversations[arrP.posInd][i]);
    }
    
    
    return currentConversation;
}
