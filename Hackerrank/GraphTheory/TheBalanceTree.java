package Hackerrank.GraphTheory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

/**
 *  *  * @author Admin  *  * @param <K>  * @param <V>  
 */
class Tuples<K, V> {
    private K key;
    private V value;

    public Tuples(K key, V value) {
	super();
	this.key = key;
	this.value = value;
    }

    public K getKey() {
	return key;
    }

    public void setKey(K key) {
	this.key = key;
    }

    public V getValue() {
	return value;
    }

    public void setValue(V value) {
	this.value = value;
    }

    @Override
    public String toString() {
	return "KeyPair [key=" + key + ", value=" + value + "]";
    }

    @Override
    public int hashCode() {
	return Objects.hash(key, value);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Tuples other = (Tuples) obj;
	return Objects.equals(key, other.key) && Objects.equals(value, other.value);
    }

}

/**
 *  *  *  * @param <T>  
 */
class AVLNode<T> {

    private T data;

    private AVLNode<T> leftAVLNodes;

    private AVLNode<T> rightAVLNodes;

    private int currNodeHeight = 0;

    public AVLNode() {

    }

    public AVLNode(T data) {
	this.data = data;
    }

    public T getData() {
	return data;
    }

    public void setData(T data) {
	this.data = data;
    }

    public AVLNode<T> getLeftAVLNodes() {
	return leftAVLNodes;
    }

    public void setLeftAVLNodes(AVLNode<T> leftAVLNodes) {
	this.leftAVLNodes = leftAVLNodes;
    }

    public AVLNode<T> getRightAVLNodes() {
	return rightAVLNodes;
    }

    public void setRightAVLNodes(AVLNode<T> rightAVLNodes) {
	this.rightAVLNodes = rightAVLNodes;
    }

    public int getCurrNodeHeight() {
	return currNodeHeight;
    }

    public void setCurrNodeHeight(int currNodeHeight) {
	this.currNodeHeight = currNodeHeight;
    }

}

/**
 *  *  *  * @param <T>  
 */
class AVLTree<T> {

    private AVLNode<T> AVLNode = new AVLNode<T>();

    private final Comparator<T> comparator;

    public AVLTree() {
	comparator = null;
    }

    public AVLTree(T rootData) {
	comparator = null;
	this.AVLNode.setData(rootData);
    }

    public AVLTree(T rootData, Comparator<T> comparator) {
	this.comparator = comparator;
	this.AVLNode.setData(rootData);
    }

    public AVLTree(Comparator<T> comparator) {
	this.comparator = comparator;
    }

    /**
     * 
     * @param nodes
     */
    public void addNodes(List<T> nodes) {
	for (T node : nodes) {
	    this.addNode(node);
	}
    }

    /**
     * 
     * @param node
     */
    public void insertNode(T node) {
	if (this.AVLNode.getData() == null) {
	    this.AVLNode.setData(node);
	    this.AVLNode.setCurrNodeHeight(0);
	    return;
	}
	AVLNode<T> rootNode = findMatchParentNode(this.AVLNode, node);

	if (rootNode != null) {
	    int factor = this.compare(rootNode.getData(), node);
	    if (factor > 0) {
		rootNode.setLeftAVLNodes(new AVLNode<T>(node));
	    } else {
		rootNode.setRightAVLNodes(new AVLNode<T>(node));
	    }
	}
    }

