"""
url: https://leetcode.com/problems/longest-common-suffix-queries/description/?envType=daily-question&envId=2026-05-28

You are given two arrays of strings wordsContainer and wordsQuery.

For each wordsQuery[i], you need to find a string from wordsContainer that has the longest common suffix with wordsQuery[i]. If there are two or more strings in wordsContainer that share the longest common suffix, find the string that is the smallest in length. If there are two or more such strings that have the same smallest length, find the one that occurred earlier in wordsContainer.

Return an array of integers ans, where ans[i] is the index of the string in wordsContainer that has the longest common suffix with wordsQuery[i].

 

Example 1:

Input: wordsContainer = ["abcd","bcd","xbcd"], wordsQuery = ["cd","bcd","xyz"]

Output: [1,1,1]

Explanation:

Let's look at each wordsQuery[i] separately:

For wordsQuery[0] = "cd", strings from wordsContainer that share the longest common suffix "cd" are at indices 0, 1, and 2. Among these, the answer is the string at index 1 because it has the shortest length of 3.
For wordsQuery[1] = "bcd", strings from wordsContainer that share the longest common suffix "bcd" are at indices 0, 1, and 2. Among these, the answer is the string at index 1 because it has the shortest length of 3.
For wordsQuery[2] = "xyz", there is no string from wordsContainer that shares a common suffix. Hence the longest common suffix is "", that is shared with strings at index 0, 1, and 2. Among these, the answer is the string at index 1 because it has the shortest length of 3.
Example 2:

Input: wordsContainer = ["abcdefgh","poiuygh","ghghgh"], wordsQuery = ["gh","acbfgh","acbfegh"]

Output: [2,0,2]

Explanation:

Let's look at each wordsQuery[i] separately:

For wordsQuery[0] = "gh", strings from wordsContainer that share the longest common suffix "gh" are at indices 0, 1, and 2. Among these, the answer is the string at index 2 because it has the shortest length of 6.
For wordsQuery[1] = "acbfgh", only the string at index 0 shares the longest common suffix "fgh". Hence it is the answer, even though the string at index 2 is shorter.
For wordsQuery[2] = "acbfegh", strings from wordsContainer that share the longest common suffix "gh" are at indices 0, 1, and 2. Among these, the answer is the string at index 2 because it has the shortest length of 6.
 

Constraints:

1 <= wordsContainer.length, wordsQuery.length <= 104
1 <= wordsContainer[i].length <= 5 * 103
1 <= wordsQuery[i].length <= 5 * 103
wordsContainer[i] consists only of lowercase English letters.
wordsQuery[i] consists only of lowercase English letters.
Sum of wordsContainer[i].length is at most 5 * 105.
Sum of wordsQuery[i].length is at most 5 * 105.
"""

"""
class Node:
    def __init__(self, val, length, index):
        self.val = val
        self.length = length
        self.childs = [None for _ in range(26)]
        self.index = index
        self.isEnd = False 
    
    def __str__(self):
        return f"Node(val='{self.val}', length={self.length}), isEnd={self.isEnd},ind={self.index}"

class Trie:
   
    def __init__(self, length, index):
        self.root = Node("", length, index)

    def addWord(self, str, ind, wLength):
        rootTmp = self.root
        for i in range(len(str)-1, -1, -1):
            ch = str[i]
            if rootTmp.childs[ord(ch)-97] is None:
                rootTmp.childs[ord(ch)-97] = Node("", 100001, ind)

            if wLength < rootTmp.childs[ord(ch)-97].length:
                rootTmp.childs[ord(ch)-97].length = wLength
                rootTmp.childs[ord(ch)-97].index = ind
            elif wLength == rootTmp.childs[ord(ch)-97].length and rootTmp.childs[ord(ch)-97].index > ind:
                rootTmp.childs[ord(ch)-97].index = ind

            rootTmp.childs[ord(ch)-97].val = ch
            rootTmp = rootTmp.childs[ord(ch)-97]

        rootTmp.isEnd = True

    def printf(self):
        temp = self.root
        self.dfs(temp, 0)

    def dfs(self, rootTmp, lv):
        if rootTmp is None:
            return

        for x in range(lv+1):
            print("# ", end="")
        print(f"val={rootTmp.val},length={rootTmp.length}, isEnd={rootTmp.isEnd}, ind={rootTmp.index}")

        for c in rootTmp.childs:
            self.dfs(c, lv+1)

    def checkLongestCommonSuffix(self, word):
        temp = self.root
        for i in range(len(word)-1, -1, -1):
            ch = word[i]
            if temp.childs[ord(ch)-97] is not None:
                temp = temp.childs[ord(ch)-97]
            else:
                break
        return temp.index
class Solution:

    def stringIndices(self, wordsContainer: List[str], wordsQuery: List[str]) -> List[int]:
        minInd = minL = 100001
        
        for ind, s in enumerate(wordsContainer):
            if minL > len(s):
                minL = len(s)
                minInd = ind

        trie = Trie(minL, minInd)
        for ind, s in enumerate(wordsContainer):
            trie.addWord(s, ind, len(s))
      #  trie.printf()

        res = len(wordsQuery)*[0]
        for ind, word in enumerate(wordsQuery):
            res[ind] = trie.checkLongestCommonSuffix(word)

        return res
"""

class TrieNode:
    def __init__(self, index, length):
        self.storage_info = (index, length)
        self.childs = [None] * 26

class Trie:
   
    def __init__(self, init_index, init_length):
        self.root = TrieNode(init_index, init_length)

    def add_word(self, word, word_ind):
        cur_node = self.root
        word_length = len(word)

        for ch in reversed(word):
            idx = ord(ch) - ord('a')
            if not cur_node.childs[idx]:
                cur_node.childs[idx] = TrieNode(word_ind, word_length)
            else:   
                cur_node.childs[idx].storage_info = min(
                    cur_node.childs[idx].storage_info,
                    (word_ind, word_length),
                    key=lambda x: (x[1], x[0])
                )

            cur_node = cur_node.childs[idx]

    def find_longest_common_suffix(self, word):
        cur_node = self.root
        for ch in reversed(word):
            idx = ord(ch) - ord('a')
            if not cur_node.childs[idx]:
               break
            cur_node = cur_node.childs[idx]

        return cur_node.storage_info[0]
        
class Solution:

    def stringIndices(self, wordsContainer: List[str], wordsQuery: List[str]) -> List[int]:
        best_idx = min(
            range(len(wordsContainer)), 
            key=lambda i: (len(wordsContainer[i]), i)
        )
       
        trie = Trie(best_idx, len(wordsContainer[best_idx]))
        for ind, w in enumerate(wordsContainer):
            trie.add_word(w, ind)
      
        return [trie.find_longest_common_suffix(q) for q in wordsQuery]