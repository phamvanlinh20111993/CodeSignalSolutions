package LeetCode;

/**
 * url: https://leetcode.com/problems/jump-game-iii/description/?envType=daily-question&envId=2026-05-17
 * 
 * Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach any index with value 0.

Notice that you can not jump outside of the array at any time.

 

Example 1:

Input: arr = [4,2,3,0,3,1,2], start = 5
Output: true
Explanation: 
All possible ways to reach at index 3 with value 0 are: 
index 5 -> index 4 -> index 1 -> index 3 
index 5 -> index 6 -> index 4 -> index 1 -> index 3 
Example 2:

Input: arr = [4,2,3,0,3,1,2], start = 0
Output: true 
Explanation: 
One possible way to reach at index 3 with value 0 is: 
index 0 -> index 4 -> index 1 -> index 3
Example 3:

Input: arr = [3,0,2,1,2], start = 2
Output: false
Explanation: There is no way to reach at index 1 with value 0.
 

Constraints:

1 <= arr.length <= 5 * 104
0 <= arr[i] < arr.length
0 <= start < arr.length
 */
public class Jump_Game_III_1306 {

	boolean canReachP = false;

	public void dfs(int start, int[] arr, boolean[] isVisited) {
		if (canReachP)
			return;
		if (arr[start] == 0) {
			canReachP = true;
			return;
		}

		isVisited[start] = true;

		int fNext = start + arr[start];
		if (fNext < arr.length && fNext >= 0 && !isVisited[fNext]) {
			dfs(fNext, arr, isVisited);
		}

		int sNext = start - arr[start];
		if (sNext < arr.length && sNext >= 0 && !isVisited[sNext]) {
			dfs(sNext, arr, isVisited);
		}
	}

	public boolean canReach(int[] arr, int start) {
		canReachP = false;
		boolean[] isVisited = new boolean[arr.length];
		dfs(start, arr, isVisited);
		return canReachP;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