    /**
     * The idea:
     * <p>
     * To remove a node in the AVL tree and keep the lowest change on the tree, we
     * can focus this:
     * <p>
     * Search the largest node on the left side node of current delete node.
     * <p>
     * Search the smallest node on the right side node of current delete node.
     * <p>
     * After get the result, swap with the current delete node then detach that node
     * from the AVL tree.
     * 
     * @param node
     *            the node need to detach from the tree.
     */
    public T deleteNode(T node) {
	List<AVLNode<T>> deletedNodes = this.findPreviousNode(null, this.AVLNode, node);
	AVLNode<T> deletedNode = deletedNodes.get(1);
	AVLNode<T> parentNode = deletedNodes.get(0);

	if (deletedNode == null) {
	    return null;
	}

	if (!hasAllChild(deletedNode) && parentNode != null) {
	    if (this.compare(parentNode.getLeftAVLNodes().getData(), node) == 0) {
		parentNode.setLeftAVLNodes(null);
	    }

	    if (this.compare(parentNode.getRightAVLNodes().getData(), node) == 0) {
		parentNode.setRightAVLNodes(null);
	    }

	    return node;

	}

	// TODO

	return null;

    }

    /**
     * 
     * @return
     */
    private AVLNode<T> findLargestLeftNode() {
	AVLNode<T> root = this.AVLNode;

	if (root != null && root.getLeftAVLNodes() != null) {
	    root = root.getLeftAVLNodes();
	    while (root.getRightAVLNodes() != null) {
		root = root.getRightAVLNodes();
	    }

	    return root;
	}

	return null;

    }

    /**
     * 
     * @return
     */
    private AVLNode<T> findSmallestRightNode() {
	AVLNode<T> root = this.AVLNode;
	if (root != null && root.getRightAVLNodes() != null) {
	    root = root.getRightAVLNodes();
	    while (root.getLeftAVLNodes() != null) {
		root = root.getLeftAVLNodes();
	    }

	    return root;
	}

	return null;
    }

    /**
     * Update oldNode to newNode, we can imagine that update is delete and add new
     * =)))
     *
     * @param oldNode
     * @param newNode
     */
    public void updateNode(T oldNode, T newNode) {
	// delete old node
	deleteNode(oldNode);
	// add new node
	addNode(newNode);
    }

    /**
     *
     * 
     * @param node
     */
    public boolean search(T node) {
	return this.findNode(this.AVLNode, node) != null ? true : false;
    }

    /**
     * 
     * @return
     */
    public List<T> sortedTree() {
	List<T> res = new ArrayList<>();
	inorderTraversal(this.AVLNode, res);
	return res;
    }

    /**
     * 
     * @return
     */
    public List<T> sortedTreeAdvance() {

	return inorderTraversalInsideList(this.AVLNode);
    }

    public Comparator<T> comparator() {
	return comparator;
    }

    /**
     * 
     */
    public void print() {
	System.out.println("####################### DFS ######################");
	dfsPrint(this.AVLNode, 0);

	System.out.println("####################### BFS ######################");
	bfsPrint(this.AVLNode);
    }

