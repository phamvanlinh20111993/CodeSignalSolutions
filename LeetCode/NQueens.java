package LeetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * url: https://leetcode.com/problems/n-queens/description/?envType=problem-list-v2&envId=backtracking
 * 
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.

 

Example 1:


Input: n = 4
Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
Example 2:

Input: n = 1
Output: [["Q"]]
 

Constraints:

1 <= n <= 9
 * 
 */
public class NQueens {

	class Solution {

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

		List<List<String>> res;

		public List<String> transformToResult(int n, List<Integer[]> queenCoords) {
			List<String> res = new ArrayList<>();

			int p = 0;
			for (int i = 0; i < n; i++) {
				StringBuilder s = new StringBuilder();
				for (int j = 0; j < n; j++) {
					if (p < queenCoords.size() && i == queenCoords.get(p)[0] && j == queenCoords.get(p)[1]) {
						s.append("Q");
						p++;
						continue;
					}
					s.append(".");
				}
				res.add(s.toString());
			}

			return res;
		}

		public void backtracking(int n, int p, Set<String> isVisited, List<Integer[]> queenCoords) {
			if (p >= n) {
				res.add(transformToResult(n, queenCoords));
				return;
			}

			for (int ind = 0; ind < n; ind++) {
				if (!isVisited.contains(p + "-" + ind)) {
					Set<String> isVisitedN = new HashSet<>(isVisited);
					isVisitedN.addAll(queenOccupieCoords(p, ind, n));
					List<Integer[]> queenCoordsN = new ArrayList<>(queenCoords);
					queenCoordsN.add(new Integer[] { p, ind });

					backtracking(n, p + 1, isVisitedN, queenCoordsN);
				}
			}
		}

		public List<List<String>> solveNQueens(int n) {
			res = new ArrayList<>();
			backtracking(n, 0, new HashSet<>(), new ArrayList<>());

			return res;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
