package Challenge;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * @author LinhPV6956
 * @created 3/1/2018
 *
 */
public class FibonacciSnowMan1 {

	public static int[] fibonacci = { 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181,
			6765, 10946, 17711, 28657 };
	static ArrayList<Integer> temp;

	static void findFibonacciList(ArrayList<Integer> temp, int n, int k) {
		int index = 0, cal;
		while (fibonacci[index] <= n)
			index++;
		cal = n;
		if (index > 0)
			cal = n - fibonacci[index - 1];

		if (cal > 1) {
			temp.add(fibonacci[index - 1]);
			if (temp.size() < k) {
				findFibonacciList(temp, cal, k);
			}
		} else {
			if (cal > 0)
				temp.add(cal);
			if (temp.size() < k) {
				if (index > 2) {
					temp.add(fibonacci[index - 2]);
					temp.add(fibonacci[index - 3]);
				}

				if (temp.size() < k && temp.size() > 0) {
					Collections.sort(temp);
					index = 0;
					while (temp.get(index) == 1)
						index++;
					cal = temp.get(index);

					temp.remove(index);

					findFibonacciList(temp, cal, k);
				}
			}
		}

	}

	static int[] fibonacciSnowMan(int n, int k) {

		temp = new ArrayList<>();
		if (n < k) {
			System.out.println("error");
			return new int[0];
		}

		if (k == 1) {
			int[] temp = { n };
			System.out.println(temp[0]);
			return temp;
		}

		findFibonacciList(temp, n, k);

		if (temp.size() != k) {
			System.out.println("error");
			int[] temp = { -1 };
			return temp;
		} else {
			Collections.sort(temp);
			for (int i = 0; i < temp.size(); i++) {
				System.out.print(temp.get(i) + " ");
			}
			System.out.println();
			return temp.stream().mapToInt(i -> i).toArray();
		}
	}

	public static void main(String[] args) {
		System.out.println("constrant : n < 10^4, k <= 500 and alway existed array k");
		System.out.println("############# test 1 ####################");
		fibonacciSnowMan(28, 5);
		//
		System.out.println("############# test 2 ####################");
		fibonacciSnowMan(33, 7);

		System.out.println("############# test 3 ####################");
		fibonacciSnowMan(1, 1);

		System.out.println("############# test 4 ####################");
		fibonacciSnowMan(21, 1);
		//
		System.out.println("############# test 5 ####################");
		fibonacciSnowMan(8, 2);
		//
		System.out.println("############# test 6 ####################");
		fibonacciSnowMan(29, 6);

		System.out.println("############# test 7 ####################");
		fibonacciSnowMan(23, 15);

		System.out.println("############# test 8 ####################");
		fibonacciSnowMan(9834, 400);

		System.out.println("############# test 9 ####################");
		fibonacciSnowMan(13, 10);

		System.out.println("############# test 10 ####################");
		fibonacciSnowMan(53, 10);
	}
}
