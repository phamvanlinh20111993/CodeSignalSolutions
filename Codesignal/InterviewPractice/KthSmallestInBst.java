package Codesignal.InterviewPractice;

public class KthSmallestInBst {
	static int pos = 0, val = -1;
	
	static void findPosBst(Tree<Integer> t, int k) {
		if(t.left != null) {
			findPosBst(t.left, k);
		}
		
		if(pos+1 == k) {
			val = t.value;
		}
		pos++;
		
		if(t.right != null) {
			findPosBst(t.right, k);
		}
	}
	
	static int kthSmallestInBst(Tree<Integer> t, int k) {
		
		pos = 0;
		findPosBst(t, k);
		System.out.println("out " + val);
		
		return val;
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
		/*
		 *                           50
		 *                        /      \
		 *                       26       77
		 *                     /   \     /  \
		 *                   12    47  	70  80
		 *                  /  \   /  \
		 *                 9    22 32  49
		 *                      /    \ 
		 *                     23     45
		 */
		kthSmallestInBst(tree.getTree(), 4);
	}

}
