/**
	url: https://leetcode.com/problems/minimum-time-to-repair-cars/?envType=problem-list-v2&envId=binary-search
	
	You are given an integer array ranks representing the ranks of some mechanics. ranksi is the rank of the ith mechanic. A mechanic with a rank r can repair n cars in r * n2 minutes.

You are also given an integer cars representing the total number of cars waiting in the garage to be repaired.

Return the minimum time taken to repair all the cars.

Note: All the mechanics can repair the cars simultaneously.

 

Example 1:

Input: ranks = [4,2,3,1], cars = 10
Output: 16
Explanation: 
- The first mechanic will repair two cars. The time required is 4 * 2 * 2 = 16 minutes.
- The second mechanic will repair two cars. The time required is 2 * 2 * 2 = 8 minutes.
- The third mechanic will repair two cars. The time required is 3 * 2 * 2 = 12 minutes.
- The fourth mechanic will repair four cars. The time required is 1 * 4 * 4 = 16 minutes.
It can be proved that the cars cannot be repaired in less than 16 minutes.​​​​​
Example 2:

Input: ranks = [5,1,8], cars = 6
Output: 16
Explanation: 
- The first mechanic will repair one car. The time required is 5 * 1 * 1 = 5 minutes.
- The second mechanic will repair four cars. The time required is 1 * 4 * 4 = 16 minutes.
- The third mechanic will repair one car. The time required is 8 * 1 * 1 = 8 minutes.
It can be proved that the cars cannot be repaired in less than 16 minutes.​​​​​
 

Constraints:

1 <= ranks.length <= 105
1 <= ranks[i] <= 100
1 <= cars <= 106
	
**/
func repairCars(ranks []int, cars int) int64 {
    // use binary search
    minRank := 101
    for _, rank := range ranks {
        if minRank > rank {
            minRank = rank
        }
    } 

    lMinute := int64(1)
    rMinute := int64(minRank*cars)*int64(cars)

    minimumTime := int64(rMinute)

    for lMinute <= rMinute {
        medMinute := lMinute + (rMinute - lMinute)/2
     //   fmt.Println("medMinute=", medMinute, ",lMinute=", lMinute, ",rMinute=", rMinute)
        totalCar := 0
        for _, rank := range ranks {
         //   fmt.Println("rank=", rank, ", value=", math.Sqrt(float64(medMinute)/float64(rank)))
            totalCar += int(math.Floor(math.Sqrt(float64(medMinute)/float64(rank))))
        }

      //  fmt.Println("total car: ", totalCar)
        if totalCar < cars {
            lMinute = medMinute + 1
        }else{
            rMinute = medMinute - 1
            minimumTime = min(minimumTime, medMinute)
        }
    }

    return minimumTime
}