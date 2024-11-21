package LeetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Url: https://leetcode.com/problems/word-break/description/
 * 
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

 

Example 1:

Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false
 

Constraints:

1 <= s.length <= 300
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 20
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.

 * @param <T>
 */
class Node1<T> {

	private T val;
	private List<Node1> childs;
	private boolean end = false;

	public Node1() {
		this.childs = new ArrayList<>();
	}

	public Node1(T val) {
		this.val = val;
		this.childs = new ArrayList<>();
	}

	public T getVal() {
		return this.val;
	}

	public boolean getEnd() {
		return this.end;
	}

	public List<Node1> getChilds() {
		return this.childs;
	}

	public void addChildNode(Node1<T> childNode) {
		this.childs.add(childNode);
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	public String toString() {
		return "[val, end]=[" + val + ", " + end + "]";
	}
}

class TrieTree1 {

	private Node1<Character> rootTrie = new Node1('0');

	public TrieTree1(List<String> wordDict) {
		for (int ind = 0; ind < wordDict.size(); ind++) {
			createTrie(this.rootTrie, wordDict.get(ind), 0);
		}
	}

	private void createTrie(Node1<Character> root, String word, int ind) {
		if (ind >= word.length())
			return;

		Node1<Character> child = null;
		List<Node1> childs = root.getChilds();
		for (int p = 0; p < childs.size(); p++) {
			if (childs.get(p).getVal().equals(word.charAt(ind))) {
				child = childs.get(p);
				break;
			}
		}

		if (child == null) {
			child = new Node1(word.charAt(ind));
			root.addChildNode(child);
		}

		if (ind == word.length() - 1) {
			child.setEnd(true);
		}

		createTrie(child, word, ind + 1);
	}

	// bfs
	public boolean findWorkBreak(String s) {

		Queue<Integer> queue = new LinkedList<>();
		queue.add(0);
		Set<Integer> isChecked = new HashSet<>();
		isChecked.add(0);

		while (!queue.isEmpty()) {
			Integer ind = queue.poll();

			if (ind == s.length())
				return true;
			List<Integer> ids = findWord(this.rootTrie, s, ind);
			for (int pos = 0; pos < ids.size(); pos++) {
				if (!isChecked.contains(ids.get(pos))) {
					isChecked.add(ids.get(pos));
					queue.add(ids.get(pos));
				}
			}
		}

		return false;
	}

	private List<Integer> findWord(Node1<Character> root, String s, int ind) {
		List<Integer> wordsInd = new ArrayList<>();

		if (root.getEnd()) {
			wordsInd.add(ind);
		}

		if (ind >= s.length())
			return wordsInd;

		List<Node1> childs = root.getChilds();
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

	private void dfsPrint(Node1<Character> rootTrie, int level) {
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

public class WordBreak {

	public boolean wordBreak(String s, List<String> wordDict) {
		TrieTree1 trieTree = new TrieTree1(wordDict);
		// trieTree.print();

		return trieTree.findWorkBreak(s);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
