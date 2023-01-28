package Codesignal.Arcade;

public class Is_Unstable_Pair {
	/**
	 * 
	 * @param s1
	 * @param s2
	 * @return true if s1 > s2 and ortherwise
	 */
	static boolean comparePairString(String s1, String s2) {
		int minLength = s1.length() < s2.length() ? s1.length() : s2.length(), 
			i;
		for (i = 0; i < minLength; i++) {
			if (s1.charAt(i) > s2.charAt(i)) {
				return true;
			} else if (s1.charAt(i) < s2.charAt(i)) {
				return false;
			}
		}
	
		return s1.length() > s2.length();
	}

	static boolean isUnstablePair(String filename1, String filename2) {

		boolean compare = comparePairString(filename1, filename2),
				less = comparePairString(filename1.toLowerCase(), filename2.toLowerCase()),
				greater = comparePairString(filename1.toUpperCase(), filename2.toUpperCase());
		
		//System.out.println(compare + "   " + less + "  " + greater);
		System.out.println((compare && !less && !greater) || (!compare && less && greater));
		
		return (compare && !less && !greater) || (!compare && less && greater);
	}

	public static void main(String[] args) {

		System.out.println("############ Test 1 ################");
		String filename11 = "aa";
		String filename12 = "AAB";
		isUnstablePair(filename11, filename12);

		System.out.println("############ Test 2 ################");
		String filename13 = "A";
		String filename14 = "z";
		isUnstablePair(filename13, filename14);

		System.out.println("############ Test 3 ################");
		String filename15 = "yyyyyy";
		String filename16 = "Azzzzzzzzz";
		isUnstablePair(filename15, filename16);

		System.out.println("############ Test 4 ################");
		String filename17 = "mehOu";
		String filename18 = "mehau";
		isUnstablePair(filename17, filename18);

		System.out.println("############ Test 5 ################");
		String filename27 = "aaZ";
		String filename28 = "AAzz";
		isUnstablePair(filename27, filename28);

		System.out.println("############ Test 6 ################");
		String filename37 = "fdsAs";
		String filename38 = "dzdw";
		isUnstablePair(filename37, filename38);
		
		System.out.println("############ Test 7 ################");
		String filename47 = "aaad";
		String filename48 = "aaAdd";
		isUnstablePair(filename47, filename48);
		
		System.out.println("############ Test 8 ################");
		String filename57 = "zzzzzAa123";
		String filename58 = "zzzzza";
		isUnstablePair(filename57, filename58);
		
		System.out.println("############ Test 9 ################");
		String filename67 = "123za";
		String filename68 = "123Z";
		isUnstablePair(filename67, filename68);
		
		System.out.println("############ Test 10 ################");
		String filename77 = "qwerTyu123";
		String filename78 = "qwertyu";
		isUnstablePair(filename77, filename78);

	}

}
