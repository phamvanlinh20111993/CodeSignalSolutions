package Hackerrank.Trees;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 
 * @author PhamLinh
 * @url https://www.hackerrank.com/challenges/tree-top-view/problem
 * 
 * Given a pointer to the root of a binary tree, print the top view of the binary tree.

The tree as seen from the top the nodes, is called the top view of the tree.

For example :

   1
    \
     2
      \
       5
      /  \
     3    6
      \
       4
Top View : 

Complete the function  and print the resulting values on a single line separated by space.

Input Format

You are given a function,

void topView(node * root) {

}
Constraints

 Nodes in the tree  

Output Format

Print the values on a single line separated by space.

Sample Input

   1
    \
     2
      \
       5
      /  \
     3    6
      \
       4
Sample Output

1 2 5 6

Explanation

   1
    \
     2
      \
       5
      /  \
     3    6
      \
       4
From the top, only nodes  are visible.
 * 
 */
public class TreeTopView {

    /*
     * 
     * class Node int data; Node left; Node right;
     */
    public static List<Integer> addToList(Integer... data) {
	List<Integer> l = new ArrayList<>();
	for (int ind = 0; ind < data.length; ind++) {
	    l.add(data[ind]);
	}

	return l;
    }

    /**
     *
     * **) Reading: to clearly understand what is tree view (it take time :( the
     * description very bad)). reference: search top view and click images then
     * check some sites to get overview.
     * 
     * **)After understanding we can use this ideas: - thinking a tree will show in
     * a oxy coordinate system with root node is [0, 0] 
     * - when go to left child node
     * then the current coords node is (rootX-1, rootY+1) 
     * - when go to right child
     * node then the current coords node is (rootX+1, rootY+1) from the top view we
     * can see that which x coordinate is come first and have not visited yet will
     * be marked as in list view. 
     * -- done. 
     * -- update: the y coord not need for this
     * case, we just focus to x
     **/
    public static void topView(Node root) {
	Queue<Node> queue = new LinkedList<>();
	Map<Integer, List<Integer>> nodeCoords = new HashMap<>();
	Set<Integer> isTaken = new HashSet<>();

	int breathX = 0;
	int depthY = 0;
	nodeCoords.put(root.data, addToList(breathX, depthY));
	queue.add(root);

	List<List<Integer>> ordersPrint = new ArrayList<>();

	while (queue.size() > 0) {
	    Node node = queue.poll();
	    List<Integer> coods = nodeCoords.get(node.data);

	    if (!isTaken.contains(coods.get(0))) {
		isTaken.add(coods.get(0));
		ordersPrint.add(addToList(node.data, coods.get(0)));
	    }

	    if (node.left != null) {
		queue.add(node.left);
		nodeCoords.put(node.left.data, addToList(coods.get(0) - 1, coods.get(1) + 1));
	    }

	    if (node.right != null) {
		queue.add(node.right);
		nodeCoords.put(node.right.data, addToList(coods.get(0) + 1, coods.get(1) + 1));
	    }

	}

	ordersPrint.stream().sorted(new Comparator<List<Integer>>() {
	    @Override
	    public int compare(List<Integer> a1, List<Integer> a2) {
		return a1.get(1) - a2.get(1);
	    }
	}).forEach(v -> {
	    System.out.print(v.get(0) + " ");
	});

    }

    public static void main(String[] args) {
	// TODO Auto-generated method stub

    }

}
