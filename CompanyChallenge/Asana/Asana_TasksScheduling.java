package CompanyChallenge.Asana;

import java.util.ArrayList;
import java.util.List;

class TaskScheduling{
	
	List<Integer> task;
	
	public TaskScheduling() {
		this.task = new ArrayList<>();
	}

	/**
	 * @return the task
	 */
	public List<Integer> getTask() {
		return task;
	}

	/**
	 * @param task the task to set
	 */
	public void setTask(List<Integer> task) {
		this.task = task;
	}
	
	/**
	 * 
	 * @param index
	 * @param value
	 */
	public void setTaskInPos(Integer index, Integer value) {
		this.task.set(index, value);
	}
	
	/**
	 * 
	 * @param value
	 */
	public void setTaskSequence(Integer value) {
		this.task.add(value);
	}
}

public class Asana_TasksScheduling {

	static boolean[] isTake;
	static int minTotal, count;
	static List<TaskScheduling> taskScheduling;

	static void calAllPosible(int workingHours, int[] tasks, int start, int total) {

		for (int i = 0; i < tasks.length; i++) {
			if (!isTake[i]) {
				boolean isLast = false;
				if (total + tasks[i] <= workingHours) {
//					if(taskScheduling.size() == count) {
//						taskScheduling.set(count, new TaskScheduling());
//						taskScheduling.get(count).setTaskSequence(tasks[i]);
//					}
					total += tasks[i];
				} else {
					total = tasks[i];
					isLast = true;
					count++;
					
//					taskScheduling.set(count, new TaskScheduling());
//					taskScheduling.get(count).setTaskSequence(tasks[i]);
				}
				isTake[i] = true;

				for (int j = 0; j < tasks.length; j++) {
					if (!isTake[j]) {
						calAllPosible(workingHours, tasks, j, total);
						break;
					}
				}

				// check result
				int allChecked = 0;
				for (int k = 0; k < tasks.length; k++) {
					if (isTake[k]) {
						allChecked++;
					}
				}
				if (allChecked == tasks.length) {
					System.out.println(count);
					if(!isLast) 
						count++;
					minTotal = minTotal > count ? count : minTotal;
				}
				if(isLast) count--;
				total -= tasks[i];
				isTake[i] = false;
			}
		}
	}

	static int tasksScheduling(int workingHours, int[] tasks) {
		int i;
		minTotal = Integer.MAX_VALUE;
		count = 0;
		isTake = new boolean[tasks.length];
		taskScheduling = new ArrayList<>();

		for (i = 0; i < tasks.length; i++) {
			if (tasks[i] > workingHours) {
				return -1;
			}
			isTake[i] = false;
		}

		calAllPosible(workingHours, tasks, 0, 0);
		System.out.println("value " + minTotal);
		return minTotal;
	}

	public static void main(String[] args) {

		// 7
		System.out.println("############# test 1 #############");
		int workingHours = 15;
		int[] tasks = { 4, 15, 6, 3, 13, 4, 7, 14, 8, 15, 5, 8 };
		tasksScheduling(workingHours, tasks);
	}

}
