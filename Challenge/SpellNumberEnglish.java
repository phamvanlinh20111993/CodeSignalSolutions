package Challenge;

import java.util.ArrayList;

public class SpellNumberEnglish {
	// quadrillion is milion bilion
	// quintillion is bilion bilion
	public static String[] Numbers = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
			"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen",
			"nineteen", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety" };
	// position from 26 to 32 in Numbers string array
	// hundred, thousand, million, billion, trillion, quadrillion, quintillion,
	// sextillion
	public static int[] unit = { 2, 3, 6, 9, 12, 15, 18, 21, 24, 27 };
	public static String[] spellNumber = { "hundred", "thousand", "million", "billion", "trillion", "quadrillion",
			"quintillion", "sextillion" };

	/**
	 * Using normal way
	 * 
	 * @param n
	 * @return
	 */
	public static String CardinalNumbers(long n) {
		
		String result = "";
		if(n < 0) {
			result += "minus ";
			n = -n;
		}
		
		// fake queue
		ArrayList<Integer> queueNumber = new ArrayList<Integer>();
		String num = String.valueOf(n);
		
		for (int i = 0; i < num.length(); i++)
			queueNumber.add(Character.getNumericValue(num.charAt(i)));

		if (n < Long.MIN_VALUE || n > Long.MAX_VALUE)
			return null;

		if (n <= 20) {
			return result + " " + Numbers[(int) n];
		} else {
			
			while (queueNumber.size() > 0) {
				int numberSize = queueNumber.size(), len = 0;
				while (numberSize - unit[len] > 0)
					len++;
				int k = numberSize - (len > 0 ? unit[len - 1] : 0);
				//
				if (k == 3 && queueNumber.get(0) > 0) {
					result += Numbers[queueNumber.get(0)] + " ";
					result += "hundred ";
					if (queueNumber.get(1) == 0 && queueNumber.get(2) == 0) {
						result += spellNumber[numberSize / 3-1] + " ";
					}
				}
				//
				if (k == 2) {
					if (queueNumber.get(0) > 1) {
						result += Numbers[queueNumber.get(0) + 18];
						if (queueNumber.get(1) > 0)
							result += "-";
						else
							result += " ";
					} else {
						int p = queueNumber.get(0);
						queueNumber.remove(0);
						int pos = p * 10 + queueNumber.get(0);

						result += pos > 0 ? Numbers[pos] + " " : "";
						result += (p > 0 || pos > 0) && numberSize > 3 ? spellNumber[numberSize / 3] + " " : "";
					}
				}
				// unit
				if (k == 1) {
					result += queueNumber.get(0) > 0 ? Numbers[queueNumber.get(0)] + " " : "";
					if (numberSize > 2) {
						if (numberSize == 3) {
							result += queueNumber.get(0) > 0 ? spellNumber[0] + " " : "";
						} else {
							result += spellNumber[numberSize / 3] + " ";
						}
					}
				}
				queueNumber.remove(0);
			}

			return result.trim();
		}
	}

	public static void main(String[] args) {
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
		
		System.out.println("############### test custom, n = 6 ###############");
		System.out.println(CardinalNumbers(6181906617156561043L));
	}

}
