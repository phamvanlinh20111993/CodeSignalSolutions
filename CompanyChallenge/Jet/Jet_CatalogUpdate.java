package CompanyChallenge.Jet;

import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

public class Jet_CatalogUpdate {
	
	static final String _SPLIT = "###";
	static String[][] catalogUpdate(String[][] catalog, String[][] updates) {
		
		TreeMap<String, String> map = new TreeMap<>();
		int i, j;
		
		for(i = 0; i < catalog.length; i++) {
			StringBuffer tmp = new StringBuffer();
			for(j = 1; j < catalog[i].length; j++) {
				tmp.append(catalog[i][j]);
				if(j < catalog[i].length - 1)
					tmp.append(_SPLIT);
			}
			map.put(catalog[i][0], tmp.toString());
		}
		
		for(i = 0; i < updates.length; i++) {
			StringBuffer tmp = new StringBuffer();
			for(j = 1; j < updates[i].length; j++) {
				tmp.append(updates[i][j]);
				if(j < updates[i].length - 1)
					tmp.append(_SPLIT);
			}	
			if(map.get(updates[i][0]) == null){
				map.put(updates[i][0], tmp.toString());
			}else {
				map.put(updates[i][0], map.get(updates[i][0])+_SPLIT +tmp.toString());
			}
		}
		
		/**
		 * debug
		 */
//		for(String key : map.keySet()) {
//			System.out.println(key + "   " + map.get(key));
//		}
		
		String [][] result = new String[map.size()][];
		TreeSet<String> sorted = null;
		i = 0;
		for(String key : map.keySet()) {
			String[] tmp = map.get(key).split(_SPLIT);
			result[i] = new String[tmp.length+1];
			result[i][0] = key;
			sorted = new TreeSet<>();
			for(j = 0; j < tmp.length; j++) {
				sorted.add(tmp[j]);
			}
			Iterator<String> tmp1 = sorted.iterator();
			j = 0;
			while(tmp1.hasNext()) {
				result[i][j+1] = tmp1.next();
				j++;
			}		
			
			i++;
		}
		
		for(i = 0; i < result.length; i++) {
			for(j = 0; j < result[i].length; j++) {
				System.out.print(result[i][j] + "   ");
			}
			System.out.println();
		}
		
		return result;
	}

	public static void main(String[] args) {
		/**
		 * test 1
		 */
		String[][] catalog = { { "Books", "Classics", "Fiction" },
				{ "Electronics", "Cell Phones", "Computers", "Ultimate item" }, { "Grocery", "Beverages", "Snacks" },
				{ "Snacks", "Chocolate", "Sweets" }, { "root", "Books", "Electronics", "Grocery" } },

				updates = { { "Snacks", "Marmalade" }, { "Fiction", "Harry Potter" }, { "root", "T-shirts" },
						{ "T-shirts", "CodeSignal" } };
		catalogUpdate(catalog, updates);
	}

}
