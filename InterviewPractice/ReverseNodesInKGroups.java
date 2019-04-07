package InterviewPractice;

class ListNode<T> {
	T value;
	ListNode<T> next;
	
	ListNode(T x) {
		value = x;
	}
	
}

public class ReverseNodesInKGroups {
	
	static ListNode<Integer> reverseNodesInKGroups(ListNode<Integer> l, int k) {

		if (k == 1) {
			return l;
		} else {
			int pos = 0;
			ListNode<Integer> temp = l, 
					          nodeTmp, 
					          result = null, 
					          listNodeOrigin = null,
					          listNodeTmp = null;

			while (temp != null) {

				nodeTmp = new ListNode<Integer>(temp.value);
				
				if(listNodeOrigin == null) {
					listNodeOrigin = new ListNode<Integer>(temp.value);
				}else {
					ListNode<Integer> load = listNodeOrigin;
					while (load.next != null) {
						load = load.next;
					}
					load.next = new ListNode<Integer>(temp.value);
				}
				
				nodeTmp.next = listNodeTmp;
				listNodeTmp = nodeTmp;
				
				if (pos > 0 && (pos + 1) % k == 0) {
					if (result == null) {
						result = listNodeTmp;
					} else {
						ListNode<Integer> tmp1 = result;
						while (tmp1.next != null) tmp1 = tmp1.next;
						tmp1.next = listNodeTmp;
						
					}
					listNodeTmp = null;
					listNodeOrigin = null;
				}
				temp = temp.next;
				pos++;
			}
			
			nodeTmp = result;
			while (nodeTmp.next != null) {
				nodeTmp = nodeTmp.next;
			}
			nodeTmp.next = listNodeOrigin;

			return result;
		}
	}
	
	public static void showList(ListNode<Integer> nodes) {
		ListNode<Integer> tmp = nodes;
		while(tmp != null) {
			System.out.print(tmp.value + " ");
			tmp = tmp.next;
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		ListNode<Integer> input = new ListNode<Integer>(1);
		input.next = new ListNode<Integer>(2);
		input.next.next =  new ListNode<Integer>(3);
		input.next.next.next =  new ListNode<Integer>(4);
		input.next.next.next.next =  new ListNode<Integer>(5);
		input.next.next.next.next.next =  new ListNode<Integer>(6);
		input.next.next.next.next.next.next =  new ListNode<Integer>(7);
		input.next.next.next.next.next.next.next =  new ListNode<Integer>(8);
		input.next.next.next.next.next.next.next.next =  new ListNode<Integer>(9);
		input.next.next.next.next.next.next.next.next.next =  new ListNode<Integer>(10);
		input.next.next.next.next.next.next.next.next.next.next =  new ListNode<Integer>(11);
		
		showList(reverseNodesInKGroups(input, 3));
		
	}

}
