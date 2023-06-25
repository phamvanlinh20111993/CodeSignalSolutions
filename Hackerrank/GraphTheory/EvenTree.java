package Hackerrank.GraphTheory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 
 * @author PhamLinh 
 * @ref:  https://www.hackerrank.com/challenges/even-tree/problem?isFullScreen=true
 *      
 *         You are given a tree (a simple connected graph with no cycles).
 * 
 *         Find the maximum number of edges you can remove from the tree to get
 *         a forest such that each connected component of the forest contains an
 *         even number of nodes.
 * 
 *         As an example, the following tree with nodes can be cut at most time
 *         to create an even forest.
 * 
 *         image
 * 
 *         Function Description
 * 
 *         Complete the evenForest function in the editor below. It should
 *         return an integer as described.
 * 
 *         evenForest has the following parameter(s):
 * 
 *         t_nodes: the number of nodes in the tree t_edges: the number of
 *         undirected edges in the tree t_from: start nodes for each edge t_to:
 *         end nodes for each edge, (Match by index to t_from.) Input Format
 * 
 *         The first line of input contains two integers and , the number of
 *         nodes and edges. The next lines contain two integers and which
 *         specify nodes connected by an edge of the tree. The root of the tree
 *         is node .
 * 
 *         Constraints
 * 
 *         Note: The tree in the input will be such that it can always be
 *         decomposed into components containing an even number of nodes. is the
 *         set of positive even integers.
 * 
 *         Output Format
 * 
 *         Print the number of removed edges.
 * 
 *         Sample Input 1
 * 
 *         CopyDownload Undirected Graph: t 2 1 3 4 5 6 7 8 9 10
 * 
 * 
 *         10 9 2 1 3 1 4 3 5 2 6 1 7 2 8 6 9 8 10 8 Sample Output 1
 * 
 *         2 Explanation 1
 * 
 *         Remove edges and to get the desired result.
 * 
 * 
 *         image image
 * 
 *         No more edges can be removed.
 */
public class EvenTree {

    static Map<Integer, Integer> storageAmountChildNode = new HashMap<Integer, Integer>();
    static int res = 0;

    static void dfs(Set<Integer> isVisited, Integer root, List<List<Integer>> adjList) {
	isVisited.add(root);

	List<Integer> notVisitData = adjList.get(root);
	int size = notVisitData.size();
	Set<Integer> notVisitNodes = new HashSet<>();

	for (int ind = 0; ind < size; ind++) {
	    if (!isVisited.contains(notVisitData.get(ind))) {
		notVisitNodes.add(notVisitData.get(ind));
		dfs(isVisited, notVisitData.get(ind), adjList);
	    }
	}

	int count = 1;
	for (int ind = 0; ind < size; ind++) {
	    if (notVisitNodes.contains(notVisitData.get(ind))) {
		count += storageAmountChildNode.getOrDefault(notVisitData.get(ind), 1);
	    }
	}

	if (count % 2 == 0 && root != 1) {
	    res++;
	}

	storageAmountChildNode.put(root, count);
	// System.out.println("root: " + root + ", total child Node1: " + count);
    }

    // Complete the evenForest function below.
    static int evenForest(int nodes, int edges, List<Integer> from, List<Integer> to) {
	List<Set<Integer>> adjListS = new ArrayList<>();
	for (int ind = 0; ind <= nodes; ind++) {
	    adjListS.add(new HashSet<>());
	}

	for (int ind = 0; ind < from.size(); ind++) {
	    adjListS.get(from.get(ind)).add(to.get(ind));
	    adjListS.get(to.get(ind)).add(from.get(ind));
	}

	List<List<Integer>> adjList = adjListS.stream().map(s -> new ArrayList<>(s)).collect(Collectors.toList());

	Set<Integer> notVisit = new HashSet<>();
	storageAmountChildNode = new HashMap<Integer, Integer>();
	res = 0;
	dfs(notVisit, 1, adjList);

	return res;
    }

    public static void main(String[] args) throws IOException {
	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

	String[] tNodesEdges = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

	int tNodes = Integer.parseInt(tNodesEdges[0]);
	int tEdges = Integer.parseInt(tNodesEdges[1]);

	List<Integer> tFrom = new ArrayList<>();
	List<Integer> tTo = new ArrayList<>();

	IntStream.range(0, tEdges).forEach(i -> {
	    try {
		String[] tFromTo = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

		tFrom.add(Integer.parseInt(tFromTo[0]));
		tTo.add(Integer.parseInt(tFromTo[1]));
	    } catch (IOException ex) {
		throw new RuntimeException(ex);
	    }
	});

	int res = evenForest(tNodes, tEdges, tFrom, tTo);

	bufferedWriter.write(String.valueOf(res));
	bufferedWriter.newLine();

	bufferedReader.close();
	bufferedWriter.close();
    }

}
