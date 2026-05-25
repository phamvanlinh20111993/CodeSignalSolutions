package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * url: https://leetcode.com/problems/jump-game-iv/?envType=daily-question&envId=2026-05-18
 * 
 * Given an array of integers arr, you are initially positioned at the first index of the array.

In one step you can jump from index i to index:

i + 1 where: i + 1 < arr.length.
i - 1 where: i - 1 >= 0.
j where: arr[i] == arr[j] and i != j.
Return the minimum number of steps to reach the last index of the array.

Notice that you can not jump outside of the array at any time.

 

Example 1:

Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
Output: 3
Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.
Example 2:

Input: arr = [7]
Output: 0
Explanation: Start index is the last index. You do not need to jump.
Example 3:

Input: arr = [7,6,9,6,9,6,9,7]
Output: 1
Explanation: You can jump directly from index 0 to index 7 which is last index of the array.
 

Constraints:

1 <= arr.length <= 5 * 104
-108 <= arr[i] <= 108
 */
public class Jump_Game_IV_1345 {

	class KV {
		int k;
		int v;

		public KV() {
		}

		public KV(int k, int v) {
			this.k = k;
			this.v = v;
		}
	}

	public int minJumps(int[] arr) {
		Map<Integer, List<Integer>> m = new HashMap<>();
		for (int ind = 0; ind < arr.length; ind++) {
			List<Integer> l = m.getOrDefault(arr[ind], new ArrayList<>());
			l.add(ind);
			m.put(arr[ind], l);
		}

		boolean[] b = new boolean[arr.length];
		Queue<KV> q = new LinkedList<>();
		q.add(new KV(0, 0));
		b[0] = true;

		while (!q.isEmpty()) {
			KV r = q.poll();
			if (r.k == arr.length - 1)
				return r.v;
			Set<Integer> s = new HashSet<>(m.get(arr[r.k]));
			if (r.k + 1 < arr.length) {
				s.add(r.k + 1);
			}
			if (r.k - 1 > -1) {
				s.add(r.k - 1);
			}
			Iterator<Integer> it = s.iterator();
			while (it.hasNext()) {
				Integer e = it.next();
				if (r.k != e && !b[e]) {
					q.add(new KV(e, r.v + 1));
					b[e] = true;
				}
			}
			m.put(arr[r.k], new ArrayList<>());
		}

		return 0;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
