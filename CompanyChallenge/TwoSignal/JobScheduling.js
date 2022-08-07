/**
 * Url: https://app.codesignal.com/company-challenges/two-sigma/ekzXNgaHnSxBmuwSi
 * 
 * Two Sigma maintains a distributed computing cluster capable of handling many
 * different types of jobs. Some jobs are quite small (for instance MapReduce
 * style jobs), but others can be larger (certain types of distributed
 * optimization).
 * 
 * Let's assume that the jobs are processed one by one, and if a new job request
 * comes in when one is already being processed it's added to the queue. You
 * were asked to implement a scheduling algorithm that adds jobs to the regular
 * queue and pushes them through in such a way that the average wait time for
 * all jobs in the queue is minimized. A new job isn't pushed through unless it
 * minimizes the average waiting time.
 * 
 * Assume that your program starts working at 0 seconds. A request for the ith
 * job came at requestTimei, and let's assume that it takes jobProcessi seconds
 * to process it.
 * 
 * Find the state of your algorithm's queue timeFromStart seconds after your
 * program started to work, assuming that all actions that could've happened
 * right at this moment have already happened.
 * 
 * Example
 * 
 * For requestTime = [0, 5, 8, 11], jobProcess = [9, 4, 2, 1], and timeFromStart =
 * 11 the output should be solution(requestTime, jobProcess, timeFromStart) =
 * [1].
 * 
 * Here's the optimal algorithm with an average wait time of (1 + 7) / 2 = 4
 * seconds:
 * 
 * 0 seconds from launch: start processing request 0 (0-based); 5 seconds: add
 * request 1 to the queue; 8 seconds: put request 2 at the front of the queue; 9
 * seconds: finish processing request 0 and start processing request 2; 11
 * seconds: put request 3 at the front of the queue; finish processing request
 * 2; start processing request 3; 12 seconds: finish processing request 3 and
 * start processing request 1; 16 seconds: finish processing request 1.
 * Therefore, 11 seconds after the program there are only 2 not yet finished
 * requests, 3 being processed, and 1 still in the queue. Thus, the answer is
 * [1].
 * 
 * Check out the image below for better understanding:
 * 
 * 
 * 
 * Input/Output
 * 
 * [execution time limit] 4 seconds (js)
 * 
 * [input] array.integer requestTime
 * 
 * A sorted array of non-negative integers. requestTime[i] represents the number
 * of seconds that will pass from the initial moment before the ith job request
 * comes in.
 * 
 * Guaranteed constraints: 1 ≤ requestTime.length ≤ 50, 0 ≤ requestTime[i] ≤
 * 100.
 * 
 * [input] array.integer jobProcess
 * 
 * Array of positive integers of the same length as requestTime. jobProcess[i]
 * represents the number of seconds it takes to process the ith job request.
 * 
 * Guaranteed constraints: jobProcess.length = requestTime.length, 1 ≤
 * jobProcess[i] ≤ 1000.
 * 
 * [input] integer timeFromStart
 * 
 * A non-negative integer. If some actions are due to happen timeFromStart
 * seconds after your program was launched, assume that they have already
 * happened.
 * 
 * Guaranteed constraints: 0 ≤ timeFromStart ≤ 100.
 * 
 * [output] array.integer
 * 
 * Array of requests' 0-based indices, the state of the queue in timeFromStart
 * seconds from the moment your program was launched. The first element should
 * be the head of the queue, and the last element should be its tail.
 * 
 * 
 */
 
class Process {
    #requestTime
    #processTime
    
    #startTime
    #order
    
    constructor(requestTime, processTime, order){
        this.#requestTime = requestTime
        this.#processTime = processTime
        this.#order = order
    }
    
    getRequestTime(){
        return this.#requestTime
    }
    
    getProcessTime(){
        return this.#processTime
    }
    
    getOrder(){
        return this.#order
    }
    
    setStartTime(startTime){
        this.#startTime = startTime
    }
    
    getFinishProcessTime(){
        return this.#startTime + this.#processTime
    }
    
    toString(){
        return `requestTime: ${this.#requestTime}, processTime:  ${this.#processTime}, startTime: ${this.#startTime}, order: ${this.#order}`
    }
} 

class ManageProcess{
    
    #queue
    #exeProcess
    
    constructor(){
		this.#queue = new Array()
        this.#exeProcess = null
	}
    
