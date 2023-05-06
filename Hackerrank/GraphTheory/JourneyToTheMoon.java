package Hackerrank.GraphTheory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JourneyToTheMoon {

    /**
     * we have a group of node:   groups = [1,2,4,5,6] 
     * => sums = [18,17,15,11,6]
     * => calculate total possible sum combine between groups
     **/
    public static long totalCount(List<Integer> groups) {
	List<Long> sums = new ArrayList<>();
	for (int i = 0; i < groups.size(); i++) {
	    sums.add(0l);
	}
	Long s = 0l;
	for (int i = sums.size() - 1; i >= 0; i--) {
	    s += groups.get(i);
	    sums.set(i, s);
	}
	long t = 0l;
	for (int i = 1; i < sums.size(); i++) {
	    t += groups.get(i - 1) * sums.get(i);
	}
	return t;
    }

    /**
     * Dfs to each node, marked visited node then divide to groups
     * 
     * @param vertex
     * @param adjList
     * @param isVisited
     * @return
     */
    public static Integer dfs(int vertex, List<Set<Integer>> adjList, Set<Integer> isVisited) {

	Integer count = 1;
	isVisited.add(vertex);
	List<Integer> vertexes = new ArrayList<>(adjList.get(vertex));
	for (Integer vert : vertexes) {
	    if (!isVisited.contains(vert)) {
		count += dfs(vert, adjList, isVisited);
	    }
	}

	return count;
    }

    public static long journeyToMoon(int n, List<List<Integer>> astronaut) {
	List<Set<Integer>> adjList = new ArrayList<>();
	for (int i = 0; i < n; i++) {
	    adjList.add(new HashSet<Integer>());
	}

	for (List<Integer> node : astronaut) {
	    adjList.get(node.get(0)).add(node.get(1));
	    adjList.get(node.get(1)).add(node.get(0));
	}

	List<Integer> groups = new ArrayList<>();

	Set<Integer> isVisited = new HashSet<>();
	for (int vertex = 0; vertex < n; vertex++) {
	    if (!isVisited.contains(vertex)) {
		Integer count = dfs(vertex, adjList, isVisited);
		groups.add(count);
	    }
	}

	for (Integer g : groups) {
	    System.out.println(g);
	}

	return totalCount(groups);

    }

    public static void main(String[] args) {
	// TODO Auto-generated method stub

    }

}
