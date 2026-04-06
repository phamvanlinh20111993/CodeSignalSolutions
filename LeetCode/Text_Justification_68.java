package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * url: https://leetcode.com/problems/text-justification/description/?envType=study-plan-v2&envId=top-interview-150
 * 
 * Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left-justified, and no extra space is inserted between words.

Note:

A word is defined as a character sequence consisting of non-space characters only.
Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
The input array words contains at least one word.
 

Example 1:

Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Example 2:

Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
Output:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be left-justified instead of fully-justified.
Note that the second line is also left-justified because it contains only one word.
Example 3:

Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth = 20
Output:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]
 

Constraints:

1 <= words.length <= 300
1 <= words[i].length <= 20
words[i] consists of only English letters and symbols.
1 <= maxWidth <= 100
words[i].length <= maxWidth
 */
public class Text_Justification_68 {

	public String buildTextJustificationLine(String[] words, int from, int to, int maxWidth, boolean isLastLine) {
		StringBuilder sb = new StringBuilder("");
		int wordLen = 0;
		for (int ind = from; ind <= to; ind++) {
			wordLen += words[ind].length();
		}

		int amountSpace = maxWidth - wordLen;
		int k = to - from;
		int space = k == 0 ? 0 : amountSpace / k;
		int justify = k == 0 ? 0 : amountSpace % k;
		for (int ind = from; ind < to; ind++) {
			sb.append(words[ind]);
			if (isLastLine) {
				sb.append(" ");
				amountSpace--;
				continue;
			}

			int spaceEx = space;
			if (justify > 0) {
				justify--;
				spaceEx++;
			}

			for (int i = 0; i < spaceEx; i++) {
				sb.append(" ");
			}
		}

		sb.append(words[to]);

		if (k == 0 || isLastLine) {
			for (int ind = 0; ind < amountSpace; ind++) {
				sb.append(" ");
			}
		}

		return sb.toString();
	}

	public List<String> fullJustify(String[] words, int maxWidth) {
		List<String> res = new ArrayList<>();
		for (int ind = 0; ind < words.length;) {
			int amount = 0;
			int p = ind;
			while (p < words.length) {
				if (amount + words[p].length() > maxWidth) {
					break;
				}
				amount += words[p].length() + 1;
				p++;
			}
			// System.out.println("from " + ind + ", to " + (p-1));
			if (p == words.length) {
				res.add(buildTextJustificationLine(words, ind, p - 1, maxWidth, true));
				break;
			}
			res.add(buildTextJustificationLine(words, ind, p - 1, maxWidth, false));

			ind = p;
		}

		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
