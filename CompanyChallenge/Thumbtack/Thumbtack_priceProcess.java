package CompanyChallenge.Thumbtack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Thumbtack_priceProcess {

	static int[] priceSuggestion(int[] contractData) 
	{
		List<Integer> data = new ArrayList<Integer>();
		int length = 2, i = 0;
		int[] result = null;
		if (contractData.length < 2) {
			length = 0;
			result = new int[length];
			return result;
		} else if (contractData.length == 2) {
			result = new int[length];
			if (contractData[0] < contractData[1]) {
				result[0] = contractData[0];
				result[1] = contractData[1];
			} else {
				result[0] = contractData[1];
				result[1] = contractData[0];
			}
			return result;
		} else {
			for (; i < contractData.length; i++)
				data.add(contractData[i]);
			Collections.sort(data);

		}

		result = new int[length];
		float median = (float) data.size() / 4;

		System.out.println(median);

		if (median != (int) median) {
			result[0] = data.get((int) median);
			result[1] = data.get(data.size() / 2 + (int) median);
		} else {
	
			int left = (int) Math.floor((float) (data.get((int) median) + data.get((int) median - 1)) / 2);
			int right = (int) Math.ceil(
					(float) (data.get((int) median + data.size() / 2) + data.get((int) median - 1 + data.size() / 2))
							/ 2);
			result[0] = left;
			result[1] = right;
		}

		System.out.println(result[0] + "    " + result[1]);
		return result;
	}

	public static void main(String[] args) {
		/**
		 * test 1
		 */
		System.out.println("###############################################");
		int[] contractData = { 10, 15, 14, 7, 11, 15 };
		priceSuggestion(contractData);
		
		/**
		 * test 2
		 */
		System.out.println("###############################################");
		int [] contractData1 = {1, 5, 6, 3, 2, 4, 7, 8};
		priceSuggestion(contractData1);
		
		/**
		 * test 2
		 */
		System.out.println("###############################################");
		int [] contractData2 = {1, 5, 6, 3};
		priceSuggestion(contractData2);
	}

}
