package Codesignal.CompanyChallenge.Wizeline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class beLong {

}

public class Wizeline_countAPI {

	static String[] countAPI(String[] calls) {

		HashMap<String, Integer> pro = new HashMap<>();
		HashMap<String, Integer> subbe = new HashMap<>();
		HashMap<String, Integer> metbe = new HashMap<>();
		ArrayList<String> order = new ArrayList<>();
		ArrayList<String> order1 = new ArrayList<>();
		ArrayList<String> order2 = new ArrayList<>();

		int i = 0;
		for (i = 0; i < calls.length; i++) {
			String[] temp = calls[i].split("/");
			if (pro.get(temp[1]) == null) {
				pro.put(temp[1], 0);
				order.add(temp[1]);
			} 
			pro.put(temp[1], pro.get(temp[1]) + 1);

			if (subbe.get(temp[2] + "-" + temp[1]) == null) {
				subbe.put(temp[2] + "-" + temp[1], 0);
				order1.add(temp[2] + "-" + temp[1]);
			} 
			subbe.put(temp[2] + "-" + temp[1], subbe.get(temp[2] + "-" + temp[1]) + 1);

			if (metbe.get(temp[3] + "-" + temp[2] + "-" + temp[1]) == null) {
				metbe.put(temp[3] + "-" + temp[2] + "-" + temp[1], 0);
				order2.add(temp[3] + "-" + temp[2] + "-" + temp[1]);
			}
			metbe.put(temp[3] + "-" + temp[2] + "-" + temp[1], 
					  metbe.get(temp[3] + "-" + temp[2] + "-" + temp[1]) + 1);
			
		}

		List<String> result = new ArrayList<>();
		for (String s : order) {
			result.add("--" + s + " (" + pro.get(s) + ")");
			for (String s1 : order1) {
				String[] tmp = s1.split("-");
				if (tmp[1].equals(s)) {
					result.add("----" + tmp[0] + " (" + subbe.get(s1) + ")");
					for (String s2 : order2) {
						String[] tmp1 = s2.split("-");
						if (tmp1[2].equals(s) && tmp1[1].equals(tmp[0])) {
							result.add("------" + tmp1[0] + " (" + metbe.get(s2) + ")");
							metbe.remove(s2);
						}
					}
					subbe.remove(s1);
				}
			}
		}

		for (i = 0; i < result.size(); i++) {
			System.out.println(result.get(i));
		}

		String[] re = result.toArray(new String[result.size()]);

		return re;
	}

	public static void main(String[] args) {
		// test
		// String[] calls = { "/project1/subproject1/method1",
		// "/project2/subproject1/method1",
		// "/project1/subproject1/method1", "/project1/subproject2/method1",
		// "/project1/subproject1/method2",
		// "/project1/subproject2/method1", "/project2/subproject1/method1",
		// "/project1/subproject2/method1" };
		//
		// countAPI(calls);

		String[] calls = { "/project/subproject1/methods", "/project/subproject1/method", "/project/subproject2/method",
				"/project/subproject3/method", "/project/subproject2/method", "/project/subproject4/method",
				"/project/subproject2/method", "/project/subproject4/method2", "/project/subproject4/method1",
				"/project/subproject1/methods", "/project/subproject4/method1", "/project/subproject2/method",
				"/project/subproject4/method", "/project/subproject2/method", "/project/subproject1/methods" };
		countAPI(calls);
	}

}
