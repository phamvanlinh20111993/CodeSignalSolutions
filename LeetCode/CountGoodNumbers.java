package LeetCode;

public class CountGoodNumbers {

    /*
    0|1|2|3|4
    2 2 0 2 0
    4 3 2 3 2
    6 5 4 5 4
    8 7 6 7 6
        8   8


    => 4*4*5*4*5	


    1=5
    2=20
    3=100
    4=400
    5=2000
    6=8000
    7=40000
    */
	/**
	 * n = 1, => 5
	 * n = 2, => 5*4
	 * n = 3, => 5*4*5 5^2*4
	 * n = 4, => 5^2*4^2
	 * n = 5, => 5^3*4^2
	 * n = 6, => 5^3*4^3
	 * n = 100000, => 5^50000*4^50000
	 */
	// base: 5^13 = 1220703125, MODULO_NUM =>          220703118
	//       5^27 = 7450580596923828224, MODULO_NUM => 769764416
	
	public static final long MODULO_NUM = 1000000007;
	
	public static long recursive(long num, long exp) {
		if (exp < 2) {
			return (long) Math.pow(num, exp);
		}

		long val = 1;
		int minM = 0;
		while (val <= MODULO_NUM) {
			val *= num;
			minM++;
		}

		if (val > MODULO_NUM) {
			val %= MODULO_NUM;
		}
		long res = recursive(val, exp / minM) * (long) Math.pow(num, exp % minM);

		return res % MODULO_NUM;
	}
	
	public static int countGoodNumbers2(long n) {

		long left = n % 2 == 0 ? n / 2 : 1 + n / 2;
		long right = n - left;

		long res = recursive(5l, left) * recursive(4l, right);

		return (int) (res % MODULO_NUM);
	}
	
	public static void main(String[] args) {
				
		System.out.println("Result " + countGoodNumbers2(50000));
		System.out.println("Result " + countGoodNumbers2(1150000));
		System.out.println("Result " + countGoodNumbers2(10000000000000l));
		System.out.println("Result " + countGoodNumbers2(4150000));
		System.out.println("Result " + countGoodNumbers2(451150000));
		System.out.println("Result " + countGoodNumbers2(761150000));
	}

}
