package InterviewPractice.Recursion;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

	/*
	 * Complete the 'arithmeticExpressions' function below.
	 *
	 * The function is expected to return a STRING. The function accepts
	 * INTEGER_ARRAY arr as parameter.
	 */

	public static String arithmeticExpressions(List<Integer> arr) {
		// Write your code here
		BigInteger total = BigInteger.valueOf(arr.get(0));
		for (Integer val : arr) {
			total.multiply(BigInteger.valueOf(val));
		}

		System.out.println(total.toString());

		return "";
	}
	
	public static void main(String [] args) {
		List<Integer> arr = new ArrayList<Integer>() {
			
		};
	}

}