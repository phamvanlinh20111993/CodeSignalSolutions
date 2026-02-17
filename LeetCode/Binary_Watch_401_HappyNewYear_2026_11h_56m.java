package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Url: https://leetcode.com/problems/binary-watch/description/?envType=daily-question&envId=2026-02-17
 * 
 * A binary watch has 4 LEDs on the top to represent the hours (0-11), and 6 LEDs on the bottom to represent the minutes (0-59). Each LED represents a zero or one, with the least significant bit on the right.

For example, the below binary watch reads "4:51".


Given an integer turnedOn which represents the number of LEDs that are currently on (ignoring the PM), return all possible times the watch could represent. You may return the answer in any order.

The hour must not contain a leading zero.

For example, "01:00" is not valid. It should be "1:00".
The minute must consist of two digits and may contain a leading zero.

For example, "10:2" is not valid. It should be "10:02".
 

Example 1:

Input: turnedOn = 1
Output: ["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]
Example 2:

Input: turnedOn = 9
Output: []
 

Constraints:

0 <= turnedOn <= 10
 */
public class Binary_Watch_401_HappyNewYear_2026_11h_56m {
	List<String> res;
	int[] HmBinary = new int[] { 1, 2, 4, 8, 16, 32, 1, 2, 4, 8 };

	public void backtracking(int[] arr, int pos, int turnedOn) {
		if (turnedOn == 0) {
			int hh = arr[9] * HmBinary[9] + arr[8] * HmBinary[8] + arr[7] * HmBinary[7] + arr[6] * HmBinary[6];
			int mm = 0;
			for (int ind = 0; ind < 6; ind++) {
				mm += arr[ind] * HmBinary[ind];
			}

			if (hh > 11 || mm > 59)
				return;

			res.add(hh + ":" + (mm < 10 ? "0" + mm : mm));
			return;
		}

		for (int ind = pos + 1; ind < 10; ind++) {
			arr[ind] = 1;
			backtracking(arr, ind, turnedOn - 1);
			arr[ind] = 0;
		}
	}

	public List<String> readBinaryWatch(int turnedOn) {
		res = new ArrayList<>();
		backtracking(new int[10], -1, turnedOn);
		return res;
	}
}
