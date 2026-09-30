package LeetCode;

/**
 * url: https://leetcode.com/problems/count-commas-in-range-ii/description/?envType=daily-question&envId=2026-09-09
 * 
 * You are given an integer n.

Return the total number of commas used when writing all integers from [1, n] (inclusive) in standard number formatting.

In standard formatting:

A comma is inserted after every three digits from the right.
Numbers with fewer than 4 digits contain no commas.
 

Example 1:

Input: n = 1002

Output: 3

Explanation:

The numbers "1,000", "1,001", and "1,002" each contain one comma, giving a total of 3.

Example 2:

Input: n = 998

Output: 0

Explanation:

​​​​​​​All numbers from 1 to 998 have fewer than four digits. Therefore, no commas are used.

 

Constraints:

1 <= n <= 1015
 * 
 */
public class Count_Commas_in_Rang_II_3871 {
	
	/**    
    1,000
    10,000
    100,000

    1,000,000
    10,000,000
    100,000,000

    1,000,000,000
    10,000,000,000
    100,000,000,000

    1,000,000,000,000
    10,000,000,000,000
    100,000,000,000,000

    1,000,000,000,000,000
*/
    public long countCommas(long n) {
        if(n < 1000) return 0;

        long amount = 0L;
       
        if(n >= 999999){
            amount += 999999 - 999;
        }else {
            return n - 999;
        }

        if(n >= 999999999){
            amount += 2*(999999999 - 999999);
        }else{
            return amount + 2*(n - 999999);
        }

        if(n >= 999999999999L){
            amount += 3*(999999999999L - 999999999);
        }else{
            return amount + 3*(n - 999999999);
        }

        if(n >= 999999999999999L){
            amount += 4*(999999999999999L - 999999999999L);
        }else{
            return amount + 4*(n - 999999999999L);
        }

        if(n == 1000000000000000L){
            amount += 5;
        }
       
        return amount;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
