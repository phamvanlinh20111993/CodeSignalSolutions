package Hackerrank.GraphTheory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

class NodeDis implements Comparable<NodeDis> {

    private int node;

    private double distance;

    public NodeDis(int node, int distance) {
	this.node = node;
	this.distance = distance;
    }

    public NodeDis(int node, double distance) {
	this.node = node;
	this.distance = distance;
    }

    public int getNode() {
	return node;
    }

    public void setNode(int node) {
	this.node = node;
    }

    public double getDistance() {
	return distance;
    }

    @Override
    public int hashCode() {
	return Objects.hash(node);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	NodeDis other = (NodeDis) obj;
	return node == other.node;
    }

    @Override
    public int compareTo(NodeDis o) {
	return (int) (this.distance - o.distance);
    }

    @Override
    public String toString() {
	return "NodeDis [node=" + node + ", distance=" + distance + "]";
    }
}

class PriorityQueue<E extends NodeDis> extends LinkedList<E> {
    /**
     * 
     */
    private static final long serialVersionUID = 375868010640521621L;

    public E poll() {

	if (this.size() == 0)
	    return null;

	E minData = this.get(0);
	int minIndex = 0;
	for (int ind = 1; ind < this.size(); ind++) {
	    if (minData.compareTo(this.get(ind)) > 0) {
		minData = this.get(ind);
		minIndex = ind;
	    }
	}

	this.remove(minIndex);

	return minData;

    }
}

/**
 * 
 * @author PhamLinh
 *
 * @param <E>
 */
class MinHeapAVLTree<E> extends AVLTree<E> {

    public void add(E data) {
	this.insertNode(data);
    }

    public E poll() {
	E minNode = null;

	AVLNode<E> travesalNode = this.AVLNode;

	if (travesalNode == null)
	    return null;

	while (travesalNode.hasLeftChild()) {
	    travesalNode = travesalNode.getLeftAVLNodes();
	}
	minNode = travesalNode.getData();
	if (travesalNode.getData() != null) {
	    this.deleteNode(minNode);
	}

	return minNode;
    }
}

public class DijkstraShortestReach2 {

    public static List<NodeDis> dijkstra(int[][] graph, int start) {
	Set<NodeDis> visited = new HashSet<>();

	Queue<NodeDis> storageNodeDistance = new PriorityQueue<>();
	storageNodeDistance.add(new NodeDis(start, 0));

	while (visited.size() < graph.length) {
	    NodeDis nodeDis = storageNodeDistance.poll();
	    visited.add(nodeDis);

	    for (int ind = 0; ind < graph[nodeDis.getNode()].length; ind++) {
		if (!visited.contains(new NodeDis(ind, -1)) && graph[nodeDis.getNode()][ind] > 0) {
		    storageNodeDistance.add(new NodeDis(ind, nodeDis.getDistance() + graph[nodeDis.getNode()][ind]));
		}
	    }
	}

	return new ArrayList<>(visited);
    }

    /**
     * origin version, Complexity = V*( V + E) + E = V^2 + V*E + E, like BFS we cost
     * O(V+E), but cost O(V+E) for get min in queue, i added every node to queue
     * except current node. this performance is too bad
     * 
     * @param adjList
     * @param amountNode
     * @param start
     * @return
     */
    public static List<Integer> dijkstra(List<List<NodeDis>> adjList, int amountNode, int start) {
	Set<NodeDis> visited = new HashSet<>();
	Queue<NodeDis> storageNodeDistance = new PriorityQueue<>();

	NodeDis startNode = new NodeDis(start, 0);
	storageNodeDistance.add(startNode);

	// O(V)
	while (visited.size() < amountNode) {
	    // O(V)
	    NodeDis nodeDis = storageNodeDistance.poll();
	    if (nodeDis == null)
		break;
	    visited.add(nodeDis);
	    // O(E/V)
	    for (NodeDis node : adjList.get(nodeDis.getNode())) {
		if (!visited.contains(node)) {
		    storageNodeDistance.add(new NodeDis(node.getNode(), nodeDis.getDistance() + node.getDistance()));
		}
	    }
	}

	System.out.println(visited);

	for (int ind = 0; ind < amountNode; ind++) {
	    NodeDis nodeDis = new NodeDis(ind, -1);
	    if (!visited.contains(nodeDis)) {
		visited.add(nodeDis);
	    }
	}

	visited.remove(startNode);

	List<Integer> res = new ArrayList<>(visited).stream().sorted(new Comparator<NodeDis>() {
	    @Override
	    public int compare(NodeDis o1, NodeDis o2) {
		return (int) (o1.getNode() - o2.getNode());
	    }
	}).map(n -> (int) n.getDistance()).collect(Collectors.toList());

	return res;
    }

