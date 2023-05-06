package Hackerrank.Implementation;

import java.util.Arrays;

/**
 * 
 * @author PhamLinh
 * @url https://www.hackerrank.com/challenges/electronics-shop/problem
 *
 *A person wants to determine the most expensive computer keyboard and USB drive that can be purchased with a give budget. Given price lists for keyboards and USB drives and a budget, find the cost to buy them. If it is not possible to buy both items, return .

Example



The person can buy a , or a . Choose the latter as the more expensive option and return .

Function Description

Complete the getMoneySpent function in the editor below.

getMoneySpent has the following parameter(s):

int keyboards[n]: the keyboard prices
int drives[m]: the drive prices
int b: the budget
Returns

int: the maximum that can be spent, or  if it is not possible to buy both items
Input Format

The first line contains three space-separated integers , , and , the budget, the number of keyboard models and the number of USB drive models.
The second line contains  space-separated integers , the prices of each keyboard model.
The third line contains  space-separated integers , the prices of the USB drives.

Constraints

The price of each item is in the inclusive range .
Sample Input 0

10 2 3
3 1
5 2 8
Sample Output 0

9
Explanation 0

Buy the  keyboard and the  USB drive for a total cost of .

Sample Input 1

5 1 1
4
5
Sample Output 1

-1
Explanation 1

There is no way to buy one keyboard and one USB drive because , so return .
 */
public class ElectronicsShop {

    static int binarySearch(int[] data, int l, int r, int v) {
	int m = (l + r) / 2;
	if (l < r) {
	    if (data[m] > v) {
		return binarySearch(data, l, m - 1, v);
	    } else if (data[m] == v) {
		return m;
	    } else {
		return binarySearch(data, m + 1, r, v);
	    }
	}

	return l;
    }

    /*
     * Complete the getMoneySpent function below.
     */
    static int getMoneySpent(int[] keyboards, int[] drives, int b) {
	/*
	 * Write your code here.
	 */
	// Way 1
//	int max = -1;
//	for (int i = 0; i < keyboards.length; i++) {
//	    for (int j = 0; j < drives.length; j++) {
//		int v = keyboards[i] + drives[j];
//		if (v <= b && v > max) {
//		    max = v;
//		}
//	    }
//	}

	// Way 2
	int max = -1;
	Arrays.sort(drives);
	for (int i = 0; i < keyboards.length; i++) {
	    int v = b - keyboards[i];
	    int findInd = binarySearch(drives, 0, drives.length - 1, v);
	    for (int j = findInd - 1; j <= findInd + 1; j++) {
		if (j <= -1 || j >= drives.length) {
		    continue;
		}

		int m = keyboards[i] + drives[j];
		if (m <= b && m > max) {
		    max = m;
		}
	    }

	}

	return max;
    }
}
