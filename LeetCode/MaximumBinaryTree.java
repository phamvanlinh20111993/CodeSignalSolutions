package LeetCode;

/**
 * Url: https://leetcode.com/problems/maximum-binary-tree/?envType=problem-list-v2&envId=tree
 * 
 * You are given an integer array nums with no duplicates. A maximum binary tree can be built recursively from nums using the following algorithm:

Create a root node whose value is the maximum value in nums.
Recursively build the left subtree on the subarray prefix to the left of the maximum value.
Recursively build the right subtree on the subarray suffix to the right of the maximum value.
Return the maximum binary tree built from nums.

 

Example 1:


Input: nums = [3,2,1,6,0,5]
Output: [6,3,5,null,2,0,null,null,1]
Explanation: The recursive calls are as follow:
- The largest value in [3,2,1,6,0,5] is 6. Left prefix is [3,2,1] and right suffix is [0,5].
    - The largest value in [3,2,1] is 3. Left prefix is [] and right suffix is [2,1].
        - Empty array, so no child.
        - The largest value in [2,1] is 2. Left prefix is [] and right suffix is [1].
            - Empty array, so no child.
            - Only one element, so child is a node with value 1.
    - The largest value in [0,5] is 5. Left prefix is [0] and right suffix is [].
        - Only one element, so child is a node with value 0.
        - Empty array, so no child.
Example 2:


Input: nums = [3,2,1]
Output: [3,null,2,null,1]
 

Constraints:

1 <= nums.length <= 1000
0 <= nums[i] <= 1000
All integers in nums are unique.

 */
public class MaximumBinaryTree {
	
	public void buildTree(int left, int right, int[] nums, TreeNode tree) {

		if (left < 0 || right < left)
			return;

		int maxInd = left, max = nums[left];
		for (int ind = left; ind <= right; ind++) {
			if (max < nums[ind]) {
				max = nums[ind];
				maxInd = ind;
			}
		}

		tree.val = max;
		if (left <= maxInd - 1) {
			tree.left = new TreeNode();
			buildTree(left, maxInd - 1, nums, tree.left);
		}

		if (maxInd + 1 <= right) {
			tree.right = new TreeNode();
			buildTree(maxInd + 1, right, nums, tree.right);
		}
	}

	public TreeNode constructMaximumBinaryTree(int[] nums) {
		TreeNode tree = new TreeNode();
		buildTree(0, nums.length - 1, nums, tree);
		return tree;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
