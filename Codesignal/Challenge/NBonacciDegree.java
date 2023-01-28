package Codesignal.Challenge;

public class NBonacciDegree {

	static int nbonacciDegree(int[] sequence) {
		int i, j, sum = sequence[0];
	
		for (i = 2; i < sequence.length; i++) {
			sum += sequence[i-1]; 
			
			
			if (sum == sequence[i]) {
				boolean flag = true;
				int sum1 = sum, tmp = 0;
				for(j = i+1; j < sequence.length; j++) {
					sum1 +=  sequence[j-1] - sequence[tmp++];
					if(sum1 != sequence[j]) {
						flag = false;
						break;
					}
				}
				
				if(flag) return i;
			}
		}

		return -1;

	}

	public static void main(String[] args) {

		System.out.println("############# test 1 #############");
		int[] sequence = { 1, 2, 3 };
		System.out.println(nbonacciDegree(sequence));

		System.out.println("############# test 2 #############");
		int[] sequence1 = { 0, 6, -2, 3, 7, 14, 22, 46, 89, 171, 328, 634, 1222, 2355 };
		System.out.println(nbonacciDegree(sequence1));

		System.out.println("############# test 3 #############");
		int[] sequence2 = { -94, 39, -4, -69, -56, -184, -274, -587, -1170, -2271, -4486, -8788, -17302, -34017, -66864,
				-131457, -258428, -508068, -998834, -1963651, -3860438, -7589419, -14920410, -29332752, -57666670 };
		System.out.println(nbonacciDegree(sequence2));

		System.out.println("############# test 4 #############");
		int[] sequence3 = { -3, 2, 1, 0, 3, 4, 7, 14, 25, 46, 85, 156, 287, 528, 971, 1786, 3285, 6042, 11113, 20440,
				37595, 69148, 127183, 233926, 430257, 791366, 1455549, 2677172, 4924087, 9056808, 16658067, 30638962,
				56353837, 103650866, 190643665, 350648368, 644942899 };
		System.out.println(nbonacciDegree(sequence3));

	}
}
