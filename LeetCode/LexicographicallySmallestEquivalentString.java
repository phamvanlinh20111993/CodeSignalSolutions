package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Url: https://leetcode.com/problems/lexicographically-smallest-equivalent-string/description/?envType=daily-question&envId=2025-06-05
 * You are given two strings of the same length s1 and s2 and a string baseStr.

We say s1[i] and s2[i] are equivalent characters.

For example, if s1 = "abc" and s2 = "cde", then we have 'a' == 'c', 'b' == 'd', and 'c' == 'e'.
Equivalent characters follow the usual rules of any equivalence relation:

Reflexivity: 'a' == 'a'.
Symmetry: 'a' == 'b' implies 'b' == 'a'.
Transitivity: 'a' == 'b' and 'b' == 'c' implies 'a' == 'c'.
For example, given the equivalency information from s1 = "abc" and s2 = "cde", "acd" and "aab" are equivalent strings of baseStr = "eed", and "aab" is the lexicographically smallest equivalent string of baseStr.

Return the lexicographically smallest equivalent string of baseStr by using the equivalency information from s1 and s2.

 

Example 1:

Input: s1 = "parker", s2 = "morris", baseStr = "parser"
Output: "makkek"
Explanation: Based on the equivalency information in s1 and s2, we can group their characters as [m,p], [a,o], [k,r,s], [e,i].
The characters in each group are equivalent and sorted in lexicographical order.
So the answer is "makkek".
Example 2:

Input: s1 = "hello", s2 = "world", baseStr = "hold"
Output: "hdld"
Explanation: Based on the equivalency information in s1 and s2, we can group their characters as [h,w], [d,e,o], [l,r].
So only the second letter 'o' in baseStr is changed to 'd', the answer is "hdld".
Example 3:

Input: s1 = "leetcode", s2 = "programs", baseStr = "sourcecode"
Output: "aauaaaaada"
Explanation: We group the equivalent characters in s1 and s2 as [a,o,e,r,s,c], [l,p], [g,t] and [d,m], thus all letters in baseStr except 'u' and 'd' are transformed to 'a', the answer is "aauaaaaada".
 

Constraints:

1 <= s1.length, s2.length, baseStr <= 1000
s1.length == s2.length
s1, s2, and baseStr consist of lowercase English letters.
 */
public class LexicographicallySmallestEquivalentString {
	
	public List<Character> dfs(Character r, Map<Character, List<Character>> tree, Set<Character> isVisit){
        isVisit.add(r);
        List<Character> res = new ArrayList<>();
        res.add(r);
        List<Character> child = tree.get(r);

        for(Character t : child){
            if(!isVisit.contains(t)){
                res.addAll(dfs(t, tree, isVisit));
            }
        }

        return res;
    }

    public Character findMin(List<Character> l){
        Character m = l.get(0);
        for(Character c : l){
            if(m > c){
                m = c;
            }
        }

        return m;
    }

    public String smallestEquivalentString(String s1, String s2, String baseStr) {

        Map<Character, List<Character>> tree = new HashMap<>();
        for(int ind = 0; ind < s1.length(); ind++){
            List<Character> t = tree.getOrDefault(s1.charAt(ind), new ArrayList<>());
            t.add(s2.charAt(ind));
            List<Character> t1 = tree.getOrDefault(s2.charAt(ind), new ArrayList<>());
            t1.add(s1.charAt(ind));
            tree.put(s1.charAt(ind), t);
            tree.put(s2.charAt(ind), t1);
        }
        
        Map<Character, Character> mapping = new HashMap<>();
        Set<Character> isVisit = new HashSet<>();
        for (Character node : tree.keySet()) {
            if(!isVisit.contains(node)){
                List<Character> res = dfs(node, tree, isVisit);
                Character m = findMin(res);
                for(Character k : res){
                    mapping.put(k, m);
                }
            }
        }

        StringBuilder resStr = new StringBuilder();
        for(int ind = 0; ind < baseStr.length(); ind++){
            resStr.append(mapping.getOrDefault(baseStr.charAt(ind), baseStr.charAt(ind)));
        }

        return resStr.toString();
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
