package LeetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Url: https://leetcode.com/problems/n-queens-ii/description/
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

Given an integer n, return the number of distinct solutions to the n-queens puzzle.

 

Example 1:


Input: n = 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown.
Example 2:

Input: n = 1
Output: 1
 

Constraints:

1 <= n <= 9
 */
public class NQueensII {

	List<String> queenOccupieCoords(int x, int y, int n) {
		List<String> res = new ArrayList<>();
		// Vertical
		for (int i = 0; i < n; i++) {
			res.add(x + "-" + i);
		}
		// horizontal
		for (int i = 0; i < n; i++) {
			res.add(i + "-" + y);
		}

		// diagonal-1
		int i = x, j = y;
		while (i >= 0 && j >= 0) {
			res.add(i + "-" + j);
			i--;
			j--;
		}
		i = x + 1;
		j = y + 1;
		while (i < n && j < n) {
			res.add(i + "-" + j);
			i++;
			j++;
		}

		// diagonal-2
		i = x + 1;
		j = y - 1;
		while (i < n && j >= 0) {
			res.add(i + "-" + j);
			i++;
			j--;
		}

		i = x - 1;
		j = y + 1;
		while (i >= 0 && j < n) {
			res.add(i + "-" + j);
			i--;
			j++;
		}

		return res;
	}

	int count;

	public void backtracking(int n, int p, Set<String> isVisited) {

		if (p >= n) {
			count++;
			return;
		}

		for (int ind = 0; ind < n; ind++) {
			if (!isVisited.contains(p + "-" + ind)) {
				Set<String> isVisitedN = new HashSet<>(isVisited);
				isVisitedN.addAll(queenOccupieCoords(p, ind, n));
				backtracking(n, p + 1, isVisitedN);
			}
		}
	}

	public int totalNQueens(int n) {
		count = 0;
		backtracking(n, 0, new HashSet<>());

		return count;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
