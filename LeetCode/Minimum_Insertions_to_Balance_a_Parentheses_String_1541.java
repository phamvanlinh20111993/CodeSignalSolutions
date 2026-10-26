package LeetCode;

/**
 * url: https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string/?envType=daily-question&envId=2026-10-09
 * 
 * Given a parentheses string s containing only the characters '(' and ')'. A parentheses string is balanced if:

Any left parenthesis '(' must have a corresponding two consecutive right parenthesis '))'.
Left parenthesis '(' must go before the corresponding two consecutive right parenthesis '))'.
In other words, we treat '(' as an opening parenthesis and '))' as a closing parenthesis.

For example, "())", "())(())))" and "(())())))" are balanced, ")()", "()))" and "(()))" are not balanced.
You can insert the characters '(' and ')' at any position of the string to balance it if needed.

Return the minimum number of insertions needed to make s balanced.

 

Example 1:

Input: s = "(()))"
Output: 1
Explanation: The second '(' has two matching '))', but the first '(' has only ')' matching. We need to add one more ')' at the end of the string to be "(())))" which is balanced.
Example 2:

Input: s = "())"
Output: 0
Explanation: The string is already balanced.
Example 3:

Input: s = "))())("
Output: 3
Explanation: Add '(' to match the first '))', Add '))' to match the last '('.
 

Constraints:

1 <= s.length <= 105
s consists of '(' and ')' only.

 */
public class Minimum_Insertions_to_Balance_a_Parentheses_String_1541 {

	public int minInsertions(String s) {
		int l = s.length();
		int stack = 0;
		int amount = 0;
		for (int i = 0; i < l; i++) {
			if (s.charAt(i) == '(') {
				if (stack % 2 != 0) {
					amount += 1;
					stack--;
				}
				stack += 2;
			} else {
				if (stack < 1) {
					stack += 2;
					amount++;
				}
				stack--;
			}
		}

		amount += Math.abs(stack);

		return amount;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
