package LeetCode;

/**
 * url: https://leetcode.com/problems/rotated-digits/description/?envType=daily-question&envId=2026-05-02
 * 
 * An integer x is a good if after rotating each digit individually by 180 degrees, we get a valid number that is different from x. Each digit must be rotated - we cannot choose to leave it alone.

A number is valid if each digit remains a digit after rotation. For example:

0, 1, and 8 rotate to themselves,
2 and 5 rotate to each other (in this case they are rotated in a different direction, in other words, 2 or 5 gets mirrored),
6 and 9 rotate to each other, and
the rest of the numbers do not rotate to any other number and become invalid.
Given an integer n, return the number of good integers in the range [1, n].

 

Example 1:

Input: n = 10
Output: 4
Explanation: There are four good numbers in the range [1, 10] : 2, 5, 6, 9.
Note that 1 and 10 are not good numbers, since they remain unchanged after rotating.
Example 2:

Input: n = 1
Output: 0
Example 3:

Input: n = 2
Output: 1
 

Constraints:

1 <= n <= 104
 */
public class Rotated_Digits_788 {

	public boolean isGoodNumber(int number) {
		int val = 0;
		int pow = 0;
		int num = number;
		while (num > 0) {
			int t = num % 10;

			switch (t) {
			case 0, 1, 8 -> {
				val += t * (int) Math.pow(10, pow);
			}
			case 2 -> {
				val += 5 * (int) Math.pow(10, pow);
			}
			case 5 -> {
				val += 2 * (int) Math.pow(10, pow);
			}
			case 6 -> {
				val += 9 * (int) Math.pow(10, pow);
			}
			case 9 -> {
				val += 6 * (int) Math.pow(10, pow);
			}
			default -> {
				return false;
			}
			}
			pow++;
			num = num / 10;
		}
		if (val == number)
			return false;

		return true;
	}

	public int rotatedDigits(int n) {
		int res = 0;

		for (int num = 1; num <= n; num++) {
			if (isGoodNumber(num)) {
				res++;
			}
		}

		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