    //TODO need to improve
    #getMinProcessTimeInQueue(){
        if(this.#queue.length === 0){
            throw new Error('Queue can not be empty')
        }
        
        let min = [10000000, -1]
        this.#queue.map((process, ind) => {
            if(process.getProcessTime() < min[0]){
                min = [process.getProcessTime(), ind]
            }
        })
        
        const outputProcess = this.#queue[min[1]]
        this.#queue.splice(min[1], 1)
        
        return outputProcess
    }
    
    #calExeProcess(currentTime){
        let tempProcess = this.#exeProcess
        while(this.#queue.length > 0       
            && tempProcess.getFinishProcessTime() < currentTime){
                
            const finishTime = tempProcess.getFinishProcessTime() 
            tempProcess = this.#getMinProcessTimeInQueue()
            tempProcess.setStartTime(finishTime)
            this.#exeProcess = tempProcess
        }
    }
    
    #calQueueAtProcess(processes){
        const currentTime = processes[0].getRequestTime()
        processes.map(process => this.#queue.push(process))
        this.#calExeProcess(currentTime)
    }
    
    addProcess(processes){
        
        const initQueueStatus = () => {
            processes[0].setStartTime(processes[0].getRequestTime())
            this.#exeProcess = processes[0]
            processes.splice(0, 1)
            processes.map(process => this.#queue.push(process))
        }
        
        // initial
        if(this.#exeProcess === null){
            initQueueStatus()
        }else{
            
            const currentTime = processes[0].getRequestTime()
            this.#calExeProcess(currentTime)
            if(this.#exeProcess.getFinishProcessTime() < currentTime){
                initQueueStatus()
            }else{
                this.#calQueueAtProcess(processes)  
            }
              
        }
    }
    
    getQueueStatusAt(timeFromStart){
        let tempProcess = this.#exeProcess
        while(this.#queue.length > 0 &&
            tempProcess.getFinishProcessTime() <= timeFromStart){
                
            const finishTime = tempProcess.getFinishProcessTime() 
            tempProcess = this.#getMinProcessTimeInQueue()
            tempProcess.setStartTime(finishTime)
        }
        
        return this.#queue
        .sort((p1, p2) => p1.getProcessTime() - p2.getProcessTime())
        .map(process => process.getOrder())
    }
    
}

/**
 * Custom test: hazzzzzzzzzzzzzzzzz
 * 
 *  requestTime: [0, 0, 0, 0, 1, 2, 3]
    jobProcess: [3, 1, 2, 1, 11, 1, 1]
    timeFromStart: 3
    Expected Output:
    [3, 5, 6, 2, 4]
    
    requestTime: [0, 10, 10, 11]
    jobProcess: [10, 110, 11, 20]
    timeFromStart: 10
    Expected Output:
    [1]
    
    requestTime: [0, 0, 11]
    jobProcess: [20, 10, 12]
    timeFromStart: 9
    Expected Output:
    [1]
    
    requestTime: [0, 0, 0, 0, 0, 10, 10, 12]
    jobProcess:  [1, 1, 1, 1, 1, 44, 41, 44]
    timeFromStart: 13
    Expected Output:
    [6, 7]
    
    requestTime: [1, 1, 1, 1, 1, 10, 10, 12]
    jobProcess: [1, 1, 6, 1, 1, 44, 41, 15]
    timeFromStart: 14
    Expected Output:
    [7, 5]
 */
solution = (requestTime, jobProcess, timeFromStart) => {
    const manageProcess = new ManageProcess()
    
    let requestJobProcess =  
    requestTime.map((requestT, ind) => [requestT, jobProcess[ind], ind])
    
    requestJobProcess = requestJobProcess
                        .filter(requestJProcess => requestJProcess[0] <= timeFromStart)
                        
    for(let ind = 0; ind < requestJobProcess.length;){
        let p = ind + 1
        let processes = [new Process(requestJobProcess[ind][0], 
                            requestJobProcess[ind][1], requestJobProcess[ind][2])]
        while(p < requestJobProcess.length && requestJobProcess[p][0] === requestJobProcess[ind][0]){
            processes.push(new Process(requestJobProcess[p][0], 
                            requestJobProcess[p][1], requestJobProcess[p][2]))
            p++
        }
        ind = p
        
        manageProcess.addProcess(processes)
    }
    
    return manageProcess.getQueueStatusAt(timeFromStart)
}
