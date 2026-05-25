package LeetCode;

/**
 * url: https://leetcode.com/problems/rotate-list/description/?envType=daily-question&envId=2026-05-05
 * 
 * Given the head of a linked list, rotate the list to the right by k places.

 

Example 1:


Input: head = [1,2,3,4,5], k = 2
Output: [4,5,1,2,3]
Example 2:


Input: head = [0,1,2], k = 4
Output: [2,0,1]
 

Constraints:

The number of nodes in the list is in the range [0, 500].
-100 <= Node.val <= 100
0 <= k <= 2 * 109
 */
public class Rotate_List_61 {

	public ListNode rotateRight(ListNode head, int k) {
		if (head == null)
			return head;
		int size = 1;
		ListNode temp = head;
		while (temp.next != null) {
			size++;
			temp = temp.next;
		}

		if (size == 1)
			return head;

		k = k % size;
		int amount = 0;

		while (amount < k) {
			temp = head;
			ListNode pre = head;
			while (temp.next != null) {
				size++;
				pre = temp;
				temp = temp.next;
			}

			temp.next = head;
			pre.next = null;
			head = temp;
			amount++;
		}

		return head;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
