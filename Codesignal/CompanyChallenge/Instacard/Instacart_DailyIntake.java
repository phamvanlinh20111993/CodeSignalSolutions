package Codesignal.CompanyChallenge.Instacard;

import java.util.ArrayList;
import java.util.List;

public class Instacart_DailyIntake {

	static int MIN_INDEX_CALORIC;
	static boolean[] isVisited;
	static List<Integer> listCalorics;

	static void getAllPossibleSum(int pos, boolean[] isVisited, int[] caloricValue, int sum) {

		for (int i = pos; i < caloricValue.length; i++) {
			if (!isVisited[i]) {
				int indexE = listCalorics.size();
				if (Math.abs(sum + caloricValue[i] - 1900) <= MIN_INDEX_CALORIC) {
					sum += caloricValue[i];
					isVisited[i] = true;
					listCalorics.add(caloricValue[i]);
					
					indexE = listCalorics.size();
					if(MIN_INDEX_CALORIC > Math.abs(sum - 1900)) {
						MIN_INDEX_CALORIC = Math.abs(sum - 1900);
					}
					
					System.out.println("val " + MIN_INDEX_CALORIC);
					
					if(Math.abs(sum - 1900) == MIN_INDEX_CALORIC) {
						for(Integer ind : listCalorics) {
							System.out.print(ind + "-");
						}
						System.out.println();
					}
					
					getAllPossibleSum(pos + 1, isVisited, caloricValue, sum);
				}

				sum -= caloricValue[i];
				isVisited[i] = false;
//				listCalorics.remove(indexE-1);
			}
		}
	}

	static int[] dailyIntake(int[] caloricValue) {
		int i;
		MIN_INDEX_CALORIC = Integer.MAX_VALUE;
		isVisited = new boolean[caloricValue.length];
		listCalorics = new ArrayList<>();

		for (i = 0; i < caloricValue.length; i++) {
			isVisited[i] = false;
		}
		getAllPossibleSum(0, isVisited, caloricValue, 0);
		return null;
	}

	public static void main(String[] args) {
		System.out.println("############### test 1 #################");
		int[] caloricValue = { 1200, 900, 975, 1050 };
		dailyIntake(caloricValue);
	}

}
