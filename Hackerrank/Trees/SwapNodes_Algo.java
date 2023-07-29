package Hackerrank.Trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author PhamLinh
 * @url https://www.hackerrank.com/challenges/swap-nodes-algo/problem?isFullScreen=true
 * 
 * 
 * A binary tree is a tree which is characterized by one of the following properties:

It can be empty (null).
It contains a root node only.
It contains a root node with a left subtree, a right subtree, or both. These subtrees are also binary trees.
In-order traversal is performed as

Traverse the left subtree.
Visit root.
Traverse the right subtree.
For this in-order traversal, start from the left child of the root node and keep exploring the left subtree until you reach a leaf. When you reach a leaf, back up to its parent, check for a right child and visit it if there is one. If there is not a child, you've explored its left and right subtrees fully. If there is a right child, traverse its left subtree then its right in the same manner. Keep doing this until you have traversed the entire tree. You will only store the values of a node as you visit when one of the following is true:

it is the first node visited, the first time visited
it is a leaf, should only be visited once
all of its subtrees have been explored, should only be visited once while this is true
it is the root of the tree, the first time visited
Swapping: Swapping subtrees of a node means that if initially node has left subtree L and right subtree R, then after swapping, the left subtree will be R and the right subtree, L.

For example, in the following tree, we swap children of node 1.

                                Depth
    1               1            [1]
   / \             / \
  2   3     ->    3   2          [2]
   \   \           \   \
    4   5           5   4        [3]
In-order traversal of left tree is 2 4 1 3 5 and of right tree is 3 5 1 2 4.

Swap operation:

We define depth of a node as follows:

The root node is at depth 1.
If the depth of the parent node is d, then the depth of current node will be d+1.
Given a tree and an integer, k, in one operation, we need to swap the subtrees of all the nodes at each depth h, where h ∈ [k, 2k, 3k,...]. In other words, if h is a multiple of k, swap the left and right subtrees of that level.

You are given a tree of n nodes where nodes are indexed from [1..n] and it is rooted at 1. You have to perform t swap operations on it, and after each swap operation print the in-order traversal of the current state of the tree.

Function Description

Complete the swapNodes function in the editor below. It should return a two-dimensional array where each element is an array of integers representing the node indices of an in-order traversal after a swap operation.

swapNodes has the following parameter(s):
- indexes: an array of integers representing index values of each , beginning with , the first element, as the root.
- queries: an array of integers, each representing a  value.

Input Format
The first line contains n, number of nodes in the tree.

Each of the next n lines contains two integers, a b, where a is the index of left child, and b is the index of right child of ith node.

Note: -1 is used to represent a null node.

The next line contains an integer, t, the size of .
Each of the next t lines contains an integer , each being a value .

Output Format
For each k, perform the swap operation and store the indices of your in-order traversal to your result array. After all swap operations have been performed, return your result array for printing.

Constraints

Either  or 
Either  or 
The index of a non-null child will always be greater than that of its parent.
Sample Input 0

3
2 3
-1 -1
-1 -1
2
1
1
Sample Output 0

3 1 2
2 1 3
Explanation 0

As nodes 2 and 3 have no children, swapping will not have any effect on them. We only have to swap the child nodes of the root node.

    1   [s]       1    [s]       1   
   / \      ->   / \        ->  / \  
  2   3 [s]     3   2  [s]     2   3
Note: [s] indicates that a swap operation is done at this depth.

Sample Input 1

5
2 3
-1 4
-1 5
-1 -1
-1 -1
1
2
Sample Output 1

4 2 1 5 3
Explanation 1

Swapping child nodes of node 2 and 3 we get

    1                  1  
   / \                / \ 
  2   3   [s]  ->    2   3
   \   \            /   / 
    4   5          4   5  
Sample Input 2

11
2 3
4 -1
5 -1
6 -1
7 8
-1 9
-1 -1
10 11
-1 -1
-1 -1
-1 -1
2
2
4
Sample Output 2

2 9 6 4 1 3 7 5 11 8 10
2 6 9 4 1 3 7 5 10 8 11
Explanation 2

Here we perform swap operations at the nodes whose depth is either 2 or 4 for  and then at nodes whose depth is 4 for .

         1                     1                          1             
        / \                   / \                        / \            
       /   \                 /   \                      /   \           
      2     3    [s]        2     3                    2     3          
     /      /                \     \                    \     \         
    /      /                  \     \                    \     \        
   4      5          ->        4     5          ->        4     5       
  /      / \                  /     / \                  /     / \      
 /      /   \                /     /   \                /     /   \     
6      7     8   [s]        6     7     8   [s]        6     7     8
 \          / \            /           / \              \         / \   
  \        /   \          /           /   \              \       /   \  
   9      10   11        9           11   10              9     10   11
 */


