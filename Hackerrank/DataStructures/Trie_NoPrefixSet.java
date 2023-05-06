package Hackerrank.DataStructures;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 
 * @author PhamLinh
 * @Url https://www.hackerrank.com/challenges/no-prefix-set/problem?isFullScreen=true
 * 
 *      There is a given list of strings where each string contains only
 *      lowercase letters from , inclusive. The set of strings is said to be a
 *      GOOD SET if no string is a prefix of another string. In this case, print
 *      GOOD SET. Otherwise, print BAD SET on the first line followed by the
 *      string being checked.
 * 
 *      Note If two strings are identical, they are prefixes of each other.
 * 
 *      Example
 * 
 *      Here 'abcd' is a prefix of 'abcde' and 'bcd' is a prefix of 'bcde'.
 *      Since 'abcde' is tested first, print
 * 
 *      BAD SET abcde .
 * 
 *      No string is a prefix of another so print
 * 
 *      GOOD SET Function Description Complete the noPrefix function in the
 *      editor below.
 * 
 *      noPrefix has the following parameter(s): - string words[n]: an array of
 *      strings
 * 
 *      Prints - string(s): either GOOD SET or BAD SET on one line followed by
 *      the word on the next line. No return value is expected.
 * 
 *      Input Format First line contains , the size of . Then next lines each
 *      contain a string, .
 * 
 *      Constraints
 * 
 *      the length of words[i] All letters in are in the range 'a' through 'j',
 *      inclusive.
 * 
 *      Sample Input00
 * 
 *      STDIN Function ----- -------- 7 words[] size n = 7 aab words = ['aab',
 *      'defgab', 'abcde', 'aabcde', 'bbbbbbbbbb', 'jabjjjad'] defgab abcde
 *      aabcde cedaaa bbbbbbbbbb jabjjjad Sample Output00
 * 
 *      BAD SET aabcde Explanation 'aab' is prefix of 'aabcde' so it is a BAD
 *      SET and fails at string 'aabcde'.
 * 
 *      Sample Input01
 * 
 *      4 aab aac aacghgh aabghgh Sample Output01
 * 
 *      BAD SET aacghgh Explanation 'aab' is a prefix of 'aabghgh', and aac' is
 *      prefix of 'aacghgh'. The set is a BAD SET. 'aacghgh' is tested before
 *      'aabghgh', so and it fails at 'aacghgh'.
 * 
 */
class KeyPairValue<K, V> {
    private K key;
    private V value;

    public KeyPairValue(K key, V value) {
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

class TrieStructure {
    private Character data;
    // a-j , 6 characters
    private TrieStructure[] childList = new TrieStructure[10];
    private boolean isEndWord = false;

    public TrieStructure() {
	this.childList = new TrieStructure[10];
	this.isEndWord = false;
    }

    public TrieStructure(Character data) {
	super();
	this.data = data;
    }

    public Character getData() {
	return data;
    }

    public void setData(Character data) {
	this.data = data;
    }

    public TrieStructure[] getChildList() {
	return childList;
    }

    public void setChildList(TrieStructure[] childList) {
	this.childList = childList;
    }

    public boolean isEndWord() {
	return isEndWord;
    }

    public void setEndWord(boolean isEndWord) {
	this.isEndWord = isEndWord;
    }

    @Override
    public String toString() {
	return "TrieStructure [data=" + data + ", childList=" + Arrays.toString(childList) + ", isEndWord=" + isEndWord
		+ "]";
    }

}

class ManageTrie {
    private TrieStructure trieData = new TrieStructure('*');
    private String prexfixWord = "";

    private boolean isCreateNew = false;

    public void addWord(String word) {
	TrieStructure parentNode = this.trieData;

	for (int ind = 0; ind < word.length(); ind++) {
	    parentNode = this.addToTrie(word.charAt(ind), parentNode);
	    if (parentNode.isEndWord()) {
		this.prexfixWord = word;
	    }
	}

	if (isCreateNew == false) {
	    this.prexfixWord = word;
	} else {
	    isCreateNew = false;
	}

	parentNode.setEndWord(true);
    }

    /**
     * 
     * @param ch
     * @return
     */
    private TrieStructure addToTrie(Character ch, TrieStructure parentNode) {
	int toIntV = (int) ch - 97;
	while (true) {
	    if (parentNode.getChildList()[toIntV] == null) {
		isCreateNew = true;
		parentNode.getChildList()[toIntV] = new TrieStructure(ch);
		return parentNode.getChildList()[toIntV];
	    }

	    if (parentNode.getChildList()[toIntV].getData().equals(ch)) {
		return parentNode.getChildList()[toIntV];
	    }

	    parentNode = parentNode.getChildList()[toIntV];
	}
    }

    public boolean hasPrefix() {
	return prexfixWord.length() > 0;
    }

    public String getPrefixWord() {
	return this.prexfixWord;
    }

    public void printTrie() {
	Stack<KeyPairValue<Integer, TrieStructure>> stack = new Stack<>();
	stack.add(new KeyPairValue<Integer, TrieStructure>(1, this.trieData));

	while (stack.size() > 0) {
	    KeyPairValue<Integer, TrieStructure> data = stack.pop();
	    for (int ind = 0; ind < data.getKey(); ind++) {
		System.out.print("-");
	    }
	    System.out.print(data.getValue().getData() + " " + data.getValue().isEndWord() + "\n");

	    for (TrieStructure child : data.getValue().getChildList()) {
		if (child != null) {
		    stack.push(new KeyPairValue<Integer, TrieStructure>(data.getKey() + 1, child));
		}
	    }
	}
    }
}

public class Trie_NoPrefixSet {

    /*
     * Complete the 'noPrefix' function below.
     *
     * The function accepts STRING_ARRAY words as parameter.
     */

    public static void noPrefix(List<String> words) {

	ManageTrie manageTrie = new ManageTrie();

	for (String word : words) {
	    manageTrie.addWord(word);
	    if (manageTrie.hasPrefix()) {
		System.out.println("BAD SET");
		System.out.println(manageTrie.getPrefixWord());
		return;
	    }
	}
	manageTrie.printTrie();
	System.out.println("GOOD SET");
    }

    public static void main(String[] args) {
	/*
	 * System.out.println("#################### Test 1 #######################");
	 * List<String> words = Arrays.asList("aab", "defgab", "abcde", "aabcde",
	 * "cedaaa", "bbbbbbbbbb", "jabjjjad"); // List<String> words =
	 * Arrays.asList("aab", "abcde", "aabcde"); noPrefix(words);
	 * 
	 * System.out.println("#################### Test 2 #######################"); //
	 * List<String> words2 = Arrays.asList("aab", "aac"); List<String> words2 =
	 * Arrays.asList("aab", "aac", "aacghgh", "aabghgh"); noPrefix(words2);
	 */

	// System.out.println("#################### Test 3 #######################");
	// List<String> words2 = Arrays.asList("aab", "defgab", "abcde", "cedaaa",
	// "bbbbbbbbbb", "jabjjjad");
	// noPrefix(words2);

	System.out.println("#################### Test 4 #######################");
	List<String> words4 = Arrays.asList("adddd", "ad");
	noPrefix(words4);
    }

}
