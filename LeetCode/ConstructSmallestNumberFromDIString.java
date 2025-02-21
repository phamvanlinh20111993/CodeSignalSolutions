package LeetCode;

/**
 * Url: https://leetcode.com/problems/construct-smallest-number-from-di-string
 * You are given a 0-indexed string pattern of length n consisting of the
 * characters 'I' meaning increasing and 'D' meaning decreasing.
 * 
 * A 0-indexed string num of length n + 1 is created using the following
 * conditions:
 * 
 * num consists of the digits '1' to '9', where each digit is used at most once.
 * If pattern[i] == 'I', then num[i] < num[i + 1]. If pattern[i] == 'D', then
 * num[i] > num[i + 1]. Return the lexicographically smallest possible string
 * num that meets the conditions.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: pattern = "IIIDIDDD" Output: "123549876" Explanation: At indices 0, 1,
 * 2, and 4 we must have that num[i] < num[i+1]. At indices 3, 5, 6, and 7 we
 * must have that num[i] > num[i+1]. Some possible values of num are
 * "245639871", "135749862", and "123849765". It can be proven that "123549876"
 * is the smallest possible num that meets the conditions. Note that "123414321"
 * is not possible because the digit '1' is used more than once. Example 2:
 * 
 * Input: pattern = "DDD" Output: "4321" Explanation: Some possible values of
 * num are "9876", "7321", and "8742". It can be proven that "4321" is the
 * smallest possible num that meets the conditions.
 * 
 * 
 * Constraints:
 * 
 * 1 <= pattern.length <= 8 pattern consists of only the letters 'I' and 'D'.
 */
public class ConstructSmallestNumberFromDIString {

	Long valid;

	public void backtrack(String pattern, int nextVal, int currP, int sign, String acc, boolean[] isVisited) {

		if (acc.length() - 1 == pattern.length()) {
			// System.out.println(acc);
			Long vl = Long.valueOf(acc);
			valid = vl < valid ? vl : valid;
			return;
		}

		// increase
		if (sign == 1) {
			for (int ind = nextVal; ind < 10; ind++) {
				if (!isVisited[ind]) {
					isVisited[ind] = true;
					sign = currP < pattern.length() && pattern.charAt(currP) == 'I' ? 1 : 0;
					backtrack(pattern, ind, currP + 1, sign, acc + ind, isVisited);
					isVisited[ind] = false;
				}
			}
			// decrease
		} else {
			for (int ind = nextVal; ind > 0; ind--) {
				if (!isVisited[ind]) {
					isVisited[ind] = true;
					sign = currP < pattern.length() && pattern.charAt(currP) == 'I' ? 1 : 0;
					backtrack(pattern, ind, currP + 1, sign, acc + ind, isVisited);
					isVisited[ind] = false;
				}
			}
		}
	}

	public String smallestNumber(String pattern) {
		boolean[] isVisited = new boolean[10];
		valid = 987654321l;

		int sign = pattern.charAt(0) == 'I' ? 1 : 0;
		backtrack(pattern, sign == 1 ? 1 : 9, 0, sign, "", isVisited);

		return "" + valid;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
