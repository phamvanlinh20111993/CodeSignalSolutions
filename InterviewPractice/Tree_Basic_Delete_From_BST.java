package InterviewPractice;

public class Tree_Basic_Delete_From_BST {

	static Tree<Integer> deleteFromBST(Tree<Integer> t, int[] queries) {

		for (int i = 0; i < queries.length; i++) {
			Tree<Integer> current = t, previous = t;
			while (current != null && current.value != null && !current.value.equals(queries[i])) {
				previous = current;
				if (queries[i] > current.value) {
					if (current.right != null) {
						current = current.right;
					} else {
						break;
					}
				} else {
					if (current.left != null) {
						current = current.left;
					} else {
						break;
					}
				}
			}
			
			if (current != null && current.value != null && current.value.equals(queries[i])) {
				// if current not have any child
				if (current.left == null && current.right == null) {
					if (previous.left != null && previous.left.value == current.value) {
						previous.left = null;
					} else if (previous.right != null && previous.right.value == current.value) {
						previous.right = null;
					} else {
						//is root
						t = null;
					}
				} else
				// if current have a child left
				if (current.left != null) {
					Tree<Integer> getChildRightLastedt = current.left;
					previous = getChildRightLastedt;
					while (getChildRightLastedt != null && getChildRightLastedt.right != null) {
						previous = getChildRightLastedt;
						getChildRightLastedt = getChildRightLastedt.right;
					}

					current.value = getChildRightLastedt.value;
					if (getChildRightLastedt.value == previous.value) {
						current.left = getChildRightLastedt.left;
					} else {
						previous.right = null;
					}
					// if current do not have any child left but have a child right
				} else {
					// is root
					if (previous.value == current.value) {
						previous.value = current.right.value;
						previous.right = null;
					} else {
						if(previous.right != null && previous.right.value == current.value)
							previous.right = current.right;
						else
							previous.left = current.right;
					}
				}
			}
		}

		return t;
	}

	public static void main(String[] args) {
		BinaryTree binaryTree = new BinaryTree();
		// 3 2 1 8 7
		System.out.println("############## Test 1 #############");
		binaryTree.constructBinaryTreeByListValue(new int[] { 5, 2, 1, 3, 6, 8, 7 });
		// System.out.println("pre order");
		// binaryTree.preOrderBinaryTree(binaryTree.getTree());
		// System.out.println("in order");
		// binaryTree.inOrderBinaryTree(binaryTree.getTree());
		// System.out.println("post order");
		// binaryTree.postOrderBinaryTree(binaryTree.getTree());
		int[] queries = { 4, 5, 6 };
		deleteFromBST(binaryTree.getTree(), queries);
		// System.out.println("pre order");
		binaryTree.preOrderBinaryTree(binaryTree.getTree());
		// null
		System.out.println("############## Test 2 #############");
		binaryTree = new BinaryTree();
		binaryTree.constructBinaryTreeByListValue(new int[] {});
		int[] queries1 = { 1, 2, 3, 5 };
		deleteFromBST(binaryTree.getTree(), queries1);
		binaryTree.preOrderBinaryTree(binaryTree.getTree());
		// null
		System.out.println("############## Test 3 #############");
		binaryTree = new BinaryTree();
		binaryTree.constructBinaryTreeByListValue(new int[] { 3, 2 });
		int[] queries2 = { 1, 2, 3, 5 };
		Tree<Integer> val = deleteFromBST(binaryTree.getTree(), queries2);
		binaryTree.preOrderBinaryTree(val);
		// null
		System.out.println("############## Test 4 #############");
		binaryTree = new BinaryTree();
		binaryTree.constructBinaryTreeByListValue(new int[] { 3, 2, 5 });
		int[] queries4 = { 1, 2, 3, 5 };
		val = deleteFromBST(binaryTree.getTree(), queries4);
		binaryTree.preOrderBinaryTree(val);
		// 5
		System.out.println("############## Test 5 #############");
		binaryTree = new BinaryTree();
		binaryTree.constructBinaryTreeByListValue(new int[] { 3, 2, 1, 5 });
		int[] queries5 = { 3, 2, 1 };
		deleteFromBST(binaryTree.getTree(), queries5);
		binaryTree.preOrderBinaryTree(binaryTree.getTree());
		// 5, 3
		System.out.println("############## Test 6 #############");
		binaryTree = new BinaryTree();
		binaryTree.constructBinaryTreeByListValue(new int[] { 5, 3, 1, 4, 7 });
		int[] queries6 = { 1, 7, 4, 6 };
		deleteFromBST(binaryTree.getTree(), queries6);
		binaryTree.preOrderBinaryTree(binaryTree.getTree());
		
		
		System.out.println("############## Test 7 #############");
		binaryTree = new BinaryTree();
		binaryTree.constructBinaryTreeByListValue(new int[] { 3, 1, 2, 4, 5});
		int[] queries7 = { 1 };
		val = deleteFromBST(binaryTree.getTree(), queries7);
		binaryTree.preOrderBinaryTree(val);
	}

}
