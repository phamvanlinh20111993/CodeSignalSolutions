package LeetCode;

/**
 * url: https://leetcode.com/problems/fruit-into-baskets/description/?envType=daily-question&envId=2025-08-04
 * 
 * You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.

You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:

You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.
Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
Given the integer array fruits, return the maximum number of fruits you can pick.

 

Example 1:

Input: fruits = [1,2,1]
Output: 3
Explanation: We can pick from all 3 trees.
Example 2:

Input: fruits = [0,1,2,2]
Output: 3
Explanation: We can pick from trees [1,2,2].
If we had started at the first tree, we would only pick from trees [0,1].
Example 3:

Input: fruits = [1,2,3,2,2]
Output: 4
Explanation: We can pick from trees [2,3,2,2].
If we had started at the first tree, we would only pick from trees [1,2].
 

Constraints:

1 <= fruits.length <= 105
0 <= fruits[i] < fruits.length

 */


public class FruitIntoBaskets {

	class Data {
		public int val;
		public int lastIndex;
		public int count;

		public Data() {
		}

		public Data(int val, int lastIndex, int count) {
			this.val = val;
			this.lastIndex = lastIndex;
			this.count = count;
		}

		public String toString() {
			return "[val=" + this.val + ", lastIndex=" + this.lastIndex + ", count=" + this.count + "]";
		}
	}

	public int totalFruit(int[] fruits) {
		Data[] distinctTwoE = new Data[2];
		distinctTwoE[0] = new Data(-1, 0, 0);
		distinctTwoE[1] = new Data(-1, 0, 0);

		int amount = 0;

		for (int ind = 0; ind < fruits.length; ind++) {
			if (distinctTwoE[0].val == fruits[ind]) {
				distinctTwoE[0].lastIndex = ind;
				distinctTwoE[0].count++;
				int currAmount = distinctTwoE[0].count + distinctTwoE[1].count;
				if (amount < currAmount) {
					amount = currAmount;
				}
				continue;
			}

			if (distinctTwoE[1].val == fruits[ind]) {
				distinctTwoE[1].lastIndex = ind;
				distinctTwoE[1].count++;
				int currAmount = distinctTwoE[0].count + distinctTwoE[1].count;
				if (amount < currAmount) {
					amount = currAmount;
				}
				continue;
			}

			if (distinctTwoE[0].val == -1) {
				distinctTwoE[0].val = fruits[ind];
				distinctTwoE[0].count = 1;
				distinctTwoE[0].lastIndex = ind;
				int currAmount = distinctTwoE[0].count + distinctTwoE[1].count;
				if (amount < currAmount) {
					amount = currAmount;
				}
				continue;
			}

			if (distinctTwoE[1].val == -1) {
				distinctTwoE[1].val = fruits[ind];
				distinctTwoE[1].count = 1;
				distinctTwoE[1].lastIndex = ind;
				int currAmount = distinctTwoE[0].count + distinctTwoE[1].count;
				if (amount < currAmount) {
					amount = currAmount;
				}
				continue;
			}

			if (distinctTwoE[0].lastIndex > distinctTwoE[1].lastIndex) {
				distinctTwoE[0].count = distinctTwoE[0].lastIndex - distinctTwoE[1].lastIndex;
				distinctTwoE[1].val = fruits[ind];
				distinctTwoE[1].count = 1;
				distinctTwoE[1].lastIndex = ind;
			} else {
				distinctTwoE[1].count = distinctTwoE[1].lastIndex - distinctTwoE[0].lastIndex;
				distinctTwoE[0].val = fruits[ind];
				distinctTwoE[0].count = 1;
				distinctTwoE[0].lastIndex = ind;
			}
		}

		return amount;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
