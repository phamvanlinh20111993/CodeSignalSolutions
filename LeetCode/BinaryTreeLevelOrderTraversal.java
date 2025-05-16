package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * url: https://leetcode.com/problems/binary-tree-level-order-traversal/
 * 
 * Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).

 

Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: [[3],[9,20],[15,7]]
Example 2:

Input: root = [1]
Output: [[1]]
Example 3:

Input: root = []
Output: []
 

Constraints:

The number of nodes in the tree is in the range [0, 2000].
-1000 <= Node.val <= 1000

 */
public class BinaryTreeLevelOrderTraversal {

	public record Record(TreeNode node, int lv) {

	}

	public List<List<Integer>> levelOrder(TreeNode root) {

		if (root == null)
			return new ArrayList<>();
		// using bfs
		Queue<Record> queue = new LinkedList<>();
		queue.add(new Record(root, 1));

		Map<Integer, List<Integer>> m = new HashMap<>();
		while (!queue.isEmpty()) {
			Record r = queue.poll();
			List<Integer> re = m.getOrDefault(r.lv, new ArrayList<Integer>());
			re.add(r.node.val);
			m.put(r.lv, re);

			if (r.node.left != null) {
				queue.add(new Record(r.node.left, r.lv + 1));
			}
			if (r.node.right != null) {
				queue.add(new Record(r.node.right, r.lv + 1));
			}
		}

		return new ArrayList<>(m.values());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
