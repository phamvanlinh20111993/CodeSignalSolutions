package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * url: https://leetcode.com/problems/balance-a-binary-search-tree/?envType=daily-question&envId=2026-02-09
 * 
 * Given the root of a binary search tree, return a balanced binary search tree with the same node values. If there is more than one answer, return any of them.

A binary search tree is balanced if the depth of the two subtrees of every node never differs by more than 1.

 

Example 1:


Input: root = [1,null,2,null,3,null,4,null,null]
Output: [2,1,3,null,null,null,4]
Explanation: This is not the only correct answer, [3,1,4,null,2] is also correct.
Example 2:


Input: root = [2,1,3]
Output: [2,1,3]
 

Constraints:

The number of nodes in the tree is in the range [1, 104].
1 <= Node.val <= 105
 * 
 */
public class Balance_a_Binary_Search_Tree_1382 {

	public void buildNode(TreeNode node, List<Integer> list, int left, int right) {
		// if(left >= right) return;
		int mid = (left + right) / 2;
		node.val = list.get(mid);
		// build left node
		if (left < mid) {
			node.left = new TreeNode();
			buildNode(node.left, list, left, mid);
		}

		// build right node
		if (mid + 1 < right) {
			node.right = new TreeNode();
			buildNode(node.right, list, mid + 1, right);
		}
	}

	public List<Integer> getSortedArrInTree(TreeNode root) {
		List<Integer> arr = new ArrayList<>();
		if (root.left != null) {
			arr.addAll(getSortedArrInTree(root.left));
		}
		arr.add(root.val);
		if (root.right != null) {
			arr.addAll(getSortedArrInTree(root.right));
		}

		return arr;
	}

	// refer using hint
	public TreeNode balanceBST(TreeNode root) {
		List<Integer> list = getSortedArrInTree(root);
		TreeNode newRoot = new TreeNode();
		buildNode(newRoot, list, 0, list.size());

		return newRoot;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
