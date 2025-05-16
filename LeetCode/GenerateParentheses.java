package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * url: https://leetcode.com/problems/generate-parentheses/description/
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

 

Example 1:

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
Example 2:

Input: n = 1
Output: ["()"]
 

Constraints:

1 <= n <= 8
 */
public class GenerateParentheses {
	
	 List<String> res;
	    public boolean isWellFormParenthese(char [] parentheses){
	        int countWellFormParenthese = 0;
	        for(int ind = 0; ind < parentheses.length; ind++){
	            if(countWellFormParenthese < 0) return false;
	            if(parentheses[ind] == '('){
	                countWellFormParenthese++;
	            }else{
	                countWellFormParenthese --;
	            }
	        }

	        return countWellFormParenthese == 0;
	    }

	    public void recursive(int i, int n, char [] parentheses){
	        if(i >= n){
	            if(isWellFormParenthese(parentheses)){
	                res.add(String.valueOf(parentheses));
	            }
	            return;
	        }
	        parentheses[i] = '(';
	        recursive(i+1, n, parentheses);

	        parentheses[i] = ')';
	        recursive(i+1, n, parentheses);
	    }

	    public List<String> generateParenthesis(int n) {
	        res = new ArrayList<>();
	        recursive(0, 2*n, new char[2*n]);
	        return res;
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
