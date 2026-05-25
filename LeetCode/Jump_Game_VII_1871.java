package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * url: https://leetcode.com/problems/jump-game-vii/description/?envType=daily-question&envId=2026-05-25
 * 
 * You are given a 0-indexed binary string s and two integers minJump and maxJump. In the beginning, you are standing at index 0, which is equal to '0'. You can move from index i to index j if the following conditions are fulfilled:

i + minJump <= j <= min(i + maxJump, s.length - 1), and
s[j] == '0'.
Return true if you can reach index s.length - 1 in s, or false otherwise.

 

Example 1:

Input: s = "011010", minJump = 2, maxJump = 3
Output: true
Explanation:
In the first step, move from index 0 to index 3. 
In the second step, move from index 3 to index 5.
Example 2:

Input: s = "01101110", minJump = 2, maxJump = 3
Output: false
 

Constraints:

2 <= s.length <= 105
s[i] is either '0' or '1'.
s[0] == '0'
1 <= minJump <= maxJump < s.length
 
 */
public class Jump_Game_VII_1871 {
	
	public boolean canReach(String s, int minJump, int maxJump) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] isVisited = new boolean[s.length()];

		isVisited[0] = true;
		q.add(0);
		while (q.size() > 0) {
			Integer ind = q.poll();

			if (ind == s.length() - 1)
				return true;

			int maxJ = Math.min(ind + maxJump, s.length() - 1);
			int minJ = ind + minJump;

			if (maxJ < minJ)
				continue;

			if (maxJ == s.length() - 1 && s.charAt(maxJ) == '0')
				return true;

			for (int nInd = maxJ; nInd >= minJ; nInd--) {
				if (isVisited[nInd])
					break;
				if (s.charAt(nInd) == '0') {
					q.add(nInd);
					isVisited[nInd] = true;
				}
			}

			for (int nInd = minJ; nInd <= maxJ; nInd++) {
				if (isVisited[nInd])
					break;
				if (s.charAt(nInd) == '0') {
					q.add(nInd);
					isVisited[nInd] = true;
				}
			}
		}

		return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
