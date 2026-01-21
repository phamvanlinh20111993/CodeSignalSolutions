package LeetCode;

import java.util.Arrays;
import java.util.Collections;

/**
 * url: https://leetcode.com/problems/max-dot-product-of-two-subsequences/description/?envType=daily-question&envId=2026-01-08
 * 
 * Given two arrays nums1 and nums2.

Return the maximum dot product between non-empty subsequences of nums1 and nums2 with the same length.

A subsequence of a array is a new array which is formed from the original array by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, [2,3,5] is a subsequence of [1,2,3,4,5] while [1,5,3] is not).
 */
public class MaxDotProductofTwoSubsequences_1458 {
	
	// my idea: dp[x][y] = max(dp[x][y-1], dp[x-1][y], dp[x-1][y-1] + nums1[x] * nums2[y])

    // close but wrong, the correct is: (chatgpt support.https://chatgpt.com/)
    // dp[x][y] = max(dp[x][y-1], dp[x-1][y], max(0, dp[x-1][x-1]) + nums1[x]* nums2[y]))
    //or dp[x][y] = max(dp[x][y-1], dp[x-1][y], dp[x-1][x-1] + nums1[i]*nums2[j], nums1[x] * nums2[y])
    // => because: when you choose to include the current pair (x,y), you have to allow starting a new subsequence at this pair if all previous subsequence contributions are negative
  /*  
   => this problem failed only pass 42/69 testcases
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n1L = nums1.length;
        int n2L = nums2.length;
        int[][] dp = new int[n1L+1][n2L+1];

        for(int x = 0; x < n1L; x++){
            dp[x][0] = nums1[x]*nums2[0];
        }

        for(int y = 0; y < n2L; y++){
            dp[0][y] = nums1[0]*nums2[y];
        }

        for(int x = 1; x < n1L; x++){
            for(int y = 1; y < n2L; y++){
                int currProduct = nums1[x]*nums2[y];
                dp[x][y] = Collections.max(Arrays.asList(Math.max(dp[x-1][y-1], 0) + currProduct, dp[x][y-1], dp[x-1][y]));
            }
        }

        for(int x = 0; x < n1L; x++){
            for(int y = 0; y < n2L; y++){
                System.out.print(dp[x][y] + " " );
            }
            System.out.println();
        }

        return dp[n1L-1][n2L-1] ;
    } */

	int[][] dp;// can change to map

	public int r(int x, int y, int[] nums1, int[] nums2) {
		if (x < 0 || y < 0) {
			return -1000000000;
		}
		if (dp[x][y] != -1000000000) {
			return dp[x][y];
		}

		int currP = nums1[x] * nums2[y];
		int max = Collections.max(Arrays.asList(r(x, y - 1, nums1, nums2), r(x - 1, y, nums1, nums2),
				r(x - 1, y - 1, nums1, nums2) + currP, currP));
		dp[x][y] = max;
		return dp[x][y];
	}

	public int maxDotProduct(int[] nums1, int[] nums2) {
		int n1L = nums1.length;
		int n2L = nums2.length;
		dp = new int[n1L][n2L];
		for (int x = 0; x < n1L; x++) {
			for (int y = 0; y < n2L; y++) {
				dp[x][y] = -1000000000;
			}
		}
		r(n1L - 1, n2L - 1, nums1, nums2);

		return dp[n1L - 1][n2L - 1];
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
