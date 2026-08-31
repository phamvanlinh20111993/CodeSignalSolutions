package LeetCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * url: https://leetcode.com/problems/make-lexicographically-smallest-array-by-swapping-elements/description/?envType=daily-question&envId=2026-08-29
 * 
 * You are given a 0-indexed array of positive integers nums and a positive integer limit.

In one operation, you can choose any two indices i and j and swap nums[i] and nums[j] if |nums[i] - nums[j]| <= limit.

Return the lexicographically smallest array that can be obtained by performing the operation any number of times.

An array a is lexicographically smaller than an array b if in the first position where a and b differ, array a has an element that is less than the corresponding element in b. For example, the array [2,10,3] is lexicographically smaller than the array [10,2,3] because they differ at index 0 and 2 < 10.

 

Example 1:

Input: nums = [1,5,3,9,8], limit = 2
Output: [1,3,5,8,9]
Explanation: Apply the operation 2 times:
- Swap nums[1] with nums[2]. The array becomes [1,3,5,9,8]
- Swap nums[3] with nums[4]. The array becomes [1,3,5,8,9]
We cannot obtain a lexicographically smaller array by applying any more operations.
Note that it may be possible to get the same result by doing different operations.
Example 2:

Input: nums = [1,7,6,18,2,1], limit = 3
Output: [1,6,7,18,1,2]
Explanation: Apply the operation 3 times:
- Swap nums[1] with nums[2]. The array becomes [1,6,7,18,2,1]
- Swap nums[0] with nums[4]. The array becomes [2,6,7,18,1,1]
- Swap nums[0] with nums[5]. The array becomes [1,6,7,18,1,2]
We cannot obtain a lexicographically smaller array by applying any more operations.
Example 3:

Input: nums = [1,7,28,19,10], limit = 3
Output: [1,7,28,19,10]
Explanation: [1,7,28,19,10] is the lexicographically smallest array we can obtain because we cannot apply the operation on any two indices.
 

Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
1 <= limit <= 10^9
 */
public class Make_Lexicographically_Smallest_Array_by_Swapping_Elements_2948 {

	  /*
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {

        for(int i = 0; i < nums.length; i++){
            for(int j = i+1; j < nums.length; j++){
                if(nums[i] > nums[j]  && Math.abs(nums[i] - nums[j]) <= limit ){
                    int tmp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = tmp;
                }
            }
        }

        return nums;
    }
    */
  /*
    record KV(PriorityQueue<Integer> p, Set<Integer> s){}

    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int l = nums.length;
        List<Integer> numArr = new ArrayList<>();

        for(int ind = 0; ind < l; ind++) {
            numArr.add(nums[ind]);
        }
        Collections.sort(numArr);
        List<KV> unionG = new ArrayList<>();

        for(int ind = 0; ind < l;) {
            int j = ind;
            Set<Integer> s = new HashSet<>();
            PriorityQueue<Integer> p = new PriorityQueue<>();
            s.add(numArr.get(j));
            p.add(numArr.get(j));
            while(j + 1 < l && Math.abs(numArr.get(j) - numArr.get(j + 1)) <= limit){
                s.add(numArr.get(j + 1));
                p.add(numArr.get(j + 1));
                j++;
            }
            unionG.add(new KV(p, s));
            ind = j + 1;
        }

        int [] res = new int[l];
        int k = 0;
        for(int ind = 0; ind < l; ind++) {
            int num = nums[ind];
            for(int j = 0; j < unionG.size(); j++) {
                KV kv = unionG.get(j);
                if(kv.s.contains(num) && !kv.p.isEmpty()) {
                    res[k] = kv.p.poll();
                    k++;
                    break;
                }
            }
        }

        return res;
    }

    */

	public int[] lexicographicallySmallestArray(int[] nums, int limit) {
		int l = nums.length;
		List<Integer> numArr = new ArrayList<>();

		for (int ind = 0; ind < l; ind++) {
			numArr.add(nums[ind]);
		}
		Collections.sort(numArr);
		Map<Integer, PriorityQueue<Integer>> unionG = new HashMap<>();

		for (int ind = 0; ind < l;) {
			int j = ind;
			PriorityQueue<Integer> p = new PriorityQueue<>();
			p.add(numArr.get(j));
			while (j + 1 < l && Math.abs(numArr.get(j) - numArr.get(j + 1)) <= limit) {
				p.add(numArr.get(j + 1));
				j++;
			}

			for (int t = ind; t <= j; t++) {
				if (!unionG.containsKey(numArr.get(t))) {
					unionG.put(numArr.get(t), p);
				}
			}

			ind = j + 1;
		}

		// System.out.println(unionG);

		int[] res = new int[l];
		int k = 0;
		for (int ind = 0; ind < l; ind++) {
			int num = nums[ind];
			PriorityQueue<Integer> p = unionG.get(num);
			if (!p.isEmpty()) {
				res[k] = p.poll();
				k++;
			}
		}

		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
