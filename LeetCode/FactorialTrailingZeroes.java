package LeetCode;

/**
 * 
 * url: https://leetcode.com/problems/factorial-trailing-zeroes/description/
 * 
 * Given an integer n, return the number of trailing zeroes in n!.

Note that n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1.

 

Example 1:

Input: n = 3
Output: 0
Explanation: 3! = 6, no trailing zero.
Example 2:

Input: n = 5
Output: 1
Explanation: 5! = 120, one trailing zero.
Example 3:

Input: n = 0
Output: 0
 

Constraints:

0 <= n <= 104
 

Follow up: Could you write a solution that works in logarithmic time complexity?
 * 
 * 
 */
public class FactorialTrailingZeroes {
	public int countTrailingZeroes(int ind) {
		int c = 0;
		while (ind % 10 == 0) {
			ind /= 10;
			c++;
		}
		return c;
	}

	public int countDevideFive(int ind) {
		int c = 0;
		while (ind % 5 == 0) {
			ind /= 5;
			c++;
		}

		return c;
	}

	public int trailingZeroes(int n) {
		int amount = 0;
		for (int ind = 4; ind <= n; ind++) {
			if (ind % 5 == 0) {
				amount += countDevideFive(ind);
			} else if (ind % 10 == 0) {
				amount += countTrailingZeroes(ind);
			}
		}
		return amount;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
