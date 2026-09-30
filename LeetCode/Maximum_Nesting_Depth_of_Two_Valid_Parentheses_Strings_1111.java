package LeetCode;

/**
 * 
 * url: https://leetcode.com/problems/maximum-nesting-depth-of-two-valid-parentheses-strings/description/?envType=daily-question&envId=2026-09-30
 * 
 * A string is a valid parentheses string (denoted VPS) if and only if it consists of "(" and ")" characters only, and:

It is the empty string, or
It can be written as AB (A concatenated with B), where A and B are VPS's, or
It can be written as (A), where A is a VPS.
We can similarly define the nesting depth depth(S) of any VPS S as follows:

depth("") = 0
depth(A + B) = max(depth(A), depth(B)), where A and B are VPS's
depth("(" + A + ")") = 1 + depth(A), where A is a VPS.
For example, "", "()()", and "()(()())" are VPS's (with nesting depths 0, 1, and 2), and ")(" and "(()" are not VPS's.

Given a VPS seq, split it into two disjoint subsequences A and B, such that A and B are VPS's (and A.length + B.length = seq.length). The subsequences may not necessarily be contiguous.

For example, for the sequence 123456789, one possible split is:

A = {1, 3, 5, 7, 9},

B = {2, 4, 6, 8}.

This corresponds to the output [0, 1, 0, 1, 0, 1, 0, 1, 0]  where 0 indicates membership in A and 1 indicates membership in B.

Now choose any such A and B such that max(depth(A), depth(B)) is the minimum possible value.

Return an answer array (of length seq.length) that encodes such a choice of A and B:  answer[i] = 0 if seq[i] is part of A, else answer[i] = 1.  Note that even though multiple answers may exist, you may return any of them.

 

Example 1:

Input: seq = "(()())"
Output: [0,1,1,1,1,0]
Example 2:

Input: seq = "()(())()"
Output: [0,0,0,1,1,0,1,1]
 

Constraints:

1 <= seq.size <= 10000
 

 */
public class Maximum_Nesting_Depth_of_Two_Valid_Parentheses_Strings_1111 {

	public int[] maxDepthAfterSplit(String seq) {
		int l = seq.length();
		int[] res = new int[l];

		int maxDepth = 0;
		int depth = 0;
		for (int ind = 0; ind < l; ind++) {
			if (seq.charAt(ind) == '(') {
				depth++;
				maxDepth = Math.max(maxDepth, depth);
				continue;
			}

			if (seq.charAt(ind) == ')') {
				depth--;
			}
		}

		int maxDepthA = maxDepth / 2;
		int curDepthB = 0;
		for (int ind = 0; ind < l; ind++) {
			if (seq.charAt(ind) == '(') {
				if (curDepthB < maxDepthA) {
					res[ind] = 1;
					curDepthB++;
				}
				continue;
			}
			if (seq.charAt(ind) == ')') {
				if (curDepthB > 0) {
					res[ind] = 1;
					curDepthB--;
				}
			}
		}

		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
