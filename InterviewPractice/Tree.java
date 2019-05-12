package InterviewPractice;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
	
	/**
	 * 
	 * @param value
	 */
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
	/**
	 * 
	 * @param listValue
	 * @return 
	 */
	public void constructBinaryTreeByListValue(ArrayList<Integer> listValue) {
		int i, t = 0;
		
		Tree<Integer> temp = this.binaryTree;
		if(temp.value == null && listValue.size() > 0) {
			t = 1;
			temp.value = listValue.get(0);
		}
		
		for(i = t; i < listValue.size(); i++) {
			temp = this.binaryTree;
			while(temp != null) {
				
				if(listValue.get(i) < temp.value) {
					if(temp.left != null) {
						temp = temp.left;
					}else {
						temp.left = new Tree<Integer>(listValue.get(i));
						break;
					}
				}else {
					if(temp.right != null) {
						temp = temp.right;
					}else {
						temp.right = new Tree<Integer>(listValue.get(i));
						break;
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * @param listValue
	 * @return 
	 */
	public void constructBinaryTreeByListValue(int [] listValue) {
		int i, t = 0;
		
		Tree<Integer> temp = this.binaryTree;
		if(temp.value == null && listValue.length > 0) {
			t = 1;
			temp.value = listValue[0];
		}
		
		for(i = t; i < listValue.length; i++) {
			temp = this.binaryTree;
			while(temp != null) {
				if(listValue[i] < temp.value) {
					if(temp.left != null) {
						temp = temp.left;
					}else {
						temp.left = new Tree<Integer>(listValue[i]);
						break;
					}
				}else {
					if(temp.right != null) {
						temp = temp.right;
					}else {
						temp.right = new Tree<Integer>(listValue[i]);
						break;
					}
				}
			}
		}
	}
	
	/**
	 *                       A
     *                     /   \
     *                    B     C
     *                   / \   /  \
     *                  D   E F    G
	 * 
	 */
	
	/**
	 * 1, Duyệt tiền thứ tự với cây này diễn ra tuần tự như sau
	 *	Thăm A, Duyệt cây gốc B, Duyệt cây gốc C
	 *	Thăm A, Thăm B, Thăm D, Thăm E, Thăm C, Thăm F, Thăm G
	 *
	 * @param binaryTree
	 * 
	 */
	public <T> void preOrderBinaryTree(Tree<T> binaryTree) {
		if (binaryTree != null) {
			System.out.println(binaryTree.value);
		}
		if (binaryTree != null && binaryTree.left != null) {
			preOrderBinaryTree(binaryTree.left);
		}
		if (binaryTree != null && binaryTree.right != null) {
			preOrderBinaryTree(binaryTree.right);
		}
	}
	
	/**
	 * 2, Duyệt trung thứ tự với cây này diễn ra tuần tự như sau
	 *	Duyệt cây gốc B, Thăm A, Duyệt cây gốc C
	 *	Thăm D, Thăm B, Thăm E, Thăm A, Thăm F, Thăm C, Thăm G
	 *
	 * @param binaryTree
	 */
	public <T> void inOrderBinaryTree(Tree<T> binaryTree) {
		if (binaryTree != null && binaryTree.left != null) {
			inOrderBinaryTree(binaryTree.left);
		}
		if (binaryTree != null) {
			System.out.println(binaryTree.value);
		}
		if (binaryTree != null && binaryTree.right != null) {
			inOrderBinaryTree(binaryTree.right);
		}
		
	}
	
	/**
	 * 3, Duyệt hậu thứ tự với cây này diễn ra tuần tự như sau
	 *	Duyệt cây gốc B, Duyệt cây gốc C, Thăm A
	 *	Thăm D, Thăm E, Thăm B, Thăm F, Thăm G, Thăm C, Thăm A
	 *
	 * @param binaryTree
	 */
	public <T> void postOrderBinaryTree(Tree<T> binaryTree) {
		if (binaryTree != null && binaryTree.left != null) {
			postOrderBinaryTree(binaryTree.left);
		}
		if (binaryTree != null && binaryTree.right != null) {
			postOrderBinaryTree(binaryTree.right);
		}
		if (binaryTree != null) {
			System.out.println(binaryTree.value);
		}
	}

	/**
	 * 
	 * @return
	 */
	public Tree<Integer> getTree() {
		return this.binaryTree;
	}
	/**
	 * 
	 * @param tree
	 */
	public void setTree(Tree<Integer> tree) {
		this.binaryTree = tree;
	}

}

