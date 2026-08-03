package LeetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * url: https://leetcode.com/problems/stone-game-iii/description/?envType=daily-question&envId=2026-08-03
 * 
 * Alice and Bob continue their games with piles of stones. There are several stones arranged in a row, and each stone has an associated value which is an integer given in the array stoneValue.

Alice and Bob take turns, with Alice starting first. On each player's turn, that player can take 1, 2, or 3 stones from the first remaining stones in the row.

The score of each player is the sum of the values of the stones taken. The score of each player is 0 initially.

The objective of the game is to end with the highest score, and the winner is the player with the highest score and there could be a tie. The game continues until all the stones have been taken.

Assume Alice and Bob play optimally.

Return "Alice" if Alice will win, "Bob" if Bob will win, or "Tie" if they will end the game with the same score.

 

Example 1:

Input: stoneValue = [1,2,3,7]
Output: "Bob"
Explanation: Alice will always lose. Her best move will be to take three piles and the score become 6. Now the score of Bob is 7 and Bob wins.
Example 2:

Input: stoneValue = [1,2,3,-9]
Output: "Alice"
Explanation: Alice must choose all the three piles at the first move to win and leave Bob with negative score.
If Alice chooses one pile her score will be 1 and the next move Bob's score becomes 5. In the next move, Alice will take the pile with value = -9 and lose.
If Alice chooses two piles her score will be 3 and the next move Bob's score becomes 3. In the next move, Alice will take the pile with value = -9 and also lose.
Remember that both play optimally so here Alice will choose the scenario that makes her win.
Example 3:

Input: stoneValue = [1,2,3,6]
Output: "Tie"
Explanation: Alice cannot win this game. She can end the game in a draw if she decided to choose all the first three piles, otherwise she will lose.
 

Constraints:

1 <= stoneValue.length <= 5 * 104
-1000 <= stoneValue[i] <= 1000
 * 
 */
class Solution {

	public int sumFromStart(int[] piles, int start, int amount) {
		int sum = 0;
		for (int ind = start; ind < start + amount; ind++) {
			sum += piles[ind];
		}
		return sum;
	}

	Map<String, Integer[]> memo;

	public Integer[] dp(int[] piles, int start, String user) {
		if (start >= piles.length) {
			return new Integer[] { 0, 0 };
		}
		String key = user + "-" + start;
		if (memo.containsKey(key)) {
			return memo.get(key);
		}
		if ("Alice".equals(user)) {
			Integer maxAliceScore = -50000000;
			Integer followingBobScore = -50000000;
			for (int x = 1; x <= 3; x++) {
				if (start + x > piles.length)
					break;
				int sumPiles = sumFromStart(piles, start, x);
				Integer[] scores = dp(piles, start + x, "Bob");
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

		Integer maxBobScore = -50000000;
		Integer followingAliceScore = -50000000;
		for (int x = 1; x <= 3; x++) {
			if (start + x > piles.length)
				break;
			int sumPiles = sumFromStart(piles, start, x);
			Integer[] scores = dp(piles, start + x, "Alice");
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

	public String stoneGameIII(int[] stoneValue) {
		memo = new HashMap<>();
		Integer[] res = dp(stoneValue, 0, "Alice");
	//	System.out.println("Alice=" + res[0] + ", Bob=" + res[1]);
		return res[0].equals(res[1]) ? "Tie" : res[0] > res[1] ? "Alice" : "Bob";
	}
}

public class Stone_Game_III_1406 {

	public static int[] generateRandomArray(int size, int min, int max) {
		Random random = new Random();
		int[] arr = new int[size];

		for (int i = 0; i < size; i++) {
			arr[i] = random.nextInt(max - min + 1) + min;
		}

		return arr;
	}

	public static void printArray(int[] arr) {
		System.out.print("[");
		for (int i = 0; i < arr.length; i++) {
			if (i > 0)
				System.out.print(",");
			System.out.print(arr[i]);
		}
		System.out.println("]");
	}

	public static void main(String[] args) {
		Solution s = new Solution();
		int[] arr = generateRandomArray(50000, -1000, 1000);
		printArray(arr);
		System.out.println(s.stoneGameIII(arr));
	}

}
