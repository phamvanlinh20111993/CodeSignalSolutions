package LeetCode;

/**
 * url: https://leetcode.com/problems/smallest-stable-index-ii/description/
 * 
 * You are given an integer array nums of length n and an integer k.

For each index i, define its instability score as max(nums[0..i]) - min(nums[i..n - 1]).

In other words:

max(nums[0..i]) is the largest value among the elements from index 0 to index i.
min(nums[i..n - 1]) is the smallest value among the elements from index i to index n - 1.
An index i is called stable if its instability score is less than or equal to k.

Return the smallest stable index. If no such index exists, return -1.

 

Example 1:

Input: nums = [5,0,1,4], k = 3

Output: 3

Explanation:

At index 0: The maximum in [5] is 5, and the minimum in [5, 0, 1, 4] is 0, so the instability score is 5 - 0 = 5.
At index 1: The maximum in [5, 0] is 5, and the minimum in [0, 1, 4] is 0, so the instability score is 5 - 0 = 5.
At index 2: The maximum in [5, 0, 1] is 5, and the minimum in [1, 4] is 1, so the instability score is 5 - 1 = 4.
At index 3: The maximum in [5, 0, 1, 4] is 5, and the minimum in [4] is 4, so the instability score is 5 - 4 = 1.
This is the first index with an instability score less than or equal to k = 3. Thus, the answer is 3.
Example 2:

Input: nums = [3,2,1], k = 1

Output: -1

Explanation:

At index 0, the instability score is 3 - 1 = 2.
At index 1, the instability score is 3 - 1 = 2.
At index 2, the instability score is 3 - 1 = 2.
None of these values is less than or equal to k = 1, so the answer is -1.
Example 3:

Input: nums = [0], k = 0

Output: 0

Explanation:

At index 0, the instability score is 0 - 0 = 0, which is less than or equal to k = 0. Therefore, the answer is 0.

 

Constraints:

1 <= nums.length <= 105
0 <= nums[i] <= 109
0 <= k <= 109

 */
public class Smallest_Stable_Index_II_3904 {

	class KV {
		int max;
		int min;

		public KV(int max) {
			this.max = max;
		}

		public KV(int max, int min) {
			this.max = max;
			this.min = min;
		}

		@Override
		public String toString() {
			return "KV{" + "max=" + max + ", min=" + min + '}';
		}
	}

	public int firstStableIndex(int[] nums, int k) {
		int l = nums.length;

		int min = 1000000000;
		int max = -min;
		KV[] kv = new KV[l];
		for (int ind = 0; ind < l; ind++) {
			max = Math.max(nums[ind], max);
			kv[ind] = new KV(max);
		}

		for (int ind = l - 1; ind > -1; ind--) {
			min = Math.min(nums[ind], min);
			kv[ind].min = min;
		}

		for (int ind = 0; ind < l; ind++) {
			// System.out.println(kv[ind]);
			if (kv[ind].max - kv[ind].min <= k) {
				return ind;
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
