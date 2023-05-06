package Hackerrank.GraphTheory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 
 * @author PhamLinh
 * @url https://www.hackerrank.com/challenges/kruskalmstrsub/problem?isFullScreen=true
 * 
 *      Given an undirected weighted connected graph, find the Really Special
 *      SubTree in it. The Really Special SubTree is defined as a subgraph
 *      consisting of all the nodes in the graph and:
 * 
 *      There is only one exclusive path from a node to every other node. The
 *      subgraph is of minimum overall weight (sum of all edges) among all such
 *      subgraphs. No cycles are formed To create the Really Special SubTree,
 *      always pick the edge with smallest weight. Determine if including it
 *      will create a cycle. If so, ignore the edge. If there are edges of equal
 *      weight available:
 * 
 *      Choose the edge that minimizes the sum where and are vertices and is the
 *      edge weight. If there is still a collision, choose any of them. Print
 *      the overall weight of the tree formed using the rules.
 * 
 *      For example, given the following edges:
 * 
 *      u v wt 1 2 2 2 3 3 3 1 5 First choose at weight . Next choose at weight
 *      . All nodes are connected without cycles for a total weight of .
 * 
 *      Function Description
 * 
 *      Complete the function in the editor below. It should return an integer
 *      that represents the total weight of the subtree formed.
 * 
 *      kruskals has the following parameters:
 * 
 *      g_nodes: an integer that represents the number of nodes in the tree
 *      g_from: an array of integers that represent beginning edge node numbers
 *      g_to: an array of integers that represent ending edge node numbers
 *      g_weight: an array of integers that represent the weights of each edge
 *      Input Format
 * 
 *      The first line has two space-separated integers and , the number of
 *      nodes and edges in the graph.
 * 
 *      The next lines each consist of three space-separated integers , and ,
 *      where and denote the two nodes between which the undirected edge exists
 *      and denotes the weight of that edge.
 * 
 *      Constraints
 ** 
 *      Note: ** If there are edges between the same pair of nodes with
 *      different weights, they are to be considered as is, like multiple edges.
 * 
 *      Output Format
 * 
 *      Print a single integer denoting the total weight of the Really Special
 *      SubTree.
 * 
 *      Sample Input 1
 * 
 *      CopyDownload Undirected Weighed Graph: g 1 2 5 3 3 4 7 4 5 6
 * 
 * 
 *      4 6 1 2 5 1 3 3 4 1 6 2 4 7 3 2 4 3 4 5 Sample Output 1
 * 
 *      12 Explanation 1
 * 
 *      The graph given in the test case is shown above.
 * 
 *      Applying Kruskal's algorithm, all of the edges are sorted in ascending
 *      order of weight.
 * 
 *      After sorting, the edge choices are available as :
 * 
 *      and
 * 
 *      Select 2 \rightarrow 3 (w=4)$ because it has the lowest weight without
 *      creating a cycle
 * 
 *      The edge would form a cycle, so it is ignored
 * 
 *      Select to finish the MST yielding a total weight of
 * 
 *      image
 * 
 * 
 *      Sample Input 2
 * 
 *      CopyDownload Undirected Weighed Graph: g 1 2 20 3 50 4 70 5 90 30 40 60
 * 
 * 
 *      5 7 1 2 20 1 3 50 1 4 70 1 5 90 2 3 30 3 4 40 4 5 60 Sample Output 2
 * 
 *      150 Explanation 2
 * 
 *      Given the graph above, select edges with weights .
 */

public class Kruskals {

    public static boolean isFormCycleDisJoinSet(Integer[] nodes, int node1, int node2) {

	while (nodes[node1] != -1) {
	    node1 = nodes[node1];
	}

	while (nodes[node2] != -1) {
	    node2 = nodes[node2];
	}

	if (node1 != node2) {
	    nodes[node1] = node2;
	    return false;
	}

	return true;
    }

    public static int spanningTree(int n, List<Integer[]> listNode) {

	listNode = listNode.stream().sorted((o1, o2) -> o1[2] - o2[2]).collect(Collectors.toList());

	listNode.stream().forEach(v -> System.out.println(v[0] + " " + v[1] + " " + v[2]));

	Integer[] nodes = new Integer[n];
	for (int ind = 0; ind < nodes.length; ind++) {
	    nodes[ind] = -1; // not set
	}

	int res = 0;
	Set<Integer> fullNode = new HashSet<>();
	for (Integer[] node : listNode) {
	    if (fullNode.size() == n) {
		break;
	    }
	    if (!isFormCycleDisJoinSet(nodes, node[0], node[1])) {
		res += node[2];
		fullNode.add(node[0]);
		fullNode.add(node[1]);
	    }
	}

	return res;
    }

    public static int kruskals(int gNodes, List<Integer> gFrom, List<Integer> gTo, List<Integer> gWeight) {
	List<Integer[]> listNode = new ArrayList<Integer[]>();

	for (int index = 0; index < gFrom.size(); index++) {
	    listNode.add(new Integer[] { gFrom.get(index), gTo.get(index), gWeight.get(index) });
	}

	return spanningTree(gNodes, listNode);
    }

    public static void main(String[] args) {

	System.out.println("###################### Test 1 ################# ");
	List<Integer[]> input = new ArrayList<>();
	input.add(new Integer[] { 0, 1, 10 });
	input.add(new Integer[] { 0, 2, 6 });
	input.add(new Integer[] { 0, 3, 5 });
	input.add(new Integer[] { 1, 3, 15 });
	input.add(new Integer[] { 2, 3, 4 });
	int n = 4;
	int res = spanningTree(n, input);
	System.out.println("Data " + res);

	System.out.println("###################### Test 2 ################# ");
	List<Integer[]> input1 = new ArrayList<>();
	input1.add(new Integer[] { 0, 1, 10 });
	input1.add(new Integer[] { 0, 2, 6 });
	input1.add(new Integer[] { 0, 3, 5 });
	input1.add(new Integer[] { 1, 3, 15 });
	input1.add(new Integer[] { 2, 3, 4 });
	input1.add(new Integer[] { 3, 4, 14 });
	input1.add(new Integer[] { 0, 4, 7 });

	int n1 = 5;
	int res1 = spanningTree(n1, input1);
	System.out.println("Data " + res1);
    }
}
