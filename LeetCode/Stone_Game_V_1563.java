package LeetCode;

/**
 * url: https://leetcode.com/problems/stone-game-v/?envType=daily-question&envId=2026-08-17
 * 
 * There are several stones arranged in a row, and each stone has an associated value which is an integer given in the array stoneValue.

In each round of the game, Alice divides the row into two non-empty rows (i.e. left row and right row), then Bob calculates the value of each row which is the sum of the values of all the stones in this row. Bob throws away the row which has the maximum value, and Alice's score increases by the value of the remaining row. If the value of the two rows are equal, Bob lets Alice decide which row will be thrown away. The next round starts with the remaining row.

The game ends when there is only one stone remaining. Alice's score is initially zero.

Return the maximum score that Alice can obtain.

 

Example 1:

Input: stoneValue = [6,2,3,4,5,5]
Output: 18
Explanation: In the first round, Alice divides the row to [6,2,3], [4,5,5]. The left row has the value 11 and the right row has value 14. Bob throws away the right row and Alice's score is now 11.
In the second round Alice divides the row to [6], [2,3]. This time Bob throws away the left row and Alice's score becomes 16 (11 + 5).
The last round Alice has only one choice to divide the row which is [2], [3]. Bob throws away the right row and Alice's score is now 18 (16 + 2). The game ends because only one stone is remaining in the row.
Example 2:

Input: stoneValue = [7,7,7,7,7,7,7]
Output: 28
Example 3:

Input: stoneValue = [4]
Output: 0
 

Constraints:

1 <= stoneValue.length <= 500
1 <= stoneValue[i] <= 106
 */
public class Stone_Game_V_1563 {
	
	 /*
    public int sum(int[] stoneValue, int left, int right){
        int s = 0;
        for(int ind = left; ind <= right; ind++){
            s += stoneValue[ind];
        }
        return s;
    }

    int[][] memo;
    int maxScore;
    public void tryAllPossible(int[] stoneValue, int left, int right, int aliceScore){
        if(left >= right){
            maxScore = Math.max(maxScore, aliceScore);
        //    System.out.println("aliceScore= " + aliceScore);
            return;
        }
        for(int ind = left; ind < right; ind++){
            memo[left][ind] = memo[left][ind] == 0 ? sum(stoneValue, left, ind) : memo[left][ind];
            memo[ind+1][right] = memo[ind+1][right] == 0 ? sum(stoneValue, ind+1, right) : memo[ind+1][right];
            int sL = memo[left][ind];
            int sR = memo[ind+1][right];

            if(sL < sR){
                tryAllPossible(stoneValue, left, ind, aliceScore + sL);
            }else if(sL > sR){
                tryAllPossible(stoneValue, ind+1, right, aliceScore + sR);
            }else{
                tryAllPossible(stoneValue, left, ind, aliceScore + sL);
                tryAllPossible(stoneValue, ind+1, right, aliceScore + sR);
            }
        }
    }

    public int stoneGameV(int[] stoneValue) {
        maxScore = 0;
        memo = new int[stoneValue.length][stoneValue.length];
        tryAllPossible(stoneValue, 0, stoneValue.length - 1, 0);
        return maxScore;
    } */
    /*
    public int sum(int[] stoneValue, int left, int right){
        int s = 0;
        for(int ind = left; ind <= right; ind++){
            s += stoneValue[ind];
        }
        return s;
    }
    
    int[][] memo;
    public int dp(int[] stoneValue, int left, int right){
        if(left >= right){
            return 0;
        }

        if(memo[left][right] > 0){
            return memo[left][right];
        }

        int maxPoint = 0;
        for(int ind = left; ind < right; ind++){
            int sL = sum(stoneValue, left, ind);
            int sR = sum(stoneValue, ind+1, right);
            if(sL < sR){
                maxPoint = Math.max(sL + dp(stoneValue, left, ind), maxPoint);
            }else if(sL > sR){
                maxPoint = Math.max(sR + dp(stoneValue, ind+1, right), maxPoint);
            }else{
                maxPoint = Math.max(dp(stoneValue, left, ind) + sL, maxPoint);
                maxPoint = Math.max(dp(stoneValue, ind+1, right) + sL, maxPoint);
            }
        }

        memo[left][right] = maxPoint;
        return maxPoint;
    }

    public int stoneGameV(int[] stoneValue) {
        memo = new int[stoneValue.length][stoneValue.length];
        return dp(stoneValue, 0, stoneValue.length - 1);
    } */
  /*
    int[][] memo;
    int[] prefix;

    public int prefixSum(int left, int right) {
        return prefix[right + 1] - prefix[left];
    }

    public int dp(int[] stoneValue, int left, int right) {
        if (left >= right) {
            return 0;
        }

        if (memo[left][right] > 0) {
            return memo[left][right];
        }

        int maxPoint = 0;

        for (int ind = left; ind < right; ind++) {
            int sL = prefixSum(left, ind);
            int sR = prefixSum(ind + 1, right);

            if (sL < sR) {
                maxPoint = Math.max(maxPoint, sL + dp(stoneValue, left, ind));
            } else if (sL > sR) {
                maxPoint = Math.max(maxPoint, sR + dp(stoneValue, ind + 1, right));
            } else {
                maxPoint = Math.max(maxPoint, sL + dp(stoneValue, left, ind));
                maxPoint = Math.max(maxPoint, sR + dp(stoneValue, ind + 1, right));
            }
        }

        return memo[left][right] = maxPoint;
    }

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }
        memo = new int[n][n];
        return dp(stoneValue, 0, n - 1);
    } 
    */

	int[][] memo;
	int[][] memo1;

	public int sum(int[] stoneValue, int left, int right) {
		int s = 0;
		for (int ind = left; ind <= right; ind++) {
			s += stoneValue[ind];
		}
		return s;
	}

	public int dp(int[] stoneValue, int left, int right) {
		if (left >= right) {
			return 0;
		}
		if (memo[left][right] > 0) {
			return memo[left][right];
		}
		int maxPoint = 0;
		for (int ind = left; ind < right; ind++) {
			memo1[left][ind] = memo1[left][ind] == 0 ? sum(stoneValue, left, ind) : memo1[left][ind];

			memo1[ind + 1][right] = memo1[ind + 1][right] == 0 ? sum(stoneValue, ind + 1, right)
					: memo1[ind + 1][right];

			int sL = memo1[left][ind];
			int sR = memo1[ind + 1][right];

			if (sL < sR) {
				maxPoint = Math.max(sL + dp(stoneValue, left, ind), maxPoint);
			} else if (sL > sR) {
				maxPoint = Math.max(sR + dp(stoneValue, ind + 1, right), maxPoint);
			} else {
				maxPoint = Math.max(dp(stoneValue, left, ind) + sL, maxPoint);
				maxPoint = Math.max(dp(stoneValue, ind + 1, right) + sL, maxPoint);
			}
		}

		memo[left][right] = maxPoint;
		return maxPoint;
	}

	public int stoneGameV(int[] stoneValue) {
		int n = stoneValue.length;
		memo = new int[n][n];
		memo1 = new int[n][n];
		return dp(stoneValue, 0, n - 1);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
