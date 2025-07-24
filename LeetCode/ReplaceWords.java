package LeetCode;

import java.util.List;

/**
 * url: https://leetcode.com/problems/replace-words/description/?envType=problem-list-v2&envId=trie
 * 
 * In English, we have a concept called root, which can be followed by some other word to form another longer word - let's call this word derivative. For example, when the root "help" is followed by the word "ful", we can form a derivative "helpful".

Given a dictionary consisting of many roots and a sentence consisting of words separated by spaces, replace all the derivatives in the sentence with the root forming it. If a derivative can be replaced by more than one root, replace it with the root that has the shortest length.

Return the sentence after the replacement.

 

Example 1:

Input: dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
Output: "the cat was rat by the bat"
Example 2:

Input: dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
Output: "a a b c"
 

Constraints:

1 <= dictionary.length <= 1000
1 <= dictionary[i].length <= 100
dictionary[i] consists of only lower-case letters.
1 <= sentence.length <= 106
sentence consists of only lower-case letters and spaces.
The number of words in sentence is in the range [1, 1000]
The length of each word in sentence is in the range [1, 1000]
Every two consecutive words in sentence will be separated by exactly one space.
sentence does not have leading or trailing spaces.
 */

class Trie {

	private Node3<Character> root;

	public Trie() {
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

	public String getDictionary(String word) {
		Node3 node = root;
		StringBuilder res = new StringBuilder("");
		for (int ind = 0; ind < word.length(); ind++) {

			if (node.getIsEndWord())
				return res.toString();

			boolean isContain = false;
			List<Node3> childs = node.getChildrens();
			for (int p = 0; p < childs.size(); p++) {
				if (childs.get(p).getValue().equals(word.charAt(ind))) {
					res.append(word.charAt(ind));
					node = childs.get(p);
					isContain = true;
					break;
				}
			}
			if (!isContain)
				return null;
		}

		return null;
	}

	public void print() {
		dfsPrint(this.root, 0);
	}

	private void dfsPrint(Node3<Character> rootTrie, int level) {
		System.out.print(level);
		for (int ind = 0; ind < level; ind++) {
			System.out.print("--");
		}
		System.out.println(" " + rootTrie);
		for (int p = 0; p < rootTrie.getChildrens().size(); p++) {
			dfsPrint(rootTrie.getChildrens().get(p), level + 1);
		}
	}

}

public class ReplaceWords {

	public String replaceWords(List<String> dictionary, String sentence) {
		String[] words = sentence.split(" ");

		Trie trie = new Trie();
		for (String dict : dictionary) {
			trie.insert(dict);
		}

		// trie.print();

		StringBuilder s = new StringBuilder();

		for (String word : words) {
			String t = trie.getDictionary(word);
			if (t != null) {
				s.append(t);
			} else {
				s.append(word);
			}
			s.append(" ");
		}

		return s.toString().substring(0, s.length() - 1);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
