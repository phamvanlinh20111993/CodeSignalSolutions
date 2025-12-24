package LeetCode;

/**
 * url: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-using-strategy/description/?envType=daily-question&envId=2025-12-18
 * 
 * You are given two integer arrays prices and strategy, where:

prices[i] is the price of a given stock on the ith day.
strategy[i] represents a trading action on the ith day, where:
-1 indicates buying one unit of the stock.
0 indicates holding the stock.
1 indicates selling one unit of the stock.
You are also given an even integer k, and may perform at most one modification to strategy. A modification consists of:

Selecting exactly k consecutive elements in strategy.
Set the first k / 2 elements to 0 (hold).
Set the last k / 2 elements to 1 (sell).
The profit is defined as the sum of strategy[i] * prices[i] across all days.

Return the maximum possible profit you can achieve.

Note: There are no constraints on budget or stock ownership, so all buy and sell operations are feasible regardless of past actions.

 

Example 1:

Input: prices = [4,2,8], strategy = [-1,0,1], k = 2

Output: 10

Explanation:

Modification	Strategy	Profit Calculation	Profit
Original	[-1, 0, 1]	(-1 × 4) + (0 × 2) + (1 × 8) = -4 + 0 + 8	4
Modify [0, 1]	[0, 1, 1]	(0 × 4) + (1 × 2) + (1 × 8) = 0 + 2 + 8	10
Modify [1, 2]	[-1, 0, 1]	(-1 × 4) + (0 × 2) + (1 × 8) = -4 + 0 + 8	4
Thus, the maximum possible profit is 10, which is achieved by modifying the subarray [0, 1]​​​​​​​.

Example 2:

Input: prices = [5,4,3], strategy = [1,1,0], k = 2

Output: 9

Explanation:

Modification	Strategy	Profit Calculation	Profit
Original	[1, 1, 0]	(1 × 5) + (1 × 4) + (0 × 3) = 5 + 4 + 0	9
Modify [0, 1]	[0, 1, 0]	(0 × 5) + (1 × 4) + (0 × 3) = 0 + 4 + 0	4
Modify [1, 2]	[1, 0, 1]	(1 × 5) + (0 × 4) + (1 × 3) = 5 + 0 + 3	8
Thus, the maximum possible profit is 9, which is achieved without any modification.

 

Constraints:

2 <= prices.length == strategy.length <= 105
1 <= prices[i] <= 105
-1 <= strategy[i] <= 1
2 <= k <= prices.length
k is even
 */
public class Best_Time_to_Buy_and_Sell_Stock_using_Strategy_3652 {

	public long maxProfit(int[] prices, int[] strategy, int k) {
		int pL = prices.length;
		// accumulate sum
		long[] prefixSum = new long[pL];
		prefixSum[0] = strategy[0] * prices[0];
		for (int i = 1; i < pL; i++) {
			prefixSum[i] = prefixSum[i - 1] + strategy[i] * prices[i];
		}

		long maxProfit = prefixSum[pL - 1];
		// System.out.println("origin sum " + maxProfit);
		// slicing window
		int left = 0, right = k - 1;
		long sumLastOne = 0L;
		for (int i = 1 + (left + right) / 2; i <= right; i++) {
			sumLastOne += prices[i];
		}
		// System.out.println("the last k / 2 elements sum " + sumLastOne);

		while (right < pL) {
			long sumBeforeLeft = (left == 0 ? 0 : prefixSum[left - 1]);
			long sumAfterRight = prefixSum[pL - 1] - prefixSum[right];

			long currentSum = sumLastOne + sumBeforeLeft + sumAfterRight;
			if (currentSum > maxProfit) {
				maxProfit = currentSum;
			}
			int mid = 1 + (left + right) / 2;
			left++;
			right++;
			if (right < pL) {
				sumLastOne += prices[right];
				sumLastOne -= prices[mid];
			}
		}

		return maxProfit;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
