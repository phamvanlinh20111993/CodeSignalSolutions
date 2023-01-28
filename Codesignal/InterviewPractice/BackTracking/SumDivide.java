package Codesignal.InterviewPractice.BackTracking;

import java.util.HashSet;
import java.util.Set;

public class SumDivide {

	private static Set<String> result;

	private static Boolean[] distinctValues;

	private static int sumArr(Boolean[] distinctValues) {
		int sum = 0;
		for (int ind = 1; ind < distinctValues.length; ind++) {
			if (distinctValues[ind]) {
				sum += ind;
			}
		}
		return sum;
	}

	private static String convertToStr(Boolean[] distinctValues) {
		String str = "";
		for (int ind = 1; ind < distinctValues.length; ind++) {
			if (distinctValues[ind]) {
				str += ind + "-";
			}
		}
		return str.substring(0, str.length() - 1);

	}

	private static void printSet(Set<String> result) {
		for (String s : result) {
			System.out.println(s);
		}
	}

	private static void backtracking(int pos, int maxRange, int n) {
		for (int ind = pos; ind < maxRange; ind++) {

			if (!distinctValues[ind]) {
				int currTotal = sumArr(distinctValues);

				if (currTotal + ind < n) {
					distinctValues[ind] = true;
					backtracking(ind + 1, n - currTotal + 1, n);
					distinctValues[ind] = false;
				} else if (currTotal + ind == n) {
					result.add(convertToStr(distinctValues) + "-" + ind);
				} else {
					break;
				}
			}
		}
	}

	/**
	 * 
	 * @param n
	 */
	private static void sumDivide(int n) {
		distinctValues = new Boolean[n + 1];

		for (int ind = 0; ind < distinctValues.length; ind++) {
			distinctValues[ind] = false;
		}

		result = new HashSet<String>();
		backtracking(1, n, n);
	}

	public static void main(String[] args) {
		System.out.println("Test 1, n = 10");
		sumDivide(10);
		printSet(result);

		System.out.println("Test 2, n = 20");
		sumDivide(20);
		printSet(result);

		long startTime = System.currentTimeMillis();
		System.out.println("Test 3, n = 120");
		sumDivide(120);
		// printSet(result);
		System.out.println("Size " + result.size());

		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Time run: " + totalTime / 1000 + "s");
	}

}
