package Codesignal.Arcade;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlphanumericLess {

	static String[] splitTokenArr(String s1) {
		ArrayList<String> containes = new ArrayList<>();
		String pattern = "(\\d+)|([a-zA-Z])";
		Pattern r = Pattern.compile(pattern);
		// Now create matcher object.
		Matcher m = r.matcher(s1);
		while (m.find()) {
			containes.add(m.group());
		}

		return containes.toArray(new String[containes.size()]);
	}

	static boolean checkStrIsNumber(String s) {
		return s.matches("-?\\d+(\\.\\d+)?");
	}

	static int caculateZero(String s) {
		String pattern = "^[0]+";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(s);

		return m.find() ? m.group().length() : 0;
	}
	
	static String ignoreLeadZero(String s) {
		int index = 0;
		while(index < s.length() && s.charAt(index++)=='0');
			
		if(s.substring(index - 1, s.length()).equals("")) return "0";
	    else return s.substring(index - 1, s.length());
	}
	

	static boolean alphanumericLess(String s1, String s2) {
		int i;
		boolean numberZero = false, isCheck = false;
		String[] tokenArr1 = splitTokenArr(s1), tokenArr2 = splitTokenArr(s2);

		for (i = 0;; i++) {
			if (i == tokenArr1.length || i == tokenArr2.length)
				break;

			if (checkStrIsNumber(tokenArr1[i]) && checkStrIsNumber(tokenArr2[i])) {
				String pos1 = ignoreLeadZero(tokenArr1[i]),
					   pos2 = ignoreLeadZero(tokenArr2[i]);
				//can not using number to compare...hell in here 
			    if(pos1.length() > pos2.length()) {
			    	return false;
			    }else if(pos1.length() < pos2.length()) {
			    	return true;
			    }else {
					if (pos1.compareTo(pos2) > 0) {
						return false;
					} else if (pos1.compareTo(pos2) < 0) {
						return true;
					} else {
						if (!isCheck) {
							numberZero = caculateZero(tokenArr1[i]) > caculateZero(tokenArr2[i]) ? true : false;
							isCheck = true;
						}
					}
			    }
			} else {
				if (tokenArr1[i].compareTo(tokenArr2[i]) < 0) {
					return true;
				}
				if (tokenArr1[i].compareTo(tokenArr2[i]) > 0) {
					return false;
				}
			}
		}

		if (numberZero)
			return numberZero;

		return tokenArr1.length < tokenArr2.length ? true : false;
	}

	public static void main(String[] args) {

//		System.out.println("################### test 1 ######################");
//		String s1 = "ab000144", s2 = "ab144";
//		System.out.println(alphanumericLess(s1, s2));

//		System.out.println("################### test 2 ######################");
//		String s11 = "9223372036854775807",
//				s21 = "9223372036854775808";
//		System.out.println(alphanumericLess(s11, s21));
		
		System.out.println("################### test 3 ######################");
		String s3 = "ab123", s4 = "ab34z";
		System.out.println(alphanumericLess(s3, s4));


//		String a = "18446744073709551615", b = "18446744073709551616";
//		if (a.compareTo(b) > 0) {
//			System.out.println(a + " > " + b);
//		} else if (a.compareTo(b) < 0) {
//			System.out.println(a + " < " + b);
//		} else {
//			System.out.println(a + " = " + b);
//		}
//		
//		System.out.println(Double.parseDouble("18446744073709551615") > Double.parseDouble("18446744073709551616"));
//		
//		System.out.println(ignoreLeadZero("0000020000000002992"));
	}

}
