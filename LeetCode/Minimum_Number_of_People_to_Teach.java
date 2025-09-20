package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * url: https://leetcode.com/problems/minimum-number-of-people-to-teach/description/?envType=daily-question&envId=2025-09-10
 * 
 * On a social network consisting of m users and some friendships between users, two users can communicate with each other if they know a common language.

You are given an integer n, an array languages, and an array friendships where:

There are n languages numbered 1 through n,
languages[i] is the set of languages the i​​​​​​th​​​​ user knows, and
friendships[i] = [u​​​​​​i​​​, v​​​​​​i] denotes a friendship between the users u​​​​​​​​​​​i​​​​​ and vi.
You can choose one language and teach it to some users so that all friends can communicate with each other. Return the minimum number of users you need to teach.

Note that friendships are not transitive, meaning if x is a friend of y and y is a friend of z, this doesn't guarantee that x is a friend of z.
 

Example 1:

Input: n = 2, languages = [[1],[2],[1,2]], friendships = [[1,2],[1,3],[2,3]]
Output: 1
Explanation: You can either teach user 1 the second language or user 2 the first language.
Example 2:

Input: n = 3, languages = [[2],[1,3],[1,2],[3]], friendships = [[1,4],[1,2],[3,4],[2,3]]
Output: 2
Explanation: Teach the third language to users 1 and 3, yielding two users to teach.
 

Constraints:

2 <= n <= 500
languages.length == m
1 <= m <= 500
1 <= languages[i].length <= n
1 <= languages[i][j] <= n
1 <= u​​​​​​i < v​​​​​​i <= languages.length
1 <= friendships.length <= 500
All tuples (u​​​​​i, v​​​​​​i) are unique
languages[i] contains only unique values
 

 */
public class Minimum_Number_of_People_to_Teach {

	public boolean canTalk(int friendI, int friendJ, int[][] languages) {
		for (int i = 0; i < languages[friendI - 1].length; i++) {
			for (int j = 0; j < languages[friendJ - 1].length; j++) {
				if (languages[friendI - 1][i] == languages[friendJ - 1][j]) {
					return true;
				}
			}
		}

		return false;
	}

	// refer to leetcode suggestion: Solutions tab, get idea from it
	public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
		Map<Integer, List<Integer>> friendS = new HashMap<>();
		Set<Integer> distinctUnConnectUser = new HashSet<>();
		for (int[] friend : friendships) {
			if (canTalk(friend[0], friend[1], languages))
				continue;
			List<Integer> f = friendS.getOrDefault(friend[0], new ArrayList<>());
			f.add(friend[1]);
			f.add(friend[0]);
			friendS.put(friend[0], f);
		}

		// System.out.println(friendS);
		for (List<Integer> f : friendS.values()) {
			for (Integer v : f) {
				distinctUnConnectUser.add(v);
			}
		}

		List<Integer> distinctUnConnectUserL = new ArrayList<>(distinctUnConnectUser);
		Set<Integer> langDis = new HashSet<>();
		for (Integer f : distinctUnConnectUserL) {
			for (int i = 0; i < languages[f - 1].length; i++) {
				langDis.add(languages[f - 1][i]);
			}
		}
		List<Integer> langDisL = new ArrayList<>(langDis);
		int maxUserKnowSameLang = 0;
		for (Integer lang : langDisL) {
			int count = 0;
			for (Integer f : distinctUnConnectUserL) {
				for (int i = 0; i < languages[f - 1].length; i++) {
					if (languages[f - 1][i] == lang) {
						count++;
						break;
					}
				}
			}

			if (count > maxUserKnowSameLang)
				maxUserKnowSameLang = count;
		}
		
		// total user not connect - the same they know = amount need to teach 
		return distinctUnConnectUser.size() - maxUserKnowSameLang;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
