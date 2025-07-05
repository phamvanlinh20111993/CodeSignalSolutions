package LeetCode;

/**
 * url: https://leetcode.com/problems/maximum-score-words-formed-by-letters/description/?envType=problem-list-v2&envId=backtracking
 * 
 * Given a list of words, list of  single letters (might be repeating) and score of every character.

Return the maximum score of any valid set of words formed by using the given letters (words[i] cannot be used two or more times).

It is not necessary to use all characters in letters and each letter can only be used once. Score of letters 'a', 'b', 'c', ... ,'z' is given by score[0], score[1], ... , score[25] respectively.

 

Example 1:

Input: words = ["dog","cat","dad","good"], letters = ["a","a","c","d","d","d","g","o","o"], score = [1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0]
Output: 23
Explanation:
Score  a=1, c=9, d=5, g=3, o=2
Given letters, we can form the words "dad" (5+1+5) and "good" (3+2+2+5) with a score of 23.
Words "dad" and "dog" only get a score of 21.
Example 2:

Input: words = ["xxxz","ax","bx","cx"], letters = ["z","a","b","c","x","x","x"], score = [4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,10]
Output: 27
Explanation:
Score  a=4, b=4, c=4, x=5, z=10
Given letters, we can form the words "ax" (4+5), "bx" (4+5) and "cx" (4+5) with a score of 27.
Word "xxxz" only get a score of 25.
Example 3:

Input: words = ["leetcode"], letters = ["l","e","t","c","o","d"], score = [0,0,1,1,1,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,0,0,0,0,0]
Output: 0
Explanation:
Letter "e" can only be used once.
 

Constraints:

1 <= words.length <= 14
1 <= words[i].length <= 15
1 <= letters.length <= 100
letters[i].length == 1
score.length == 26
0 <= score[i] <= 10
words[i], letters[i] contains only lower case English letters.
 */
public class Maximum_Score_Words_Formed_by_Letters {
	
	public int wordToScore(String word, int[] scores) {
		int score = 0;
		for (int ind = 0; ind < word.length(); ind++) {
			score += scores[word.charAt(ind) - 'a'];
		}

		return score;
	}

	record Data(String word, int score) {
	}

	int res;

	public void backtrack(int s, Data[] wordsScore, int[] mapLetters, int acc) {
		if (res < acc) {
			res = acc;
		}

		for (int ind = s; ind < wordsScore.length; ind++) {
			String word = wordsScore[ind].word;
			boolean isChoose = true;
			int[] mapLettersClone = mapLetters.clone();
			for (int p = 0; p < word.length(); p++) {
				int pos = word.charAt(p) - 'a';
				mapLettersClone[pos]--;
				if (mapLettersClone[pos] < 0) {
					isChoose = false;
					break;
				}
			}
			if (isChoose) {
				backtrack(ind + 1, wordsScore, mapLettersClone, acc + wordsScore[ind].score);
			}

		}
	}

	public int maxScoreWords(String[] words, char[] letters, int[] score) {
		int[] mapLetters = new int[26];

		for (char c : letters) {
			mapLetters[c - 'a']++;
		}

		Data[] wordsScore = new Data[words.length];
		for (int ind = 0; ind < words.length; ind++) {
			wordsScore[ind] = new Data(words[ind], wordToScore(words[ind], score));
		}
		res = 0;
		backtrack(0, wordsScore, mapLetters, 0);

		return res;

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
