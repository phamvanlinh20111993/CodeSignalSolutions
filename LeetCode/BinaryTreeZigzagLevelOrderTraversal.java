package LeetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/
 * 
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).

 

Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: [[3],[20,9],[15,7]]
Example 2:

Input: root = [1]
Output: [[1]]
Example 3:

Input: root = []
Output: []
 

Constraints:

The number of nodes in the tree is in the range [0, 2000].
-100 <= Node.val <= 100
 */
public class BinaryTreeZigzagLevelOrderTraversal {

	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

		if (root == null) {
			return new ArrayList<>();
		}

		Queue<TreeNode> queue = new LinkedList<>();

		queue.add(root);
		List<List<Integer>> res = new ArrayList<>();
		int pos = 0;
		while (!queue.isEmpty()) {
			List<Integer> temp = new ArrayList<>();
			int size = queue.size();
			for (int ind = 0; ind < size; ind++) {
				TreeNode node = queue.poll();
				if (pos % 2 == 0) {
					temp.add(node.val);
				} else {
					temp.add(0, node.val);
				}

				if (node.left != null) {
					queue.add(node.left);
				}
				if (node.right != null) {
					queue.add(node.right);
				}
			}
			pos++;
			res.add(temp);
		}

		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
