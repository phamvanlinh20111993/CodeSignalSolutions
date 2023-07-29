package Hackerrank.BitManipulation;

/**
 * 
 * @author PhamLinh
 * @url https://www.hackerrank.com/challenges/sum-vs-xor/problem?isFullScreen=true
 * 
 * Given an integer , find each  such that:

where  denotes the bitwise XOR operator. Return the number of 's satisfying the criteria.

Example

There are four values that meet the criteria:

Return .

Function Description

Complete the sumXor function in the editor below.

sumXor has the following parameter(s):
- int n: an integer

Returns
- int: the number of values found

Input Format

A single integer, .

Constraints

Subtasks

 for  of the maximum score.
Output Format

Sample Input 0

5
Sample Output 0

2
Explanation 0

For , the  values  and  satisfy the conditions:

Sample Input 1

10
Sample Output 1

4
Explanation 1

For , the  values , , , and  satisfy the conditions:


 */
public class SumvsXOR {
    
    /*
     * Complete the 'sumXor' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts LONG_INTEGER n as parameter.
     */

    public static long sumXor(long n) {
        
        if(n == 0l) return 1;
        
    // Write your code here
        String nStr = Long.toBinaryString(n);
        int countZero = 0;
        for(int ind = 0; ind < nStr.length(); ind++){
            if(nStr.charAt(ind) == '0'){
                countZero++;
            }
        }
        
        return  (long) Math.pow(2, countZero);
    }
    
    public static void main(String[] args) {
	// TODO Auto-generated method stub

    }

}
