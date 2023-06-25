package Hackerrank.DataStructures;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author PhamLinh
 * @url https://www.hackerrank.com/challenges/contacts/problem
 *
 * @param <K>
 * @param <V>
 * 
 *            We're going to make our own Contacts application! The application
 *            must perform two types of operations:
 * 
 *            add name, where is a string denoting a contact name. This must
 *            store as a new contact in the application. find partial, where is
 *            a string denoting a partial name to search the application for. It
 *            must count the number of contacts starting with and print the
 *            count on a new line. Given sequential add and find operations,
 *            perform each operation in order.
 * 
 *            Example Operations are requested as follows:
 * 
 *            add ed add eddie add edward find ed add edwina find edw find a
 *            Three operations include the names 'ed', 'eddie', and 'edward'.
 *            Next, matches all names. Note that it matches and counts the
 *            entire name 'ed'. Next, add 'edwina' and then find 'edw'. names
 *            match: 'edward' and 'edwina'. In the final operation, there are
 *            names that start with 'a'. Return the array .
 * 
 *            Function Description
 * 
 *            Complete the contacts function below.
 * 
 *            contacts has the following parameters:
 * 
 *            string queries[n]: the operations to perform Returns
 * 
 *            int[]: the results of each find operation Input Format
 * 
 *            The first line contains a single integer, , the number of
 *            operations to perform (the size of ). Each of the following lines
 *            contains a string, .
 * 
 *            Constraints
 * 
 *            and contain lowercase English letters only. The input does not
 *            have any duplicate for the operation. Sample Input
 * 
 *            STDIN Function ----- -------- 4 queries[] size n = 4 add hack
 *            queries = ['add hack', 'add hackerrank', 'find hac', 'find hak']
 *            add hackerrank find hac find hak Sample Output
 * 
 *            2 0 Explanation
 * 
 *            Add a contact named hack. Add a contact named hackerrank. Find the
 *            number of contact names beginning with hac. Both name start with
 *            hac, add to the return array. Find the number of contact names
 *            beginning with hak. neither name starts with hak, add to the
 *            return array.
 * 
 */
class KeyPair<K, V> {
	private K key;
	private V value;

	public KeyPair(K key, V value) {
		super();
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public boolean hasKey(K key) {
		return this.key.equals(key);
	}

	@Override
	public String toString() {
		return "KeyPair [key=" + key + ", value=" + value + "]";
	}

}

class Node {

	private Character data;
	private List<KeyPair<Character, Integer>> childList;
	private Integer parentIndex;
	private Integer totalChild = 0;
	private Integer curentIndex;

	public Node(Character data, List<KeyPair<Character, Integer>> childList, Integer parentIndex) {
		super();
		this.data = data;
		this.childList = childList;
		this.parentIndex = parentIndex;
	}

	public Node(Character data, List<KeyPair<Character, Integer>> childList, Integer parentIndex, Integer curentIndex) {
		super();
		this.data = data;
		this.childList = childList;
		this.parentIndex = parentIndex;
		this.curentIndex = curentIndex;
	}

	public Character getData() {
		return data;
	}

	public void setData(Character data) {
		this.data = data;
	}

	public List<KeyPair<Character, Integer>> getChildList() {
		return childList;
	}

	public void setChildList(List<KeyPair<Character, Integer>> childList) {
		this.childList = childList;
	}

	public Integer getParentIndex() {
		return parentIndex;
	}

	public void setParentIndex(Integer parentIndex) {
		this.parentIndex = parentIndex;
	}

	public Integer getCurentIndex() {
		return curentIndex;
	}

	public void setCurentIndex(Integer curentIndex) {
		this.curentIndex = curentIndex;
	}

	public Integer getTotalChild() {
		return totalChild;
	}

	public void setTotalChild(Integer totalChild) {
		this.totalChild = totalChild;
	}

	@Override
	public String toString() {
		return "Node1 [data=" + data + ", childList=" + childList + ", parentIndex=" + parentIndex + ", totalChild="
				+ totalChild + ", curentIndex=" + curentIndex + "]";
	}

}

class Trie {

	private List<Node> trieNodes = new ArrayList<>();

	public Trie() {
		trieNodes.add(new Node('-', new ArrayList<>(), -1, 0));
	}

	public void add(String data) {
		Node parentNode = this.trieNodes.get(0);
		parentNode.setTotalChild(parentNode.getTotalChild() + 1);
		for (int ind = 0; ind < data.length(); ind++) {
			char character = data.charAt(ind);
			parentNode = this.addToTrie(character, parentNode);
		}
		// add a null node mark as end of string data
		parentNode.getChildList().add(new KeyPair<Character, Integer>('-', this.trieNodes.size()));
		// parentNode.setTotalChild(parentNode.getTotalChild() + 1);
		this.trieNodes.add(new Node('-', null, null));
	}

	private Node addToTrie(Character character, Node curentNode) {
		Node parentNode = curentNode;
		while (true) {
			KeyPair<Character, Integer> hasData = parentNode.getChildList().stream()
					.filter(data -> data.hasKey(character)).findFirst().orElse(null);
			if (hasData != null) {
				int nextIndex = hasData.getValue();
				parentNode = this.trieNodes.get(nextIndex);
				parentNode.setTotalChild(parentNode.getTotalChild() + 1);
				return parentNode;
			} else {
				parentNode.getChildList().add(new KeyPair<Character, Integer>(character, this.trieNodes.size()));
				Node newNode = new Node(character, new ArrayList<>(), parentNode.getCurentIndex(),
						this.trieNodes.size());
				newNode.setTotalChild(newNode.getTotalChild() + 1);
				this.trieNodes.add(newNode);
				return newNode;
			}
		}
	}

	public Integer find(String data) {
		Node parentNode = this.trieNodes.get(0);

		for (int ind = 0; ind < data.length(); ind++) {
			char character = data.charAt(ind);
			KeyPair<Character, Integer> hasData = parentNode.getChildList().stream().filter(c -> c.hasKey(character))
					.findFirst().orElse(null);
			if (hasData != null) {
				int nextIndex = hasData.getValue();
				parentNode = this.trieNodes.get(nextIndex);
			} else {
				parentNode = null;
				break;
			}
		}

		return parentNode != null ? this.trieNodes.get(parentNode.getCurentIndex()).getTotalChild() : 0;
	}

	@Override
	public String toString() {
		trieNodes.stream().forEach(v -> System.out.println(v));
		return "Trie [trieNodes=" + this.trieNodes + "]";
	}

}

public class Trie_Contacts {

	/*
	 * Complete the 'contacts' function below.
	 *
	 * The function is expected to return an INTEGER_ARRAY. The function accepts
	 * 2D_STRING_ARRAY queries as parameter.
	 */

	public static List<Integer> contacts(List<List<String>> queries) {
		// Write your code here
		Trie trie = new Trie();
		List<Integer> res = new ArrayList<>();
		for (List<String> query : queries) {
			String command = query.get(0);
			String data = query.get(1);
			if (command.equals("add")) {
				trie.add(data);
			} else {
				res.add(trie.find(data));
			}
		}

		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
