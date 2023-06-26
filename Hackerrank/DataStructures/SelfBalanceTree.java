package Hackerrank.DataStructures;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author PhamLinh
 * @link https://www.hackerrank.com/challenges/self-balancing-tree/problem?isFullScreen=true
 * 
 * An AVL tree (Georgy Adelson-Velsky and Landis' tree, named after the inventors) is a self-balancing binary search tree. In an AVL tree, the heights of the two child subtrees of any node differ by at most one; if at any time they differ by more than one, rebalancing is done to restore this property.

We define balance factor for each node as :

balanceFactor = height(left subtree) - height(right subtree)
The balance factor of any node of an AVL tree is in the integer range [-1,+1]. If after any modification in the tree, the balance factor becomes less than −1 or greater than +1, the subtree rooted at this node is unbalanced, and a rotation is needed.

AVL_Tree_Rebalancing.svg.png

(https://en.wikipedia.org/wiki/AVL_tree)

You are given a pointer to the root of an AVL tree. You need to insert a value into this tree and perform the necessary rotations to ensure that it remains balanced.

Input Format

You are given a function,

node *insert(node * root,int new_val)
{


}
'node' is defined as :

struct node
{
int val;            //value
struct node* left;  //left child
struct node* right; //right child
int ht;             //height of the node
} node;
You only need to complete the function.

Note: All the values in the tree will be distinct. Height of a Null node is -1 and the height of the leaf node is 0.


Output Format

Insert the new value into the tree and return a pointer to the root of the tree. Ensure that the tree remains balanced.

Sample Input

    3
  /  \
 2    4
       \
        5
The value to be inserted is 6.

Sample Output

    3
  /  \
 2    5
     / \
    4   6
Explanation

After inserting 6 in the tree. the tree becomes:

    3 (Balance Factor = -2)
  /  \
 2    4 (Balance Factor = -2)
       \
        5 (Balance Factor = -1)
         \
          6 (Balance Factor = 0)
Balance Factor of nodes 3 and 4 is no longer in the range [-1,1]. We need to perform a rotation to balance the tree. This is the right right case. We perform a single rotation to balance the tree.

After performing the rotation, the tree becomes :

                              3 (Balance Factor = -1)
                            /   \
      (Balance Factor = 0) 2     5 (Balance Factor = 0)
                                / \
           (Balance Factor = 0)4   6 (Balance Factor = 0)
 */

class Node1 {
    int val; // Value
    int ht; // Height
    Node1 left; // Left child
    Node1 right; // Right child
}

public class SelfBalanceTree {
    private static Node1 root = null;

    /**
     * 
     * @param a
     * @param b
     * @return
     */
    private static int compare(int a, int b) {
	return a > b ? 1 : a == b ? 0 : -1;
    }

    /**
     * 
     * @param rootAffectNode
     *            Z node, check the image
     */
    private static void LL(Node1 rootAffectNode) {
	Node1 childLeftRootAffectNode = rootAffectNode.left;
	Node1 childRightRootAffectNode = rootAffectNode.right;

	Integer data = rootAffectNode.val;
	rootAffectNode.val = childLeftRootAffectNode.val;

	Node1 newRightNode = new Node1();
	newRightNode.val = data;
	newRightNode.right = childRightRootAffectNode;
	newRightNode.left = childLeftRootAffectNode.right;

	rootAffectNode.right = newRightNode;
	rootAffectNode.left = childLeftRootAffectNode.left;
	// re-update height
	newRightNode.ht = updateHeight(newRightNode);
	rootAffectNode.ht = updateHeight(rootAffectNode);
    }

    /**
     * 
     * @param Z
     *            node, check the image
     *
     */
    private static void RL(Node1 Z) {
	Node1 Y = Z.right;
	Node1 X = Y.left;
	Node1 T3 = X.right;

	Y.left = T3;
	X.right = Y;
	Z.right = X;

	// re update height
	Z.ht = updateHeight(Z);
	Y.ht = updateHeight(Y);
	X.ht = updateHeight(X);

	RR(Z);
    }

    /**
     * 
     * @param Z
     *            node, check the image
     * @param insertedNode
     */
    private static void LR(Node1 Z) {

	Node1 Y = Z.left;
	Node1 X = Y.right;
	Node1 T2 = X.left;

	Y.right = T2;
	X.left = Y;
	Z.left = X;

	// re update height
	Z.ht = updateHeight(Z);
	Y.ht = updateHeight(Y);
	X.ht = updateHeight(X);

	LL(Z);
    }

    /**
     * 
     * @param rootAffectNode
     */
    private static void RR(Node1 rootAffectNode) {
	Node1 childLeftRootAffectNode = rootAffectNode.left;
	Node1 childRightRootAffectNode = rootAffectNode.right;

	Integer data = rootAffectNode.val;
	rootAffectNode.val = childRightRootAffectNode.val;

	rootAffectNode.right = childRightRootAffectNode.right;

	Node1 newNode = new Node1();
	newNode.val = data;
	newNode.left = childLeftRootAffectNode;
	newNode.right = childRightRootAffectNode.left;

	rootAffectNode.left = newNode;

	// re update height
	newNode.ht = updateHeight(newNode);
	rootAffectNode.ht = updateHeight(rootAffectNode);
    }

    /**
     * 
     * @param rootAffectNode
     * @param insertedNode
     */
    private static void reBalanceTree(Node1 rootAffectNode, Integer insertedNode) {

	int factorAffectRoot = compare(insertedNode, rootAffectNode.val);

	Integer compareData = factorAffectRoot < 0 ? rootAffectNode.left.val : rootAffectNode.right.val;
	int factorAffectChildRoot = compare(insertedNode, compareData);

	if (factorAffectRoot > 0 && factorAffectChildRoot > 0) {
	    RR(rootAffectNode);
	} else if (factorAffectRoot < 0 && factorAffectChildRoot > 0) {
	    LR(rootAffectNode);
	} else if (factorAffectRoot > 0 && factorAffectChildRoot < 0) {
	    RL(rootAffectNode);
	} else if (factorAffectRoot < 0 && factorAffectChildRoot < 0) {
	    LL(rootAffectNode);
	}
    }

    /**
     * 
     * @param rootNode
     * @return
     */
    private static Integer updateBalanceFactor(Node1 rootNode) {
	int leftHeight = 0;
	if (rootNode.left != null) {
	    leftHeight = rootNode.left.ht + 1;
	}

	int rightHeight = 0;
	if (rootNode.right != null) {
	    rightHeight = rootNode.right.ht + 1;
	}

	return rightHeight - leftHeight;
    }

    private static Integer updateHeight(Node1 rootNode) {
	int leftHeight = 0;
	if (rootNode.left != null) {
	    leftHeight = rootNode.left.ht;
	}

	int rightHeight = 0;
	if (rootNode.right != null) {
	    rightHeight = rootNode.right.ht;
	}
	int h = rootNode.left == null && rootNode.right == null ? 0 : 1;
	return Math.max(leftHeight, rightHeight) + h;
    }

    private static void updateTreeHeight(Node1 rootNode, Integer node) {

	if (rootNode == null) {
	    return;
	}

	if (rootNode.val < node) {
	    updateTreeHeight(rootNode.left, node);
	}

	if (rootNode.val > node) {
	    updateTreeHeight(rootNode.right, node);
	}

	rootNode.ht = updateHeight(rootNode);
    }

    /**
     * 
     * @param tree
     * @param node
     */
    private static void insert1(Node1 tree, Integer node) {

	int factor = compare(tree.val, node);

	if (factor > 0) {
	    if (tree.left != null) {
		insert1(tree.left, node);
	    }
	} else {
	    if (tree.right != null) {
		insert1(tree.right, node);
	    }
	}

	if (factor != 0) {
	    Node1 updateData = new Node1();
	    updateData.val = node;
	    updateData.ht = 0;

	    if (factor > 0 && tree.left == null) {
		tree.left = updateData;
	    } else if (factor < 0 && tree.right == null) {
		tree.right = updateData;
	    }
	}

	tree.ht = updateHeight(tree);

	int balanceFactor = updateBalanceFactor(tree);

	if (balanceFactor > 1 || balanceFactor < -1) {
	    reBalanceTree(tree, node);
	//    updateTreeHeight(tree, node);
	}
    }

    static Node1 insert(Node1 root, int val) {

	if (root == null) {
	    root = new Node1();
	    root.val = val;
	    return root;
	}

	insert1(root, val);
	return root;
    }

    /**
     * 
     * @param node
     * @param level
     */
    private static void dfsPrint(Node1 node, int level) {
	if (node == null)
	    return;
	if (level == 0) {
	    System.out.println("---ROOT: V:" + node.val + "#H:" + node.ht + "#BF:" + updateBalanceFactor(node));
	} else {
	    for (int ind = 0; ind <= level; ind++) {
		System.out.print("----");
	    }
	    System.out.print("NODE: V:" + node.val + "#H:" + node.ht + "BF:" + updateBalanceFactor(node));
	    System.out.println();
	}
	dfsPrint(node.left, level + 1);
	dfsPrint(node.right, level + 1);
    }

    public static void test1() {
	List<Integer> ds = Arrays.asList(14, 25, 21, 10, 23, 7, 26, 12, 30, 16);

	root = null;
	for (Integer d : ds) {
	    root = insert(root, d);
	}

	dfsPrint(root, 1);
	System.out.println("###############################################");
	root = insert(root, 19);
	dfsPrint(root, 1);
    }

    public static void test2() {
	List<Integer> ds = Arrays.asList(3, 2, 4, 5);

	root = null;
	for (Integer d : ds) {
	    root = insert(root, d);
	}

	dfsPrint(root, 1);
	System.out.println("###############################################");
	root = insert(root, 6);
	dfsPrint(root, 1);
    }

    public static void test3() {
	List<Integer> ds = Arrays.asList(138, 180, 113, 136, 169, 118, 28, 191, 150, 195, 152, 31, 123, 16, 185, 17, 45,
		196, 11, 49, 94, 157, 129, 173, 154, 32, 12, 2, 117, 149, 194, 186, 59, 99, 142, 90, 170, 183, 57, 141,
		127, 58, 122, 189, 66, 177, 104);

	root = null;
	for (Integer d : ds) {
	    root = insert(root, d);
	}

	dfsPrint(root, 1);
	System.out.println("###############################################");
	root = insert(root, 188);
	dfsPrint(root, 1);
    }

    public static void main(String[] args) {
	System.out.println("########################### test 1 #########################");
	test1();

	System.out.println("########################### test 2 #########################");
	test2();

	System.out.println("########################### test 3 #########################");
	test3();
    }

}
