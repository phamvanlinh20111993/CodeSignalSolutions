package Codesignal.Challenge;

public class PrimeSumOfPrimeFactors {

	static boolean checkPrimNumber(int n) {
		int ind, count = 0; 
		for(ind = 1; ind <= Math.sqrt(n); ind++)
			if(n%ind == 0) count+=2;
		
		return count == 2;
	}
	
	static int findListOfPrimeFactors(int n) {
		for(int i = 2; i<= n ;i++) {
			if(n%i == 0) 
				return i + findListOfPrimeFactors(n/i);
		}
		
		return 0;
	}
	
	static boolean primeSumOfPrimeFactors(int n) {
		return checkPrimNumber( findListOfPrimeFactors(n));
	}
	
	public static void main(String[] args) {
		System.out.println("############ Test 1 ############");
		System.out.println(primeSumOfPrimeFactors(148));
		
		System.out.println("############ Test 1 ############");
		System.out.println(primeSumOfPrimeFactors(2145353448));
	}

}
