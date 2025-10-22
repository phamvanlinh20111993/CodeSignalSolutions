package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * url: https://leetcode.com/problems/delete-and-earn/description/
 * 
 * You are given an integer array nums. You want to maximize the number of points you get by performing the following operation any number of times:

Pick any nums[i] and delete it to earn nums[i] points. Afterwards, you must delete every element equal to nums[i] - 1 and every element equal to nums[i] + 1.
Return the maximum number of points you can earn by applying the above operation some number of times.

 

Example 1:

Input: nums = [3,4,2]
Output: 6
Explanation: You can perform the following operations:
- Delete 4 to earn 4 points. Consequently, 3 is also deleted. nums = [2].
- Delete 2 to earn 2 points. nums = [].
You earn a total of 6 points.
Example 2:

Input: nums = [2,2,3,3,3,4]
Output: 9
Explanation: You can perform the following operations:
- Delete a 3 to earn 3 points. All 2's and 4's are also deleted. nums = [3,3].
- Delete a 3 again to earn 3 points. nums = [3].
- Delete a 3 once more to earn 3 points. nums = [].
You earn a total of 9 points.
 

Constraints:

1 <= nums.length <= 2 * 104
1 <= nums[i] <= 104

 * 
 */
public class Delete_and_Earn {
	
	/**
	 * Check topics to know to use DP will pass
	 * 
	 * when value run from 1 to max value in nums array:
     * dp[v] = Math.max(dp[v-2] + if(val) is in nums arr ? v*(frequency v in nums arr) : 0, dp[v-1]) if v-2 > -1
     *       = dp[v-1]
     * 
     *  (remove nums[i]+1 and nums[i]-1, but v run from 1 to n so we can ignore dp[v+2] )
	 * 
	 * @param nums
	 * @return
	 */
	public int deleteAndEarn(int[] nums) {
		int[] dp = new int[10001];

		Map<Integer, Integer> map = new HashMap<>();
		int maxVal = 0;
		for (int i = 0; i < nums.length; i++) {
			map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
			maxVal = Math.max(nums[i], maxVal);
		}

		for (int val = 1; val <= maxVal; val++) {
			if (map.containsKey(val)) {
				int numsI = val * map.get(val);
				int maxPre = val - 2 > -1 ? dp[val - 2] : 0;
				dp[val] = Math.max(dp[val - 1], maxPre + numsI);
			} else {
				dp[val] = dp[val - 1];
			}
		}

		int max = 0;
		for (int val = 1; val <= maxVal; val++) {
			max = Math.max(max, dp[val]);
		}

		return max;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
