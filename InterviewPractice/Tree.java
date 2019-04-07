package InterviewPractice;
/**
 * 
 * @author PhamVanLinh
 *
 */
public class Tree<T> {
	T value;
	
	Tree() {

	}
	
	Tree(T x){
		value = x;
	}
	
	Tree<T> left;
	Tree<T> right;
}

class BinaryTree {
	private Tree<Integer> binaryTree;

	public BinaryTree() {
		binaryTree = new Tree<Integer>();
	}

	public BinaryTree(Integer value) {
		binaryTree = new Tree<Integer>();
		binaryTree.value = value;
	}

	public void AddValue(Integer value) {
		Tree<Integer> temp = this.binaryTree;
		while (temp != null) {
			if (value > temp.value) {
				if (temp.right != null) {
					temp = temp.right;
				} else {
					temp.right = new Tree<Integer>(value);
					break;
				}
			} else {
				if (temp.left != null) {
					temp = temp.left;
				} else {
					temp.left = new Tree<Integer>(value);
					break;
				}
			}
		}
	}

	public Tree<Integer> getTree() {
		return this.binaryTree;
	}

	public void setTree(Tree<Integer> tree) {
		this.binaryTree = tree;
	}

}

