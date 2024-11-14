package LeetCode;

import java.util.Map;

public class RomanToInteger {

	public static int romanToInt(String s) {
		Map<String, Integer> romanMap = 
				Map.of("I", 1, "V", 5, "X", 10, "L", 50, "C", 100, "D", 500, "M", 1000);

		int res = 0;
		for (int ind = 0; ind < s.length();) {
			int nextVal = ind < s.length() - 1 ? romanMap.get(Character.toString(s.charAt(ind + 1))) : -1;
			int val = romanMap.get(Character.toString(s.charAt(ind)));

			if (nextVal > val) {
				ind++;
				res += nextVal - val;
			} else {
				res += val;
			}

			ind++;
		}

		return res;
	}

	public static void main(String[] args) {
	}

}
