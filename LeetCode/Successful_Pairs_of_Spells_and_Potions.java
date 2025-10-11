package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Url: https://leetcode.com/problems/successful-pairs-of-spells-and-potions/?envType=daily-question&envId=2025-10-08
 * 
 * You are given two positive integer arrays spells and potions, of length n and m respectively, where spells[i] represents the strength of the ith spell and potions[j] represents the strength of the jth potion.

You are also given an integer success. A spell and potion pair is considered successful if the product of their strengths is at least success.

Return an integer array pairs of length n where pairs[i] is the number of potions that will form a successful pair with the ith spell.

 

Example 1:

Input: spells = [5,1,3], potions = [1,2,3,4,5], success = 7
Output: [4,0,3]
Explanation:
- 0th spell: 5 * [1,2,3,4,5] = [5,10,15,20,25]. 4 pairs are successful.
- 1st spell: 1 * [1,2,3,4,5] = [1,2,3,4,5]. 0 pairs are successful.
- 2nd spell: 3 * [1,2,3,4,5] = [3,6,9,12,15]. 3 pairs are successful.
Thus, [4,0,3] is returned.
Example 2:

Input: spells = [3,1,2], potions = [8,5,8], success = 16
Output: [2,0,2]
Explanation:
- 0th spell: 3 * [8,5,8] = [24,15,24]. 2 pairs are successful.
- 1st spell: 1 * [8,5,8] = [8,5,8]. 0 pairs are successful. 
- 2nd spell: 2 * [8,5,8] = [16,10,16]. 2 pairs are successful. 
Thus, [2,0,2] is returned.
 

Constraints:

n == spells.length
m == potions.length
1 <= n, m <= 105
1 <= spells[i], potions[i] <= 105
1 <= success <= 1010
 


 */

class IndexList {
	public List<Integer> indexes = new ArrayList<>();
	public Integer curInd = 0;
}

public class Successful_Pairs_of_Spells_and_Potions {

	public int[] successfulPairs(int[] spells, int[] potions, long success) {
		int pL = potions.length;
		int[] atLeastWin = new int[pL];

		for (int ind = 0; ind < pL; ind++) {
			atLeastWin[ind] = (int) Math.ceil(success / (double) potions[ind]);
		}

		Arrays.sort(atLeastWin);

		int spL = spells.length;
		Map<Integer, IndexList> map = new HashMap<>();
		for (int ind = 0; ind < spL; ind++) {
			IndexList indL = map.getOrDefault(spells[ind], new IndexList());
			indL.indexes.add(ind);
			map.put(spells[ind], indL);
		}
		Arrays.sort(spells);

		int[] res = new int[spL];
		int startP = 0;
		for (int ind = 0; ind < spL; ind++) {
			while (startP < pL && spells[ind] >= atLeastWin[startP]) {
				startP++;
			}
			IndexList indL = map.get(spells[ind]);
			res[indL.indexes.get(indL.curInd)] = startP;
			indL.curInd++;
		}

		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
