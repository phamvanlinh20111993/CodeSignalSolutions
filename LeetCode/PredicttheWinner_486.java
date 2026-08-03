package LeetCode;

/**
 * url: https://leetcode.com/problems/predict-the-winner/description/?envType=daily-question&envId=2026-08-01
 * You are given an integer array nums. Two players are playing a game with this array: player 1 and player 2.

Player 1 and player 2 take turns, with player 1 starting first. Both players start the game with a score of 0. At each turn, the player takes one of the numbers from either end of the array (i.e., nums[0] or nums[nums.length - 1]) which reduces the size of the array by 1. The player adds the chosen number to their score. The game ends when there are no more elements in the array.

Return true if Player 1 can win the game. If the scores of both players are equal, then player 1 is still the winner, and you should also return true. You may assume that both players are playing optimally.

 

Example 1:

Input: nums = [1,5,2]
Output: false
Explanation: Initially, player 1 can choose between 1 and 2. 
If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2). 
So, final score of player 1 is 1 + 2 = 3, and player 2 is 5. 
Hence, player 1 will never be the winner and you need to return false.
Example 2:

Input: nums = [1,5,233,7]
Output: true
Explanation: Player 1 first chooses 1. Then player 2 has to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.
Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.
 

Constraints:

1 <= nums.length <= 20
0 <= nums[i] <= 107
 */
public class PredicttheWinner_486 {
	 /*  
    public void recursive(int[] nums, int start, int end, int turn, String path, long[] points){
        if(end < start){
        //    if(points[0]  > points[1]){
                System.out.println(path + ", person 1: " + points[0] + ", person 2: " +  points[1]);
        //    }
            return;
        }
        
        points[turn] += nums[start];
        recursive(nums, start+1, end, (turn + 1) % 2, path + "["+ start +"]", points);
        points[turn] -= nums[start];

        if(end == start) return;

        points[turn] += nums[end];
        recursive(nums, start, end-1, (turn + 1) % 2, path + "[" + end +"]", points);
        points[turn] -= nums[end];
    }

    public boolean predictTheWinner(int[] nums) {
       recursive(nums, 0, nums.length -1, 0, "", new long[2]);
        return false;
    } */

	public int[] dp(int[] nums, int start, int end, int user) {
		if (start > end) {
			return new int[] { 0, 0 };
		}
		if (user == 0) {
			int[] maxLeftScoreArr = dp(nums, start + 1, end, 1);
			int[] maxRightScoreArr = dp(nums, start, end - 1, 1);

			// user 1
			int leftScore1 = maxLeftScoreArr[0] + nums[start];
			int rightScore1 = maxRightScoreArr[0] + nums[end];

			int maxScoreUser2 = 0;
			int maxScoreUser1 = 0;

			maxScoreUser1 = rightScore1;
			maxScoreUser2 = maxRightScoreArr[1];

			if (leftScore1 > rightScore1) {
				maxScoreUser1 = leftScore1;
				maxScoreUser2 = maxLeftScoreArr[1];
			}
			return new int[] { maxScoreUser1, maxScoreUser2 };
		}

		int[] maxLeftScoreArr = dp(nums, start + 1, end, 0);
		int[] maxRightScoreArr = dp(nums, start, end - 1, 0);

		// user 2
		int leftScore = maxLeftScoreArr[1] + nums[start];
		int rightScore = maxRightScoreArr[1] + nums[end];

		int maxScoreUser2 = 0;
		int maxScoreUser1 = 0;

		maxScoreUser1 = maxRightScoreArr[0];
		maxScoreUser2 = rightScore;
		if (leftScore > rightScore) {
			maxScoreUser1 = maxLeftScoreArr[0];
			maxScoreUser2 = leftScore;
		}

		return new int[] { maxScoreUser1, maxScoreUser2 };
	}

	public boolean predictTheWinner(int[] nums) {
		int[] res = dp(nums, 0, nums.length - 1, 0);
		return res[0] >= res[1];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
