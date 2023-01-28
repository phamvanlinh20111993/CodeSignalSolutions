package Codesignal.Challenge;

public class HappyPanda {

	static int happyPanda(int[] coins, int[][] candies) {
		int happyScore = 0, myMoney = 0, maxScore = 0;
		
		for(int i = 0; i < coins.length; i++) {
			myMoney += coins[i];
			System.out.println(myMoney + "  " + candies[i][0] + "  " + candies[i][1]);
			if(myMoney >= candies[i][0]) {
				if(maxScore < candies[i][1])
					maxScore = candies[i][1];
				myMoney -= candies[i][0];
				happyScore += candies[i][1];
			}else {
				if(maxScore <  candies[i][1])
						happyScore -= candies[i][1];
			}
			System.out.println(happyScore);
		}
		
		System.out.println(happyScore);
		
		return happyScore;
	}

	public static void main(String[] args) {

		System.out.println("############ Test 1 #############");
		int[] coins = { 10, 10, 10 };
		int[][] candies = { { 10, 20 }, { 9, 10 }, { 11, 50 } };
		happyPanda(coins, candies);

		System.out.println("############ Test 2 #############");
		int[] coins1 = { 100, 200, 300 };
		int[][] candies1 = { { 200, 50 }, { 300, 15 }, { 400, 35 } };
		happyPanda(coins1, candies1);
		
		System.out.println("############ Test 3 #############");
		int[] coins2 = { 47, 81, 33, 93 };
		int[][] candies2 = { { 10,40 }, { 1,86 }, { 88,59 }, { 43,36}};
		happyPanda(coins2, candies2);
		
		System.out.println("############ Test 4 #############");
		int[] coins3 = { 129, 27, 317, 339, 214, 159, 236 };
		int[][] candies3 = {{537,708}, 
		                    {277,767}, 
		                    {463,423}, 
		                    {441,121}, 
		                    {412,748}, 
		                    {615,228}, 
		                    {170,210}};
		happyPanda(coins3, candies3);
	}
	

}
