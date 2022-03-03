package CompanyChallenge.Thumbtack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
/**
 * 
 * @author PhamVanLinh
 * 
 * hiá»ƒu sai Ä‘á»�
 *
 */
public class Thumbtack_SpamClusterization {

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
		HashMap<Double, String> clusterMap = new HashMap<>();

		// calculate jaccard index
		for (i = 0; i < requests.length - 1; i++) {
		//	String[] splitWords = requests[i].toLowerCase().replaceAll("[^a-zA-Z0-9 ]", "").split("\\s+");
			String[] splitWords = splitWord(requests[i].toLowerCase());
			HashMap<String, String> preventDuplicateWord = new HashMap<>(), preventDuplicateWordTmp;

			for (j = 0; j < splitWords.length; j++) {
				preventDuplicateWord.put(splitWords[j], "1");
			}

			for (j = i + 1; j < requests.length; j++) {
				preventDuplicateWordTmp = new HashMap<>(preventDuplicateWord);
				HashSet<String> preventDupWord = new HashSet<>();
				int dupWord = 0, totalWord = preventDuplicateWordTmp.size();
				//splitWords = requests[j].toLowerCase().replaceAll("[^a-zA-Z0-9 ]", "").split("\\s+");
				splitWords = splitWord(requests[j].toLowerCase());
				for (k = 0; k < splitWords.length; k++) {
					preventDupWord.add(splitWords[k]);

					if (preventDuplicateWordTmp.containsKey(splitWords[k])) {
						dupWord++;
						preventDuplicateWordTmp.remove(splitWords[k]);
					}
				}
				totalWord += preventDupWord.size() - dupWord;
				// grouping cluster
				double rate = (double) dupWord / (double)totalWord;
				if (rate >= threshold) {
					if (clusterMap.containsKey(rate)) {
						clusterMap.put(rate, clusterMap.get(rate) + "--" + i + "--" + j);
					} else {
						clusterMap.put(rate, i + "--" + j);
					}
				}
			}
		}

		// sort output
		int[][] result = new int[clusterMap.size()][];

		i = 0;
		for (Double key : clusterMap.keySet()) {
			String[] splitKey = clusterMap.get(key).split("--");
			HashSet<String> preventDupWord = new HashSet<>();
			for (k = 0; k < splitKey.length; k++)
				preventDupWord.add(splitKey[k]);
			
			result[i] = new int[preventDupWord.size()];
			splitKey = preventDupWord.toArray(new String[preventDupWord.size()]);
			for (k = 0; k < splitKey.length; k++) {
				result[i][k] = ids[Integer.parseInt(splitKey[k])];
			}
			Arrays.sort(result[i]);
			i++;
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
		double threshold = 0.5;
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
