package Codesignal.CompanyChallenge.TwoSignal;

public class Two_Signal_OptimalStockBasket {

	static int max_TotalSum = 0;

	static void getAllPosibleSum(int[][] stocks, int riskBudget, int i, int sum, int total) {

		if (i == stocks.length && total > max_TotalSum) {
			max_TotalSum = total;
		}
		for (int j = i; j < stocks.length; j++) {
			if (sum + stocks[j][1] <= riskBudget) {
				if (j < stocks.length) {
					getAllPosibleSum(stocks, riskBudget, j + 1, sum + stocks[j][1], total + stocks[j][0]);
				}
			} else {
				if (total > max_TotalSum) {
					max_TotalSum = total;
				}
			}
		}
	}

	static int optimalStockBasket(int[][] stocks, int riskBudget) {
		int i, sum = 0, total = 0;
		max_TotalSum = 0;
		for (i = 0; i < stocks.length; i++) {
			sum += stocks[i][1];
			total += stocks[i][0];
		}

		if (sum <= riskBudget) {
			System.out.println("total " + total);
			return total < 0 ? 0 : total;
		} else {
			getAllPosibleSum(stocks, riskBudget, 0, 0, 0);
			System.out.println("total " + max_TotalSum);
		}

		return max_TotalSum < 0 ? 0 : max_TotalSum;
	}

	public static void main(String[] args) {
		// 21
		System.out.println("################ test 1 ###############");
		int[][] stocks = { { -1, 2 }, { 10, 15 }, { 11, 13 }, { 9, 10 } };
		int riskBudget = 30;
		optimalStockBasket(stocks, riskBudget);

		// 46
		System.out.println("################ test 2 ###############");
		int[][] stocks1 = { { 1, 4 }, 
				            { 2, 5 }, 
				            { -1, 2 }, 
				            { -2, 5 }, 
				            { 5, 1 }, //1
				            { 3, 2 }, //1
				            { 1, 1 }, //1
				            { 1, 2 }, 
				            { 10, 8 },//1
				            { 20, 18 }, 
				            { 13, 13 }, 
				            { 9, 12 }, 
				            { 12, 7 }, //1
				            { 10, 12 }, 
				            { 15, 11 } };//1
		int riskBudget1 = 31;
		optimalStockBasket(stocks1, riskBudget1);

		// 1051
		System.out.println("################ test 3 ###############");
		int[][] stocks2 = { { 107, 7 }, 
				            { 101, 2 }, 
				            { 105, 5 }, 
				            { 102, 3 }, 
				            { 106, 6 }, 
				            { 100, 1 }, 
				            { 110, 10 },
				            { 108, 8 }, 
				            { 109, 9 }, 
				            { 103, 4 } };
		int riskBudget2 = 55;
		optimalStockBasket(stocks2, riskBudget2);
		
		// 0
		System.out.println("################ test 4 ###############");
		int[][] stocks3 = { { -1, 2 }, { -2, 3 }, { -1, 1 }, { -3, 2 }};
		int riskBudget3 = 10;
		optimalStockBasket(stocks3, riskBudget3);
	} 

}
