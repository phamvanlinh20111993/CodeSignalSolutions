package LeetCode;

public class Longest_Valid_Parentheses_32 {

	public int longestValidParentheses(String s) {
		int length = 0;
		int sL = s.length();
		int haftSL = sL / 2;
		for (int ind = 0; ind < sL;) {
			int am = 0;
			int newInd = ind + 1;
			for (int i = ind; i < sL; i++) {
				if (s.charAt(i) == '(') {
					am++;
				} else if (s.charAt(i) == ')') {
					am--;
					if (am == 0) {
						length = Math.max(i - ind + 1, length);
						newInd = i;
					}
				}
				if (am < 0 || am > haftSL) {
					break;
				}
			}
			ind = newInd;
		}

		return length;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
