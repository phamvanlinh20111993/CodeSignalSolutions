package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Url: https://leetcode.com/problems/subsets/description/
 * 
 * Given an integer array nums of unique elements, return all possible 
subsets
 (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

 

Example 1:

Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
Example 2:

Input: nums = [0]
Output: [[],[0]]
 

Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10
All the numbers of nums are unique.
 */
public class Subsets {
	List<List<Integer>> res;
	/*
	public void backtrack(int[] nums, int pos, int length, List<Integer> acc) {
		if (acc.size() >= length) {
			res.add(acc);
			return;
		}
		for (int ind = pos; ind < nums.length; ind++) {
			List<Integer> accT = new ArrayList<>(acc);
			accT.add(nums[ind]);
			backtrack(nums, ind + 1, length, accT);
		}
	}

	public List<List<Integer>> subsets(int[] nums) {
		res = new ArrayList<>();
        res.add(new ArrayList<>());
		for(int ind = 1; ind <= nums.length; ind++) {
			backtrack(nums, 0, ind, new ArrayList<>());
		}
		
		return res;
	} */
	
	public void backtrack(int[] nums, int pos, List<Integer> acc) {
		if (acc.size() <= nums.length) {
			res.add(acc);
		}
		for (int ind = pos; ind < nums.length; ind++) {
			List<Integer> accT = new ArrayList<>(acc);
			accT.add(nums[ind]);
			backtrack(nums, ind + 1, accT);
		}
	}

	public List<List<Integer>> subsets(int[] nums) {
		res = new ArrayList<>();
		backtrack(nums, 0, new ArrayList<>());
		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
