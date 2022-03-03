/**
 * https://app.codesignal.com/company-challenges/quora/jJmR5MKDZiaDuBMsd
 * 
 * The search bar at the top of every page on Quora allows you to search the most up-to-date people, topics and questions on the site.

The goal is to quickly return the most relevant results that match the search query entered into the input text field. Every time a new user, question or topic is added (or old ones deleted), subsequent queries must reflect those changes immediately. This is handled using a fast in-memory service.

Input comes into the service as the following commands:

ADD <type> <id> <score> <data string that can contain spaces>
Adds the following <type> of item (user | topic | question | board) with the unique <id> string and <score> float, corresponding to the <data string> that would be used to match queries.
DEL <id>
Deletes the item specified by unique identifier <id>.
QUERY <number of results> <query string that can contain spaces>
Queries for the specified integer number of results (up to 20) that match a given <query string>. For a data item to be considered a matching result to a query, each token in the query must be found in the data string as a case-insensitive prefix to any token in the data string. The results are ranked in descending order of score, and only the top few results are given as specified. When there is a tie in the score, newer items (more recently added) should be ranked higher. If there are no results, return an empty list.
Your task is to write an equivalent service that will able to answer these queries.

Example

For

queries = [["ADD", "user", "u1", "1.0", "Adam D'Angelo"],
           ["ADD", "user", "u2", "1.0", "Adam Black"],
           ["ADD", "topic", "t1", "0.8", "Adam D'Angelo"],
           ["ADD", "question", "q1", "0.5", 
               "What does Adam D'Angelo do at Quora?"],
           ["ADD", "question", "q2", "0.5", 
               "How did Adam D'Angelo learn programming?"],
           ["QUERY", "10", "Adam"],
           ["QUERY", "10", "Adam D'A"],
           ["QUERY", "10", "Adam Cheever"],
           ["QUERY", "10", "LEARN how"],
           ["QUERY", "1", "lear H"],
           ["QUERY", "0", "lea"],
           ["DEL", "u2"],
           ["QUERY", "2", "Adam"]]
the output should be

solution(queries) = [["u2", "u1", "t1", "q2", "q1"], 
                            ["u1", "t1", "q2", "q1"], 
                            [], 
                            ["q2"], 
                            ["q2"], 
                            [], 
                            ["u1", "t1"]]
Input/Output

[execution time limit] 4 seconds (js)

[input] array.array.string queries

For each valid i, query[i] means a single query. queries[i][0] equals "ADD", "DEL" or "QUERY" and other query elements are given as described above.
It is guaranteed that the number of results for each QUERY is not bigger than 20.

Guaranteed constraints:
5 ≤ queries.length ≤ 100,
1 ≤ queries[i][j].length ≤ 100.

[output] array.array.string
 */



solution = queries => {
    
    const findIndex = (storage, data) => {
        for(let ind = 0; ind < storage.length; ind++){
            if(data == storage[ind][2]){
                return ind
            }
        }
        return -1
    }
    
    // If queryString = ["A A" and dataString = "A linh" => is match or not?
    const compareMatching = (queryString, dataString) => {
        let tokenQueryStrings = queryString.split(/\s+/)
        let tokenDataStrings = dataString.split(/\s+/)
         
        let flags = new Array(tokenQueryStrings.length).fill(false)
        tokenQueryStrings.map((tokenQueryString, ind) => {
            tokenDataStrings.map(tokenDataString => {
                if(tokenDataString.toLowerCase().startsWith(tokenQueryString.toLowerCase())){
                    flags[ind] = true
                }
            })
        })
        return flags.filter(v => v == false).length === 0
    }
    
    // Contains add query
    // storage = [["user", "u1", "1.0", "Adam D'Angelo"]]
    // query = ["QUERY", "10", "LEARN how"],
    const getMappingData = (storage, query) => {
        let res = Array()
        storage.map(dataString => {
            if(compareMatching(query[2], dataString[4])){
               res.push(dataString) 
            }
        })
        
        //filter result
        res = res.sort((v1, v2) => {
            if(parseFloat(v2[3]) - parseFloat(v1[3]) === 0){
                let indV1 = findIndex(storage, v1[2])
                let indV2 = findIndex(storage, v2[2])
                return indV2 - indV1
            }else{
                return parseFloat(v2[3]) - parseFloat(v1[3])
            }
        })
        
        return res.slice(0, parseInt(query[1]));
    }
    
    // data
    const updateStorage = (storageQueue, type, data) =>{
        switch(type){
            case "ADD":
                storageQueue.push(data)
                break
            case "DEL":
                storageQueue = storageQueue.filter(v => v[2] != data[1])
                storageQueue = !storageQueue ? new Array() : storageQueue
                break
            default:
                break;
        }
        
        return storageQueue
    }
    
    let storageQueue = new Array() 
    let res = new Array()
    queries.map(query => {
        if(query[0] == "QUERY"){
            let resTmp = getMappingData(storageQueue, query)
            resTmp = resTmp ? resTmp.map(v => v[2]) : []
            res.push(resTmp)
        }else{
            storageQueue = updateStorage(storageQueue, query[0], query)
        }
    })

    return res
}
