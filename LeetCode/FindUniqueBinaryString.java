package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Url:
 * https://leetcode.com/problems/find-unique-binary-string/description/?envType=daily-question&envId=2025-02-20
 * 
 * Given an array of strings nums containing n unique binary strings each of length n, return a binary string of length n that does not appear in nums. If there are multiple answers, you may return any of them.

 

Example 1:

Input: nums = ["01","10"]
Output: "11"
Explanation: "11" does not appear in nums. "00" would also be correct.
Example 2:

Input: nums = ["00","01"]
Output: "11"
Explanation: "11" does not appear in nums. "10" would also be correct.
Example 3:

Input: nums = ["111","011","001"]
Output: "101"
Explanation: "101" does not appear in nums. "000", "010", "100", and "110" would also be correct.
 

Constraints:

n == nums.length
1 <= n <= 16
nums[i].length == n
nums[i] is either '0' or '1'.
All the strings of nums are unique.
 * 
 */
public class FindUniqueBinaryString {

	List<String> res;

	public void recursive(String acc, int len) {
		if (acc.length() == len) {
			res.add(acc);
			return;
		}
		recursive(acc + "0", len);
		recursive(acc + "1", len);
	}

	public String findDifferentBinaryString(String[] nums) {
		Set<String> s = new HashSet<>(Arrays.asList(nums));
		res = new ArrayList<>();
		recursive("", nums.length);

		for (String t : res) {
			if (!s.contains(t))
				return t;
		}

		return "";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
