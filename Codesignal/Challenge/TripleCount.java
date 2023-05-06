package Codesignal.Challenge;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author PhamLinh
 * @link https://app.codesignal.com/challenge/2fCurs3wkNHDWYR4a
 * 
 *       Given a list of positive and negative integers and some value k,
 *       calculate the number of triplets (x, y, z) that satisfy the following
 *       condition: x - y = k and y - z = k.
 * 
 *       Keep track of the positive integers given and count them towards the x,
 *       y, z values of the triplet. If a negative integer is given delete all
 *       positive integers with that absolute value from your tracking list.
 * 
 *       Example: [5, 1, 9, -1], k=4
 * 
 *       Inspect 5 it's a positive integer keep track of it [5] Calculate all
 *       triplets in your tracking list that meet criteria: 0
 * 
 *       Inspect 1 it's a positive integer keep track of it [5, 1] Calculate all
 *       triplets in your tracking list that meet criteria: 0
 * 
 *       Inspect 9 it's a positive integer keep track of it [5, 1, 9] Calculate
 *       all triplets in your tracking list that meet criteria: 1, (9, 5, 1)
 * 
 *       Inspect -1 it's a negative integer, erase all 1's from tracking list
 *       [5, 9] Calculate all triplets in your tracking list that meet criteria:
 *       0
 * 
 *       Output the count of triplets after each number. [0, 0, 1, 0] => 0 + 0 +
 *       1 + 0 => 1
 * 
 *       [execution time limit] 3 seconds (java)
 * 
 *       [memory limit] 1 GB
 * 
 *       [input] array.integer numbers
 * 
 *       Positive or negative integer numbers. Does not include 0.
 * 
 *       [input] integer k
 * 
 *       k > 0
 * 
 *       [output] integer64
 * 
 *       Sum of all count of triplets
 * 
 *       [Java] Syntax Tips
 * 
 *       // Prints help message to the console // Returns a string // // Globals
 *       declared here will cause a compilation error, // declare variables
 *       inside the function instead! String helloWorld(String name) {
 *       System.out.println("This prints to the console when you Run Tests");
 *       return "Hello, " + name; }
 * 
 */
public class TripleCount {

    // int checkCount(Map<Integer, Integer> m, int v, int k, Set<Integer> checkD ){

    // if(!m.containsKey(v)){
    // return 0;
    // }

    // int val = m.get(v);
    // int total = 0;
    // int lM = v - 2*k;
    // int l = v - k;
    // int r = v + k;
    // int rM = v + 2*k;

    // if(m.containsKey(l) && m.containsKey(lM) && !checkD.contains(l) &&
    // !checkD.contains(lM)){
    // total += val * m.get(l) * m.get(lM);
    // }

    // if(m.containsKey(r) && m.containsKey(rM) && !checkD.contains(r) &&
    // !checkD.contains(rM)){
    // total += val * m.get(r) * m.get(rM);
    // }

    // if(m.containsKey(l) && m.containsKey(r)&& !checkD.contains(l) &&
    // !checkD.contains(r) ){
    // total += val * m.get(l) * m.get(r);
    // }

    // return total;
    // }

    // int getCount(Map<Integer, Integer> m, int k){

    // Set<Integer> checkD = new HashSet<>();
    // int c = 0;
    // for (Map.Entry<Integer, Integer> entry : m.entrySet()) {
    // c += checkCount(m, entry.getKey(), k, checkD);
    // checkD.add(entry.getKey());
    // }

    // return c;
    // }

    // long solution(int[] numbers, int k) {
    // Map<Integer, Integer> m = new HashMap<>();

    // int count = 0;
    // for(int ind = 0; ind < numbers.length; ind++){
    // if(numbers[ind] < 0){
    // m.remove(-numbers[ind]);
    // }else{
    // int val = m.containsKey(numbers[ind]) ? m.get(numbers[ind]) : 0;
    // m.put(numbers[ind], val + 1);
    // }

    // count += getCount(m, k);
    // }

    // return count;
    // }
    
    /**
     * 
     * @param m
     * @param v
     * @param k
     * @return
     */
    int checkCount(Map<Integer, Integer> m, int v, int k) {

	if (!m.containsKey(v)) {
	    return 0;
	}

	int val = m.get(v);
	int total = 0;
	int lM = v - 2 * k;
	int l = v - k;
	int r = v + k;
	int rM = v + 2 * k;

	if (m.containsKey(l) && m.containsKey(lM)) {
	    total += val * m.get(l) * m.get(lM);
	}

	if (m.containsKey(r) && m.containsKey(rM)) {
	    total += val * m.get(r) * m.get(rM);
	}

	if (m.containsKey(l) && m.containsKey(r)) {
	    total += val * m.get(l) * m.get(r);
	}

	return total;
    }

    long solution(int[] numbers, int k) {
	Map<Integer, Integer> m = new HashMap<>();

	int count = 0, total = 0;
	for (int ind = 0; ind < numbers.length; ind++) {

	    if (numbers[ind] < 0) {
		int oldVal = checkCount(m, -numbers[ind], k);
		m.remove(-numbers[ind]);
		total -= oldVal;
	    } else {
		int val = m.containsKey(numbers[ind]) ? m.get(numbers[ind]) : 0;
		total -= checkCount(m, numbers[ind], k);
		m.put(numbers[ind], val + 1);
		total += checkCount(m, numbers[ind], k);
	    }

	    count += total;
	}

	return count;
    }

    public static void main(String[] args) {
	/**
	 * Test example
	 *   
  
          [12, 10, 8, 6, 4, 2] => 10
           0,  0,  1, 2, 3, 4
           
           1: 12,10,8
           2, 12,10,8; 10,8,6;
           3, 12,10,8; 10,8,6;8,6,4
           4, 12,10,8; 10,8,6;8,6,4;6,4,2
           
           
           
           [12, 10, 8, 6, -8, 4, 2] => 4
            0,  0,  1, 2, 0,  0, 1 => 4
          
          [12, 10, 8, 6, 6, 4, 2] => 18
           0,  0,  1, 2, 3, 5, 7
           
           
           
          [12, 12, 10, 10, 8, 8, 8] => 24
           0,   0,  0, 0,  4, 8, 12
           
          [12, 12, 10, 10, 8, 8, 8, 6] => 42
           0,   0,  0, 0,  4, 8, 12, 12 + 6 = 18 => 4+8+12+18
           
          [12, 12, 10, 10, 8, 8, 8, 6, -6, 4] => 66
           0,   0,  0, 0,  4, 8, 12,18, 12, 12 => 42+26 = 66
	 */

    }

}
