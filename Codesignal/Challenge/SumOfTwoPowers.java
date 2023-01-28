package Codesignal.Challenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SumOfTwoPowers {
	public static void main(String [] args) {
		int n = 2465, k = 2, i = 0, j = 0;
		double s = Math.pow(n, (double)1/k);
		int left = 0, right = 0;
		j = (int)s;
		Map<Integer, Integer> abc = new HashMap<>();
		
		while(i <= j) {
			left = (int)Math.pow(i, k);
			right = (int)Math.pow(j, k);
			if(left + right == n) {
				abc.put(left, right);
				i++;
				j--;
			}else {
				if(left + right < n) i++;
				else j--;
			}
		}
		i = 0;
		 int [][] bb = new int[abc.size()][2];
		 for(int a:abc.keySet()) {
			 bb[i][0]= a;
			 bb[i][1] = abc.get(a);
			 i++;
		 }
		 
		ArrayList<Integer> l = new ArrayList<>();
		ArrayList<Integer> r = new ArrayList<>();
	}
}
