package Codesignal.InterviewPractice.DynamicPrograming;

import java.util.Arrays;
import java.util.Collections;

/**
 * 
 * @author PhamLinh
 * @url https://app.codesignal.com/interview-practice/question/idSmSy6u2LNiNDjKw/description
 * 
 * In San Francisco, there is a row of several beautiful houses called the Painted Ladies. Each of the Painted Ladies can be painted with one of three colors: red, blue or green. The cost of painting each house with a certain color is different. cost[i][0] for each i is the cost of painting house i red, cost[i][1] is the cost of painting it blue, and cost[i][2] is the cost of painting it green.

You want to paint all the houses in a way such that no two adjacent Painted Ladies have the same color. Find the minimum cost to achieve this.

Example

For cost = [[1, 3, 4], [2, 3, 3], [3, 1, 4]], the output should be
solution(cost) = 5.

You can paint the first Painted Lady red for a cost of 1, the second one green for a cost of 3, and the third one blue for a cost of 1, for a total cost of 5.

Input/Output

    [execution time limit] 3 seconds (java)

    [memory limit] 1 GB

    [input] array.array.integer cost

    The costs of painting each Painted Lady a certain color following the guidelines: cost[i][0] for each i is the cost of painting house i red, cost[i][1] is the cost of painting it blue, and cost[i][2] is the cost of painting it green.

    Guaranteed constraints:
    1 ≤ cost.length ≤ 105,
    cost[i].length = 3,
    1 ≤ cost[i][j] ≤ 104.

    [output] integer

    The minimal cost of painting all the Painted Ladies so that no two adjacent houses are the same color.

 * 
 */
public class PaintHouses_2024 {

    // call ms[x][c] is the min cost from xth house at color i(red, blue,
    // green)(r,g,b)(0,1,2)
    // then we have:
    // ms[x][r] = min(ms[x-1][b], ms[x-1][g]) + c[x][r]
    // ms[x][b] = min(ms[x-1][r], ms[x-1][g]) + c[x][b]
    // ms[x][g] = min(ms[x-1][b], ms[x-1][r]) + c[x][g]
    // => mincost = min(ms[x][r], ms[x][b], ms[x][g])
    static int[][] m;

    static int dp(int x, int color, int[][] cost) {
	if (x < 0)
	    return 0;

	if (m[x][color] > 0)
	    return m[x][color];

	if (color == 0) {
	    int pb = dp(x - 1, 1, cost);
	    int pg = dp(x - 1, 2, cost);
	    m[x][color] = Math.min(pb, pg) + cost[x][0];
	}
	if (color == 1) {
	    int pr = dp(x - 1, 0, cost);
	    int pg = dp(x - 1, 2, cost);
	    m[x][color] = Math.min(pr, pg) + cost[x][1];
	}
	if (color == 2) {
	    int pr = dp(x - 1, 0, cost);
	    int pb = dp(x - 1, 1, cost);
	    m[x][color] = Math.min(pr, pb) + cost[x][2];
	}
	return m[x][color];
    }

    static int solution(int[][] cost) {
	m = new int[cost.length][3];

	int r = dp(cost.length - 1, 0, cost);
	int b = dp(cost.length - 1, 1, cost);
	int g = dp(cost.length - 1, 2, cost);

	return Collections.min(Arrays.asList(r, b, g));
    }

    public static void main(String[] args) {

    }
}
