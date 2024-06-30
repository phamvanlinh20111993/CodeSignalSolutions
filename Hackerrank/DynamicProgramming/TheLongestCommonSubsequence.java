package Hackerrank.DynamicProgramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author PhamLinh
 * @url https://www.hackerrank.com/challenges/dynamic-programming-classics-the-longest-common-subsequence/copy-from/385174268?isFullScreen=true
 * 
 * A subsequence is a sequence that can be derived from another sequence by deleting some elements without changing the order of the remaining elements. Longest common subsequence (LCS) of 2 sequences is a subsequence, with maximal length, which is common to both the sequences.

Given two sequences of integers, and

, find the longest common subsequence and print it as a line of space-separated integers. If there are multiple common subsequences with the same maximum length, print any one of them.

In case multiple solutions exist, print any of them. It is guaranteed that at least one non-empty common subsequence will exist.

Recommended References

This Youtube video tutorial explains the problem and its solution quite well.

Function Description

Complete the longestCommonSubsequence function in the editor below. It should return an integer array of a longest common subsequence.

longestCommonSubsequence has the following parameter(s):

    a: an array of integers
    b: an array of integers

Input Format

The first line contains two space separated integers
and , the sizes of sequences and .
The next line contains space-separated integers .
The next line contains space-separated integers

.

Constraints




Constraints


Output Format

Print the longest common subsequence as a series of space-separated integers on one line. In case of multiple valid answers, print any one of them.

Sample Input

5 6
1 2 3 4 1
3 4 1 2 1 3

Sample Output

1 2 3

Explanation

There is no common subsequence with length larger than 3. And "1 2 3", "1 2 1", "3 4 1" are all correct answers. 
 */

public class TheLongestCommonSubsequence {
    
    // call lcs[i, j] is longest common subsequence from a[0 to i] with b[0 to j]
    // then we have :
    // lcs[i,j] = max(lcs[i-1, j], lcs[i, j-1]), if a[i] != b[j]
    //            lcs(i-1, j-1, a, b) + 1 ,  if a[i] == b[j]
    //
     static Map<String, Integer> storeData;
     static Map<Integer, List<String>> trace;
     public static Integer lcs(Integer i, Integer j, List<Integer> a, List<Integer> b){
         if(i < 0 || j < 0) return 0;
         String key = i + "-" + j;
         if(storeData.containsKey(key)){
             return storeData.get(key);
         }
         
         if(a.get(i).equals(b.get(j))){
             Integer dt = lcs(i-1, j-1, a, b);
             List<String> coord = trace.getOrDefault(dt+1, new ArrayList<>());
             coord.add(key);
             trace.put(dt+1, coord);
             storeData.put(key, dt + 1);
             return dt + 1;
         }
         
         Integer l = lcs(i-1, j, a, b);
         Integer r = lcs(i, j-1, a, b);
         Integer max = Math.max(l, r);
         storeData.put(key, max);
         
         return  max;
     }
     
     static List<Integer> resCoords = new ArrayList<>();
     public static void recursion(Map<Integer, List<String>> trace, Integer x, Integer y,
            Integer start, Integer total){
                
         if(resCoords.get(total-1) != -1) return;
         if(start > total) return ;
         List<String> st = trace.get(start);
         
         for(String k : st){
             if(resCoords.get(total-1) != -1) return;
             String [] c = k.split("-");
             Integer i = Integer.valueOf(c[0]);
             Integer j = Integer.valueOf(c[1]);
             if(start > 1 && (i <= x || j <= y)) continue;
             resCoords.set(start - 1, i);
             recursion(trace, i, j, start + 1, total);
         }
     }
    
    public static List<Integer> longestCommonSubsequence(List<Integer> a, List<Integer> b) {
        // Write your code here
        storeData = new HashMap<>();
        trace = new HashMap<>();
        Integer maxV = lcs(a.size()-1, b.size()-1, a, b);
     //   System.out.println(storeData);
        System.out.println("maxV " + maxV + " " + trace);
        List<Integer> res = new ArrayList<>();
        
        resCoords = new ArrayList<>();
        for(int ind = 0; ind < maxV; ind++){
            resCoords.add(-1);
        }
        recursion(trace, -1, -1, 1, maxV);
        
        System.out.println(resCoords);
        for(Integer coord : resCoords){
             res.add(a.get(coord));
        }
        
        return res;
    }

    
    public static void main(String[] args) {
	// TODO Auto-generated method stub

    }

}
