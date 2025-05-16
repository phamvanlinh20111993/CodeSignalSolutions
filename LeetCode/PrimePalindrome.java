package LeetCode;

/**
 * url: https://leetcode.com/problems/prime-palindrome/description/?envType=problem-list-v2&envId=number-theory
 * 
 * Given an integer n, return the smallest prime palindrome greater than or equal to n.

An integer is prime if it has exactly two divisors: 1 and itself. Note that 1 is not a prime number.

For example, 2, 3, 5, 7, 11, and 13 are all primes.
An integer is a palindrome if it reads the same from left to right as it does from right to left.

For example, 101 and 12321 are palindromes.
The test cases are generated so that the answer always exists and is in the range [2, 2 * 108].

 

Example 1:

Input: n = 6
Output: 7
Example 2:

Input: n = 8
Output: 11
Example 3:

Input: n = 13
Output: 101
 

Constraints:

1 <= n <= 108
 */
public class PrimePalindrome {
	
	Integer mininumNumber = -1;
    public static boolean isPrime(long number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true; 
    }

    public void recursive(int c, int n, int m, String f){
        if(mininumNumber != -1 || c > m/2) return;

        if(c == m/2){
            String t = new StringBuilder(f).reverse().toString();
            if(m % 2== 0){
                Integer num = Integer.valueOf(f+t);
                if(isPrime(num) && num >= n){
                    mininumNumber = num;
                }
            }else{
                for(int ind = 0; ind < 10; ind++){
                    Integer num1 = Integer.valueOf(f+ind+t);
                    if(isPrime(num1) && num1 >= n){
                        mininumNumber = num1;
                        break;
                    }
                }
            }

            return;
        }

        for(int ind = 0; ind < 10; ind++){
            if(c == 0){
                if(ind%2 != 0){
                    recursive(c+1, n, m, f+ind);
                }
            }else{
                recursive(c+1, n, m, f+ind);
            }
        }
    }

    // 1000
    // abcd
    public int primePalindrome(int n) {
        if(n < 10){
            while(!isPrime(n++));
            return n-1;
        }
        mininumNumber = -1;
        int l = (n+"").length();
        while(mininumNumber == -1){
            recursive(0, n, l++, "");
        }
        
        return mininumNumber;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
