package CompanyChallenge.Thumbtack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Thumbtack_SpamClusterization1 {

	static String[] splitWord(String words) {
		ArrayList<String> wordSplit = new ArrayList<>();
		String word = "";
		for (int i = 0; i < words.length(); i++) {
			if ((words.charAt(i) >= 'a' && words.charAt(i) <= 'z')
					|| (words.charAt(i) >= 'A' && words.charAt(i) <= 'Z')) {
				word += words.charAt(i);
			} else {
				if (!word.equals("")) {
					wordSplit.add(word);
				}
				word = "";
			}
		}

		if (!word.equals("")) {
			wordSplit.add(word);
		}

		return wordSplit.toArray(new String[wordSplit.size()]);
	}

	static int[][] spamClusterization(String[] requests, int[] ids, double threshold) {
		int i, j, k;
		String[][] normalizeRequest = new String[requests.length][];
		Set<String> preventDup;
		ArrayList<ArrayList<Integer>> resultTmp = new ArrayList<>();
		/**
		 * create a array string to convert requests to words not duplicate
		 */
		for (i = 0; i < requests.length; i++) {
			String[] tmp = splitWord(requests[i].toLowerCase());
			preventDup = new HashSet<>();
			for (String word : tmp) {
				preventDup.add(word);
			}
			normalizeRequest[i] = new String[preventDup.size()];
			j = 0;
			for (String word : preventDup) {
				normalizeRequest[i][j++] = word;
			}
		}
		/**
		 * caculate jaccar index and check spam
		 */
		for (i = 0; i < normalizeRequest.length - 1; i++) {
			HashMap<String, Boolean> preventDuplicateWord = new HashMap<>(), preventDuplicateWordTmp;

			for (j = 0; j < normalizeRequest[i].length; j++) {
				preventDuplicateWord.put(normalizeRequest[i][j], true);
			}

			for (j = i + 1; j < normalizeRequest.length; j++) {
				preventDuplicateWordTmp = new HashMap<>(preventDuplicateWord);
				int countDup = 0;
				// caculate A n B
				for (k = 0; k < normalizeRequest[j].length; k++) {
					if (preventDuplicateWordTmp.containsKey(normalizeRequest[j][k])
							&& preventDuplicateWordTmp.get(normalizeRequest[j][k])) {
						countDup++;
						preventDuplicateWordTmp.put(normalizeRequest[j][k], false);
					}
				}

				int totalSize = preventDuplicateWord.size() + normalizeRequest[j].length - countDup;

				// grouping cluster
				double rate = (double) countDup / totalSize;
				if (rate >= threshold) {
				//	System.out.println(ids[i] + "  " + ids[j]);
					boolean isExisted = false;
					for (k = 0; k < resultTmp.size(); k++) {
						if (resultTmp.get(k).contains(ids[i]) || resultTmp.get(k).contains(ids[j])) {
							if (resultTmp.get(k).contains(ids[i]) && !resultTmp.get(k).contains(ids[j])) {
								resultTmp.get(k).add(ids[j]);
							}
							if (resultTmp.get(k).contains(ids[j]) && !resultTmp.get(k).contains(ids[i])) {
								resultTmp.get(k).add(ids[i]);
							}
							isExisted = true;
							break;
						}
					}

					if (!isExisted) {
						ArrayList<Integer> tmp = new ArrayList<>();
						tmp.add(ids[i]);
						tmp.add(ids[j]);
						resultTmp.add(tmp);
					}
				}
			}

		}

		// sort output
		int[][] result = new int[resultTmp.size()][];
		i = 0;

		for (ArrayList<Integer> key : resultTmp) {
			Collections.sort(key);
			result[i++] = key.stream().mapToInt(Integer::intValue).toArray();
		}

		Arrays.sort(result, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});

		for (i = 0; i < result.length; i++) {
			for (j = 0; j < result[i].length; j++) {
				System.out.print(result[i][j] + "  ");
			}
			System.out.println();
		}

		return result;
	}

	public static void main(String[] args) {
		System.out.println(" ############## Test 1 ##############");
		String[] requests = { "I need a             new window.", "I really, really want to replace my window!",
				"Replace my window.", "I want a new window.",
				"I want a new carpet, I want a new carpet, I want a new carpet.", "Replace my carpet" };
		int[] ids = { 374, 2845, 83, 1848, 1837, 1500 };
		double threshold = 0.1;
		spamClusterization(requests, ids, threshold);

		System.out.println(" ############## Test 5 ##############");
		String[] requests1 = { "I need a new window", "I really, really want to replace my window",
				"Replace mY !!.windoW........", "I want a new window?",
				"I want a new carpet, i want a new carpet, I WANT A NEW CARPET", "RePlAcE!!! !!!My!!! !!!CaRpEt!!!!" };
		int[] ids1 = { 374, 2845, 83, 1848, 1837, 1500 };
		double threshold1 = 0.5;
		spamClusterization(requests1, ids1, threshold1);

		System.out.println(" ############## Test 2 ##############");
		String[] requests2 = { "A", "C", "A C" };
		int[] ids2 = { 374, 2845, 83 };
		double threshold2 = 0.5;
		spamClusterization(requests2, ids2, threshold2);

		System.out.println(" ############## Test 4 ##############");
		String[] requests3 = { "I need a new window" };
		int[] ids3 = { 239 };
		double threshold3 = 0;
		spamClusterization(requests3, ids3, threshold3);

	}

}
