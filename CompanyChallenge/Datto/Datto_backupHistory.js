/**
 * url: https://app.codesignal.com/company-challenges/datto/kyntbF46nN6CzMRz8
 * 
 * 
  During a team hackathon at Datto, you decide to quickly implement file backup history. But to do this you first need to know how many backups of a certain file already exist.

You are given the creationTimes of files in the database, where creationTimes[i] stands for the time the ith file was created. Assume that all files are configured to be backed up every k seconds after their creation time, but this default behavior can be changed manually.

The system administrator can make one of two manual request types:

cancel all future auto-backups for the specified file;
immediately create a backup of the specified file.
Note that manual requests are processed before automatic actions, so if a cancellation request coincides with an auto-backup, the auto-backup doesn't happen. In a given second only 1 backup of a file is possible, so in the case that a manual request coincides with an auto-backup, only one backup is made.

Given a list of backupRequests, your task is to find the number of backups that have been made of each file by the given time t. Events that occurred at exactly t seconds should be included in the answer.

Example

For creationTimes = [461620201, 461620203, 461620207],

backupRequests = [[1, 0, 461620202],
                  [1, 2, 461620208],
                  [0, 2, 461620210],
                  [1, 0, 461620204],
                  [1, 1, 461620209],
                  [1, 1, 461620203]]
k = 3, and t = 461620210, the output should be
solution(creationTimes, backupRequests, k, t) = [4, 3, 1].

Here's how the backups were created:

for file 0: manual backups at 461620202 and 461620204 and automatic ones at 461620207, 461620210 (461620204 is skipped because it was made manually);
for file 1: manual backups at times 461620203 and 461620209 and auto-backup at time 461620206;
for file 2: manual backup at time 461620208 and a cancellation at time 461620210, which canceled the auto-backup at 461620210.
Check out the image below for better understanding:



Input/Output

[execution time limit] 4 seconds (js)

[input] array.integer creationTimes

Array of timestamps of when the files were created, sorted in ascending order.

Guaranteed constraints:
1 ≤ creationTimes.length ≤ 1000,
4 · 108 ≤ creationTimes[i] ≤ 5 · 108.

[input] array.array.integer backupRequests

A list of requests. For each valid i the ith request is given as backupRequests[i] = [type, file, time], where:

type is the type of request, 0 for cancellation and 1 for manual backup;
file is the file number, 0 ≤ file < creationTimes.length;
time is the time the request was made, which is guaranteed to be a positive integer not less than creationTimes[file].
It is guaranteed that no two requests to the same file occur simultaneously.

Guaranteed constraints:
1 ≤ backupRequests.length ≤ 1000.

[input] integer k

A positive integer.

Guaranteed constraints:
2 ≤ k ≤ 10.

[input] integer t

A positive integer.

Guaranteed constraints:
108 ≤ t ≤ 109.

[output] array.integer

Array of the same length as creationTimes, where the ith element is the number of times the ith file was backed up.
 */


class Task {

	#createdTime
	#k
	#backupAt = new Set()
	#cancelBackupTime
	#timeEnd
	#isCancelAutoBackup

	constructor(createdTime, k, t) {
		this.#k = k
		this.#createdTime = createdTime
		this.#timeEnd = t
		this.#isCancelAutoBackup = false
	}

	setCancelTime(cancelBackupTime) {
		if (!this.#isCancelAutoBackup) {
			this.#cancelBackupTime = cancelBackupTime
			this.#isCancelAutoBackup = true
		} else {
			this.#cancelBackupTime = this.#cancelBackupTime > cancelBackupTime
				? cancelBackupTime : this.#cancelBackupTime
		}
	}

	setManualBackup(manualBackupTime) {
		manualBackupTime <= this.#timeEnd && this.#backupAt.add(manualBackupTime)
	}

