package Hackerrank.Greedy;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * @author PhamLinh
 * @Url https://www.hackerrank.com/challenges/angry-children/problem?isFullScreen=true
 */
public class MaxMin {

	public static int maxMin(int k, List<Integer> arr) {
		// Write your code here
		arr = arr.stream().sorted().collect(Collectors.toList());
		int min = 1000000000;

		for (int ind = 0; ind < arr.size() - k + 1; ind++) {
			if (arr.get(ind + k - 1) - arr.get(ind) < min) {
				min = arr.get(ind + k - 1) - arr.get(ind);
			}
		}

		return min;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
