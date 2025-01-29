package LeetCode;

//@formatter:off
/**
 * Url: https://leetcode.com/problems/merge-two-sorted-lists/
 * You are given the heads of two sorted linked lists list1 and list2.

	Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.
	
	Return the head of the merged linked list.
	
	 
	
	Example 1:
	
	
	Input: list1 = [1,2,4], list2 = [1,3,4]
	Output: [1,1,2,3,4,4]
	Example 2:
	
	Input: list1 = [], list2 = []
	Output: []
	Example 3:
	
	Input: list1 = [], list2 = [0]
	Output: [0]
	 
	
	Constraints:
	
	The number of nodes in both lists is in the range [0, 50].
	-100 <= Node.val <= 100
	Both list1 and list2 are sorted in non-decreasing order.
 */


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
//@formatter:on
public class MergeTwoSortedLists {
	public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
		ListNode root = new ListNode(-1);
		ListNode tmp = root;
		ListNode temp = list1;
		ListNode temp1 = list2;
		while (temp != null && temp1 != null) {
			if (temp.val < temp1.val) {
				tmp.next = new ListNode(temp.val);
				temp = temp.next;
			} else {
				tmp.next = new ListNode(temp1.val);
				temp1 = temp1.next;
			}
			tmp = tmp.next;
		}

		if (temp != null) {
			tmp.next = temp;
		}

		if (temp1 != null) {
			tmp.next = temp1;
		}

		return root.next;
	}

	public static void main(String[] args) {
		System.out.println("Happy new year 2025 !!!, 10:53AM 01/01/2025 Lunar Calendar");

	}

}
