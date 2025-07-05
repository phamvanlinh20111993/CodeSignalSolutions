package LeetCode;

/**
 * url: https://leetcode.com/problems/sort-an-array/description/?envType=problem-list-v2&envId=merge-sort
 * Given an array of integers nums, sort the array in ascending order and return it.

You must solve the problem without using any built-in functions in O(nlog(n)) time complexity and with the smallest space complexity possible.

 

Example 1:

Input: nums = [5,2,3,1]
Output: [1,2,3,5]
Explanation: After sorting the array, the positions of some numbers are not changed (for example, 2 and 3), while the positions of other numbers are changed (for example, 1 and 5).
Example 2:

Input: nums = [5,1,1,2,0,0]
Output: [0,0,1,1,2,5]
Explanation: Note that the values of nums are not necessarily unique.
 

Constraints:

1 <= nums.length <= 5 * 104
-5 * 104 <= nums[i] <= 5 * 104
 */
public class Sort_an_Array {
	
	void merge(int[] nums, int l, int m, int r) {
		int[] temp = new int[r - l + 1];
		int i = l, j = m + 1; // left arr [l, m], right arr [m+1, r]
		int t = 0;
		while (i <= m && j <= r) {
			if (nums[i] < nums[j]) {
				temp[t++] = nums[i++];
			} else {
				temp[t++] = nums[j++];
			}
		}

		while (i <= m) {
			temp[t++] = nums[i++];
		}

		while (j <= r) {
			temp[t++] = nums[j++];
		}

		for (int ind = 0; ind < r - l + 1; ind++) {
			nums[l + ind] = temp[ind];
		}
	}

	void mergeSort(int[] nums, int l, int r) {
		if (l >= r)
			return;
		int m = (l + r) / 2;

		mergeSort(nums, l, m);
		mergeSort(nums, m + 1, r);
		merge(nums, l, m, r);
	}

	public int[] sortArray(int[] nums) {
		mergeSort(nums, 0, nums.length - 1);
		return nums;
	}

	public static void main(String[] args) {
		

	}

}
