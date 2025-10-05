package LeetCode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Url: https://leetcode.com/problems/network-delay-time/
 * 
 * You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.

We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.

 

Example 1:


Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
Output: 2
Example 2:

Input: times = [[1,2,1]], n = 2, k = 1
Output: 1
Example 3:

Input: times = [[1,2,1]], n = 2, k = 2
Output: -1
 

Constraints:

1 <= k <= n <= 100
1 <= times.length <= 6000
times[i].length == 3
1 <= ui, vi <= n
ui != vi
0 <= wi <= 100
All the pairs (ui, vi) are unique. (i.e., no multiple edges.)
 

 * 
 */
public class NetworkDelayTime {
	
	public record NodeDis(int from, int to, int cost) {
	}

	public int networkDelayTime(int[][] times, int n, int k) {
		PriorityQueue<NodeDis> prioQueue = new PriorityQueue<>(new Comparator<NodeDis>() {
			@Override
			public int compare(NodeDis p1, NodeDis p2) {
				return p1.cost - p2.cost;
			}
		});

		prioQueue.add(new NodeDis(k - 1, k - 1, 0));

		List<List<NodeDis>> adjMatrix = new ArrayList<>();
		for (int ind = 0; ind < n; ind++) {
			adjMatrix.add(new ArrayList<>());
		}

		for (int[] time : times) {
			adjMatrix.get(time[0] - 1).add(new NodeDis(time[0] - 1, time[1] - 1, time[2]));
		}
		// System.out.println("adjMatrix = " + adjMatrix);

		Set<Integer> nodeVisit = new HashSet<>();
		Map<String, Integer> nodeCost = new HashMap<>();
		while (nodeVisit.size() < n && prioQueue.size() > 0) {
			NodeDis node = prioQueue.poll();
			nodeVisit.add(node.to);
			// System.out.println(node + " | " + nodeVisit);
			List<NodeDis> nodeList = adjMatrix.get(node.to);
			for (NodeDis nodeDis : nodeList) {
				if (!nodeVisit.contains(nodeDis.to)) {
					prioQueue.add(new NodeDis(node.from, nodeDis.to, node.cost + nodeDis.cost));
					Integer minCost = nodeCost.getOrDefault(node.from + " " + nodeDis.to, 1000000000);
					minCost = minCost > node.cost + nodeDis.cost ? node.cost + nodeDis.cost : minCost;
					nodeCost.put(node.from + " " + nodeDis.to, minCost);
				}
			}
		}

		// System.out.println(nodeCost);
		if (nodeCost.size() < n - 1)
			return -1;
		Integer res = -1;
		for (Integer v : nodeCost.values()) {
			if (res < v)
				res = v;
		}

		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
