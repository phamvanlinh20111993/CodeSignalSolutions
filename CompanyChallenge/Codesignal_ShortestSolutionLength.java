package CompanyChallenge;

/**
 * 
 * @author PhamLinh
 * https://app.codesignal.com/company-challenges/codesignal/pMAdBWmkH9cEiZo4j
 * 
 * In the "Challenges" section on CodeSignal a user can solve one of the existing challenges or submit their own. Each challenge has its own scoreboard organized by programming language. Solutions in these scoreboards are sorted according to their length. When the length of a solution is calculated, all comments and spaces are ignored. Your task is to implement a simplified version of this length calculator.

Assume that there are only two types of comments:

line-comment: starting with '//' and ending at the end of the line;
block-comment: starting with '/*', and ending with the first occurrence of '*/'. It may span several lines.
/*Each non-space character outside comments adds 1 to the total length.

Note that all characters inside each comment are ignored, so they don't introduce nested comments. See the examples for more details.

Also note that outside any other comment both '//' and '/*' start a new comment (i.e. in this task you don't have to consider the cases where '//', '/*' or '*/' appear inside a string literal).

/*Example

For

source = ["int a = 2;",
          "int b = 47;/*37;*///41;",
          "int c = 3/*4//5*/;",
          "return a / b * c/*a /* b / c*/;"]
the output should be solution(source) = 34.

In the 1st line there are 7 non-space characters;
In the 2nd line there are 2 comments - /*37;*/ and //41;. Besides those there are only 8 non-space characters;
The 3rd line contains 1 comment - /*4//5*/, and 7 non-space characters;
The last line of code has 1 comment - /*a /* b / c*/, and 12 non-space characters;
In summary, there are 7 + 8 + 7 + 12 = 34 countable characters.
For

/*source = ["var a = 2;",
          "/*",
          "var b = 2;",
          "if (a === b) {",
          "  b = a + 1;",
          "  //b = a * 2 - 1;",
          "}",
          "*/",
          "var b = 3;",
          "return a * b;"] 
/*the output should be solution(source) = 24.

Here the code contains one block-comment that covers lines 2 to 8 (1-based). Other lines don't contain comments and have 7, 7, 10 symbols to count, respectively. So, the answer is 7 + 7 + 10 = 24.

Input/Output

[execution time limit] 3 seconds (java)

[input] array.string source

Guaranteed constraints:
1 ≤ source.length ≤ 10,
1 ≤ source[i].length ≤ 150.

[output] integer */
 *
 */
public class Codesignal_ShortestSolutionLength {
	 
	 /*int solution(String[] source) {
     int res = 0, i, j;
	String tmp;
	boolean commentMultiLine = false;

	for (i = 0; i < source.length; i++) {
		tmp = source[i];
		for (j = 0; j < tmp.length(); j++) {
			if (!commentMultiLine && j < tmp.length() - 1 && tmp.charAt(j) == '/') {
				if (tmp.charAt(j+1) == '/') {
					break;
				}
				if (tmp.charAt(j + 1) == '*') {
					commentMultiLine = true;
					j+=2;
				}
			}

			if (commentMultiLine && j < tmp.length() - 1 
					&& tmp.charAt(j) == '*' && tmp.charAt(j + 1) == '/') {
				commentMultiLine = false;
				j += 1;
				continue;
			}

			if (j < tmp.length() && !commentMultiLine && tmp.charAt(j) != ' ') {
				res++;
			}
		}
	}

	return res;
}
*/

	int solution(String[] source) {
		String tmp;
		int res = 0;
		boolean commentMultiLine = false;

		for (int i = 0; i < source.length; i++) {
			tmp = source[i];
			boolean isInString = false;
			Integer startCut = 0;
			for (int j = 0; j < tmp.length() - 1; j++) {
				// check is start or end of a string
				if (j > 0 && tmp.charAt(j - 1) != '\\' && tmp.charAt(j) == '"') {
					isInString = !isInString;
				}
				// oneline comment
				if (!commentMultiLine && !isInString && tmp.charAt(j) == '/' && tmp.charAt(j + 1) == '/') {
					if (j <= 0) {
						tmp = "";
					} else {
						tmp = tmp.substring(0, j);
					}
					break;
				}
				// mutiline comment
				if (!commentMultiLine && !isInString && tmp.charAt(j) == '/' && tmp.charAt(j + 1) == '*') {
					commentMultiLine = true;
					startCut = j;
					j++;
					continue;
				}
				if (commentMultiLine && !isInString && tmp.charAt(j) == '*' && tmp.charAt(j + 1) == '/') {
					commentMultiLine = false;
					tmp = tmp.substring(0, startCut) + tmp.substring(j + 2);
					j = startCut - 1;
				}
			}

			if (!commentMultiLine) {
				res += tmp.replaceAll("\\s*", "").length();
			}

		}

		return res;
	}

}
