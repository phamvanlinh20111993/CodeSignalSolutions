package Hackerrank.GraphTheory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 *  *  *  *  *  * @param <T>  
 */
class AVLTree1<T> {

    private AVLNode<T> AVLNode = new AVLNode<T>();

    private final Comparator<T> comparator;

    public AVLTree1() {
	comparator = null;
    }

    public AVLTree1(T rootData) {
	comparator = null;
	this.AVLNode.setData(rootData);
    }

    public AVLTree1(T rootData, Comparator<T> comparator) {
	this.comparator = comparator;
	this.AVLNode.setData(rootData);
    }

    public AVLTree1(Comparator<T> comparator) {
	this.comparator = comparator;
    }

    /**
     *  *  * @param nodes  
     */

    public void addNodes(List<T> nodes) {
	for (T node : nodes) {
	    // this.addNode(node);
	    this.insertNode(node);
	}
    }

    /**
     *  *  * @param node  
     */

    public void insertNode(T node) {
	if (this.AVLNode.getData() == null) {
	    this.AVLNode.setData(node);
	    this.AVLNode.setCurrNodeHeight(0);
	    return;
	}
	// AVLNode<T> rootNode = findMatchParentNode(this.AVLNode, node);
	//
	// if (rootNode != null) {
	// int factor = this.compare(rootNode.getData(), node);
	// if (factor > 0) {
	// rootNode.setLeftAVLNodes(new AVLNode<T>(node));
	// } else {
	// rootNode.setRightAVLNodes(new AVLNode<T>(node));
	// }
	// }
	ins(this.AVLNode, node);
    }

    /**
     * 
     * @param tree
     * @param node
     */
    private void ins(AVLNode<T> tree, T node) {
	int factor = this.compare(tree.getData(), node);

	if (factor > 0) {
	    if (tree.hasLeftChild()) {
		ins(tree.getLeftAVLNodes(), node);
	    }
	} else {
	    if (tree.hasRightChild()) {
		ins(tree.getRightAVLNodes(), node);
	    }
	}

	if (factor > 0 && !tree.hasLeftChild()) {
	    tree.setLeftAVLNodes(new AVLNode<T>(node));
	} else if (factor < 0 && !tree.hasRightChild()) {
	    tree.setRightAVLNodes(new AVLNode<T>(node));
	}

	tree.setCurrNodeHeight(updateHeight(tree) + 1);
	tree.setBalanceFactor(updateBalanceFactor(tree));
    }

    /**
     *  * The idea:  *
     * <p>
     *  * To remove a node in the AVL tree and keep the lowest change on the tree,
     * we  * can focus this:  *
     * <p>
     *  * Search the largest node on the left side node of current delete node.  *
     * <p>
     *  * Search the smallest node on the right side node of current delete node.  *
     * <p>
     *  * After get the result, swap with the current delete node then detach that
     * node  * from the AVL tree.  *  * @param node  * the node need to detach from
     * the tree.  
     */

