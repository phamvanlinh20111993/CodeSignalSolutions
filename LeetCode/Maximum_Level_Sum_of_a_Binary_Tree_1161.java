package LeetCode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * url: https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/description/?envType=daily-question&envId=2026-01-06
 * 
 * Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.

Return the smallest level x such that the sum of all the values of nodes at level x is maximal.

 

Example 1:


Input: root = [1,7,0,7,-8,null,null]
Output: 2
Explanation: 
Level 1 sum = 1.
Level 2 sum = 7 + 0 = 7.
Level 3 sum = 7 + -8 = -1.
So we return the level with the maximum sum which is level 2.
Example 2:

Input: root = [989,null,10250,98693,-89388,null,null,null,-32127]
Output: 2
 

Constraints:

The number of nodes in the tree is in the range [1, 104].
-105 <= Node.val <= 105
 */
public class Maximum_Level_Sum_of_a_Binary_Tree_1161 {

	record NodeLevel(TreeNode node, int level) {
	}

	public int maxLevelSum(TreeNode root) {
		Map<Integer, Integer> sumByLevel = new HashMap<>();

		Queue<NodeLevel> queue = new LinkedList<>();
		queue.add(new NodeLevel(root, 1));
		while (!queue.isEmpty()) {
			NodeLevel nodeLevel = queue.poll();

			sumByLevel.put(nodeLevel.level, sumByLevel.getOrDefault(nodeLevel.level, 0) + nodeLevel.node.val);

			if (nodeLevel.node.left != null) {
				queue.add(new NodeLevel(nodeLevel.node.left, nodeLevel.level + 1));
			}
			if (nodeLevel.node.right != null) {
				queue.add(new NodeLevel(nodeLevel.node.right, nodeLevel.level + 1));
			}
		}

		Integer levelMaxSum = 0;
		Integer maxSumByLevel = -100000;
		for (Map.Entry<Integer, Integer> entry : sumByLevel.entrySet()) {
			if (maxSumByLevel < entry.getValue()) {
				maxSumByLevel = entry.getValue();
				levelMaxSum = entry.getKey();
			}
		}

		return levelMaxSum;
	}
	
	public int maxLevelSum_Improvement(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int levelMaxSum = 0;
        int maxSumByLevel = -100000;
        int level = 0;
        while(!queue.isEmpty()){
            level++;
            int sumByLevel = 0;
            int queueSize = queue.size();
            for(int i = 0; i < queueSize; i++){
                TreeNode node = queue.poll();
                sumByLevel += node.val;
                if(node.left != null) {
                    queue.add(node.left);
                }
                if(node.right != null) {
                    queue.add(node.right);
                }
            }

            if (maxSumByLevel < sumByLevel){
                maxSumByLevel = sumByLevel;
                levelMaxSum = level;
            }
        }

        return levelMaxSum;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
