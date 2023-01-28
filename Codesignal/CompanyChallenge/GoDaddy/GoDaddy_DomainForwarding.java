package Codesignal.CompanyChallenge.GoDaddy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

/**
 * Domain name forwarding lets GoDaddy domain owners automatically redirect
 * their site visitors to a different site URL. Sometimes the visitors have to
 * go through multiple redirects before ending up on the correct site.
 * 
 * Using the DNS Manager, GoDaddy customers can view redirects in a simple
 * visual format. One handy feature is the ability to group the domains by the
 * final website they redirect to. Your task is to implement this feature.
 * 
 * For the given redirects list, organize its domains into groups where for a
 * specific group each domain eventually redirects visitors to the same website.
 * For
 * 
 * redirects = [["godaddy.net", "godaddy.com"], ["godaddy.org",
 * "godaddycares.com"], ["godady.com", "godaddy.com"], ["godaddy.ne",
 * "godaddy.net"]] the output should be
 * 
 * domainForwarding(redirects) = [["godaddy.com", "godaddy.ne", "godaddy.net",
 * "godady.com"], ["godaddy.org", "godaddycares.com"]] In the first group,
 * "godaddy.ne" redirects to "godaddy.net", which in turn redirects to
 * "godaddy.com". "godady.com" redirects visitors to "godaddy.com" as well. In
 * the second group, "godaddy.org" redirects visitors to "godaddycares.com".
 * 
 * Note, that domains in each group are sorted lexicographically and groups
 * themselves are sorted lexicographically by the domain they redirect to. So in
 * the example, the first group goes before the second because "godaddy.com" is
 * lexicographically smaller than "godaddycares.com".
 * 
 * Input/Output
 * 
 * [execution time limit] 4 seconds (js)
 * 
 * [input] array.array.string redirects
 * 
 * Each of redirects[i] consists of two domains. The second element is the
 * domain to which the first element redirects. Each domain is a string that may
 * consist of lowercase English letters, hyphens ('-') and full stops ('.'). It
 * is guaranteed that domain redirects do not contain cycles, i.e. it is
 * impossible to get back to the current site after any number of redirects. It
 * is also guaranteed that each domain redirects to no more than one another
 * domain, i.e. for each i ≠ j redirects[i][0] ≠ redirects[j][0].
 * 
 * Guaranteed constraints: 1 ≤ redirects.length ≤ 15, redirects[i].length == 2,
 * 1 ≤ redirects[i][j].length ≤ 25.
 * 
 * [output] array.array.string
 * 
 * Return the array of domain groups, such that each domain from redirects
 * belongs to one of the group, and domains from one group redirect visitors to
 * the same website. Arrange the domains in each group in lexicographical order,
 * and sort the groups by the domains they redirect to (also lexicographically).
 * 
 * @author PhamVanLinh
 *
 */

public class GoDaddy_DomainForwarding {
	/**
	 * 
	 * @param set
	 * @return
	 */
	public static <T> List<T> convertSetToList(Set<T> set) {
		List<T> list = new ArrayList<>();
		for (T t : set)
			list.add(t);
		return list;
	}

