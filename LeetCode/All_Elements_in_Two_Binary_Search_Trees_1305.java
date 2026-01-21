package LeetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * url: https://leetcode.com/problems/all-elements-in-two-binary-search-trees/description/?envType=problem-list-v2&envId=sorting
 * 
 * Given two binary search trees root1 and root2, return a list containing all the integers from both trees sorted in ascending order.

 

Example 1:


Input: root1 = [2,1,4], root2 = [1,0,3]
Output: [0,1,1,2,3,4]
Example 2:


Input: root1 = [1,null,8], root2 = [8,1]
Output: [1,1,8,8]
 

Constraints:

The number of nodes in each tree is in the range [0, 5000].
-105 <= Node.val <= 105
 * 
 */
public class All_Elements_in_Two_Binary_Search_Trees_1305 {

	public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		Queue<TreeNode> queue = new LinkedList<>();
		if (root1 != null) {
			queue.add(root1);
			while (!queue.isEmpty()) {
				TreeNode tN = queue.poll();
				pq.add(tN.val);
				if (tN.left != null) {
					queue.add(tN.left);
				}
				if (tN.right != null) {
					queue.add(tN.right);
				}
			}
		}

		if (root2 != null) {
			queue.add(root2);
			while (!queue.isEmpty()) {
				TreeNode tN = queue.poll();
				pq.add(tN.val);
				if (tN.left != null) {
					queue.add(tN.left);
				}
				if (tN.right != null) {
					queue.add(tN.right);
				}
			}
		}

		List<Integer> list = new ArrayList<>();
		while (!pq.isEmpty()) {
			list.add(pq.poll());
		}
		return list;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
