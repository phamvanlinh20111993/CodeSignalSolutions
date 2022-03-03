package CompanyChallenge.Codesignal;

import java.util.ArrayList;
import java.util.TreeMap;

public class CodeSignal_opponentMatching {

	static int[][] opponentMatching(int[] xp) {

		int i = 0, j = 0, k = 0, z = 0, t = 0, x = 0, res = 0, l = 0, y = 0, n = 0;
		ArrayList<Integer> temp = new ArrayList<>();
		int[][] result = new int[xp.length / 2][];

		if (xp.length == 1)
			return result;

		for (; i < xp.length; i++)
			temp.add(i);

		TreeMap<Integer, Integer> map = new TreeMap<>();
		for (i = 0; i < xp.length; i++) {
			if (map.get(xp[i]) == null)
				map.put(xp[i], temp.get(i));
			else {
				result[t] = new int[2];
				result[t][0] = map.get(xp[i]);
				result[t][1] = temp.get(i);
				map.remove(xp[i]);
				t++;
			}
		}

		temp = new ArrayList<>();
		for (Integer te : map.keySet()) {
			temp.add(te);
		}
		
		i = 0;
		if(temp.size()%2 != 0) 	n = 1;
		
		while(temp.size() > n) {
			z = Integer.MAX_VALUE;
			i = 0;
	
			while(i < temp.size()-1) {
				j = i+1;
				res = Math.abs(temp.get(i) - temp.get(j));
				if(res < z) {
					z = res;
					k = map.get(temp.get(i)); 
					l = map.get(temp.get(j));
					
					x = i;
					y = j;
				}
				i++;
			}
			
			result[t] = new int[2];
			if(k < l) {
				result[t][0] = k;
				result[t][1] = l;
		    } else {
				result[t][0] = l;
				result[t][1] = k;
			}

			temp.remove(y);
			temp.remove(x);
			t++;
		}
		
		for(i = 0; i < result.length; i++) {
			System.out.println(result[i][0] + "    " + result[i][1]);
		}
		
		return result;
	}

	public static void main(String[] args) {

		System.out.println("######################test (1)########################");
		int[] xp = { 200, 100, 70, 130, 100, 800, 810 };
		opponentMatching(xp);

		System.out.println("######################test (2)########################");
		int[] xp1 = { 1, 1000000000 };
		opponentMatching(xp1);

		System.out.println("######################test (3)########################");
		int[] xp2 = { 1, 5, 11, 3, 1, 16, 100 };
		opponentMatching(xp2);

		System.out.println("######################test (4)########################");
		int[] xp3 = { 1000000000, 100000000, 1 };
		opponentMatching(xp3);

		System.out.println("######################test (5)########################");
		int[] xp4 = { 100, 100, 100, 200, 134, 100, 100, 134, 134, 200 };
		opponentMatching(xp4);

		System.out.println("######################test (6)########################");
		int[] xp5 = { 100, 130, 100, 2610, 1434, 100, 300, 1304, 134, 200 };
		opponentMatching(xp5);

		System.out.println("######################test (7)########################");
		int[] xp6 = { 100, 130, 100 };
		opponentMatching(xp6);
		
		System.out.println("######################test (8)########################");
		int [] xp7 = {10, 20, 25, 26};
		opponentMatching(xp7);
	}

}
