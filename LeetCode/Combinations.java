package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * url: https://leetcode.com/problems/combinations/description/?envType=problem-list-v2&envId=backtracking
 * Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].

You may return the answer in any order.

 

Example 1:

Input: n = 4, k = 2
Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
Explanation: There are 4 choose 2 = 6 total combinations.
Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.
Example 2:

Input: n = 1, k = 1
Output: [[1]]
Explanation: There is 1 choose 1 = 1 total combination.
 

Constraints:

1 <= n <= 20
1 <= k <= n

 */
public class Combinations {

	List<List<Integer>> options;

	public void backtrackOp(int k, int st, int n, int t, List<Integer> acc) {
		if (k <= 0) {
			options.add(acc);
			return;
		}
		for (int ind = st; ind <= n; ind++) {
			acc.set(t, ind);
			backtrackOp(k - 1, ind + 1, n, t + 1, new ArrayList<>(acc));
		}
	}

	public List<List<Integer>> combine(int n, int k) {
		options = new ArrayList<>();
		List<Integer> ans = new ArrayList<>();
		for (int ind = 0; ind < k; ind++) {
			ans.add(0);
		}
		backtrackOp(k, 1, n, 0, ans);
		return options;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
