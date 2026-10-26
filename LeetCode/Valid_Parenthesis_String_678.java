package LeetCode;

import java.util.LinkedList;
import java.util.List;

/**
 * url: https://leetcode.com/problems/valid-parenthesis-string/description/?envType=daily-question&envId=2026-10-04
 * 
 * 
 * Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.

The following rules define a valid string:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
 

Example 1:

Input: s = "()"
Output: true
Example 2:

Input: s = "(*)"
Output: true
Example 3:

Input: s = "(*))"
Output: true
Example 4:

Input: s = "("
Output: false
 

Constraints:

1 <= s.length <= 100
s[i] is '(', ')' or '*'.
 */
public class Valid_Parenthesis_String_678 {

	public boolean checkValidString(String s) {
		int l = s.length();

		List<Character> st = new LinkedList<>();
		for (int ind = 0; ind < l; ind++) {
			if (s.charAt(ind) == '(') {
				st.add('(');
			} else if (s.charAt(ind) == ')') {
				boolean isFound = false;
				for (int k = st.size() - 1; k > -1; k--) {
					if (st.get(k) == '(') {
						st.remove(k);
						isFound = true;
						break;
					}
				}
				if (!isFound) {
					st.add(')');
				}
			} else {
				st.add('*');
			}
		}

		for (int ind = 0; ind < st.size();) {
			if (st.get(ind) == ')') {
				if (ind - 1 >= 0 && st.get(ind - 1) == '*') {
					st.remove(ind);
					st.remove(ind - 1);
					ind = ind <= 0 ? ind : ind - 1;
					continue;
				}
				return false;
			} else if (st.get(ind) == '(') {
				boolean isFound = false;
				for (int k = ind + 1; k < st.size(); k++) {
					if (st.get(k) == '*') {
						isFound = true;
						st.remove(k);
						st.remove(ind);
						break;
					}
				}
				if (!isFound) {
					return false;
				}
				ind = ind <= 0 ? ind : ind - 1;
			} else {
				ind++;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
