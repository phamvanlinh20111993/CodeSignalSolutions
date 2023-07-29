package Hackerrank.BitManipulation;

/**
 * 
 * @author PhamLinh
 * @url https://www.hackerrank.com/challenges/cipher/problem?isFullScreen=true
 * 
 * Jack and Daniel are friends. They want to encrypt their conversations so that they can save themselves from interception by a detective agency so they invent a new cipher.

Every message is encoded to its binary representation. Then it is written down  times, shifted by  bits. Each of the columns is XORed together to get the final encoded string.

If  and  it looks like so:

1001011     shift 0 
01001011    shift 1
001001011   shift 2
0001001011  shift 3
----------
1110101001  <- XORed/encoded string s
Now we have to decode the message. We know that . The first digit in  so our output string is going to start with . The next two digits are also , so they must have been XORed with . We know the first digit of our  shifted string is a  as well. Since the  digit of  is , we XOR that with our  and now know there is a  in the  position of the original string. Continue with that logic until the end.

Then the encoded message  and the key  are sent to Daniel.

Jack is using this encoding algorithm and asks Daniel to implement a decoding algorithm. Can you help Daniel implement this?

Function Description

Complete the cipher function in the editor below. It should return the decoded string.

cipher has the following parameter(s):

k: an integer that represents the number of times the string is shifted
s: an encoded string of binary digits
Input Format

The first line contains two integers  and , the length of the original decoded string and the number of shifts.
The second line contains the encoded string  consisting of  ones and zeros.

Constraints




It is guaranteed that  is valid.

Output Format

Return the decoded message of length , consisting of ones and zeros.

Sample Input 0

7 4
1110100110
Sample Output 0

1001010
Explanation 0

1001010
 1001010
  1001010
   1001010
----------
1110100110
Sample Input 1

6 2
1110001
Sample Output 1

101111
Explanation 1

101111
 101111
-------
1110001
Sample Input 2

10 3
1110011011
Sample Output 2

10000101
Explanation 2

10000101 010000101

0010000101
1110011011
 * 
 */
public class Cipher {
    /*
     * Complete the 'cipher' function below.
     *
     * The function is expected to return a STRING. The function accepts following
     * parameters: 1. INTEGER k 2. STRING s
     */

    public static int cal(StringBuilder s, int k, int preTmp) {
	int t = 0, ind = s.length() - 1;

	// while(ind > -1 && ind >= s.length() - k + 1){
	// t ^= Character.getNumericValue(s.charAt(ind--));
	// }
	// return t;

	// 1 1 1 => 1
	// 1 1 0 => 0
	// 1 0 1 => 0
	// 1 0 0 => 1
	// 0 1 1 => 0
	// 0 1 0 => 1
	// 0 0 1 => 1
	// 0 0 0 => 0
	// formula: a ^ b ^ c
	int curr = s.charAt(ind) - 48;
	if (s.length() >= k) {
	    int pre = s.charAt(s.length() - k) - 48;
	    return curr ^ pre ^ preTmp;
	} else {
	    return preTmp ^ curr;
	}
    }

    public static String cipher(int k, String s) {
	// Write your code here
	// total calculation step is: (s.length -1) + k
	StringBuilder str = new StringBuilder("");
	int tmp = 0;

	for (int ind = 0; ind < s.length(); ind++) {
	    if (str.length() == s.length() - k + 1)
		break;

	    // s.charAt(ind) = x xor tmp
	    if (s.charAt(ind) == '1') {
		if (tmp == 1) {
		    str.append("0");
		} else {
		    str.append("1");
		}
	    } else {
		if (tmp == 1) {
		    str.append("1");
		} else {
		    str.append("0");
		}
	    }
	    tmp = cal(str, k, tmp);
	}

	return str.toString();
    }

    public static void main(String[] args) {
	// TODO Auto-generated method stub

    }

}
