package LeetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * url: https://leetcode.com/problems/sum-of-k-mirror-numbers/?envType=daily-question&envId=2025-06-23
 * 
 * A k-mirror number is a positive integer without leading zeros that reads the same both forward and backward in base-10 as well as in base-k.

For example, 9 is a 2-mirror number. The representation of 9 in base-10 and base-2 are 9 and 1001 respectively, which read the same both forward and backward.
On the contrary, 4 is not a 2-mirror number. The representation of 4 in base-2 is 100, which does not read the same both forward and backward.
Given the base k and the number n, return the sum of the n smallest k-mirror numbers.

 

Example 1:

Input: k = 2, n = 5
Output: 25
Explanation:
The 5 smallest 2-mirror numbers and their representations in base-2 are listed as follows:
  base-10    base-2
    1          1
    3          11
    5          101
    7          111
    9          1001
Their sum = 1 + 3 + 5 + 7 + 9 = 25. 
Example 2:

Input: k = 3, n = 7
Output: 499
Explanation:
The 7 smallest 3-mirror numbers are and their representations in base-3 are listed as follows:
  base-10    base-3
    1          1
    2          2
    4          11
    8          22
    121        11111
    151        12121
    212        21212
Their sum = 1 + 2 + 4 + 8 + 121 + 151 + 212 = 499.
Example 3:

Input: k = 7, n = 17
Output: 20379000
Explanation: The 17 smallest 7-mirror numbers are:
1, 2, 3, 4, 5, 6, 8, 121, 171, 242, 292, 16561, 65656, 2137312, 4602064, 6597956, 6958596
 

Constraints:

2 <= k <= 9
1 <= n <= 30
 * 
 */
public class Sum_of_k_Mirror_Numbers {

	public boolean isMirrorBase10(long n) {
		long t = n;
		long r = 0l;
		int c = (n + "").length() - 1;
		while (t > 0) {
			r += (t % 10l) * Math.pow(10, c--);
			t = t / 10l;
		}
		return r == n;
	}

	public long toBase10(long n, int base) {
		long t = n;
		long r = 0L;
		int c = 0;
		while (t > 0) {
			r += (t % 10l) * Math.pow(base, c++);
			t = t / 10L;
		}
		return r;
	}

	public long toBase10(String n, int base) {
		long r = 0L;
		int c = 0;
		for (int ind = n.length() - 1; ind >= 0; ind--) {
			r += (n.charAt(ind) - '0') * Math.pow(base, c++);
		}

		return r;
	}

	public List<Long> bfs(int base, int count) {
		Queue<String> queue = new LinkedList<>();
		List<Long> res = new ArrayList<>();
		for (int ind = 1; ind < base; ind++) {
			queue.add(ind + "");
			res.add((long) ind);
		}
		List<String> nums = new ArrayList<>();
		int l = 1;
		while (!queue.isEmpty()) {
			String tmp = queue.poll();
			if (tmp.length() > l) {
				if (res.size() >= count) {
					break;
				}
				l = tmp.length();

				for (int ind = 0; ind < nums.size(); ind++) {
					String r = new StringBuilder(nums.get(ind)).reverse().toString();
					long base10Num = toBase10(nums.get(ind) + r, base);
					if (isMirrorBase10(base10Num)) {
						res.add(base10Num);
					}
					if (res.size() >= count) {
						break;
					}
				}

				for (int ind = 0; ind < nums.size(); ind++) {
					String r = new StringBuilder(nums.get(ind)).reverse().toString();
					for (int p = 0; p < base; p++) {
						if (res.size() >= count) {
							break;
						}
						long base10Num = toBase10(nums.get(ind) + p + r, base);
						if (isMirrorBase10(base10Num)) {
							res.add(base10Num);
						}
					}
				}

				nums = new ArrayList<>();
			}

			if (res.size() >= count) {
				break;
			}

			nums.add(tmp);
			for (int ind = 0; ind < base; ind++) {
				queue.add(tmp + ind);
			}
		}

		return res;
	}

	public long kMirror(int k, int n) {
		List<Long> firstNDt = bfs(k, n);
		Long r = 0l;
		for (int ind = 0; ind < n; ind++) {
			r += firstNDt.get(ind);
		}

		return r;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
