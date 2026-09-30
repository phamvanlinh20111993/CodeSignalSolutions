package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * url: https://leetcode.com/problems/construct-uniform-parity-array-ii/?envType=daily-question&envId=2026-09-03
 * 
 * You are given an array nums1 of n distinct integers.

You want to construct another array nums2 of length n such that the elements in nums2 are either all odd or all even.

For each index i, you must choose exactly one of the following (in any order):

nums2[i] = nums1[i]​​​​​​​
nums2[i] = nums1[i] - nums1[j], for an index j != i, such that nums1[i] - nums1[j] >= 1
Return true if it is possible to construct such an array, otherwise return false.

 

Example 1:

Input: nums1 = [1,4,7]

Output: true

Explanation:​​​​​​​​​​​​​​

Set nums2[0] = nums1[0] = 1.
Set nums2[1] = nums1[1] - nums1[0] = 4 - 1 = 3.
Set nums2[2] = nums1[2] = 7.
nums2 = [1, 3, 7], and all elements are odd. Thus, the answer is true.
Example 2:

Input: nums1 = [2,3]

Output: false

Explanation:

It is not possible to construct nums2 such that all elements have the same parity. Thus, the answer is false.

Example 3:

Input: nums1 = [4,6]

Output: true

Explanation:

Set nums2[0] = nums1[0] = 4.
Set nums2[1] = nums1[1] = 6.
nums2 = [4, 6], and all elements are even. Thus, the answer is true.
 

Constraints:

1 <= n == nums1.length <= 105
1 <= nums1[i] <= 109
nums1 consists of distinct integers.
 */
public class Construct_Uniform_Parity_Array_II_3876 {

	public boolean uniformArray1(int[] nums1) {
		int l = nums1.length;

		PriorityQueue<Integer> pqOdd = new PriorityQueue<Integer>();
		List<Integer> odds = new ArrayList<>();
		PriorityQueue<Integer> pqEven = new PriorityQueue<Integer>();
		List<Integer> evens = new ArrayList<>();

		for (int ind = 0; ind < l; ind++) {
			if (nums1[ind] % 2 == 0) {
				pqEven.add(nums1[ind]);
				continue;
			}
			pqOdd.add(nums1[ind]);
		}
		while (!pqEven.isEmpty())
			evens.add(pqEven.poll());
		while (!pqOdd.isEmpty())
			odds.add(pqOdd.poll());

		// choose odds data
		int lOdd = odds.size();
		boolean canOdd = true;
		for (int j = 0; j < l; j++) {
			if (nums1[j] % 2 != 0)
				continue;
			if (lOdd > 0 && nums1[j] - odds.get(0) >= 1)
				continue;
			canOdd = false;
			break;
		}

		boolean canEven = true;
		for (int j = 0; j < l; j++) {
			if (nums1[j] % 2 == 0)
				continue;
			if (lOdd > 0 && nums1[j] - odds.get(0) >= 1)
				continue;
			canEven = false;
			break;
		}

		// System.out.println(canOdd + " " + canEven);

		return canOdd || canEven;
	}

	public boolean uniformArray(int[] nums1) {
		int l = nums1.length;
		int minOdds = 1000000000;
		for (int ind = 0; ind < l; ind++) {
			if (nums1[ind] % 2 != 0 && minOdds > nums1[ind]) {
				minOdds = nums1[ind];
			}
		}

		// choose odds data
		boolean canOdd = true;
		for (int j = 0; j < l; j++) {
			if (nums1[j] % 2 != 0)
				continue;
			if (nums1[j] - minOdds >= 1)
				continue;
			canOdd = false;
			break;
		}

		boolean canEven = true;
		for (int j = 0; j < l; j++) {
			if (nums1[j] % 2 == 0)
				continue;
			if (nums1[j] - minOdds >= 1)
				continue;
			canEven = false;
			break;
		}

		return canOdd || canEven;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
