package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * url: https://leetcode.com/problems/sum-of-distances/description/?envType=daily-question&envId=2026-04-23
 * 
 * You are given a 0-indexed integer array nums. 
 * There exists an array arr of length nums.length, where arr[i] is the sum of |i - j| 
 * over all j such that nums[j] == nums[i] and j != i. If there is no such j, set arr[i] to be 0.

Return the array arr.
 */

class KVDT {
	List<Long> prefixSum = new ArrayList<>();
	Map<Integer, Integer> m = new HashMap<>();

	@Override
	public String toString() {
		return "KV{" + " m=" + m + ", l=" + prefixSum + '}';
	}
}


public class Sum_of_Distances_2615 {

	
	public static long[] distance(int[] nums) {
		Map<Integer, KVDT> m = new HashMap<>();
		int l = nums.length;
		for (int ind = 0; ind < l; ind++) {
			KVDT kv = m.getOrDefault(nums[ind], new KVDT());
			int size = kv.prefixSum.size();
			if (size == 0) {
				kv.prefixSum.add((long) ind);
			} else {
				kv.prefixSum.add(kv.prefixSum.get(size - 1) + ind);
			}
			kv.m.put(ind, size);

			m.put(nums[ind], kv);
		}

		// System.out.println(m);
		long[] res = new long[l];

		for (int ind = 0; ind < l; ind++) {
			KVDT p = m.get(nums[ind]);
			if (p.prefixSum.size() < 2) {
				continue;
			}
			long preSum = 0l;
			if (p.m.get(ind) > 0) {
				preSum = p.prefixSum.get(p.m.get(ind) - 1);
			}
			int prefixSumS = p.prefixSum.size();
			long postSum = p.prefixSum.get(prefixSumS - 1) - ind - preSum;
			long left = (long)p.m.get(ind) * (long)ind - preSum; 
			long right = postSum - (long)ind * (long)(prefixSumS - p.m.get(ind) - 1);
			res[ind] = left + right;
		}

		return res;
	}
	
	static int[] randomData() {
		int [] res = new int[100000];
		for(int ind = 0; ind < 100000; ind++) {
			res[ind] = 1000000000;
		}
		
		return res;
	}

	public static void main(String[] args) {
//		long[] dt = distance(randomData());
//		for(int ind = 0; ind < dt.length; ind++) {
//			System.out.println(dt[ind] + " " + ind);
//		}
	}

}
