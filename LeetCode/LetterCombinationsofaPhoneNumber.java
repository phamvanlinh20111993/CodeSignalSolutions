package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Url: https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.

A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.


 

Example 1:

Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
Example 2:

Input: digits = ""
Output: []
Example 3:

Input: digits = "2"
Output: ["a","b","c"]
 

Constraints:

0 <= digits.length <= 4
digits[i] is a digit in the range ['2', '9'].
 */
public class LetterCombinationsofaPhoneNumber {
	List<String> res;

    public void backtracking(List<Character[]> comb, String digits, int pos, String acc){
        if(pos >= digits.length()){
            res.add(acc);
            return;
        }

        Character[] num = comb.get(digits.charAt(pos) - '0' - 2);
        for(int ind = 0; ind < num.length; ind++){
            backtracking(comb, digits, pos+1, acc+num[ind]);
        }
    }

    public List<String> letterCombinations(String digits) {
        if(digits.length() == 0) 
            return new ArrayList<>();
        res = new ArrayList<>();
        List<Character[]> comb = List.of(
            new Character[]{'a', 'b', 'c'},
            new Character[]{'d', 'e', 'f'},
            new Character[]{'g', 'h', 'i'},
            new Character[]{'j', 'k', 'l'},
            new Character[]{'m', 'n', 'o'},
            new Character[]{'p', 'q', 'r', 's'},
            new Character[]{'t', 'u', 'v'},
            new Character[]{'w', 'x', 'y', 'z'}
        );
        backtracking(comb, digits, 0, "");

        return res;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
