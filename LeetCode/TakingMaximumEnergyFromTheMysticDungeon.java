package LeetCode;

/**
 * Url: https://leetcode.com/problems/taking-maximum-energy-from-the-mystic-dungeon/description/?envType=daily-question&envId=2025-10-10
 * 
 * In a mystic dungeon, n magicians are standing in a line. Each magician has an attribute that gives you energy. Some magicians can give you negative energy, which means taking energy from you.

You have been cursed in such a way that after absorbing energy from magician i, you will be instantly transported to magician (i + k). This process will be repeated until you reach the magician where (i + k) does not exist.

In other words, you will choose a starting point and then teleport with k jumps until you reach the end of the magicians' sequence, absorbing all the energy during the journey.

You are given an array energy and an integer k. Return the maximum possible energy you can gain.

Note that when you are reach a magician, you must take energy from them, whether it is negative or positive energy.

 

Example 1:

Input: energy = [5,2,-10,-5,1], k = 3

Output: 3

Explanation: We can gain a total energy of 3 by starting from magician 1 absorbing 2 + 1 = 3.

Example 2:

Input: energy = [-2,-3,-1], k = 2

Output: -1

Explanation: We can gain a total energy of -1 by starting from magician 2.

 

Constraints:

1 <= energy.length <= 105
-1000 <= energy[i] <= 1000
1 <= k <= energy.length - 1
 

​​​​​​
 */
public class TakingMaximumEnergyFromTheMysticDungeon {
	

    // base on hint: dp[i] = dp[i+k] + energy[i]
    // Explaination:
    // e[] =  0 1 2 3 4 5 6 7 8 9 10
    // k = 3
    // with i = 6(e.l - 1 - k = 11 - 3 - 1 = 7)
    // dp[7] = 7 (then 7+k = 11 out of E) => start from index 7 to end it always equal energy[index]
    // Then:
    // dp[3] = dp[3+3] + energy[3]
    // ....
    //.. just change visualize from end to start :))) :(((
    public int maximumEnergy(int[] energy, int k) {
        int eL = energy.length;
        int [] dp = new int[eL];

        for(int ind = eL-1; ind >= eL-k-1; ind --){
            dp[ind] = energy[ind];
        }

        for(int ind = eL-k-1; ind > -1; ind--){
            dp[ind] = dp[ind+k] + energy[ind];
        }

        int max = -1000000;
        for(int ind = 0; ind < eL; ind++){
            max = Math.max(max, dp[ind]);
        }
        
        return max;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
