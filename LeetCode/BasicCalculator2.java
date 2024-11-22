package LeetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Url: https://leetcode.com/problems/basic-calculator-ii/description/
 * Given a string s which represents an expression, evaluate this expression and return its value. 

The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].

Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().

 

Example 1:

Input: s = "3+2*2"
Output: 7
Example 2:

Input: s = " 3/2 "
Output: 1
Example 3:

Input: s = " 3+5 / 2 "
Output: 5
 

Constraints:

1 <= s.length <= 3 * 105
s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
s represents a valid expression.
All the integers in the expression are non-negative integers in the range [0, 231 - 1].
The answer is guaranteed to fit in a 32-bit integer.
 */
public class BasicCalculator2 {
	
	public int calculate(String s) {
		List<String> nums = new ArrayList<>();
		String num = "";
		for (int ind = 0; ind < s.length(); ind++) {
			if (s.charAt(ind) == ' ')
				continue;
			if (s.charAt(ind) == '+' || s.charAt(ind) == '-' || s.charAt(ind) == '*' || s.charAt(ind) == '/') {
				if (num != "")
					nums.add(num);
				nums.add(String.valueOf(s.charAt(ind)));
				num = "";
			} else {
				num += String.valueOf(s.charAt(ind));
			}
		}
		if (num != "")
			nums.add(num);

		for (int ind = 0; ind < nums.size(); ind++) {
			if (nums.get(ind).equals("*") || nums.get(ind).equals("/")) {
				String value = nums.get(ind).equals("*")
						? String.valueOf(Integer.valueOf(nums.get(ind - 1)) * Integer.valueOf(nums.get(ind + 1)))
						: String.valueOf(Integer.valueOf(nums.get(ind - 1)) / Integer.valueOf(nums.get(ind + 1)));
				nums.set(ind + 1, value);
				nums.set(ind, "");
				nums.set(ind - 1, "");
			}
		}
		nums = nums.stream().filter(v -> !v.equals("")).collect(Collectors.toList());

		int res = Integer.valueOf(nums.get(0));
		for (int ind = 0; ind < nums.size(); ind++) {
			if (nums.get(ind).equals("+")) {
				res += Integer.valueOf(nums.get(ind + 1));
			} else if (nums.get(ind).equals("-")) {
				res -= Integer.valueOf(nums.get(ind + 1));
			}
		}
		return res;
	}

	public static void main(String[] args) {
		

	}

}
