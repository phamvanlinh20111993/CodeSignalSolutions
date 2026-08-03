package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * url: https://leetcode.com/problems/stone-game-ii/?q=Stone+game+1
 * 
 * Alice and Bob continue their games with piles of stones. There are a number of piles arranged in a row, and each pile has a positive integer number of stones piles[i]. The objective of the game is to end with the most stones.

Alice and Bob take turns, with Alice starting first.

On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M. Then, we set M = max(M, X). Initially, M = 1.

The game continues until all the stones have been taken.

Assuming Alice and Bob play optimally, return the maximum number of stones Alice can get.

 

Example 1:

Input: piles = [2,7,9,4,4]

Output: 10

Explanation:

If Alice takes one pile at the beginning, Bob takes two piles, then Alice takes 2 piles again. Alice can get 2 + 4 + 4 = 10 stones in total.
If Alice takes two piles at the beginning, then Bob can take all three piles left. In this case, Alice get 2 + 7 = 9 stones in total.
So we return 10 since it's larger.

Example 2:

Input: piles = [1,2,3,4,5,100]

Output: 104

 

Constraints:

1 <= piles.length <= 100
1 <= piles[i] <= 104

 */
public class Stone_Game_II_1140 {

	public int sumFromStart(int[] piles, int start, int amount) {
		int sum = 0;
		for (int ind = start; ind < start + amount; ind++) {
			sum += piles[ind];
		}
		return sum;
	}

	Map<String, Integer[]> memo;

	public Integer[] dp(int[] piles, int start, int M, String user) {
		if (start >= piles.length) {
			return new Integer[] { 0, 0 };
		}
		String key = user + "-" + start + "-" + M;

		if (memo.containsKey(key)) {
			return memo.get(key);
		}

		if ("Alice".equals(user)) {
			Integer maxAliceScore = 0;
			Integer followingBobScore = 0;

			for (int x = 1; x <= 2 * M; x++) {
				if (start + x > piles.length)
					break;
				int sumPiles = sumFromStart(piles, start, x);
				Integer[] scores = dp(piles, start + x, Math.max(M, x), "Bob");
				Integer aliceScore = sumPiles + scores[0];
				if (aliceScore > maxAliceScore) {
					maxAliceScore = aliceScore;
					followingBobScore = scores[1];
				}
			}
			Integer[] t = new Integer[] { maxAliceScore, followingBobScore };
			memo.put(key, t);
			return t;
		}

		Integer maxBobScore = 0;
		Integer followingAliceScore = 0;
		for (int x = 1; x <= 2 * M; x++) {
			if (start + x > piles.length)
				break;
			int sumPiles = sumFromStart(piles, start, x);
			Integer[] scores = dp(piles, start + x, Math.max(M, x), "Alice");
			Integer bobScore = sumPiles + scores[1];
			if (bobScore > maxBobScore) {
				maxBobScore = bobScore;
				followingAliceScore = scores[0];
			}
		}

		Integer[] t = new Integer[] { followingAliceScore, maxBobScore };
		memo.put(key, t);
		return t;
	}

	public int stoneGameII(int[] piles) {
		memo = new HashMap<>();
		Integer[] res = dp(piles, 0, 1, "Alice");
		return res[0];
	}

	public static void main(String[] args) {
		

	}

}
