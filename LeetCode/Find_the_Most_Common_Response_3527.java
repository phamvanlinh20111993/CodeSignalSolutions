package LeetCode;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * url: https://leetcode.com/problems/find-the-most-common-response/?envType=problem-list-v2&envId=string
 * 
 * You are given a 2D string array responses where each responses[i] is an array of strings representing survey responses from the ith day.

Return the most common response across all days after removing duplicate responses within each responses[i]. If there is a tie, return the lexicographically smallest response.

 

Example 1:

Input: responses = [["good","ok","good","ok"],["ok","bad","good","ok","ok"],["good"],["bad"]]

Output: "good"

Explanation:

After removing duplicates within each list, responses = [["good", "ok"], ["ok", "bad", "good"], ["good"], ["bad"]].
"good" appears 3 times, "ok" appears 2 times, and "bad" appears 2 times.
Return "good" because it has the highest frequency.
Example 2:

Input: responses = [["good","ok","good"],["ok","bad"],["bad","notsure"],["great","good"]]

Output: "bad"

Explanation:

After removing duplicates within each list we have responses = [["good", "ok"], ["ok", "bad"], ["bad", "notsure"], ["great", "good"]].
"bad", "good", and "ok" each occur 2 times.
The output is "bad" because it is the lexicographically smallest amongst the words with the highest frequency.
 

Constraints:

1 <= responses.length <= 1000
1 <= responses[i].length <= 1000
1 <= responses[i][j].length <= 10
responses[i][j] consists of only lowercase English letters
 */
public class Find_the_Most_Common_Response_3527 {

	public String findCommonResponse(List<List<String>> responses) {
		Map<String, Integer> amountDupByResponse = new TreeMap<>();
		for (List<String> res : responses) {
			Set<String> removeDup = new HashSet<>(res);
			Iterator<String> it = removeDup.iterator();

			while (it.hasNext()) {
				String name = it.next();
				int c = amountDupByResponse.getOrDefault(name, 0);
				amountDupByResponse.put(name, ++c);
			}
		}

		String res = responses.get(0).get(0);
		int count = -1;
		for (Map.Entry<String, Integer> entry : amountDupByResponse.entrySet()) {
			if (entry.getValue() > count) {
				count = entry.getValue();
				res = entry.getKey();
			}
		}

		return res;
	}

	public static void main(String[] args) {

	}

}
