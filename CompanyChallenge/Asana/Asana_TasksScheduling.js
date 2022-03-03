
/**
 * As a new engineer at Asana, you're working on a feature that helps users
 * estimate how long it will take them to complete their workload. All a user
 * has to do is specify the number of hours they work every day, provide the
 * time allocated for each task, and the feature automatically calculates the
 * number of days it will take for the user to finish all of their tasks. Since
 * you think it's a bad habit to leave a given task unfinished at the end of a
 * day, tasks should be distributed so that each one will be completed during
 * the working hours of a single day.
 * 
 * Given the time allocated to a user's tasks and their daily workingHours,
 * return the minimum number of days necessary for them to complete the given
 * tasks. If there's no way to tackle all of them, return -1 instead.
 * 
 * Example
 * 
 * For workingHours = 2 and tasks = [1, 2, 1], the output should be
 * tasksScheduling(workingHours, tasks) = 2.
 * 
 * The first and the third tasks can be completed during the first day, and the
 * second task can be completed during the second day.
 * 
 * For workingHours = 3 and tasks = [2, 2, 2], the output should be
 * tasksScheduling(workingHours, tasks) = 3.
 * 
 * Completing any two tasks in a day requires 4 hours which is more than
 * workingHours, so it is impossible to complete more than one task during a
 * single day, meaning that the total number of days needed to complete all the
 * tasks is 3.
 * 
 * For workingHours = 2 and tasks = [1, 1, 3], the output should be
 * tasksScheduling(workingHours, tasks) = -1.
 * 
 * It is impossible to complete the third task during a single day.
 * 
 * Input/Output
 * 
 * [execution time limit] 4 seconds (js)
 * 
 * [input] integer workingHours
 * 
 * A positive integer representing the number of hours a user works in a day.
 * 
 * Guaranteed constraints: 1 ≤ workingHours ≤ 15.
 * 
 * [input] array.integer tasks
 * 
 * An array of positive integers. tasks[i] equals the time it will take to
 * finish the ith task.
 * 
 * Guaranteed constraints: 1 ≤ tasks.length ≤ 12, 1 ≤ tasks[i] ≤ 15.
 * 
 * [output] integer
 * 
 * The minimum number of days required to complete all the tasks, or -1 if it is
 * impossible to finish all of them.
 */

tasksScheduling = (tasks, workingHours) => {
    let countResponse = 0
    let tasksOverWorkingHour = workingHours.filter(v => v > tasks)
    if(tasksOverWorkingHour && tasksOverWorkingHour.length > 0){
        return -1
    }
    countResponse = workingHours.filter(v => v == tasks).length
    workingHours = workingHours.filter(v => v < tasks)
    
    let loopFindAllSumSet = workingHours =>{
        let isEqualTasks = false;
        let nearestValue = ""
        let nearestTotal = 0
        dpGetSumSetTaskEqualWorkingHours = (current, tasks) =>{
            // reduce recursive not necessary
            if(isEqualTasks){
                return
            }
            if(current <= 0){
                let totalSumSet = new Map()
                workingHours.map((v, ind) =>{ 
                    if(nearestTotal < v){
                        nearestTotal = v
                        nearestValue = ind + ""
                    }
                    totalSumSet.set(v, ind + "")
                })
                return totalSumSet
            }
            
            let totalSumSet = dpGetSumSetTaskEqualWorkingHours(current - 1, tasks)
            let currentTask = workingHours[current]
        
            totalSumSetNew = new Map(totalSumSet)
        // console.log('totalSumSet before', totalSumSet)
            totalSumSet.forEach((value, key) => {
                if(currentTask + key <= tasks && !value.includes(current)){
                    if(key + currentTask == tasks){
                        nearestValue = value + "-" + current
                        nearestTotal = key + currentTask
                        isEqualTasks = true
                        return
                    }
                    if(key + currentTask > nearestTotal){
                        nearestValue = value +"-" + current
                        nearestTotal = key + currentTask
                    }
                    
                    totalSumSetNew.set(key + currentTask, value + '-'+ current)
                }
            }) 
        // console.log('totalSumSet after', totalSumSetNew)
            return totalSumSetNew
        }
        
        dpGetSumSetTaskEqualWorkingHours(workingHours.length - 1, tasks)
      // console.log('data', nearestValue, nearestTotal)
        return [nearestValue, nearestTotal]
    }
    
    let totalSum = workingHours.reduce((t, v) => t += v, 0)
    while(totalSum > tasks){
        const [nearestValue, nearestTotal] = loopFindAllSumSet(workingHours)
        let nearestValueArr = nearestValue.split("-")
        workingHours = workingHours.filter((v, ind) => !nearestValueArr.includes(ind+""))
        countResponse++
        totalSum = workingHours.reduce((t, v) => t += v, 0)
    }
    
    if(workingHours.length > 0){
        countResponse++
    }
    
    return countResponse
}
