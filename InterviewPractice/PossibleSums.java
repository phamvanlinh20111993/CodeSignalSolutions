package InterviewPractice;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PossibleSums {

	static Set<Integer> dups = new HashSet<Integer>();

	static void backtrackingAllSumPosible(int init, int[] sums, int sum) {
		dups.add(sum);
		for (int ind = init + 1; ind < sums.length; ind++) {
			backtrackingAllSumPosible(ind, sums, sum + sums[ind]);
		}
	}

	static int possibleSums(int[] sums, int[] amounts) {

		for (int ind = 0; ind < sums.length; ind++) {
			backtrackingAllSumPosible(ind, sums, sums[ind]);
		}

		// System.out.println("count = " + dups.size());

		Set<Integer> total = new HashSet<Integer>();
		total.addAll(dups);

		for (int ind = 0; ind < amounts.length; ind++) {
			if (amounts[ind] > 1) {
				for (int pos = 1; pos < amounts[ind]; pos++) {
					Iterator<Integer> i = dups.iterator();
					while (i.hasNext()) {
						total.add(sums[ind] * pos + i.next());
					}
				}
				dups.addAll(total);
			}
		}

		// System.out.println("size: " + total.size() + ", " + total);

		return total.size();
	}

	public static void main(String[] args) {

		// 9
		// System.out.println("############## test 1 ###############");
		// int[] coins = { 10, 50, 100 };
		// int[] quantity = { 1, 2, 1 };
		// possibleSums(coins, quantity);
		//
		// // ???
		// System.out.println("############## test custom ###############");
		// int[] coins1 = { 3, 1, 4, 6, 7, 9, 5 };// 12, 44, 423, 23, 32 };
		// int[] quantity1 = { 100, 84, 104, 4234, 43, 6534, 53534 };// 99999, 4234,
		// 232, 56756, 3543 };
		// possibleSums(coins1, quantity1);
		//
		// // ???
		// System.out.println("############## test custom1 ###############");
		// int[] coins2 = { 10, 20, 50, 100 };// 12, 44, 423, 23, 32 };
		// int[] quantity2 = { 10000, 20000, 50000, 100000 };// 99999, 4234, 232, 56756,
		// 3543 };
		// possibleSums(coins2, quantity2);
		//
		// // ???
		// System.out.println("############## test custom3 ###############");
		// int[] coins3 = { 10, 20, 50, 100, 1, 2, 3, 4, 5, 6, 7, 8, 11, 9, 130, 12, 14,
		// 36, 13, 17 };
		// int[] quantity3 = { 10000, 20000, 50000, 10000, 50000, 50000, 50000, 10001,
		// 10001, 10001, 50000, 50000, 50000,
		// 50000, 50000, 50000, 50000, 50000, 50000, 50000 };
		// possibleSums(coins3, quantity3);

		System.out.println("############## test custom4 ###############");
		int[] coins4 = { 10, 20, 50, 100, 1, 2, 3, 4, 5, 6, 7, 8, 11, 9, 130, 12, 14, 36, 13, 17, 21, 22, 23, 24, 25,
				26, 27, 28, 29, 30 };
		int[] quantity4 = { 10000, 20000, 50000, 10000, 50000, 50000, 50000, 10001, 10001, 10001, 50000, 50000, 50000,
				50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000,
				50000, 50000 };
		int count = possibleSums(coins4, quantity4);
		System.out.println("count " + count);

		// int[] coins = { 10, 50, 100 };
		// int[] quantity = { 1, 2, 1 };
		// calAllPossibleSum(0, coins, quantity);

	}

}