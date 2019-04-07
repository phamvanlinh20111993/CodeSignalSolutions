package Challenge;

import java.util.ArrayList;
import java.util.HashMap;

public class SpellNumberEnglish_Recursive {

	public static String[] Numbers = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
			"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen",
			"nineteen", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety", "hundred" };
	public static int[] unit = { 3, 6, 9, 12, 15, 18, 21, 24, 27, 30 };
	public static HashMap<Integer, String> unitMap = new HashMap<>();

	/**
	 * Read number in range [0, 999]
	 * 
	 * @param numberSplit
	 * @return
	 */
	public static String spellUnitNumber(ArrayList<Integer> numberSplit) {
		String result = "";
		int hundreds = numberSplit.size() == 3 ? numberSplit.get(0) : 0,
				dozens = numberSplit.size() >= 2 ? numberSplit.get(numberSplit.size() - 2) : 0,
				units = numberSplit.size() >= 1 ? numberSplit.get(numberSplit.size() - 1) : 0;

		result += hundreds > 0 ? " " + Numbers[hundreds] + " " + Numbers[Numbers.length - 1] : "";
		result += dozens > 1 ? " " + Numbers[dozens + 18] : dozens > 0 ? " " + Numbers[dozens * 10 + units] : "";
		result += units > 0 ? dozens > 1 ? "-" + Numbers[units] : " " + Numbers[units] : "";

		return result;
	}

	/**
	 * Using recursive to spell number
	 * 
	 * @param n
	 * @param result
	 * 
	 */
	public static String RecursiveCardinalNumbers(ArrayList<Integer> queueNumber, String result) {
		int numberSize = queueNumber.size(), len = 0;

		while (numberSize - unit[len] >= 0)
			len++;
		int k = numberSize - (len > 0 ? unit[len - 1] : 0);

		k = k == 0 ? numberSize - (len > 1 ? unit[len - 2] : 0) : k;
		ArrayList<Integer> temp = new ArrayList<>();

		int v = k, s = -1;
		while (v > 0) {
			int tmp = queueNumber.remove(0);
			s = tmp > 0 ? 1 : s;
			temp.add(tmp);
			v--;
		}

		result += temp.size() > 0 ? spellUnitNumber(temp) : "";

		if (queueNumber.size() > 2 && k > 0 && s > 0) {
			result += " " + unitMap.get(queueNumber.size());
		}

		if (queueNumber.size() > 0) {
			return RecursiveCardinalNumbers(queueNumber, result);
		}

		return result.trim();
	}

	/**
	 * 
	 * check number
	 * 
	 * @param n
	 * @return
	 */
	public static String CardinalNumbers(long n) {
		String result = "";

		if (n < 0L) {
			n = -n;
			result += "minus";
		}

		if (n < 20) {
			result += Numbers[(int) n];
		} else {
			ArrayList<Integer> queueNumber = new ArrayList<Integer>();
			String num = String.valueOf(n);

			for (int i = 0; i < num.length(); i++)
				queueNumber.add(Character.getNumericValue(num.charAt(i)));

			result = RecursiveCardinalNumbers(queueNumber, result);
		}

		return result;
	}

	public static void main(String[] args) {
		unitMap.put(3, "thousand");
		unitMap.put(6, "million");
		unitMap.put(9, "billion");
		unitMap.put(12, "trillion");
		unitMap.put(15, "quadrillion");
		unitMap.put(18, "quintillion");
		unitMap.put(21, "sextillion");
		unitMap.put(24, "septillion");
		unitMap.put(27, "octillion");
		unitMap.put(30, "nonillion");

		System.out.println("############### test 1, n = 326638456 ###############");
		System.out.println(CardinalNumbers(326638456L));

		System.out.println("############### test 2, n = 1000000000 ###############");
		System.out.println(CardinalNumbers(1000000000L));

		System.out.println("############### test 3, n = 101208 ###############");
		System.out.println(CardinalNumbers(101208L));

		System.out.println("############### test 4, 4353451304060500 ###############");
		System.out.println(CardinalNumbers(4353451304060500L));

		System.out.println("############### test 5, n = 10331208 ###############");
		System.out.println(CardinalNumbers(10331208L));

		System.out.println("############### test 6, n = 350 ###############");
		System.out.println(CardinalNumbers(350L));

		System.out.println("############### test 7, n = 3550000000000000000 ###############");
		System.out.println(CardinalNumbers(3550000000000000000L));

		System.out.println("############### test 8, n = 10000 ###############");
		System.out.println(CardinalNumbers(10000L));

		System.out.println("############### test 9, n = 100000 ###############");
		System.out.println(CardinalNumbers(100000L));

		System.out.println("############### test 10, n = 1089005090 ###############");
		System.out.println(CardinalNumbers(1089005090L));

		System.out.println("############### test 11, n = 928239852375 ###############");
		System.out.println(CardinalNumbers(928239852375L));

		System.out.println("############### test 12, n = -260000433 ###############");
		System.out.println(CardinalNumbers(-260000433L));

		System.out.println("############### test 13, n = -24560000433L ###############");
		System.out.println(CardinalNumbers(-24560000433L));

		System.out.println("############### test 14, n = 9223372036854775807L ###############");
		System.out.println(CardinalNumbers(9223372036854775807L));

	}

}
