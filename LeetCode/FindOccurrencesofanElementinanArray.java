package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are given an integer array nums, an integer array queries, and an integer x.

For each queries[i], you need to find the index of the queries[i]th occurrence of x in the nums array. If there are fewer than queries[i] occurrences of x, the answer should be -1 for that query.

Return an integer array answer containing the answers to all queries.

 

Example 1:

Input: nums = [1,3,1,7], queries = [1,3,2,4], x = 1

Output: [0,-1,2,-1]

Explanation:

For the 1st query, the first occurrence of 1 is at index 0.
For the 2nd query, there are only two occurrences of 1 in nums, so the answer is -1.
For the 3rd query, the second occurrence of 1 is at index 2.
For the 4th query, there are only two occurrences of 1 in nums, so the answer is -1.
Example 2:

Input: nums = [1,2,3], queries = [10], x = 5

Output: [-1]

Explanation:

For the 1st query, 5 doesn't exist in nums, so the answer is -1.
 

Constraints:

1 <= nums.length, queries.length <= 105
1 <= queries[i] <= 105
1 <= nums[i], x <= 104
 */
public class FindOccurrencesofanElementinanArray {
	
	public int[] occurrencesOfElement(int[] nums, int[] queries, int x) {
		Map<Integer, List<Integer>> occurrences = new HashMap<>();
		for (int ind = 0; ind < nums.length; ind++) {
			List<Integer> indexes = occurrences.getOrDefault(nums[ind], new ArrayList<Integer>());
			indexes.add(ind);
			occurrences.put(nums[ind], indexes);
		}

		int[] res = new int[queries.length];
		for (int ind = 0; ind < queries.length; ind++) {
			if (occurrences.containsKey(x)) {
				List<Integer> indexes = occurrences.getOrDefault(x, new ArrayList<Integer>());
				if (queries[ind] > indexes.size()) {
					res[ind] = -1;
				} else {
					res[ind] = indexes.get(queries[ind] - 1);
				}
			} else {
				res[ind] = -1;
			}
		}

		return res;
	}
	
	public static void main(String[] args) {
		

	}

}
