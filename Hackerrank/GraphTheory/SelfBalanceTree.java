package Hackerrank.GraphTheory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

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

    /**
     *  *  * @param node  * @return  
     */

    public boolean hasAllChild() {
	return this.hasLeftChild() && this.hasRightChild();
    }

    /**
     *  *  * @return  
     */

    public boolean hasNoneChild() {
	return !this.hasLeftChild() && !this.hasRightChild();
    }

    /**
     *  *  * @param node  * @return  
     */

    public boolean hasLeftChild() {
	return this.getLeftAVLNodes() != null;
    }

    /**
     *  *  * @param node  * @return  
     */

    public boolean hasRightChild() {
	return this.getRightAVLNodes() != null;
    }

    /**
     *  *  * @return  
     */

    public boolean dataIsNull() {
	return this.getData() == null;
    }
}

/**
 * 
 * @author PhamLinh
 * @link https://www.hackerrank.com/challenges/self-balancing-tree/problem?isFullScreen=false
 * @param <T>
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
	    // this.addNode(node);
	    this.insertNode(node);
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
	/*
	 * AVLNode<T> rootNode = findMatchParentNode(this.AVLNode, node);
	 * 
	 * if (rootNode != null) { int factor = this.compare(rootNode.getData(), node);
	 * if (factor > 0) { rootNode.setLeftAVLNodes(new AVLNode<T>(node)); } else {
	 * rootNode.setRightAVLNodes(new AVLNode<T>(node)); } }
	 */
	insert(this.AVLNode, node);
    }

    /**
     * Insert node to the binary tree and operate balance tree
     * <p>
     * *) Complexity:
     * <p>
     * +) insert itself using recursion: 1*n*log(n)
     * <p>
     * +) reBalanceTree() function: O(1)
     * <p>
     * +) updateTreeHeight() function: 1*n*log(n)
     * <p>
     * => remove updateTreeHeight() function only take n*log(n) complexity
     * 
     * @param tree
     * @param node
     */
    private void insert(AVLNode<T> tree, T node) {

	int factor = this.compare(tree.getData(), node);

	if (factor > 0 && tree.hasLeftChild()) {
	    insert(tree.getLeftAVLNodes(), node);

	} else if (factor < 0 && tree.hasRightChild()) {
	    insert(tree.getRightAVLNodes(), node);
	}

	if (factor != 0) {
	    AVLNode<T> updateData = new AVLNode<T>(node);
	    updateData.setCurrNodeHeight(0);

	    if (factor > 0 && !tree.hasLeftChild()) {
		tree.setLeftAVLNodes(updateData);
	    } else if (factor < 0 && !tree.hasRightChild()) {
		tree.setRightAVLNodes(updateData);
	    }
	}

	tree.setCurrNodeHeight(updateHeight(tree));

	int balanceFactor = getBalanceFactor(tree);

	if (balanceFactor > 1 || balanceFactor < -1) {
	    reBalanceTree(tree, node);
	    // updateTreeHeight(tree, node);
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
	// the leaf node has 0 height
	int h = rootNode.hasNoneChild() ? 0 : 1;
	return Math.max(leftHeight, rightHeight) + h;
    }

    /**
     * 
     * @param rootNode
     * @return
     */
    private Integer getBalanceFactor(AVLNode<T> rootNode) {

	if (rootNode == null) {
	    return 0;
	}

	int leftHeight = 0;
	if (rootNode.hasLeftChild()) {
	    leftHeight = rootNode.getLeftAVLNodes().getCurrNodeHeight() + 1;
	}

	int rightHeight = 0;
	if (rootNode.hasRightChild()) {
	    rightHeight = rootNode.getRightAVLNodes().getCurrNodeHeight() + 1;
	}

	return rightHeight - leftHeight;
    }

    /**
     * re-balance the tree
     * 
     * @param rootAffectNode
     * @param insertedNode
     */
    private void reBalanceTree(AVLNode<T> rootAffectNode, T insertedNode) {

	int factorAffectRoot = this.compare(insertedNode, rootAffectNode.getData());

	T compareData = factorAffectRoot < 0 ? rootAffectNode.getLeftAVLNodes().getData()
		: rootAffectNode.getRightAVLNodes().getData();
	int factorAffectChildRoot = this.compare(insertedNode, compareData);

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
     * re-balance the tree when delete node, this ideal, which calculate the
     * re-balance case (RR, RL, LL, LR) base on only rootAffectNode - poe's
     * suggestion({@link} https://poe.com/). You can check the case insert node when
     * re-balance case base on both rootAffectNode and insertedNode data
     * 
     * @param rootAffectNode
     *
     */
    private void reBalanceTreeWhenDelete(AVLNode<T> rootAffectNode) {

	int factorAffectRoot = this.getBalanceFactor(rootAffectNode);

	if (factorAffectRoot > 1 && this.getBalanceFactor(rootAffectNode.getRightAVLNodes()) >= 0) {
	    RR(rootAffectNode);
	} else if (factorAffectRoot < -1 && this.getBalanceFactor(rootAffectNode.getRightAVLNodes()) >= 0) {
	    LR(rootAffectNode);
	} else if (factorAffectRoot > 1 && this.getBalanceFactor(rootAffectNode.getLeftAVLNodes()) >= 0) {
	    RL(rootAffectNode);
	} else if (factorAffectRoot < -1 && this.getBalanceFactor(rootAffectNode.getLeftAVLNodes()) >= 0) {
	    LL(rootAffectNode);
	}
    }

    /**
     * 
     * @param rootAffectNode
     *            Z node, check the image
     */
    private void LL(AVLNode<T> rootAffectNode) {
	AVLNode<T> childLeftRootAffectNode = rootAffectNode.getLeftAVLNodes();
	AVLNode<T> childRightRootAffectNode = rootAffectNode.getRightAVLNodes();

	T data = rootAffectNode.getData();
	rootAffectNode.setData(childLeftRootAffectNode.getData());

	AVLNode<T> newRightNode = new AVLNode<T>(data);
	newRightNode.setRightAVLNodes(childRightRootAffectNode);
	newRightNode.setLeftAVLNodes(childLeftRootAffectNode.getRightAVLNodes());

	rootAffectNode.setRightAVLNodes(newRightNode);
	rootAffectNode.setLeftAVLNodes(childLeftRootAffectNode.getLeftAVLNodes());

	// we need to re update current height of relevant nodes
	newRightNode.setCurrNodeHeight(updateHeight(newRightNode));
	rootAffectNode.setCurrNodeHeight(updateHeight(rootAffectNode));
    }

    /**
     * 
     * @param Z
     *            node, check the image
     *
     */
    private void RL(AVLNode<T> Z) {
	AVLNode<T> Y = Z.getRightAVLNodes();
	AVLNode<T> X = Y.getLeftAVLNodes();
	AVLNode<T> T3 = X.getRightAVLNodes();

	Y.setLeftAVLNodes(T3);
	X.setRightAVLNodes(Y);
	Z.setRightAVLNodes(X);

	// we need to re update current height of relevant nodes
	Z.setCurrNodeHeight(updateHeight(Z));
	Y.setCurrNodeHeight(updateHeight(Y));
	X.setCurrNodeHeight(updateHeight(X));

	this.RR(Z);
    }

    /**
     * 
     * @param Z
     *            node, check the image
     * @param insertedNode
     */
    private void LR(AVLNode<T> Z) {

	AVLNode<T> Y = Z.getLeftAVLNodes();
	AVLNode<T> X = Y.getRightAVLNodes();
	AVLNode<T> T2 = X.getLeftAVLNodes();

	Y.setRightAVLNodes(T2);
	X.setLeftAVLNodes(Y);
	Z.setLeftAVLNodes(X);

	// we need to re update current height of relevant nodes
	Z.setCurrNodeHeight(updateHeight(Z));
	Y.setCurrNodeHeight(updateHeight(Y));
	X.setCurrNodeHeight(updateHeight(X));

	this.LL(Z);
    }

    /**
     * Right Right case
     * 
     * @param rootAffectNode
     */
    private void RR(AVLNode<T> rootAffectNode) {
	AVLNode<T> childLeftRootAffectNode = rootAffectNode.getLeftAVLNodes();
	AVLNode<T> childRightRootAffectNode = rootAffectNode.getRightAVLNodes();

	T data = rootAffectNode.getData();
	rootAffectNode.setData(childRightRootAffectNode.getData());

	rootAffectNode.setRightAVLNodes(childRightRootAffectNode.getRightAVLNodes());

	AVLNode<T> newLeftNode = new AVLNode<T>(data);
	newLeftNode.setLeftAVLNodes(childLeftRootAffectNode);
	newLeftNode.setRightAVLNodes(childRightRootAffectNode.getLeftAVLNodes());

	rootAffectNode.setLeftAVLNodes(newLeftNode);

	// we need to re update current height of relevant nodes
	newLeftNode.setCurrNodeHeight(updateHeight(newLeftNode));
	rootAffectNode.setCurrNodeHeight(updateHeight(rootAffectNode));

    }

    /**
     * 
     * @param nodes
     * @return
     */
    public List<T> deleteNodes(List<T> nodes) {
	List<T> res = new ArrayList<>();
	for (T node : nodes) {
	    System.out.println("delete node " + node);
	    T delNode = deleteNode(node);
	    this.dfsPrint(this.AVLNode, 1);
	    if (delNode != null) {
		res.add(delNode);
	    }
	}

	return res;
    }

    // TODO we can improve the delete by using recursion then reduce the complexity
    // from O(2*n*log(n)) to O(n*log(n))
    // Although O(2*n*log(n)) = O(n*log(n)) when n come to infinite
    /**
     * delete node then re-balance the tree
     * 
     * The complexity: O(2*n*log(n))
     * <p>
     * - Delete node: O(n*log(n))
     * <p>
     * - re-balance node: O(n*log(n))
     * <p>
     * Note: we can improve the deleteNode() function using recursion
     * 
     * @param node
     * @return
     */
    public T deleteNode(T node) {
	return deleletNodeFrom(this.AVLNode, node);
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
     * @param root
     *            the segment of tree
     * @param node
     *            the node need to detach from the tree.
     */
    private T deleletNodeFrom(AVLNode<T> root, T node) {
	List<AVLNode<T>> deletedNodes = this.findPreviousNode(null, root, node);
	if (deletedNodes == null || deletedNodes.get(1) == null) {
	    return null;
	}
	AVLNode<T> deletedNode = deletedNodes.get(1);
	AVLNode<T> parentNode = deletedNodes.get(0);

	/**
	 * If deleted node did not has any child, remove itself and then return parent
	 * node
	 */
	if (deletedNode.hasNoneChild() && parentNode != null) {
	    if (parentNode.hasLeftChild() && this.compare(parentNode.getLeftAVLNodes().getData(), node) == 0) {
		parentNode.setLeftAVLNodes(null);
	    } else if (parentNode.hasRightChild() && this.compare(parentNode.getRightAVLNodes().getData(), node) == 0) {
		parentNode.setRightAVLNodes(null);
	    }
	    rebalanceDeleteNode(this.AVLNode, parentNode.getData());
	    return node;
	}
	/**
	 * find the largest node data on the left side
	 */
	if (deletedNode.hasLeftChild()) {
	    AVLNode<T> parentDelNode = deletedNode;
	    AVLNode<T> leftNode = deletedNode.getLeftAVLNodes();
	    while (leftNode.hasRightChild()) {
		parentDelNode = leftNode;
		leftNode = leftNode.getRightAVLNodes();
	    }
	    deletedNode.setData(leftNode.getData());

	    AVLNode<T> data = leftNode.hasLeftChild() ? leftNode.getLeftAVLNodes() : null;

	    if (parentDelNode.hasLeftChild()
		    && this.compare(parentDelNode.getLeftAVLNodes().getData(), leftNode.getData()) == 0) {
		parentDelNode.setLeftAVLNodes(data);
	    }

	    if (parentDelNode.hasRightChild()
		    && this.compare(parentDelNode.getRightAVLNodes().getData(), leftNode.getData()) == 0) {
		parentDelNode.setRightAVLNodes(data);
	    }

	    rebalanceDeleteNode(this.AVLNode, parentDelNode.getData());
	    return node;
	}

	/**
	 * find the smallest node data on the right side
	 */
	if (deletedNode.hasRightChild()) {
	    AVLNode<T> parentDelNode = deletedNode;
	    AVLNode<T> rightNode = deletedNode.getRightAVLNodes();

	    // smallest is on left side
	    while (rightNode.hasLeftChild()) {
		parentDelNode = rightNode;
		rightNode = rightNode.getLeftAVLNodes();
	    }
	    // replace the deleted node by the smallest data on left side
	    deletedNode.setData(rightNode.getData());
	    AVLNode<T> data = rightNode.hasRightChild() ? rightNode.getRightAVLNodes() : null;

	    if (parentDelNode.hasLeftChild()
		    && this.compare(parentDelNode.getLeftAVLNodes().getData(), rightNode.getData()) == 0) {
		parentDelNode.setLeftAVLNodes(data);
	    }

	    if (parentDelNode.hasRightChild()
		    && this.compare(parentDelNode.getRightAVLNodes().getData(), rightNode.getData()) == 0) {
		parentDelNode.setRightAVLNodes(data);
	    }

	    rebalanceDeleteNode(this.AVLNode, parentDelNode.getData());
	    return node;
	}

	return null;
    }

    /**
     * 
     * @param rootNode
     * @param searchData
     */
    private void rebalanceDeleteNode(AVLNode<T> rootNode, T searchData) {
	if (rootNode == null) {
	    return;
	}

	int compareFactor = this.compare(rootNode.getData(), searchData);
	if (compareFactor < 0) {
	    rebalanceDeleteNode(rootNode.getRightAVLNodes(), searchData);
	} else if (compareFactor > 0) {
	    rebalanceDeleteNode(rootNode.getLeftAVLNodes(), searchData);
	}

	rootNode.setCurrNodeHeight(updateHeight(rootNode));

	int balanceFactor = getBalanceFactor(rootNode);

	if (balanceFactor > 1 || balanceFactor < -1) {
	    reBalanceTreeWhenDelete(rootNode);
	}
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
	insertNode(newNode);
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
	System.out.println("####################### Depth First Search ######################");
	dfsPrint(this.AVLNode, 0);

	System.out.println("####################### Breadth First Search ######################");
	bfsPrint(this.AVLNode);
    }

    /**
     * 
     * @param node
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
     * 
     * @param rootNode
     * @param node
     * @return
     */
    private AVLNode<T> findMatchParentNode(AVLNode<T> rootNode, T node) {
	int compareFactor = this.compare(rootNode.getData(), node);
	if (compareFactor > 0) {
	    if (rootNode.hasLeftChild()) {
		return findMatchParentNode(rootNode.getLeftAVLNodes(), node);
	    }
	    return rootNode;
	} else if (compareFactor < 0) {
	    if (rootNode.hasRightChild()) {
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
	    return findPreviousNode(rootNode, rootNode.getRightAVLNodes(), searchData);
	} else {
	    return findPreviousNode(rootNode, rootNode.getLeftAVLNodes(), searchData);
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
	    System.out.println("---ROOT: V:" + node.getData() + "#H:" + node.getCurrNodeHeight() + "#BF:"
		    + getBalanceFactor(node));
	} else {
	    for (int ind = 0; ind <= level; ind++) {
		System.out.print("----");
	    }
	    System.out.print(
		    "NODE: V:" + node.getData() + "#H:" + node.getCurrNodeHeight() + "#BF:" + getBalanceFactor(node));
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
     * 
     * @param node
     * @param res
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
     * Compares its two arguments for order. Returns a negative integer, zero, or a
     * positive integer as the first argument is less than, equal to, or greater
     * than the second.
     * 
     * @k1 > @k2 then return 1
     * @k1 == @k2 then return 0
     * @k1 < @k2 then return -1
     */
    @SuppressWarnings("unchecked")
    private final int compare(T k1, T k2) {
	return comparator == null ? ((Comparable<T>) k1).compareTo(k2) : comparator.compare(k1, k2);
    }

}

public class SelfBalanceTree {

    public static void test1() {
	AVLTree<Integer> avlTree = new AVLTree<Integer>();
	avlTree.insertNode(30);
	avlTree.insertNode(21);
	avlTree.insertNode(25);
	avlTree.insertNode(12);
	avlTree.insertNode(244);
	avlTree.print();

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

	System.out.println("\n################### Remove node ");
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
	avlTree.deleteNode(1);

	avlTree.print();
	avlTree.sortedTree().forEach(v -> System.out.print(v + " "));
    }

    public static void test2() {
	AVLTree<Integer> avlTree = new AVLTree<Integer>();
	avlTree.insertNode(60);
	avlTree.insertNode(40);
	avlTree.insertNode(80);
	avlTree.insertNode(38);
	avlTree.insertNode(50);
	avlTree.insertNode(70);
	avlTree.insertNode(90);

	avlTree.insertNode(45);
	avlTree.insertNode(55);
	avlTree.insertNode(20);
	// LR case root 60
	// avlTree.insertNode(59);

	// LL case root 60
	// avlTree.insertNode(39);
	// avlTree.insertNode(10);

	// RL case root 60
	avlTree.insertNode(65);
	avlTree.insertNode(73);
	avlTree.insertNode(85);
	avlTree.insertNode(100);
	avlTree.insertNode(64);
	avlTree.insertNode(67);
	avlTree.insertNode(72);
	avlTree.insertNode(76);
	avlTree.insertNode(110);
	avlTree.insertNode(61);

	avlTree.print();
    }

    public static void test3() {
	AVLTree<Integer> avlTree = new AVLTree<Integer>();
	avlTree.insertNode(1);
	avlTree.insertNode(2);
	avlTree.insertNode(3);
	avlTree.print();
	avlTree.insertNode(4);
	avlTree.print();
	avlTree.insertNode(5);
	avlTree.print();
	avlTree.insertNode(6);
	avlTree.print();
	// avlTree.insertNode(7);
	// avlTree.insertNode(8);
	// avlTree.insertNode(9);
	// avlTree.insertNode(10);
	// avlTree.print();
	// avlTree.insertNode(11);
	// avlTree.insertNode(12);
	// avlTree.insertNode(13);
	// avlTree.insertNode(14);
	// avlTree.insertNode(15);
	// avlTree.print();

    }

    public static void test4() {
	AVLTree<Integer> avlTree = new AVLTree<Integer>();
	avlTree.insertNode(14);
	avlTree.insertNode(25);
	avlTree.insertNode(21);
	avlTree.insertNode(10);
	avlTree.insertNode(23);
	avlTree.insertNode(7);
	avlTree.insertNode(26);
	avlTree.insertNode(12);
	avlTree.insertNode(30);
	avlTree.insertNode(16);
	avlTree.print();

	avlTree.insertNode(19);
	avlTree.print();
    }

    public static void test5() {
	AVLTree<Integer> avlTree = new AVLTree<Integer>();
	avlTree.addNodes(Arrays.asList(138, 180, 113, 136, 169, 118, 28, 191, 150, 195, 152, 31, 123, 16, 185, 17, 45,
		196, 11, 49, 94, 157, 129, 173, 154, 32, 12, 2, 117, 149, 194, 186, 59, 99, 142, 90, 170, 183, 57, 141,
		127, 58, 122, 189, 66, 177, 104));
	avlTree.print();

	avlTree.insertNode(188);
	avlTree.print();
    }

    public static void test6() {
	AVLTree<Integer> avlTree = new AVLTree<Integer>();
	avlTree.addNodes(Arrays.asList(138, 180, 113, 136, 169, 118, 28, 191, 150, 195, 152, 31, 123, 16, 185, 17, 45,
		196, 11, 49, 94, 157, 129, 173, 154, 32, 12, 2, 117, 149, 194, 186, 59, 99, 142, 90, 170, 183, 57, 141,
		127, 58, 122, 189, 66, 177, 104));
	avlTree.print();

	avlTree.insertNode(188);
	avlTree.print();

	avlTree.deleteNode(138);
	avlTree.print();
	avlTree.deleteNode(136);
	avlTree.print();
    }

    public static void test7() {
	AVLTree<Integer> avlTree = new AVLTree<Integer>();
	avlTree.insertNode(1);
	avlTree.insertNode(2);
	avlTree.insertNode(3);
	avlTree.insertNode(4);
	avlTree.insertNode(5);
	avlTree.insertNode(6);
	avlTree.insertNode(7);
	avlTree.insertNode(8);
	avlTree.insertNode(9);
	avlTree.insertNode(10);
	avlTree.insertNode(16);
	avlTree.insertNode(17);
	avlTree.insertNode(18);
	avlTree.insertNode(19);
	avlTree.insertNode(20);

	avlTree.print();

	System.out.println("delete 4");
	avlTree.deleteNode(4);
	avlTree.print();

	System.out.println("delete 8");
	avlTree.deleteNode(8);
	avlTree.print();

	System.out.println("delete 2, 3, 1, 6, 5");
	avlTree.deleteNodes(Arrays.asList(2, 3, 1, 6, 5));
	avlTree.print();

    }

    public static void main(String[] args) {
	// System.out.println("\n++++++++++++++++++++++++++++++++1+++++++++++++++++++++++++++++++++++++");
	// test1();
	//
	// System.out.println("\\n+++++++++++++++++++++++++++++++++2++++++++++++++++++++++++++++++++++++");
	// test2();
	//
	// System.out.println("\n++++++++++++++++++++++++++++++++3+++++++++++++++++++++++++++++++++++++");
	// test3();
	//
	// System.out.println("\n++++++++++++++++++++++++++++++++4+++++++++++++++++++++++++++++++++++++");
	// test4();
	//
	System.out.println("\n++++++++++++++++++++++++++++++++5+++++++++++++++++++++++++++++++++++++");
	test5();

	System.out.println("\n++++++++++++++++++++++++++++++++6+++++++++++++++++++++++++++++++++++++");
	test6();

	System.out.println("\n++++++++++++++++++++++++++++++++7+++++++++++++++++++++++++++++++++++++");
	test7();
    }

}
