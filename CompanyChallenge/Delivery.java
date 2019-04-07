package CompanyChallenge;

public class Delivery {
	
	boolean [] delivery (int [] order, int [][] shoppers) {
		
		boolean [] state = new boolean[shoppers.length];
		int i = 0;
		double temp = 0;
		for(i = 0; i < shoppers.length; i++) {
			temp = 0;
			temp = (double)(order[0] +shoppers[i][0])/shoppers[i][1] +  shoppers[i][2];
			if(temp >= order[1] && temp <= (order[1] + order[2]) )
				state[i] = true;
			else state[i] = false;
		}
		
		return state;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean [] t = new boolean[2];
		
		//t = delivery({100, 4, 4}, {100, 33, 1});
	}

}
