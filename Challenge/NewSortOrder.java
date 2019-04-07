package Challenge;

import java.util.ArrayList;
import java.util.TreeMap;

public class NewSortOrder {

	static String[] newSortOrder(String[] docIDs) {

		TreeMap<String, Integer> mapChar = new TreeMap<>();
		TreeMap<String, Integer> mapNum = new TreeMap<>();
		ArrayList<String> result = new ArrayList<>();

		int i, j;
		boolean flag;
		for (i = 0; i < docIDs.length; i++) {
			flag = true;
			if (docIDs[i].length() == 0
				 ||	docIDs[i].charAt(0) != 'X' && docIDs[i].charAt(docIDs[i].length() - 1) == 'X'
				 || docIDs[i].charAt(0) == 'X' && docIDs[i].charAt(docIDs[i].length() - 1) != 'X')
				continue;
			for (j = 1; j < docIDs[i].length() - 1; j++) {
				if (docIDs[i].charAt(j) == 'X') {
					flag = false;
					break;
				}
			}
			if (!flag)
				continue;

			String tmp = "";
			if (docIDs[i].charAt(0) == 'X' && docIDs[i].charAt(docIDs[i].length() - 1) == 'X') {
				for (j = 1; j < docIDs[i].length() - 1; j++) {
					if (!Character.isDigit(docIDs[i].charAt(j))) {
						tmp += docIDs[i].charAt(j);
					}
				}
				if (tmp.equals(""))
					continue;
				docIDs[i] = tmp;
			}

			// la so
			if (Character.isDigit(docIDs[i].charAt(0))) {
				if (mapNum.get(docIDs[i]) == null)
					mapNum.put(docIDs[i], 1);
				else
					mapNum.put(docIDs[i], mapNum.get(docIDs[i]) + 1);
			} else {
				if (mapChar.get(docIDs[i]) == null)
					mapChar.put(docIDs[i], 1);
				else
					mapChar.put(docIDs[i], mapChar.get(docIDs[i]) + 1);
			}

		}
		// la ki tu
		for (String key : mapChar.keySet()) {
			if (mapChar.get(key) > 1) {
				int t = mapChar.get(key);
				while (t >= 1) {
					result.add(key);
					t--;
				}
			} else
				result.add(key);
		}
		// la so
		ArrayList<String> result1 = new ArrayList<>();
		for (String key : mapNum.keySet()) {
			if (mapNum.get(key) > 1) {
				int t = mapNum.get(key);
				while (t >= 1) {
					result1.add(key);
					t--;
				}
			} else
				result1.add(key);
		}
		
		for (i = 0; i < result1.size(); i++)
			System.out.print(result1.get(i) + " ");
		
		System.out.println("\n");
		for (i = result1.size() - 1; i >= 0; i--) {
			j = i - 1;
			if (i > 0) {
				while (result1.get(i).charAt(0) == result1.get(j).charAt(0)) {
					j--;
					if(j < 0) {
						break;
					}
				}
			}
			
			if (j != i - 1) {
				if(j < 0) j = -1;
				for (int k = j+1; k <= i; k++)
					result.add(result1.get(k));
				i = j+1;
			} else
				result.add(result1.get(i));
		}

		for (i = 0; i < result.size(); i++)
			System.out.print(result.get(i) + " ");

		return result.toArray(new String[result.size()]);
	}

	public static void main(String[] args) {
		System.out.println("################### test 1 ######################");
		String[] docIDs = { "bbb", "aXa", "123", "abc", "5aa", "Xa1x2x3X" };
		newSortOrder(docIDs);

		System.out.println("\n\n################## test 2 #######################");
		String[] docIDs1 = { "X0X", "", "abcX", "Xab", "XXXXa", "aaaxt" };
		newSortOrder(docIDs1);

		System.out.println("\n\n################## test 3 ######################");
		String[] docIDs2 = { "80", "aaa", "aaa", "xxxx", "93", "99", "92", "xxx", "XXX", "XaX", "00","010","040","050","060","070", "0x", "100x", "9001" };
		newSortOrder(docIDs2);
	}

}
