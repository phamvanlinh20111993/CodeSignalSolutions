package LeetCode;

import java.util.Stack;

/**
 * Url: https://leetcode.com/problems/decode-string/description/
 * 
 * Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].

The test cases are generated so that the length of the output will never exceed 105.

 

Example 1:

Input: s = "3[a]2[bc]"
Output: "aaabcbc"
Example 2:

Input: s = "3[a2[c]]"
Output: "accaccacc"
Example 3:

Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"
 

Constraints:

1 <= s.length <= 30
s consists of lowercase English letters, digits, and square brackets '[]'.
s is guaranteed to be a valid input.
All the integers in s are in the range [1, 300].
 * 
 */
public class DecodeString {
	
	public String decodeString(String s) {

		Stack<String> st = new Stack<>();
		String res = "";
		String numStr = "";

		for (int ind = 0; ind < s.length(); ind++) {
			if (Character.isDigit(s.charAt(ind))) {
				numStr += s.charAt(ind);
			} else if (s.charAt(ind) == '[') {
				st.push(numStr);
				numStr = "";
			} else if (s.charAt(ind) == ']') {
				String r = "";
				while (!st.peek().matches("\\d+")) {
					r += st.pop();
				}
				r = new StringBuilder(r).reverse().toString();
				Integer amount = Integer.valueOf(st.pop());
				if (st.isEmpty()) {
					res += r.repeat(amount);
				} else {
					st.push(new StringBuilder(r.repeat(amount)).reverse().toString());
				}
			} else if (st.isEmpty()) {
				res += s.charAt(ind);
			} else {
				st.push(Character.toString(s.charAt(ind)));
			}
		}

		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
