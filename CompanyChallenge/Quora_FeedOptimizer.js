/**
 * Link: https://app.codesignal.com/company-challenges/quora/dhcAAknWbF5mREWFL
 * 
 * Quora shows a customized feed of recent stories on a user's home page.
 * Stories in Quora refer to activities that happen on the site, such as when a
 * user adds a question or upvotes an answer. Every story has a score, which
 * represents the value that the story brings to the user. The goal is to
 * quickly generate a feed of the best and most recent stories for the user
 * every time they reload their Quora home page.
 * 
 * Your task is to design the algorithm that picks the stories that are
 * displayed in this feed.
 * 
 * You are given a list of stories that, for each story, contains: the time the
 * story was published, the story's score, and the height in pixels that it
 * takes to display the story. Given the total number of pixels h that are
 * available for displaying the story feed in the browser, you want to maximize
 * the sum of the scores for the stories that you can display in the feed every
 * time the user reloads their home page. You can only consider recent stories,
 * so a story can be displayed at any given moment only if it was posted no
 * longer than span units of time before that moment. You do not have to use up
 * all the pixels available in the browser.
 * 
 * For each reload event, return an array where the first element is the best
 * available sum of scores, and the other elements are the set of story IDs that
 * correspond to that score sum. The IDs should be returned in ascending order.
 * The answer arrays should be ordered by their corresponding reload event
 * times.
 * 
 * If two sets of stories have the same score, choose the set that contains
 * fewer stories. If there is still a tie, choose the set which has the
 * lexicographically smaller set of IDs.
 * 
 * Example
 * 
 * For span = 10, h = 100, and
 * 
 * events = [[11, 50, 30], [12], [13, 40, 20], [14, 45, 40], [15], [16], [18,
 * 45, 20], [21], [22]] the output should be
 * 
 * solution(span, h, events) = [[50, 1], [135, 1, 2, 3], [135, 1, 2, 3], [140,
 * 1, 3, 4], [130, 2, 3, 4]] There are 4 stories (with IDs 1 to 4) and 5 reload
 * events.
 * 
 * At the first reload, there is only one story (ID = 1) with score of 50
 * available for display; After the next two reloads, there are 3 stories that
 * take up 90 of the 100 pixels available, for a total score of 135; After
 * reloading at time 21, there are 4 stories available to choose from, but only
 * 3 will fit into the browser height. The best set is [1, 3, 4] for a total
 * score of 140; At the last reload event, you can no longer consider story 1
 * when choosing stories because it is more than 10 time units old. The best set
 * of scores is thus [2, 3, 4].
 * 
 * For a good visualization of this, check out the image below:
 * 
 * 
 * 
 * Input/Output
 * 
 * [execution time limit] 4 seconds (js)
 * 
 * [input] integer span
 * 
 * The number of time units elapsed prior to the reload event.
 * 
 * Guaranteed constraints: 5 ≤ span ≤ 100.
 * 
 * [input] integer h
 * 
 * The height of the browser in pixels.
 * 
 * Guaranteed constraints: 50 ≤ h ≤ 500.
 * 
 * [input] array.array.integer events
 * 
 * An array of events. A story event is described by 3 positive integers: the
 * time of publication, its score, and its height in pixels. A reload event is
 * described by 1 positive integer: the timestamp of the reload.
 * 
 * The events are given in chronological order, and it is guaranteed that no two
 * events occur at the same time.
 * 
 * Guaranteed constraints: 5 ≤ events.length ≤ 100, 1 ≤ events[i][j] < 109.
 * 
 * [output] array.array.integer
 * 
 * An array containing an entry for each reload event in events. For each entry,
 * the score sum is the first element and the recent story IDs from which this
 * score is derived are the remaining elements of the array.
 */



// // listSameScore = [[], [], []]
// const compareSetStoriesSameScore = listSameScore => {
//     if(!listSameScore || listSameScore.length < 2) return listSameScore[0] || []
    
//     listSameScore = listSameScore.sort((a, b) => a.length - b.length)
//     let equalStories = [], ind = 1
//     while(ind < listSameScore.length 
//         && listSameScore[ind].length === listSameScore[0].length) {
//         equalStories.push(listSameScore[ind++])
//     }
    
//     return equalStories
//     .sort((a, b) => a.join('-').localeCompare(b.join('-')))[0]
// }

// /**
//  * always get higher score with same heigh
//  */
// const getHigherScore = input => {
//     let mapHeigh = new Map()
    
