package CompanyChallenge;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

public class Wizeline_Roadmap {

	static String[][] roadmap(String[][] tasks, String[][] queries) {

		String[][] result = new String[queries.length][];
		int i = 0, j = 0, k = 0;
		HashMap<String, String> sortUser = new HashMap<>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		for (i = 0; i < tasks.length; i++) {
			try {
				Date d_start = sdf.parse(tasks[i][1]);
				Date d_end = sdf.parse(tasks[i][2]);
				for (j = 3; j < tasks[i].length; j++) {
					String value = Long.toString(d_start.getTime()) + " " + Long.toString(d_end.getTime());
					if (sortUser.get(tasks[i][j]) == null) {
						sortUser.put(tasks[i][j], tasks[i][0] + "#" + value);
					} else {
						sortUser.put(tasks[i][j], sortUser.get(tasks[i][j]) + "#" + tasks[i][0] + "#" + value);
					}
				}

			} catch (ParseException e) {
				System.out.println(e.getMessage());
			}
		}

		// debug
		for (String key : sortUser.keySet()) {
			System.out.println(key + " " + sortUser.get(key));
		}

		for (i = 0; i < queries.length; i++) {
			String[] temp = sortUser.get(queries[i][0]).split("#");
			try {
				Date d = sdf.parse(queries[i][1]);
				if (temp.length > 2) {
					TreeMap<String, String> orderEnDate = new TreeMap<>();
					
					for (j = 0; j < temp.length; j++) {
						if (j % 2 != 0) {
							String[] temp1 = temp[j].split(" ");
							long startDate = Long.parseLong(temp1[0]), endDate = Long.parseLong(temp1[1]);
							if (d.getTime() <= endDate && d.getTime() >= startDate) {
								orderEnDate.put(temp[j - 1], temp1[1]);
							}
						}
					}
					result[k] = new String[orderEnDate.size()];
					int l = 0;
					TreeMap<String, String> duplicateDate = new TreeMap<>();

					for (String key : orderEnDate.keySet()) {
						if (duplicateDate.get(orderEnDate.get(key)) == null) {
							duplicateDate.put(orderEnDate.get(key), key);
						} else {
							duplicateDate.put(orderEnDate.get(key),
									duplicateDate.get(orderEnDate.get(key)) + "#" + key);
						}
					}
				
					for (String key : duplicateDate.keySet()) {
						String[] temp1 = duplicateDate.get(key).split("#");
						if (temp1.length > 0) {
							for (j = 0; j < temp1.length; j++) {
								result[k][l] = temp1[j];
								l++;
							}
						} else {
							result[k][l] = temp1[j];
							l++;
						}
					}

					k++;

				} else {
					String[] temp1 = temp[1].split(" ");
					long startDate = Long.parseLong(temp1[0]), endDate = Long.parseLong(temp1[1]);

					if (d.getTime() <= endDate && d.getTime() >= startDate) {
						result[k] = new String[1];
						result[k][0] = temp[0];
					}else
						result[k] = new String[0];
					k++;
				}
			} catch (ParseException e) {
				System.out.println(e.getMessage());
			}
		}

		for (i = 0; i < result.length; i++) {
			for (j = 0; j < result[i].length; j++) {
				System.out.print(result[i][j] + "   ");
			}
			System.out.println();
		}

		return result;
	}