    /**
     * 
     * @param node
     */
    private void addNode(T node) {

	if (this.AVLNode.getData() == null) {
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
		if (traverseNode.getLeftAVLNodes() == null)
		    break;
		traverseNode = traverseNode.getLeftAVLNodes();
	    } else if (factor == 0) {
		return;
	    } else {
		isChildLeft = false;
		if (traverseNode.getRightAVLNodes() == null)
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
     * 
     * @param rootNode
     * @param node
     * @return
     */
    private AVLNode<T> findMatchParentNode(AVLNode<T> rootNode, T node) {
	int compareFactor = this.compare(rootNode.getData(), node);
	if (compareFactor > 0) {
	    if (rootNode.getLeftAVLNodes() != null) {
		return findMatchParentNode(rootNode.getLeftAVLNodes(), node);
	    }
	    return rootNode;
	} else if (compareFactor < 0) {
	    if (rootNode.getRightAVLNodes() != null) {
		return findMatchParentNode(rootNode.getRightAVLNodes(), node);
	    }
	    return rootNode;
	} else {
	    return null;
	}

    }

    /**
     * 
     * @param rootNode
     * @param searchData
     * @return
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
     * Find the node match with searchData
     *
     * @param parentNode
     * @param rootNode
     * @param searchData
     * @return a list of two elements with the first one is the parent node, the
     *         second one is the currentNode
     */
    private List<AVLNode<T>> findPreviousNode(AVLNode<T> parentNode, AVLNode<T> rootNode, T searchData) {
	if (rootNode == null) {
	    return null;
	}
	int compareFactor = this.compare(rootNode.getData(), searchData);
	if (compareFactor == 0) {

	    return Arrays.asList(parentNode, rootNode);
	} else if (compareFactor < 0) {
	    return findPreviousNode(parentNode, rootNode.getRightAVLNodes(), searchData);
	} else {
	    return findPreviousNode(parentNode, rootNode.getLeftAVLNodes(), searchData);
	}
    }

    /**
     * 
     * @param node
     * @param level
     */
    private void dfsPrint(AVLNode<T> node, int level) {
	if (node == null)
	    return;
	if (level == 0) {
	    System.out.println("---ROOT: " + node.getData());
	} else {
	    for (int ind = 0; ind <= level; ind++) {
		System.out.print("----");
	    }
	    System.out.print("NODE: " + node.getData());
	    System.out.println();
	}
	dfsPrint(node.getLeftAVLNodes(), level + 1);
	dfsPrint(node.getRightAVLNodes(), level + 1);
    }

    /**
     * 
     * @param node
     * @param level
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

	    if (tupleNode.getKey().getLeftAVLNodes() != null) {
		queue.add(new Tuples<>(tupleNode.getKey().getLeftAVLNodes(), tupleNode.getValue() + 1));
	    }

	    if (tupleNode.getKey().getRightAVLNodes() != null) {
		queue.add(new Tuples<>(tupleNode.getKey().getRightAVLNodes(), tupleNode.getValue() + 1));
	    }
	}

	System.out.println();
    }

    /**
     * 
     * @param node
     * @param res
     */
    private void inorderTraversal(AVLNode<T> node, List<T> res) {
	if (node.getLeftAVLNodes() != null) {
	    inorderTraversal(node.getLeftAVLNodes(), res);
	}
	res.add(node.getData());
	if (node.getRightAVLNodes() != null) {
	    inorderTraversal(node.getRightAVLNodes(), res);
	}
    }

    /**
     * Reference:
     * https://stackoverflow.com/questions/49063499/inorder-traversal-of-tree-in-python-returning-a-list
     * 
     * @param node
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
     * Compares two keys using the correct comparison method for this TreeMap.
     */
    @SuppressWarnings("unchecked")
    private final int compare(T k1, T k2) {
	return comparator == null ? ((Comparable<T>) k1).compareTo(k2) : comparator.compare(k1, k2);
    }

    /**
     * 
     * @param node
     * @return
     */
    private boolean hasAllChild(AVLNode<T> node) {
	return hasLeftChild(node) && hasRightChild(node);
    }

    /**
     * 
     * @param node
     * @return
     */
    private boolean hasLeftChild(AVLNode<T> node) {
	return node.getLeftAVLNodes() != null;
    }

    /**
     * 
     * @param node
     * @return
     */
    private boolean hasRightChild(AVLNode<T> node) {
	return node.getRightAVLNodes() != null;
    }

}

public class TheBalanceTree {

    public static void main(String[] args) {
	AVLTree<Integer> avlTree = new AVLTree<Integer>();
	avlTree.insertNode(30);
	avlTree.insertNode(21);
	avlTree.insertNode(25);
	avlTree.insertNode(12);
	avlTree.insertNode(244);
	avlTree.insertNode(144);
	avlTree.insertNode(2466);
	avlTree.insertNode(40);
	avlTree.insertNode(2444);

	avlTree.addNodes(Arrays.asList(2466, 1, 2, 3, 4, 5, 6, 30));

	avlTree.print();

	System.out.println("################### Get sorted data ");
	avlTree.sortedTree().forEach(v -> System.out.print(v + " "));
	System.out.println("\n################### Get sorted data using advanced methodology");
	avlTree.sortedTreeAdvance().forEach(v -> System.out.print(v + " "));
    }

}
