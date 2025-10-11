package LeetCode;

import java.util.HashSet;
import java.util.Set;

/**
 * url: https://leetcode.com/problems/verbal-arithmetic-puzzle/description/?envType=problem-list-v2&envId=backtracking
 * 
 * Given an equation, represented by words on the left side and the result on the right side.

You need to check if the equation is solvable under the following rules:

Each character is decoded as one digit (0 - 9).
No two characters can map to the same digit.
Each words[i] and result are decoded as one number without leading zeros.
Sum of numbers on the left side (words) will equal to the number on the right side (result).
Return true if the equation is solvable, otherwise return false.

 

Example 1:

Input: words = ["SEND","MORE"], result = "MONEY"
Output: true
Explanation: Map 'S'-> 9, 'E'->5, 'N'->6, 'D'->7, 'M'->1, 'O'->0, 'R'->8, 'Y'->'2'
Such that: "SEND" + "MORE" = "MONEY" ,  9567 + 1085 = 10652
Example 2:

Input: words = ["SIX","SEVEN","SEVEN"], result = "TWENTY"
Output: true
Explanation: Map 'S'-> 6, 'I'->5, 'X'->0, 'E'->8, 'V'->7, 'N'->2, 'T'->1, 'W'->'3', 'Y'->4
Such that: "SIX" + "SEVEN" + "SEVEN" = "TWENTY" ,  650 + 68782 + 68782 = 138214
Example 3:

Input: words = ["LEET","CODE"], result = "POINT"
Output: false
Explanation: There is no possible mapping to satisfy the equation, so we return false.
Note that two different characters cannot map to the same digit.
 

Constraints:

2 <= words.length <= 5
1 <= words[i].length, result.length <= 7
words[i], result contain only uppercase English letters.
The number of different characters used in the expression is at most 10.
 */

class CharByNum {
    public int value = -1;
    public int resultInd = -1;
    public int wordsInd = -1;
    public int wordInd = -1;

     public CharByNum(){
        this.value = -1;
        this.resultInd = -1;
        this.wordsInd = -1;
        this.wordInd = -1;
     }
}

public class VerbalArithmeticPuzzle {

	boolean canGetResult = false;

	public void backtracking(String result, String[] words, CharByNum[] charByNum, boolean[] isOccupyNum, int resultInd,
			int wordsInd, int wordInd, int resultSum, int wordSum) {

		if (wordsInd == words.length && resultSum - wordSum == 0) {
			canGetResult = true;
			return;
		}

		if (canGetResult || wordsInd >= words.length || resultSum - wordSum < 0) {
			return;
		}

		if (resultInd >= result.length()) {
			if (charByNum[words[wordsInd].charAt(wordInd) - 65].value == -1) {
				int pos = wordInd == 0 && words[wordInd].length() > 1 ? 1 : 0;
				for (; pos < 10; pos++) {
					if (!isOccupyNum[pos]) {
						if (charByNum[words[wordsInd].charAt(wordInd) - 65].value == -1) {
							charByNum[words[wordsInd].charAt(wordInd) - 65].value = pos;
							charByNum[words[wordsInd].charAt(wordInd) - 65].wordsInd = wordsInd;
							charByNum[words[wordsInd].charAt(wordInd) - 65].wordInd = wordInd;
						}
						isOccupyNum[pos] = true;

						int resultSumTmp = resultSum, wordSumTmp = wordSum, wordIndTmp = wordInd,
								wordsIndTmp = wordsInd;

						if (wordIndTmp == words[wordsInd].length() - 1) {
							wordIndTmp = -1;
							wordsIndTmp++;
							resultSumTmp -= (wordSumTmp * 10 + charByNum[words[wordsInd].charAt(wordInd) - 65].value);
							wordSumTmp = 0;
						} else {
							wordSumTmp = wordSumTmp * 10 + charByNum[words[wordsInd].charAt(wordInd) - 65].value;
						}

						backtracking(result, words, charByNum, isOccupyNum, resultInd, wordsIndTmp, wordIndTmp + 1,
								resultSumTmp, wordSumTmp);
						CharByNum c = charByNum[words[wordsInd].charAt(wordInd) - 65];
						if (c.wordsInd == wordsInd && c.wordInd == wordInd) {
							charByNum[words[wordsInd].charAt(wordInd) - 65].value = -1;
						}
						isOccupyNum[pos] = false;
					}
				}
			} else {
				int resultSumTmp = resultSum, wordSumTmp = wordSum, wordIndTmp = wordInd, wordsIndTmp = wordsInd;

				if (wordIndTmp == words[wordsInd].length() - 1) {
					wordIndTmp = -1;
					wordsIndTmp++;
					resultSumTmp -= (wordSumTmp * 10 + charByNum[words[wordsInd].charAt(wordInd) - 65].value);
					wordSumTmp = 0;
				} else {
					wordSumTmp = wordSumTmp * 10 + charByNum[words[wordsInd].charAt(wordInd) - 65].value;
				}
				backtracking(result, words, charByNum, isOccupyNum, resultInd, wordsIndTmp, wordIndTmp + 1,
						resultSumTmp, wordSumTmp);
			}
		} else {
			int ind = resultInd == 0 && result.length() > 1 ? 1 : 0;
			if (charByNum[result.charAt(resultInd) - 65].value == -1) {
				for (; ind < 10; ind++) {
					if (!isOccupyNum[ind]) {
						if (charByNum[result.charAt(resultInd) - 65].value == -1) {
							charByNum[result.charAt(resultInd) - 65].value = ind;
							charByNum[result.charAt(resultInd) - 65].resultInd = resultInd;
						}
						isOccupyNum[ind] = true;
						backtracking(result, words, charByNum, isOccupyNum, resultInd + 1, wordsInd, wordInd,
								resultSum * 10 + charByNum[result.charAt(resultInd) - 65].value, wordSum);
						isOccupyNum[ind] = false;
						if (charByNum[result.charAt(resultInd) - 65].resultInd == resultInd) {
							charByNum[result.charAt(resultInd) - 65].value = -1;
						}
					}
				}
			} else {
				backtracking(result, words, charByNum, isOccupyNum, resultInd + 1, wordsInd, wordInd,
						resultSum * 10 + charByNum[result.charAt(resultInd) - 65].value, wordSum);
			}
		}
	}

	public boolean isSolvable(String[] words, String result) {
		canGetResult = false;
		CharByNum[] charByNum = new CharByNum[26];
		for (int ind = 0; ind < 26; ind++) {
			charByNum[ind] = new CharByNum();
		}
		boolean[] isOccupyNumex = new boolean[10];

		Set<Character> s = new HashSet<>();

		for (int ind = 0; ind < words.length; ind++) {
			for (int p = 0; p < words[ind].length(); p++) {
				s.add(words[ind].charAt(p));
			}
		}

		for (int ind = 0; ind < result.length(); ind++) {
			s.add(result.charAt(ind));
		}

		if (s.size() > 10)
			return false;

		backtracking(result, words, charByNum, isOccupyNumex, 0, 0, 0, 0, 0);

		return canGetResult;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
