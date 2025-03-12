package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Url: https://leetcode.com/problems/recover-a-tree-from-preorder-traversal/
 * 
 * We run a preorder depth-first search (DFS) on the root of a binary tree.

At each node in this traversal, we output D dashes (where D is the depth of this node), then we output the value of this node.  If the depth of a node is D, the depth of its immediate child is D + 1.  The depth of the root node is 0.

If a node has only one child, that child is guaranteed to be the left child.

Given the output traversal of this traversal, recover the tree and return its root.

 

Example 1:


Input: traversal = "1-2--3--4-5--6--7"
Output: [1,2,5,3,4,6,7]
Example 2:


Input: traversal = "1-2--3---4-5--6---7"
Output: [1,2,5,3,null,6,null,4,null,7]
Example 3:


Input: traversal = "1-401--349---90--88"
Output: [1,401,null,349,88,90]
 

Constraints:

The number of nodes in the original tree is in the range [1, 1000].
1 <= Node.val <= 109


 */
public class RecoverATreeFromPreorderTraversal {

	public static List<Integer> toListNode(String traversal) {
		List<Integer> nodeLevel = new ArrayList<>();
		StringBuilder dash = new StringBuilder("");
		StringBuilder numStr = new StringBuilder("");

		for (int ind = 0; ind < traversal.length(); ind++) {
			if (Character.isDigit(traversal.charAt(ind))) {
				if (!dash.isEmpty()) {
					nodeLevel.add(dash.length());
					dash = new StringBuilder("");
				}
				numStr.append(traversal.charAt(ind));
			} else {
				if (!numStr.isEmpty()) {
					nodeLevel.add(Integer.valueOf(numStr.toString()));
					numStr = new StringBuilder("");
				}
				dash.append(traversal.charAt(ind));
			}
		}
		nodeLevel.add(Integer.valueOf(numStr.toString()));
		return nodeLevel;
	}

	static Integer nodeInd;

	public static void dfs(List<Integer> nodeLevel, int level, TreeNode treeNode) {
		if (nodeInd + 1 < nodeLevel.size() && level + 1 == nodeLevel.get(nodeInd + 1)) {
			nodeInd += 2;
			treeNode.left = new TreeNode(nodeLevel.get(nodeInd));
			dfs(nodeLevel, level + 1, treeNode.left);
		}

		if (nodeInd + 1 < nodeLevel.size() && level + 1 == nodeLevel.get(nodeInd + 1)) {
			nodeInd += 2;
			treeNode.right = new TreeNode(nodeLevel.get(nodeInd));
			dfs(nodeLevel, level + 1, treeNode.right);
		}
	}

	public static TreeNode recoverFromPreorder(String traversal) {
		List<Integer> nodeLevel = toListNode(traversal);
		nodeInd = 0;
		TreeNode treeNode = new TreeNode(Integer.valueOf(nodeLevel.get(0)));
		dfs(nodeLevel, 0, treeNode);

		return treeNode;
	}

	public static void main(String[] args) {

		recoverFromPreorder("1-2--3--4-5--6--7");

		recoverFromPreorder("1-2--3---4-5--6---7");

		recoverFromPreorder("1-401--349---90--88");

	}

}