	getTotalBackupTime() {
		// v in range (a, b]
		const isInRange = (v, range = [0, 0]) => v > range[0] && v <= range[1]

		const isConcideBackup = (v, leftRange, k) => v > leftRange && (v - leftRange) % k === 0
		// checked
		const cancelBackupTime = this.#isCancelAutoBackup === true &&
			this.#timeEnd > this.#cancelBackupTime ? this.#cancelBackupTime : this.#timeEnd
		const autoBackupRange = [this.#createdTime, cancelBackupTime]

		let amountAutoBackup = Math.floor((autoBackupRange[1] - autoBackupRange[0]) / this.#k)
		amountAutoBackup = amountAutoBackup < 0 ? 0 : amountAutoBackup
		let total = this.#backupAt.size + amountAutoBackup
		// check duplicate 
		Array.from(this.#backupAt)
			.map(v => isInRange(v, autoBackupRange)
				&& isConcideBackup(v, autoBackupRange[0], this.#k) ? total-- : '')
		 // check duplicate 
        if(this.#isCancelAutoBackup === true && this.#cancelBackupTime <= this.#timeEnd
            && isConcideBackup(this.#cancelBackupTime, autoBackupRange[0], this.#k)){
            total --
        }

		return total
	}
}

class TaskManager {
	#taskManager
	#t
	#k

	constructor(t, k) {
		this.#taskManager = new Array()
		this.#t = t
		this.#k = k
	}

	createTask(createTime) {
		this.#taskManager.push(new Task(createTime, this.#k, this.#t))
	}

	/** [0, 1, 2] => [request type, request to file index, request time]**/
	requestBackup(request = [0, 0, 0]) {
		if (request.length < 3) {
			throw Error("Request length array must be greater than or equal 3")
		}

		const taskIndex = this.#taskManager[request[1]]
		// cancel backup file
		if (request[0] === 0) {
			taskIndex.setCancelTime(request[2])
			// manual backup file
		} else {
			taskIndex.setManualBackup(request[2])
		}
	}

	getResult() {
		return this.#taskManager.map(task => task.getTotalBackupTime())
	}
}

solution = (creationTimes, backupRequests, k, t) => {
	const taskManager = new TaskManager(t, k)
	creationTimes.map(v => taskManager.createTask(v))

	backupRequests
		.map(request => taskManager.requestBackup(request))

	return taskManager.getResult();
}


// Test1, output: [4, 3, 1]
const creationTimes = [461620201, 461620203, 461620207],
	backupRequests = [
		[1, 0, 461620202],
		[1, 2, 461620208],
		[0, 2, 461620210],
		[1, 0, 461620204],
		[1, 1, 461620209],
		[1, 1, 461620203]
	],
	k = 3,
	t = 461620210
console.log("############################## Test1, output: [4, 3, 1] ############################")
console.log('out: ', solution(creationTimes, backupRequests, k, t));



// Test2, output: [2, 0, 0, 0]
const creationTimes1 = [461620203, 461620206, 461620207, 461620214],
	backupRequests1 = [
		[1, 0, 461620205],
		[1, 0, 461620206],
		[1, 0, 461620210]
	],
	k1 = 2,
	t1 = 461620206
console.log("############################## Test2, output: [2, 0, 0, 0] ############################")
console.log('out: ', solution(creationTimes1, backupRequests1, k1, t1));



// Test3, output: [39993, 39991, 163, 9899]
const creationTimes2 = [461620242, 461620255, 461620439, 461720700],
	backupRequests2 = [
		[1, 0, 461620253],
		[1, 1, 461620255],
		[0, 3, 461770200],
		[0, 3, 461815200],
		[1, 2, 461620783],
		[1, 0, 461620434],
		[1, 1, 461620302],
		[0, 2, 461621253]
	],
	k2 = 5,
	t2 = 461820200
console.log("############################## Test3, output: [39993, 39991, 163, 9899] ############################")
console.log('out: ', solution(creationTimes2, backupRequests2, k2, t2));

