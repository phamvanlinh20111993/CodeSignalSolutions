package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Url:
 * https://leetcode.com/problems/fraction-to-recurring-decimal/?envType=daily-question&envId=2025-09-24
 * 
 * Given two integers representing the numerator and denominator of a fraction,
 * return the fraction in string format.
 * 
 * If the fractional part is repeating, enclose the repeating part in
 * parentheses.
 * 
 * If multiple answers are possible, return any of them.
 * 
 * It is guaranteed that the length of the answer string is less than 104 for
 * all the given inputs.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: numerator = 1, denominator = 2 Output: "0.5" Example 2:
 * 
 * Input: numerator = 2, denominator = 1 Output: "2" Example 3:
 * 
 * Input: numerator = 4, denominator = 333 Output: "0.(012)"
 * 
 * 
 * Constraints:
 * 
 * -231 <= numerator, denominator <= 231 - 1 denominator != 0
 * 
 */
public class Fraction_to_Recurring_Decimal {
	
	public static String divideNoRecursive(long numerator, long denominator) {
		StringBuilder res = new StringBuilder("");

		Map<String, Integer> checkDupNums = new HashMap<String, Integer>();

		if (numerator < denominator) {
			res.append("0.");
		} else {
			long dt = numerator / denominator;
			res.append(dt);
			if (numerator % denominator == 0) {
				return res.toString();
			}
			res.append(".");
		}
		numerator = numerator % denominator;

		while (true) {
			if (checkDupNums.containsKey(numerator + " " + denominator)) {
				int start = checkDupNums.get(numerator + " " + denominator);
				int end = res.length();
				return res.substring(0, start) + "(" + res.substring(start, end) + ")";
			}
			checkDupNums.put(numerator + " " + denominator, res.length());

			if (numerator < denominator) {
				numerator *= 10;
				while (numerator < denominator) {
					numerator *= 10;
					res.append("0");
				}
			}

			long val = numerator / denominator;
			res.append(val);
			if (numerator % denominator == 0) {
				break;
			}
			numerator = numerator % denominator;
		}

		return res.toString();
	}

	static Map<String, Integer> checkDup;
	public static StringBuilder divide(int numerator, int denominator, boolean isFraction, int count) {
		StringBuilder res = new StringBuilder("");
		if (numerator < denominator) {
			if (!isFraction) {
				res.append("0.");
			}
			numerator *= 10;
			while (numerator < denominator) {
				numerator *= 10;
				res.append("0");
			}
		}

		int dt = numerator / denominator;
		res.append(dt);
		if (numerator % denominator == 0) {
			return res;
		} else {
			if (!isFraction && !res.toString().contains(".")) {
				res.append(".");
			}
		}
		if (checkDup.containsKey(numerator + " " + denominator)) {
	//		System.out.println("Start " + checkDup.get(numerator + " " + denominator) + " End " + count);
			return res;
		}
//		checkDup.put(numerator + " " + denominator, count);

		res.append(divide(numerator % denominator, denominator, true, count + 1));

		return res;
	}

	public static String fractionToDecimalRecursive(int numerator, int denominator) {
		String sign = numerator < 0 || denominator < 0 ? "-" : "";
		checkDup = new HashMap<>();
//		String result = divide(numerator < 0 ? 0 - numerator : numerator,
//				denominator < 0 ? 0 - denominator : denominator, false, 0).toString();
		
		String result = divideNoRecursive(numerator, denominator).toString();

		return sign + result;
	}
	
	public static String fractionToDecimal(int numerator, int denominator) {
		String sign = numerator < 0 || denominator < 0 ? "-" : "";
		if (numerator < 0 && denominator < 0)
			sign = "";
		if (numerator == 0)
			return "0";

		long numeratorL = numerator;
		long denominatorL = denominator;
		String result = divideNoRecursive(numeratorL < 0 ? 0L - numeratorL : numeratorL,
				denominatorL < 0 ? 0L - denominatorL : denominatorL);
		return sign + result;
	}

	public static void main(String[] args) {
		System.out.println("Test 1: " + fractionToDecimal(1, 2040));

		System.out.println("Test 2: " + fractionToDecimal(1000999, 9));

		System.out.println("Test 3: " + fractionToDecimal(3, 39));
		
		System.out.println("Test 4: " + fractionToDecimal(4, 333));
		
		System.out.println("Test 5: " + fractionToDecimal(-122, 2150));
		
		System.out.println("Test 6: " + fractionToDecimal(-122, 1230));
		
		
		int count = 7, max = 100000, min = -100000;
		//  92061/ -65299
		
		while (count < 1000) {
			int numerator = (int) (Math.random() * (max - min + 1) + min);
			int denominator = (int) (Math.random() * (max - min + 1) + min);
			System.out.println("Test " + count + ": " + numerator + " / " + denominator + " = "
					+ fractionToDecimal(numerator, denominator));
			count++;
		}
	}

}
