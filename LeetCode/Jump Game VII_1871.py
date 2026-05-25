
"""
url: https://leetcode.com/problems/jump-game-vii/description/?envType=daily-question&envId=2026-05-25

You are given a 0-indexed binary string s and two integers minJump and maxJump. In the beginning, you are standing at index 0, which is equal to '0'. You can move from index i to index j if the following conditions are fulfilled:

i + minJump <= j <= min(i + maxJump, s.length - 1), and
s[j] == '0'.
Return true if you can reach index s.length - 1 in s, or false otherwise.

 

Example 1:

Input: s = "011010", minJump = 2, maxJump = 3
Output: true
Explanation:
In the first step, move from index 0 to index 3. 
In the second step, move from index 3 to index 5.
Example 2:

Input: s = "01101110", minJump = 2, maxJump = 3
Output: false
 

Constraints:

2 <= s.length <= 105
s[i] is either '0' or '1'.
s[0] == '0'
1 <= minJump <= maxJump < s.length
"""

class Solution:
    def canReach(self, s: str, minJump: int, maxJump: int) -> bool:
        l = len(s)
      #  isVisited = [False]*l

        dq = deque([])
        dq.append(0)
        # ChatGpt support idea. Thanks
        # when bfs [0, [0+minJum, )+maxJump]], => [a, b] < [a1, b1], first will cover [a,b] then we will apply [max(b, a1), b1]
        # farestScanning check the value between max(b, a1) => ignore reduntdancy scanning
        
        farestScanning = 0

        while dq: 
            n = dq.popleft()
            if n == l-1:
                return True
            minJ = n + minJump
            farestScanning = max(minJ, farestScanning+1)
            maxJ = min(n + maxJump, l-1)
            if minJ > maxJ:
                continue
            if maxJ == l-1 and s[maxJ] == '0':
                return True

            for i in range(farestScanning, maxJ+1):
                if s[i]== '0':
                   dq.append(i)
            farestScanning = maxJ    
        return False

        