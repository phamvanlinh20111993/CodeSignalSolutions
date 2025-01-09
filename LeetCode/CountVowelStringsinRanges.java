package LeetCode;

/**
 * url: https://leetcode.com/problems/count-vowel-strings-in-ranges/description/
 * 
 * 2559. Count Vowel Strings in Ranges
Solved
Medium
Topics
Companies
Hint
You are given a 0-indexed array of strings words and a 2D array of integers queries.

Each query queries[i] = [li, ri] asks us to find the number of strings present in the range li to ri (both inclusive) of words that start and end with a vowel.

Return an array ans of size queries.length, where ans[i] is the answer to the ith query.

Note that the vowel letters are 'a', 'e', 'i', 'o', and 'u'.

 

Example 1:

Input: words = ["aba","bcb","ece","aa","e"], queries = [[0,2],[1,4],[1,1]]
Output: [2,3,0]
Explanation: The strings starting and ending with a vowel are "aba", "ece", "aa" and "e".
The answer to the query [0,2] is 2 (strings "aba" and "ece").
to query [1,4] is 3 (strings "ece", "aa", "e").
to query [1,1] is 0.
We return [2,3,0].
Example 2:

Input: words = ["a","e","i"], queries = [[0,2],[0,1],[2,2]]
Output: [3,2,1]
Explanation: Every string satisfies the conditions, so we return [3,2,1].
 

Constraints:

1 <= words.length <= 105
1 <= words[i].length <= 40
words[i] consists only of lowercase English letters.
sum(words[i].length) <= 3 * 105
1 <= queries.length <= 105
0 <= li <= ri < words.length
 */
public class CountVowelStringsinRanges {
	
	private boolean isVowelStringInRange(String str) {
		char s = str.charAt(0), e = str.charAt(str.length() - 1);
		return (s == 'a' || s == 'e' || s == 'i' || s == 'o' || s == 'u')
				&& (e == 'a' || e == 'e' || e == 'i' || e == 'o' || e == 'u');
	}

	public int[] vowelStrings(String[] words, int[][] queries) {
		int[] prefixSum = new int[words.length];
		prefixSum[0] = isVowelStringInRange(words[0]) ? 1 : 0;
		for (int ind = 1; ind < words.length; ind++) {
			prefixSum[ind] = prefixSum[ind - 1] + (isVowelStringInRange(words[ind]) ? 1 : 0);
		}

		int[] res = new int[queries.length];
		for (int ind = 0; ind < queries.length; ind++) {
			if (queries[ind][0] == 0) {
				res[ind] = prefixSum[queries[ind][1]];
			} else {
				res[ind] = prefixSum[queries[ind][1]] - prefixSum[queries[ind][0] - 1];
			}
		}

		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
