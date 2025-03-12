package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Url: https://leetcode.com/problems/3sum/description/
 * 
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.

 

Example 1:

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Explanation: 
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].
Notice that the order of the output and the order of the triplets does not matter.
Example 2:

Input: nums = [0,1,1]
Output: []
Explanation: The only possible triplet does not sum up to 0.
Example 3:

Input: nums = [0,0,0]
Output: [[0,0,0]]
Explanation: The only possible triplet sums up to 0.
 

Constraints:

3 <= nums.length <= 3000
-105 <= nums[i] <= 105
 */
public class Three3Sum {

	String sortJoin(int a, int b, int c) {
		// Bubble sort
		if (a > b) {
			int temp = a;
			a = b;
			b = temp;
		}
		if (b > c) {
			int temp = b;
			b = c;
			c = temp;
		}
		if (a > b) {
			int temp = a;
			a = b;
			b = temp;
		}

		return a + "-" + b + "-" + c;
	}

	public List<List<Integer>> threeSum1(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		Set<String> isDup = new HashSet<>();

		Map<Integer, Integer> storeNums = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			storeNums.put(nums[i], storeNums.getOrDefault(nums[i], 0) + 1);
		}

		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				int numK = nums[i] + nums[j];
				if (storeNums.containsKey(-numK)) {
					if (-numK == nums[i] && -numK == nums[j] && storeNums.get(-numK) < 3)
						continue;
					else if ((-numK == nums[i] || -numK == nums[j]) && storeNums.get(-numK) == 1)
						continue;
					String join = sortJoin(nums[i], nums[j], -numK);
					// System.out.println(join);
					if (!isDup.contains(join)) {
						isDup.add(join);
						res.add(List.of(nums[i], nums[j], -numK));
					}
				}
			}
		}

		return res;
	}

	public int binarySearch(int a, int b, int val, int[] nums) {
		int m = (a + b) / 2;
		if (nums[m] == val) {
			return m;
		}
		if (a >= b)
			return -1;

		if (nums[m] < val) {
			return binarySearch(m + 1, b, val, nums);
		} else {
			return binarySearch(a, m - 1, val, nums);
		}
	}

	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		int nL = nums.length;
		Arrays.sort(nums);

		Set<String> isDup = new HashSet<>();
		for (int i = 0; i < nL; i++) {
			for (int j = i + 1; j < nL; j++) {
				int numK = nums[i] + nums[j];
				int p = binarySearch(0, nL - 1, -numK, nums);
				if (p > -1 && p != i && p != j) {
					String join = sortJoin(nums[i], nums[j], -numK);
					if (!isDup.contains(join)) {
						isDup.add(join);
						res.add(List.of(nums[i], nums[j], -numK));
					}
				}
			}
		}

		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
