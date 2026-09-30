package LeetCode;

import java.util.Stack;

/**
 * url: https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/submissions/2154516733/?envType=daily-question&envId=2026-09-27
 * 
 * You are given a string s that consists of lower case English letters and brackets.

Reverse the strings in each pair of matching parentheses, starting from the innermost one.

Your result should not contain any brackets.

 

Example 1:

Input: s = "(abcd)"
Output: "dcba"
Example 2:

Input: s = "(u(love)i)"
Output: "iloveu"
Explanation: The substring "love" is reversed first, then the whole string is reversed.
Example 3:

Input: s = "(ed(et(oc))el)"
Output: "leetcode"
Explanation: First, we reverse the substring "oc", then "etco", and finally, the whole string.
 

Constraints:

1 <= s.length <= 2000
s only contains lower case English characters and parentheses.
It is guaranteed that all parentheses are balanced.
 */
public class Reverse_Substrings_Between_Each_Pair_of_Parentheses_1190 {

	public String reverseParentheses(String s) {
		StringBuilder sb = new StringBuilder();
		Stack<Character> st = new Stack<>();

		for (int ind = 0; ind < s.length(); ind++) {
			if (s.charAt(ind) == ')') {
				StringBuilder sbTmp = new StringBuilder();
				while (!st.isEmpty() && st.peek() != '(') {
					sbTmp.append(st.pop());
				}
				st.pop();
				for (int i = 0; i < sbTmp.length(); i++) {
					char c = sbTmp.charAt(i);
					st.push(c);
				}
				continue;
			}
			st.push(s.charAt(ind));
		}

		while (!st.isEmpty()) {
			sb.append(st.pop());
		}
		sb.reverse();

		return sb.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
