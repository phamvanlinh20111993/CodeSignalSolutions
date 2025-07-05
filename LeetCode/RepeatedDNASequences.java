package LeetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.

For example, "ACGAATTCCG" is a DNA sequence.
When studying DNA, it is useful to identify repeated sequences within the DNA.

Given a string s that represents a DNA sequence, return all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule. You may return the answer in any order.

 

Example 1:

Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
Output: ["AAAAACCCCC","CCCCCAAAAA"]
Example 2:

Input: s = "AAAAAAAAAAAAA"
Output: ["AAAAAAAAAA"]
 

Constraints:

1 <= s.length <= 105
s[i] is either 'A', 'C', 'G', or 'T'.
 * 
 */
public class RepeatedDNASequences {

	public int hashFunction(String t) {
		int hash = 0;
		for (int ind = 0; ind < t.length(); ind++) {
			hash = 31 * hash + ((byte) t.charAt(ind) & 0xf);
		}

		return hash;
	}

	public List<String> findRepeatedDnaSequences(String s) {

		Set<Integer> val = new HashSet<>();
		Set<String> res = new HashSet<>();

		for (int ind = 0; ind <= s.length() - 10; ind++) {
			String tmp = s.substring(ind, ind + 10);
			int hash = hashFunction(tmp);
			if (val.contains(hash)) {
				res.add(tmp);
			} else {
				val.add(hash);
			}
		}

		return new ArrayList<>(res);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
