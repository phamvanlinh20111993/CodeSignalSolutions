/**
 * url: https://app.codesignal.com/company-challenges/dropbox/28W9bF4KvzS7L6ame
 * 
 * 
 * To set user expectations during file uploads, Dropbox indicates how long a
 * file will take to transfer with its upload time estimator.
 * 
 * Your goal is to implement a particular type of a upload time estimator.
 * Suppose that several clients are uploading files to Dropbox at the same given
 * moment. The ith client uploads a file of sizesi megabytes. For a single file,
 * upload speed is v megabytes per second, but if there are several files
 * uploading at the same time then uploads occur simultaneously in parallel
 * threads. For each thread the upload speed equals v / n, where n is the number
 * of currently active threads.
 * 
 * Given each file's size and its upload start time, determine the upload end
 * times.
 * 
 * Example
 * 
 * For sizes = [21, 10], uploadingStart = [100, 105], and v = 2 the output
 * should be undefined(sizes, uploadingStart, v) = [116, 115].
 * 
 * During the first 5 seconds only the first file is uploading at a speed of 2
 * MB/sec. Thus, when the second file upload begins, 10 MBs of the first file
 * will already have been uploaded. For the next 10 seconds both files upload
 * simultaneously with a speed of 1 MB/sec each. After this point (15 seconds
 * since the first file started uploading) the second file is uploaded
 * successfully, and only 1 MB of the first file remains to be transferred. It
 * takes 0.5 more seconds to finish uploading the first file. Rounding 115.5 up,
 * we obtain 116. Check out the image below for better understanding:
 * 
 * 
 * 
 * Input/Output
 * 
 * [execution time limit] 4 seconds (js)
 * 
 * [input] array.integer sizes
 * 
 * Array of positive integers. sizes[i] equals the size of the ith file in
 * megabytes.
 * 
 * Guaranteed constraints: 0 ≤ sizes.length ≤ 10, 1 ≤ sizes[i] ≤ 35.
 * 
 * [input] array.integer uploadingStart
 * 
 * Array of positive integers of the same length as sizes. uploadingStart[i]
 * represents the number of seconds that will pass from the current moment
 * before uploading of the ith file starts.
 * 
 * Guaranteed constraints: uploadingStart.length = sizes.length, 1 ≤
 * uploadingStart[i] ≤ 3 · 104.
 * 
 * [input] integer v
 * 
 * Guaranteed constraints: 1 ≤ v ≤ 100.
 * 
 * [output] array.integer
 * 
 * The ith element of the result should be equal to the number of seconds that
 * will pass from the current moment before uploading of the ith file finishes.
 * If the upload takes a non-integer number of seconds, round it up.
 */


class Task {
    #startTime;
    #originSize;
    #index;
    
    #currSize;
    #currTime;
    
    constructor(startTime, originSize, index){
        this.#startTime = startTime
        this.#originSize = originSize
        this.#index = index
        
        this.#currSize = originSize
        this.#currTime = startTime 
    }
    
    isTaskEnd(uploadedTime, speed){
    	return uploadedTime > this.getTimeEnd(speed)
    }
    
    updateSizeAfterUploadedTime(uploadedTime, speed){
    	if(uploadedTime < this.#currTime){
    		throw new Error(`Wrong uploadedTime constraints. current Time: ${this.#currTime}`)
    	}
    	
        if(this.isTaskEnd(uploadedTime, speed)){
            this.#currTime = this.getTimeEnd(speed)
            return 0
        }
        
        this.#currSize -= speed * (uploadedTime - this.#currTime);
        this.#currTime = uploadedTime;
        return this.#currSize
    }
    
    getTimeEnd(speed){
        return this.#currTime + (this.#currSize / speed)
    }
    
    get currTime(){
        return this.#currTime
    }
    
    get startTime(){
        return this.#startTime
    }
    
    get index(){
        return this.#index
    }
    
    get toString(){
    	return `taskInd: ${this.#index}, currSize: ${this.#currSize}, currTime: ${this.#currTime}`;
    }
}

class ManageTask {
    #manageTaskList = new Array();
    #currThread = 0;
    #currentTime;
    #speed = 0;
    // key is task index, value is time end
    #markTaskEndMap = new Map()
    
    constructor(speed){
        this.#speed = speed
    }
    
    getMinTimeTaskEnd(){
    	let minTime = 30001, // > 3*10^4
    	    taskInd = -1;
            
    	this.#manageTaskList
    	.filter(task => !this.#markTaskEndMap.has(task.index)) // task still running
    	.map((task, ind) => {
    		const timeEnd = task.getTimeEnd(this.#speed/this.getCurrentThread())
    		if(timeEnd < minTime){
    			minTime = timeEnd
    			taskInd = ind
    		}
    	})

    	return {minTime, taskInd}
    }
    
    getCurrentThread(){
        return this.#currThread <= 0 ? 1 : this.#currThread
    }
    
    checkPreviousTask(currTime){
    	let data = this.getMinTimeTaskEnd()

    	while(data.minTime < currTime){
    		this.#markTaskEndMap.set(this.#manageTaskList[data.taskInd].index, 
    				this.#manageTaskList[data.taskInd].getTimeEnd(this.#speed/this.getCurrentThread()));
    		
    		this.#manageTaskList = [...this.#manageTaskList
        	.filter(task => !this.#markTaskEndMap.has(task.index)) // task still running
        	.map(task => {
        		task.updateSizeAfterUploadedTime(data.minTime, this.#speed/this.getCurrentThread())
        		return task;
        	})]
    		this.#currThread --;
            	
    		data = this.getMinTimeTaskEnd();
    	}
    	
    	this.#manageTaskList = [...this.#manageTaskList
    	.map(task => {
    		task.updateSizeAfterUploadedTime(currTime, this.#speed/this.getCurrentThread())
    		return task;
    	})]
    }
    
    addTask(task){
        this.#currentTime = task.startTime;
        this.checkPreviousTask(this.#currentTime)
        this.#currThread ++;
        this.#manageTaskList.push(task)
        
        this.#manageTaskList.map(task => console.log(task.toString))
    }
    
    calAllTaskFinish(){
    	while(this.#manageTaskList.length > 0){
    		let data = this.getMinTimeTaskEnd();
    		this.#markTaskEndMap.set(this.#manageTaskList[data.taskInd].index, 
    				this.#manageTaskList[data.taskInd].getTimeEnd(this.#speed/this.getCurrentThread()));
    		
    		this.#manageTaskList = [...this.#manageTaskList
            	.filter(task => !this.#markTaskEndMap.has(task.index)) // task still running
            	.map(task => {
            		task.updateSizeAfterUploadedTime(data.minTime, this.#speed/this.getCurrentThread())
            		return task;
            	})]
    		
    		this.#currThread --;
    	}
    }
    
    get response(){
    	this.calAllTaskFinish();
    	const sortMapByInd = new Map([...this.#markTaskEndMap.entries()].sort());	
        return Array.from(sortMapByInd.values()).map(time => Math.ceil(time))
    }
}

solution = (sizes, uploadingStart, v)=> {
    
    let sortData = uploadingStart.map((time, ind) => ({ind: ind, size: sizes[ind], time: time}))
    sortData = sortData.sort((a, b) => a.time - b.time)
    
    manageTasks = new ManageTask(v)
    sortData.map( data => {
        manageTasks.addTask(new Task(data.time, data.size, data.ind))
    })
    
    return manageTasks.response
}
