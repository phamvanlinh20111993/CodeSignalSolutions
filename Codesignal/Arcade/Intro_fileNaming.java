package Codesignal.Arcade;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Intro_fileNaming {

	static final String _SPLIT = "##", _SPLIT1 = "--", _IFNULL = "NN";
	static Integer MAX = -1;

	static String creatNum(String num) {
		return "(" + num + ")";
	}

	static String splitNum(String content) {
		int i, j, ascIInum, tmp = 0;
		StringBuffer re = new StringBuffer("");

		for (i = 0; i < content.length();) {
			ascIInum = (int) content.charAt(i);
			if (ascIInum <= 57 && ascIInum >= 48) {
				re.append(content.charAt(i));
				j = i + 1;
				while ((int) content.charAt(j) <= 57 && (int) content.charAt(j) >= 48) {
					re.append(content.charAt(j));
					j++;
				}
				i = j;
				tmp++;
				re.append(_SPLIT1);
			}
			i++;
		}

		if (tmp > MAX)
			MAX = tmp;

		return re.toString().substring(0, re.length() - _SPLIT1.length());
	}

	static String[] fileNaming(String[] names) {

		String pattern = "(\\w+)((\\(\\d+\\))*)$";
		Pattern r = Pattern.compile(pattern);
		HashMap<String, String> containNum = new HashMap<String, String>();
		Integer i = new Integer(0), j = 0;
		/**
		 * state 1
		 */
		for (i = 0; i < names.length; i++) {
			Matcher m = r.matcher(names[i]);
			if (m.find()) {
				if (containNum.get(m.group(1)) == null) {
					if (!m.group(2).equals("")) {
						containNum.put(m.group(1), splitNum(m.group(2)));
					} else {
						containNum.put(m.group(1), _IFNULL);
					}
				} else {
					if (!m.group(2).equals("")) {
						containNum.put(m.group(1), containNum.get(m.group(1)) + _SPLIT + splitNum(m.group(2)));
					} else {
						containNum.put(m.group(1), containNum.get(m.group(1)) + _SPLIT + _IFNULL);
					}
				}
			}
		}

		String[] result = new String[names.length];
		String[][] splStringAndNum = new String[names.length][MAX + 2];

		for (String key : containNum.keySet()) {
			String[] temp = containNum.get(key).split(_SPLIT);
			if (temp.length > 0) {
				for (i = 0; i < temp.length; i++) {
					splStringAndNum[i][0] = key;
					if (!temp[i].equals(_IFNULL)) {
						String[] temp1 = temp[i].split(_SPLIT1);
						for (j = 0; j < temp1.length; j++) {
							splStringAndNum[i][j + 1] = temp1[j];
						}
					} else {
						splStringAndNum[i][1] = _IFNULL;
					}
				}
			}
		}

		/**
		 * state 2
		 */
		int initial, k;
		for (i = 0; i < names.length; i++) {
			for (k = 1; k < MAX+2; k++) {
				j = i;
				initial = 1;
				containNum.clear();
				HashMap<String, String> containNum1 = new HashMap<String, String>();
				
				//while (j + 1 < names.length && splStringAndNum[j][0].equals(splStringAndNum[j + 1][0])) {
				while (j < names.length ) {
					if (k == 1) {
						if (splStringAndNum[j][k + 1] != null)
							containNum.put(splStringAndNum[j][k + 1], splStringAndNum[j][k + 1]);

						if (containNum.get(splStringAndNum[j][k]) == null) {
							containNum.put(splStringAndNum[j][k], splStringAndNum[j][k]);
						} else {
							if (k == 1 && containNum.get(splStringAndNum[j][k]).equals("NN")) {
								while (containNum.get(Integer.toString(initial)) != null)
									initial++;
								containNum.put(Integer.toString(initial), Integer.toString(initial));
								splStringAndNum[j][k] = Integer.toString(initial);
							}
						}
					} else{
						if (splStringAndNum[j][k - 1] != null) {	
							if(containNum.get(splStringAndNum[j][k - 1]) == null) {
								containNum.put(splStringAndNum[j][k - 1], splStringAndNum[j][k - 1]);
							}else{
								if(splStringAndNum[j][k] == null) {
									while (containNum1.get(Integer.toString(initial)) != null)
										initial++;
									splStringAndNum[j][k] = Integer.toString(initial);
									containNum1.put(Integer.toString(initial), Integer.toString(initial));
								}else
									containNum1.put(splStringAndNum[j][k], splStringAndNum[j][k]);
							}
						}
					}
					j++;
				}
			}
			i = j;
		}

		for (i = 0; i < names.length; i++) {
			for (j = 0; j < MAX + 2; j++) {
				System.out.print(splStringAndNum[i][j] + "   ");
			}
			System.out.println();
		}

		return result;
	}

	public static void main(String[] args) {
		/**
		 * test 1
		 */
		// System.out.println("##############################################");
		// String[] names = { "doc", "doc", "image", "doc(1)", "doc(1)(2)" };
		// fileNaming(names);
		/**
		 * test 2
		 */
//		System.out.println("##############################################");
//		String[] names1 = { "dd", "dd(1)", "dd(2)", "dd", "dd(1)", "dd(1)(2)", "dd(1)(1)", "dd", "dd(1)", "dd(1)(1)" };
//		fileNaming(names1);

		/**
		 * test 3
		 */
		 System.out.println("##############################################");
		 String[] names2 = { "dd", "dd(1)", "dd", "dd(1)", "dd(1)(1)", "dd(1)(2)",
		 "dd(1)(1)(1)", "dd(1)", "dd(1)(3)" };
		 fileNaming(names2);
	}

}
