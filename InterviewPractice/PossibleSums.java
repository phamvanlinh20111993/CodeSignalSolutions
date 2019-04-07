package InterviewPractice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class PossibleSums {

	static Set<Integer> possibleSums;

	static boolean[] isChoose;

	static int length;

	static void getAllPossibleNum(int quantity, int pos, int[] stores, ArrayList<Integer> lists, int val) {

		for (int i = val; i < lists.size(); i++) {

			if (isChoose[i] && pos <= quantity) {

				stores[i] = lists.get(i);
				isChoose[i] = false;

				if (pos < quantity) {
					getAllPossibleNum(quantity, pos + 1, stores, lists, i);
				} else {
					for (int k = 0; k < stores.length; k++) {
						if (stores[k] > 0)
							System.out.print(stores[k] + " ");
					}
					length++;
					System.out.println();
				}

				isChoose[i] = true;
				stores[i] = 0;
			}
		}
	}

	static int possibleSums(int[] coins, int[] quantity) {

		int res = 0, i;
		length = 0;
		possibleSums = new HashSet<>();

		Map<Integer, Integer> preventDup = new TreeMap<>();
		for (i = 0; i < coins.length; i++) {
			preventDup.put(coins[i],
					preventDup.get(coins[i]) == null ? quantity[i] : preventDup.get(coins[i]) + quantity[i]);
		}

		ArrayList<Integer> listCoins = new ArrayList<>();
		for (Integer key : preventDup.keySet()) {
			listCoins.add(key);
		}

		isChoose = new boolean[listCoins.size()];
		int[] outP = new int[listCoins.size()];
		for (i = 0; i < listCoins.size(); i++) {
			for (int j = 0; j < isChoose.length; j++) {
				isChoose[j] = true;
			}
			getAllPossibleNum(i, 0, outP, listCoins, 0);
		}

		System.out.println("Length " + length);

		return res;
	}

	public static void main(String[] args) {

		// 9
		System.out.println("############## test 1 ###############");
		int[] coins = { 10, 50, 100 };
		int[] quantity = { 1, 2, 1 };
		possibleSums(coins, quantity);

		// ???
		System.out.println("############## test custom ###############");
		int[] coins1 = { 3, 1, 4, 6, 7, 9, 5 };// 12, 44, 423, 23, 32 };
		int[] quantity1 = { 100, 84, 104, 4234, 43, 6534, 53534 };// 99999, 4234, 232, 56756, 3543 };
		possibleSums(coins1, quantity1);

		// ???
		System.out.println("############## test custom1 ###############");
		int[] coins2 = { 10, 20, 50, 100 };// 12, 44, 423, 23, 32 };
		int[] quantity2 = { 10000, 20000, 50000, 100000 };// 99999, 4234, 232, 56756, 3543 };
		possibleSums(coins2, quantity2);

		// ???
		System.out.println("############## test custom3 ###############");
		int[] coins3 = { 10, 20, 50, 100, 1, 2, 3, 4, 5, 6, 7, 8, 11, 9, 10, 12, 14, 36, 13, 17 };
		int[] quantity3 = { 10000, 20000, 50000, 100000, 1000, 1000, 1000, 10001, 10001, 10001, 222, 22, 24, 2, 22, 23, 27,
				23, 45, 3345 };
		possibleSums(coins3, quantity3);
	
	}

}