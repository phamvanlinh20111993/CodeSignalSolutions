package LearnHowToUseGit;

import java.util.HashMap;
import java.util.Map;

public class ConvertFromRomanNumber {
	static int syntaxRomanNumber(String syntax) {
		syntax = syntax.toUpperCase().trim();
		Map<String, Integer> romanBase = new HashMap<String, Integer>();
		romanBase.put("I", 1);
		romanBase.put("V", 5);
		romanBase.put("X", 10);
		romanBase.put("L", 50);
		romanBase.put("C", 100);
		romanBase.put("D", 500);
		romanBase.put("M", 1000);
		romanBase.put("I", 1);
		return romanBase.containsKey(syntax) ? romanBase.get(syntax) : -1;

	}

	static int romanNumber(String romanNum) {
		int i, decimaNum = 0, previous = -1, current;

		for (i = 0; i < romanNum.length(); i++) {
			current = syntaxRomanNumber(Character.toString(romanNum.charAt(i)));
			decimaNum += previous > 0 && previous < current ? -2*previous + current : current;
			previous = current;
		}
		System.out.println(decimaNum);
		return decimaNum;
	}

	public static void main(String[] args) {
		System.out.println("############## test 1 #############");
		romanNumber("XX");

		System.out.println("############## test 2 #############");
		romanNumber("XIX");

		System.out.println("############## test 3 #############");
		romanNumber("MMCCLXVI");

	}

}