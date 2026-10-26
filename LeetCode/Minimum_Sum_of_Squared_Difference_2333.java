package LeetCode;

import java.util.Arrays;

/**
 * url: https://leetcode.com/problems/minimum-sum-of-squared-difference/?envType=daily-question&envId=2026-10-10
 * 
 * You are given two positive 0-indexed integer arrays nums1 and nums2, both of length n.

The sum of squared difference of arrays nums1 and nums2 is defined as the sum of (nums1[i] - nums2[i])2 for each 0 <= i < n.

You are also given two positive integers k1 and k2. You can modify any of the elements of nums1 by +1 or -1 at most k1 times. Similarly, you can modify any of the elements of nums2 by +1 or -1 at most k2 times.

Return the minimum sum of squared difference after modifying array nums1 at most k1 times and modifying array nums2 at most k2 times.

Note: You are allowed to modify the array elements to become negative integers.

 

Example 1:

Input: nums1 = [1,2,3,4], nums2 = [2,10,20,19], k1 = 0, k2 = 0
Output: 579
Explanation: The elements in nums1 and nums2 cannot be modified because k1 = 0 and k2 = 0. 
The sum of square difference will be: (1 - 2)2 + (2 - 10)2 + (3 - 20)2 + (4 - 19)2 = 579.
Example 2:

Input: nums1 = [1,4,10,12], nums2 = [5,8,6,9], k1 = 1, k2 = 1
Output: 43
Explanation: One way to obtain the minimum sum of square difference is: 
- Increase nums1[0] once.
- Increase nums2[2] once.
The minimum of the sum of square difference will be: 
(2 - 5)2 + (4 - 8)2 + (10 - 7)2 + (12 - 9)2 = 43.
Note that, there are other ways to obtain the minimum of the sum of square difference, but there is no way to obtain a sum smaller than 43.
 

Constraints:

n == nums1.length == nums2.length
1 <= n <= 105
0 <= nums1[i], nums2[i] <= 105
0 <= k1, k2 <= 109
 * 
 */

public class Minimum_Sum_of_Squared_Difference_2333 {

	record R(int index, long amount) {
	}

	public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
		int l = nums1.length;
		long[] minusNums = new long[l];
		long total = 0;
		for (int ind = 0; ind < l; ind++) {
			minusNums[ind] = Math.abs(nums1[ind] - nums2[ind]);
			total += minusNums[ind];
		}
		if (total <= k1 + k2)
			return 0;
		Arrays.sort(minusNums);
		int sumK = k1 + k2;
		R diff = null;
		long sum = 0;
		for (int ind = l - 1; ind >= 0;) {
			int t = ind - 1;
			while (t > 0 && minusNums[t] == minusNums[ind]) {
				t--;
			}
			long next = t >= 0 ? minusNums[t] : 0;
			long maxS = (l - t - 1) * (minusNums[ind] - next);
			diff = new R(t + 1, maxS);
			sum += maxS;
			if (sum > sumK)
				break;
			ind = t;
			if (ind < 0)
				break;
		}

		long allowSum = diff.amount - (sum - k1 - k2);
		long minus = allowSum / (l - diff.index);
		long mod = allowSum % (l - diff.index);
		long targetSum = minusNums[diff.index];
		
		for (int ind = diff.index; ind < l; ind++) {
			minusNums[ind] = targetSum - minus - (mod-- > 0 ? 1 : 0);
		}

		long res = 0L;
		for (int ind = 0; ind < l; ind++) {
			res += minusNums[ind] * minusNums[ind];
		}

		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
