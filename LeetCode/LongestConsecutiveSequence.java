package LeetCode;

import java.util.HashSet;
import java.util.Set;

/**
 * Url: https://leetcode.com/problems/longest-consecutive-sequence/description/
 * 
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

You must write an algorithm that runs in O(n) time.

 

Example 1:

Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
Example 2:

Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9
Example 3:

Input: nums = [1,0,1,2]
Output: 3
 

Constraints:

0 <= nums.length <= 105
-109 <= nums[i] <= 109
 */
public class LongestConsecutiveSequence {
	
	public int longestConsecutive(int[] nums) {

		int maxA = 0;
		Set<Integer> a = new HashSet<>();
		for (int ind = 0; ind < nums.length; ind++) {
			a.add(nums[ind]);
		}

		Set<Integer> isVisited = new HashSet<>();
		for (int ind = 0; ind < nums.length; ind++) {
			if (isVisited.contains(nums[ind]))
				continue;

			int mT = 1;
			isVisited.add(nums[ind]);
			int n = nums[ind];
			while (a.contains(n + 1)) {
				isVisited.add(n + 1);
				n++;
				mT++;
			}
			n = nums[ind];
			while (a.contains(n - 1)) {
				isVisited.add(n - 1);
				n--;
				mT++;
			}
			if (maxA < mT)
				maxA = mT;
		}

		return maxA;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
