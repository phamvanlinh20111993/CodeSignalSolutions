package LeetCode;

/**
 * url: https://leetcode.com/problems/find-bottom-left-tree-value/description/
 * 
 * Given the root of a binary tree, return the leftmost value in the last row of the tree.

 

Example 1:


Input: root = [2,1,3]
Output: 1
Example 2:


Input: root = [1,2,3,4,null,5,6,null,null,7]
Output: 7
 

Constraints:

The number of nodes in the tree is in the range [1, 104].
-231 <= Node.val <= 231 - 1
 */
public class FindBottomLeftTreeValue {
	

    int maxLevel = -1;
    int maxVal = -1;

    public void preOrder(TreeNode root, int lv){
        if(root == null) return;

        preOrder(root.left, lv+1);

        preOrder(root.right, lv+1);

        if(maxLevel < lv){
            maxVal = root.val;
            maxLevel = lv;
        }

    }

    public int findBottomLeftValue(TreeNode root) {
         maxLevel = -1;
         maxVal = -1;

         preOrder(root, 0);
         
         return maxVal;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
