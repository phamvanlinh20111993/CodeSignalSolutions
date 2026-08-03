package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * url: https://leetcode.com/problems/stone-game/description/?envType=daily-question&envId=2026-08-02
 * 
 * Alice and Bob play a game with piles of stones. There are an even number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].

The objective of the game is to end with the most stones. The total number of stones across all the piles is odd, so there are no ties.

Alice and Bob take turns, with Alice starting first. Each turn, a player takes the entire pile of stones either from the beginning or from the end of the row. This continues until there are no more piles left, at which point the person with the most stones wins.

Assuming Alice and Bob play optimally, return true if Alice wins the game, or false if Bob wins.

 

Example 1:

Input: piles = [5,3,4,5]
Output: true
Explanation: 
Alice starts first, and can only take the first 5 or the last 5.
Say she takes the first 5, so that the row becomes [3, 4, 5].
If Bob takes 3, then the board is [4, 5], and Alice takes 5 to win with 10 points.
If Bob takes the last 5, then the board is [3, 4], and Alice takes 4 to win with 9 points.
This demonstrated that taking the first 5 was a winning move for Alice, so we return true.
Example 2:

Input: piles = [3,7,2,3]
Output: true
 

Constraints:

2 <= piles.length <= 500
piles.length is even.
1 <= piles[i] <= 500
sum(piles[i]) is odd.
 

 */

public class Stone_Game_877 {
	Map<String, Integer[]> rememberStep;

	public Integer[] dp(int[] nums, int start, int end, String user) {

		if (start > end) {
			return new Integer[] { 0, 0 };
		}

		String key = user + "-" + start + "-" + end;
		if (rememberStep.containsKey(key)) {
			return rememberStep.get(key);
		}

		if (user == "Alice") {
			Integer[] maxLeftPilesArr = dp(nums, start + 1, end, "Bob");
			Integer[] maxRightPilesArr = dp(nums, start, end - 1, "Bob");

			// user 1
			int leftPiles = maxLeftPilesArr[0] + nums[start];
			int rightPiles = maxRightPilesArr[0] + nums[end];

			int maxPilesUser1 = rightPiles;
			int maxPilesUser2 = maxRightPilesArr[1];

			if (leftPiles > rightPiles) {
				maxPilesUser1 = leftPiles;
				maxPilesUser2 = maxLeftPilesArr[1];
			}

			Integer[] res = new Integer[] { maxPilesUser1, maxPilesUser2 };
			rememberStep.put(key, res);
			return res;
		}

		Integer[] maxLeftPilesArr = dp(nums, start + 1, end, "Alice");
		Integer[] maxRightPilesArr = dp(nums, start, end - 1, "Alice");

		// user 2
		int leftPiles = maxLeftPilesArr[1] + nums[start];
		int rightPiles = maxRightPilesArr[1] + nums[end];

		int maxPilesUser1 = maxRightPilesArr[0];
		int maxPilesUser2 = rightPiles;
		if (leftPiles > rightPiles) {
			maxPilesUser1 = maxLeftPilesArr[0];
			maxPilesUser2 = leftPiles;
		}

		Integer[] res = new Integer[] { maxPilesUser1, maxPilesUser2 };
		rememberStep.put(key, res);
		return res;
	}

	public boolean stoneGame(int[] piles) {
		rememberStep = new HashMap<>();
		Integer[] res = dp(piles, 0, piles.length - 1, "Alice");
		return res[0] >= res[1];
	}

	public static void main(String[] args) {

	}

}
