package Codesignal.CompanyChallenge.Thumbtack;

import java.util.HashMap;
import java.util.TreeMap;

public class Thumbtack_CatigoryRecomendations {

	static String categoryRecommendations(String[][] requestData, String[] proSelections) {

		int i, j = 0;
		String result = "";
		TreeMap<String, Double> totalScore = new TreeMap<>();
		TreeMap<String, Integer> timeDuplicate = new TreeMap<>();

		for (i = 0; i < requestData.length; i++) {
			int total = 0, duplicate = 0;
			HashMap<String, Integer> temp = new HashMap<>();

			for (j = 0; j < proSelections.length; j++) {
				if (temp.get(proSelections[j]) == null) {
					total++;
					temp.put(proSelections[j], 1);
				}
			}

			for (j = 0; j < requestData[i].length; j++) {
				if (temp.get(requestData[i][j]) == null) {
					total++;
					temp.put(requestData[i][j], 1);

					if (timeDuplicate.get(requestData[i][j]) == null) {
						timeDuplicate.put(requestData[i][j], 0);
					}
					timeDuplicate.put(requestData[i][j], timeDuplicate.get(requestData[i][j]) + 1);

				} else {
					duplicate++;
				}
			}

			for (String key : temp.keySet()) {
				if (totalScore.get(key) == null) {
					totalScore.put(key, (double) duplicate / total);
				} else {
					totalScore.put(key, totalScore.get(key) + (double) duplicate / total);
				}
			}

		}

		double max = 0;

		for (String key : timeDuplicate.keySet()) {
			if (totalScore.get(key) / timeDuplicate.get(key) > max) {
				max = totalScore.get(key) / timeDuplicate.get(key);
				result = key;
			}
		//	System.out.println(key + "   " + totalScore.get(key) + "  " + timeDuplicate.get(key));
		}

		System.out.println("######### result: " + result);
		return result;
	}

	public static void main(String[] args) {

		System.out.println("################ Test 1 ################");
		String[][] requestData = {
				{ "Accounting", "Administrative Support", "Advertising", "Bodyguard", "Auctioneering" },
				{ "Accounting", "Administrative Support" }, { "Advertising", "Auctioneering", "Bodyguard" },
				{ "Bodyguard", "Services Business", "Consulting" } };
		String[] proSelections = { "Accounting", "Advertising" };
		categoryRecommendations(requestData, proSelections);

		System.out.println("############### Test 2 ##################");
		String[][] requestData1 = { { "Accounting", "Bodyguard" }, { "Accounting", "Auctioneering" } };
		String[] proSelections1 = { "Accounting" };
		categoryRecommendations(requestData1, proSelections1);

		System.out.println("################### Test 3 ##################");
		String[][] requestData2 = { { "Bodyguard" } };
		String[] proSelections2 = { "Bodyguard" };
		categoryRecommendations(requestData2, proSelections2);

		System.out.println("##################### Test 4 ###################");
		String[][] requestData3 = { { "String" } };
		String[] proSelections3 = { "Alpha", "Beta", "Gamma", "ZZZ" };
		categoryRecommendations(requestData3, proSelections3);

		System.out.println("##################### Test 5 ###################");
		String[][] requestData4 = { { "A", "B", "C" }, { "A", "C", "DD", "E", "F", "G" }, { "B" }, { "BB", "DD", "Z" },
				{ "BZ" } };
		String[] proSelections4 = { "A" };
		categoryRecommendations(requestData4, proSelections4);

	}

}
