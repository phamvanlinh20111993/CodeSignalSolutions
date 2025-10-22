package LeetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * url: https://leetcode.com/problems/maximum-total-damage-with-spell-casting/description/?envType=daily-question&envId=2025-10-11
 * 
 * A magician has various spells.

You are given an array power, where each element represents the damage of a spell. Multiple spells can have the same damage value.

It is a known fact that if a magician decides to cast a spell with a damage of power[i], they cannot cast any spell with a damage of power[i] - 2, power[i] - 1, power[i] + 1, or power[i] + 2.

Each spell can be cast only once.

Return the maximum possible total damage that a magician can cast.

 

Example 1:

Input: power = [1,1,3,4]

Output: 6

Explanation:

The maximum possible damage of 6 is produced by casting spells 0, 1, 3 with damage 1, 1, 4.

Example 2:

Input: power = [7,1,6,6]

Output: 13

Explanation:

The maximum possible damage of 13 is produced by casting spells 1, 2, 3 with damage 1, 6, 6.

 

Constraints:

1 <= power.length <= 105
1 <= power[i] <= 109
 

 * 
 */
public class MaximumTotalDamageWithSpellCasting {
	
	/**
    // with val is from 1 to max value in powser. we go from 1 to max
    => dp[val] = Math.max(dp[val-3] + t, dp[val-2], dp[val-1]), 
                                        if val-3 > -1, t == 0 if   val is not in power, 
                                                              else val*(frequency val in power)
               = Math.max(dp[val-2], dp[val-1])
    
    Not pass all test case. TLE because of 1 <= power[i] <= 10^9 :(
    
 	So we change to sort power array and calculae the val-1, val-2, val-3 base on the power[ind-1] with current val in power[ind]
    
 */
	public long maximumTotalDamage1(int[] power) {
		Map<Integer, Integer> m = new HashMap<>();
		int l = power.length, maxValuePower = 0, minValuePower = Integer.MAX_VALUE;

		for (int ind = 0; ind < l; ind++) {
			m.put(power[ind], m.getOrDefault(power[ind], 0) + 1);
			maxValuePower = Math.max(maxValuePower, power[ind]);
			minValuePower = Math.min(minValuePower, power[ind]);
		}

		long[] dp = new long[maxValuePower + 1];

		for (int val = minValuePower; val <= maxValuePower; val++) {
			long valMinusThree = val > 2 ? dp[val - 3] : 0l;
			long valMinusTwo = val > 1 ? dp[val - 2] : 0l;
			int valueInMap = 0;
			if (m.containsKey(val)) {
				valueInMap = m.get(val) * val;
			}
			dp[val] = Math.max(valMinusThree + valueInMap, Math.max(dp[val - 1], valMinusTwo));
		}

		long res = 0l;
		for (int val = minValuePower; val <= maxValuePower; val++) {
			System.out.println(val + " " + dp[val]);
			res = Math.max(dp[val], res);
		}

		return res;
	}

	record KV(int k, int v) {
	}

	public long maximumTotalDamage(int[] power) {
		Map<Integer, Integer> m = new TreeMap<>();
		int l = power.length;

		for (int ind = 0; ind < l; ind++) {
			m.put(power[ind], m.getOrDefault(power[ind], 0) + 1);
		}

		long[] dp = new long[l];
		KV[] kv = new KV[l];
		int index = 0;
		for (Map.Entry<Integer, Integer> entry : m.entrySet()) {
			kv[index++] = new KV(entry.getKey(), entry.getValue());
		}

		int kvL = index;
		dp[0] = kv[0].k * (long)kv[0].v;
		for (index = 1; index < kvL; index++) {
			System.out.println(kv[index]);
			int val = kv[index].k;

			long valMinusThree = 0L;
			if (kv[index - 1].k <= kv[index].k - 3) {
				valMinusThree = dp[index - 1];
			} else {
				int tmp = index - 1;
				while (tmp >= 0 && kv[tmp].k + 3 > kv[index].k)
					tmp--;
				valMinusThree = tmp == -1 ? 0 : dp[tmp];
			}

			long valMinusTwo = 0l;
			if (kv[index - 1].k <= kv[index].k - 2) {
				valMinusTwo = dp[index - 1];
			} else {
				int tmp = index - 1;
				while (tmp >= 0 && kv[tmp].k + 2 > kv[index].k)
					tmp--;
				valMinusTwo = tmp == -1 ? 0 : dp[tmp];
			}

			long valMinusOne = 0l;
			if (kv[index - 1].k >= kv[index].k - 1) {
				valMinusOne = dp[index - 1];
			}

			long valueInMap = val * kv[index].v;
			dp[index] = Math.max(valMinusThree + valueInMap, Math.max(valMinusOne, valMinusTwo));
		}

		long res = 0l;
		for (index = 0; index < l; index++) {
			res = Math.max(dp[index], res);
		}

		return res;
	}

	public static void main(String[] args) {
		

	}

}
