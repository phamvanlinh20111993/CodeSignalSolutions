package InterviewPractice;

public class HasPathWithGivenSum {
	
	static boolean isHasPath;
	static void DFSFindSum(Tree<Integer> t, int s, int sum) {
		if(t.left == null && t.right == null) {
			if(sum + t.value == s) isHasPath = true;
		}
		if(t.left != null) {
			DFSFindSum(t.left, s, sum + t.value);
		}
		if(t.right != null) {
			DFSFindSum(t.right, s, sum + t.value);
		}
		
	}
	
	static long sumT;
	static void DFSFindSum(Tree<Integer> t, String sum) {
		if(t.left == null && t.right == null) {
			sumT += Long.parseLong(( sum + Integer.toString(t.value) ));
		}
		if(t.left != null) {
			DFSFindSum(t.left, sum + Integer.toString(t.value));
		}
		if(t.right != null) {
			DFSFindSum(t.right, sum + Integer.toString(t.value));
		}
		
	}
	
	/**
	 * codesignal https://app.codesignal.com/interview-practice/task/TG4tEMPnAc3PnzRCs
	 * @param t
	 * @param s
	 * @return 
	 */
	static boolean hasPathWithGivenSum(Tree<Integer> t, int s) {
		isHasPath = false;
		DFSFindSum(t, s, 0);
		System.out.println(isHasPath);
		return isHasPath;
	}
	/**
	 * codesignal https://app.codesignal.com/interview-practice/task/2oxNWXTS8eWBzvnRB/description
	 * @param t
	 * @return
	 */
	static long digitTreeSum(Tree<Integer> t) {
		sumT = 0L;
		DFSFindSum(t, "");
		System.out.println(sumT);
		return sumT;
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
		hasPathWithGivenSum(tree.getTree(), 97);
		
		digitTreeSum(tree.getTree());
	}

}
