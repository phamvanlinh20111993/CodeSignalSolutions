package LeetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

/**
 * Url: https://leetcode.com/problems/word-break-ii/description/
 * 
 * Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences in any order.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

 

Example 1:

Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
Output: ["cats and dog","cat sand dog"]
Example 2:

Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: []
 

Constraints:

1 <= s.length <= 20
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 10
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
Input is generated in a way that the length of the answer doesn't exceed 105.

 */
/**
 * 
 * @param <T>
 */
class Node<T> {

	private T val;
	private List<Node<T>> childs;
	private boolean end = false;

	public Node() {
		this.childs = new ArrayList<>();
	}

	public Node(T val) {
		this.val = val;
		this.childs = new ArrayList<>();
	}

	public T getVal() {
		return this.val;
	}

	public boolean getEnd() {
		return this.end;
	}

	public List<Node<T>> getChilds() {
		return this.childs;
	}

	public void addChildNode(Node<T> childNode) {
		this.childs.add(childNode);
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	public String toString() {
		return "[val, end]=[" + val + ", " + end + "]";
	}
}

class TrieTree {

	private Node<Character> rootTrie = new Node<Character>('0');

	public TrieTree(List<String> wordDict) {
		for (int ind = 0; ind < wordDict.size(); ind++) {
			createTrie(this.rootTrie, wordDict.get(ind), 0);
		}
	}

	private void createTrie(Node<Character> root, String word, int ind) {
		if (ind >= word.length())
			return;

		Node<Character> child = null;
		List<Node<Character>> childs = root.getChilds();
		for (int p = 0; p < childs.size(); p++) {
			if (childs.get(p).getVal().equals(word.charAt(ind))) {
				child = childs.get(p);
				break;
			}
		}

		if (child == null) {
			child = new Node<Character>(word.charAt(ind));
			root.addChildNode(child);
		}

		if (ind == word.length() - 1) {
			child.setEnd(true);
		}

		createTrie(child, word, ind + 1);
	}

	private record QueueData(Integer ind, String word) {
		public boolean equals(QueueData other) {
			if (this == other) {
				return true;
			}
			if (other == null) {
				return false;
			}
			if (!(other instanceof QueueData)) {
				return false;
			}
			QueueData data = (QueueData) other;
			if (data.ind.equals(this.ind)) {
				return true;
			}
			return false;
		}

		public int hashCode() {
			return Objects.hash(ind);
		}
	}

	// bfs
	public List<String> findWorkBreak(String s) {
		Queue<QueueData> queue = new LinkedList<>();
		queue.add(new QueueData(0, ""));
		Set<Integer> isChecked = new HashSet<>();
		isChecked.add(0);

		List<String> res = new ArrayList<>();
		while (!queue.isEmpty()) {
			QueueData data = queue.poll();

			if (data.ind() == s.length()) {
				res.add(data.word().toString());
			}

			List<Integer> ids = findWord(this.rootTrie, s, data.ind());
			// System.out.println("Value=" + data.ind() + ", " + ids);
			for (int pos = 0; pos < ids.size(); pos++) {
				// if(!isChecked.contains(ids.get(pos))){
				// isChecked.add(ids.get(pos));
				String newWord = data.word() + (data.word() == "" ? "" : " ") + s.substring(data.ind(), ids.get(pos));
				queue.add(new QueueData(ids.get(pos), newWord));
				// }
			}
		}

		return res;
	}

	private List<Integer> findWord(Node<Character> root, String s, int ind) {
		List<Integer> wordsInd = new ArrayList<>();

		if (root.getEnd()) {
			wordsInd.add(ind);
		}

		if (ind >= s.length())
			return wordsInd;

		List<Node<Character>> childs = root.getChilds();
		for (int p = 0; p < childs.size(); p++) {
			if (childs.get(p).getVal().equals(s.charAt(ind))) {
				wordsInd.addAll(findWord(childs.get(p), s, ind + 1));
			}
		}

		return wordsInd;
	}

	public void print() {
		dfsPrint(this.rootTrie, 0);
	}

	private void dfsPrint(Node<Character> rootTrie, int level) {
		System.out.print(level);
		for (int ind = 0; ind < level; ind++) {
			System.out.print("--");
		}
		System.out.println(" " + rootTrie);
		for (int p = 0; p < rootTrie.getChilds().size(); p++) {
			dfsPrint(rootTrie.getChilds().get(p), level + 1);
		}
	}

}

public class WordBreak2 {

	public List<String> wordBreak(String s, List<String> wordDict) {
		TrieTree trieTree = new TrieTree(wordDict);
		// trieTree.print();
		return trieTree.findWorkBreak(s);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
