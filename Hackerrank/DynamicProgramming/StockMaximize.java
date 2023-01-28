package Hackerrank.DynamicProgramming;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * @author PhamLinh
 * @Url: https://www.hackerrank.com/challenges/stockmax/problem?isFullScreen=true
 * 
 * 
 *       Your algorithms have become so good at predicting the market that you
 *       now know what the share price of Wooden Orange Toothpicks Inc. (WOT)
 *       will be for the next number of days.
 * 
 *       Each day, you can either buy one share of WOT, sell any number of
 *       shares of WOT that you own, or not make any transaction at all. What is
 *       the maximum profit you can obtain with an optimum trading strategy?
 * 
 *       Example
 * 
 *       Buy one share day one, and sell it day two for a profit of . Return .
 * 
 * 
 *       No profit can be made so you do not buy or sell stock those days.
 *       Return .
 * 
 *       Function Description
 * 
 *       Complete the stockmax function in the editor below.
 * 
 *       stockmax has the following parameter(s):
 * 
 *       prices: an array of integers that represent predicted daily stock
 *       prices Returns
 * 
 *       int: the maximum profit achievable Input Format
 * 
 *       The first line contains the number of test cases .
 * 
 *       Each of the next pairs of lines contain: - The first line contains an
 *       integer , the number of predicted prices for WOT. - The next line
 *       contains n space-separated integers , each a predicted stock price for
 *       day .
 * 
 *       Constraints
 * 
 *       Output Format
 * 
 *       Output lines, each containing the maximum profit which can be obtained
 *       for the corresponding test case.
 * 
 *       Sample Input
 * 
 *       STDIN Function ----- -------- 3 q = 3 3 prices[] size n = 3 5 3 2
 *       prices = [5, 3, 2] 3 prices[] size n = 3 1 2 100 prices = [1, 2, 100] 4
 *       prices[] size n = 4 1 3 1 2 prices =[1, 3, 1, 2] Sample Output
 * 
 *       0 197 3 Explanation
 * 
 *       For the first case, there is no profit because the share price never
 *       rises, return . For the second case, buy one share on the first two
 *       days and sell both of them on the third day for a profit of . For the
 *       third case, buy one share on day 1, sell one on day 2, buy one share on
 *       day 3, and sell one share on day 4. The overall profit is .
 */

// Fail 1 testcase -_- , but js Passed @@

class KeyPair<K, V> {
	K k;
	V v;

	public KeyPair(K k, V v) {
		super();
		this.k = k;
		this.v = v;
	}

	public K getK() {
		return k;
	}

	public void setK(K k) {
		this.k = k;
	}

	public V getV() {
		return v;
	}

	public void setV(V v) {
		this.v = v;
	}

	@Override
	public String toString() {
		return "KeyPair [k=" + k + ", v=" + v + "]";
	}

}

public class StockMaximize {

	public static long stockmax(List<Integer> prices) {
		// Write your code here
		long profit = 0l;

		List<KeyPair<Integer, Integer>> sortedData = new ArrayList<>();
		for (int ind = 0; ind < prices.size(); ind++) {
			sortedData.add(new KeyPair<Integer, Integer>(prices.get(ind), ind));
		}

		sortedData = sortedData.stream().sorted(new Comparator<KeyPair<Integer, Integer>>() {
			@Override
			public int compare(KeyPair<Integer, Integer> o1, KeyPair<Integer, Integer> o2) {
				return o1.getK() == o2.getK() ? o1.getV() - o2.getV() : o2.getK() - o1.getK();
			}
		}).collect(Collectors.toList());

		List<Integer> maxAtEs = new ArrayList<Integer>();
		maxAtEs.add(sortedData.get(0).getV());
		for (int ind = 1; ind < sortedData.size(); ind++) {
			if (sortedData.get(ind).getV() > maxAtEs.get(maxAtEs.size() - 1)) {
				maxAtEs.add(sortedData.get(ind).getV());
			}
		}

		for (int ind = 0; ind < maxAtEs.size(); ind++) {
			int value = prices.get(maxAtEs.get(ind)), 
				pos = maxAtEs.get(ind) - 1, 
				val = 0;
			while (pos > (ind > 0 ? maxAtEs.get(ind - 1) : -1) && value > prices.get(pos)) {
				val += prices.get(pos);
				pos--;
			}

			if (maxAtEs.get(ind) - pos > 1) {
				profit += (maxAtEs.get(ind) - pos - 1) * value - val;
			}
		}

		return profit;
	}

	public static void main(String[] args) {

	}
}
