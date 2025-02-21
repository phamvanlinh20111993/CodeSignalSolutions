package LeetCode;

import java.util.LinkedList;
import java.util.List;

/**
 * url: https://leetcode.com/problems/find-elements-in-a-contaminated-binary-tree/description/
 * 
 * Given a binary tree with the following rules:

root.val == 0
For any treeNode:
If treeNode.val has a value x and treeNode.left != null, then treeNode.left.val == 2 * x + 1
If treeNode.val has a value x and treeNode.right != null, then treeNode.right.val == 2 * x + 2
Now the binary tree is contaminated, which means all treeNode.val have been changed to -1.

Implement the FindElements class:

FindElements(TreeNode* root) Initializes the object with a contaminated binary tree and recovers it.
bool find(int target) Returns true if the target value exists in the recovered binary tree.
 

Example 1:


Input
["FindElements","find","find"]
[[[-1,null,-1]],[1],[2]]
Output
[null,false,true]
Explanation
FindElements findElements = new FindElements([-1,null,-1]); 
findElements.find(1); // return False 
findElements.find(2); // return True 
Example 2:


Input
["FindElements","find","find","find"]
[[[-1,-1,-1,-1,-1]],[1],[3],[5]]
Output
[null,true,true,false]
Explanation
FindElements findElements = new FindElements([-1,-1,-1,-1,-1]);
findElements.find(1); // return True
findElements.find(3); // return True
findElements.find(5); // return False
Example 3:


Input
["FindElements","find","find","find","find"]
[[[-1,null,-1,-1,null,-1]],[2],[3],[4],[5]]
Output
[null,true,false,false,true]
Explanation
FindElements findElements = new FindElements([-1,null,-1,-1,null,-1]);
findElements.find(2); // return True
findElements.find(3); // return False
findElements.find(4); // return False
findElements.find(5); // return True
 

Constraints:

TreeNode.val == -1
The height of the binary tree is less than or equal to 20
The total number of nodes is between [1, 104]
Total calls of find() is between [1, 104]
0 <= target <= 106
 * 
 * 
 */

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode() {
	}

	TreeNode(int val) {
		this.val = val;
	}

	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}

public class FindElementsInAContaminatedBinaryTree {
	
	 TreeNode r;
	    int val = -1;

	    public void rebuildTree(TreeNode root, int value){
	        if(root != null){
	            root.val = value;
	        }
	        if(root.left != null){
	            rebuildTree(root.left, 2*value + 1);
	        }
	        if(root.right != null){
	            rebuildTree(root.right, 2*value + 2);
	        }
	    }

	    public FindElements(TreeNode root) {
	        rebuildTree(root, 0);
	        r = root;
	    }

	    public void findNum(TreeNode root, int pos, LinkedList<Integer> rootL){
	        if(pos == rootL.size()){
	            val = 1;
	            return;
	        }

	        if(root.left != null && root.left.val == rootL.get(pos)){
	            findNum(root.left, pos+1, rootL);
	        }
	        if(root.right != null && root.right.val == rootL.get(pos)){
	            findNum(root.right, pos+1, rootL);
	        }
	    }
	    
	    public boolean find(int target) {
	    	LinkedList<Integer> l = new LinkedList<>();
	        while(target > 0){
	            l.addFirst(target);
	            target = target%2 == 0 ? target/2 - 1 : (target-1) / 2;
	        }
	        val = -1;
	        findNum(r, 0, l);
	        return val > -1 ? true : false;
	    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
