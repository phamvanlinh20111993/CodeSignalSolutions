package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * url: https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/description/
 * 
 * Given the root of a binary tree, the depth of each node is the shortest distance to the root.

Return the smallest subtree such that it contains all the deepest nodes in the original tree.

A node is called the deepest if it has the largest depth possible among any node in the entire tree.

The subtree of a node is a tree consisting of that node, plus the set of all descendants of that node.

 

Example 1:


Input: root = [3,5,1,6,2,0,8,null,null,7,4]
Output: [2,7,4]
Explanation: We return the node with value 2, colored in yellow in the diagram.
The nodes coloured in blue are the deepest nodes of the tree.
Notice that nodes 5, 3 and 2 contain the deepest nodes in the tree but node 2 is the smallest subtree among them, so we return it.
Example 2:

Input: root = [1]
Output: [1]
Explanation: The root is the deepest node in the tree.
Example 3:

Input: root = [0,1,3,null,2]
Output: [2]
Explanation: The deepest node in the tree is 2, the valid subtrees are the subtrees of nodes 2, 1 and 0 but the subtree of node 2 is the smallest.
 

Constraints:

The number of nodes in the tree will be in the range [1, 500].
0 <= Node.val <= 500
The values of the nodes in the tree are unique.
 

Note: This question is the same as 1123: https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/
 * 
 */
public class SmallestSubtreeWithAllTheDeepestNodes {
	
	public record Info(TreeNode curr, int height, List<TreeNode> path) {
	}

	public TreeNode subtreeWithAllDeepest(TreeNode root) {
		Stack<Info> st = new Stack<>();
		int maxH = -1;

		List<Info> infos = new ArrayList<>();

		st.push(new Info(root, 0, new ArrayList<>()));
		while (!st.isEmpty()) {
			Info inf = st.pop();

			TreeNode curr = inf.curr;
			int h = inf.height;

			if (h > maxH) {
				maxH = h;
				infos = new ArrayList<>();
				infos.add(inf);
			} else if (h == maxH) {
				infos.add(inf);
			}
			if (curr.left != null) {
				List<TreeNode> path = new ArrayList<>(inf.path);
				path.add(inf.curr);
				st.push(new Info(curr.left, h + 1, path));
			}

			if (curr.right != null) {
				List<TreeNode> path = new ArrayList<>(inf.path);
				path.add(inf.curr);
				st.push(new Info(curr.right, h + 1, path));
			}
		}

		List<TreeNode> pathR = infos.get(0).path;
		pathR.add(infos.get(0).curr);
		int minInd = pathR.size() - 1;
		for (int ind = 1; ind < infos.size(); ind++) {
			List<TreeNode> path = infos.get(ind).path;
			path.add(infos.get(ind).curr);
			int p = path.size() - 1;

			while (p > -1) {
				if (pathR.get(p).val == path.get(p).val) {
					minInd = p < minInd ? p : minInd;
					break;
				}
				p--;
			}
		}

		return pathR.get(minInd);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
