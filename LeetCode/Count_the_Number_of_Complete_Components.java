package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * url: https://leetcode.com/problems/count-the-number-of-complete-components/description/?envType=problem-list-v2&envId=union-find
 * 
 * You are given an integer n. There is an undirected graph with n vertices, numbered from 0 to n - 1. You are given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting vertices ai and bi.

Return the number of complete connected components of the graph.

A connected component is a subgraph of a graph in which there exists a path between any two vertices, and no vertex of the subgraph shares an edge with a vertex outside of the subgraph.

A connected component is said to be complete if there exists an edge between every pair of its vertices.

 

Example 1:



Input: n = 6, edges = [[0,1],[0,2],[1,2],[3,4]]
Output: 3
Explanation: From the picture above, one can see that all of the components of this graph are complete.
Example 2:



Input: n = 6, edges = [[0,1],[0,2],[1,2],[3,4],[3,5]]
Output: 1
Explanation: The component containing vertices 0, 1, and 2 is complete since there is an edge between every pair of two vertices. On the other hand, the component containing vertices 3, 4, and 5 is not complete since there is no edge between vertices 4 and 5. Thus, the number of complete components in this graph is 1.
 

Constraints:

1 <= n <= 50
0 <= edges.length <= n * (n - 1) / 2
edges[i].length == 2
0 <= ai, bi <= n - 1
ai != bi
There are no repeated edges.
 */
public class Count_the_Number_of_Complete_Components {

	/*
    public void dfsEdge(int node, List<List<Integer>> adj, Set<Integer> isVisited, 
        Set<String> isVisitedEdge){

        isVisited.add(node);
        for(int ind = 0; ind < adj.get(node).size(); ind++){
            String edge = node + "-" + adj.get(node).get(ind);
            if(!isVisitedEdge.contains(edge)){
                isVisitedEdge.add(edge);
                dfsEdge(adj.get(node).get(ind), adj, isVisited, isVisitedEdge);
            }
        }
    }

    public int countCompleteComponents(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for(int ind = 0; ind < n; ind++){
            adj.add(new ArrayList<>());
        }
        for(int [] dt: edges){
            adj.get(dt[0]).add(dt[1]);
            adj.get(dt[1]).add(dt[0]);
        }

        int count = 0;

        Set<Integer> isVisitedS = new HashSet<>();
        for(int ind = 0; ind < n; ind++){
            if(!isVisitedS.contains(ind)){
                Set<Integer> isVisited = new HashSet<>();
                Set<String> isVisitedEdge = new HashSet<>();
                dfsEdge(ind, adj, isVisited, isVisitedEdge);
                isVisitedS.addAll(isVisited);
                int nodeNum = isVisited.size();

                if(nodeNum*(nodeNum-1) == isVisitedEdge.size()) count++;
            }
          
        }

        return count;
    } */

    // refer idea from leetCode solution
    int findParent(int [] unionFindArr, int node){
        while(unionFindArr[node] != node){
            node = unionFindArr[node];
        }
        return node;
    }

    public int countCompleteComponents(int n, int[][] edges) {
        
        // initial base array with each node is parent of itself
        int [] unionFindArr = new int[n];
        for(int ind = 0; ind < n; ind++){
            unionFindArr[ind] = ind;
        }

        Set<String> listEdges = new HashSet<>();
        for(int [] dt: edges){
            listEdges.add(dt[0]+"-"+dt[1]);
            listEdges.add(dt[1]+"-"+dt[0]);
            // union the nodes to the each their set
            unionFindArr[findParent(unionFindArr, dt[0])] = findParent(unionFindArr, dt[1]);
        }
        
        Map<Integer, List<Integer>> groupMap = new HashMap<>();
        for(int node = 0; node < n; node++){
           int parent = findParent(unionFindArr, node);
           List<Integer> g = groupMap.getOrDefault(parent, new ArrayList<>());
           g.add(node);
           groupMap.put(parent, g);
           
        }
      
        List<List<Integer>> groups = new ArrayList<List<Integer>>(groupMap.values());
        int count = 0;

        for(List<Integer> g : groups){
            int s = g.size();
            boolean isComplete = true;
            for(int ind = 0; ind < s - 1; ind++){
                for(int j = ind+1; j < s; j++){
                    if(!listEdges.contains(g.get(ind) + "-" + g.get(j)) || 
                    !listEdges.contains(g.get(j) + "-" + g.get(ind))){
                        isComplete = false;
                        break;
                    }
                }
            }

            if(isComplete) count++;
        }


       

        return count;
    }


}