    /**
     * Complexity = V*V + E = V^2 + E, like BFS we cost O(V+E), but cost O(V) for
     * get min in queue. it equal to basic Dijkstra algo
     * 
     * @param adjList
     * @param amountNode
     * @param start
     * @return
     */
    public static List<Integer> dijkstra_V1(List<List<NodeDis>> adjList, int amountNode, int start) {
	Set<NodeDis> visited = new HashSet<>();
	Queue<NodeDis> storageNodeDistance = new PriorityQueue<>();

	NodeDis startNode = new NodeDis(start, 0);
	storageNodeDistance.add(startNode);

	// store the min distance from start node to current node, we only need compare
	// with the larger value
	// then compare and then add to queue.
	Map<Integer, Integer> minDistFromNode = new HashMap<>();

	while (visited.size() < amountNode) {
	    NodeDis nodeDis = storageNodeDistance.poll();
	    if (nodeDis == null) {
		break;
	    }
	    visited.add(nodeDis);
	    for (NodeDis node : adjList.get(nodeDis.getNode())) {
		Integer minDistNode = minDistFromNode.getOrDefault(node.getNode(), Integer.MAX_VALUE);
		if (!visited.contains(node) && nodeDis.getDistance() + node.getDistance() < minDistNode) {
		    storageNodeDistance.add(new NodeDis(node.getNode(), nodeDis.getDistance() + node.getDistance()));
		    minDistFromNode.put(node.getNode(), (int) (nodeDis.getDistance() + node.getDistance()));
		}
	    }
	}

	System.out.println(visited);

	for (int ind = 0; ind < amountNode; ind++) {
	    NodeDis nodeDis = new NodeDis(ind, -1);
	    if (!visited.contains(nodeDis)) {
		visited.add(nodeDis);
	    }
	}

	visited.remove(startNode);

	List<Integer> res = new ArrayList<>(visited).stream().sorted(new Comparator<NodeDis>() {
	    @Override
	    public int compare(NodeDis o1, NodeDis o2) {
		return (int) (o1.getNode() - o2.getNode());
	    }
	}).map(n -> (int) n.getDistance()).collect(Collectors.toList());

	return res;
    }

    /**
     * Using priority queue, reduce the complexity. Complexity = V*Log(V)+E
     * 
     * @param adjList
     * @param amountNode
     * @param start
     * @return
     */
    public static List<Integer> dijkstra_V2(List<List<NodeDis>> adjList, int amountNode, int start) {

	Set<NodeDis> visited = new HashSet<>();
	MinHeap<NodeDis> storageNodeDistance = new MinHeap<NodeDis>(new Comparator<NodeDis>() {
	    @Override
	    public int compare(NodeDis o1, NodeDis o2) {
		return (int) o1.getDistance() - (int) o2.getDistance();
	    }
	});

	NodeDis startNode = new NodeDis(start, 0);
	storageNodeDistance.add(startNode);

	// store the min distance from start node to current node, we only need compare
	// with the larger value
	// then compare and then add to queue.
	Map<Integer, Integer> minDistFromNode = new HashMap<>();

	while (visited.size() < amountNode) {
	    NodeDis nodeDis = storageNodeDistance.poll();
	    if (nodeDis == null) {
		break;
	    }
	    visited.add(nodeDis);
	    for (NodeDis node : adjList.get(nodeDis.getNode())) {
		Integer minDistNode = minDistFromNode.getOrDefault(node.getNode(), Integer.MAX_VALUE);
		if (!visited.contains(node) && nodeDis.getDistance() + node.getDistance() < minDistNode) {
		    storageNodeDistance.add(new NodeDis(node.getNode(), nodeDis.getDistance() + node.getDistance()));
		    minDistFromNode.put(node.getNode(), (int) (nodeDis.getDistance() + node.getDistance()));
		}
	    }
	}

	System.out.println(visited);

	for (int ind = 0; ind < amountNode; ind++) {
	    NodeDis nodeDis = new NodeDis(ind, -1);
	    if (!visited.contains(nodeDis)) {
		visited.add(nodeDis);
	    }
	}

	visited.remove(startNode);

	List<Integer> res = new ArrayList<>(visited).stream().sorted(new Comparator<NodeDis>() {
	    @Override
	    public int compare(NodeDis o1, NodeDis o2) {
		return (int) (o1.getNode() - o2.getNode());
	    }
	}).map(n -> (int) n.getDistance()).collect(Collectors.toList());

	return res;
    }

