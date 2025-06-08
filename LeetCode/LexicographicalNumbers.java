package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * url: https://leetcode.com/problems/lexicographical-numbers/description/
 * 
 * Given an integer n, return all the numbers in the range [1, n] sorted in lexicographical order.

You must write an algorithm that runs in O(n) time and uses O(1) extra space. 

 

Example 1:

Input: n = 13
Output: [1,10,11,12,13,2,3,4,5,6,7,8,9]
Example 2:

Input: n = 2
Output: [1,2]
 

Constraints:

1 <= n <= 5 * 104
 */
public class LexicographicalNumbers {

	public List<Integer> lexicalOrderNlogN(int n) {

		PriorityQueue<String> heap = new PriorityQueue<>();
		for (int ind = 1; ind <= n; ind++) {
			heap.offer(ind + "");
		}
		List<Integer> res = new ArrayList<>();
		while (!heap.isEmpty()) {
			res.add(Integer.valueOf(heap.poll()));
		}

		return res;
	}

	// check suggestion at the answer: Solution tab/LetCode
	public List<Integer> lexicalOrderDfs(int n) {
		int[] base = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Stack<Integer> st = new Stack<>();

		for (int ind = base.length - 1; ind > 0; ind--) {
			if (base[ind] <= n) {
				st.push(base[ind]);
			}
		}

		List<Integer> res = new ArrayList<>();
		while (!st.isEmpty()) {
			int val = st.pop();
			res.add(val);
			for (int ind = base.length - 1; ind > -1; ind--) {
				int next = val * 10 + base[ind];
				if (next <= n) {
					st.push(next);
				}
			}
		}

		return res;
	}

	public List<Integer> lexicalOrder(int n) {
		// return lexicalOrderNlogN(n);
		return lexicalOrderDfs(n);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
