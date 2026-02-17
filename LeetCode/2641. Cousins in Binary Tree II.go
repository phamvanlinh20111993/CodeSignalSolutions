/**
  url: https://leetcode.com/problems/cousins-in-binary-tree-ii/description/?envType=problem-list-v2&envId=hash-table
  Given the root of a binary tree, replace the value of each node in the tree with the sum of all its cousins' values.

Two nodes of a binary tree are cousins if they have the same depth with different parents.

Return the root of the modified tree.

Note that the depth of a node is the number of edges in the path from the root node to it.

 

Example 1:


Input: root = [5,4,9,1,10,null,7]
Output: [0,0,0,7,7,null,11]
Explanation: The diagram above shows the initial binary tree and the binary tree after changing the value of each node.
- Node with value 5 does not have any cousins so its sum is 0.
- Node with value 4 does not have any cousins so its sum is 0.
- Node with value 9 does not have any cousins so its sum is 0.
- Node with value 1 has a cousin with value 7 so its sum is 7.
- Node with value 10 has a cousin with value 7 so its sum is 7.
- Node with value 7 has cousins with values 1 and 10 so its sum is 11.
Example 2:


Input: root = [3,1,2]
Output: [0,0,0]
Explanation: The diagram above shows the initial binary tree and the binary tree after changing the value of each node.
- Node with value 3 does not have any cousins so its sum is 0.
- Node with value 1 does not have any cousins so its sum is 0.
- Node with value 2 does not have any cousins so its sum is 0.
 

Constraints:

The number of nodes in the tree is in the range [1, 105].
1 <= Node.val <= 104
**/
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func replaceValueInTree(root *TreeNode) *TreeNode {
    type QueueData struct {
        lv int
        node *TreeNode
    }

    queue := []QueueData{{0, root}}

    for len(queue) > 0 {
        var queueLen int = len(queue)
        queueTmp := []QueueData{}
        var sum int = 0
        var level int = 0
        for i := 0; i < queueLen; i++ {
            lv := queue[i].lv
            level = lv
            node := queue[i].node
            if lv < 2 {
                node.Val = 0
            }
            if node.Left != nil {
                sum += node.Left.Val
                queueTmp = append(queueTmp, QueueData{lv+1, node.Left})
            }
            if node.Right != nil {
                sum += node.Right.Val
                queueTmp = append(queueTmp, QueueData{lv+1, node.Right})
            }
        }

        if level > 0 {
           for i := 0; i < len(queue); i++ { 
                node := queue[i].node
                var originLeftVal int = 0
                if node.Left != nil {
                    var tmp int = node.Left.Val
                    originLeftVal = tmp
                    if node.Right != nil {
                        tmp += node.Right.Val
                    }
                    node.Left.Val = sum - tmp
                }
                if node.Right != nil {
                    var tmp int = node.Right.Val
                    if node.Left != nil {
                        tmp += originLeftVal
                    }
                    node.Right.Val = sum - tmp
                }  
           }
        }
        
        queue = queueTmp
    }

    return root
}