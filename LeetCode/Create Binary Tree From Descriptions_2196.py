

"""
url: https://leetcode.com/problems/create-binary-tree-from-descriptions/description/?envType=daily-question&envId=2026-06-07

You are given a 2D integer array descriptions where descriptions[i] = [parenti, childi, isLefti] indicates that parenti is the parent of childi in a binary tree of unique values. Furthermore,

If isLefti == 1, then childi is the left child of parenti.
If isLefti == 0, then childi is the right child of parenti.
Construct the binary tree described by descriptions and return its root.

The test cases will be generated such that the binary tree is valid.

 

Example 1:


Input: descriptions = [[20,15,1],[20,17,0],[50,20,1],[50,80,0],[80,19,1]]
Output: [50,20,80,15,17,19]
Explanation: The root node is the node with value 50 since it has no parent.
The resulting binary tree is shown in the diagram.
Example 2:


Input: descriptions = [[1,2,1],[2,3,0],[3,4,1]]
Output: [1,2,null,null,3,4]
Explanation: The root node is the node with value 1 since it has no parent.
The resulting binary tree is shown in the diagram.
 

Constraints:

1 <= descriptions.length <= 104
descriptions[i].length == 3
1 <= parenti, childi <= 105
0 <= isLefti <= 1
The binary tree described by descriptions is valid.
"""

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def createBinaryTree(self, descriptions: List[List[int]]) -> Optional[TreeNode]:
        m = {}
        s = set()
        for d in descriptions:
            if d[0] in m:
                node = m[d[0]]
            else:
                node = TreeNode(d[0])
                m[d[0]] = node

            s.add(d[1])
            if d[1] in m:
                childNode = m[d[1]]
            else:
                childNode = TreeNode(d[1])
                m[d[1]] = childNode
               

            if d[2] == 1:
                node.left = childNode
            else:
                node.right = childNode

        for d in descriptions:
            if d[0] not in s:
                return m[d[0]]

        #for key, value in m.items():
        #    print(f"Key: {key} -> Value: {value}")

        return TreeNode()