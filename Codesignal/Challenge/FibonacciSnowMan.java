package Codesignal.Challenge;

public class FibonacciSnowMan {
	public static int[] fibonacci = { 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181,
			6765, 10946 };
	static int[] res;
	static int sum = 0;

	static void Try(int pos, int[] res, int i, int n, int k) {
		for (int j = pos; j >= 0; j--) {
			if (sum + fibonacci[j] <= n) {
			//	System.out.println(i + "  " + fibonacci[j]);
				res[i] = fibonacci[j];
				sum += fibonacci[j];

				if (sum < n && i < k - 1) {
					Try(pos, res, i + 1, n, k);
				} else {
					System.out.println("sum " + sum);
					if(sum == n) {
						for (int m = 0; m < k; m++) {
							System.out.print(res[i] + " ");
						}
						System.out.println();
						return;
					}
				}
				sum -= fibonacci[j];
				res[i] = 0;
			}

		}
	}

	static int[] fibonacciSnowMan(int n, int k) {
		int[] result = new int[k];
		res = new int[k];
		if (n < k)
			return new int[0];
		int maxFibonacci = n - (k - 1), i = 0;

		while (fibonacci[i] < maxFibonacci)
			i++;

		int pos = i--;

		for (i = 0; i < k; i++) {
			res[i] = 0;
		}
		
		System.out.println("max " +fibonacci[pos]);
		
		Try(pos, res, 0, n, k);

		return result;
	}

	public static void main(String[] args) {

		System.out.println("############# test 1 ####################");
		fibonacciSnowMan(18, 5);

	}

}