	public static void main(String[] args) {
		// /**
		// * test 1
		// */
		// System.out.println("#########################################");
		// String[][] tasks = { { "A", "2017-02-01", "2017-03-01", "Sam", "Evan", "Troy"
		// },
		// { "B", "2017-01-16", "2017-02-15", "Troy" }, { "C", "2017-02-13",
		// "2017-03-13", "Sam", "Evan" } },
		// queries = { { "Evan", "2017-03-10" }, { "Troy", "2017-02-04" } };
		// roadmap(tasks, queries);
		// /**
		// * Test 2
		// */
		// System.out.println("#########################################");
		// String[][] tasks1 = { { "RXGWB", "2017-10-10", "2017-12-09", "Kyle" },
		// { "QOEHU", "2017-08-25", "2017-12-11", "Corey", "Kai", "Kaleb", "Reuben" },
		// { "HRCPX", "2017-04-04", "2017-07-21", "Corey", "Jenson", "Kyle" },
		// { "SQFYX", "2017-07-07", "2017-12-02", "Reuben", "Kaleb", "Kai", "Kyle" },
		// { "BIDVM", "2017-04-20", "2017-12-08", "Kaleb" } },
		// queries1 = { { "Reuben", "2017-06-09" }, { "Jenson", "2017-04-13" }, {
		// "Corey", "2017-12-01" },
		// { "Jenson", "2017-05-23" }, { "Corey", "2017-08-19" } };
		// roadmap(tasks1, queries1);

		// System.out.println("#########################################");
		// String[][] tasks2 = { { "LNWBN", "2017-08-13", "2017-12-24", "Corey", "Kyle",
		// "Kaleb", "Reuben" },
		// { "NSXEN", "2017-08-20", "2017-09-18", "Kai" },
		// { "DNMDC", "2017-06-19", "2017-08-07", "Kaleb", "Kai", "Kyle", "Reuben" },
		// { "UYWEQ", "2017-04-23", "2017-07-18", "Corey", "Kyle", "Reuben", "Kai" },
		// { "LIVNH", "2017-11-01", "2017-12-24", "Kaleb", "Kai" } },
		// queries2 = { { "Corey", "2017-10-21" }, { "Reuben", "2017-03-16" }, {
		// "Kaleb", "2017-11-22" },
		// { "Kaleb", "2017-03-22" }, { "Reuben", "2017-10-06" } };
		// roadmap(tasks2, queries2);

		System.out.println("#########################################");
		String[][] tasks6 = { { "BWTYQ", "2017-09-07", "2017-09-09", "Kaleb" },
				{ "IVNTU", "2017-06-15", "2017-09-07", "Vincenzo", "Kai", "Landyn", "Jenson" },
				{ "YZNVZ", "2017-06-06", "2017-06-20", "Corey", "Reuben", "Kyle", "Daxton" },
				{ "UKZGD", "2017-09-16", "2017-09-19", "Kai", "Kyle", "Jenson", "Jamal" },
				{ "ZLYNM", "2017-08-24", "2017-10-01", "Kyle", "Kai", "Jamal" },
				{ "EXLAG", "2017-06-05", "2017-09-19", "Kaleb", "Kyle", "Kai" },
				{ "ANBUQ", "2017-05-16", "2017-09-08", "Kai" },
				{ "MVTMI", "2017-07-16", "2017-08-24", "Kyle", "Landyn" },
				{ "ITXJC", "2017-09-05", "2017-09-06", "Reuben", "Corey", "Daxton" },
				{ "KLFDO", "2017-05-25", "2017-09-15", "Corey", "Jenson", "Kai" } },
				queries6 = { { "Kaleb", "2017-05-01" }, { "Kyle", "2017-09-11" }, { "Kai", "2017-10-22" },
						{ "Jenson", "2017-08-17" }, { "Jenson", "2017-08-06" }, { "Reuben", "2017-06-25" },
						{ "Jenson", "2017-08-10" }, { "Kaleb", "2017-06-03" }, { "Jenson", "2017-06-10" },
						{ "Vincenzo", "2017-06-24" }, { "Vincenzo", "2017-06-14" }, { "Kai", "2017-08-01" },
						{ "Kaleb", "2017-09-14" }, { "Landyn", "2017-07-24" }, { "Landyn", "2017-05-03" },
						{ "Jenson", "2017-10-13" }, { "Kyle", "2017-07-07" }, { "Corey", "2017-07-08" },
						{ "Landyn", "2017-05-04" }, { "Corey", "2017-09-06" } };
		roadmap(tasks6, queries6);
	}

}
