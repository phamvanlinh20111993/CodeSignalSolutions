package LeetCode;

import java.util.Stack;

/**
 * Url: https://leetcode.com/problems/basic-calculator/description/
 * 
 * Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.

Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().

 

Example 1:

Input: s = "1 + 1"
Output: 2
Example 2:

Input: s = " 2-1 + 2 "
Output: 3
Example 3:

Input: s = "(1+(4+5+2)-3)+(6+8)"
Output: 23
 

Constraints:

1 <= s.length <= 3 * 105
s consists of digits, '+', '-', '(', ')', and ' '.
s represents a valid expression.
'+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
'-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
There will be no two consecutive operators in the input.
Every number and running calculation will fit in a signed 32-bit integer.
 */
public class BasicCalculator {

	public int calc(int res, String op, String num) {
		// when num = --2 or --3, return 2 or 3 respectly
		if (num.length() > 1 && num.charAt(0) == '-' && num.charAt(1) == '-')
			num = num.substring(2);

		return op.equals("+") ? res + Integer.valueOf(num) : res - Integer.valueOf(num);
	}

	public String calcSingleExpression(Stack<String> singleExpression) {
		int res = 0;
		String num = "", op = "+";

		while (!singleExpression.empty()) {
			String v = singleExpression.pop();
			if (v.equals("-")) {
				// is a negative integer
				if (num.equals("")) {
					num += v;
				} else {
					res = calc(res, op, num);
					op = "-";
					num = "";
				}
			} else if (v.equals("+")) {
				res = calc(res, op, num);
				op = "+";
				num = "";
			} else {
				num += v;
			}
		}

		if (!num.equals(""))
			res = calc(res, op, num);
		return String.valueOf(res);
	}

	public int calculate(String s) {
		Stack<String> stack = new Stack<>();
		int ind = 0;
		while (ind < s.length()) {
			if (s.charAt(ind) == '(') {
				stack.push(String.valueOf(s.charAt(ind)));
			} else if (s.charAt(ind) == ')') {
				Stack<String> strExpression = new Stack<>();
				while (!stack.isEmpty() && !stack.peek().equals("(")) {
					String v = stack.pop();
					if (v.equals(" "))
						continue;
					strExpression.push(v);
				}
				// remove ')' char
				stack.pop();
				// re-add value
				stack.push(calcSingleExpression(strExpression));
			} else {
				stack.push(String.valueOf(s.charAt(ind)));
			}
			ind++;
		}

		// final call
		Stack<String> strExpression = new Stack<>();
		while (!stack.isEmpty()) {
			String v = stack.pop();
			if (v.equals(" "))
				continue;
			strExpression.push(v);
		}

		return Integer.valueOf(calcSingleExpression(strExpression));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
