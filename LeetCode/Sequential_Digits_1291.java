package LeetCode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * url: https://leetcode.com/problems/sequential-digits/?envType=daily-question&envId=2026-07-13
 * 
 * An integer has sequential digits if and only if each digit in the number is one more than the previous digit.

Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.

 

Example 1:

Input: low = 100, high = 300
Output: [123,234]
Example 2:

Input: low = 1000, high = 13000
Output: [1234,2345,3456,4567,5678,6789,12345]
 

Constraints:

10 <= low <= high <= 10^9
 * 
 */
public class Sequential_Digits_1291 {

	private static List<Integer> allNums = new ArrayList<>();
	private static Integer MAX_NUM = 1000000000;

	static {
		for (int ind = 1; ind < 10; ind++) {
			allNums.addAll(recursive(ind, ind));
		}
		allNums.sort(Comparator.naturalOrder());
	}

	public static List<Integer> recursive(int start, Integer nextNum) {
		if (nextNum > MAX_NUM || start + 1 > 9) {
			return new ArrayList<>();
		}
		List<Integer> nums = new ArrayList<>();
		nums.add(nextNum * 10 + start + 1);
		nums.addAll(recursive(start + 1, nextNum * 10 + start + 1));
		return nums;
	}

	public List<Integer> sequentialDigits(int low, int high) {
		List<Integer> res = new ArrayList<>();
		for (Integer num : allNums) {
			if (num >= low && num <= high) {
				res.add(num);
			}
		}

		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
