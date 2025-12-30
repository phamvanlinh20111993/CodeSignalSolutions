package LeetCode;

/**
 * url: https://leetcode.com/problems/maximum-candies-allocated-to-k-children/
 * 
 * You are given a 0-indexed integer array candies. Each element in the array denotes a pile of candies of size candies[i]. You can divide each pile into any number of sub piles, but you cannot merge two piles together.

You are also given an integer k. You should allocate piles of candies to k children such that each child gets the same number of candies. Each child can be allocated candies from only one pile of candies and some piles of candies may go unused.

Return the maximum number of candies each child can get.

 

Example 1:

Input: candies = [5,8,6], k = 3
Output: 5
Explanation: We can divide candies[1] into 2 piles of size 5 and 3, and candies[2] into 2 piles of size 5 and 1. We now have five piles of candies of sizes 5, 5, 3, 5, and 1. We can allocate the 3 piles of size 5 to 3 children. It can be proven that each child cannot receive more than 5 candies.
Example 2:

Input: candies = [2,5], k = 11
Output: 0
Explanation: There are 11 children but only 7 candies in total, so it is impossible to ensure each child receives at least one candy. Thus, each child gets no candy and the answer is 0.
 

Constraints:

1 <= candies.length <= 105
1 <= candies[i] <= 107
1 <= k <= 1012
 * 
 */
public class MaximumCandiesAllocatedtoKChildren_2226 {
	
	/* Not pass all: only 61/101 => take time to check
    public int binarySearch(int l, int r, int val, int[] candies){
        if(r < l) {
            return -1;
        }
        int m = (r+l)/2;
        if(candies[m] > val){
            if(m == 0 || candies[m-1] <= val){
                return m;
            }
            return binarySearch(l, m-1, val, candies);
        }
        return binarySearch(m+1, r, val, candies);
    }

    public int maximumCandies(int[] candies, long k) {
        long sum = 0l;
        int candiL = candies.length;
        for(int ind = 0; ind < candiL; ind++){
            sum += candies[ind];
        }
        if(sum < k) return 0;
        Arrays.sort(candies);
        int medNum = (int)(sum/k);

        for(int ind = 0; ind < candiL; ind++){
            System.out.print(candies[ind] + " ");
        }

        for(int med = medNum; med > 0; med--){
            int maxIndex = candies[candiL-1]/med;
            System.out.println("\nmaxIndex=" + maxIndex + ", medNum=" + medNum);
            int amount = 0, 
                startC = 0, 
                d = 1;

            int pos = binarySearch(0, candiL-1, med-1, candies);
            System.out.println("med="+med+", pos=" + pos);
            while(d <= maxIndex){
                startC = pos;
                pos = binarySearch(startC, candiL-1, med*d, candies);
                 System.out.println("nextpos=" + pos);
                if(pos == -1){
                    amount += d*(candiL - startC);
                    break;
                }
                amount += (d-1)*(pos - startC);
                if (pos == candiL-1){
                    amount += candies[pos]/med;
                    break;
                }
                System.out.println("amount=" + amount);
                d += candies[pos]/med == d ? 1 : candies[pos]/med ;
            }

            if(pos != candiL-1){
                amount += (d-1)*(candiL - startC);
            }
            if(amount >= k) return med;
        }

        return 0;
    } */

    int res;
    public int binarySearch(int l, int r, long k, int[] candies){
        if(r < l) {
            return 0;
        }
        int m = (r+l)/2;
        long amountOfK = 0;
        for(int ind = 0; ind < candies.length; ind++){
            amountOfK += candies[ind]/m;
        }

        if (amountOfK >= k) {
            res = Math.max(res, m);
            return binarySearch(m+1, r, k, candies);
        }

        return binarySearch(l, m-1, k, candies);
    }

    // reference: the first look at Leetcode solution :)))
    public int maximumCandies(int[] candies, long k) {
        long sum = 0l;
        int candiL = candies.length;
        int maxVal = -1;
        for(int ind = 0; ind < candiL; ind++){
            sum += candies[ind];
            maxVal = Math.max(candies[ind], maxVal);
        }
        if(sum < k) return 0;
        res = 0;
        binarySearch(1, maxVal, k, candies);

        return res;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
