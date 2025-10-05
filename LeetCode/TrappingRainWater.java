package LeetCode;

/**
 * Url: https://leetcode.com/problems/trapping-rain-water/description/
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

 

Example 1:


Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
Example 2:

Input: height = [4,2,0,3,2,5]
Output: 9
 

Constraints:

n == height.length
1 <= n <= 2 * 104
0 <= height[i] <= 105
 */
public class TrappingRainWater {

	public static int calAmountTrapWater(int startInd, int endInd, int[] height) {
		int baseHeight = Math.min(height[startInd], height[endInd]);
		int res = 0;
		for (int ind = startInd + 1; ind < endInd; ind++) {
			res += (baseHeight - height[ind]);
		}
	//	System.out.println("startInd="+startInd + ", endInd= " + endInd + ", val = " + res);

		return res;
	}

	public static int trap(int[] height) {
		int startHeight = height[0], startInd = 0;
		int hL = height.length;
		int res = 0;
		for (int ind = 0; ind < hL;) {
			if (startHeight == 0 && height[ind] == 0) {
				ind++;
				continue;
			}

			startHeight = height[ind];
			startInd = ind;
			int p = ind + 1;
			int nearestHeight = -1, nearestInd = -1;
			// System.out.println("startInd=" + startInd + ", p=" + p);
			while (p < hL) {
				if (height[p] >= startHeight) {
					res += calAmountTrapWater(startInd, p, height);
					startHeight = height[p];
					startInd = p;
					nearestHeight = -1;
					break;
				}
				if (nearestHeight <= height[p]) {
					nearestHeight = height[p];
					nearestInd = p;
				}
				p++;
			}

			if (nearestHeight != -1) {
				res += calAmountTrapWater(startInd, nearestInd, height);
				startHeight = nearestHeight;
				startInd = nearestInd;
				ind = nearestInd;
			} else {
				ind = p;
			}
		}

		return res;
	}
	
	public static int[] bigTest() {
		int maxHeight = 100000;
		int [] res = new int[2*10000];
		
		for(int ind = 0; ind < res.length; ind++) {
			res[ind] = maxHeight--;
		}
		
		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("################################### test 1 ################################");
		long startTime = System.nanoTime();
		int res = trap(bigTest());
		long stopTime = System.nanoTime();
		System.out.println("Total execute time: " + (double)(stopTime - startTime)/1000000 + "ms, value " + res);
	}

}
