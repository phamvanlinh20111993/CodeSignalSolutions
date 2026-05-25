"""
url: https://leetcode.com/problems/minimum-jumps-to-reach-end-via-prime-teleportation/?envType=daily-question&envId=2026-05-08

You are given an integer array nums of length n.

You start at index 0, and your goal is to reach index n - 1.

From any index i, you may perform one of the following operations:

Adjacent Step: Jump to index i + 1 or i - 1, if the index is within bounds.
Prime Teleportation: If nums[i] is a prime number p, you may instantly jump to any index j != i such that nums[j] % p == 0.
Return the minimum number of jumps required to reach index n - 1.

 

Example 1:

Input: nums = [1,2,4,6]

Output: 2

Explanation:

One optimal sequence of jumps is:

Start at index i = 0. Take an adjacent step to index 1.
At index i = 1, nums[1] = 2 is a prime number. Therefore, we teleport to index i = 3 as nums[3] = 6 is divisible by 2.
Thus, the answer is 2.

Example 2:

Input: nums = [2,3,4,7,9]

Output: 2

Explanation:

One optimal sequence of jumps is:

Start at index i = 0. Take an adjacent step to index i = 1.
At index i = 1, nums[1] = 3 is a prime number. Therefore, we teleport to index i = 4 since nums[4] = 9 is divisible by 3.
Thus, the answer is 2.

Example 3:

Input: nums = [4,6,5,8]

Output: 3

Explanation:

Since no teleportation is possible, we move through 0 → 1 → 2 → 3. Thus, the answer is 3.
 

Constraints:

1 <= n == nums.length <= 105
1 <= nums[i] <= 106
"""


class KV:
    def __init__(self, ind, lv):
        self.ind = ind
        self.lv = lv

    def __str__(self):
        return f"[ind: {self.ind}, {self.lv}]"

class Solution:
    def isPrime(self, num: int) -> bool:
        if num < 2:
            return False 
        c = 0
        r = int(math.sqrt(num)) + 1
        for x in range(2, r):
            if num % x == 0:
                return False
        return True

    def addDataInPrime(self, num: int, m: dict, ind: int):
        primes=[2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101,103,107,109,113,127,131,137,139,149,151,157,163,167,173,179,181,191,193,197,199,211,223,227,229,233,239,241,251,257,263,269,271,277,281,283,293,307,311,313,317,331,337,347,349,353,359,367,373,379,383,389,397,401,409,419,421,431,433,439,443,449,457,461,463,467,479,487,491,499,503,509,521,523,541,547,557,563,569,571,577,587,593,599,601,607,613,617,619,631,641,643,647,653,659,661,673,677,683,691,701,709,719,727,733,739,743,751,757,761,769,773,787,797,809,811,821,823,827,829,839,853,857,859,863,877,881,883,887,907,911,919,929,937,941,947,953,967,971,977,983,991,997,1009] # 1000000

        for p in primes: 
            if num % p == 0:
                if p in m:
                    m[p].append(ind)
                while(num%p == 0):
                    num = num // p
        if num in m:
            m[num].append(ind)

    def minJumps(self, nums: List[int]) -> int:
        m = {}
        visited = []
        l = len(nums)
        for ind, num in enumerate(nums):
            visited.append(False)
            if self.isPrime(num):
                m[num] = []
       
        for ind, num in enumerate(nums):
            #if num in m:
            #    continue
            self.addDataInPrime(num, m, ind)

        #print(m)
        q = deque()
        q.append(KV(0, 0))
        visited[0] = True
        res = l
        while(len(q) > 0):
            el = q.popleft()
            #print(el)
            if el.ind == l-1:
                return el.lv
                #print("lv", el.lv )
                #res = min(res,  el.lv)

            if el.ind > 0 and visited[el.ind-1] == False:
                q.append(KV(el.ind-1, el.lv+1))
                visited[el.ind-1] = True

            if el.ind+1 < l and visited[el.ind+1] == False:
                q.append(KV(el.ind+1, el.lv+1))
                visited[el.ind+1] = True

            if nums[el.ind] in m:
                for n in m[nums[el.ind]]:
                    if visited[n] == False:
                        visited[n] = True
                        q.append(KV(n, el.lv+1))
                m.pop(nums[el.ind])

        return res
        