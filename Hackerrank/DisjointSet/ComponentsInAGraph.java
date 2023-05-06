package Hackerrank.DisjointSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author PhamLinh
 * @url https://www.hackerrank.com/challenges/components-in-graph/problem?isFullScreen=true
 * 
 * There are  nodes in an undirected graph, and a number of edges connecting some nodes. In each edge, the first value will be between  and , inclusive. The second node will be between  and , inclusive. Given a list of edges, determine the size of the smallest and largest connected components that have  or more nodes. A node can have any number of connections. The highest node value will always be connected to at least  other node.

Note Single nodes should not be considered in the answer.

Example

image
The smaller component contains  nodes and the larger contains . Return the array .

Function Description
Complete the connectedComponents function in the editor below.

connectedComponents has the following parameter(s):
- int bg[n][2]: a 2-d array of integers that represent node ends of graph edges

Returns
- int[2]: an array with 2 integers, the smallest and largest component sizes

Input Format

The first line contains an integer , the size of .
Each of the next  lines contain two space-separated integers,  and .

Constraints

Sample Input

STDIN   Function
-----   --------
5       bg[] size n = 5
1 6     bg = [[1, 6],[2, 7], [3, 8], [4,9], [2, 6]]
2 7
3 8
4 9
2 6
Sample Output

2 4
Explanation

image

Since the component with node  contains only one node, it is not considered.

The number of vertices in the smallest connected component in the graph is  based on either  or .

The number of vertices in the largest connected component in the graph is  i.e. .
 */
public class ComponentsInAGraph {

    static Set<Integer> isVisited = new HashSet<>();

    public static Integer dfs(Map<Integer, List<Integer>> adjListSet, Integer node) {
	isVisited.add(node);
	Integer count = 0;
	for (Integer n : adjListSet.get(node)) {
	    if (!isVisited.contains(n)) {
		count += dfs(adjListSet, n) + 1;
	    }
	}

	return count;
    }

    public static List<Integer> componentsInGraph(List<List<Integer>> gb) {
	// Write your code here
	Map<Integer, List<Integer>> adjListSet = new HashMap<>();

	for (List<Integer> node : gb) {
	    List<Integer> connectedNodes = adjListSet.getOrDefault(node.get(0), new ArrayList<>());
	    connectedNodes.add(node.get(1));
	    adjListSet.put(node.get(0), connectedNodes);

	    List<Integer> connectedNodesNext = adjListSet.getOrDefault(node.get(1), new ArrayList<>());
	    connectedNodesNext.add(node.get(0));
	    adjListSet.put(node.get(1), connectedNodesNext);
	}

	Integer countMax = 0, countMin = 1000000;
	for (Map.Entry<Integer, List<Integer>> entr : adjListSet.entrySet()) {

	    if (!isVisited.contains(entr.getKey())) {
		Integer count = dfs(adjListSet, entr.getKey());
		if (countMax < count) {
		    countMax = count;
		}

		if (countMin > count) {
		    countMin = count;
		}
	    }
	}

	List<Integer> res = new ArrayList<>();
	res.add(countMin + 1);
	res.add(countMax + 1);

	return res;
    }

    public static void main(String[] args) {
	// TODO Auto-generated method stub

    }

}
