package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Url: https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 * 
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

 

Example 1:


Input: root = [1,2,3,4,5,6,7]
Output: [1,#,2,3,#,4,5,6,7,#]
Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
Example 2:

Input: root = []
Output: []
 

Constraints:

The number of nodes in the tree is in the range [0, 212 - 1].
-1000 <= Node.val <= 1000
 

Follow-up:

You may only use constant extra space.
The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.
 */

class NodeBFS {
	public int val;
	public NodeBFS left;
	public NodeBFS right;
	public NodeBFS next;

	public NodeBFS() {
	}

	public NodeBFS(int _val) {
		val = _val;
	}

	public NodeBFS(int _val, NodeBFS _left, NodeBFS _right, NodeBFS _next) {
		val = _val;
		left = _left;
		right = _right;
		next = _next;
	}
};

public class PopulatingNextRightPointersinEachNode {

	public NodeBFS connect(NodeBFS root) {

		if (root == null)
			return root;

		Queue<NodeBFS> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {
			int s = queue.size();
			NodeBFS conn = null;
			for (int ind = 0; ind < s; ind++) {
				NodeBFS n = queue.poll();
				if (n.left != null) {
					queue.add(n.left);
					queue.add(n.right);
				}

				if (conn == null) {
					conn = n;
				} else {
					conn.next = n;
					conn = conn.next;
				}
			}

		}

		return root;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
