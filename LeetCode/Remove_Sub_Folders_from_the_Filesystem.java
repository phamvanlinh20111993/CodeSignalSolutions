package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * url: https://leetcode.com/problems/remove-sub-folders-from-the-filesystem/description/?envType=daily-question&envId=2025-07-19
 * 
 * Given a list of folders folder, return the folders after removing all sub-folders in those folders. You may return the answer in any order.

If a folder[i] is located within another folder[j], it is called a sub-folder of it. A sub-folder of folder[j] must start with folder[j], followed by a "/". For example, "/a/b" is a sub-folder of "/a", but "/b" is not a sub-folder of "/a/b/c".

The format of a path is one or more concatenated strings of the form: '/' followed by one or more lowercase English letters.

For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string and "/" are not.
 

Example 1:

Input: folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
Output: ["/a","/c/d","/c/f"]
Explanation: Folders "/a/b" is a subfolder of "/a" and "/c/d/e" is inside of folder "/c/d" in our filesystem.
Example 2:

Input: folder = ["/a","/a/b/c","/a/b/d"]
Output: ["/a"]
Explanation: Folders "/a/b/c" and "/a/b/d" will be removed because they are subfolders of "/a".
Example 3:

Input: folder = ["/a/b/c","/a/b/ca","/a/b/d"]
Output: ["/a/b/c","/a/b/ca","/a/b/d"]
 

Constraints:

1 <= folder.length <= 4 * 104
2 <= folder[i].length <= 100
folder[i] contains only lowercase letters and '/'.
folder[i] always starts with the character '/'.
Each folder name is unique.
 * 
 */
class Node6<T> {
	private Map<T, Node6<T>> childrens;
	private T value;
	private boolean isEndWord = false;

	public Node6() {
		this.isEndWord = false;
		this.childrens = new HashMap<>();
	}

	public Node6(T value) {
		this.value = value;
		this.childrens = new HashMap<>();
	}

	public Map<T, Node6<T>> getChildrens() {
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

class TrieSubFolder {
	private Node6<String> root;

	public TrieSubFolder() {
		root = new Node6<String>("");
	}

	public void insert(List<String> words) {
		Node6 node = root;
		int s = words.size();
		for (int ind = 0; ind < s; ind++) {
			Map<String, Node6> childs = node.getChildrens();
			boolean isExist = false;
			if (childs.containsKey(words.get(ind))) {
				node = childs.get(words.get(ind));
				isExist = true;
			}
			if (!isExist) {
				node = new Node6(words.get(ind));
				childs.put(words.get(ind), node);
			}
		}

		// mark as end a word
		node.setIsEndWord(true);
	}

	public String findFolder(List<String> words) {
		Node6 node = root;
		StringBuilder subFolder = new StringBuilder("");
		int s = words.size();
		for (int ind = 0; ind < s; ind++) {
			Map<String, Node6> childs = node.getChildrens();
			if (childs.containsKey(words.get(ind))) {
				subFolder.append("/").append(words.get(ind));
				node = childs.get(words.get(ind));
				if (node.getIsEndWord())
					return subFolder.toString();
			}
		}

		return subFolder.toString();
	}
}

public class Remove_Sub_Folders_from_the_Filesystem {
	public List<String> getPathNames(String fo) {
		List<String> pathNames = new ArrayList<>();
		StringBuilder temp = new StringBuilder("");
		for (int ind = 0; ind < fo.length(); ind++) {
			if (fo.charAt(ind) == '/') {
				if (temp.length() > 0) {
					pathNames.add(temp.toString());
					temp = new StringBuilder("");
				}
			} else {
				temp.append(fo.charAt(ind));
			}
		}
		if (temp.length() > 0) {
			pathNames.add(temp.toString());
		}

		return pathNames;
	}

	public List<String> removeSubfolders(String[] folder) {
		List<List<String>> pathNamesL = new ArrayList<>();
		TrieSubFolder trie = new TrieSubFolder();

		for (String fo : folder) {
			List<String> pathNames = getPathNames(fo);
			pathNamesL.add(pathNames);
			trie.insert(pathNames);
		}

		Set<String> res = new HashSet<>();
		for (List<String> pathNames : pathNamesL) {
			res.add(trie.findFolder(pathNames));
		}

		return new ArrayList<>(res);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
