package LeetCode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * https://leetcode.com/problems/minimum-swaps-to-sort-by-digit-sum/description/?envType=daily-question&envId=2026-10-17
 * 
 * You are given an array nums of distinct positive integers. You need to sort the array in increasing order based on the sum of the digits of each number. If two numbers have the same digit sum, the smaller number appears first in the sorted order.

Return the minimum number of swaps required to rearrange nums into this sorted order.

A swap is defined as exchanging the values at two distinct positions in the array.

 

Example 1:

Input: nums = [37,100]

Output: 1

Explanation:

Compute the digit sum for each integer: [3 + 7 = 10, 1 + 0 + 0 = 1] → [10, 1]
Sort the integers based on digit sum: [100, 37]. Swap 37 with 100 to obtain the sorted order.
Thus, the minimum number of swaps required to rearrange nums is 1.
Example 2:

Input: nums = [22,14,33,7]

Output: 0

Explanation:

Compute the digit sum for each integer: [2 + 2 = 4, 1 + 4 = 5, 3 + 3 = 6, 7 = 7] → [4, 5, 6, 7]
Sort the integers based on digit sum: [22, 14, 33, 7]. The array is already sorted.
Thus, the minimum number of swaps required to rearrange nums is 0.
Example 3:

Input: nums = [18,43,34,16]

Output: 2

Explanation:

Compute the digit sum for each integer: [1 + 8 = 9, 4 + 3 = 7, 3 + 4 = 7, 1 + 6 = 7] → [9, 7, 7, 7]
Sort the integers based on digit sum: [16, 34, 43, 18]. Swap 18 with 16, and swap 43 with 34 to obtain the sorted order.
Thus, the minimum number of swaps required to rearrange nums is 2.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 109
nums consists of distinct positive integers.
 * 
 */
public class Minimum_Swaps_to_Sort_by_Digit_Sum_3551 {

	class KV {
		int n;
		int i;
		int s;

		public KV(int n, int i, int s) {
			this.n = n;
			this.i = i;
			this.s = s;
		}

		@Override
		public String toString() {
			return "KV{" + "n=" + n + ", ind=" + i + ", sumDigit=" + s + '}';
		}
	}

	public int digitSum(int num) {
		int s = 0;
		while (num > 0) {
			s += num % 10;
			num /= 10;
		}
		return s;
	}

	public int minSwaps(int[] nums) {
		int min = 0;
		int l = nums.length;
		KV[] st = new KV[l];

		Map<Integer, KV> m = new HashMap<>();
		for (int ind = 0; ind < l; ind++) {
			st[ind] = new KV(nums[ind], ind, digitSum(nums[ind]));
			m.put(nums[ind], st[ind]);
		}

		Arrays.sort(st, new Comparator<KV>() {
			@Override
			public int compare(KV k1, KV k2) {
				if (k1.s == k2.s) {
					return k1.n - k2.n;
				}
				return k1.s - k2.s;
			}
		});

		for (int ind = l - 1; ind > -1; ind--) {
			KV kv = st[ind];
			if (kv.n == nums[ind]) {
				continue;
			}
			KV rp = m.get(nums[ind]);
			int t = nums[kv.i];
			nums[kv.i] = rp.n;
			nums[rp.i] = t;
			rp.i = kv.i;
			min++;
		}

		return min;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
