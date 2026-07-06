package LeetCode;
/**
 * url: https://leetcode.com/problems/process-string-with-special-operations-i/?envType=daily-question&envId=2026-06-16
 * You are given a string s consisting of lowercase English letters and the special characters: *, #, and %.

Build a new string result by processing s according to the following rules from left to right:

If the letter is a lowercase English letter append it to result.
A '*' removes the last character from result, if it exists.
A '#' duplicates the current result and appends it to itself.
A '%' reverses the current result.
Return the final string result after processing all characters in s.

 

Example 1:

Input: s = "a#b%*"

Output: "ba"

Explanation:

i	s[i]	Operation	Current result
0	'a'	Append 'a'	"a"
1	'#'	Duplicate result	"aa"
2	'b'	Append 'b'	"aab"
3	'%'	Reverse result	"baa"
4	'*'	Remove the last character	"ba"
Thus, the final result is "ba".

Example 2:

Input: s = "z*#"

Output: ""

Explanation:

i	s[i]	Operation	Current result
0	'z'	Append 'z'	"z"
1	'*'	Remove the last character	""
2	'#'	Duplicate the string	""
Thus, the final result is "".

 

Constraints:

1 <= s.length <= 20
s consists of only lowercase English letters and special characters *, #, and %.
 */
public class Process_String_with_Special_Operations_I_3612 {
	
	 public String processStr(String s) {
	        int l = s.length();
	        int ind = 0;
	        StringBuilder res = new StringBuilder("");
	        while(ind < l){
	            if (s.charAt(ind) >= 'a' && s.charAt(ind) <= 'z' || 
	                s.charAt(ind) >= 'A' && s.charAt(ind) <= 'Z'){
	                res.append(s.charAt(ind));
	            }else if(s.charAt(ind) == '*'){
	                if (res.length() > 0) {
	                    res.deleteCharAt(res.length() - 1);
	                }
	            }else if(s.charAt(ind) == '#'){
	                res = res.append(res);
	            }else if(s.charAt(ind) == '%'){
	                res = res.reverse();
	            }

	            ind++;
	        }

	        return res.toString();
	    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
