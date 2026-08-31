package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * url: https://leetcode.com/problems/find-the-largest-almost-missing-integer/?envType=daily-question&envId=2026-08-18
 *
 * You are given an integer array nums and an integer k.

An integer x is almost missing from nums if x appears in exactly one subarray of size k within nums.

Return the largest almost missing integer from nums. If no such integer exists, return -1.

A subarray is a contiguous sequence of elements within an array.
 

Example 1:

Input: nums = [3,9,2,1,7], k = 3

Output: 7

Explanation:

1 appears in 2 subarrays of size 3: [9, 2, 1] and [2, 1, 7].
2 appears in 3 subarrays of size 3: [3, 9, 2], [9, 2, 1], [2, 1, 7].
3 appears in 1 subarray of size 3: [3, 9, 2].
7 appears in 1 subarray of size 3: [2, 1, 7].
9 appears in 2 subarrays of size 3: [3, 9, 2], and [9, 2, 1].
We return 7 since it is the largest integer that appears in exactly one subarray of size k.

Example 2:

Input: nums = [3,9,7,2,1,7], k = 4

Output: 3

Explanation:

1 appears in 2 subarrays of size 4: [9, 7, 2, 1], [7, 2, 1, 7].
2 appears in 3 subarrays of size 4: [3, 9, 7, 2], [9, 7, 2, 1], [7, 2, 1, 7].
3 appears in 1 subarray of size 4: [3, 9, 7, 2].
7 appears in 3 subarrays of size 4: [3, 9, 7, 2], [9, 7, 2, 1], [7, 2, 1, 7].
9 appears in 2 subarrays of size 4: [3, 9, 7, 2], [9, 7, 2, 1].
We return 3 since it is the largest and only integer that appears in exactly one subarray of size k.

Example 3:

Input: nums = [0,0], k = 1

Output: -1

Explanation:

There is no integer that appears in only one subarray of size 1.

 

Constraints:

1 <= nums.length <= 50
0 <= nums[i] <= 50
1 <= k <= nums.length
 */
public class Find_the_Largest_Almost_Missing_Integer_3471 {

	public int largestInteger(int[] nums, int k) {
		int lam = -1;
		List<Integer[]> subArr = new ArrayList<>();
		for (int ind = 0; ind <= nums.length - k; ind++) {
			Integer[] arr = new Integer[k];
			for (int j = ind; j < ind + k; j++) {
				arr[j - ind] = nums[j];
			}
			subArr.add(arr);
		}

		Map<Integer, Integer> countA = new HashMap<>();

		Set<Integer> s = Arrays.stream(nums).boxed().collect(Collectors.toSet());

		int[] uS = s.stream().mapToInt(Integer::intValue).toArray();

		for (int ind = 0; ind < uS.length; ind++) {
			for (int i = 0; i < subArr.size(); i++) {
				for (int j = 0; j < k; j++) {
					if (uS[ind] == subArr.get(i)[j]) {
						countA.put(uS[ind], countA.getOrDefault(uS[ind], 0) + 1);
						break;
					}
				}
			}
		}

		for (Map.Entry<Integer, Integer> entry : countA.entrySet()) {
			if (entry.getValue() == 1) {
				lam = Math.max(lam, entry.getKey());
			}
		}

		return lam;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
