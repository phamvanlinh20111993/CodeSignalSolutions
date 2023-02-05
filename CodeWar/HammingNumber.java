package CodeWar;

import java.util.ArrayList;
import java.util.List;

public class HammingNumber {

	public static long nextHammingNumberAt(long currentNumber) {
		int _twoP = (int) Math.ceil(Math.log10(currentNumber) / Math.log10(2));
		int _threeP = (int) Math.ceil(Math.log10(currentNumber) / Math.log10(3));
		int _fiveP = (int) Math.ceil(Math.log10(currentNumber) / Math.log10(5));
		int[] nums = { _twoP, _threeP, _fiveP };

		return check(nums, currentNumber);
	}

	public static long check(int[] nums, long minData) {
		long minMaxNumber = 1000 * minData;
		List<Long> addNumber = new ArrayList<>();

		for (int ind = nums[0] - 1; ind >= 0; ind--) {
			for (int in = 0; in <= nums[1]; in++) {
				long currentN = (long) Math.pow(3, in) * (long) Math.pow(2, ind);
				if (currentN < minData) {
					for (int p = 0; p <= nums[2]; p++) {
						long currentNT = currentN * (long) Math.pow(5, p);
						if (currentNT > minData) {
							addNumber.add(currentNT);
							break;
						}
					}
				} else if(currentN > minData) {
					addNumber.add(currentN);
					break;
				}
			}

		}

		long lackData = (long) Math.pow(2, nums[0]);
		if (lackData > minData)
			addNumber.add(lackData);

		minMaxNumber = addNumber.get(0);
		for (int ind = 1; ind < addNumber.size(); ind++) {
			if (minMaxNumber > addNumber.get(ind)) {
				minMaxNumber = addNumber.get(ind);
			}
		}

		return minMaxNumber;
	}

	public static long findN(int n) {
		long firstNumber = 2;
		int count = 2;
		while (count < n) {
			long nextNum = nextHammingNumberAt(firstNumber);
			firstNumber = nextNum;
			// System.out.println(firstNumber);
			count++;
		}

		return firstNumber;
	}

	public static void main(String[] args) {
		// System.out.println(nextHammingNumberAt(6));
		long startTime = System.nanoTime();
		int n = 1200;
		System.out.println("the " + n + "th hamming number is " + findN(n));

		long endTime = System.nanoTime();
		long totalTime = endTime - startTime;
		System.out.println(totalTime / 1000000000d + "s");
	}

}
