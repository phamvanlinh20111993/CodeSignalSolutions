package CompanyChallenge.Thumbtack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class Thumbtack_requestMatching {
	static final String _DIS = "###";

	static String[] requestMatching(String[] pros, int[] distances, int[] travelPreferences) {
		List<String> reTemp = null;
		TreeMap<Integer, String> sortPainter = new TreeMap<>();
		TreeMap<Integer, String> sortNotPainter = new TreeMap<>();
		int i, j, k, length = 0;

		for (i = 0; i < pros.length; i++) {
			if (travelPreferences[i] - distances[i] >= 0) {
				if (sortPainter.get(distances[i]) == null) {
					sortPainter.put(distances[i], pros[i]);
				} else {
					sortPainter.put(distances[i], sortPainter.get(distances[i]) + _DIS + pros[i]);
				}
			} else {
				k = travelPreferences[i] - distances[i];
				if (sortNotPainter.get(k) == null) {
					sortNotPainter.put(k, pros[i]);
				} else {
					sortNotPainter.put(k, sortNotPainter.get(k) + _DIS + pros[i]);
				}
			}
		}

		String[] result = null;
		if (pros.length > 5) {
			result = new String[5];
		} else {
			result = new String[pros.length];
		}

		// for (Integer key : sortPainter.keySet()) {
		// System.out.println(key + " " + sortPainter.get(key));
		// }

		j = 0;
		for (Integer key : sortPainter.keySet()) {
			String[] temp = sortPainter.get(key).split(_DIS);

			if (temp.length > 1) {
				reTemp = new ArrayList<>();
				for (k = 0; k < temp.length; k++) {
					reTemp.add(temp[k]);
				}
				Collections.sort(reTemp);

				for (k = 0; k < temp.length; k++) {
					result[j] = reTemp.get(k);
					j++;
					if (j == result.length)
						break;
				}

			} else {
				result[j] = temp[0];
				j++;
				if (j == result.length)
					break;
			}
		}

		if (j < result.length) {

			String[] reversal = new String[sortNotPainter.size()];
			String[] Done = new String[sortNotPainter.size()];
			k = 0;
			for (Integer key : sortNotPainter.keySet()) {
				reversal[k] = sortNotPainter.get(key);
				k++;
			}

			k = 0;
			for (i = sortNotPainter.size() - 1; i >= 0; i--) {
				Done[k] = reversal[i];
				k++;
			}

			for (k = 0; k < sortNotPainter.size(); k++) {
				String[] temp = Done[k].split(_DIS);
				if (temp.length > 1) {
					reTemp = new ArrayList<>();
					for (i = 0; i < temp.length; i++) {
						reTemp.add(temp[i]);
					}
					Collections.sort(reTemp);
					for (i = 0; i < temp.length; i++) {
						result[j] = reTemp.get(i);
						j++;
						if (j == result.length)
							break;
					}

				} else {
					result[j] = temp[0];
					j++;
					if (j == result.length)
						break;
				}
			}
		}

		for (i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
		return result;

	}

	public static void main(String[] args) {
		/**
		 * test 1
		 * 
		 */
		System.out.println("###########  test 1  ###############");
		String[] pros = { "Michael", "Mary", "Ann", "Nick", "Dan", "Mark" };
		int[] distances = { 12, 10, 19, 15, 5, 20 }, travelPreferences = { 12, 8, 25, 10, 3, 10 };
		requestMatching(pros, distances, travelPreferences);
		/**
		 * test 2
		 */
		System.out.println("###########  test 2  ###############");
		String[] pros1 = { "Ann", "Michael", "Mary" };
		int[] distances1 = { 5, 5, 5 }, travelPreferences1 = { 3, 10, 7 };
		requestMatching(pros1, distances1, travelPreferences1);
		/**
		 * test 3
		 */
		System.out.println("###########  test 3  ###############");
		String[] pros2 = { "Ann", "Anna", "Hanna" };
		int[] distances2 = { 5, 3, 2 }, travelPreferences2 = { 5, 4, 2 };
		requestMatching(pros2, distances2, travelPreferences2);
	}
}
