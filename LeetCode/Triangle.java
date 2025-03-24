package LeetCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Url: https://leetcode.com/problems/triangle/description/
 * 
 * Given a triangle array, return the minimum path sum from top to bottom.

For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.

 

Example 1:

Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
Output: 11
Explanation: The triangle looks like:
   2
  3 4
 6 5 7
4 1 8 3
The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
Example 2:

Input: triangle = [[-10]]
Output: -10
 

Constraints:

1 <= triangle.length <= 200
triangle[0].length == 1
triangle[i].length == triangle[i - 1].length + 1
-104 <= triangle[i][j] <= 104
 

Follow up: Could you do this using only O(n) extra space, where n is the total number of rows in the triangle?
 */
public class Triangle {
	/*  
    // fail not pass
    int min = 2000000;
    public void recursion(int rP, int cP, List<List<Integer>> triangle, int s){

        if(rP >= triangle.size()) {
            min = min > s ? s : min;
            return;
        }
        
        recursion(rP+1, cP, triangle, s + triangle.get(rP).get(cP));
        if(cP + 1 < triangle.get(rP).size()){
            recursion(rP+1, cP+1, triangle, s + triangle.get(rP).get(cP + 1));
        }
    }

    public int minimumTotal(List<List<Integer>> triangle)
        min = 2000000;
        recursion(0, 0, triangle, 0);
        return min;
    } */
	Map<String, Integer> memorize;

	public int recursion(List<List<Integer>> triangle, int i, int j) {
		if (i < 1) {
			memorize.put("0-" + j, triangle.get(0).get(j));
			return triangle.get(i).get(j);
		}
		if (memorize.containsKey(i + "-" + j)) {
			return memorize.get(i + "-" + j);
		}
		int val;

		if (j >= triangle.get(i).size() - 1) {
			val = recursion(triangle, i - 1, j - 1);
		} else if (j < 1) {
			val = recursion(triangle, i - 1, j);
		} else {
			val = Math.min(recursion(triangle, i - 1, j - 1), recursion(triangle, i - 1, j));
		}

		memorize.put(i + "-" + j, val + triangle.get(i).get(j));

		return val + triangle.get(i).get(j);
	}

	public int minimumTotal(List<List<Integer>> triangle) {
		memorize = new HashMap<>();
		int r = triangle.size() - 1;
		int min = 200000;
		for (int j = 0; j < triangle.get(r).size(); j++) {
			min = Math.min(min, recursion(triangle, r, j));
		}
		// System.out.println(memorize);

		return min;
	}
}
