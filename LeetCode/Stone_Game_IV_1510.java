package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * url: https://leetcode.com/problems/stone-game-iv/description/?envType=daily-question&envId=2026-08-10
 * 
 * Alice and Bob take turns playing a game, with Alice starting first.

Initially, there are n stones in a pile. On each player's turn, that player makes a move consisting of removing any non-zero square number of stones in the pile.

Also, if a player cannot make a move, he/she loses the game.

Given a positive integer n, return true if and only if Alice wins the game otherwise return false, assuming both players play optimally.

 

Example 1:

Input: n = 1
Output: true
Explanation: Alice can remove 1 stone winning the game because Bob doesn't have any moves.
Example 2:

Input: n = 2
Output: false
Explanation: Alice can only remove 1 stone, after that Bob removes the last one winning the game (2 -> 1 -> 0).
Example 3:

Input: n = 4
Output: true
Explanation: n is already a perfect square, Alice can win with one move, removing 4 stones (4 -> 0).
 

Constraints:

1 <= n <= 105
 */
public class Stone_Game_IV_1510 {
	/*
	Map<String, Boolean[]> memo;

	public Boolean[] dp(int amountN, String currUser) {
		String key = amountN + currUser;
		if (memo.containsKey(key)) {
			return memo.get(key);
		}

		if (amountN <= 0) {
			memo.put(key, new Boolean[] { currUser == "Alice" ? false : true, currUser == "Bob" ? false : true });
			return memo.get(key);
		}

		if (currUser == "Alice") {
			for (int amountCanChoose = 1; amountCanChoose <= Math.sqrt(amountN); amountCanChoose++) {
				Boolean[] turns = dp(amountN - amountCanChoose * amountCanChoose, "Bob");
				if (turns[0]) {
					memo.put(key, new Boolean[] { true, false });
					return new Boolean[] { true, false };
				}
			}
			// Bob win
			memo.put(key, new Boolean[] { false, true });
			return new Boolean[] { false, true };
		}

		for (int amountCanChoose = 1; amountCanChoose <= Math.sqrt(amountN); amountCanChoose++) {
			Boolean[] turns = dp(amountN - amountCanChoose * amountCanChoose, "Alice");
			if (turns[1]) {
				memo.put(key, new Boolean[] { false, true });
				return new Boolean[] { false, true };
			}
		}
		// Alice win
		memo.put(key, new Boolean[] { true, false });
		return new Boolean[] { true, false };
	}

	public boolean winnerSquareGame(int n) {
		memo = new HashMap<>();
		Boolean[] re = dp(n, "Alice");
		return re[0];
	}
    */

	Boolean[][][] memo;

	public Boolean[] dp(int amountN, int currUser) {
		if (memo[amountN][currUser][0] != null) {
			return memo[amountN][currUser];
		}

		if (amountN <= 0) {
			memo[amountN][currUser] = currUser == 0 
					? new Boolean[] { false, true } 
			        : new Boolean[] { true, false };
			return memo[amountN][currUser];
		}

		if (currUser == 0) { // Alice
			for (int amountCanChoose = 1; amountCanChoose <= Math.sqrt(amountN); amountCanChoose++) {
				int next = amountN - amountCanChoose * amountCanChoose;
				Boolean[] turns = dp(next, 1);
				if (turns[0]) {
					memo[amountN][currUser] = new Boolean[] { true, false };
					return memo[amountN][currUser];
				}
			}

			memo[amountN][currUser] = new Boolean[] { false, true };
			return memo[amountN][currUser];
		}

		// Bob
		for (int amountCanChoose = 1; amountCanChoose <= Math.sqrt(amountN); amountCanChoose++) {
			int next = amountN - amountCanChoose * amountCanChoose;
			Boolean[] turns = dp(next, 0);
			if (turns[1]) {
				memo[amountN][currUser] = new Boolean[] { false, true };
				return memo[amountN][currUser];
			}
		}

		memo[amountN][currUser] = new Boolean[] { true, false };
		return memo[amountN][currUser];
	}

	public boolean winnerSquareGame(int n) {
		memo = new Boolean[n + 1][2][2];
		Boolean[] result = dp(n, 0);
		return result[0];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
