package Hackerrank.BitManipulation;

import java.util.List;

/**
 * @author PhamLinh
 * @url https://www.hackerrank.com/challenges/sansa-and-xor/problem?isFullScreen=true
 * 
 */
public class SansaAndXOR {

	// (1^2)^(2^3) = 1^3 (because 2^2 = 0)
	public static int sansaXor(List<Integer> arr) {
		int res = 0;

		if (arr.size() % 2 != 0) {
			res = arr.get(0);
			for (int ind = 2; ind < arr.size(); ind += 2) {
				res = res ^ arr.get(ind);
			}
		}

		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
