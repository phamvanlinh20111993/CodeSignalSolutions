package InterviewPractice.BackTracking;
import java.util.TreeSet;

public class PosibleSum {

	static int[] mark;
	static int[] re;
	static TreeSet<Integer> distinctSum;

	static void TryFindSum(int[] numbers, int i, int length) {

		for (int j = 0; j < numbers.length; j++) {
			if (mark[j] == 0) {
				if (i > 0) {
					if (re[i - 1] < numbers[j]) {
						re[i] = numbers[j];
						mark[j] = 1;
						if (i == length) {
							int sum = 1;
							for (int k = 0; k <= length; k++) {
								sum *= re[k];
							}
							distinctSum.add(sum);
						} else {
							TryFindSum(numbers, i + 1, length);
						}
						mark[j] = 0;
					}
				} else {
					re[i] = numbers[j];
					mark[j] = 1;
					if (i == length) {
						int sum = 1;
						for (int k = 0; k <= length; k++) {
							sum *= re[k];
						}
					
						distinctSum.add(sum);
					} else {
						TryFindSum(numbers, i + 1, length);
					}
					mark[j] = 0;
				}

			}
		}
	}

	public static int productFromDifferentDigits(int n) {

		int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9 };
		distinctSum = new TreeSet<>();
		mark = new int[10];
		re = new int[10];
		
		for (int i = 0; i < 10; i++)
			mark[i] = 0;

		for (int i = 1; i <= 9; i++) {
			TryFindSum(numbers, 0, i);
		}
		distinctSum.add(0);
		distinctSum.add(1);
		distinctSum.add(2);
		for(int index : distinctSum) {
			System.out.println(index);
		}

		System.out.println(distinctSum.size());

		return 0;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("############## Test 1 #############");
		productFromDifferentDigits(5);
	}

}
