package InterviewPractice;

class ListNode1<T> {
	T value;
	ListNode1<T> next;
	
	ListNode1(T x) {
		value = x;
	}
}


public class ReverseNodesInKGroups_Optimal {
	
	public static void showList(ListNode1<Integer> nodes) {
		ListNode1<Integer> tmp = nodes;
		while(tmp != null) {
			System.out.print(tmp.value + " ");
			tmp = tmp.next;
		}
		System.out.println();
	}
	
	static ListNode1<Integer> reverseNodesInKGroups(ListNode1<Integer> l, int k) {
		if (k == 1) {
			return l;
		} else {
			int pos = 0;
			ListNode1<Integer> temp = l, 
							   tmp = null,
					           reverse = null, 
					           reverseList = null;
			
			while(temp.next != null) {
				for(pos = 0; pos < k && temp.next != null; pos++) {
					reverse = temp;
					temp = temp.next;
					reverse.next = null;
					if(tmp == null) {
						tmp = reverse;
					}
					
					if(reverseList == null) {
						reverseList = reverse;
					}else {
						reverse.next = reverseList;
						reverseList = reverse;
					}
				}
				System.out.println(tmp.value);
				showList(reverseList);
				
			}
			return reverseList;
		}
	}
	
	public static void main(String[] args) {
		ListNode1<Integer> input = new ListNode1<Integer>(1);
		input.next = new ListNode1<Integer>(2);
		input.next.next =  new ListNode1<Integer>(3);
		input.next.next.next =  new ListNode1<Integer>(4);
		input.next.next.next.next =  new ListNode1<Integer>(5);
		input.next.next.next.next.next =  new ListNode1<Integer>(6);
		input.next.next.next.next.next.next =  new ListNode1<Integer>(7);
		input.next.next.next.next.next.next.next =  new ListNode1<Integer>(8);
		input.next.next.next.next.next.next.next.next =  new ListNode1<Integer>(9);
		input.next.next.next.next.next.next.next.next.next =  new ListNode1<Integer>(10);
		input.next.next.next.next.next.next.next.next.next.next =  new ListNode1<Integer>(11);
		input.next.next.next.next.next.next.next.next.next.next.next =  new ListNode1<Integer>(12);
		
		System.out.println("Init list ");
		 showList(input);
		System.out.println("Out put ");
		reverseNodesInKGroups(input, 3);

	}

}
