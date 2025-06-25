package LeetCode;


/**
 * url: https://leetcode.com/problems/swap-nodes-in-pairs/description/
 * 
 * Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)

 

Example 1:

Input: head = [1,2,3,4]

Output: [2,1,4,3]

Explanation:



Example 2:

Input: head = []

Output: []

Example 3:

Input: head = [1]

Output: [1]

Example 4:

Input: head = [1,2,3]

Output: [2,1,3]

 

Constraints:

The number of nodes in the list is in the range [0, 100].
0 <= Node.val <= 100
 */
public class Swap_Nodes_in_Pairs {
	
	public ListNode swapPairs(ListNode head) {
        ListNode h = head;
        if(h == null) return h;
        ListNode n = head.next;
        if(n == null) return h;

        while(n != null){
            int t = n.val;
            n.val = h.val;
            h.val = t;
            if(n == null || n.next == null) break;
            n = n.next.next;
            h = h.next.next; 
        }

        return head;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
