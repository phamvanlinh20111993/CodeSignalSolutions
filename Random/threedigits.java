package Random;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * 
 * @author PhamLinh
 * @Url: https://open.kattis.com/problems/threedigits
 */
public class threedigits {

    public static long stripLastZeros(long num) {
	while (num > 9 && num % 10 == 0)
	    num = num / 10;

	return num;
    }

    public static long getLastThreeNumber(long num) {
	long res = 0;
	int count = 0;

	if (num < 1000000)
	    return num;

	while (num > 9 && count < 6) {
	    res += (num % 10) * (int) Math.pow(10, count++);
	    num = (int) Math.floor(num / 10);
	}

	return res;
    }

    public static String threeDigits(Integer num) {

	long res = 1l;
	for (int ind = 2; ind <= num; ind++) {
	    res = getLastThreeNumber(stripLastZeros(stripLastZeros(res) * stripLastZeros(ind)));
	}

	String resStr = String.valueOf(stripLastZeros(res));

	if (num > 6 && resStr.length() < 3) {
	    while (resStr.length() < 3) {
		resStr = "0" + resStr;
	    }
	}

	return resStr.length() > 3 ? resStr.substring(resStr.length() - 3) : resStr;
    }

    public static String factorialBig(long num) {
	BigInteger n = new BigInteger("1");
	for (int ind = 2; ind <= num; ind++) {
	    n = n.multiply(new BigInteger(String.valueOf(ind)));
	}

	String res = n.toString().replaceAll("0+$", "");
	return res.length() > 3 ? res.substring(res.length() - 3) : res;
    }

    public static void main(String[] args) {

	for (int ind = 1; ind <= 1000; ind++) {
	    System.out.println("################# Test " + ind + " ####################");
	    String res = threeDigits(ind);
	    String real = factorialBig(ind);
	    System.out.println(res + " " + real + " " + real.equals(res));
	}

	System.out.println("################# Test 32 ####################");
	System.out.println(threeDigits(9992921));

	System.out.println("################# Test 33 ####################");
	System.out.println(threeDigits(10000000));

	// System.out.println(new BigInteger("620448401733239439360000").multiply(new
	// BigInteger("25")));
	System.out.println(new BigInteger("62044840173323943936").multiply(new BigInteger("25")));

	// Scanner sc = new Scanner(System.in);
	// Integer in = sc.nextInt();
	// System.out.println(threeDigits(in));
	// sc.close();
    }

}
