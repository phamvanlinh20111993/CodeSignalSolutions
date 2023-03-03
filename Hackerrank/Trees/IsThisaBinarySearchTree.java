package Hackerrank.Trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author PhamLinh
 * @Url https://www.hackerrank.com/challenges/is-binary-search-tree/problem
 *
 *      For the purposes of this challenge, we define a binary tree to be a
 *      binary search tree with the following ordering requirements:
 * 
 *      The value of every node in a node's left subtree is less than the data
 *      value of that node. The value of every node in a node's right subtree is
 *      greater than the data value of that node. Given the root node of a
 *      binary tree, can you determine if it's also a binary search tree?
 * 
 *      Complete the function in your editor below, which has parameter: a
 *      pointer to the root of a binary tree. It must return a boolean denoting
 *      whether or not the binary tree is a binary search tree. You may have to
 *      write one or more helper functions to complete this challenge.
 * 
 *      Input Format
 * 
 *      You are not responsible for reading any input from stdin. Hidden code
 *      stubs will assemble a binary tree and pass its root node to your
 *      function as an argument.
 * 
 *      Constraints
 * 
 *      Output Format
 * 
 *      You are not responsible for printing any output to stdout. Your function
 *      must return true if the tree is a binary search tree; otherwise, it must
 *      return false. Hidden code stubs will print this result as a Yes or No
 *      answer on a new line.
 * 
 *      Sample Input
 * 
 *      tree
 * 
 *      Sample Output
 * 
 *      No
 */
class Node {
	int data;
	Node left;
	Node right;
}

public class IsThisaBinarySearchTree {

	boolean isBinary = true;

	int[] dfs(Node root) {
		List<Integer> vals = new ArrayList<Integer>();
		vals.add(root.data);

		if (root.left != null) {
			int[] data = dfs(root.left);
			vals.add(data[0]);
			vals.add(data[1]);
			isBinary = data[0] >= root.data || data[1] >= root.data ? false : isBinary;
		}

		if (root.right != null) {
			int[] data = dfs(root.right);
			vals.add(data[0]);
			vals.add(data[1]);
			isBinary = data[0] <= root.data || data[1] <= root.data ? false : isBinary;
		}

		return new int[] { Collections.min(vals), Collections.max(vals) };
	}

	boolean checkBST(Node root) {
		dfs(root);
		return isBinary;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
