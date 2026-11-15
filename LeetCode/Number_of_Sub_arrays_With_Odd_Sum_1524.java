package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * url:
 * https://leetcode.com/problems/number-of-sub-arrays-with-odd-sum/?envType=daily-question&envId=2026-11-15
 * 
 * Given an array of integers arr, return the number of subarrays with an odd
 * sum.
 * 
 * Since the answer can be very large, return it modulo 109 + 7.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: arr = [1,3,5] Output: 4 Explanation: All subarrays are
 * [[1],[1,3],[1,3,5],[3],[3,5],[5]] All sub-arrays sum are [1,4,9,3,8,5]. Odd
 * sums are [1,9,3,5] so the answer is 4. Example 2:
 * 
 * Input: arr = [2,4,6] Output: 0 Explanation: All subarrays are
 * [[2],[2,4],[2,4,6],[4],[4,6],[6]] All sub-arrays sum are [2,6,12,4,10,6]. All
 * sub-arrays have even sum and the answer is 0. Example 3:
 * 
 * Input: arr = [1,2,3,4,5,6,7] Output: 16
 * 
 * 
 * Constraints:
 * 
 * 1 <= arr.length <= 105 1 <= arr[i] <= 100
 */
public class Number_of_Sub_arrays_With_Odd_Sum_1524 {
	
	

/* 
    // My idea is used prefix sum, but read the hints to get final idea
    arr: [1,2,3,4,5,6,7]
    [0, 1, 4, 5] index odd
    [2, 3, 6] index even

    6: 4
    3: 2
    2: 2

    5: 2
    4: 2
    index odd: 4
    => 16

    ##################################
    arr: [1,3,5]
    [0, 2] index odd
    [1] index even

    1: 1
    2: 1

    index odd: 2
    => 4

    ##################################
    arr: [7, 23, 4, 18, 55, 2, 11, 36, 9, 43]

    [0, 4, 5, 8] index odd
    [1, 2, 3, 6, 7, 9] index even

    9: 4
    7: 3
    6: 3
    3: 1
    2: 1
    1: 1

    8: 5
    5: 3
    4: 3
    index odd: 4
    => 28
*/    
    public int numOfSubarrays(int[] arr) {
        int l = arr.length;
        long [] prefixSum = new long[l];
        List<Integer> sumOdds = new ArrayList<>();
        List<Integer> sumEvens = new ArrayList<>();
        for(int ind = 0; ind < l; ind++){
            prefixSum[ind] = arr[ind] + (ind == 0 ? 0 : prefixSum[ind-1]);
            if(prefixSum[ind] % 2 != 0){
                sumOdds.add(ind);
            }else{
                sumEvens.add(ind);
            }
        }

        System.out.println(sumOdds);
        System.out.println(sumEvens);

        long res = sumOdds.size();
        // cal sumOdd
        int sumOddInd = sumOdds.size()-1;
        for(int ind = sumEvens.size() - 1; ind >= 0; ind--){
            while(sumOddInd >= 0 && sumEvens.get(ind) < sumOdds.get(sumOddInd)) {
                sumOddInd --;
            }
            res += sumOddInd + 1;
        }

        int sumEvenInd = sumEvens.size() - 1;
        for (int ind = sumOdds.size() - 1; ind >= 0; ind--) {
            while (sumEvenInd >= 0 && sumOdds.get(ind) < sumEvens.get(sumEvenInd)) {
                sumEvenInd--;
            }
            res += sumEvenInd + 1;
        }

        return (int)(res % 1000000007);
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
