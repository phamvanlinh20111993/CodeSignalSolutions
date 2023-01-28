package Hackerrank.DynamicProgramming;

import java.math.BigInteger;

/**
 * 
 * @author PhamLinh Url
 * @Url https://www.hackerrank.com/challenges/fibonacci-modified/problem?isFullScreen=true
 * 
 *      Implement a modified Fibonacci sequence using the following definition:
 * 
 *      Given terms and where , term is computed as:
 * 
 *      Given three integers, , , and , compute and print the term of a modified
 *      Fibonacci sequence.
 * 
 *      Example
 * 
 * 
 * 
 *      Return .
 * 
 *      Function Description
 * 
 *      Complete the fibonacciModified function in the editor below. It must
 *      return the number in the sequence.
 * 
 *      fibonacciModified has the following parameter(s):
 * 
 *      int t1: an integer int t2: an integer int n: the iteration to report
 *      Returns
 * 
 *      int: the number in the sequence Note: The value of may far exceed the
 *      range of a -bit integer. Many submission languages have libraries that
 *      can handle such large results but, for those that don't (e.g., C++), you
 *      will need to compensate for the size of the result.
 * 
 *      Input Format
 * 
 *      A single line of three space-separated integers, the values of , , and .
 * 
 *      Constraints
 * 
 *      may far exceed the range of a -bit integer. Sample Input
 * 
 *      0 1 5 Sample Output
 * 
 *      5 Explanation
 * 
 *      The first two terms of the sequence are and , which gives us a modified
 *      Fibonacci sequence of . The term is .
 */
public class FibonacciModified {

	/*
	 * Complete the 'fibonacciModified' function below.
	 *
	 * The function is expected to return an INTEGER. The function accepts following
	 * parameters: 1. INTEGER t1 2. INTEGER t2 3. INTEGER n
	 */

	public static BigInteger fibonacciModified(long t1, long t2, int n) {
		// Write your code here
		// formula t(i+2) = ti + (t(i+1))^2

		BigInteger tn = new BigInteger("0");
		BigInteger t1B = new BigInteger(String.valueOf(t1));
		BigInteger t2B = new BigInteger(String.valueOf(t2));
		for (int i = 3; i <= n; i++) {
			tn = t1B.add(t2B.multiply(t2B));
			t1B = t2B;
			t2B = tn;
		}

		return tn;
	}

	public static void main(String[] args) {

	}

}
