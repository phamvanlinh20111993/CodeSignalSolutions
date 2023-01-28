/**
 * https://app.codesignal.com/company-challenges/datto/SBsoAzWpYj8xCeKjW
 * 
 * Datto is working on creating a backup-time estimator. For simplicity's sake, let's assume that there is only one server completing multiple jobs (i.e. backups) in parallel. Without any parallelization, the ith job takes backupDurationi units of time to be backed up. But if there are n jobs running in parallel, the backup process goes n times slower for each job.

For the ith job you know the value of backupDurationi and the moment startTimesi it was added to the backup queue. Your task is to estimate all the backup completion times. Note that it is impossible to have more than maxThreads threads performing backups in parallel. If there is more than one job waiting to be backed up, the one with the smallest initial backup time is chosen. It's guaranteed that all the jobs are added to the backup queue at different times.

Example

For startTimes = [461620201, 461620202, 461620203],
backupDuration = [2, 2, 2], and maxThreads = 2,
the output should be
solution(startTimes, backupDuration, maxThreads) = [461620204.0, 461620206.0, 461620207.0].

At moment 461620201 the first job is added to the queue and its backup process starts.

At moment 461620202 the second job is added to the second thread, while the first job is 50% complete in the first thread.

At moment 461620203 another job is added, but both of the threads are busy, so nothing happens (the first job is 75% complete, and second one is 25%).

At moment 461620204 the first job finishes in the first thread, and the third job starts to run. The second job is 50% complete at the moment.

At moment 461620206 the second job finishes, and the third one is 50% complete.

At moment 461620207 the third job finishes its work.

Check out the image below for better understanding.


For startTimes = [461620201, 461620202, 461620203], backupDuration = [2, 2, 2],
and maxThreads = 3, the output should be
solution(startTimes, backupDuration, maxThreads) = [461620204.5, 461620206.5, 461620207.0].
Input/Output

[execution time limit] 4 seconds (js)

[input] array.integer startTimes

A sorted array of unique positive integers. The ith element is the time the ith job was added to the backup queue.

Guaranteed constraints:
0 ≤ startTimes.length ≤ 400,
461620201 ≤ startTimes[i] ≤ 461629979.

[input] array.integer backupDuration

Array of positive integers of the same size as startTimes. For each valid i backupDuration[i] is the amount of time it takes to back up the ith job if there is only one running thread.

Guaranteed constraints:
backupDuration.length = startTimes.length,
1 ≤ backupDuration[i] ≤ 104.

[input] integer maxThreads

The maximum number of threads that can work in parallel.

Guaranteed constraints:
1 ≤ maxThreads ≤ 45.

[output] array.float

Array of the same length as startTimes, where the ith element is the moment by which the ith job is backed up.
Your output will be considered correct if the absolute error of each output value does not exceed 10-5.
 */

class Thread {
	#changeTime;
    #size;
    #index;
    
    constructor({startTime, size, index}){
        this.#changeTime = startTime
        this.#size = size
        this.#index = index
    }
    
    #isThreadEnd(uploadedTime, speed){
    	return uploadedTime > this.getTimeEnd(speed)
    }
    
    getTimeEnd(speed){
        return this.#changeTime + (this.#size / speed)
    }
    
    setChangeTime(changeTime){
    	this.#changeTime = changeTime
    }
   
    updateThreadStatus(uploadedTime, speed){
    	if(uploadedTime < this.#changeTime){
    		throw new Error(`Wrong uploadedTime constraints: uploadedTime >= changeTime, but
                    uploadedTime (${uploadedTime}) < changeTime (${this.#changeTime}) `)
    	}
        if(this.#isThreadEnd(uploadedTime, speed)){
            this.#changeTime = this.getTimeEnd(speed)
        }
        this.#size -= speed * (uploadedTime - this.#changeTime);
        this.#changeTime = uploadedTime;
    }
    
    get index(){
    	return this.#index
    }
}

class ManageTask {
	#totalThread = 0;
	#activeThreads = new Array();
	#queue = new Array();
	#markingFinishTasks = new Map();
	
	constructor(totalThread){
		this.#totalThread = totalThread;
	}
	
	#getAmountActiveThread(){
		return this.#activeThreads.length || 1;
	}
	
	#getSpeed(){
		return 1 / this.#getAmountActiveThread();
	}
	
	#getMinTimeFreeThread(){
		let minTime = 461629980 * 10000 * 45,// > 461629979
            threadPos = -1;
		this.#activeThreads.map((thread, ind) => {
			const timeThreadEnd = thread.getTimeEnd(this.#getSpeed());
           // console.log("timeThreadEnd", timeThreadEnd)
			if(timeThreadEnd < minTime){
				minTime = timeThreadEnd
				threadPos = ind
			}
		})
		
		return {minTime, threadPos}
	}
	
    #updateActiveThreadsAt({minTime, threadPos}){
        const taskF = this.#activeThreads[threadPos]
		this.#markingFinishTasks.set(taskF.index, taskF.getTimeEnd(this.#getSpeed()))
		// step 2: update status threads at mintime
		this.#activeThreads = this.#activeThreads.map(thread => {
			thread.index !== taskF.index && thread.updateThreadStatus(minTime, this.#getSpeed())
			return thread
		})
		// step 3: remove task finished from activeTheads
		this.#activeThreads = this.#activeThreads.filter(thread => thread.index !== taskF.index)
		// step 4: add more task from queue to thread
		while(this.#queue.length > 0 && this.#activeThreads.length < this.#totalThread){
			const currThread = this.#queue.shift()
			currThread.setChangeTime(minTime)
			this.#activeThreads.push(currThread)
		}
    }
    
	#updateThreadStatusAt(time){
		let data = this.#getMinTimeFreeThread()
		while(data.minTime < time){
			this.#updateActiveThreadsAt(data)
			data = this.#getMinTimeFreeThread()
		}
		
		// no task in thread finish at 'time' 
		this.#activeThreads = this.#activeThreads.map(thread => {
			thread.updateThreadStatus(time, this.#getSpeed())
			return thread
		})
	}
	
	addTask(thread, time){
		this.#updateThreadStatusAt(time);
		if(this.#activeThreads.length < this.#totalThread){
			this.#activeThreads.push(thread)
		}else{
			this.#queue.push(thread)
		}
	}
	
	#calEndAllThread(){
		while(this.#activeThreads.length > 0){
			this.#updateActiveThreadsAt(this.#getMinTimeFreeThread())
		}
	}
	
	get response(){
		this.#calEndAllThread()
		const sortMapByInd = new Map([...this.#markingFinishTasks.entries()]
        .sort((a,b) => parseInt(a) - parseInt(b)));
		
        return Array.from(sortMapByInd.values())
	}
}

solution = (startTimes, backupDuration, maxThreads) => {
	let tasks = startTimes.map((startTime, index) => ({startTime, size: backupDuration[index], index}))
	const manageTask = new ManageTask(maxThreads)
	tasks.map(task => manageTask.addTask(new Thread(task), task.startTime))
	
	return manageTask.response
}
