package InterviewPractice;

public class ClimbingStairs {
	
	
	static int count, sum;
	
	static void Try(int n) {
		
		for(int j = 1; j <= 2; j++) {
			if(sum + j <= n) {
				sum += j;
				if(sum == n)
					count++;
				else if(sum < n)
					Try(n);
				
				sum -= j;
			}
		}
		
	}
	
	static int climbingStairs(int n) {
		count = 0;sum = 0;
		Try(n);
		System.out.println("count " + count);
		return count;
	}
	
	static int climbingStairs1(int n) {
		int f = 1, s = 1, re = 0;
		
		for(int i = 3; i <= n+1; i++) {
			re = f+s;
			f = s;
			s = re;
		}
		
		return re;
	}
	
	public static void main(String[] args) {


		System.out.println("################ test 1 ################");
		climbingStairs(4);
		
		System.out.println("################ test 2 ################");
		climbingStairs(26);
		
//		System.out.println("################ test 3 ################");
//		climbingStairs(48);
		
		System.out.println("################ test 4 ################");
		climbingStairs1(26+1);

	}

}
