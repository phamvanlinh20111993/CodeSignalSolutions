package LeetCode;

/**
 * 
 * url: https://leetcode.com/problems/angle-between-hands-of-a-clock/description/?envType=daily-question&envId=2026-06-18
 * 
 * Given two numbers, hour and minutes, return the smaller angle (in degrees) formed between the hour and the minute hand.

Answers within 10-5 of the actual value will be accepted as correct.

 

Example 1:


Input: hour = 12, minutes = 30
Output: 165
Example 2:


Input: hour = 3, minutes = 30
Output: 75
Example 3:


Input: hour = 3, minutes = 15
Output: 7.5
 

Constraints:

1 <= hour <= 12
0 <= minutes <= 59
 */
public class Angle_Between_Hands_of_a_Clock_1344 {

	// 1 minute = 360/60 = 6 degree
	// 1 hour = 360/12 = 30 degrees
	// move between hours. Eg: 2h-3h:
	// 60 minutes move 30 degrees => 1 minute = 30/60 = 3/6 = 1/2 degress
	public double angleClock(int hour, int minutes) {
		double minuteDegree = 6 * minutes;
		double hourDegree = (hour < 12 ? hour * 30 : 0) + minutes / 2.0;
		// System.out.println(minuteDegree + " " + hourDegree);
		double curDegree = Math.abs(minuteDegree - hourDegree);
		return curDegree > 180 ? 360 - curDegree : curDegree;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
