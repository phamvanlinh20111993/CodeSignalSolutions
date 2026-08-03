package LeetCode;

import java.util.TreeMap;

/**
 * Url: https://leetcode.com/problems/smallest-palindromic-rearrangement-i/description/?envType=daily-question&envId=2026-07-28
 * 
 * You are given a palindromic string s.

Return the lexicographically smallest palindromic permutation of s.

 

Example 1:

Input: s = "z"

Output: "z"

Explanation:

A string of only one character is already the lexicographically smallest palindrome.

Example 2:

Input: s = "babab"

Output: "abbba"

Explanation:

Rearranging "babab" → "abbba" gives the smallest lexicographic palindrome.

Example 3:

Input: s = "daccad"

Output: "acddca"

Explanation:

Rearranging "daccad" → "acddca" gives the smallest lexicographic palindrome.

 

Constraints:

1 <= s.length <= 105
s consists of lowercase English letters.
s is guaranteed to be palindromic.
 */
public class Smallest_Palindromic_Rearrangement_I_3517 {

	public String smallestPalindrome(String s) {
		TreeMap<Character, Integer> m = new TreeMap<>();
		for (int ind = 0; ind < s.length(); ind++) {
			Integer dt = m.getOrDefault(s.charAt(ind), 0);
			m.put(s.charAt(ind), dt + 1);
		}

		StringBuilder b = new StringBuilder();
		Character oddCh = null;
		for (Character key : m.keySet()) {
			Integer currentVal = m.get(key);
			if (currentVal % 2 != 0) {
				oddCh = key;
				currentVal--;
			}
			while (currentVal > m.get(key) / 2) {
				b.append(key);
				currentVal--;
			}
			m.put(key, currentVal);
		}

		if (oddCh != null) {
			b.append(oddCh);
		}

		for (Character key : m.descendingKeySet()) {
			Integer currentVal = m.get(key);
			while (currentVal > 0) {
				b.append(key);
				currentVal--;
			}
		}

		return b.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
