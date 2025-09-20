package LeetCode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Url: https://leetcode.com/problems/maximum-average-pass-ratio/description/?envType=daily-question&envId=2025-09-01
 * 
 * There is a school that has classes of students and each class will be having a final exam. You are given a 2D integer array classes, where classes[i] = [passi, totali]. You know beforehand that in the ith class, there are totali total students, but only passi number of students will pass the exam.

You are also given an integer extraStudents. There are another extraStudents brilliant students that are guaranteed to pass the exam of any class they are assigned to. You want to assign each of the extraStudents students to a class in a way that maximizes the average pass ratio across all the classes.

The pass ratio of a class is equal to the number of students of the class that will pass the exam divided by the total number of students of the class. The average pass ratio is the sum of pass ratios of all the classes divided by the number of the classes.

Return the maximum possible average pass ratio after assigning the extraStudents students. Answers within 10-5 of the actual answer will be accepted.

 

Example 1:

Input: classes = [[1,2],[3,5],[2,2]], extraStudents = 2
Output: 0.78333
Explanation: You can assign the two extra students to the first class. The average pass ratio will be equal to (3/4 + 3/5 + 2/2) / 3 = 0.78333.
Example 2:

Input: classes = [[2,4],[3,9],[4,5],[2,10]], extraStudents = 4
Output: 0.53485
 

Constraints:

1 <= classes.length <= 105
classes[i].length == 2
1 <= passi <= totali <= 105
1 <= extraStudents <= 105

 */
public class MaximumAveragePassRatio {
	
	 /**
   Check for: 
    Input: classes = [[2,4],[3,9],[4,5],[2,10]], extraStudents = 4
    Output: 0.53485
      
    2/4=0.5, 3/5 = 0.6     => 0.1
             4/6=0.6666      => 0.06666666
             5/7=0.7142         => 0.0476
    3/9=0.333, 4/10 = 0.4  => 0.0666666
               5/11 = 0.45   => 0.054 
               6/12=  0.5       => 0.05
    4/5=0.8, 5/6=0.83      => 0.03
             6/7=0.85714     => 0.0238
             7/8=0.875          => 0.01785714285
    2/10=0.2, 3/11= 0.27   => 0.07
              4/12=0.3333    => 0.06
              5/13=0.384        => 0.05128

    => check Hints and use Heap. Steps:
       1. Check the current ratio when add 1 extra students to each classes => put to the Heap then keep the max change ratio after add.
       2. Pickup the max change ratio then re-add, Heap auto re-order the change ratio status
       3. Continue until the extraStudents == 0. Check the result.
     */

    record Data(int numerator, int denominator, double changeRatio){}

    public double maxAverageRatio(int[][] classes, int extraStudents) {
        int l = classes.length;
        PriorityQueue<Data> priorityQueue = new PriorityQueue<>(new Comparator<Data>() {
            public int compare(Data o1, Data o2) {
                return o2.changeRatio > o1.changeRatio ? 1 : -1;
            }
        });

        extraStudents--;
        double res = 0.0D;
        for(int ind = 0; ind < l; ind++){
            double ratio = (double)classes[ind][0]/classes[ind][1];
            double newRatio = (double)(classes[ind][0] + 1)/(classes[ind][1]+1); 
            res += ratio;
            priorityQueue.add(new Data(classes[ind][0]+1, classes[ind][1]+1, newRatio - ratio));
        }

    //    System.out.println("priorityQueue " + extraStudents  + ": " + priorityQueue);

        while(extraStudents > 0){
            Data currentMaxChangeRatio = priorityQueue.poll();
            res += currentMaxChangeRatio.changeRatio;
            double ratio = (double)currentMaxChangeRatio.numerator/currentMaxChangeRatio.denominator;
            double newRatio = (double)(currentMaxChangeRatio.numerator+1)/(currentMaxChangeRatio.denominator+1); 

            priorityQueue.add(new Data(currentMaxChangeRatio.numerator+1, currentMaxChangeRatio.denominator+1, newRatio - ratio));

    //         System.out.println("priorityQueue " + extraStudents  + ": " + priorityQueue);

            extraStudents --;
        }

        Data currentMaxChangeRatio = priorityQueue.poll();
        res += currentMaxChangeRatio.changeRatio;

        return res/l;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