//     input.map(v => {
//         if(mapHeigh.has(v[2])){
//             oldScore = mapHeigh.get(v[2])[1]
//             if(oldScore < v[1]){
//                 mapHeigh.set(v[2], v)
//             }
//         }else{
//             mapHeigh.set(v[2], v) 
//         }
//     })
    
//     return [ ...mapHeigh.values() ];
// }

// const dpMaxScore = (input, h) => {
//     input = input.sort((a, b) => b[1] - a[1])
//     console.log('input', input)    
    
//     const savePos = new Map()
//     const isVisit = new Array(input.length).fill(0)
//     const dp = (input, index, h) => {
//         if(savePos.has(h)) return savePos.get(h)
        
//         const isInclude = (arrOfArr, val) => {
//             let isInc = true, pos = -1
//             for(let ind = 0; ind < arrOfArr.length; ind++){
//                 if(!(arrOfArr[ind].split('-') || []).includes(val+'')){
//                    isInc = false
//                    pos = ind
//                    break
//                 }         
//             }
          
//             return [isInc, pos]
//         }
        
//         let maxScore = 0, path = ['']
//         for(let ind = index; ind < input.length; ind++){
//             if(isVisit[ind] === 0 && h - input[ind][2] >= 0){
//               isVisit[ind] = 1
//               const data = dp(input, ind + 1, h - input[ind][2])
//               isVisit[ind] = 0
              
//               const [isInc, pos] = isInclude(data[1], input[ind][3])
//               if(!isInc){
//                 if(maxScore < data[0] + input[ind][1]){
//                     maxScore = data[0] + input[ind][1]
//                     path = [data[1][0] + '-' + input[ind][3]]
//                 }else if(maxScore === data[0] + input[ind][1]){
//                     path.push(data[1][0] + '-' + input[ind][3])
//                 }
//               }
//             }
//         }
//         savePos.set(h, [maxScore, path])
//         return [maxScore, path]
//     }
    
//     dp(input, 0, h)
//     console.log('savePos', savePos)
    
//     const paths = savePos.get(h)[1]
//     .map(path => (path.split('-') || [])
//                  .slice(1).map(v => parseInt(v))
//                  .sort((a, b) => a - b)
//     )
    
//     const indexs = compareSetStoriesSameScore(paths)
    
//     const res = []
//     const inputMap = new Map()
//     input.map(v => inputMap.set(v[3], v))
//     indexs.map(ind => res.push(inputMap.get(ind)))
    
//     return [savePos.get(h)[0], res]
// }

// class QuoraFeed {
//     #span
//     #maxH
//     #totalScore = 0
//     #currentH = 0
//     #queue = new Array()
    
//     constructor(span, h){
//         this.#span = span
//         this.#maxH = h
//         this.#queue = new Array()
//     }
    
//     addEvent(event, index){
//         let [eventTime, eventScore, eventH] = event;
//         if(this.#currentH + eventH <= this.#maxH){
//             this.#totalScore += eventScore
//             this.#currentH += eventH
//             this.#queue.push([...event, index])
//         }else{
//             // backpack problem dynamic programming
//             console.log('this.#queue', this.#queue)
//             const data = dpMaxScore([...this.#queue, [...event, index]].filter(v => v[2] <= this.#maxH), this.#maxH)
//             this.#queue = data[1]
//             this.#totalScore = data[0]
//         }
//     }
    
//     removeOldestEvent(eventTime){
//         if(this.#queue.length > 0){
//             let oldestEvent = this.#queue[0]
//             if(eventTime - oldestEvent[0] > this.#span){
//                 this.#queue.shift()
//                 this.#totalScore -= oldestEvent[1]
//             }
//         }
//     }
    
//     get getCurrentFeedOptimizer(){
//         let indexs = [this.#totalScore]
//         this.#queue.map(v => indexs.push(v[3]))
//         return indexs
//     }
// }

// solution = (span, h, events)=> {
//     const quoraFeed = new QuoraFeed(span, h)
//     //console.log(quoraFeed.getCurrentFeedOptimizer) 
//     let res = [], ind = 1
//     events.map(event => {
//         if(event.length < 2){
//             quoraFeed.removeOldestEvent(event[0])
//             res.push(quoraFeed.getCurrentFeedOptimizer)
//         }else{
//             quoraFeed.addEvent(event, ind)
//             ind++
//         }
//     })
    
//     return res
// }

