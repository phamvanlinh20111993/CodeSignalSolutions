package Codesignal.Challenge;

import java.util.TreeMap;

public class MedianScores {
	
	
	static class Node {
		int key = 0;
		Node left, right;

		public Node(int key) {
			this.key = key;
			this.right = this.left = null;
		}
	}

	static class BinaryTree {

		public Node root;

		BinaryTree() {
			root = null;
		}

		BinaryTree(int key) {
			root = new Node(key);
		}

		void insert(int key) {

			if (root == null) {
				root = new Node(key);
			} else {
				Node tmp = root;

				while (true) {
					if (key < tmp.key) {
						if (tmp.left != null)
							tmp = tmp.left;
						else {
							tmp.left = new Node(key);
							break;
						}
					} else {
						if (tmp.right != null)
							tmp = tmp.right;
						else {
							tmp.right = new Node(key);
							break;
						}
					}
				}
			}
		}
	}
	
	static BinaryTree tree;
	static int k, num = -1, num1 = -1;

	// search node
	static void searchValue(Node root) {
		if (root != null) {
			searchValue(root.left);
			if (k == 1)
				num1 = root.key;
			if (k == 0)
				num = root.key;
			k--;
			searchValue(root.right);
		}
	}

	static int[] medianScores1(int[] scores) {
		tree = new BinaryTree();
		int[] result = new int[scores.length];
		int i, sum = 0, j = 0;
		for (i = 0; i < scores.length; i++) {
			sum += 1;
			tree.insert(scores[i]);
			k = sum / 2;
			searchValue( tree.root);
			if (sum % 2 == 0) {
				int tmp1 = num1;
				result[j++] = (int) Math.ceil((double) (num + tmp1) / 2);
			} else {
				result[j++] = num;
			}
		}

		for (i = 0; i < scores.length; i++)
			System.out.print(result[i] + "   ");
		System.out.println();

		return result;
	}

	static int[] medianScores(int[] scores) {
		int[] result = new int[scores.length], tmp = new int[scores.length];
		TreeMap<Integer, String> map = new TreeMap<>();
		int i, sum = 0, j = 0, k;
		for (i = 0; i < scores.length; i++) {
			sum += 1;
			if (map.get(scores[i]) != null)
				map.put(scores[i], map.get(scores[i]) + "-" + scores[i]);
			else
				map.put(scores[i], Integer.toString(scores[i]));
			k = 0;
			for (Integer key : map.keySet()) {
				String[] listN = map.get(key).split("-");
				if (listN.length > 1) {
					int p = 0;
					while (p < listN.length)
						tmp[k++] = Integer.parseInt(listN[p++]);
				} else
					tmp[k++] = Integer.parseInt(map.get(key));
			}

			if (sum % 2 == 0) {
				result[j++] = (int) Math.ceil((double) (tmp[(sum / 2) - 1] + tmp[sum / 2]) / 2);
			} else {
				result[j++] = tmp[sum / 2];
			}
		}

		for (i = 0; i < scores.length; i++)
			System.out.print(result[i] + "   ");
		System.out.println();

		return result;
	}

	public static void main(String[] args) {
		int[] scores = { 97, 65, 90, 95, 95, 78, 80, 84, 70, 67, 87, 71, 53, 65, 86, 76, 81, 67, 78, 84, 92, 84, 75, 55,
				58, 55, 59, 71, 91, 76, 54, 58 };
		System.out.println("############## test 1 ###############");
		// // medianScores(scores);
		medianScores1(scores);

		int[] scores1 = { 100, 20, 50, 70, 45 };
		System.out.println("############## test 2 ###############");
		// medianScores(scores1);
		medianScores1(scores1);
		
		int[] scores2 = { 10,20,30 };
		System.out.println("############## test 3 ###############");
		// medianScores(scores1);
		medianScores1(scores2);

	}

}