    public T deleteNode(T node) {
	List<AVLNode<T>> deletedNodes = this.findPreviousNode(null, this.AVLNode, node);
	AVLNode<T> deletedNode = deletedNodes.get(1);
	AVLNode<T> parentNode = deletedNodes.get(0);

	if (deletedNode == null) {
	    return null;
	}
	/**
	 * If deleted node was not has any child, remove itself and then return parent
	 * node
	 */
	if (deletedNode.hasNoneChild() && parentNode != null) {
	    if (parentNode.hasLeftChild() && this.compare(parentNode.getLeftAVLNodes().getData(), node) == 0) {
		parentNode.setLeftAVLNodes(null);
	    }
	    if (parentNode.hasRightChild() && this.compare(parentNode.getRightAVLNodes().getData(), node) == 0) {
		parentNode.setRightAVLNodes(null);
	    }
	    return node;
	}
	/**
	 * find the largest node on the left side
	 */
	if (deletedNode.hasLeftChild()) {
	    AVLNode<T> parentDelNode = deletedNode;
	    AVLNode<T> leftNode = deletedNode.getLeftAVLNodes();
	    while (leftNode.hasRightChild()) {
		parentDelNode = leftNode;
		leftNode = leftNode.getRightAVLNodes();
	    }
	    deletedNode.setData(leftNode.getData());

	    AVLNode<T> data = null;
	    if (leftNode.hasLeftChild()) {
		data = leftNode.getLeftAVLNodes();
	    }

	    if (parentDelNode.hasLeftChild()
		    && this.compare(parentDelNode.getLeftAVLNodes().getData(), leftNode.getData()) == 0) {
		parentDelNode.setLeftAVLNodes(data);
	    }

	    if (parentDelNode.hasRightChild()
		    && this.compare(parentDelNode.getRightAVLNodes().getData(), leftNode.getData()) == 0) {
		parentDelNode.setRightAVLNodes(data);
	    }

	    return node;
	}

	/**
	 * find the smallest node on the right side
	 */
	if (deletedNode.hasRightChild()) {
	    AVLNode<T> parentDelNode = deletedNode;
	    AVLNode<T> rightNode = deletedNode.getRightAVLNodes();
	    while (rightNode.hasLeftChild()) {
		parentDelNode = rightNode;
		rightNode = rightNode.getLeftAVLNodes();
	    }

	    deletedNode.setData(rightNode.getData());
	    AVLNode<T> data = null;
	    if (rightNode.hasRightChild()) {
		data = rightNode.getRightAVLNodes();
	    }

	    if (parentDelNode.hasLeftChild()
		    && this.compare(parentDelNode.getLeftAVLNodes().getData(), rightNode.getData()) == 0) {
		parentDelNode.setLeftAVLNodes(data);
	    }

	    if (parentDelNode.hasRightChild()
		    && this.compare(parentDelNode.getRightAVLNodes().getData(), rightNode.getData()) == 0) {
		parentDelNode.setRightAVLNodes(data);
	    }

	    return node;
	}

	return null;
    }

    /**
     *  * Update oldNode to newNode, we can imagine that update is delete and add
     * new  * =)))  *  * @param oldNode  * @param newNode  
     */

    public void updateNode(T oldNode, T newNode) {
	// delete old node
	deleteNode(oldNode);
	// add new node
	// addNode(newNode);
	insertNode(newNode);
    }

    /**
     *  *  *  * @param node  
     */

    public boolean search(T node) {
	return this.findNode(this.AVLNode, node) != null ? true : false;
    }

    /**
     *  *  * @return  
     */

    public List<T> sortedTree() {
	List<T> res = new ArrayList<>();
	inorderTraversal(this.AVLNode, res);
	return res;
    }

    /**
     *  *  * @return  
     */

    public List<T> sortedTreeAdvance() {

	return inorderTraversalInsideList(this.AVLNode);
    }

    public Comparator<T> comparator() {
	return comparator;
    }

    /**
     *  *  
     */

    public void print() {
	System.out.println("####################### DFS ######################");
	dfsPrint(this.AVLNode, 0);

	System.out.println("####################### BFS ######################");
	bfsPrint(this.AVLNode);
    }

    /**
     *  *  * @param node  
     */

    private void addNode(T node) {

	if (this.AVLNode.dataIsNull()) {
	    this.AVLNode.setData(node);
	    this.AVLNode.setCurrNodeHeight(0);
	    return;
	}

	AVLNode<T> traverseNode = this.AVLNode;
	boolean isChildLeft = true;

	while (true) {
	    int factor = this.compare(traverseNode.getData(), node);
	    if (factor > 0) {
		isChildLeft = true;
		if (!traverseNode.hasLeftChild())
		    break;
		traverseNode = traverseNode.getLeftAVLNodes();
	    } else if (factor == 0) {
		return;
	    } else {
		isChildLeft = false;
		if (!traverseNode.hasRightChild())
		    break;
		traverseNode = traverseNode.getRightAVLNodes();
	    }
	}

	if (isChildLeft) {
	    traverseNode.setLeftAVLNodes(new AVLNode<T>(node));
	} else {
	    traverseNode.setRightAVLNodes(new AVLNode<T>(node));
	}
    }

