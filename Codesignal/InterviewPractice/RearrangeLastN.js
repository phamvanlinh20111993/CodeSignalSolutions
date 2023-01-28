
/**
 * Note: Try to solve this task in O(list size) time using O(1) additional
 * space, since this is what you'll be asked during an interview.
 * 
 * Given a singly linked list of integers l and a non-negative integer n, move
 * the last n list nodes to the beginning of the linked list.
 * 
 * Example
 * 
 * For l = [1, 2, 3, 4, 5] and n = 3, the output should be rearrangeLastN(l, n) =
 * [3, 4, 5, 1, 2]; For l = [1, 2, 3, 4, 5, 6, 7] and n = 1, the output should
 * be rearrangeLastN(l, n) = [7, 1, 2, 3, 4, 5, 6]. Input/Output
 * 
 * [execution time limit] 3 seconds (java)
 * 
 * [input] linkedlist.integer l
 * 
 * A singly linked list of integers.
 * 
 * Guaranteed constraints: 0 ≤ list size ≤ 105, -1000 ≤ element value ≤ 1000.
 * 
 * [input] integer n
 * 
 * A non-negative integer.
 * 
 * Guaranteed constraints: 0 ≤ n ≤ list size.
 * 
 * [output] linkedlist.integer
 * 
 * Return l with the n last elements moved to the beginning.
 */

// Definition for singly-linked list:
// class ListNode<T> {
// ListNode(T x) {
// value = x;
// }
// T value;
// ListNode<T> next;
// }
//
//
// If I have time and I'm not as tired as I am today, I will try to solve it
// like the first one
// To day, 10:44pm 12/3/2019
ListNode<Integer> rearrangeLastN(ListNode<Integer> l, int n) {
    
    if(l == null || n == 0) return l;
    
    ListNode<Integer> temp = l, temp1 = l, tmp = null, tmp1 = null;
    
    int size = 0, ind = 0;
    
    while(temp != null && temp.next != null){
        temp = temp.next;
        size++;
    }
    size++;
    
    if(n == size) return l;
    
    int stop = size - n;
    
    
    ind = 0;
    while(ind < size && temp1 != null){
        if(ind >= stop){
             if(tmp == null){
                tmp = new ListNode(temp1.value);
                tmp1 = tmp;
            }else{
                tmp1.next = new ListNode(temp1.value);
                tmp1 = tmp1.next;
            }    
        }
        
        temp1 = temp1.next;
        ind++;
    }
    
    ind = 0;  
    temp1 = l;
    while(ind < stop && temp1 != null){
        if(tmp == null){
            tmp = new ListNode(temp1.value);
            tmp1 = tmp;
        }else{
            tmp1.next = new ListNode(temp1.value);
            tmp1 = tmp1.next;
        }
        temp1 = temp1.next;
        ind++;
    }

    
    return tmp;
}
