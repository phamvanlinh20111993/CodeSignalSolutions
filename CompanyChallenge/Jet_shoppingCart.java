package CompanyChallenge;

import java.util.ArrayList;
import java.util.HashMap;
public class Jet_shoppingCart {

	static String[] shoppingCart(String[] requests) {

		int i = 0, j = 0;

		HashMap<String, Integer> hmap = new HashMap<>();
		ArrayList<String> key = new ArrayList<String>();
		ArrayList<String> type = new ArrayList<String>();
		for (;i < requests.length; i++) {
			
			String[] s = requests[i].split(" : ");
			if(!requests[i].equals("checkout")){
					type.add(s[0]);
					key.add(s[1]);
			}
			
			if (s[0].equals("add")) {
				hmap.put(s[1], 1);
			} else if (s[0].equals("remove")) {
				if (hmap.get(s[1]) != null)
					hmap.remove(s[1]);
			} else if (s[0].equals("checkout")) {
				if (hmap.size() > 0)
					hmap.clear();
			} else if (s[0].equals("quantity_upd")) {
				Integer num = Integer.parseInt(s[2].substring(1, s[2].length()));
				if (s[2].charAt(0) == '-')
					num = -num;
				if (hmap.get(s[1]) != null)
					hmap.put(s[1], hmap.get(s[1]) + num);
			}

		}

		String[] result = new String[hmap.size()];
			
		for(i = 0; i < type.size(); i++) {
			if(type.get(i).equals("remove")) {
				String tmp = key.get(i);
				for(j = 0; j <= i; j++) {
					if(key.get(j).equals(tmp)) {
						key.set(j, "remove");
					}
				}
			}
		}
		
		for (i = 0; i < key.size(); i++)
			if(key.equals("remove"))
				key.remove(i);
		
		
		i = 0;
		for(j = 0; j < key.size(); j++) {
			if(hmap.get(key.get(j)) != null) {
				result[i] = key.get(j) + " : " + hmap.get(key.get(j));
				i++;
				hmap.remove(key.get(j));
			}
		}

		for (i = 0; i < result.length; i++)
			System.out.println(result[i]);

		return result;
	}

	public static void main(String[] args) {

		// String[] requests = { "add : milk", "add : pickles", "remove : milk",
		// "checkout", "add : milk",
		// "quantity_upd : milk : +3", "quantity_upd : milk : -2" };

		// shoppingCart(requests);

		String[] requests = { "add : milk", "add : pickles", "add : fruitz", "add : vegetables", "add : computer",
				"add : whattheawesomeshopisit", "quantity_upd : computer : +2", "remove : computer", "remove : milk",
				"add : computer", "quantity_upd : fruitz : +100", "add : computer mouse", "add : computer monitor",
				"quantity_upd : computer mouse : +3", "quantity_upd : computer mouse : +5",
				"quantity_upd : computer : +3", "quantity_upd : fruitz : -50", "add : fruitz seed" };

		shoppingCart(requests);

	}

}
