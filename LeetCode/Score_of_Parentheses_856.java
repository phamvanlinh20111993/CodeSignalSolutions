package LeetCode;

/**
 * url: https://leetcode.com/problems/score-of-parentheses/?envType=daily-question&envId=2026-10-05
 * Given a balanced parentheses string s, return the score of the string.

The score of a balanced parentheses string is based on the following rule:

"()" has score 1.
AB has score A + B, where A and B are balanced parentheses strings.
(A) has score 2 * A, where A is a balanced parentheses string.
 

Example 1:

Input: s = "()"
Output: 1
Example 2:

Input: s = "(())"
Output: 2
Example 3:

Input: s = "()()"
Output: 2
 

Constraints:

2 <= s.length <= 50
s consists of only '(' and ')'.
s is a balanced parentheses string.
 */
public class Score_of_Parentheses_856 {

	public int scoreOfParentheses(String s) {
		int l = s.length();
		int level = 0;
		int[] ps = new int[2 + l / 2];

		for (int ind = 0; ind < l; ind++) {
			if (s.charAt(ind) == '(') {
				level++;
				continue;
			}

			if (ps[level + 1] > 0) {
				ps[level] += 2 * ps[level + 1];
				ps[level + 1] = 0;
			} else {
				ps[level] += 1;
			}
			level--;
		}

		return ps[1];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
