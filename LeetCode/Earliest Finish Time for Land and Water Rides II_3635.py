"""
url: https://leetcode.com/problems/earliest-finish-time-for-land-and-water-rides-ii/description/

You are given two categories of theme park attractions: land rides and water rides.

Land rides
landStartTime[i] – the earliest time the ith land ride can be boarded.
landDuration[i] – how long the ith land ride lasts.
Water rides
waterStartTime[j] – the earliest time the jth water ride can be boarded.
waterDuration[j] – how long the jth water ride lasts.
A tourist must experience exactly one ride from each category, in either order.

A ride may be started at its opening time or any later moment.
If a ride is started at time t, it finishes at time t + duration.
Immediately after finishing one ride the tourist may board the other (if it is already open) or wait until it opens.
Return the earliest possible time at which the tourist can finish both rides.

 

Example 1:

Input: landStartTime = [2,8], landDuration = [4,1], waterStartTime = [6], waterDuration = [3]

Output: 9

Explanation:​​​​​​​

Plan A (land ride 0 → water ride 0):
Start land ride 0 at time landStartTime[0] = 2. Finish at 2 + landDuration[0] = 6.
Water ride 0 opens at time waterStartTime[0] = 6. Start immediately at 6, finish at 6 + waterDuration[0] = 9.
Plan B (water ride 0 → land ride 1):
Start water ride 0 at time waterStartTime[0] = 6. Finish at 6 + waterDuration[0] = 9.
Land ride 1 opens at landStartTime[1] = 8. Start at time 9, finish at 9 + landDuration[1] = 10.
Plan C (land ride 1 → water ride 0):
Start land ride 1 at time landStartTime[1] = 8. Finish at 8 + landDuration[1] = 9.
Water ride 0 opened at waterStartTime[0] = 6. Start at time 9, finish at 9 + waterDuration[0] = 12.
Plan D (water ride 0 → land ride 0):
Start water ride 0 at time waterStartTime[0] = 6. Finish at 6 + waterDuration[0] = 9.
Land ride 0 opened at landStartTime[0] = 2. Start at time 9, finish at 9 + landDuration[0] = 13.
Plan A gives the earliest finish time of 9.

Example 2:

Input: landStartTime = [5], landDuration = [3], waterStartTime = [1], waterDuration = [10]

Output: 14

Explanation:​​​​​​​

Plan A (water ride 0 → land ride 0):
Start water ride 0 at time waterStartTime[0] = 1. Finish at 1 + waterDuration[0] = 11.
Land ride 0 opened at landStartTime[0] = 5. Start immediately at 11 and finish at 11 + landDuration[0] = 14.
Plan B (land ride 0 → water ride 0):
Start land ride 0 at time landStartTime[0] = 5. Finish at 5 + landDuration[0] = 8.
Water ride 0 opened at waterStartTime[0] = 1. Start immediately at 8 and finish at 8 + waterDuration[0] = 18.
Plan A provides the earliest finish time of 14.​​​​​​​

 

Constraints:

1 <= n, m <= 5 * 104
landStartTime.length == landDuration.length == n
waterStartTime.length == waterDuration.length == m
1 <= landStartTime[i], landDuration[i], waterStartTime[j], waterDuration[j] <= 105
"""

class Solution:
    """
    def sortByStartTime(self, secondStartTime: List[int], secondDuration: List[int]) -> Tuple[List[int], List[int]]:
        # Combine both lists into pairs
        combined = sorted(zip(secondStartTime, secondDuration))
        sortedStart, sortedDuration = zip(*combined)
        return list(sortedStart), list(sortedDuration)

    # call k as finish time, is the second ride [a, b]
    # case 1: k < a: => result = a - k + k + b = a + b
    # case 2: k >=a:                             k + b
    # => min = min(k + b, a + b) => compare k and a, which one is smaller will be the result
    # => Solution:
    #   using sorting for sort second ride [a, b] with a is the key
    #   using slide window to trace index 
    def findEarliestFinishTime(self, firstStartTime: List[int], firstDuration: List[int], secondStartTime: List[int], secondDuration: List[int]) -> int:
        res =  float("inf")
        finishTime = []
        for fInd, firstSt in enumerate(firstStartTime):
            finishTime.append(firstSt + firstDuration[fInd])
        finishTime.sort()

        secondStartTime, secondDuration = self.sortByStartTime(secondStartTime, secondDuration)
       
        tmp = len(secondStartTime)-1
        while tmp > -1 and secondStartTime[tmp] > finishTime[0]:
            res = min(res, secondStartTime[tmp] + secondDuration[tmp])
            tmp -= 1

        traceInd = 0
        for k in finishTime:
            while traceInd < len(secondStartTime) and k >= secondStartTime[traceInd]:
                res = min(res, k + secondDuration[traceInd])
                traceInd += 1
          
        return res

    
    def earliestFinishTime(self, landStartTime: List[int], landDuration: List[int], waterStartTime: List[int], waterDuration: List[int]) -> int:
        return min(self.findEarliestFinishTime(landStartTime, landDuration, waterStartTime, waterDuration), self.findEarliestFinishTime(waterStartTime, waterDuration, landStartTime, landDuration))
    """ 
    
    # chatgpt support improvement
    def _compute_min_finish(
        self,
        firstStart: List[int],
        firstDuration: List[int],
        secondStart: List[int],
        secondDuration: List[int],
    ) -> int:

        # Step 1: compute finish times of first rides
        first_finish = sorted(
            s + d for s, d in zip(firstStart, firstDuration)
        )

        # Step 2: sort second rides by start time
        second = sorted(zip(secondStart, secondDuration))
        n = len(second)

        # Step 3: precompute suffix minimum of (start + duration)
        suffix_min = [0] * n
        suffix_min[-1] = second[-1][0] + second[-1][1]

        for i in range(n - 2, -1, -1):
            suffix_min[i] = min(
                suffix_min[i + 1],
                second[i][0] + second[i][1],
            )

        res = float("inf")
        min_duration_prefix = float("inf")
        j = 0

        # Step 4: iterate over finish times
        for k in first_finish:

            # move pointer to include all rides with start <= k
            while j < n and second[j][0] <= k:
                min_duration_prefix = min(
                    min_duration_prefix,
                    second[j][1],
                )
                j += 1

            # Case 1: already opened rides
            if min_duration_prefix != float("inf"):
                res = min(res, k + min_duration_prefix)

            # Case 2: future rides
            if j < n:
                res = min(res, suffix_min[j])

        return res

    def earliestFinishTime(
        self,
        landStartTime: List[int],
        landDuration: List[int],
        waterStartTime: List[int],
        waterDuration: List[int],
    ) -> int:

        return min(
            self._compute_min_finish(
                landStartTime, landDuration,
                waterStartTime, waterDuration
            ),
            self._compute_min_finish(
                waterStartTime, waterDuration,
                landStartTime, landDuration
            ),
        )