package LeetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * url: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
 * 
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

 

Example 1:


Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
Example 2:


Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
Example 3:

Input: root = [1,2], p = 1, q = 2
Output: 1
 

Constraints:

The number of nodes in the tree is in the range [2, 105].
-109 <= Node.val <= 109
All Node.val are unique.
p != q
p and q will exist in the tree.
 */
public class LowestCommonAncestorOfABinaryTree {
	
	List<Integer> newV;
    public void dfs(TreeNode root, TreeNode v, int level,  List<Integer> arr){
        if(newV != null) return;

        if(arr.size() <= level){
            arr.add(root.val);
        } else { 
           arr.set(level, root.val); 
        }

        if(root.val == v.val){
            newV = arr;
            return;
        }

        if(root.left != null){
            dfs(root.left, v, level+1, arr);
        }
        if(root.right != null){
            dfs(root.right, v, level+1, arr);
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<Integer> newValue = new ArrayList<>();
        newV = null;
        dfs(root, p, 0, newValue);
       
        Set<Integer> dis = new HashSet<>();
        for(Integer v : newV){
            dis.add(v);
            if(v.equals(p.val)){
                break;
            }
        }
    
        newV = null;
        newValue = new ArrayList<>();
        dfs(root, q, 0, newValue);
        int ind = 0;
        for(Integer v : newV){
            if(v.equals(q.val)){
                break;
            }
            ind++;
        }

        for(int i = ind; i > -1; i--){
            if(dis.contains(newV.get(i))){
                return new TreeNode(newV.get(i));
            }
        }
       
        return new TreeNode(q.val);
    }
}
