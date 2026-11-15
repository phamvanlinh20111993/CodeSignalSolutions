package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * url: https://leetcode.com/problems/find-servers-that-handled-most-number-of-requests/?envType=daily-question&envId=2026-11-09
 * 
 * You have k servers numbered from 0 to k-1 that are being used to handle multiple requests simultaneously. Each server has infinite computational capacity but cannot handle more than one request at a time. The requests are assigned to servers according to a specific algorithm:

The ith (0-indexed) request arrives.
If all servers are busy, the request is dropped (not handled at all).
If the (i % k)th server is available, assign the request to that server.
Otherwise, assign the request to the next available server (wrapping around the list of servers and starting from 0 if necessary). For example, if the ith server is busy, try to assign the request to the (i+1)th server, then the (i+2)th server, and so on.
You are given a strictly increasing array arrival of positive integers, where arrival[i] represents the arrival time of the ith request, and another array load, where load[i] represents the load of the ith request (the time it takes to complete). Your goal is to find the busiest server(s). A server is considered busiest if it handled the most number of requests successfully among all the servers.

Return a list containing the IDs (0-indexed) of the busiest server(s). You may return the IDs in any order.

 

Example 1:


Input: k = 3, arrival = [1,2,3,4,5], load = [5,2,3,3,3] 
Output: [1] 
Explanation: 
All of the servers start out available.
The first 3 requests are handled by the first 3 servers in order.
Request 3 comes in. Server 0 is busy, so it's assigned to the next available server, which is 1.
Request 4 comes in. It cannot be handled since all servers are busy, so it is dropped.
Servers 0 and 2 handled one request each, while server 1 handled two requests. Hence server 1 is the busiest server.
Example 2:

Input: k = 3, arrival = [1,2,3,4], load = [1,2,1,2]
Output: [0]
Explanation: 
The first 3 requests are handled by first 3 servers.
Request 3 comes in. It is handled by server 0 since the server is available.
Server 0 handled two requests, while servers 1 and 2 handled one request each. Hence server 0 is the busiest server.
Example 3:

Input: k = 3, arrival = [1,2,3], load = [10,12,11]
Output: [0,1,2]
Explanation: Each server handles a single request, so they are all considered the busiest.
 

Constraints:

1 <= k <= 105
1 <= arrival.length, load.length <= 105
arrival.length == load.length
1 <= arrival[i], load[i] <= 109
arrival is strictly increasing.
 * 
 */
public class Find_Servers_That_Handled_Most_Number_of_Requests_1606 {
	
	class Task {
		int arrival;
		int load;
		int index;
		long timeToHandle;

		public Task(int arrival, int load, int index) {
			this.arrival = arrival;
			this.load = load;
			this.index = index;
			this.timeToHandle = (long) arrival + load;
		}

		@Override
		public String toString() {
			return "Task{" + "arrival=" + arrival + ", load=" + load + ", timeToHandle=" + timeToHandle + ", index="
					+ index + '}';
		}

	}

	class ServerInfo implements Comparable<ServerInfo> {
		int serverId;
		Task task;

		public ServerInfo(int serverId, Task task) {
			this.serverId = serverId;
			this.task = task;
		}

		@Override
		public int compareTo(ServerInfo other) {
			return Long.compare(this.task.timeToHandle, other.task.timeToHandle);
		}

		@Override
		public String toString() {
			return "ServerInfo{" + "serverId=" + serverId + ", task=" + task + '}';
		}
	}

	public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
		PriorityQueue<ServerInfo> taskQueue = new PriorityQueue<ServerInfo>();
		TreeSet<Integer> availableServerSet = new TreeSet<>(); // Red-black tree,
		int[] taskServerCount = new int[k];
		for (int serverId = 0; serverId < k; serverId++) {
			availableServerSet.add(serverId);
			taskServerCount[serverId] = 0;
		}

		List<Task> tasks = new ArrayList<>();
		for (int ind = 0; ind < arrival.length; ind++) {
			tasks.add(new Task(arrival[ind], load[ind], ind));
		}

		Integer maxServerCount = 0;
		List<Integer> res = new ArrayList<>();
		for (Task task : tasks) {
			while (!taskQueue.isEmpty() && taskQueue.peek().task.timeToHandle <= task.arrival) {
				ServerInfo sv = taskQueue.poll();
				availableServerSet.add(sv.serverId);
			}

			int ikth = task.index % k;
			// System.out.println("available at " + task.index + " : " +
			// availableServerSet);
			if (availableServerSet.size() > 0) {
				if (!availableServerSet.contains(ikth)) {
					Integer nextAvailableServer = availableServerSet.higher(ikth);
					ikth = nextAvailableServer == null ? availableServerSet.first() : nextAvailableServer;
				}

				availableServerSet.add(ikth);
				taskQueue.add(new ServerInfo(ikth, task));
				availableServerSet.remove(ikth);
				taskServerCount[ikth]++;
				maxServerCount = Math.max(taskServerCount[ikth], maxServerCount);
			}

			// System.out.println(taskQueue);

		}

		for (int serverId = 0; serverId < k; serverId++) {
			if (taskServerCount[serverId] == maxServerCount) {
				res.add(serverId);
			}
			// System.out.print(taskServerCount[serverId]+" ");
		}

		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
