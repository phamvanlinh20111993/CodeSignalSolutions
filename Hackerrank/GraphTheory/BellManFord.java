package Hackerrank.GraphTheory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class NodeWeight<E> {
    private Integer nodeId;
    private E weight;

    public NodeWeight() {
    }

    public NodeWeight(Integer nodeId, E weight) {
        super();
        this.nodeId = nodeId;
        this.weight = weight;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public E getWeight() {
        return weight;
    }

    public void setWeight(E weight) {
        this.weight = weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        NodeWeight other = (NodeWeight) obj;
        return Objects.equals(nodeId, other.nodeId);
    }

    @Override
    public String toString() {
        return "NodeWeight [nodeId=" + nodeId + ", weight=" + weight + "]";
    }
}

class BellManFord {

    /**
     * 
     * @param graph
     * @param start
     * @return
     */
    public static List<Integer> bellmanFord(int[][] graph, int start) {
        List<Integer> minDistance = new ArrayList<>();

        for (int ind = 0; ind < graph.length; ind++) {
            minDistance.add(100000);
        }

        // the min distance from itself is 0, no cost, it is very important with this
        // set up.
        minDistance.set(start, 0);

        for (int v = 0; v < graph.length; v++) {
            for (int ind = 0; ind < graph.length; ind++) {
                for (int j = 0; j < graph[0].length; j++) {
                    if (graph[ind][j] != 0 && minDistance.get(ind) > graph[ind][j] + minDistance.get(j)) {
                        minDistance.set(ind, graph[ind][j] + minDistance.get(j));
                    }
                }
            }
        }

        return minDistance;
    }

    /**
     * 
     * @param graph
     * @param start
     * @return
     */
    public static List<Integer> bellmanFord(List<List<NodeWeight<Integer>>> graph, int start) {
        List<Integer> minDistance = new ArrayList<>();

        for (int ind = 0; ind < graph.size(); ind++) {
            minDistance.add(100000);
        }

        // the min distance from itself is 0, no cost, it is very important with this
        // set up.
        minDistance.set(start, 0);
        
        // run vertex - 1 time
        for (int v = 0; v < graph.size() - 1; v++) {
            for (int ind = 0; ind < graph.size(); ind++) {
                for (NodeWeight<Integer> nodeW : graph.get(ind)) {
                //    if (minDistance.get(ind) > nodeW.getWeight() + minDistance.get(nodeW.getNodeId())) {
                //        minDistance.set(ind, nodeW.getWeight() + minDistance.get(nodeW.getNodeId()));
                //    }
                    minDistance.set(ind, Math.min(minDistance.get(ind), nodeW.getWeight() + minDistance.get(nodeW.getNodeId())));
                }
            }
        }

        return minDistance;
    }

    public static void main(String[] args) {
        System.out.println("################# test 1 #####################");

        int[][] graph = { { 0, 4, 3, 1, 0, 0, 0, 0 }, 
                          { 4, 0, 5, 0, 0, 0, 0, 0 }, 
                          { 3, 0, 0, 4, 0, 0, 3, 0 },
                          { 1, 0, 4, 0, 5, 9, 0, 0 }, 
                          { 0, 0, 0, 5, 0, 2, 5, 0 }, 
                          { 0, 0, 0, 9, 2, 0, 0, 14 },
                          { 0, 0, 3, 0, 5, 0, 0, 8 }, 
                          { 0, 0, 0, 0, 0, 14, 8, 0 } };
        int start = 0;
        System.out.println("The min distance between nodes from node "+start+" is "); 
        bellmanFord(graph, 0).forEach(v -> System.out.println(v));
        
        System.out.println("################# test 2 #####################");

        int[][] graph1 = { { 0, 7, 9, 0, 0, 14 }, 
                           { 7, 0, 10, 15, 0, 0 }, 
                           { 9, 10, 0, 11, 0, 2 },
                           { 0, 15, 11, 0, 6, 0 }, 
                           { 0, 0, 0, 6, 0, 9 }, 
                           { 14, 0, 2, 0, 9, 0 } };

        int start1 = 0;
        System.out.println("The min distance between nodes from node "+start+" is "); 
        bellmanFord(graph1, start1).forEach(v -> System.out.println(v));
        
        System.out.println("################# test 3 #####################");

        int[][] graph2 = { { 0, 9, 0, 7, 0, 0, 0 }, 
                           { 9, 0, 1, 0, 0, 0, 0  }, 
                           { 0, 1, 0, 0, 0, 0, 3  },
                           { 7, 0, 0, 0, 1, 3, 0  }, 
                           { 0, 0, 0, 1, 0, 6, 0  }, 
                           { 0, 0, 0, 3, 6, 0, 8  },
                           { 0, 0, 3, 0, 0, 8, 0  }
                           };
        int start2 = 0;
        System.out.println("The min distance between nodes from node "+start+" is "); 
        bellmanFord(graph2, start2).forEach(v -> System.out.println(v));
        
        System.out.println("################# test 4 #####################");

        List<List<NodeWeight<Integer>>> graph3 = List.of(
                List.of(new NodeWeight<Integer>(1, 10), new NodeWeight<Integer>(2, 5)),
                List.of(new NodeWeight<Integer>(0, 10), new NodeWeight<Integer>(2, 3),  new NodeWeight<Integer>(3, 4)),
                List.of(new NodeWeight<Integer>(0, 5), new NodeWeight<Integer>(1, 3), new NodeWeight<Integer>(5, 7), new NodeWeight<Integer>(4, 12)),
                List.of(new NodeWeight<Integer>(1, 4), new NodeWeight<Integer>(4, 9)),
                List.of(new NodeWeight<Integer>(3, 9), new NodeWeight<Integer>(2, 12), new NodeWeight<Integer>(5, 8), new NodeWeight<Integer>(6, 6)),
                List.of(new NodeWeight<Integer>(2, 7), new NodeWeight<Integer>(4, 8), new NodeWeight<Integer>(6, 4)),
                List.of(new NodeWeight<Integer>(4, 6), new NodeWeight<Integer>(5, 4))
        );
                      
        int start3 = 0;
        System.out.println("The min distance between nodes from node " + start + " is "); 
        bellmanFord(graph3, start3).forEach(v -> System.out.println(v));
    }

}
