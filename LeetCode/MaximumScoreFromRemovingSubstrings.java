package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * url: https://leetcode.com/problems/maximum-score-from-removing-substrings/description/?envType=daily-question&envId=2025-07-23
 * You are given a string s and two integers x and y. You can perform two types of operations any number of times.

Remove substring "ab" and gain x points.
For example, when removing "ab" from "cabxbae" it becomes "cxbae".
Remove substring "ba" and gain y points.
For example, when removing "ba" from "cabxbae" it becomes "cabxe".
Return the maximum points you can gain after applying the above operations on s.

 

Example 1:

Input: s = "cdbcbbaaabab", x = 4, y = 5
Output: 19
Explanation:
- Remove the "ba" underlined in "cdbcbbaaabab". Now, s = "cdbcbbaaab" and 5 points are added to the score.
- Remove the "ab" underlined in "cdbcbbaaab". Now, s = "cdbcbbaa" and 4 points are added to the score.
- Remove the "ba" underlined in "cdbcbbaa". Now, s = "cdbcba" and 5 points are added to the score.
- Remove the "ba" underlined in "cdbcba". Now, s = "cdbc" and 5 points are added to the score.
Total score = 5 + 4 + 5 + 5 = 19.
Example 2:

Input: s = "aabbaaxybbaabb", x = 5, y = 4
Output: 20
 

Constraints:

1 <= s.length <= 105
1 <= x, y <= 104
s consists of lowercase English letters.
 */
public class MaximumScoreFromRemovingSubstrings {

	record KeyValue(Character[] c, int p) {
	}

	public int maximumGain(String s, int x, int y) {
		Stack<Character> st = new Stack<>();
		KeyValue[] p = new KeyValue[2];

		if (x > y) {
			p[0] = new KeyValue(new Character[] { 'a', 'b' }, x);
			p[1] = new KeyValue(new Character[] { 'b', 'a' }, y);
		} else {
			p[0] = new KeyValue(new Character[] { 'b', 'a' }, y);
			p[1] = new KeyValue(new Character[] { 'a', 'b' }, x);
		}

		int point = 0;
		for (int ind = 0; ind < s.length(); ind++) {
			if (!st.isEmpty()) {
				if (p[0].c[0] == st.peek() && p[0].c[1] == s.charAt(ind)) {
					st.pop();
					point += p[0].p;
					continue;
				}
			}

			st.push(s.charAt(ind));
		}

		List<Character> list = new ArrayList<>(st);
		st = new Stack<>();
		for (int ind = 0; ind < list.size(); ind++) {
			if (!st.isEmpty()) {
				if (p[1].c[0] == st.peek() && p[1].c[1] == list.get(ind)) {
					st.pop();
					point += p[1].p;
					continue;
				}
			}

			st.push(list.get(ind));
		}

		return point;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
