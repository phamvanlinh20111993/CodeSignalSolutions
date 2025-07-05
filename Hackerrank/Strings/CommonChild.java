package Hackerrank.Strings;

/**
 * Url: https://www.hackerrank.com/challenges/common-child/problem?isFullScreen=true
 * 
 * A string is said to be a child of a another string if it can be formed by deleting 0 or more characters from the other string. Letters cannot be rearranged. Given two strings of equal length, what's the longest string that can be constructed such that it is a child of both?

Example



These strings have two children with maximum length 3, ABC and ABD. They can be formed by eliminating either the D or C from both strings. Return .

Function Description

Complete the commonChild function in the editor below.

commonChild has the following parameter(s):

string s1: a string
string s2: another string
Returns

int: the length of the longest string which is a common child of the input strings
Input Format

There are two lines, each with a string,  and .

Constraints

 where  means "the length of "
All characters are upper case in the range ascii[A-Z].
Sample Input

HARRY
SALLY
Sample Output

 2
Explanation

The longest string that can be formed by deleting zero or more characters from  and  is , whose length is 2.

Sample Input 1

AA
BB
Sample Output 1

0
Explanation 1

 and  have no characters in common and hence the output is 0.

Sample Input 2

SHINCHAN
NOHARAAA
Sample Output 2

3
Explanation 2

The longest string that can be formed between  and  while maintaining the order is .

Sample Input 3

ABCDEF
FBDAMN
Sample Output 3

2
Explanation 3
 is the longest child of the given strings.
 */
public class CommonChild {
	/*
	 * Complete the 'commonChild' function below.
	 *
	 * The function is expected to return an INTEGER. The function accepts following
	 * parameters: 1. STRING s1 2. STRING s2
	 * 
	 * s[i][j] = max(s[i][j-1], s[i-1][j-1]), if(s[i] != s[j]) = 1 + s[i-1][j-1]
	 * if(s[i] == s[j])
	 */
	static int[][] maxL;

	public static int memorize(int i, int j, String s1, String s2) {
		if (i < 0 || j < 0 || i >= s1.length() || j >= s2.length()) {
			return 0;
		}

		if (maxL[i][j] > -1)
			return maxL[i][j];

		int m = 0;
		if (s1.charAt(i) == s2.charAt(j)) {
			m++;
			m += memorize(i - 1, j - 1, s1, s2);
		} else {
			m = Math.max(memorize(i, j - 1, s1, s2), memorize(i - 1, j, s1, s2));
		}
		maxL[i][j] = m;

		return maxL[i][j];
	}

	public static int commonChild(String s1, String s2) {
		// Write your code here
		int s1L = s1.length(), s2L = s2.length();
		maxL = new int[s1L][s2L];

		for (int i = 0; i < s1L; i++) {
			for (int j = 0; j < s2L; j++) {
				maxL[i][j] = -1;
			}
		}

		return memorize(s1L - 1, s2L - 1, s1, s2);
	}
}
