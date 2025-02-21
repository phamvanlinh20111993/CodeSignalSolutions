package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Url: https://leetcode.com/problems/subsets-ii/description/
 * 
 * Given an integer array nums that may contain duplicates, return all possible 
subsets
 (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

 

Example 1:

Input: nums = [1,2,2]
Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
Example 2:

Input: nums = [0]
Output: [[],[0]]
 

Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10

 */
public class SubsetsII {

	Map<String, List<Integer>> res;

	public void backtrack(int[] nums, int pos, List<Integer> acc) {

		if (acc.size() > 0) {
			res.putIfAbsent(acc.stream().sorted().map(String::valueOf).collect(Collectors.joining("-")), acc);
		}

		for (int i = pos; i < nums.length; i++) {
			List<Integer> t = new ArrayList<>(acc);
			t.add(nums[i]);
			backtrack(nums, i + 1, t);
		}
	}

	public List<List<Integer>> subsetsWithDup(int[] nums) {
		res = new HashMap<>();
		backtrack(nums, 0, new ArrayList<>());
		List<List<Integer>> list = new ArrayList<>(res.values());
		list.add(new ArrayList<>());
		return list;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
