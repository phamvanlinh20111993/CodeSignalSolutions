package LeetCode;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * link https://leetcode.com/problems/restore-ip-addresses/description/
 * 
 * A valid IP address consists of exactly four integers separated by single dots. Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.

For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
Given a string s containing only digits, return all possible valid IP addresses that can be formed by inserting dots into s. You are not allowed to reorder or remove any digits in s. You may return the valid IP addresses in any order.

 

Example 1:

Input: s = "25525511135"
Output: ["255.255.11.135","255.255.111.35"]
Example 2:

Input: s = "0000"
Output: ["0.0.0.0"]
Example 3:

Input: s = "101023"
Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 

Constraints:

1 <= s.length <= 20
s consists of digits only.
 */
public class RestoreIPAddresse {
	
	List<String> res = new ArrayList<>();

	public void recursive(int[] c, int currInd, List<String> acc) {
		if (acc.size() == 4 && currInd >= c.length) {
			res.add(String.join(".", acc));
			return;
		}
		int nu = 0;
		for (int ind = currInd; ind < c.length; ind++) {
			nu = nu * 10 + c[ind];
			if (nu > 255)
				break;

			List<String> newAcc = new ArrayList<>(acc);
			newAcc.add(nu + "");

			recursive(c, ind + 1, newAcc);
			if (ind == currInd && c[ind] == 0)
				break;
		}
	}

	public List<String> restoreIpAddresses(String s) {
		int l = s.length();
		if (l < 4)
			return new ArrayList<>();
		int[] c = new int[l];
		for (int ind = 0; ind < l; ind++) {
			c[ind] = s.charAt(ind) - '0';
		}
		recursive(c, 0, new ArrayList<>());
		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
