package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Url: https://leetcode.com/problems/permutations/submissions/1502426123/
 * Given an array nums of distinct integers, return all the possible 
permutations
. You can return the answer in any order.

 

Example 1:

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
Example 2:

Input: nums = [0,1]
Output: [[0,1],[1,0]]
Example 3:

Input: nums = [1]
Output: [[1]]
 

Constraints:

1 <= nums.length <= 6
-10 <= nums[i] <= 10
All the integers of nums are unique.
 */
public class Permutations {
	
	List<List<Integer>> res;

    public void backtrack(int[] nums, int pos, int[] isVisited, List<Integer> acc){
        if(pos >= nums.length){
            res.add(acc);
            return;
        }
        for(int ind = 0; ind < nums.length; ind++){
            if(isVisited[ind] == 0){
                isVisited[ind] = 1;
                List<Integer> accT = new ArrayList<Integer>(acc);
                accT.add(nums[ind]);
                backtrack(nums, pos+1, isVisited, accT);
                isVisited[ind] = 0;
            }
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();
        int[] isVisited = new int[nums.length];
        backtrack(nums, 0, isVisited, new ArrayList<>());
        return res;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
