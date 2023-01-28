package Codesignal.Challenge;

import java.util.HashMap;
import java.util.Map;

public class GreedySupplyGigs {

	public static int greedySupplyGigs(String[][] offers) {
		int total = 0, i, j, max, k;
		Map<String, Integer> accept = new HashMap<>(), reject = new HashMap<>();
		
		for (i = 0; i < offers.length; i++) {
			String[] tmp;
			max = 0;
			k = -1;
			
			for (j = 0; j < offers[i].length; j++) {
				tmp = offers[i][j].split(" \\$");
				if (Integer.parseInt(tmp[1]) > max) {
					if (reject.get(tmp[0]) != null) {
						if (accept.get(tmp[0]) != null) {
							if (2 * reject.get(tmp[0]) <= accept.get(tmp[0])) {
								max = Integer.parseInt(tmp[1]);
								k = j;
							}
						}
					} else {
						max = Integer.parseInt(tmp[1]);
						k = j;
					}
				}
			}

			System.out.println(max);

			total += max;

			for (j = 0; j < offers[i].length; j++) {
				tmp = offers[i][j].split(" \\$");
				if (j == k) {
					if (accept.get(tmp[0]) == null)
						accept.put(tmp[0], 1);
					else
						accept.put(tmp[0], accept.get(tmp[0]) + 1);
				} else {
					if (reject.get(tmp[0]) == null)
						reject.put(tmp[0], 1);
					else
						reject.put(tmp[0], reject.get(tmp[0]) + 1);
				}
			}
		}

		for (String key : accept.keySet()) {
			System.out.print(key + "  - " + accept.get(key) + ", ");
		}
		System.out.println();
		for (String key : reject.keySet()) {
			System.out.print(key + " - " + reject.get(key) + ", ");
		}

		System.out.println();

		return total;
	}

	public static void main(String[] args) {
		// System.out.println("################### test 1#####################");
		// String[][] offers = { { "V $75", "H $100", "P $100" }, { "F $100" }, { "P
		// $150" }, { "H $75" },
		// { "R $150", "F $250", "C $125", "X $150", "P $200", "Q $150" }, { "B $125" },
		// { "F $150", "B $75", "H $75" }, { "D $200", "X $175", "C $125", "B $250" },
		// { "R $150", "V $125", "D $200" }, { "H $250" } };
		// System.out.println(greedySupplyGigs(offers));

		// System.out.println("\n################### test 2#####################");
		// String[][] offers1 = { { "A $100","B $200"}, {"A $250"}, {"A $200"} };
		// System.out.println(greedySupplyGigs(offers1));
		//
		// System.out.println("\n################### test 2#####################");
		// String[][] offers2 = { { "A $100", "B $200", "C $150"} };
		// System.out.println(greedySupplyGigs(offers2));

//		String[][] offers3 = { 
//				{}, 
//				{ "L $343" }, 
//				{},
//				{ "U $393", "M $304", "N $127", "P $240", "R $76", "G $270", "A $281", "L $360", "E $202", "Q $117" },
//				{ "A $278" }, 
//				{ "Q $350", "M $398" }, 
//				{ "L $328", "A $167" }, 
//				{ "G $126", "P $75" },
//				{ "P $370", "A $392", "R $246", "L $342" }, 
//				{ "P $276" },
//				{ "N $85", "G $195", "M $272", "A $278", "L $345" },
//				{ "E $274", "M $397", "R $94", "G $374", "P $292" },
//				{ "A $98", "L $112", "E $255", "P $391", "M $351", "N $320", "Q $71", "R $155", "U $338", "G $315" },
//				{ "N $218", "A $217", "L $123", "E $235", "G $181", "R $336", "P $74", "M $121", "Q $148", "U $112" },
//				{ "G $102", "N $259" }, 
//				{ "L $369", "P $197" },
//				{ "U $401", "A $274", "P $166", "L $372", "R $184", "M $106", "Q $320", "N $149", "E $218", "G $333" },
//				{ "M $250", "L $265" }, 
//				{ "N $396", "R $375", "P $194", "U $301", "M $342", "G $105", "L $148" }, 
//				{} 
//				};
//		 System.out.println(greedySupplyGigs(offers3));
		
//		String [][] offers5 = {{"V $75","H $100","P $100"}, 
//				{"F $100"}, 
//				{"P $150"}, 
//				{"H $75"}, 
//				{"R $150","F $250","C $125","X $150","P $200","Q $150"}, 
//				{"B $125"}, 
//				{"F $150","B $75","H $75"}, 
//				{"D $200","X $175","C $125","B $250"}, 
//				{"R $150","V $125","D $200"}, 
//				{"H $250"}};
//		System.out.println(greedySupplyGigs(offers5));
		
		String [][] offers6 = {        {"L $270"}, 
			                           {"B $202","T $240","D $263"}, 
		                               {}, 
		                               {}, 
		                               {}, 
		                               {}, 
		                               {}, 
		                               {}, 
		                               {}, 
		                               {}, 
		                               {"T $101"}, 
                                       {"L $104"}, 
		                               {"L $377","B $252","D $209"}, 
		                               {}, 
		                               {}, 
		                               {"L $419","T $335"}, 
		                               {"B $375"}, 
		                               {}, 
		                               {}, 
		                               {"D $84","B $215","T $384"}, 
		                               {"B $100"}, 
		                               {}, 
		                               {"T $212","B $173","D $313"}, 
                                       {"B $341","L $399"}, 
		                               {}, 
		                               {}, 
		                               {"L $159","T $234"}, 
		                               {"B $186","D $344","L $80"}};
		System.out.println(greedySupplyGigs(offers6));
	}

}
