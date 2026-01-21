package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * url: https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/description/?envType=daily-question&envId=2026-01-10
 * 
 * Given two strings s1 and s2, return the lowest ASCII sum of deleted characters to make two strings equal.

 

Example 1:

Input: s1 = "sea", s2 = "eat"
Output: 231
Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
Deleting "t" from "eat" adds 116 to the sum.
At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.
Example 2:

Input: s1 = "delete", s2 = "leet"
Output: 403
Explanation: Deleting "dee" from "delete" to turn the string into "let",
adds 100[d] + 101[e] + 101[e] to the sum.
Deleting "e" from "leet" adds 101[e] to the sum.
At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.
 

Constraints:

1 <= s1.length, s2.length <= 1000
s1 and s2 consist of lowercase English letters.

 */
public class Minimum_ASCII_Delete_Sum_for_Two_Strings_712 {
	
	Map<String, Integer> m;

    public Integer d(int x, int y, String s1, String s2) {
        if(x < 0 || y < 0) {
            return 0;
        }

        if(m.containsKey(x+"-"+y)){
            return m.get(x+"-"+y);
        }

        Integer curC = s1.charAt(x) == s2.charAt(y) ? (int) s1.charAt(x) : 0;
        Integer a = d(x-1, y, s1, s2);
        Integer b = d(x-1, y-1, s1, s2);
        b = b + curC;
        Integer c = d(x, y-1, s1, s2);
        Integer max;

        if(a > b){
            if(a > c){
                max = a;
            }else{
                max = c;
            }
        }else{
            if (b > c){
                max = b;
            }else{
                max = c;
            }
        }
        
        m.put(x+"-"+y, max);
        return max;
    }

    /**
        The LCS formula: 
        // d[x][y] = max(d[x-1][y], d[x-1][y-1] + 1 if s1[x] = s2[y] 
        //                                      + 0 if s1[x] != s2[y], d[x][y-1])
        But the problem is to find the sum of deleted characters => that mean the LCS change to find maximum sum of LCS value not the amount of the original LCS before.

        => new formula:
        a = d[x-1][y]
        b = d[x-1][y-1] + (int) s1.charAt(x) if s1[x] == s2[y]
                        + 0                  if s1[x] != s2[y]
        c = d[x][y-1]

        d[x][y] = max(a, b, c)
     */ 
    public int minimumDeleteSum(String s1, String s2) {
        m = new HashMap<>();
        int s1L = s1.length()-1;
        int s2L = s2.length()-1;

        int totalSum = 0;
        for(int i = 0; i < s1L+1; i++){
            totalSum += (int) s1.charAt(i);
        }
        for(int i = 0; i < s2L+1; i++){
            totalSum += (int) s2.charAt(i);
        }

        d(s1L, s2L, s1, s2);
        
        int totalLCSSum = 0;
        for (Map.Entry<String, Integer> kv: m.entrySet()) {
            totalLCSSum = Math.max(totalLCSSum, kv.getValue());
        }

        return totalSum - 2*totalLCSSum;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