	/**
	 * 
	 * @param redirects
	 * @return
	 */
	public static String[][] domainForwarding(String[][] redirects) {

		Map<String, String> rebuildListRedirect = new HashMap<String, String>();
		Set<String> uniqueRootTree = new TreeSet<String>();
		for (int i = 0; i < redirects.length; i++) {
			if (!rebuildListRedirect.containsKey(redirects[i][1])) {
				rebuildListRedirect.put(redirects[i][1], redirects[i][0]);
			} else {
				rebuildListRedirect.put(redirects[i][1],
						rebuildListRedirect.get(redirects[i][1]) + "--" + redirects[i][0]);
			}

			String root = redirects[i][1];
			int j = 0;
			for (; j < redirects.length;) {
				if (root.equals(redirects[j][0])) {
					root = redirects[j][1];
					j = 0;
				} else
					j++;
			}
			uniqueRootTree.add(root);
		}

		// System.out.println("List root");
		// for (String key : uniqueRootTree) {
		// System.out.print(key + " ");
		// }
		//
		// System.out.println("\nBuild tree");
		// for (String key : rebuildListRedirect.keySet()) {
		// System.out.println(key + " " + rebuildListRedirect.get(key));
		// }

		List<List<String>> listResponse = new ArrayList<List<String>>();
		for (String key : uniqueRootTree) {
			Stack<String> stackDfs = new Stack<>();
			stackDfs.add(key);
			TreeSet<String> listNode = new TreeSet<String>();

			while (stackDfs.size() > 0) {
				String k = stackDfs.pop();
				listNode.add(k);
				String[] children = rebuildListRedirect.containsKey(k) ? rebuildListRedirect.get(k).split("--") : null;
				if (children != null) {
					for (String child : children)
						stackDfs.add(child);
				}
			}
			listResponse.add(convertSetToList(listNode));
		}

//		Collections.sort(listResponse, new Comparator<List<String>>() {
//			@Override
//			public int compare(List<String> list1, List<String> list2) {
//				return list1.get(1).compareToIgnoreCase(list2.get(1));
//			}
//		});

		String[][] responseArr = new String[listResponse.size()][];

		int index = 0;
	//	System.out.println("Out ");
		for (List<String> list : listResponse) {
			responseArr[index] = new String[list.size()];
			int pos = 0;
			for (String element : list) {
				responseArr[index][pos++] = element;
				System.out.print(element + " ");
			}
			System.out.println();
			index++;
		}

		return responseArr;
	}

	public static void main(String[] args) {
		System.out.println("############### test 1 ##############");
		/**
		 * [["godaddy.com", "godaddy.ne", "godaddy.net", "godady.com"], ["godaddy.org",
		 * "godaddycares.com"]]
		 */
		String[][] redirects = { { "godaddy.net", "godaddy.com" }, 
				                 { "godaddy.org", "godaddycares.com" },
				                 { "godady.com", "godaddy.com" }, 
				                 { "godaddy.ne", "godaddy.net" } };
		domainForwarding(redirects);

		System.out.println("############### test 2 ##############");
		/**
		 * [["a-b.c","a.c","aa-b.c","bb-b.c","cc-b.c","d-cc-b.c","e-cc-b.c"]]
		 */
		String[][] redirects1 = { { "a-b.c", "a.c" }, { "aa-b.c", "a-b.c" }, { "bb-b.c", "a-b.c" },
				{ "cc-b.c", "a-b.c" }, { "d-cc-b.c", "bb-b.c" }, { "e-cc-b.c", "bb-b.c" } };
		domainForwarding(redirects1);

		System.out.println("############### test 3 ##############");
		/**
		 * [["a","b"]]
		 */
		String[][] redirects2 = { { "a", "b" } };
		domainForwarding(redirects2);

		System.out.println("############### test 4 ##############");
		/**
		 * [["b","f"], ["c","d"]]
		 */
		String[][] redirects3 = { { "c", "d" }, { "f", "b" } };
		domainForwarding(redirects3);

		System.out.println("############### test 5 ##############");
		/**
		 * [["b","c"], ["a","z"]]
		 */
		String[][] redirects4 = { { "a", "z" }, { "c", "b" } };
		domainForwarding(redirects4);
		
		
		System.out.println("############ Test custom ###############");
		String[][] redirectsC = { { "godaddy.net", "godaddy.com" }, 
				                 { "godaddy.org", "godaddycares.com" },
				                 { "godady.com", "godaddy.com" }, 
				                 { "godaddy.ne", "godaddy.net" },
				                 { "godaddy1.ne", "godaddy.net" },
				                 { "godaddy12.ne", "godaddy.ne" },
				                 { "godaddy123.ne", "godaddy.ne" },
				                 { "godaddy1234.ne", "godaddy.ne" },
				                 { "godaddy1.org", "godaddy.org" },
				                 { "godaddy12.org", "godaddy.org" }
		};
		domainForwarding(redirectsC);

	}
}
