package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * url: https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/description/?envType=daily-question&envId=2026-01-07
 * 
 * Given the root of a binary tree, split the binary tree into two subtrees by removing one edge such that the product of the sums of the subtrees is maximized.

Return the maximum product of the sums of the two subtrees. Since the answer may be too large, return it modulo 109 + 7.

Note that you need to maximize the answer before taking the mod and not after taking it.

 

Example 1:


Input: root = [1,2,3,4,5,6]
Output: 110
Explanation: Remove the red edge and get 2 binary trees with sum 11 and 10. Their product is 110 (11*10)
Example 2:


Input: root = [1,null,2,3,4,null,null,5,6]
Output: 90
Explanation: Remove the red edge and get 2 binary trees with sum 15 and 6.Their product is 90 (15*6)
 

Constraints:

The number of nodes in the tree is in the range [2, 5 * 104].
1 <= Node.val <= 104
 */
public class Maximum_Product_of_Splitted_Binary_Tree_1339 {
	
	/**
	 * Memory Limit Exceeded
	 *	52 / 54 testcases passed
	 */
	Map<String, Integer> sumByLevelChilds;
    public void postOrder(TreeNode node, int lv, String sign) {
        if (node == null) return;
        postOrder(node.left, lv + 1, sign+"l");
        postOrder(node.right, lv + 1, sign+"r");
        int sum = node.val;
        if(node.left != null) {
            sum += sumByLevelChilds.getOrDefault((lv + 1)+sign+"l", node.left.val);
        }
        if(node.right != null) {
            sum += sumByLevelChilds.getOrDefault((lv + 1)+sign+"r", node.right.val);
        }
        sumByLevelChilds.put(lv + sign, sum);
    }

    public int maxProduct(TreeNode root) {
        if(root == null) return 0;
        sumByLevelChilds = new HashMap<>();
        postOrder(root, 0, "");
     
        long divideMaxSum = 0L;
        Integer totalSum = sumByLevelChilds.get("0");

        for (Map.Entry<String, Integer> entry : sumByLevelChilds.entrySet()) {
            long curDivideSum = Long.valueOf(entry.getValue()) * (totalSum - entry.getValue());
            divideMaxSum = Math.max(curDivideSum, divideMaxSum);
        }
        return (int) (divideMaxSum%1000000007);
    }
    
    // ################################################passed############################################
    List<Integer> subTreeSum;
    public void postOrder(TreeNode node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        int sum = node.val;
        if(node.left != null) {
            sum += node.left.val;
        }
        if(node.right != null) {
            sum += node.right.val;
        }
        node.val = sum;
       // System.out.print(node.val +"-->");
       subTreeSum.add(sum);
    }

    public int maxProduct1(TreeNode root) {
        if(root == null) return 0;

        subTreeSum = new ArrayList<>();
        postOrder(root);
     
        long divideMaxSum = 0L;
        Integer totalSum = subTreeSum.get(subTreeSum.size() - 1);
        for (Integer s : subTreeSum){
            Long currentDivideSum = Long.valueOf(s)*(totalSum - s);
            divideMaxSum = Math.max(currentDivideSum, divideMaxSum);
        }

        return (int) (divideMaxSum%1000000007);
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
