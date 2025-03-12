package LeetCode;

/**
 * Url:https://leetcode.com/problems/check-if-number-is-a-sum-of-powers-of-three/description/ 	
 * 
 * Given an integer n, return true if it is possible to represent n as the sum of distinct powers of three. Otherwise, return false.

An integer y is a power of three if there exists an integer x such that y == 3x.

 

Example 1:

Input: n = 12
Output: true
Explanation: 12 = 31 + 32
Example 2:

Input: n = 91
Output: true
Explanation: 91 = 30 + 32 + 34
Example 3:

Input: n = 21
Output: false
 

Constraints:

1 <= n <= 107

 */
public class CheckifNumberisaSumofPowersofThree {

	public boolean isPowOfThree(int pos, int n) {
		if (n == 0)
			return true;

		boolean isPow = false;
		for (int p = pos; p > -1; p--) {
			int powOfThree = (int) Math.pow(3, p);
			if (n - powOfThree >= 0) {
				isPow = isPowOfThree(p - 1, n - powOfThree);
				if (isPow)
					break;
			}
		}

		return isPow;
	}

	public boolean checkPowersOfThree(int n) {
		return isPowOfThree(16, n);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
