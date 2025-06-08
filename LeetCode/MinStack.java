package LeetCode;

import java.util.Stack;

/**
 * Url: https://leetcode.com/problems/min-stack/description/
 * 
 * i use hints to resolve this problem
 * 
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

Implement the MinStack class:

MinStack() initializes the stack object.
void push(int val) pushes the element val onto the stack.
void pop() removes the element on the top of the stack.
int top() gets the top element of the stack.
int getMin() retrieves the minimum element in the stack.
You must implement a solution with O(1) time complexity for each function.

 

Example 1:

Input
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

Output
[null,null,null,null,-3,null,0,-2]

Explanation
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin(); // return -3
minStack.pop();
minStack.top();    // return 0
minStack.getMin(); // return -2
 

Constraints:

-231 <= val <= 231 - 1
Methods pop, top and getMin operations will always be called on non-empty stacks.
At most 3 * 104 calls will be made to push, pop, top, and getMin.
 * 
 */
public class MinStack {

	record ValM(int val, int min) {
	}

	Stack<ValM> st;

	public MinStack() {
		st = new Stack<ValM>();
	}

	public void push(int val) {
		ValM v = new ValM(-1, Integer.MAX_VALUE);
		if (!st.isEmpty()) {
			v = st.peek();
		}

		st.push(v.min > val ? new ValM(val, val) : new ValM(val, v.min));
	}

	public void pop() {
		st.pop();
	}

	public int top() {
		return st.peek().val;
	}

	public int getMin() {
        return st.peek().min;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
