package LeetCode;

/**
 * url: https://leetcode.com/problems/count-nodes-equal-to-average-of-subtree/submissions/2137601131/?envType=daily-question&envId=2026-09-10
 * 
 * Given the root of a binary tree, return the number of nodes where the value of the node is equal to the average of the values in its subtree.

Note:

The average of n elements is the sum of the n elements divided by n and rounded down to the nearest integer.
A subtree of root is a tree consisting of root and all of its descendants.
 

Example 1:


Input: root = [4,8,5,0,1,null,6]
Output: 5
Explanation: 
For the node with value 4: The average of its subtree is (4 + 8 + 5 + 0 + 1 + 6) / 6 = 24 / 6 = 4.
For the node with value 5: The average of its subtree is (5 + 6) / 2 = 11 / 2 = 5.
For the node with value 0: The average of its subtree is 0 / 1 = 0.
For the node with value 1: The average of its subtree is 1 / 1 = 1.
For the node with value 6: The average of its subtree is 6 / 1 = 6.
Example 2:


Input: root = [1]
Output: 1
Explanation: For the node with value 1: The average of its subtree is 1 / 1 = 1.
 

Constraints:

The number of nodes in the tree is in the range [1, 1000].
0 <= Node.val <= 1000
 */
public class Count_Nodes_Equal_to_Average_of_Subtree_2265 {

	int res = 0;

	public int[] dfs(TreeNode root) {
		int sum = root.val;
		int amountE = 1;
		if (root.left != null) {
			int[] l = dfs(root.left);
			sum += l[0];
			amountE += l[1];
		}

		if (root.right != null) {
			int[] r = dfs(root.right);
			sum += r[0];
			amountE += r[1];
		}
		res += ((sum / amountE) == root.val) ? 1 : 0;
		return new int[] { sum, amountE };
	}

	public int averageOfSubtree(TreeNode root) {
		res = 0;
		dfs(root);

		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
