package LeetCode;

/**
 * url: https://leetcode.com/problems/container-with-most-water/description/?envType=study-plan-v2&envId=leetcode-75
 * 
 * You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).

Find two lines that together with the x-axis form a container, such that the container contains the most water.

Return the maximum amount of water a container can store.

Notice that you may not slant the container.

 

Example 1:


Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
Example 2:

Input: height = [1,1]
Output: 1
 

Constraints:

n == height.length
2 <= n <= 105
0 <= height[i] <= 104
 * 
 */
public class ContainerWithMostWater {
	
	public int maxArea(int[] height) {

		int max = 0, len = height.length, maxH = -1;
        for(int ind = 0; ind < len; ind++){

            if(height[ind] > maxH){
                maxH = height[ind];
            }else{
                continue;
            }

            int currH = 0;
            for(int p = len - 1; p > ind; p--){
                if(currH > height[ind]) break;
                if(height[p] > currH){
                    currH = height[p];
                    int m = Math.min(currH, height[ind]) * (p - ind);
                    if(max < m){
                        max = m;
                    }   
                }else{
                    currH = max/(p - ind);
                }
            }
        }

        return max;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