    /**
     *  *  * @param rootNode  * @param node  * @return  
     */

    private AVLNode<T> findMatchParentNode(AVLNode<T> rootNode, T node) {

	int compareFactor = this.compare(rootNode.getData(), node);
	if (compareFactor > 0) {
	    if (rootNode.hasLeftChild()) {
		return findMatchParentNode(rootNode.getLeftAVLNodes(), node);
	    }

	    rootNode.setCurrNodeHeight(updateHeight(rootNode) + 1);

	    return rootNode;
	} else if (compareFactor < 0) {
	    if (rootNode.hasRightChild()) {
		return findMatchParentNode(rootNode.getRightAVLNodes(), node);
	    }

	    rootNode.setCurrNodeHeight(updateHeight(rootNode) + 1);

	    return rootNode;
	} else {
	    return null;
	}

    }

    /**
     * 
     * @param rootNode
     * @return
     */
    private Integer updateHeight(AVLNode<T> rootNode) {
	int leftHeight = 0;
	if (rootNode.hasLeftChild()) {
	    leftHeight = rootNode.getLeftAVLNodes().getCurrNodeHeight();
	}

	int rightHeight = 0;
	if (rootNode.hasRightChild()) {
	    rightHeight = rootNode.getRightAVLNodes().getCurrNodeHeight();
	}

	return Math.max(leftHeight, rightHeight);
    }

    /**
     * 
     * @param rootNode
     * @return
     */
    private Integer updateBalanceFactor(AVLNode<T> rootNode) {
	int leftHeight = 0;
	if (rootNode.hasLeftChild()) {
	    leftHeight = rootNode.getLeftAVLNodes().getCurrNodeHeight();
	}

	int rightHeight = 0;
	if (rootNode.hasRightChild()) {
	    rightHeight = rootNode.getRightAVLNodes().getCurrNodeHeight();
	}

	return leftHeight - rightHeight;
    }

    /**
     *  *  * @param rootNode  * @param searchData  * @return  
     */

    private AVLNode<T> findNode(AVLNode<T> rootNode, T searchData) {
	if (rootNode == null) {
	    return null;
	}
	int compareFactor = this.compare(rootNode.getData(), searchData);
	if (compareFactor == 0) {
	    return rootNode;
	} else if (compareFactor < 0) {
	    return findNode(rootNode.getRightAVLNodes(), searchData);
	} else {
	    return findNode(rootNode.getLeftAVLNodes(), searchData);
	}
    }

    /**
     *  * Find the node match with searchData  *  * @param parentNode   * @param
     * rootNode  * @param searchData  * @return a list of two elements with the
     * first one is the parent node, the  * second one is the currentNode  
     */

    private List<AVLNode<T>> findPreviousNode(AVLNode<T> parentNode, AVLNode<T> rootNode, T searchData) {
	if (rootNode == null) {
	    return null;
	}
	int compareFactor = this.compare(rootNode.getData(), searchData);
	if (compareFactor == 0) {
	    return Arrays.asList(parentNode, rootNode);
	} else if (compareFactor < 0) {
	    return findPreviousNode(rootNode, rootNode.getRightAVLNodes(), searchData);
	} else {
	    return findPreviousNode(rootNode, rootNode.getLeftAVLNodes(), searchData);
	}
    }

    /**
     *  *  * @param node  * @param level  
     */

    private void dfsPrint(AVLNode<T> node, int level) {
	if (node == null)
	    return;
	if (level == 0) {
	    System.out.println(
		    "---ROOT: " + node.getData() + "#" + node.getCurrNodeHeight() + "#" + node.getBalanceFactor());
	} else {
	    for (int ind = 0; ind <= level; ind++) {
		System.out.print("----");
	    }
	    System.out
		    .print("NODE: " + node.getData() + "#" + node.getCurrNodeHeight() + "#" + node.getBalanceFactor());
	    System.out.println();
	}
	dfsPrint(node.getLeftAVLNodes(), level + 1);
	dfsPrint(node.getRightAVLNodes(), level + 1);
    }

