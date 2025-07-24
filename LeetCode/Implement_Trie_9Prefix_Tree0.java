package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * url: https://leetcode.com/problems/implement-trie-prefix-tree/description/?envType=problem-list-v2&envId=trie
 * 
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.

Implement the Trie class:

Trie() Initializes the trie object.
void insert(String word) Inserts the string word into the trie.
boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
 

Example 1:

Input
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
Output
[null, null, true, false, true, null, true]

Explanation
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // return True
trie.search("app");     // return False
trie.startsWith("app"); // return True
trie.insert("app");
trie.search("app");     // return True
 

Constraints:

1 <= word.length, prefix.length <= 2000
word and prefix consist only of lowercase English letters.
At most 3 * 104 calls in total will be made to insert, search, and startsWith.
 * 
 * @param <T>
 */
class Node3<T> {
	private List<Node3<T>> childrens;
	private T value;
	private boolean isEndWord = false;

	public Node3() {
		this.isEndWord = false;
		this.childrens = new ArrayList<>();
	}

	public Node3(T value) {
		this.value = value;
		this.childrens = new ArrayList<>();
	}

	public List<Node3<T>> getChildrens() {
		return this.childrens;
	}

	public T getValue() {
		return this.value;
	}

	public boolean getIsEndWord() {
		return this.isEndWord;
	}

	public void setIsEndWord(boolean isEndWord) {
		this.isEndWord = isEndWord;
	}

	public String toString() {
		return "[value, isEndWord]=[" + value + ", " + isEndWord + "]";
	}

}

public class Implement_Trie_9Prefix_Tree0 {

	private Node3<Character> root;

	public Implement_Trie_9Prefix_Tree0() {
		root = new Node3<Character>('0');
	}

	public void insert(String word) {
		Node3 node = root;
		for (int ind = 0; ind < word.length(); ind++) {
			List<Node3> childs = node.getChildrens();
			boolean isExist = false;
			for (int p = 0; p < childs.size(); p++) {
				if (childs.get(p).getValue().equals(word.charAt(ind))) {
					node = childs.get(p);
					isExist = true;
					break;
				}
			}
			if (!isExist) {
				node = new Node3(word.charAt(ind));
				childs.add(node);
			}
		}

		// mark as end a word
		node.setIsEndWord(true);

	}

	public boolean search(String word) {
		Node3 node = root;
		for (int ind = 0; ind < word.length(); ind++) {
			List<Node3> childs = node.getChildrens();
			boolean isExist = false;
			for (int p = 0; p < childs.size(); p++) {
				if (childs.get(p).getValue().equals(word.charAt(ind))) {
					node = childs.get(p);
					isExist = true;
					break;
				}
			}
			if (!isExist) {
				return false;
			}
		}

		if (node.getIsEndWord()) {
			return true;
		}

		return false;
	}

	public boolean startsWith(String prefix) {
		Node3 node = root;
		for (int ind = 0; ind < prefix.length(); ind++) {
			List<Node3> childs = node.getChildrens();
			boolean isExist = false;
			for (int p = 0; p < childs.size(); p++) {
				if (childs.get(p).getValue().equals(prefix.charAt(ind))) {
					node = childs.get(p);
					isExist = true;
					break;
				}
			}
			if (!isExist) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
