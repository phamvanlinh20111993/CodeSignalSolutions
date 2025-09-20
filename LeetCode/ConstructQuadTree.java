package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * url: https://leetcode.com/problems/construct-quad-tree/description/?envType=problem-list-v2&envId=tree
 * 
 * Given a n * n matrix grid of 0's and 1's only. We want to represent grid with a Quad-Tree.

Return the root of the Quad-Tree representing grid.

A Quad-Tree is a tree data structure in which each internal node has exactly four children. Besides, each node has two attributes:

val: True if the node represents a grid of 1's or False if the node represents a grid of 0's. Notice that you can assign the val to True or False when isLeaf is False, and both are accepted in the answer.
isLeaf: True if the node is a leaf node on the tree or False if the node has four children.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;
}
We can construct a Quad-Tree from a two-dimensional area using the following steps:

If the current grid has the same value (i.e all 1's or all 0's) set isLeaf True and set val to the value of the grid and set the four children to Null and stop.
If the current grid has different values, set isLeaf to False and set val to any value and divide the current grid into four sub-grids as shown in the photo.
Recurse for each of the children with the proper sub-grid.

If you want to know more about the Quad-Tree, you can refer to the wiki.

Quad-Tree format:

You don't need to read this section for solving the problem. This is only if you want to understand the output format here. The output represents the serialized format of a Quad-Tree using level order traversal, where null signifies a path terminator where no node exists below.

It is very similar to the serialization of the binary tree. The only difference is that the node is represented as a list [isLeaf, val].

If the value of isLeaf or val is True we represent it as 1 in the list [isLeaf, val] and if the value of isLeaf or val is False we represent it as 0.

 

Example 1:


Input: grid = [[0,1],[1,0]]
Output: [[0,1],[1,0],[1,1],[1,1],[1,0]]
Explanation: The explanation of this example is shown below:
Notice that 0 represents False and 1 represents True in the photo representing the Quad-Tree.

Example 2:



Input: grid = [[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0]]
Output: [[0,1],[1,1],[0,1],[1,1],[1,0],null,null,null,null,[1,0],[1,0],[1,1],[1,1]]
Explanation: All values in the grid are not the same. We divide the grid into four sub-grids.
The topLeft, bottomLeft and bottomRight each has the same value.
The topRight have different values so we divide it into 4 sub-grids where each has the same value.
Explanation is shown in the photo below:

 

Constraints:

n == grid.length == grid[i].length
n == 2x where 0 <= x <= 6 
 */

class Node123 {
	public boolean val;
	public boolean isLeaf;
	public Node123 topLeft;
	public Node123 topRight;
	public Node123 bottomLeft;
	public Node123 bottomRight;

	public Node123() {
		this.val = false;
		this.isLeaf = false;
		this.topLeft = null;
		this.topRight = null;
		this.bottomLeft = null;
		this.bottomRight = null;
	}

	public Node123(boolean val, boolean isLeaf) {
		this.val = val;
		this.isLeaf = isLeaf;
		this.topLeft = null;
		this.topRight = null;
		this.bottomLeft = null;
		this.bottomRight = null;
	}

	public Node123(boolean val, boolean isLeaf, Node123 topLeft, Node123 topRight, Node123 bottomLeft,
			Node123 bottomRight) {
		this.val = val;
		this.isLeaf = isLeaf;
		this.topLeft = topLeft;
		this.topRight = topRight;
		this.bottomLeft = bottomLeft;
		this.bottomRight = bottomRight;
	}
}

public class ConstructQuadTree {

	public Integer[][] newCoords(int x, int y, int l) {
		Integer[][] res = { { x, y }, { x + l, y }, { x, y + l }, { x + l, y + l } };

		return res;
	}

	public record KV(boolean isSameValue, boolean val) {
	}

	public KV checkSameValueInRange(int sX, int sY, int l, int[][] grid) {
		int initialVal = grid[sY][sX];
		for (int y = sY; y < sY + l; y++) {
			for (int x = sX; x < sX + l; x++) {
				if (grid[y][x] != initialVal)
					return new KV(false, initialVal == 1 ? true : false);
			}
		}
		return new KV(true, initialVal == 1 ? true : false);
	}

	public record QueueDT(int x, int y, int l, Node123 node) {
	}

	public Node123 construct(int[][] grid) {
		Queue<QueueDT> queue = new LinkedList<>();

		int l = grid.length;
		KV kv = checkSameValueInRange(0, 0, l, grid);
		if (kv.isSameValue) {
			return new Node123(kv.val, true);
		}

		Node123 res = new Node123(true, false);
		Integer[][] init = newCoords(0, 0, l / 2);
		for (int ind = 0; ind < init.length; ind++) {
			kv = checkSameValueInRange(init[ind][0], init[ind][1], l / 2, grid);
			if (kv.isSameValue) {
				if (ind == 0) {
					res.topLeft = new Node123(kv.val, true);
				} else if (ind == 1) {
					res.topRight = new Node123(kv.val, true);
				} else if (ind == 2) {
					res.bottomLeft = new Node123(kv.val, true);
				} else {
					res.bottomRight = new Node123(kv.val, true);
				}
				continue;
			}
			if (ind == 0) {
				res.topLeft = new Node123(true, false);
				queue.add(new QueueDT(init[ind][0], init[ind][1], l / 2, res.topLeft));
			} else if (ind == 1) {
				res.topRight = new Node123(true, false);
				queue.add(new QueueDT(init[ind][0], init[ind][1], l / 2, res.topRight));
			} else if (ind == 2) {
				res.bottomLeft = new Node123(true, false);
				queue.add(new QueueDT(init[ind][0], init[ind][1], l / 2, res.bottomLeft));
			} else {
				res.bottomRight = new Node123(true, false);
				queue.add(new QueueDT(init[ind][0], init[ind][1], l / 2, res.bottomRight));
			}
		}

		while (!queue.isEmpty()) {
			QueueDT stP = queue.poll();

			if (stP.l > 1) {
				Integer[][] dv = newCoords(stP.x, stP.y, stP.l / 2);
				for (int ind = 0; ind < dv.length; ind++) {
					kv = checkSameValueInRange(dv[ind][0], dv[ind][1], stP.l / 2, grid);
					if (kv.isSameValue) {
						if (ind == 0) {
							stP.node.topLeft = new Node123(kv.val, true);
						} else if (ind == 1) {
							stP.node.topRight = new Node123(kv.val, true);
						} else if (ind == 2) {
							stP.node.bottomLeft = new Node123(kv.val, true);
						} else {
							stP.node.bottomRight = new Node123(kv.val, true);
						}
						continue;
					}

					if (ind == 0) {
						stP.node.topLeft = new Node123(true, false);
						queue.add(new QueueDT(dv[ind][0], dv[ind][1], stP.l / 2, stP.node.topLeft));
					} else if (ind == 1) {
						stP.node.topRight = new Node123(true, false);
						queue.add(new QueueDT(dv[ind][0], dv[ind][1], stP.l / 2, stP.node.topRight));
					} else if (ind == 2) {
						stP.node.bottomLeft = new Node123(true, false);
						queue.add(new QueueDT(dv[ind][0], dv[ind][1], stP.l / 2, stP.node.bottomLeft));
					} else {
						stP.node.bottomRight = new Node123(true, false);
						queue.add(new QueueDT(dv[ind][0], dv[ind][1], stP.l / 2, stP.node.bottomRight));
					}
				}
			}
		}

		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
