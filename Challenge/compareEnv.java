package Challenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class compareEnv {

	static String Key(int key) {
		String s = "";
		switch (key) {
			case 0:
				s = "KEY is different";
				break;
			case 1:
				s = "KEY is only in A ";
				break;
			default:
				s = "KEY is only in B";
				break;
		}

		return s;
	}

	static String[] compareEnv1(String[] a, String[] b) {

		HashMap<String, String> Enva = new HashMap<>();
		HashMap<String, String> Envb = new HashMap<>();
		List<String> re = new ArrayList<>();
		
		int i = 0;
		for(i = 0; i < a.length; i++) {
			String [] temp = a[i].split("=");
			Enva.put(temp[0], temp[1]);
		}
		
		for(i = 0; i < b.length; i++) {
			String [] temp = b[i].split("=");
			Envb.put(temp[0], temp[1]);
		}
		
		for(String tmp:Enva.keySet()) {
			if(Envb.get(tmp) != null) {
				if(!Envb.get(tmp).equals(Enva.get(tmp)))
					re.add(tmp + " " + Key(0));
			}else {
				re.add(tmp + " " + Key(1));
			}
		}
		
		for(String tmp:Envb.keySet()) {
			if(Enva.get(tmp) == null) {
				re.add(tmp + " " + Key(4));
			}
		}
		
		Collections.sort(re);
		for(i = 0; i < re.size(); i++)
			System.out.println(re.get(i));
		String [] result = re.toArray(new String[re.size()]);
		return result;
	}

	public static void main(String[] args) {

		/**
		 * test 1
		 */
		String[] a = { "Port=3000", "DbUser=serviceuser", "DbPassword=foobar", "TimeOut=500" },
				b = { "TimeOut=5000", "DbPassword=fizzbuzz", "DbUser=serviceuser", "EnableFoo=true" };

		compareEnv1(a, b);

	}

}
