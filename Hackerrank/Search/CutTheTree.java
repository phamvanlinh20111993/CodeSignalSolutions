package Hackerrank.Search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class NodeVal {
    private Integer nodeId;
    private Integer value;

    public NodeVal(Integer nodeId, Integer value) {
	super();
	this.nodeId = nodeId;
	this.value = value;
    }

    public Integer getNodeId() {
	return nodeId;
    }

    public void setNodeId(Integer nodeId) {
	this.nodeId = nodeId;
    }

    public Integer getValue() {
	return value;
    }

    public void setValue(Integer value) {
	this.value = value;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((nodeId == null) ? 0 : nodeId.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	NodeVal other = (NodeVal) obj;
	if (nodeId == null) {
	    if (other.nodeId != null)
		return false;
	} else if (!nodeId.equals(other.nodeId))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "NodeVal [nodeId=" + nodeId + ", value=" + value + "]";
    }
}

public class CutTheTree {

    public static Integer dfs(NodeVal rootNode, List<Integer> sumNodes, List<Boolean> isVisited,
	    List<List<NodeVal>> adjList) {

	isVisited.set(rootNode.getNodeId(), true);
	List<NodeVal> childList = adjList.get(rootNode.getNodeId());
	Integer sum = 0;
	for (NodeVal nodeV : childList) {
	    if (!isVisited.get(nodeV.getNodeId())) {
		sum += dfs(nodeV, sumNodes, isVisited, adjList);
	    }
	}
	sum += rootNode.getValue();

	sumNodes.set(rootNode.getNodeId(), sum);

	return sum;
    }

    /*
     * Complete the 'cutTheTree' function below.
     *
     * The function is expected to return an INTEGER. The function accepts following
     * parameters: 1. INTEGER_ARRAY data 2. 2D_INTEGER_ARRAY edges
     */

    public static int cutTheTree(List<Integer> data, List<List<Integer>> edges) {
	// Write your code here
	List<Set<NodeVal>> adjSet = new ArrayList<>();
	List<Integer> sumNodes = new ArrayList<>();
	List<Boolean> isVisited = new ArrayList<>();

	for (int ind = 0; ind < data.size(); ind++) {
	    adjSet.add(new HashSet<>());
	    sumNodes.add(0);
	    isVisited.add(false);
	}

	for (List<Integer> edge : edges) {
	    Set<NodeVal> dt = adjSet.get(edge.get(0) - 1);
	    dt.add(new NodeVal(edge.get(1) - 1, data.get(edge.get(1) - 1)));
	    adjSet.set(edge.get(0) - 1, dt);

	    Set<NodeVal> dtN = adjSet.get(edge.get(1) - 1);
	    dtN.add(new NodeVal(edge.get(0) - 1, data.get(edge.get(0) - 1)));
	    adjSet.set(edge.get(1) - 1, dtN);
	}

	List<List<NodeVal>> adjList = adjSet.stream().map(v -> new ArrayList<>(v)).collect(Collectors.toList());

	for (Set<NodeVal> adjD : adjSet) {
	    System.out.println(adjD);
	}

	dfs(new NodeVal(0, data.get(0)), sumNodes, isVisited, adjList);
	System.out.println("sumNodes= " + sumNodes);

	Integer minDiff = Integer.MAX_VALUE;
	for (int ind = 1; ind < sumNodes.size(); ind++) {
	    Integer diff = Math.abs(sumNodes.get(0) - 2 * sumNodes.get(ind));
	    if (minDiff > diff) {
		minDiff = diff;
	    }
	}

	System.out.println("min= " + minDiff);

	return minDiff;
    }

    public static void main(String[] args) {
	// TODO Auto-generated method stub
	System.out.println("############# test 1 ###########");
	List<Integer> data = Arrays.asList(100, 200, 100, 500, 100, 600);
	List<List<Integer>> edges = new ArrayList<>();
	edges.add(Arrays.asList(1, 2));
	edges.add(Arrays.asList(2, 3));
	edges.add(Arrays.asList(2, 5));
	edges.add(Arrays.asList(4, 5));
	edges.add(Arrays.asList(5, 6));

	cutTheTree(data, edges);

	System.out.println("############# test 2 ###########");
	List<Integer> data1 = Arrays.asList(205, 573, 985, 242, 830, 514, 592, 263, 142, 915);
	List<List<Integer>> edges1 = new ArrayList<>();
	edges1.add(Arrays.asList(2, 8));
	edges1.add(Arrays.asList(10, 5));
	edges1.add(Arrays.asList(1, 7));
	edges1.add(Arrays.asList(6, 9));
	edges1.add(Arrays.asList(4, 3));
	edges1.add(Arrays.asList(8, 10));
	edges1.add(Arrays.asList(5, 1));
	edges1.add(Arrays.asList(7, 6));
	edges1.add(Arrays.asList(9, 4));

	cutTheTree(data1, edges1);
    }

}
