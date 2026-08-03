package LeetCode;

/**
 * i have idea but still need solution from leetcode to solve this problem. That
 * mean this solve by reference answer. THanks
 */
/**
 * url: https://leetcode.com/problems/concatenate-non-zero-digits-and-multiply-by-sum-ii/description/?envType=daily-question&envId=2026-07-08
 * 
 * You are given a string s of length m consisting of digits. You are also given a 2D integer array queries, where queries[i] = [li, ri].

For each queries[i], extract the substring s[li..ri]. Then, perform the following:

Form a new integer x by concatenating all the non-zero digits from the substring in their original order. If there are no non-zero digits, x = 0.
Let sum be the sum of digits in x. The answer is x * sum.
Return an array of integers answer where answer[i] is the answer to the ith query.

Since the answers may be very large, return them modulo 109 + 7.

 

Example 1:

Input: s = "10203004", queries = [[0,7],[1,3],[4,6]]

Output: [12340, 4, 9]

Explanation:

s[0..7] = "10203004"
x = 1234
sum = 1 + 2 + 3 + 4 = 10
Therefore, answer is 1234 * 10 = 12340.
s[1..3] = "020"
x = 2
sum = 2
Therefore, the answer is 2 * 2 = 4.
s[4..6] = "300"
x = 3
sum = 3
Therefore, the answer is 3 * 3 = 9.
Example 2:

Input: s = "1000", queries = [[0,3],[1,1]]

Output: [1, 0]

Explanation:

s[0..3] = "1000"
x = 1
sum = 1
Therefore, the answer is 1 * 1 = 1.
s[1..1] = "0"
x = 0
sum = 0
Therefore, the answer is 0 * 0 = 0.
Example 3:

Input: s = "9876543210", queries = [[0,9]]

Output: [444444137]

Explanation:

s[0..9] = "9876543210"
x = 987654321
sum = 9 + 8 + 7 + 6 + 5 + 4 + 3 + 2 + 1 = 45
Therefore, the answer is 987654321 * 45 = 44444444445.
We return 44444444445 modulo (109 + 7) = 444444137.
 

Constraints:

1 <= m == s.length <= 105
s consists of digits only.
1 <= queries.length <= 105
queries[i] = [li, ri]
0 <= li <= ri < m
 */
public class Concatenate_Non_Zero_Digits_and_Multiply_by_Sum_II_3756 {

	static int MOD = 1000000007;
	// formula: (a×b)%m = ((a%m)×(b%m))%m
	static long[] POW10 = new long[100001];// 1 <= m == s.length <= 10^5
	static {
		POW10[0] = 1;
		for (int ind = 1; ind < 100001; ind++) {
			POW10[ind] = (10 * POW10[ind - 1]) % MOD;
		}
	}

	public int[] sumAndMultiply(String s, int[][] queries) {
		int l = s.length();
		int[] prefixSum = new int[l];
		long[] prefixCombine = new long[l];
		int[] cnt = new int[l];
		prefixSum[0] = s.charAt(0) - '0';
		prefixCombine[0] = s.charAt(0) - '0';
		cnt[0] = s.charAt(0) - '0' > 0 ? 1 : 0;

		for (int ind = 1; ind < l; ind++) {
			int num = s.charAt(ind) - '0';
			prefixSum[ind] = prefixSum[ind - 1] + num;
			if (num > 0) {
				prefixCombine[ind] = (10 * prefixCombine[ind - 1] + num) % MOD;
				cnt[ind] = cnt[ind - 1] + 1;
			} else {
				prefixCombine[ind] = prefixCombine[ind - 1];
				cnt[ind] = cnt[ind - 1];
			}
		}

		int[] res = new int[queries.length];
		int ind = 0;
		// (a−b)modN=((amodN)−(bmodN))modN
		for (int[] query : queries) {
			long prefixCombineLeft = query[0] == 0 ? 0 : prefixCombine[query[0] - 1];
			int cntL = query[0] == 0 ? 0 : cnt[query[0] - 1];
			long leftN = (POW10[cnt[query[1]] - cntL] * prefixCombineLeft) % MOD;
			long rightN = prefixCombine[query[1]];

			long minusRL = (rightN - leftN + MOD) % MOD; // need reference to solve problem

			long prefixSumL = query[0] == 0 ? 0 : prefixSum[query[0] - 1];
			res[ind++] = (int) ((minusRL * (prefixSum[query[1]] - prefixSumL)) % MOD);
		}

		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
