package CompanyChallenge.Mz;

public class Mz_resourceCatchUp {

	static int resourceCatchUp(int[] logOut, int[] logIn) {
		
		
		int time = (int) Math.ceil((float)(logIn[0] - logOut[0])/3600);
		int consumption = logOut[1] - logIn[1];		
		System.out.println(consumption/time);
		
		return consumption/time;
	}
	
	public static void main(String[] args) {
		
		 int [] logOut = {1451604600, 100}, logIn = {1451660401, 36};
		 
		 resourceCatchUp( logOut, logIn);
	}

}