const compareSetStoriesSameScore = listSameScore => {
    if(!listSameScore || listSameScore.length < 2) return listSameScore[0] || []
    
    listSameScore = listSameScore.sort((a, b) => a.length - b.length)
    
    let equalStories = [listSameScore[0]], ind = 1
    while(ind < listSameScore.length 
        && listSameScore[ind].length === listSameScore[0].length) {
        equalStories.push(listSameScore[ind++])
    }
    
  
    return equalStories
    .sort((a, b) => a.join('-').localeCompare(b.join('-')))[0]
}

/**
 * always get higher score with same heigh
 */
const getHigherScore = input => {
    let mapHeigh = new Map()
    
    input.map(v => {
        if(mapHeigh.has(v[2])){
            oldScore = mapHeigh.get(v[2])[1]
            if(oldScore < v[1]){
                mapHeigh.set(v[2], v)
            }
        }else{
            mapHeigh.set(v[2], v) 
        }
    })
    
    return [ ...mapHeigh.values() ];
}

//i am using on my own solution dp. luckily i passed
//the best is: https://vi.wikipedia.org/wiki/B%C3%A0i_to%C3%A1n_x%E1%BA%BFp_ba_l%C3%B4
//formula: f[i][j]=max(f[i-1][j],v[i]+f[i][j-w[i]]) with f[i][j] is max valuable at object i, weigh j 
const dpCalMaxScore = (input, h) => {
	// sort with case same heigh but diff score.
    input = input.sort((a, b) => b[1] - a[1])
    // console.log('input', input)    
    
    const savePos = new Map()
    const isVisit = new Array(input.length).fill(0)
    
    const dp = (input, index, h) => {
        if(savePos.has(h)) return savePos.get(h)
        
        const isInclude = (arrOfArr, val) => {
            let isInc = true, pos = -1
            for(let ind = 0; ind < arrOfArr.length; ind++){
                if(!(arrOfArr[ind].split('-') || []).includes(val+'')){
                   isInc = false
                   pos = ind
                   break
                }         
            }
          
            return [isInc, pos]
        }
        
        let maxScore = 0, path = ['']
        for(let ind = index; ind < input.length; ind++){
            if(isVisit[ind] === 0 && h - input[ind][2] >= 0){
              isVisit[ind] = 1
              const data = dp(input, ind + 1, h - input[ind][2])
              isVisit[ind] = 0
              
              // should improve in this case
              const [isInc, pos] = isInclude(data[1], input[ind][3])
              if(!isInc){
                if(maxScore < data[0] + input[ind][1]){
                    maxScore = data[0] + input[ind][1]
                    path = [data[1][0] + '-' + input[ind][3]]
                }else if(maxScore === data[0] + input[ind][1]){
                    path.push(data[1][0] + '-' + input[ind][3])
                }
              }
            }
        }
        savePos.set(h, [maxScore, path])
        return [maxScore, path]
    }
    
    dp(input, 0, h)
   // console.log('savePos', savePos)
    
    const paths = savePos.get(h)[1]
    .map(path => (path.split('-') || [])
                 .slice(1).map(v => parseInt(v))
                 .sort((a, b) => a - b)
    )
    
    const indexs = compareSetStoriesSameScore(paths)
    
    return [savePos.get(h)[0], indexs]
}

class QuoraFeed {
    #span
    #maxH
    #queue = new Array()
    
    constructor(span, h){
        this.#span = span
        this.#maxH = h
        this.#queue = new Array()
    }
    
    addEvent(event, index){
        let [eventTime, eventScore, eventH] = event;
        if(eventH <= this.#maxH){
            this.#queue.push([...event, index])
        }
    }
    
    removeOldestEvent(eventTime){
        if(this.#queue.length > 0){
            let ind = 0
            while(ind < this.#queue.length && eventTime - this.#queue[ind][0] > this.#span){
                ind++
            }
            this.#queue = this.#queue.slice(ind)
        }
    }
    
    get getCurrentFeedOptimizer(){
      //  console.log('this.#queue', this.#queue)
        const filterInput = [...this.#queue].filter(v => v[2] <= this.#maxH)
        const data = dpCalMaxScore(filterInput, this.#maxH)
   //     console.log('data', data)
        return [data[0], ...(data[1] || [])]
    }
}

solution = (span, h, events)=> {
    const quoraFeed = new QuoraFeed(span, h)
    // console.log(quoraFeed.getCurrentFeedOptimizer)
    let res = [], ind = 1
    events.map(event => {
        if(event.length < 2){
            quoraFeed.removeOldestEvent(event[0])
            res.push(quoraFeed.getCurrentFeedOptimizer)
        }else{
            quoraFeed.addEvent(event, ind)
            ind++
        }
    })
    
    return res
}