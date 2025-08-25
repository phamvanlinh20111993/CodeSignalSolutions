package LeetCode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * url: https://leetcode.com/problems/reordered-power-of-2/description/?envType=daily-question&envId=2025-08-10
 * 
 * You are given an integer n. We reorder the digits in any order (including the original order) such that the leading digit is not zero.

Return true if and only if we can do this so that the resulting number is a power of two.

 

Example 1:

Input: n = 1
Output: true
Example 2:

Input: n = 10
Output: false
 

Constraints:

1 <= n <= 109
 */
public class ReorderedPowerOf2 {
	
	public boolean reorderedPowerOf2(int n) {
		List<Map<Character, Integer>> allPowerOfTwo = new ArrayList<>();

		for (int ind = 0; ind < 32; ind++) {
			int v = (int) Math.pow(2, ind);
			char[] chars = String.valueOf(v).toCharArray();
			Map<Character, Integer> store = new HashMap<>();
			for (int p = 0; p < chars.length; p++) {
				store.put(chars[p], store.getOrDefault(chars[p], 0) + 1);
			}
			allPowerOfTwo.add(store);
		}

		char[] nChr = String.valueOf(n).toCharArray();
		for (int ind = 0; ind < allPowerOfTwo.size(); ind++) {
			Map<Character, Integer> curPower = allPowerOfTwo.get(ind);
			boolean isMatch = true;
			for (int p = 0; p < nChr.length; p++) {
				if (!curPower.containsKey(nChr[p])) {
					isMatch = false;
					break;
				}
				curPower.put(nChr[p], curPower.get(nChr[p]) - 1);
			}

			Collection<Integer> curPowerV = curPower.values();
			for (Integer val : curPowerV) {
				if (val != 0) {
					isMatch = false;
				}
			}

			if (isMatch)
				return true;
		}

		return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
