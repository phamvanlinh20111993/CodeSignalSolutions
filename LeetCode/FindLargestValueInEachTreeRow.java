package LeetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * url: https://leetcode.com/problems/find-largest-value-in-each-tree-row/
 * 
 * Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).

 

Example 1:


Input: root = [1,3,2,5,3,null,9]
Output: [1,3,9]
Example 2:

Input: root = [1,2,3]
Output: [1,3]
 

Constraints:

The number of nodes in the tree will be in the range [0, 104].
-231 <= Node.val <= 231 - 1

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

class TreeLevel {
	public int level;
	public TreeNode node;

	public TreeLevel(int lv, TreeNode node) {
		this.level = lv;
		this.node = node;
	}
}

public class FindLargestValueInEachTreeRow {

	public List<Integer> largestValues(TreeNode root) {
		List<Integer> res = new ArrayList<>();

		Queue<TreeLevel> q = new LinkedList<>();
		q.add(new TreeLevel(0, root));
		while (q.size() > 0) {
			TreeLevel tl = q.poll();
			if (tl.node == null)
				continue;

			if (res.size() <= tl.level) {
				res.add(tl.node.val);
			}
			if (res.get(tl.level) < tl.node.val) {
				res.set(tl.level, tl.node.val);
			}
			q.add(new TreeLevel(tl.level + 1, tl.node.left));
			q.add(new TreeLevel(tl.level + 1, tl.node.right));
		}

		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