    /*
     * Complete the 'shortestReach' function below.
     *
     * The function is expected to return an INTEGER_ARRAY. The function accepts
     * following parameters: 1. INTEGER n 2. 2D_INTEGER_ARRAY edges 3. INTEGER s
     */
    public static List<Integer> shortestReach(int n, List<List<Integer>> edges, int s) {

	List<List<NodeDis>> adjList = new ArrayList<>();

	for (int ind = 0; ind < n; ind++) {
	    adjList.add(new ArrayList<>());
	}

	for (List<Integer> nodeList : edges) {
	    Integer nodeStart = nodeList.get(0);
	    Integer nodeEnd = nodeList.get(1);
	    Integer distance = nodeList.get(2);

	    List<NodeDis> nodes = adjList.get(nodeStart - 1);
	    nodes.add(new NodeDis(nodeEnd - 1, distance));
	    adjList.set(nodeStart - 1, nodes);

	    List<NodeDis> nodeEnds = adjList.get(nodeEnd - 1);
	    nodeEnds.add(new NodeDis(nodeStart - 1, distance));
	    adjList.set(nodeEnd - 1, nodeEnds);
	}

	// adjList.forEach(v -> System.out.println(v));

	return dijkstra(adjList, n, s - 1);
    }

    
    public static void main(String[] args) {
	/*
	System.out.println("################# test 1 #####################");

	int[][] graph = { { 0, 4, 3, 1, 0, 0, 0, 0 }, { 4, 0, 5, 0, 0, 0, 0, 0 }, { 3, 0, 0, 4, 0, 0, 3, 0 },
		{ 1, 0, 4, 0, 5, 9, 0, 0 }, { 0, 0, 0, 5, 0, 2, 5, 0 }, { 0, 0, 0, 9, 2, 0, 0, 14 },
		{ 0, 0, 3, 0, 5, 0, 0, 8 }, { 0, 0, 0, 0, 0, 14, 8, 0 } };
	int start = 1, end = 7;

	System.out.println("The min distance between node (" + start + ", " + end + ")=" + dijkstra(graph, start, end));
	System.out.println("The min distance between nodes from node " + start + " is ");
	dijkstra(graph, start).forEach(e -> System.out.println(e));

	System.out.println("################# test 2 #####################");

	int[][] graph1 = { { 0, 7, 9, 0, 0, 14 }, { 7, 0, 10, 15, 0, 0 }, { 9, 10, 0, 11, 0, 2 },
		{ 0, 15, 11, 0, 6, 0 }, { 0, 0, 0, 6, 0, 9 }, { 14, 0, 2, 0, 9, 0 } };
	int start1 = 0, end1 = 4;

	System.out.println(
		"The min distance between node (" + start1 + ", " + end1 + ")=" + dijkstra(graph1, start1, end1));
	System.out.println("The min distance between nodes from node " + start1 + " is ");
	dijkstra(graph1, start1).forEach(e -> System.out.println(e));

	System.out.println("################# test 3 #####################");

	int[][] graph2 = { { 0, 9, 0, 7, 0, 0, 0 }, { 9, 0, 1, 0, 0, 0, 0 }, { 0, 1, 0, 0, 0, 0, 3 },
		{ 7, 0, 0, 0, 1, 3, 0 }, { 0, 0, 0, 1, 0, 6, 0 }, { 0, 0, 0, 3, 6, 0, 8 }, { 0, 0, 3, 0, 0, 8, 0 } };
	int start2 = 0, end2 = 6;

	System.out.println(
		"The min distance between node (" + start2 + ", " + end2 + ")=" + dijkstra(graph2, start2, end2));
	System.out.println("The min distance between nodes from node " + start2 + " is ");
	dijkstra(graph2, start2).forEach(e -> System.out.println(e));
        */
	
	MinHeapAVLTree<Integer> storageNodeDistance = new MinHeapAVLTree<Integer>();
	storageNodeDistance.add(1);
	System.out.println("storageNodeDistance.poll() = " + storageNodeDistance.poll());
	storageNodeDistance.add(13);
	storageNodeDistance.add(4);
	storageNodeDistance.add(8);
	storageNodeDistance.print();
	System.out.println("storageNodeDistance.poll() = " + storageNodeDistance.poll());
	storageNodeDistance.print();
	
	System.out.println("storageNodeDistance.poll() = " + storageNodeDistance.poll());
	System.out.println("storageNodeDistance.poll() = " + storageNodeDistance.poll());
	System.out.println("storageNodeDistance.poll() = " + storageNodeDistance.poll());
	System.out.println("storageNodeDistance.poll() = " + storageNodeDistance.poll());
	storageNodeDistance.print();
    }

}
