package Codesignal.InterviewPractice;

import java.util.Stack;

public class IsSubTree {

	static boolean compareTwoTree(Tree<Integer> t1, Tree<Integer> t2) {
		Stack<Tree> stackTree1 = new Stack<Tree>(), stackTree2 = new Stack<Tree>();

		stackTree1.push(t1);
		stackTree2.push(t2);

		while (!stackTree1.isEmpty() && !stackTree2.isEmpty()) {
			Tree<Integer> tmp = stackTree1.pop(), tmp1 = stackTree2.pop();

			if (!tmp.value.equals(tmp1.value))
				return false;
			if ((tmp1.right != null && tmp.right == null) || (tmp1.left != null && tmp.left == null))
				return false;

			if (tmp1.right != null) {
				stackTree1.push(tmp.right);
				stackTree2.push(tmp1.right);
			}

			if (tmp1.left != null) {
				stackTree1.push(tmp.left);
				stackTree2.push(tmp1.left);
			}
		}

		if (!stackTree2.isEmpty())
			return false;

		return true;
	}

	static boolean isSubtree(Tree<Integer> t1, Tree<Integer> t2) {

		if (t1 == null || t2 == null)
			return false;

		Stack<Tree> stackTree = new Stack<Tree>();
		stackTree.push(t1);
		while (!stackTree.isEmpty()) {
			Tree tmp = stackTree.pop();
			System.out.print(tmp.value + " ");
			if (tmp.value.equals(t2.value)) {
				if (compareTwoTree(tmp, t2)) {
					return true;
				}
			}

			if (tmp.right != null) {
				stackTree.push(tmp.right);
			}
			if (tmp.left != null) {
				stackTree.push(tmp.left);
			}
		}

		return false;
	}

	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree(50);
		tree.AddValue(26);
		tree.AddValue(47);
		tree.AddValue(12);
		tree.AddValue(22);
		tree.AddValue(32);
		tree.AddValue(45);
		tree.AddValue(49);
		tree.AddValue(77);
		tree.AddValue(70);
		tree.AddValue(80);
		tree.AddValue(9);
		tree.AddValue(23);

		BinaryTree tree1 = new BinaryTree(77);
		tree1.AddValue(70);
		tree1.AddValue(80);

		System.out.println("\n" + isSubtree(tree.getTree(), tree1.getTree()));
	}

}
