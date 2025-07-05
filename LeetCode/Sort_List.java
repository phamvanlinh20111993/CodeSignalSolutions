package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * url: https://leetcode.com/problems/sort-list/?envType=problem-list-v2&envId=merge-sort
 * Given the head of a linked list, return the list after sorting it in ascending order.

 

Example 1:


Input: head = [4,2,1,3]
Output: [1,2,3,4]
Example 2:


Input: head = [-1,5,3,4,0]
Output: [-1,0,3,4,5]
Example 3:

Input: head = []
Output: []
 

Constraints:

The number of nodes in the list is in the range [0, 5 * 104].
-105 <= Node.val <= 105
 

Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?


 */
public class Sort_List {

	void merge(Integer[] nums, int l, int m, int r) {
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

	void mergeSort(Integer[] nums, int l, int r) {
		if (l >= r)
			return;
		int m = (l + r) / 2;

		mergeSort(nums, l, m);
		mergeSort(nums, m + 1, r);
		merge(nums, l, m, r);
	}

	public ListNode sortList(ListNode head) {
		List<Integer> l = new ArrayList<>();
		ListNode v = head;
		while (v != null) {
			l.add(v.val);
			v = v.next;
		}

		Integer[] arr = new Integer[l.size()];
		l.toArray(arr);
		mergeSort(arr, 0, l.size() - 1);
		ListNode r = head;
		int ind = 0;
		while (r != null) {
			r.val = arr[ind++];
			r = r.next;
		}

		return head;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
