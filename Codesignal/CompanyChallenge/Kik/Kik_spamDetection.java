package Codesignal.CompanyChallenge.Kik;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class Kik_spamDetection {

	static int gcd_Euclid(int a, int b) {
		return (b == 0) ? a : gcd_Euclid(b, a % b);
	}

	static String[] splitWord(String words) {
		ArrayList<String> wordSplit = new ArrayList<>();
		String word = "";
		for (int i = 0; i < words.length(); i++) {
			if ((words.charAt(i) >= 'a' && words.charAt(i) <= 'z')
					|| (words.charAt(i) >= 'A' && words.charAt(i) <= 'Z')) {
				word += words.charAt(i);
			} else if (!word.equals("")) {
				wordSplit.add(word);
				word = "";
				
			}
		}
		if (!word.equals("")) {
			wordSplit.add(word);
		}

		return wordSplit.toArray(new String[wordSplit.size()]);
	}

	static String[] spamDetection(String[][] messages, String[] spamSignals) {

		String[] re = new String[4];
		int i, j, title1 = 0, title4 = 0, countTitle3 = -1, msgl = messages.length;
		String strtitle3 = "", temp;
		HashMap<String, Integer> title3 = new HashMap<>();
		TreeSet<String> strtitle4 = new TreeSet<>();

		TreeSet<Integer> ordertitle2 = new TreeSet<>();
		HashMap<String, ArrayList<String>> ctidtitle2 = new HashMap<>();

		for (i = 0; i < msgl; i++) {
			// title1
			String[] sp = splitWord(messages[i][0]);
			//String[] sp = messages[i][0].replace("[^a-zA-Z]", "").split("\\s+");
			if (sp.length < 5)
				title1++;

			// title2
			ArrayList<String> tmp;
			if (ctidtitle2.get(messages[i][1]) == null) {
				tmp = new ArrayList<>();
			} else {
				tmp = new ArrayList<>(ctidtitle2.get(messages[i][1]));
			}
			tmp.add(messages[i][0]);
			ctidtitle2.put(messages[i][1], tmp);

			// title3
			if (title3.get(messages[i][0]) == null)
				title3.put(messages[i][0], 1);
			else
				title3.put(messages[i][0], title3.get(messages[i][0]) + 1);

			if (countTitle3 < title3.get(messages[i][0])) {
				countTitle3 = title3.get(messages[i][0]);
				strtitle3 = messages[i][0];
			}

			// title4
			List<String> splitMessage = Arrays.asList(splitWord(messages[i][0].toLowerCase()));

			for (j = 0; j < spamSignals.length; j++) {
				if (splitMessage.contains(spamSignals[j])) {
					title4++;
					strtitle4.add(spamSignals[j]);
					break;
				}
			}
		}

		// check title 1
		if ((float) title1 <= 0.9 * (float) msgl) {
			re[0] = "passed";
		} else {
			if (title1 == msgl)
				re[0] = "failed: 1/1";
			else
				re[0] = "failed: " + (title1 / gcd_Euclid(title1, msgl)) + "/" + (msgl / gcd_Euclid(title1, msgl));
		}

		// check title 2
		temp = "";
		for (String key : ctidtitle2.keySet()) {

			double countMaxMessage = -1.00;
			ArrayList<String> listMessage = ctidtitle2.get(key);
			HashMap<String, Integer> countMessageDup = new HashMap<>();

			for (String msg : listMessage) {
				if (countMessageDup.containsKey(msg)) {
					countMessageDup.put(msg, countMessageDup.get(msg) + 1);
					if (countMaxMessage < countMessageDup.get(msg)) {
						countMaxMessage = countMessageDup.get(msg);
					}
				} else {
					countMessageDup.put(msg, 1);
				}
			}

			if (2 * countMaxMessage > listMessage.size()) {
				ordertitle2.add(Integer.parseInt(key));
			}
		}
		if (ordertitle2.size() > 0) {
			temp = "failed:";
			for (Integer id : ordertitle2) {
				temp += " " + id;
			}
			re[1] = temp;
		} else {
			re[1] = "passed";
		}

		// check title 3
		if (msgl > 1 && 2 * countTitle3 > msgl) {
			re[2] = "failed: " + strtitle3;
		} else {
			re[2] = "passed";
		}

		// check title 4
		if ((float) 2 * title4 <= msgl) {
			re[3] = "passed";
		} else {
			temp = "failed:";
			for (String val : strtitle4) {
				temp += " " + val;
			}
			re[3] = temp;
		}

		for (i = 0; i < re.length; i++)
			System.out.println(re[i]);

		return re;
	}

	public static void main(String[] args) {

		// test
		System.out.println("\n############Test1: ###############");
		String[][] messages = { { "Sale today!", "2837273" }, { "Unique offer!", "3873827" },
				{ "Only today and only for you!", "2837273" }, { "Sale today!", "2837273" },
				{ "Unique offer!", "3873827" } };
		String[] spamSignals = { "sale", "discount", "offer" };

		spamDetection(messages, spamSignals);

		System.out.println("\n############Test2: ###############");
		String[][] messages1 = { { "Check Codesignal out", "7284736" }, { "Check Codesignal out", "7462832" },
				{ "Check Codesignal out", "3625374" }, { "Check Codesignal out", "7264762" } };
		String[] spamSignals1 = { "sale", "discount", "offer" };
		spamDetection(messages1, spamSignals1);

		System.out.println("\n############Test3: ###############");
		String[][] messages2 = { { "a", "1" } };
		String[] spamSignals2 = { "b" };
		spamDetection(messages2, spamSignals2);

		// test
		System.out.println("\n############Test custom: ###############");
		String[][] messages3 = { { "Sale today!", "2837273" }, 
				{ "Unique offer!", "3873827" },
				{ "Unique offer!", "2837273" }, 
				{ "Unique discount offer!", "2837273" },
				{ "Unique  discount offer!", "2837273" }, 
				{ "Unique discount offer!", "2837273" },
				{ "Unique discount offer!", "2837273" }, 
				{ "Unique offer!", "2837273" }, 
				{ "Unique offer!", "2837273" },
				{ "Unique offer!", "2837273" }, 
				{ "Unique offer!", "2837273" }, 
				{ "Unique offer!", "2837273" },
				{ "Unique offer!", "2837273" }, 
				{ "Unique offer!", "2837273" }, 
				{ "Unique offer!", "2837273" },
				{ "Unique offer!", "2837273" }, 
				{ "Unique offer!", "2837273" }, 
				{ "Unique offer!", "2837273" },
				{ "Unique offer!", "2837273" }, 
				{ "Unique offer!", "2837273" }, 
				{ "Unique offer discount!", "2837273" },
				{ "Unique offer discount!", "2837273" }, 
				{ "Unique offer!", "2837273" }, 
				{ "Unique offer!", "2837273" },
				{ "Only  discount today and only for you!", "2837273" }, 
				{ "Sale discount today!", "2837273" },
				{ "Sale discount  today!", "2837273" }, 
				{ "Sale today discount !", "2837273" },
				{ "Unique offer!", "3873827" } };
		String[] spamSignals3 = { "sale", "discount", "offer" };

		spamDetection(messages3, spamSignals3);

		// String msg = " A AAA VAA DA P ";
		// String [] a = msg.split("\\s+");
		// for(int i = 0; i < a.length; i++)
		// System.out.println("aaaa " + a[i]);

	}

}
