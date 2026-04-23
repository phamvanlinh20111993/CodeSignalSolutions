package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * url: https://leetcode.com/problems/minimum-absolute-distance-between-mirror-pairs/description/?envType=daily-question&envId=2026-04-17
 * 
 * You are given an integer array nums.

A mirror pair is a pair of indices (i, j) such that:

0 <= i < j < nums.length, and
reverse(nums[i]) == nums[j], where reverse(x) denotes the integer formed by reversing the digits of x. Leading zeros are omitted after reversing, for example reverse(120) = 21.
Return the minimum absolute distance between the indices of any mirror pair. The absolute distance between indices i and j is abs(i - j).

If no mirror pair exists, return -1.

 

Example 1:

Input: nums = [12,21,45,33,54]

Output: 1

Explanation:

The mirror pairs are:

(0, 1) since reverse(nums[0]) = reverse(12) = 21 = nums[1], giving an absolute distance abs(0 - 1) = 1.
(2, 4) since reverse(nums[2]) = reverse(45) = 54 = nums[4], giving an absolute distance abs(2 - 4) = 2.
The minimum absolute distance among all pairs is 1.

Example 2:

Input: nums = [120,21]

Output: 1

Explanation:

There is only one mirror pair (0, 1) since reverse(nums[0]) = reverse(120) = 21 = nums[1].

The minimum absolute distance is 1.

Example 3:

Input: nums = [21,120]

Output: -1

Explanation:

There are no mirror pairs in the array.

 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 109​​​​​​​
 */
public class Minimum_Absolute_Distance_Between_Mirror_Pairs_3761 {

    public Integer reverse(int num){
        Integer res = 0;
        while(num > 0){
            res  = res*10 + num%10;
            num = num/10;
        }
        return res;
    }

    public int minMirrorPairDistance(int[] nums) {
        Map<Integer, List<Integer>> reverseNumMap = new HashMap<>();

        for(int ind = 0; ind < nums.length; ind++){
            List<Integer> dt = reverseNumMap.getOrDefault(nums[ind], new ArrayList<>());
            dt.add(ind);
            reverseNumMap.put(nums[ind], dt);
        }

        int res = 1000000001;
        for(int ind = 0; ind < nums.length; ind++){
            int reverseNum = reverse(nums[ind]);
            if(reverseNumMap.containsKey(reverseNum)){
                List<Integer> listInd = reverseNumMap.get(reverseNum);
                for(int p = 0; p < listInd.size(); p++){
                    if(listInd.get(p) > ind){
                        res = Math.min(res, Math.abs(listInd.get(p) - ind));
                        break;
                    }
                }
            }
            if(res == 1) break;
        }

        return res == 1000000001 ? -1 : res;
    }
}
