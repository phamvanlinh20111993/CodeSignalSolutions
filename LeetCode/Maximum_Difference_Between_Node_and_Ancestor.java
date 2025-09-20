package LeetCode;

/**
 * url: https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/?envType=problem-list-v2&envId=tree
 * 
 * Given the root of a binary tree, find the maximum value v for which there exist different nodes a and b where v = |a.val - b.val| and a is an ancestor of b.

A node a is an ancestor of b if either: any child of a is equal to b or any child of a is an ancestor of b.

 

Example 1:


Input: root = [8,3,10,1,6,null,14,null,null,4,7,13]
Output: 7
Explanation: We have various ancestor-node differences, some of which are given below :
|8 - 3| = 5
|3 - 7| = 4
|8 - 1| = 7
|10 - 13| = 3
Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.
Example 2:


Input: root = [1,null,2,null,0,3]
Output: 3
 

Constraints:

The number of nodes in the tree is in the range [2, 5000].
0 <= Node.val <= 105
 
 */
public class Maximum_Difference_Between_Node_and_Ancestor {

	int diff;

	public void dfs(int max, int min, TreeNode node) {
		if (node == null)
			return;
		max = max < node.val ? node.val : max;
		min = min > node.val ? node.val : min;
		diff = Math.abs(max - min) > diff ? Math.abs(max - min) : diff;
		dfs(max, min, node.left);
		dfs(max, min, node.right);
	}

	public int maxAncestorDiff(TreeNode root) {
		dfs(-1, 1000000, root);
		return diff;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
