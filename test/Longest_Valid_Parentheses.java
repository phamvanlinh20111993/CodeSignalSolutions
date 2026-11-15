package test;

public class Longest_Valid_Parentheses {

	public static int longestValidParentheses(String s) {
		int length = 0;
		int sL = s.length();
		for (int ind = 0; ind < sL;) {
			int am = 0;
			int i = ind;
			int newInd = ind + 1;
			for (; i < sL; i++) {
				if (s.charAt(i) == '(') {
					am++;
				} else if (s.charAt(i) == ')') {
					am--;
					if (am == 0) {
						length = Math.max(i - ind + 1, length);
						newInd = i;
					}
				}
				if (am < 0 || am > sL / 2) {
					break;
				}
			}

			ind = newInd;
		}

		return length;
	}
	
	public static void test1() {
		int n = 3 * 10000;

		String s = "(".repeat(n / 2) + ")".repeat(n / 2);

		System.out.println("length = " + s.length());
		System.out.println("s = " + s);

		long start = System.nanoTime();

		int result = longestValidParentheses(s);

		long end = System.nanoTime();

		System.out.println("result = " + result);
		System.out.println("time = " + (end - start) / 1_000_000.0 + " ms");
	}
	
	public static void test2() {
		int n = 3 * 10000;

		String s = "(".repeat(n - 1) + ")";

		System.out.println("length = " + s.length());

		long start = System.nanoTime();

		int result = longestValidParentheses(s);

		long end = System.nanoTime();

		System.out.println("result = " + result);
		System.out.println("time = " + (end - start) / 1_000_000.0 + " ms");
	}

	public static void main(String[] args) {
		System.out.println(
				"########################################### test 1 #########################################");
		test1();

		System.out.println(
				"########################################### test 2 #########################################");
		test2();

	}

}
