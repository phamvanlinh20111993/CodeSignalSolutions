
"""
url: https://leetcode.com/problems/count-the-number-of-special-characters-ii/?envType=daily-question&envId=2026-05-27

You are given a string word. A letter c is called special if it appears both in lowercase and uppercase in word, and every lowercase occurrence of c appears before the first uppercase occurrence of c.

Return the number of special letters in word.

 

Example 1:

Input: word = "aaAbcBC"

Output: 3

Explanation:

The special characters are 'a', 'b', and 'c'.

Example 2:

Input: word = "abc"

Output: 0

Explanation:

There are no special characters in word.

Example 3:

Input: word = "AbBCab"

Output: 0

Explanation:

There are no special characters in word.

 

Constraints:

1 <= word.length <= 2 * 105
word consists of only lowercase and uppercase English letters.
"""


class Solution:
    """
    def numberOfSpecialChars(self, word: str) -> int:
        m = {}
        for ind, w in enumerate(word):
            if w.isupper() and w not in m:
                m[w] = [ind, 0]
        res = 0

        #print(m)

        for ind, w in enumerate(word):
            u = w.upper()
            if w.islower() and u in m:
        #        print(w)
                if ind < m[u][0]:
                    if m[u][1] < 2:
                        res+=1
                        m[u][1] = 2
                else:
                    if  m[u][1] == 2:
                        res -= 1
                        m[u][1] = 3
        return res  
    """

    def numberOfSpecialChars(self, word: str) -> int:
        pos = {c: [float('inf'),float('inf')] for c in string.ascii_uppercase}

        for ind, c in enumerate(word):
            if c.isupper():
                if pos[c][0] == float('inf'):
                    pos[c][0] = ind
            else: 
                pos[c.upper()][1] = ind
      #  print(pos)
        return sum(1 for a in pos.values() if a[1] < a[0] < float('inf'))