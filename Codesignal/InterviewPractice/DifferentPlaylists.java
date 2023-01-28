package Codesignal.InterviewPractice;

import java.math.BigInteger;

public class DifferentPlaylists {

	// a^b with b very large
	static Integer getLargeModule(Integer a, Integer b, Long MOD) {
		BigInteger res = new BigInteger("1");
		BigInteger bigA = new BigInteger(String.valueOf(a));
		BigInteger bigMOD = new BigInteger(String.valueOf(MOD));
		// a^b = (a*a)^(b/2) => b even
		// a^b = a*(a*a)^((b-1)/2) => odd
		while (b > 0) {
			if (b % 2 != 0) {
				res = res.multiply(bigA).mod(bigMOD);
			}
			b = (int) Math.floor(b / 2);
			bigA = bigA.pow(2).mod(bigMOD);
		}
		return res.intValue();
	}

	static int differentPlaylists(int n, int k, int l) {
		if (k > n)
			return 0;

		Long modulo = 1000000007L;
		Long value = 1L;

		if (l > k) {
			for (int i = 0; i < k; i++) {
				value *= (long) (n - i);
				value %= modulo;
			}

			Integer result = getLargeModule(n - k, l - k, modulo);
			value = value * result.longValue();

			value = value % modulo;
		} else {
			int c = n;

			for (int ind = l; ind > 0; ind--) {
				value *= (c--);
				value %= modulo;
			}
		}

		return value.intValue();
	}

	public static void main(String[] args) {

	}
}
