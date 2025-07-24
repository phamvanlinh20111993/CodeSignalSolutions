package LeetCode;

/**
 * url: https://leetcode.com/problems/maximum-ice-cream-bars/description/?envType=problem-list-v2&envId=counting-sort
 * 
 * It is a sweltering summer day, and a boy wants to buy some ice cream bars.

At the store, there are n ice cream bars. You are given an array costs of length n, where costs[i] is the price of the ith ice cream bar in coins. The boy initially has coins coins to spend, and he wants to buy as many ice cream bars as possible. 

Note: The boy can buy the ice cream bars in any order.

Return the maximum number of ice cream bars the boy can buy with coins coins.

You must solve the problem by counting sort.

 

Example 1:

Input: costs = [1,3,2,4,1], coins = 7
Output: 4
Explanation: The boy can buy ice cream bars at indices 0,1,2,4 for a total price of 1 + 3 + 2 + 1 = 7.
Example 2:

Input: costs = [10,6,8,7,7,8], coins = 5
Output: 0
Explanation: The boy cannot afford any of the ice cream bars.
Example 3:

Input: costs = [1,6,3,1,2,5], coins = 20
Output: 6
Explanation: The boy can buy all the ice cream bars for a total price of 1 + 6 + 3 + 1 + 2 + 5 = 18.
 

Constraints:

costs.length == n
1 <= n <= 105
1 <= costs[i] <= 105
1 <= coins <= 108
 */
public class MaximumIceCreamBars {

	public int maxIceCream(int[] costs, int coins) {
		int max = -1, min = 1000000, l = costs.length;
		for (int ind = 0; ind < l; ind++) {
			if (max < costs[ind]) {
				max = costs[ind];
			}
			if (min > costs[ind]) {
				min = costs[ind];
			}
		}

		int countingSortLength = max - min;
		// System.out.println(max + " " + min);
		int[] countingSortArr = new int[countingSortLength + 1];
		for (int ind = 0; ind < l; ind++) {
			countingSortArr[costs[ind] - min] += 1;
		}

		// for(int ind = 0; ind <= countingSortLength; ind++ ){
		// System.out.print(countingSortArr[ind] + " ");
		// }

		int res = 0, ind = 0;
		while (coins > 0 && ind <= countingSortLength) {
			if (countingSortArr[ind] > 0) {
				if (coins < ind + min)
					break;
				coins -= (ind + min);
				countingSortArr[ind]--;
				res++;
			} else {
				ind++;
			}
		}

		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