class NodeSwap{
    private int value;
    private NodeSwap leftNode;
    private NodeSwap rightNode;
    
    private int depth = 0;
    
    public NodeSwap(){}
    
    public NodeSwap(int value){
        this.value = value;
    }
    
    public NodeSwap(int value, int depth){
        this.value = value;
        this.depth = depth;
    }
    
    public void setValue(int value){
        this.value = value;
    }
    
    public int getValue(){
        return this.value;
    }
    
    public NodeSwap getLeftNode(){
        return this.leftNode;
    }
    
    public void setLeftNode(NodeSwap leftNode){
        this.leftNode = leftNode;
    }
    
    public void setRightNode(NodeSwap rightNode){
        this.rightNode = rightNode;
    }
    
    public NodeSwap getRightNode(){
        return this.rightNode;
    }
    
    public int getDepth(){
        return this.depth;
    }
    
    public void setDepth(int depth){
        this.depth = depth;
    }
    
    public boolean hasValue(){ return this.value > 0;}
}

class TreeTwoChildNode{
    
    private NodeSwap rootNode;
    
    private Map<Integer, List<Integer>> mappingByDepth; 
    
    private int maxDepth = 1;
    
    private Map<Integer, NodeSwap> mapTreeNode;
    
    public TreeTwoChildNode(int value){
        rootNode = new NodeSwap(value, 1);
        mappingByDepth = new HashMap<>(); 
        mapTreeNode = new HashMap<>();
        mapTreeNode.put(rootNode.getValue(), rootNode);
    }
    
    public void addNodes(List<List<Integer>> indexes){
        int previousNode = 1;
        
        for(List<Integer> indexNode : indexes){
            NodeSwap preNode = mapTreeNode.get(previousNode++);
            
            if(indexNode.get(0) > -1){
                preNode.setLeftNode(new NodeSwap(indexNode.get(0), preNode.getDepth() + 1));
                mapTreeNode.put(indexNode.get(0), preNode.getLeftNode());
            }
            
            if(indexNode.get(1) > -1){
                preNode.setRightNode(new NodeSwap(indexNode.get(1), preNode.getDepth() + 1));
                mapTreeNode.put(indexNode.get(1), preNode.getRightNode());
            }   
        }
        
        for(NodeSwap node: mapTreeNode.values()){
            List<Integer> nodes = mappingByDepth.getOrDefault(node.getDepth(), new ArrayList<>());
            nodes.add(node.getValue());
            mappingByDepth.put(node.getDepth(), nodes);
            
            if(maxDepth < node.getDepth()) maxDepth = node.getDepth();
        }
        
    //   System.out.println(mappingByDepth);
    }
    
    public void swappingNodesByDepth(int depth){
        int depthV = depth, k = 2;
        while(depthV <= maxDepth){
            List<Integer> nodeList = mappingByDepth.getOrDefault(depthV, new ArrayList<>());
            
            for(Integer nV: nodeList){
        	NodeSwap node = mapTreeNode.get(nV);
                
        	NodeSwap leftNode = node.getLeftNode();
        	NodeSwap righNode = node.getRightNode();
                
                node.setLeftNode(righNode);
                node.setRightNode(leftNode);
            }
            
            depthV = depth * k++;
        }
    }
    
    /**
    *
    * ...
    *
    **/
    public List<Integer> getTreeValues(){
	NodeSwap root = this.rootNode;
        return inOrderTraverse(root);
    }
    
    private List<Integer> inOrderTraverse(NodeSwap root){
        
        if(root == null){
           return new ArrayList<>();
        }
        
        List<Integer> listValue = inOrderTraverse(root.getLeftNode());
        listValue.add(root.getValue());
        listValue.addAll(inOrderTraverse(root.getRightNode()));
        
        return listValue;
    }
}

public class SwapNodes_Algo {
    
    /*
     * Complete the 'swapNodes' function below.
     *
     * The function is expected to return a 2D_INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. 2D_INTEGER_ARRAY indexes
     *  2. INTEGER_ARRAY queries
     */

    public static List<List<Integer>> swapNodes(List<List<Integer>> indexes, List<Integer> queries) {
        
            TreeTwoChildNode treeTwoChildNode = new TreeTwoChildNode(1);
            treeTwoChildNode.addNodes(indexes);
            
            List<List<Integer>> res = new ArrayList<>();
            
          //   System.out.println(treeTwoChildNode.getTreeValues());
            
            for(Integer depthQuery: queries){
                treeTwoChildNode.swappingNodesByDepth(depthQuery);
                res.add(treeTwoChildNode.getTreeValues());
            }
            
            return res;
    }
    
    public static void main(String[] args) {
	// TODO Auto-generated method stub

    }

}