    /**
     *  *  * @param node  * @param level  
     */

    private void bfsPrint(AVLNode<T> rootNode) {

	Queue<Tuples<AVLNode<T>, Integer>> queue = new LinkedList<Tuples<AVLNode<T>, Integer>>();
	queue.add(new Tuples<>(rootNode, 0));
	int level = 0;
	while (queue.size() > 0) {
	    Tuples<AVLNode<T>, Integer> tupleNode = queue.poll();
	    for (int ind = 0; ind < level; ind++) {
		System.out.print("..");
	    }
	    if (level == tupleNode.getValue()) {
		System.out.print(tupleNode.getKey().getData());
	    } else {
		System.out.println();
		System.out.print(tupleNode.getKey().getData());
		level = tupleNode.getValue();
	    }

	    if (tupleNode.getKey().hasLeftChild()) {
		queue.add(new Tuples<>(tupleNode.getKey().getLeftAVLNodes(), tupleNode.getValue() + 1));
	    }

	    if (tupleNode.getKey().hasRightChild()) {
		queue.add(new Tuples<>(tupleNode.getKey().getRightAVLNodes(), tupleNode.getValue() + 1));
	    }
	}

	System.out.println();
    }

    /**
     *  *  * @param node  * @param res  
     */

    private void inorderTraversal(AVLNode<T> node, List<T> res) {
	if (node.hasLeftChild()) {
	    inorderTraversal(node.getLeftAVLNodes(), res);
	}
	res.add(node.getData());
	if (node.hasRightChild()) {
	    inorderTraversal(node.getRightAVLNodes(), res);
	}
    }

    /**
     *  * Reference:  *
     * https://stackoverflow.com/questions/49063499/inorder-traversal-of-tree-in-python-returning-a-list
     *  *  * @param node  
     */

    private List<T> inorderTraversalInsideList(AVLNode<T> node) {
	if (node == null)
	    return new ArrayList<T>();

	List<T> leftNodes = inorderTraversalInsideList(node.getLeftAVLNodes());
	List<T> rightNodes = inorderTraversalInsideList(node.getRightAVLNodes());
	leftNodes.add(node.getData());
	leftNodes.addAll(rightNodes);
	return leftNodes;
    }

    /**
     *  * Compares two keys using the correct comparison method for this TreeMap.  
     */

    @SuppressWarnings("unchecked")
    private final int compare(T k1, T k2) {
	return comparator == null ? ((Comparable<T>) k1).compareTo(k2) : comparator.compare(k1, k2);
    }

}

public class SelfBalanceTree_Company {

    public static void main(String[] args) {
	AVLTree1<Integer> avlTree = new AVLTree1<Integer>();
	avlTree.insertNode(30);
	avlTree.insertNode(21);
	avlTree.insertNode(25);
	avlTree.insertNode(12);
	avlTree.insertNode(244);
	avlTree.insertNode(144);
	avlTree.insertNode(2466);
	avlTree.insertNode(40);
	avlTree.insertNode(2444);
	avlTree.insertNode(39);
	avlTree.insertNode(35);
	avlTree.insertNode(31);

	avlTree.addNodes(Arrays.asList(2466, 1, 2, 3, 4, 5, 6, 30));

	avlTree.print();

	System.out.println("################### Get sorted data ");
	avlTree.sortedTree().forEach(v -> System.out.print(v + " "));
	System.out.println("\n################### Get sorted data using advanced methodology");
	avlTree.sortedTreeAdvance().forEach(v -> System.out.print(v + " "));

	// System.out.println("\n################### Remove node ");
	// PASS
	// avlTree.deleteNode(30);
	// PASS
	// avlTree.deleteNode(6);
	// PASS
	// avlTree.deleteNode(3);
	// PASS
	// avlTree.deleteNode(244);
	// PASS
	// avlTree.deleteNode(21);
	// PASS
	// avlTree.deleteNode(12);
	// PASS
	// avlTree.deleteNode(1);

	// avlTree.print();
	// avlTree.sortedTree().forEach(v -> System.out.print(v + " "));
    }

}
